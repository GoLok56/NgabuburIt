package io.github.golok56.ngabuburit.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.github.golok56.ngabuburit.R;
import io.github.golok56.ngabuburit.entities.ToDo;

public class DetailActivity extends AppCompatActivity {
    private ToDo todo;

    private TextView tvNama;
    private TextView tvDeskripsi;
    private TextView tvStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        todo = getIntent().getParcelableExtra("todo");

        tvNama = findViewById(R.id.textViewNamaTodoActivityDetail);
        tvDeskripsi = findViewById(R.id.textViewDetailTodoActivityDetail);
        tvStatus = findViewById(R.id.textViewStatusTodoActivityDetail);

        tvNama.setText(todo.getNama());
        tvDeskripsi.setText(todo.getDeskripsi());

        String status = todo.getStatus() == 0 ? "Belum Selesai" : "Selesai";
        tvStatus.setText(status);
    }
}
