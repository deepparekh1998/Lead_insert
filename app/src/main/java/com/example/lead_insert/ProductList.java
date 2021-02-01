package com.example.lead_insert;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ListView listproduct;
    ArrayList<ProductListModel> productModels;
    TextView txtsales_orderno, txtcustomername, txtheadlistprod;
    ImageView arrowproductlist;
    String from;

    private static ProductListAdapter proadapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        Log.d("Product","Title done");

        Intent intent = getIntent();


        listproduct = (ListView)findViewById(R.id.list_product);
        
        txtsales_orderno = (TextView)findViewById(R.id.product_orderno);
        from = getIntent().getStringExtra("from");
        if (from.equalsIgnoreCase("Sales Order")){
            txtsales_orderno.setText("Sales Order No");
        } else if(from.equalsIgnoreCase("Quotation list")) {
            txtsales_orderno.setText("Quotation No");
        }





        Log.d("String", "List Changed");

        txtcustomername = (TextView)findViewById(R.id.product_customername);
        txtheadlistprod = (TextView)findViewById(R.id.headprodlist);

        arrowproductlist = (ImageView)findViewById(R.id.backarrowaddprodtlist);
        arrowproductlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.d("Product","Getting list");

        productModels = new ArrayList<>();

        productModels.add(new ProductListModel("FibreWires", "10", "250", "2%","2500"));
        productModels.add(new ProductListModel("Santizers", "15", "80", "2.5%","1000"));
        productModels.add(new ProductListModel("Perfumes", "18", "500", "3%","1800"));
        productModels.add(new ProductListModel("Plastic", "25", "350", "5%","2000"));

        proadapt = new ProductListAdapter( ProductList.this,productModels);

        listproduct.setAdapter(proadapt);

        Log.d("Product","Adapter setted");

        listproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductListModel data = productModels.get(position);
            }
        });
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
}
