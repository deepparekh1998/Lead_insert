package com.example.lead_insert;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationListAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<NotificationListModel> notList;

    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object noftobject= getItem(position);
        NotificationList NotificationActivity = (NotificationList) noftobject;

    }

    private static class ViewHolder {
        TextView txtnotif_message;
        TextView txtdatetime;
        TextView txtnotiftype;
    }


    // Constructor.
    public NotificationListAdapter(Context context, ArrayList<NotificationListModel> notdata)
    {
        mContext = context;
        this.notList = notdata;
    }

    public int getCount() {
        return notList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        NotificationListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new NotificationListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.notification_list_item, parent, false);
            viewHolder.txtnotif_message = (TextView) convertView.findViewById(R.id.txt_notify_msg);
            viewHolder.txtdatetime = (TextView) convertView.findViewById(R.id.txt_datetime);
            viewHolder.txtnotiftype = (TextView) convertView.findViewById(R.id.txt_notify_type);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NotificationListAdapter.ViewHolder) convertView.getTag();
            result = convertView;

        }

        viewHolder.txtnotif_message.setText("Notification Message: "+notList.get(position).getNotification_msg());
        viewHolder.txtdatetime.setText("Date-Time: "+notList.get(position).getDatetime());
        String jew = notList.get(position).getType_notfy();
        if (jew.equals("New")){
            viewHolder.txtnotiftype.setBackgroundColor(Color.GREEN);
        }
        else if (jew.equals("Alert")) {
            viewHolder.txtnotiftype.setBackgroundColor(Color.RED);
        }
        else if (jew.equals("Report")) {
            viewHolder.txtnotiftype.setBackgroundColor(Color.CYAN);
        }
        else{
            viewHolder.txtnotiftype.setBackgroundColor(Color.GRAY);
        }
        viewHolder.txtnotiftype.setText(notList.get(position).getType_notfy());


        return convertView;
    }

}