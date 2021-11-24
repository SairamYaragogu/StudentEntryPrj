package com.app.studententryprj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.studententryprj.adapter.StudentNoteAdapter;
import com.app.studententryprj.databinding.ActivityMainBinding;
import com.app.studententryprj.room.entity.StudentNote;
import com.app.studententryprj.util.Utils;
import com.app.studententryprj.viewmodel.StudentNoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    StudentNoteAdapter studentNoteAdapter;
    List<StudentNote> list;
    StudentNoteViewModel studentNoteViewModel;
    StudentNote studentNote = new StudentNote();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initRecyclerView();
        setClickListeners();
        initViewModel();
        studentNoteObserver();
    }

    private void initRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        studentNoteAdapter = new StudentNoteAdapter(this, list, new SelectedOption() {
            @Override
            public void option(String option) {
                callUpdate(option.split(",")[0],option.split(",")[1]);
            }

            @Override
            public void position(int pos) {

            }
        });
        binding.recyclerView.setAdapter(studentNoteAdapter);
    }
    private void setClickListeners(){
        binding.btnCreate.setOnClickListener(this);
    }
    private void initViewModel(){
        studentNoteViewModel = ViewModelProviders.of(this).get(StudentNoteViewModel.class);
    }
    private void callInsert(String name, String email){
        studentNote.setStudentName(name);
        studentNote.setStudentEmail(email);
        studentNoteViewModel.insert(studentNote);
    }
    private void callUpdate(String name, String email){
        studentNote.setStudentName(name);
        studentNote.setStudentEmail(email);
        studentNoteViewModel.update(studentNote);
    }
    private void studentNoteObserver(){
        studentNoteViewModel = ViewModelProviders.of(this).get(StudentNoteViewModel.class);
        studentNoteViewModel.getAllNotes().observe(this, new Observer<List<StudentNote>>() {
            @Override
            public void onChanged(List<StudentNote> studentNotes) {
                binding.tvRecordCount.setText(studentNotes.size()+" records found.");
                list.addAll(studentNotes);
                studentNoteAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreate:
                Utils.showInputDialog(this, "Create", true, true,"","", new SelectedOption() {
                    @Override
                    public void option(String option) {
                        String[] arr = option.split(",");
                        callInsert(arr[0],arr[1]);
                    }

                    @Override
                    public void position(int pos) {

                    }
                });
        }
    }
}