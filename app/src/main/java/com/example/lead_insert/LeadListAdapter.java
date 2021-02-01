package com.example.lead_insert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LeadListAdapter extends ArrayAdapter<LeadListModel> implements View.OnClickListener {

    private ArrayList<LeadListModel> dataSet;
    Context mcontext;

    public void updateList(ArrayList<LeadListModel> filteredList) {
        dataSet = filteredList;
        notifyDataSetChanged();
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtLeadDesc;
        TextView txtCompName;
        TextView txtContName;
        TextView txtMobile;
    }

    public LeadListAdapter(ArrayList<LeadListModel> data, Context context) {
        super(context, R.layout.lead_list_item,data);
        this.dataSet = data;
        this.mcontext = context;
    }
    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        LeadListModel leadListActivity = (LeadListModel) object;

    }
    private int lastPosition = -1;
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        LeadListModel dataModel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lead_list_item, parent, false);
            viewHolder.txtLeadDesc = (TextView) convertView.findViewById(R.id.lead_desc);
            viewHolder.txtCompName = (TextView) convertView.findViewById(R.id.lead_comp);
            viewHolder.txtContName = (TextView) convertView.findViewById(R.id.lead_contname);
            viewHolder.txtMobile = (TextView) convertView.findViewById(R.id.lead_mob);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;


        viewHolder.txtLeadDesc.setText("Lead Description: "+dataModel.getLead_description());
        viewHolder.txtCompName.setText("Company Name: "+dataModel.getCompany_name());
        viewHolder.txtContName.setText("Contact Name: "+dataModel.getContact_name());
        viewHolder.txtMobile.setText("Mobile Number: "+dataModel.getMobile());

        // Return the completed view to render on screen
        return convertView;
    }
}
