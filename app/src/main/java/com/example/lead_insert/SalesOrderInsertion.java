package com.example.lead_insert;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SalesOrderInsertion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinSalesCustomer, spinSalesPayment, spinSalesTags, spinSalesDivision;
    EditText SalesOrderDate, SalesExpiryDate, CustomerPreference, SalesNote;
    Button btnSalesCancel, btnSalesAddProduct;
    ImageView arrowsales;
    TextView txtheadsales;
    static Context context;

    final Calendar cald = Calendar.getInstance();

    public static void goToProduct() {
        Intent product = new Intent(context,ProductDetailing.class);
        context.startActivity(product);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesorder_insertion_activity);


        SalesInitialize();
        Salesspinner();
        SalesCalendOrderDate();
        SalesCalendExpiryDate();
        Salesvalidate();

    }

    void SalesInitialize() {

        txtheadsales = (TextView)findViewById(R.id.headsales);
        spinSalesCustomer = (Spinner)findViewById(R.id.spinner_sales_customer);
        SalesOrderDate = (EditText) findViewById(R.id.edit_sales_orderdate);
        SalesExpiryDate = (EditText) findViewById(R.id.edit_sales_expirydate);
        spinSalesPayment = (Spinner)findViewById(R.id.spinner_sales_payment);
        spinSalesTags = (Spinner)findViewById(R.id.spinner_sales_tags);
        spinSalesDivision = (Spinner)findViewById(R.id.spinner_sales_division);
        CustomerPreference = (EditText) findViewById(R.id.edit_sales_customerpref);
        SalesNote = (EditText) findViewById(R.id.edit_sales_note);

        context = this;
        btnSalesCancel = (Button) findViewById(R.id.btn_sales_cancel);

        btnSalesAddProduct = (Button) findViewById(R.id.btn_sales_addProduct);
        btnSalesAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesOrderInsertion.this, ProductDetailing.class);
                startActivity(intent);
            }
        });
        arrowsales = (ImageView)findViewById(R.id.backarrowaddsales);
        arrowsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void Salesspinner() {

        spinSalesCustomer.setOnItemSelectedListener(this);
        List<String> custom = new ArrayList<String>();
        custom.add("Customer");
        custom.add("Customer 1");

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

        // Creating adapter for spinner
        ArrayAdapter<String> adaptercustomer = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, custom);
        ArrayAdapter<String> adapterpayment = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pay);
        ArrayAdapter<String> adaptertags = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tgs);
        ArrayAdapter<String> adapterdivision = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, salesdiv);

        // Drop down layout style - list view with radio button
        adaptercustomer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterpayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptertags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterdivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinSalesCustomer.setAdapter(adaptercustomer);
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
    
    void SalesCalendOrderDate() {
        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//                SalesOrderDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cald.set(Calendar.YEAR, year);
                cald.set(Calendar.MONTH, month);
                cald.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                SalesOrderDate.setText(sdf.format(cald.getTime()));
            }
        };

        SalesOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SalesOrderInsertion.this, date,
                        cald.get(Calendar.YEAR), cald.get(Calendar.MONTH),
                        cald.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void SalesCalendExpiryDate() {
        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//        SalesExpiryDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cald.set(Calendar.YEAR, year);
                cald.set(Calendar.MONTH, month);
                cald.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                SalesExpiryDate.setText(sdf.format(cald.getTime()));
            }
        };

        SalesExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SalesOrderInsertion.this, date, cald
                        .get(Calendar.YEAR), cald.get(Calendar.MONTH),
                        cald.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void Salesvalidate() {

        if (spinSalesCustomer.getSelectedItem().equals("Customer")) {
            Toast.makeText(context, "Please Select Sales Customer", Toast.LENGTH_SHORT).show();

        } else if (spinSalesPayment.getSelectedItem().equals("Payment Term")) {
            Toast.makeText(context,"Please Select Proper Payment Term", Toast.LENGTH_SHORT).show();

        } else if (spinSalesTags.getSelectedItem().equals("Tags")) {
            Toast.makeText(context,"Please Select Sales Tags", Toast.LENGTH_SHORT).show();

        } else if (spinSalesDivision.getSelectedItem().equals("Division")) {
            Toast.makeText(context,"Please Select Sales Division", Toast.LENGTH_SHORT).show();

        } else if (CustomerPreference.getText().toString().trim().length() == 0) {
            CustomerPreference.requestFocus();
            CustomerPreference.setError("Please Enter Customer Reference");
            CustomerPreference.setFocusable(true);

        } else if (CustomerPreference.getText().toString().trim().length() < 3) {
            CustomerPreference.requestFocus();
            CustomerPreference.setError("Please Provide Valid Customer Reference");
            CustomerPreference.setFocusable(true);

        } else if (CustomerPreference.getText().toString().trim().length() > 50) {
            CustomerPreference.requestFocus();
            CustomerPreference.setError("Customer Reference Maximum Length must be 50");
            CustomerPreference.setFocusable(true);

        }else if (SalesNote.getText().toString().trim().length() > 30) {
            SalesNote.requestFocus();
            SalesNote.setError("Notes Maximum Length must be 250");
            SalesNote.setFocusable(true);
        }
    }
}
