package com.app.studententryprj.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.studententryprj.room.entity.StudentNote;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student_table")
    LiveData<List<StudentNote>> getAllNotes();

    @Insert
    void Insert(StudentNote note);

    @Update
        //(onConflict = OnConflictStrategy.REPLACE)
    void Update(StudentNote note);

    @Delete
    void Delete(StudentNote note);
}
