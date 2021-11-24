package com.app.studententryprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.studententryprj.MainActivity;
import com.app.studententryprj.R;
import com.app.studententryprj.SelectedOption;
import com.app.studententryprj.databinding.StudentItemRowBinding;
import com.app.studententryprj.room.entity.StudentNote;
import com.app.studententryprj.util.Utils;

import java.util.List;

public class StudentNoteAdapter extends RecyclerView.Adapter<StudentNoteAdapter.MyViewHolder> {
    Context myCtx;
    List<StudentNote> list;
    SelectedOption selectedOption;
    public StudentNoteAdapter(Context myCtx, List<StudentNote> list, SelectedOption selectedOption) {
        this.myCtx = myCtx;
        this.list = list;
        this.selectedOption = selectedOption;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentItemRowBinding bindingView = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.student_item_row,parent,false);
        return new MyViewHolder(bindingView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindingView.setData(list.get(position));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Utils.showInputDialog(myCtx, "Update", true, true,list.get(position).getStudentName(),list.get(position).getStudentEmail(), new SelectedOption() {
                    @Override
                    public void option(String option) {
                        selectedOption.option(option);
                    }

                    @Override
                    public void position(int pos) {

                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        StudentItemRowBinding bindingView;
        public MyViewHolder(@NonNull StudentItemRowBinding bindingView) {
            super(bindingView.getRoot());
            this.bindingView = bindingView;
        }
    }
}
