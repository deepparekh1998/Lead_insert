package com.example.lead_insert;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OpportunityList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<OpportunityListModel> ModelData;
    FloatingActionButton floatButton;
    ListView Opplist;
    EditText  editsearchOpp, editStartDate, editEndDate;
    ImageView imgOppfilt;
    LinearLayout Opplinearfilt;
    TextView txtheadopplist;

    Spinner spinnerStatus;

    final Calendar oppcalendar = Calendar.getInstance();


    private static OpportunityListAdapter oppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opportunity_list);
        setTitle("Opportunity");


        txtheadopplist = (TextView)findViewById(R.id.headlistopport);
        Opplinearfilt = (LinearLayout)findViewById(R.id.linearfilter);
        editsearchOpp = (EditText) findViewById(R.id.opport_search);
        editStartDate = (EditText)findViewById(R.id.editstartdate);
        editEndDate = (EditText)findViewById(R.id.editenddate);
        imgOppfilt = (ImageView)findViewById(R.id.imageoppfilt);
        spinnerStatus = (Spinner) findViewById(R.id.status);
        Opplist = (ListView) findViewById(R.id.opp_list);

        floatButton = (FloatingActionButton) findViewById(R.id.floating_action_button);


        searchoption();
        showhideoppfilt();
        spinStatus();
        showStartDate();
        showEndDate();


        ModelData = new ArrayList<>();

        ModelData.add(new OpportunityListModel("21/01/2020", "1", "Deep Parekh", "40000", "22/01/2020", "Bhavesh Patel"));
        ModelData.add(new OpportunityListModel("21/01/2020", "2", "Nilkanth Jani", "10000", "22/01/2020", "Keval Patel"));
        ModelData.add(new OpportunityListModel("21/01/2020", "3", "Karan Bhatt", "60000", "22/01/2020", "Mayur Barot"));
        ModelData.add(new OpportunityListModel("21/01/2020", "4", "Ishaan Patel", "50000", "22/01/2020", "Monil Patel"));

        oppAdapter = new OpportunityListAdapter(ModelData, OpportunityList.this);

        Opplist.setAdapter(oppAdapter);

        Opplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OpportunityListModel oppdata = ModelData.get(position);
            }
        });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpportunityList.this, OpportunityInsertion.class);
                startActivity(intent);
            }
        });
    }


        void searchoption() {
            editsearchOpp.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // filter your list from your input
                    filter(s.toString());
                    //you can use runnable postDelayed like 500 ms to delay search text
                }

                private void filter(String toString) {
                    ArrayList<OpportunityListModel> filteredOpportList = new ArrayList<>();
                    for (OpportunityListModel oppitem : ModelData) {
                        if (oppitem.getCustomer_name().toUpperCase().contains(toString.toUpperCase())) {
                            filteredOpportList.add(oppitem);
                        }
                    }
                    oppAdapter.updateList(filteredOpportList);
                }
            });
        }

    void showhideoppfilt() {
        imgOppfilt.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            @Override
            public void onClick(View v) {
                visible = !visible;
                Opplinearfilt.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
    }

    void showStartDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -7);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        String formattedPreviousDate = df.format(c.getTime());
        editStartDate.setText(formattedPreviousDate);
    }

    void showEndDate() {
        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
        editEndDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                oppcalendar.set(Calendar.YEAR, year);
                oppcalendar.set(Calendar.MONTH, month);
                oppcalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editEndDate.setText(sdf.format(oppcalendar.getTime()));
            }
        };

        editEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OpportunityList.this, date, oppcalendar
                        .get(Calendar.YEAR), oppcalendar.get(Calendar.MONTH),
                        oppcalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void spinStatus () {
        spinnerStatus.setOnItemSelectedListener(this);
        List<String> status = new ArrayList<String>();
        status.add("Status");
        status.add("All");
        status.add("New");
        status.add("Qualified");
        status.add("Warned");
        status.add("Closed");


        //Creating Adapter for SpinnerStatus
        ArrayAdapter<String> OppAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);


        // Drop down layout style - list view with radio button
        OppAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerStatus.setAdapter(OppAdapter);
    }

        @Override
        public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){

        }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }

}

