package com.example.lead_insert;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class QuotationList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    
    ArrayList<QuotationListModel> quotationModelData;
    FloatingActionButton floatButtonquotation;
    ListView quotationList;
    EditText  editsearchQuotation, editquotationStartDate, editquotationEndDate;
    ImageView imgquotationfilt;
    LinearLayout quotationlinearfilt, barlayout;
    TextView txtquotorderno, txtquotcustomername, textlisthead;;

    Spinner spinQuotationPayment, spinQuotationTags, spinQuotationDivision;
    Button btn_quotationApply;

    final Calendar quotationCalendar = Calendar.getInstance();

    private static QuotationListAdapter quotationAdapter;




    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quotation_list);
        setTitle("Quotation");

        Log.d("Product","Title done");




        Log.d("Quotation","Getting chnaged list");

        quotationList = (ListView)findViewById(R.id.quot_list);
        Log.d("Product","Getting list");

        quotationListInitialize();
        quotationListSearch();
        showhidequotationfilt();
        quotationListspinner();
        quotationListStartDate();
        quotationListEndDate();
        changeList();

        quotationModelData = new ArrayList<>();

        quotationModelData.add(new QuotationListModel("Parekh Nikunj", "27/01/2021", "001", "Cash","TG1","1"));
        quotationModelData.add(new QuotationListModel("Patel Chirag", "27/01/2021", "002", "Net Banking","TG2","2"));
        quotationModelData.add(new QuotationListModel("Nilkanth Jani", "27/01/2021", "003", "Cash","TG3","3"));
        quotationModelData.add(new QuotationListModel("Bhargav Patel", "27/01/2021", "004", "Cash","TG4","4"));

        quotationAdapter = new QuotationListAdapter(quotationModelData, QuotationList.this);

        quotationList.setAdapter(quotationAdapter);

        Log.d("Product","Adapter setted");

        quotationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuotationListModel data = quotationModelData.get(position);

            }
        });
    }

    void quotationListInitialize() {

        barlayout = (LinearLayout)findViewById(R.id.appBarLayout);
        textlisthead = (TextView)findViewById(R.id.headlistquot);

        txtquotorderno = (TextView)findViewById(R.id.quot_orderno);
        txtquotcustomername = (TextView)findViewById(R.id.quot_customername);

        quotationlinearfilt = (LinearLayout) findViewById(R.id.linear_quotfilter);
        editsearchQuotation = (EditText) findViewById(R.id.quot_search);
        editquotationStartDate = (EditText) findViewById(R.id.editquot_startdate);
        editquotationEndDate = (EditText) findViewById(R.id.editquot_enddate);
        imgquotationfilt = (ImageView) findViewById(R.id.imagequotfilt);
        spinQuotationPayment = (Spinner) findViewById(R.id.spinner_quot_payment);
        spinQuotationTags = (Spinner) findViewById(R.id.spinner_quot_tags);
        spinQuotationDivision = (Spinner) findViewById(R.id.spinner_quot_division);
        quotationList = (ListView) findViewById(R.id.quot_list);
        btn_quotationApply = (Button)findViewById(R.id.btnquotApply);


        floatButtonquotation = (FloatingActionButton) findViewById(R.id.floating_action_button_search);

        floatButtonquotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuotationList.this, QuotationInsertion.class);
                startActivity(intent);
            }
        });

    }

    void quotationListSearch() {

        editsearchQuotation.addTextChangedListener(new TextWatcher() {
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
                ArrayList<QuotationListModel> filteredQuotList = new ArrayList<>();
                for (QuotationListModel quotitem : quotationModelData) {
                    if (quotitem.getCustomer_name().toUpperCase().contains(toString.toUpperCase())) {
                        filteredQuotList.add(quotitem);
                    }
                }
                quotationAdapter.updateList(filteredQuotList);
            }
        });
    }

    void showhidequotationfilt() {

        imgquotationfilt.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            @Override
            public void onClick(View v) {
                visible = !visible;
                quotationlinearfilt.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });

    }

    void quotationListspinner() {

        spinQuotationPayment.setOnItemSelectedListener(this);
        List<String> qoutpay = new ArrayList<String>();
        qoutpay.add("Payment Term");
        qoutpay.add("Online");
        qoutpay.add("Cash on Delivery");

        spinQuotationTags.setOnItemSelectedListener(this);
        List<String> qoutgs = new ArrayList<String>();
        qoutgs.add("Tags");
        qoutgs.add("Tag 1");
        qoutgs.add("Tag 2");

        spinQuotationDivision.setOnItemSelectedListener(this);
        List<String> qoutdiv = new ArrayList<String>();
        qoutdiv.add("Division");
        qoutdiv.add("Division 1");
        qoutdiv.add("Division 2");

        //Creating Adapter for spinner
        ArrayAdapter<String> adapterqoutpayment = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qoutpay);
        ArrayAdapter<String> adapterqouttags = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qoutgs);
        ArrayAdapter<String> adapterqoutdivision = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qoutdiv);

        // Drop down layout style - list view with radio button
        adapterqoutpayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterqouttags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterqoutdivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinQuotationPayment.setAdapter(adapterqoutpayment);
        spinQuotationTags.setAdapter(adapterqouttags);
        spinQuotationDivision.setAdapter(adapterqoutdivision);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    void quotationListStartDate() {

        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//                editquotationStartDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                quotationCalendar.set(Calendar.YEAR, year);
                quotationCalendar.set(Calendar.MONTH, month);
                quotationCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editquotationStartDate.setText(sdf.format(quotationCalendar.getTime()));
            }
        };

        editquotationStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuotationList.this, date,
                        quotationCalendar.get(Calendar.YEAR), quotationCalendar.get(Calendar.MONTH),
                        quotationCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    void quotationListEndDate() {

        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//            editquotationEndDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                quotationCalendar.set(Calendar.YEAR, year);
                quotationCalendar.set(Calendar.MONTH, month);
                quotationCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editquotationEndDate.setText(sdf.format(quotationCalendar.getTime()));
            }
        };

        editquotationEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuotationList.this, date, quotationCalendar
                        .get(Calendar.YEAR), quotationCalendar.get(Calendar.MONTH),
                        quotationCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void changeList() {

    }
}
