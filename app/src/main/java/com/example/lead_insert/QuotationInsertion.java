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
import android.widget.LinearLayout;
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

public class QuotationInsertion extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinQuotCustomer, spinQuotPayment, spinQuotTags, spinQuotDivision;
    EditText quotDate, quotExpiryDate, quotCustomerPreference, quotNote;
    Button btnQuotCancel, btnQuotAddProduct;
    TextView texthead;
    ImageView arrowquot;
     static Context context;

    final Calendar quotcalen = Calendar.getInstance();

    public static void goToProduct() {
        Intent product = new Intent(context,ProductDetailing.class);
        context.startActivity(product);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quotation_insertion_activity);

        context = this;

        quotationInitialize();
        quotationSpinner();
        quotationCalendDate();
        quotationCalendExpiryDate();
        quotationValidate();

    }

    void quotationInitialize() {


        texthead = (TextView)findViewById(R.id.headquot);

        spinQuotCustomer = (Spinner)findViewById(R.id.spinner_quotation_customer);
        quotDate = (EditText) findViewById(R.id.edit_quotationdate);
        quotExpiryDate = (EditText) findViewById(R.id.edit_quotation_expirydate);
        spinQuotPayment = (Spinner)findViewById(R.id.spinner_quotation_payment);
        spinQuotTags = (Spinner)findViewById(R.id.spinner_quotation_tags);
        spinQuotDivision = (Spinner)findViewById(R.id.spinner_quotation_division);
        quotCustomerPreference = (EditText) findViewById(R.id.edit_quotation_customerpref);
        quotNote = (EditText) findViewById(R.id.edit_quotation_note);
        btnQuotCancel = (Button) findViewById(R.id.btn_quotation_cancel);

        arrowquot = (ImageView)findViewById(R.id.backarrowaddquot);
        arrowquot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        context = this;
        btnQuotAddProduct = (Button) findViewById(R.id.btn_quotation_addProduct);

        btnQuotAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuotationInsertion.this, ProductDetailing.class);
                startActivity(intent);
            }
        });
    }

    void quotationSpinner() {

        spinQuotCustomer.setOnItemSelectedListener(this);
        List<String> qtcustom = new ArrayList<String>();
        qtcustom.add("Customer");
        qtcustom.add("Customer 1");

        spinQuotPayment.setOnItemSelectedListener(this);
        List<String> qtpay = new ArrayList<String>();
        qtpay.add("Payment Term");
        qtpay.add("Online");
        qtpay.add("Cash on Delivery");

        spinQuotTags.setOnItemSelectedListener(this);
        List<String> qttgs = new ArrayList<String>();
        qttgs.add("Tags");
        qttgs.add("Tag 1");
        qttgs.add("Tag 2");

        spinQuotDivision.setOnItemSelectedListener(this);
        List<String> qtdiv = new ArrayList<String>();
        qtdiv.add("Division");
        qtdiv.add("Division 1");
        qtdiv.add("Division 2");

        // Creating adapter for spinner
        ArrayAdapter<String> adapterquotcustomer = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, qtcustom);
        ArrayAdapter<String> adapterquotpayment = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qtpay);
        ArrayAdapter<String> adapterquottags = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qttgs);
        ArrayAdapter<String> adapterquotdivision = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qtdiv);

        // Drop down layout style - list view with radio button
        adapterquotcustomer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterquotpayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterquottags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterquotdivision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinQuotCustomer.setAdapter(adapterquotcustomer);
        spinQuotPayment.setAdapter(adapterquotpayment);
        spinQuotTags.setAdapter(adapterquottags);
        spinQuotDivision.setAdapter(adapterquotdivision);

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

    void quotationCalendDate() {

        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//                quotDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                quotcalen.set(Calendar.YEAR, year);
                quotcalen.set(Calendar.MONTH, month);
                quotcalen.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                quotDate.setText(sdf.format(quotcalen.getTime()));
            }
        };

        quotDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuotationInsertion.this, date,
                        quotcalen.get(Calendar.YEAR), quotcalen.get(Calendar.MONTH),
                        quotcalen.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void quotationCalendExpiryDate() {

        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//        quotExpiryDate.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                quotcalen.set(Calendar.YEAR, year);
                quotcalen.set(Calendar.MONTH, month);
                quotcalen.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                quotExpiryDate.setText(sdf.format(quotcalen.getTime()));
            }
        };

        quotExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuotationInsertion.this, date, quotcalen
                        .get(Calendar.YEAR), quotcalen.get(Calendar.MONTH),
                        quotcalen.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void quotationValidate() {

        if (spinQuotCustomer.getSelectedItem().equals("Customer")) {
            Toast.makeText(context, "Please Select Customer", Toast.LENGTH_SHORT).show();

        } else if (spinQuotPayment.getSelectedItem().equals("Payment Term")) {
            Toast.makeText(context, "Please Select Proper Payment Term", Toast.LENGTH_SHORT).show();

        } else if (spinQuotTags.getSelectedItem().equals("Tags")) {
            Toast.makeText(context, "Please Select Tags", Toast.LENGTH_SHORT).show();

        } else if (spinQuotDivision.getSelectedItem().equals("Division")) {
            Toast.makeText(context, "Please Select Division", Toast.LENGTH_SHORT).show();

        } else if (quotCustomerPreference.getText().toString().trim().length() == 0) {
            quotCustomerPreference.requestFocus();
            quotCustomerPreference.setError("Please Enter Customer Reference");
            quotCustomerPreference.setFocusable(true);

        } else if (quotCustomerPreference.getText().toString().trim().length() < 3) {
            quotCustomerPreference.requestFocus();
            quotCustomerPreference.setError("Please Provide Valid Customer Reference");
            quotCustomerPreference.setFocusable(true);

        } else if (quotCustomerPreference.getText().toString().trim().length() > 50) {
            quotCustomerPreference.requestFocus();
            quotCustomerPreference.setError("Customer Reference Maximum Length must be 50");
            quotCustomerPreference.setFocusable(true);

        } else if (quotNote.getText().toString().trim().length() > 30) {
            quotNote.requestFocus();
            quotNote.setError("Notes Maximum Length must be 250");
            quotNote.setFocusable(true);

        }
    }
}
