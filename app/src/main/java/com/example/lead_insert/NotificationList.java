package com.example.lead_insert;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotificationList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView listnotify;
    ArrayList<NotificationListModel> notificationModels;
    TextView  txtheadnotif, txtnotification_message, txtdatetime,txtnotfytype;
//    ImageView arrownotificationlist;
    Context notcontext;



    private static NotificationListAdapter notifadapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_list);

        Log.d("Notification", "Title done");
        notcontext = this;


        listnotify = (ListView) findViewById(R.id.list_notification);
        txtnotification_message = (TextView)findViewById(R.id.txt_notify_msg);
        txtdatetime = (TextView)findViewById(R.id.txt_datetime);
        txtnotfytype = (TextView)findViewById(R.id.txt_notify_type);
//        arrownotificationlist = (ImageView)findViewById(R.id.backarrowaddnotiflist);
//        arrownotificationlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        Log.d("Notification", "Getting list");



        notificationModels = new ArrayList<>();

        notificationModels.add(new NotificationListModel("Please Select Your ID", "30/01/2021 09:20am","New"));
        notificationModels.add(new NotificationListModel("Unsufficient Balance", "30/01/2021 11:15am","Alert"));
        notificationModels.add(new NotificationListModel("Task Submitted","30/01/2021 10:00am","Report"));

        notifadapt = new NotificationListAdapter(NotificationList.this, notificationModels);

        listnotify.setAdapter(notifadapt);

        Log.d("Notification", "Adapter setted");

        listnotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotificationListModel data = notificationModels.get(position);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
