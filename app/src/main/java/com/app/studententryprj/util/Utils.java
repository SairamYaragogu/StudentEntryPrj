package com.app.studententryprj.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.app.studententryprj.R;
import com.app.studententryprj.SelectedOption;

public class Utils {
    public static void showAlertDialog(Context myCtx, String message){
        View dialogView = LayoutInflater.from(myCtx).inflate(R.layout.custom_alert_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(myCtx);
        builder.setView(dialogView);

        TextView tvMessage=dialogView.findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        AlertDialog alertDialog = null;
        try {
            alertDialog = builder.create();
            alertDialog.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        TextView btnOk=dialogView.findViewById(R.id.btnOk);
        TextView btnCancel=dialogView.findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.GONE);

        AlertDialog finalAlertDialog = alertDialog;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalAlertDialog.dismiss();
            }
        });
    }
    public static void showInputDialog(Context myCtx,String btnName, boolean showCancelBtn,boolean isCancelable,String name,String email, SelectedOption selectedOption){
        View dialogView = LayoutInflater.from(myCtx).inflate(R.layout.input_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(myCtx);
        builder.setView(dialogView);

        EditText etStudentName=dialogView.findViewById(R.id.etStudentName);
        EditText etStudentEmail=dialogView.findViewById(R.id.etStudentEmail);

        AlertDialog alertDialog = builder.create();
        if(isCancelable)
            alertDialog.setCancelable(true);
        else alertDialog.setCancelable(false);
        alertDialog.show();

        TextView btnOk=dialogView.findViewById(R.id.btnOk);
        btnOk.setText(btnName);
        TextView btnCancel=dialogView.findViewById(R.id.btnCancel);

        if(btnName.equalsIgnoreCase("Update")){
            etStudentName.setText(name);
            etStudentEmail.setText(email);
        }

        if(!showCancelBtn){
            btnCancel.setVisibility(View.GONE);
        }
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etStudentName.getText().toString().isEmpty() && !etStudentEmail.getText().toString().isEmpty()){
                    selectedOption.option(etStudentName.getText().toString()+","+etStudentEmail.getText().toString());
                    alertDialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
