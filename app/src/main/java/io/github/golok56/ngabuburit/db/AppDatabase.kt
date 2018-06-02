package io.github.golok56.ngabuburit.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import io.github.golok56.ngabuburit.db.dao.ToDoDao
import io.github.golok56.ngabuburit.entities.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(ctx: Context) = instance?: Room.databaseBuilder(
                ctx,
                AppDatabase::class.java,
                "todolist"
        ).build()
    }
}