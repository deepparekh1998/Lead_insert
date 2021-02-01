package com.example.lead_insert;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class OpportunityListAdapter extends ArrayAdapter<OpportunityListModel> implements View.OnClickListener, OpportStatusChoice.SingleChoiceListener {

    private ArrayList<OpportunityListModel> dataSet;
    Context mcontext;
    Spinner spinnerStatus;
    Button btnchange;


    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        btnchange.setTag("Selected Item  = " + list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {
        btnchange.setText("Change Status");
    }

    public void updateList(ArrayList<OpportunityListModel> filteredOpportList) {
        dataSet = filteredOpportList;
        notifyDataSetChanged();
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtDate;
        TextView txtOppDesc;
        TextView txtCustName;
        TextView txtExpectedRevenue;
        TextView txtExpectedClosingDate;
        TextView txtEmployeeName;
        Button btnChange;
    }

    public OpportunityListAdapter(ArrayList<OpportunityListModel> data, Context context) {
        super(context, R.layout.opportunity_list_item, data);
        this.dataSet = data;
        this.mcontext = context;


//        btnchange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DialogFragment OpportStatusChoice =  new OpportStatusChoice();
////                OpportStatusChoice.setCancelable(false);
////                OpportStatusChoice.show(context,"Status Type");
//            }
//
//        });
    }

    @Override
    public void onClick(View v) {

//        int position=(Integer) v.getTag();
//        Object object= getItem(position);
//        OpportunityListModel opportunityActivity = (OpportunityListModel) object;

    }

    private int lastPosition = -1;

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        OpportunityListModel ModelData = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.opportunity_list_item, parent, false);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.textview_date);
            viewHolder.txtOppDesc = (TextView) convertView.findViewById(R.id.opp_desc);
            viewHolder.txtCustName = (TextView) convertView.findViewById(R.id.opp_custname);
            viewHolder.txtExpectedRevenue = (TextView) convertView.findViewById(R.id.opp_revenue);
            viewHolder.txtExpectedClosingDate = (TextView) convertView.findViewById(R.id.textclosedate);
            viewHolder.txtEmployeeName = (TextView) convertView.findViewById(R.id.opp_empname);
            viewHolder.btnChange = (Button) convertView.findViewById(R.id.btnstatus);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        lastPosition = position;

        viewHolder.txtDate.setText("Date: " + ModelData.getDate());
        viewHolder.txtOppDesc.setText("Opportunity Desc: " + ModelData.getOpp_description());
        viewHolder.txtCustName.setText("Customer Name: " + ModelData.getCustomer_name());
        viewHolder.txtExpectedRevenue.setText("Expected Revenue: " + ModelData.getExpected_revenue());
        viewHolder.txtExpectedClosingDate.setText("Expected ClosingDate: " + ModelData.getExpected_closingdate());
        viewHolder.txtEmployeeName.setText("Employee Name: " + ModelData.getEmployee_name());
//        viewHolder.btnChange.setOnClickListener(this);
        viewHolder.btnChange.setTag(position);

        View finalConvertView = convertView;
//        viewHolder.btnChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(SalesOrderAdapter.this, ProductDetailing.class);
////                startActivity(intent);
//
////                SalesOrderInsertion.goToProduct();
//                Intent product = new Intent(finalConvertView.getContext(), OpportStatusChoice.class);
//                finalConvertView.getContext().startActivity(product);
//            }
//        });
        notifyDataSetChanged();

        viewHolder.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.opportunity_popup_list, null);
                int width = 800;
                int height =800;
                Button cancel, change;
                RadioGroup radioGroupOpport;
                RadioButton radioQualified, radioWon, radioClosed;
                cancel = popupView.findViewById(R.id.btn_close);
                change = popupView.findViewById(R.id.btn_chnage);
                radioGroupOpport = popupView.findViewById(R.id.radiogroup_opport);
                radioQualified = popupView.findViewById(R.id.radioBtn_quailfy);
                radioWon = popupView.findViewById(R.id.radioBtn_won);
                radioClosed = popupView.findViewById(R.id.radioBtn_closed);

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

                //Set the location of the window on the screen
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                //Initialize the elements of our window, install the handler
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
//                radioGroupOpport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                    }
//                });
            }

        });



        viewHolder.btnChange.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
