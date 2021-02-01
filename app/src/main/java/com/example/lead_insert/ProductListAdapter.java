package com.example.lead_insert;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class ProductListAdapter extends ArrayAdapter<ProductListModel> implements View.OnClickListener{
    private Context mContext;
    ArrayList<ProductListModel> productList;



    private static class ViewHolder {
        TextView txtproduct_name;
        TextView txtquantity;
        TextView txtunit_price;
        TextView txttax;
        TextView txttotal;
    }



    // Constructor.
    public ProductListAdapter(Context c,ArrayList<ProductListModel> productList)
    {
        super(c, R.layout.product_list,productList);
        mContext = c;
        this.productList=productList;
    }

    public int getCount() {
        return productList.size();
    }

    @Override
    public void onClick(View v) {
//        int position=(Integer) v.getTag();
//        Object productobject= getItem(position);
//        ProductDetailing ProductActivity = (ProductDetailing) productobject;

    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ProductListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.product_list_item, parent, false);
            viewHolder.txtproduct_name = (TextView) convertView.findViewById(R.id.txt_product_name);
            viewHolder.txtquantity = (TextView) convertView.findViewById(R.id.txt_product_qty);
            viewHolder.txtunit_price = (TextView) convertView.findViewById(R.id.txt_unit_price);
            viewHolder.txttax = (TextView) convertView.findViewById(R.id.txt_product_tax);
            viewHolder.txttotal = (TextView) convertView.findViewById(R.id.txt_total);


            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ProductListAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.txtproduct_name.setText("Product Name:"+productList.get(position).getProduct_name());
        viewHolder.txtquantity.setText("Qty:"+productList.get(position).getQuantity());
        viewHolder.txtunit_price.setText("Unit Price:"+productList.get(position).getUnit_price());
        viewHolder.txttax.setText("Tax:"+productList.get(position).getTax());
        viewHolder.txttotal.setText("Total:"+productList.get(position).getTotal());


        return convertView;

    }



}





