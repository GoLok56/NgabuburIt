package io.github.golok56.ngabuburit.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.github.golok56.ngabuburit.entities.ToDo;

@Dao
public interface ToDoDao {
    @Query("SELECT * FROM ToDo")
    public List<ToDo> getAll();

    @Insert
    public void insert(ToDo todo);

    @Update
    public void edit(ToDo todo);
}
