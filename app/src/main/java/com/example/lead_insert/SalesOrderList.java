package com.example.lead_insert;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class SalesOrderList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<SalesListModel> ModelSalesData;
    FloatingActionButton floatButtonSales;
    ListView Saleslist;
    EditText  editsearchSales, editsalesStartDate, editsalesEndDate;
    ImageView imgSalesfilt;
    LinearLayout Saleslinearfilt;
    TextView txtheadsaleslist;



    Spinner spinSalesPayment, spinSalesTags, spinSalesDivision;
    Button btnapply;

    final Calendar salescalendar = Calendar.getInstance();


    private static SalesOrderAdapter salesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesorder_list);
        setTitle("Sales");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SalesListInitialize();
        SalesListSearch();
        showhidesalesfilt();
        SalesListspinner();
        SalesListStartOrderDate();
        SalesListEndOrderDate();

        ModelSalesData = new ArrayList<>();

        ModelSalesData.add(new SalesListModel("Parekh Nikunj", "27/01/2021", "001", "Cash","TG1","1"));
        ModelSalesData.add(new SalesListModel("Patel Chirag", "27/01/2021", "002", "Net Banking","TG2","2"));
        ModelSalesData.add(new SalesListModel("Nilkanth Jani", "27/01/2021", "003", "Cash","TG3","3"));
        ModelSalesData.add(new SalesListModel("Bhargav Patel", "27/01/2021", "004", "Cash","TG4","4"));

        salesAdapter = new SalesOrderAdapter(ModelSalesData, SalesOrderList.this);

        Saleslist.setAdapter(salesAdapter);

        Saleslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SalesListModel data = ModelSalesData.get(position);

            }
        });


    }

    void SalesListInitialize() {

        txtheadsaleslist = (TextView)findViewById(R.id.headsaleslist);
        Saleslinearfilt = (LinearLayout) findViewById(R.id.linear_salesfilter);
        editsearchSales = (EditText) findViewById(R.id.sales_search);
        editsalesStartDate = (EditText) findViewById(R.id.editsales_startdate);
        editsalesEndDate = (EditText) findViewById(R.id.editsales_enddate);
        imgSalesfilt = (ImageView) findViewById(R.id.imagesalesfilt);
        spinSalesPayment = (Spinner) findViewById(R.id.spinner_sales_payment);
        spinSalesTags = (Spinner) findViewById(R.id.spinner_sales_tags);
        spinSalesDivision = (Spinner) findViewById(R.id.spinner_sales_division);
        Saleslist = (ListView) findViewById(R.id.sales_list);
        btnapply = (Button)findViewById(R.id.btnApply);


        floatButtonSales = (FloatingActionButton) findViewById(R.id.floating_action_button_search);

        floatButtonSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesOrderList.this, SalesOrderInsertion.class);
                startActivity(intent);
            }
        });
    }

    void SalesListSearch() {
        editsearchSales.addTextChangedListener(new TextWatcher() {
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
                ArrayList<SalesListModel> filteredSalesList = new ArrayList<>();
                for (SalesListModel salesitem : ModelSalesData) {
                    if (salesitem.getCustomer_name().toUpperCase().contains(toString.toUpperCase())) {
                        filteredSalesList.add(salesitem);
                    }
                }
                salesAdapter.updateList(filteredSalesList);
            }
        });
    }

    void showhidesalesfilt() {
        imgSalesfilt.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            @Override
            public void onClick(View v) {
                visible = !visible;
                Saleslinearfilt.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
    }

    void SalesListspinner() {

        spinSalesPayment.setOnItemSelectedListener(this);
        List<String> pay = new ArrayList<String>();
        pay.add("Payment Term");
        pay.add("Online");
        pay.add("Cash on Delivery");

        spinSalesTags.setOnItemSelectedListener(this);
        List<String> tgs = new ArrayList<String>();
        tgs.add("Tags");
        tgs.add("Tag 1");
        tgs.add("Tag 2");

        spinSalesDivision.setOnItemSelectedListener(this);
        List<String> salesdiv = new ArrayList<String>();
        salesdiv.add("Division");
        salesdiv.add("Division 1");
        salesdiv.add("Division 2");

        //Creating Adapter for spinner
        ArrayAdapter<String> adapterpayment = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pay);
        ArrayAdapter<String> adaptertags = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tgs);
        ArrayAdapter<String> adapterdivision = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, salesdiv);

        // Drop down layout style - list view with radio button
        adapterpayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptertags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterdivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinSalesPayment.setAdapter(adapterpayment);
        spinSalesTags.setAdapter(adaptertags);
        spinSalesDivision.setAdapter(adapterdivision);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    void SalesListStartOrderDate() {
        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//                editsalesStartDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                salescalendar.set(Calendar.YEAR, year);
                salescalendar.set(Calendar.MONTH, month);
                salescalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editsalesStartDate.setText(sdf.format(salescalendar.getTime()));
            }
        };

        editsalesStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SalesOrderList.this, date,
                        salescalendar.get(Calendar.YEAR), salescalendar.get(Calendar.MONTH),
                        salescalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void SalesListEndOrderDate() {
        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//        editsalesEndDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                salescalendar.set(Calendar.YEAR, year);
                salescalendar.set(Calendar.MONTH, month);
                salescalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editsalesEndDate.setText(sdf.format(salescalendar.getTime()));
            }
        };

        editsalesEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SalesOrderList.this, date, salescalendar
                        .get(Calendar.YEAR), salescalendar.get(Calendar.MONTH),
                        salescalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

}
