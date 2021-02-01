package com.example.lead_insert;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
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

public class OpportunityInsertion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinCustomer, spinProduct, spinActivityType, spinEmployeeName, spinSalesTeam, spinTags;
//    LinearLayout linearLayout;
    EditText Opport, ExpRev, Probab, Date, Email, Phone, Mobile, Notes;
    RatingBar bar;
    Button btnCancel, btnSave;
    TextView txtopprthead;
    ImageView arrowopport;
    static Context context;


    final Calendar mcalendar = Calendar.getInstance();

    public static void goToProduct() {
        Intent product = new Intent(context,OpportunityList.class);
        context.startActivity(product);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opportunity_insertion_activity);



//        context = this;
        opportInitialize();
        Opportspinner();
        Opportcalend();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    void opportInitialize() {

        txtopprthead = (TextView)findViewById(R.id.headopprt);
         Opport = (EditText) findViewById(R.id.editopp);
         ExpRev = (EditText) findViewById(R.id.editrevenue);
         Probab = (EditText) findViewById(R.id.editprobability);
         Date = (EditText) findViewById(R.id.editdate);
         Email = (EditText) findViewById(R.id.editemail);
         Phone = (EditText) findViewById(R.id.editphn);
         Mobile = (EditText) findViewById(R.id.editmobile);
         Notes = (EditText) findViewById(R.id.note);
         spinCustomer = (Spinner) findViewById(R.id.customer);
         spinProduct = (Spinner) findViewById(R.id.product);
         spinActivityType = (Spinner) findViewById(R.id.activity);
         spinEmployeeName = (Spinner) findViewById(R.id.empname);
         spinSalesTeam = (Spinner) findViewById(R.id.salesteam);
         spinTags = (Spinner) findViewById(R.id.tags);

         bar = (RatingBar) findViewById(R.id.rating);
        LayerDrawable stars = (LayerDrawable) bar.getProgressDrawable();
//        stars.getDrawable(0).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_OUT);
        stars.getDrawable(1).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        arrowopport = (ImageView)findViewById(R.id.backarrowaddopport);
        arrowopport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

         btnCancel = (Button) findViewById(R.id.btnCancelOpp);

         btnSave = (Button) findViewById(R.id.btnSaveOpp);
         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Opportvalidate();
             }
         });
     }

      void Opportspinner() {

        spinCustomer.setOnItemSelectedListener(this);
        List<String> custom = new ArrayList<String>();
        custom.add("Customer");
        custom.add("Customer 1");

        spinProduct.setOnItemSelectedListener(this);
        List<String> pr = new ArrayList<String>();
        pr.add("Product");
        pr.add("Chemicals");

        spinActivityType.setOnItemSelectedListener(this);
        List<String> acttype = new ArrayList<String>();
        acttype.add("Activity Type");
        acttype.add(" Entertainment");

        spinEmployeeName.setOnItemSelectedListener(this);
        List<String> ename = new ArrayList<String>();
        ename.add("Employee Name");
        ename.add("ABC");

        spinSalesTeam.setOnItemSelectedListener(this);
        List<String> st = new ArrayList<String>();
        st.add("Sales Team");
        st.add("Sales Team 1");

        spinTags.setOnItemSelectedListener(this);
        List<String> tg = new ArrayList<String>();
        tg.add("Tags");
        tg.add("tags 1");

                // Creating adapter for spinner
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, custom);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pr);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, acttype);
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ename);
                ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, st);
                ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tg);

                // Drop down layout style - list view with radio button
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                // attaching data adapter to spinner
                spinCustomer.setAdapter(adapter1);
                spinProduct.setAdapter(adapter2);
                spinActivityType.setAdapter(adapter3);
                spinEmployeeName.setAdapter(adapter4);
                spinSalesTeam.setAdapter(adapter5);
                spinTags.setAdapter(adapter6);
    }


    void Opportcalend(){

        String date_n = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
//        Date.setText(date_n);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mcalendar.set(Calendar.YEAR, year);
                mcalendar.set(Calendar.MONTH, month);
                mcalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Date.setText(sdf.format(mcalendar.getTime()));
            }
        };

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OpportunityInsertion.this, date, mcalendar
                        .get(Calendar.YEAR), mcalendar.get(Calendar.MONTH),
                        mcalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    void Opportvalidate() {


                if (Opport.getText().toString().length() == 0) {
                    Opport.requestFocus();
                    Opport.setError("Please Enter Opportunity");
                    Opport.setFocusable(true);

                } else if (ExpRev.getText().toString().trim().length() == 0) {
                    ExpRev.requestFocus();
                    ExpRev.setError("Please Enter Expected Revenue");
                    ExpRev.setFocusable(true);

                } else if (ExpRev.getText().toString().trim().length() < 100) {
                    ExpRev.requestFocus();
                    ExpRev.setError("Please Provide Valid Expected Revenue");
                    ExpRev.setFocusable(true);

                } else if (ExpRev.getText().toString().trim().length() > 50000000) {
                    ExpRev.requestFocus();
                    ExpRev.setError("Company Name Maximum Length must be 50000000");
                    ExpRev.setFocusable(true);

                } else if (Probab.getText().toString().length() == 0) {
                    Probab.requestFocus();
                    Probab.setError("Please Enter Probability");
                    Probab.setFocusable(true);

                } else if (spinCustomer.getSelectedItem().equals("Customer")) {
                    Toast.makeText(context, "Please Select Customer", Toast.LENGTH_SHORT).show();

                } else if (spinProduct.getSelectedItem().equals("Product")) {
                    Toast.makeText(context, "Please Select Product", Toast.LENGTH_SHORT).show();

                } else if (spinActivityType.getSelectedItem().equals("Activity Type")) {
                    Toast.makeText(context, "Please Select Activity Type", Toast.LENGTH_SHORT).show();

                } else if (Email.getText().length() > 0) {
                    Log.e("Tes", "editEmailId length" + Email.getText().length());
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!Email.getText().toString().trim().matches(emailPattern)) {
                        Log.e("Tes", "match");
                    /* return true;
                } else {*/
                        Log.e("Test", "not match");
                        Email.requestFocus();
                        Email.setError("Please Provide Valid Email");
                        Email.setFocusable(true);

                    } else if (Phone.getText().length() > 0) {
                        if (Phone.getText().length() < 10 || Phone.getText().length() > 15) {
                            Phone.requestFocus();
                            Phone.setError("Please Provide Valid Phone");
                            Phone.setFocusable(true);

                        } else if (spinEmployeeName.getSelectedItem().equals("Employee Name")) {
                            Toast.makeText(context, "Please Select Employee Name", Toast.LENGTH_SHORT).show();

                        } else if (spinSalesTeam.getSelectedItem().equals("Sales Team")) {
                            Toast.makeText(context, "Please Select Sales Team", Toast.LENGTH_SHORT).show();

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