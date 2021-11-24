package com.app.studententryprj.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.studententryprj.room.entity.StudentNote;
import com.app.studententryprj.room.repository.StudentRepo;

import java.util.List;

public class StudentNoteViewModel extends AndroidViewModel {
    private StudentRepo studentRepo;
    private LiveData<List<StudentNote>> allNotes;

    public StudentNoteViewModel(@NonNull Application application) {
        super(application);
        studentRepo = new StudentRepo(application);
        allNotes = studentRepo.getAllNotes();
    }

    public LiveData<List<StudentNote>> getAllNotes() {
        if (allNotes == null) {
            allNotes = new MutableLiveData<List<StudentNote>>();
        }
        return allNotes;
    }
    public void insert(StudentNote note) {
        studentRepo.insert(note);
    }
    public void update(StudentNote note) {
        studentRepo.update(note);
    }
    public void delete(StudentNote note) {
        studentRepo.delete(note);
    }
}
