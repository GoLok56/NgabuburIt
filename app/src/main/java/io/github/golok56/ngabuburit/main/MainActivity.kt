package io.github.golok56.ngabuburit.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.golok56.ngabuburit.R
import io.github.golok56.ngabuburit.addtodo.AddToDoActivity
import io.github.golok56.ngabuburit.db.AppDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)
        database = AppDatabase.getInstance(this)

        Single.fromCallable { database.getToDoDao().getAll() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { todoList -> adapter.todos = todoList }

        recyclerviewTodolistActivityMain.adapter = adapter

        fabAddNewTodoActivityMain.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }
    }

}
