package io.github.golok56.ngabuburit.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Callable;

import io.github.golok56.ngabuburit.R;
import io.github.golok56.ngabuburit.db.AppDatabase;
import io.github.golok56.ngabuburit.detail.DetailActivity;
import io.github.golok56.ngabuburit.entities.ToDo;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context ctx;
    private AppDatabase database;
    private List<ToDo> todos;

    public MainAdapter(Context ctx) {
        this.ctx = ctx;
        database = AppDatabase.getInstance(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_todo_activity_main,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ToDo todo = todos.get(position);

        holder.tvNama.setText(todo.getNama());

        int idImg = todo.getStatus() == 0 ? R.drawable.baseline_check_box_outline_blank_black_24dp :
                R.drawable.baseline_check_box_black_24dp;
        holder.imgStatus.setImageResource(idImg);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailActivity.class)
                        .putExtra("todo", todo);
                ctx.startActivity(intent);
            }
        });

        holder.imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newStatus = todo.getStatus() == 0 ? 1 : 0;
                todo.setStatus(newStatus);

                Completable
                        .fromCallable(new Callable<Void>() {
                            @Override
                            public Void call() {
                                database.getToDoDao().edit(todo);
                                return null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos != null ? todos.size() : 0;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama;
        private ImageView imgStatus;

        ViewHolder(View itemView) {
            super(itemView);

            imgStatus = itemView.findViewById(R.id.imageViewStatusTodoItemTodo);
            tvNama = itemView.findViewById(R.id.textViewTodoNameItemTodo);
        }
    }
}
