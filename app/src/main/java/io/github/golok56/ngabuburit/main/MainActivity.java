package io.github.golok56.ngabuburit.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.concurrent.Callable;

import io.github.golok56.ngabuburit.R;
import io.github.golok56.ngabuburit.addtodo.AddToDoActivity;
import io.github.golok56.ngabuburit.db.AppDatabase;
import io.github.golok56.ngabuburit.entities.ToDo;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private MainAdapter adapter;

    private RecyclerView rvTodos;
    private FloatingActionButton fabAddNew;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MainAdapter(this);
        database = AppDatabase.getInstance(this);

        Single
                .fromCallable(new Callable<List<ToDo>>() {
                    @Override
                    public List<ToDo> call() {
                        return database.getToDoDao().getAll();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ToDo>>() {
                    @Override
                    public void accept(List<ToDo> todos) {
                        adapter.setTodos(todos);
                    }
                });

        rvTodos = findViewById(R.id.recyclerviewTodolistActivityMain);
        rvTodos.setAdapter(adapter);

        fabAddNew = findViewById(R.id.fabAddNewTodoActivityMain);
        fabAddNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivity(intent);
            }
        });
    }
}
