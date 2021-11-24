package com.app.studententryprj.room.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.studententryprj.room.dao.StudentDao;
import com.app.studententryprj.room.entity.StudentNote;

@Database(entities = StudentNote.class, version = 1)
public abstract class StudentNoteDatabase extends RoomDatabase {
    private static StudentNoteDatabase instance;
    public abstract StudentDao studentDao();

    public static synchronized StudentNoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentNoteDatabase.class , "student_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // new PopulateDb(instance).execute();
        }
    };
}
