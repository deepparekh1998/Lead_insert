package com.example.lead_insert;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

public class QuotationListAdapter extends ArrayAdapter<QuotationListModel> implements View.OnClickListener{

    private ArrayList<QuotationListModel> quotationData;
    Context qcontext;
    Button btnviewproduct;

    // View lookup cache
    private static class ViewHolder {
        TextView txtCustomer_Name;
        TextView txtQuotation_Date;
        TextView txtQuotation_No;
        TextView txtPayment;
        TextView txtTags;
        TextView txtDivisions;
        Button btnviewproduct;

    }


    public QuotationListAdapter(ArrayList<QuotationListModel> qdata, Context context) {
        super(context, R.layout.quotation_list_item,qdata);
        this.quotationData = qdata;
        this.qcontext = context;
    }

    public void updateList(ArrayList<QuotationListModel> filteredQuotList) {
        quotationData = filteredQuotList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {


//        int position = (Integer) v.getTag();
//        Object quotationobject = getItem(position);
//        QuotationList quotationActivity = (QuotationList) quotationobject;

    }

    private int lastPosition = -1;
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        QuotationListModel quotationModel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        QuotationListAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new QuotationListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.quotation_list_item, parent, false);
            viewHolder.txtCustomer_Name = (TextView) convertView.findViewById(R.id.quot_customername);
            viewHolder.txtQuotation_Date = (TextView) convertView.findViewById(R.id.quotorder_date);
            viewHolder.txtQuotation_No = (TextView) convertView.findViewById(R.id.quot_orderno);
            viewHolder.txtPayment = (TextView) convertView.findViewById(R.id.quot_payment);
            viewHolder.txtTags = (TextView) convertView.findViewById(R.id.quot_tags);
            viewHolder.txtDivisions = (TextView) convertView.findViewById(R.id.quot_division);
            viewHolder.btnviewproduct = (Button) convertView.findViewById(R.id.btnquot_view_product);

            result=convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (QuotationListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;

        viewHolder.txtCustomer_Name.setText("Customer Name: "+quotationModel.getCustomer_name());
        viewHolder.txtQuotation_Date.setText(quotationModel.getQuotation_date());
        viewHolder.txtQuotation_No.setText("Quotation No: "+quotationModel.getQuotation_no());
        viewHolder.txtPayment.setText("Payment Terms: "+quotationModel.getPayment());
        viewHolder.txtTags.setText("Tag: "+quotationModel.getTags());
        viewHolder.txtDivisions.setText("Divisions: "+quotationModel.getDivisions());
        viewHolder.btnviewproduct.setOnClickListener(this);

        View finalConvertView = convertView;
        viewHolder.btnviewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SalesOrderAdapter.this, ProductDetailing.class);
//                startActivity(intent);

//                SalesOrderInsertion.goToProduct();
                Intent product = new Intent(finalConvertView.getContext(),ProductList.class);
                product.putExtra("from", "Quotation list");
                finalConvertView.getContext().startActivity(product);
            }
        });
        notifyDataSetChanged();

        viewHolder.btnviewproduct.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
