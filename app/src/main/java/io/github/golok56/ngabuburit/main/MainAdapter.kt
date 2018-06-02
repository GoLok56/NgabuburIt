package io.github.golok56.ngabuburit.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.golok56.ngabuburit.R
import io.github.golok56.ngabuburit.addtodo.AddToDoActivity
import io.github.golok56.ngabuburit.db.AppDatabase
import io.github.golok56.ngabuburit.detail.DetailActivity
import io.github.golok56.ngabuburit.entities.ToDo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_todo_activity_main.view.*

class MainAdapter(private val ctx: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var todos: List<ToDo>? = null
    private val database = AppDatabase.getInstance(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_activity_main, parent, false)
    )

    override fun getItemCount() = todos?.size?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todo = todos?.get(position)
        holder.itemView.textViewTodoNameItemTodo.text = todo?.nama

        val image = if (todo?.status == 0) R.drawable.baseline_check_box_outline_blank_black_24dp
            else R.drawable.baseline_check_box_black_24dp
        holder.itemView.imageViewStatusTodoItemTodo.setImageResource(image)

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, DetailActivity::class.java)
                    .putExtra("todo", todo)
            ctx.startActivity(intent)
        }

        holder.itemView.imageViewStatusTodoItemTodo.setOnClickListener {
            todo?.status = if (todo?.status == 0) 1 else 0
            Completable.fromCallable { database.getToDoDao().edit(todo!!) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            notifyItemChanged(position)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}