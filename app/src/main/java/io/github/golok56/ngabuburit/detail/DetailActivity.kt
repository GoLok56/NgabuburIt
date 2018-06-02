package io.github.golok56.ngabuburit.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.golok56.ngabuburit.R
import io.github.golok56.ngabuburit.entities.ToDo
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var todo: ToDo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        todo = intent.getParcelableExtra("todo")
        textViewDetailTodoActivityDetail.text = todo.deskripsi
        textViewNamaTodoActivityDetail.text = todo.nama
        textViewStatusTodoActivityDetail.text = if (todo?.status == 0) "Belum Selesai" else "Selesai"
    }
}
