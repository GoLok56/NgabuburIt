package io.github.golok56.ngabuburit.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.github.golok56.ngabuburit.entities.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo")
    fun getAll(): List<ToDo>
    @Insert
    fun insert(todo: ToDo)
    @Update
    fun edit(todo: ToDo)
}