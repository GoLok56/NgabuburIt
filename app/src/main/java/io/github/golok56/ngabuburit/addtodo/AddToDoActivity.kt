package io.github.golok56.ngabuburit.addtodo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.golok56.ngabuburit.main.MainActivity
import io.github.golok56.ngabuburit.R
import io.github.golok56.ngabuburit.db.AppDatabase
import io.github.golok56.ngabuburit.entities.ToDo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_todo.*

class AddToDoActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        database = AppDatabase.getInstance(this)
        buttonSaveTodoActivityAddTodo.setOnClickListener { save() }
    }

    private fun save() {
        val nama = editTextNamaTodoActivityAddToDo.text.toString()
        val deskripsi = editTextDeskripsiTodoActivityAddTodo.text.toString()

        Completable
                .fromCallable {
                    database.getToDoDao().insert(ToDo(
                            nama = nama,
                            deskripsi = deskripsi
                    ))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
    }
}