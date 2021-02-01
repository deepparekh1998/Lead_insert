package com.example.lead_insert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LeadList extends AppCompatActivity {
    ListView Leadlist;
    ArrayList<LeadListModel> dataModels;
    FloatingActionButton floatButton;
    EditText searchList;
    TextView txtheadlead_list;


    private static LeadListAdapter adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_list);
        setTitle("Lead Insertion");

        txtheadlead_list = (TextView)findViewById(R.id.headleadlist);
        Leadlist = (ListView) findViewById(R.id.list);
        searchList = (EditText) findViewById(R.id.lead_search);
        floatButton = (FloatingActionButton)findViewById(R.id.floating_action_button);

        searchoption();



        dataModels = new ArrayList<>();

        dataModels.add(new LeadListModel("Good", "Informatics IT Solution", "Deep Parekh", "9979020979"));
        dataModels.add(new LeadListModel("Excellent", "Image Software", "Nilkanth Jani", "9898256832"));
        dataModels.add(new LeadListModel("Fair", "L & T", "Karan Bhatt", "9909256323"));
        dataModels.add(new LeadListModel("Bad", "WiseDV", "Ishaan Patel", "9574475235"));

        adapt = new LeadListAdapter(dataModels, LeadList.this);

        Leadlist.setAdapter(adapt);

        Leadlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LeadListModel data = dataModels.get(position);

            }
        });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeadList.this, LeadInsertion.class);
                startActivity(intent);
            }
        });
    }
    void searchoption() {

        searchList.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }

            private void filter(String toString) {
                ArrayList<LeadListModel> filteredList = new ArrayList<>();
                for (LeadListModel item : dataModels) {
                    if (item.getContact_name().toUpperCase().contains(toString.toUpperCase())) {
                        filteredList.add(item);
                    }
                }
                adapt.updateList(filteredList);
            }
        });

    }

}

