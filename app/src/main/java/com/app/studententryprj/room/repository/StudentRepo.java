package com.app.studententryprj.room.repository;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.appcompat.widget.AppCompatRadioButton$InspectionCompanion;
import androidx.lifecycle.LiveData;

import com.app.studententryprj.room.dao.StudentDao;
import com.app.studententryprj.room.database.StudentNoteDatabase;
import com.app.studententryprj.room.entity.StudentNote;

import java.util.List;


public class StudentRepo {
    private StudentDao studentDao;
    private LiveData<List<StudentNote>> allNotes;

    public StudentRepo(Application application) { //application is subclass of context
        StudentNoteDatabase database = StudentNoteDatabase.getInstance(application);
        studentDao = database.studentDao();
        allNotes = studentDao.getAllNotes();
    }
    public LiveData<List<StudentNote>> getAllNotes() {
        return allNotes;
    }
    public void insert(StudentNote studentNote){
        new InsertNoteAsyncTask(studentDao).execute(studentNote);
    }
    public void update(StudentNote studentNote){
        new UpdateNoteAsyncTask(studentDao).execute(studentNote);
    }
    public void delete(StudentNote studentNote){
        new DeleteNoteAsyncTask(studentDao);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<StudentNote,Void,Void> {
        private StudentDao studentDao;
        public InsertNoteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentNote... studentNotes) {
            studentDao.Insert(studentNotes[0]); //single note
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<StudentNote,Void,Void> {
        private StudentDao studentDao;
        public UpdateNoteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentNote... studentNotes) {
            studentDao.Update(studentNotes[0]); //single note
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<StudentNote,Void,Void> {
        private StudentDao studentDao;
        public DeleteNoteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentNote... studentNotes) {
            studentDao.Delete(studentNotes[0]); //single note
            return null;
        }
    }
}
