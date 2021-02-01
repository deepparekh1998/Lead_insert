package com.example.lead_insert;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LeadInsertion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinTitle, spinCountry, spinState, spinCity, spinSales, spinProduct, spinTags;
//    LinearLayout linearLayout;
    EditText Desc, Comp, Cont, Add1, Add2, Add3, Pincode, Email, Phone, Mobile, Fax, Job, Notes;
    RatingBar bar;
    Button btnCancel, btnSave;
    Context context;
    ArrayList<LeadListModel> dataModels;
    FloatingActionButton floatButton;
    ListView Leadlist;
    ImageView arrowlead;
    TextView txtleadhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_insertion_activity);





        context = this;

        leadinitialize();
        leadspinner();
    }

   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    void leadinitialize() {

        txtleadhead = (TextView)findViewById(R.id.headlead);

        Desc = (EditText) findViewById(R.id.editdesc);
        Comp = (EditText) findViewById(R.id.editcomp);
        Cont = (EditText) findViewById(R.id.editcont);
        Add1 = (EditText) findViewById(R.id.editadd1);
        Add2 = (EditText) findViewById(R.id.editadd2);
        Add3 = (EditText) findViewById(R.id.editadd3);
        Pincode = (EditText) findViewById(R.id.editpin);
        Email = (EditText) findViewById(R.id.editemail);
        Phone = (EditText) findViewById(R.id.editphn);
        Mobile = (EditText) findViewById(R.id.editmobile);
        Fax = (EditText) findViewById(R.id.editfax);
        Job = (EditText) findViewById(R.id.editjob);
        Notes = (EditText) findViewById(R.id.note);
        bar = (RatingBar) findViewById(R.id.rating);

        LayerDrawable stars = (LayerDrawable) bar.getProgressDrawable();
//        stars.getDrawable(0).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_OUT);
        stars.getDrawable(1).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        spinTitle = (Spinner) findViewById(R.id.title);
        spinCountry = (Spinner) findViewById(R.id.country);
        spinState = (Spinner) findViewById(R.id.state);
        spinCity = (Spinner) findViewById(R.id.city);
        spinSales = (Spinner) findViewById(R.id.sales);
        spinProduct = (Spinner) findViewById(R.id.product);
        spinTags = (Spinner) findViewById(R.id.tags);
        arrowlead = (ImageView)findViewById(R.id.backarrowaddlead);
        arrowlead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadvalidate();
            }
        });
    }


    void leadspinner() {

        spinTitle.setOnItemSelectedListener(this);
        List<String> title = new ArrayList<String>();
        title.add("Title");
        title.add("Title 1");

        spinCountry.setOnItemSelectedListener(this);
        List<String> country = new ArrayList<String>();
        country.add("Country");
        country.add("India");

        spinState.setOnItemSelectedListener(this);
        List<String> state = new ArrayList<String>();
        state.add("State");
        state.add("Gujarat");

        spinCity.setOnItemSelectedListener(this);
        List<String> city = new ArrayList<String>();
        city.add("City");
        city.add("Baroda");

        spinSales.setOnItemSelectedListener(this);
        List<String> sp = new ArrayList<String>();
        sp.add("Sales Person");
        sp.add("Sales Person 1");

        spinProduct.setOnItemSelectedListener(this);
        List<String> pr = new ArrayList<String>();
        pr.add("Product");
        pr.add("Chemicals");

        spinTags.setOnItemSelectedListener(this);
        List<String> tg = new ArrayList<String>();
        tg.add("Tags");
        tg.add("tags 1");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, title);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, country);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, state);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, city);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sp);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pr);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tg);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinTitle.setAdapter(dataAdapter);
        spinCountry.setAdapter(adapter);
        spinState.setAdapter(adapter1);
        spinCity.setAdapter(adapter2);
        spinSales.setAdapter(adapter3);
        spinProduct.setAdapter(adapter4);
        spinTags.setAdapter(adapter5);
    }




    void leadvalidate() {

        if (Desc.getText().toString().length() == 0) {
            Desc.requestFocus();
            Desc.setError("Please Enter Description");
            Desc.setFocusable(true);
//                    return false;

        } else if (spinTitle.getSelectedItem().equals("Title")) {
            Toast.makeText(context, "Please Select Title", Toast.LENGTH_SHORT).show();

        } else if (Comp.getText().toString().trim().length() == 0) {
            Comp.requestFocus();
            Comp.setError("Please Enter Company Name");
            Comp.setFocusable(true);
//                    return false;

        } else if (Comp.getText().toString().trim().length() < 3) {
            Comp.requestFocus();
            Comp.setError("Please Provide Valid Company Name");
            Comp.setFocusable(true);
//                    return false;

        } else if (Comp.getText().toString().trim().length() > 50) {
            Comp.requestFocus();
            Comp.setError("Company Name Maximum Length must be 50");
            Comp.setFocusable(true);
//                    return false;

        } else if (Cont.getText().toString().trim().length() == 0) {
            Cont.requestFocus();
            Cont.setError("Please Enter Contact Name");
            Cont.setFocusable(true);
//                    return false;

        } else if (Cont.getText().toString().trim().length() < 3) {
            Cont.requestFocus();
            Cont.setError("Please Provide Valid Contact Name");
            Cont.setFocusable(true);
//                    return false;

        } else if (Cont.getText().toString().trim().length() > 20) {
            Cont.requestFocus();
            Cont.setError("Contact Name Maximum Length must be 20");
            Cont.setFocusable(true);
//                    return false;

        } else if (Add1.getText().toString().trim().length() == 0) {
            Add1.requestFocus();
            Add1.setError("Please Enter Address");
            Add1.setFocusable(true);
//                    return false;

        } else if (Add1.getText().toString().trim().length() > 50) {
            Add1.requestFocus();
            Add1.setError(" Address1  Maximum Length must be 50");
            Add1.setFocusable(true);
//                    return false;

        } else if (Add2.getText().toString().trim().length() == 0) {
            Add2.requestFocus();
            Add2.setError("Please Enter Address");
            Add2.setFocusable(true);
//                    return false;

        } else if (Add2.getText().toString().trim().length() > 50) {
            Add2.requestFocus();
            Add2.setError("Address2 Maximum Length must be 50");
            Add2.setFocusable(true);
//                    return false;

        } else if (Add3.getText().toString().trim().length() == 0) {
            Add3.requestFocus();
            Add3.setError("Please Enter Address");
            Add3.setFocusable(true);
//                    return false;

        } else if (Add3.getText().toString().trim().length() > 50) {
            Add3.requestFocus();
            Add3.setError("Address3 Maximum Length must be 50");
            Add3.setFocusable(true);
//                    return false;

        } else if (Pincode.getText().length() == 0) {
            Pincode.requestFocus();
            Pincode.setError("Enter Pincode");
            Pincode.setFocusable(true);
//                      return false;
        } else if (Pincode.getText().length() < 6 || Pincode.getText().length() > 6) {
            Pincode.requestFocus();
            Pincode.setError("Please Provide Valid Pincode");
            Pincode.setFocusable(true);
//                      return false;

        } else if (spinCountry.getSelectedItem().equals("Country")) {
            Toast.makeText(context, "Please Select Country", Toast.LENGTH_SHORT).show();

        } else if (spinState.getSelectedItem().equals("State")) {
            Toast.makeText(context, "Please Select State", Toast.LENGTH_SHORT).show();

        } else if (spinCity.getSelectedItem().equals("City")) {
            Toast.makeText(context, "Please Select City", Toast.LENGTH_SHORT).show();

        } else if (Email.getText().length() > 0) {
            Log.e("Tes", "editEmailId length" + Email.getText().length());
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (!Email.getText().toString().trim().matches(emailPattern)) {
                Log.e("Tes", "match");
                    /* return true;
                } else {*/
                Log.e("Test", "not match");
                Email.requestFocus();
                Email.setError("Please Provide Valid Email Id");
                Email.setFocusable(true);
//                            return false;

            } else if (Fax.getText().length() > 0) {
                if (Fax.getText().length() < 8 || Fax.getText().length() > 10) {
                    Fax.requestFocus();
                    Fax.setError("Please Provide Valid Fax No.");
                    Fax.setFocusable(true);
//                                return false;
                }
            } else if (Phone.getText().length() > 0) {
                if (Phone.getText().length() < 10 || Phone.getText().length() > 15) {
                    Phone.requestFocus();
                    Phone.setError("Please Provide Valid Phone No.");
                    Phone.setFocusable(true);
//                                return false;
                }
            } else if (Mobile.getText().length() > 0) {
                if (Mobile.getText().length() < 10 || Mobile.getText().length() > 10) {
                    Mobile.requestFocus();
                    Mobile.setError("Please Provide Valid Mobile No.");
                    Mobile.setFocusable(true);
//                                return false;

                } else if (Job.getText().toString().trim().length() == 0) {
                    Job.requestFocus();
                    Job.setError("Please Enter Job Position");
                    Job.setFocusable(true);

                } else if (Job.getText().toString().trim().length() > 30) {
                    Job.requestFocus();
                    Job.setError("Job Position Maximum Length must be 50");
                    Job.setFocusable(true);

                } else if (spinSales.getSelectedItem().equals("Sales Person")) {
                    Toast.makeText(context, "Please Select Sales Person", Toast.LENGTH_SHORT).show();

                } else if (spinProduct.getSelectedItem().equals("Product")) {
                    Toast.makeText(context, "Please Select Product", Toast.LENGTH_SHORT).show();

                } else if (spinTags.getSelectedItem().equals("Tags")) {
                    Toast.makeText(context, "Please Select Tags", Toast.LENGTH_SHORT).show();

                } else if (Notes.getText().toString().trim().length() > 30) {
                    Notes.requestFocus();
                    Notes.setError("Notes Maximum Length must be 250");
                    Notes.setFocusable(true);
                }
            }
        }
    }
}


