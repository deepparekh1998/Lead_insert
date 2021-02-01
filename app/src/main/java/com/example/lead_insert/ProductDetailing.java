package com.example.lead_insert;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailing extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinProduct, spinTax;
    EditText ProductDescription, ProductQuantity, ProductPrice;
    TextView textTotalAmount, txtprodthead,  textQtyPrice;
    Button btnAddmore, btnSave;
    ImageView arrowproduct;
    Context context;
    String from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detailing_activity);
        context = this;




        ProductInitialize();
        ProductSpinner();
        ProductTotal();
//        ProductValidate();
    }



    void ProductInitialize() {

        txtprodthead = (TextView)findViewById(R.id.headprodt);

        spinProduct = (Spinner)findViewById(R.id.spinner_product);
        ProductDescription = (EditText)findViewById(R.id.edit_product_description);
        ProductQuantity = (EditText)findViewById(R.id.edit_product_quantity);
        ProductQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        ProductPrice = (EditText)findViewById(R.id.edit_product_unitprice);
        ProductPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
        textTotalAmount = (TextView)findViewById(R.id.text_totalamount);
        textQtyPrice = (TextView) findViewById(R.id.text_qty_price);
        spinTax = (Spinner)findViewById(R.id.spinner_tax);

        btnAddmore = (Button) findViewById(R.id.btn_product_addmore);

        btnSave = (Button) findViewById(R.id.btn_product_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductValidate();
            }
        });

//        ProductPrice.addTextChangedListener();
        arrowproduct = (ImageView)findViewById(R.id.backarrowaddprodt);
        arrowproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void ProductSpinner() {
        spinProduct.setOnItemSelectedListener(this);
        List<String> prod = new ArrayList<String>();
        prod.add("Product");
        prod.add("Product 1");

        spinTax.setOnItemSelectedListener(this);
        List<String> tx = new ArrayList<String>();
        tx.add("Tax");
        tx.add("GST");
        tx.add("S-GST");
        tx.add("C-GST");

        // Creating adapter for spinner
        ArrayAdapter<String> adapterproduct = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prod);
        ArrayAdapter<String> adaptertax = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tx);

        // Drop down layout style - list view with radio button
        adapterproduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptertax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinProduct.setAdapter(adapterproduct);
        spinTax.setAdapter(adaptertax);

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

    void ProductTotal() {

        ProductQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double price = Double.parseDouble(!ProductPrice.getText().toString().isEmpty() ?
                        ProductPrice.getText().toString() : "0");
                Double qty = Double.parseDouble(!ProductQuantity.getText().toString().isEmpty() ?
                        ProductQuantity.getText().toString() : "0");
//                Double total = price * qty;
//                textQtyPrice.setText(total.toString());
            }
        });

        ProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double price = Double.parseDouble(!ProductPrice.getText().toString().isEmpty() ?
                        ProductPrice.getText().toString() : "0");

                Double qty = Double.parseDouble(!ProductQuantity.getText().toString().isEmpty() ?
                        ProductQuantity.getText().toString() : "0");
                Double value = price * qty;
                textQtyPrice.setText(value.toString());
            }
        });
    }

    void ProductValidate() {

        if (spinProduct.getSelectedItem().equals("Product")) {
            Toast.makeText(context, "Please Select Product", Toast.LENGTH_SHORT).show();

        } else if (ProductDescription.getText().toString().trim().length() > 30) {
            ProductDescription.requestFocus();
            ProductDescription.setError("Descriptions Maximum Length must be 250");
            ProductDescription.setFocusable(true);

        } else if (spinTax.getSelectedItem().equals("Taxs")) {
            Toast.makeText(context, "Please Select Taxs", Toast.LENGTH_SHORT).show();
        }
    }
}
