package io.github.golok56.ngabuburit.addtodo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Callable;

import io.github.golok56.ngabuburit.R;
import io.github.golok56.ngabuburit.db.AppDatabase;
import io.github.golok56.ngabuburit.entities.ToDo;
import io.github.golok56.ngabuburit.main.MainActivity;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AddToDoActivity extends AppCompatActivity {
    private AppDatabase database;

    private EditText etNama;
    private EditText etDeskripsi;

    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        database = AppDatabase.getInstance(this);

        etNama = findViewById(R.id.editTextNamaTodoActivityAddToDo);
        etDeskripsi = findViewById(R.id.editTextDeskripsiTodoActivityAddTodo);
        btnSave = findViewById(R.id.buttonSaveTodoActivityAddTodo);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        Completable
                .fromCallable(new Callable<Void>() {
                    @Override
                    public Void call() {
                        database.getToDoDao().insert(new ToDo(
                                etNama.getText().toString(),
                                etDeskripsi.getText().toString(),
                                0
                        ));
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AddToDoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
