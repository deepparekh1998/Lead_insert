package com.example.lead_insert;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.lead_insert.SalesOrderInsertion.context;

public class SalesOrderAdapter extends ArrayAdapter<SalesListModel> implements View.OnClickListener {

    private ArrayList<SalesListModel> dataSales;
    Context scontext;
    Button btnviewproduct;

    public void updateList(ArrayList<SalesListModel> filteredSalesList) {
        dataSales = filteredSalesList;
        notifyDataSetChanged();
    }


    // View lookup cache
    private static class ViewHolder {
        TextView txtCustomer_Name;
        TextView txtSalesOrder_Date;
        TextView txtSalesOrder_No;
        TextView txtPayment;
        TextView txtTags;
        TextView txtDivisions;
        Button btnviewproduct;

    }



    public SalesOrderAdapter(ArrayList<SalesListModel> data, Context context) {
        super(context, R.layout.salesorder_list,data);
        this.dataSales = data;
        this.scontext = context;

    }

    @Override
    public void onClick(View v) {
//        int position=(Integer) v.getTag();
//        Object salesobject= getItem(position);
//        SalesOrderList SalesOrderActivity = (SalesOrderList) salesobject;


    }

    private int lastPosition = -1;
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        SalesListModel salesModel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        SalesOrderAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {


            viewHolder = new SalesOrderAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.salesorder_list_item, parent, false);
            viewHolder.txtCustomer_Name = (TextView) convertView.findViewById(R.id.sales_customername);
            viewHolder.txtSalesOrder_Date = (TextView) convertView.findViewById(R.id.salesorder_date);
            viewHolder.txtSalesOrder_No = (TextView) convertView.findViewById(R.id.sales_orderno);
            viewHolder.txtPayment = (TextView) convertView.findViewById(R.id.sales_payment);
            viewHolder.txtTags = (TextView) convertView.findViewById(R.id.sales_tags);
            viewHolder.txtDivisions = (TextView) convertView.findViewById(R.id.sales_division);
            viewHolder.btnviewproduct = (Button) convertView.findViewById(R.id.btnview_product);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SalesOrderAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;

        viewHolder.txtCustomer_Name.setText("Customer Name:"+salesModel.getCustomer_name());
        viewHolder.txtSalesOrder_Date.setText(salesModel.getSales_orderdate());
        viewHolder.txtSalesOrder_No.setText("SalesOrder No:"+salesModel.getSales_orderno());
        viewHolder.txtPayment.setText("Payment:"+salesModel.getPayment());
        viewHolder.txtTags.setText("Tags:"+salesModel.getTags());
        viewHolder.txtDivisions.setText("Division:"+salesModel.getDivisions());
      //  viewHolder.btnviewproduct.setOnClickListener(this);
        viewHolder.btnviewproduct.setTag(position);

        View finalConvertView = convertView;
        viewHolder.btnviewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SalesOrderAdapter.this, ProductDetailing.class);
//                startActivity(intent);

//                SalesOrderInsertion.goToProduct();
                Intent product = new Intent(finalConvertView.getContext(),ProductList.class);
               product.putExtra("from","Sales Order");
                finalConvertView.getContext().startActivity(product);
            }
        });
        notifyDataSetChanged();
        // Return the completed view to render on screen
        return convertView;
    }
}
