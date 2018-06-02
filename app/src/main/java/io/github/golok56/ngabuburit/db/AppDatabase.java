package io.github.golok56.ngabuburit.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import io.github.golok56.ngabuburit.db.dao.ToDoDao;
import io.github.golok56.ngabuburit.entities.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context ctx) {
        if (sInstance == null) sInstance = Room.databaseBuilder(
                ctx,
                AppDatabase.class,
                "todolist"
        ).build();

        return sInstance;
    }

    public abstract ToDoDao getToDoDao();
}
