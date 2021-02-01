package com.example.lead_insert;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OpportStatusChoice extends AppCompatActivity {

    int position = 0;

    public interface SingleChoiceListener {
        void onPositiveButtonClicked(String[] item, int position);

        void onNegativeButtonClicked();
    }

    SingleChoiceListener choiceListener;

    public void onCreateDialog( Bundle savedInstanceState) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OpportStatusChoice.this);
        alertDialog.setTitle("Status Type");
        String[] items = {"Qualified", "Won", "Closed"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(OpportStatusChoice.this, "Clicked on java", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(OpportStatusChoice.this, "Clicked on android", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(OpportStatusChoice.this, "Clicked on Data Structures", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(OpportStatusChoice.this, "Clicked on HTML", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(OpportStatusChoice.this, "Clicked on CSS", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

        OpportStatusChoice.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                choiceListener.onNegativeButtonClicked();
            }
        });

        OpportStatusChoice.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                choiceListener.onPositiveButtonClicked(items, position);
            }
        });
//        return null;
    }
        private static void setPositiveButton (String change, DialogInterface.OnClickListener
        onClickListener){


        }

        private static void setNegativeButton (String close, DialogInterface.OnClickListener
        onClickListener){
        }
}





