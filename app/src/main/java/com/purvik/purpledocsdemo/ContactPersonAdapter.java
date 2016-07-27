package com.purvik.purpledocsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Purvik Rana on 27-07-2016.
 */
public class ContactPersonAdapter extends ArrayAdapter<ContactPerson> implements Filterable {

    private static final String TAG = "TextWatcher";
    Context context;
    int layoutId;
    ArrayList<ContactPerson> originalList;
    ArrayList<ContactPerson> nameList;
    private PersonFilter filter;



    public ContactPersonAdapter(Context context, int layoutId, ArrayList<ContactPerson> data) {
        super(context, layoutId, data);
        this.context = context;
        this.layoutId = layoutId;

        this.originalList = data;
        this.nameList = data;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ContactPersonHolder holder = null;
        final String PhoneNo;

        if (item == null) {

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();/*(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
            item = inflater.inflate(layoutId,parent,false);

            holder = new ContactPersonHolder();
            holder.imgView = (ImageView) item.findViewById(R.id.imgView);
            holder.tvName =  (TextView) item.findViewById(R.id.tvName);
            holder.tvPhoneNo =  (TextView) item.findViewById(R.id.tvPhoneNo);

            PhoneNo = holder.tvPhoneNo
                    .getText().toString();

            holder.btnCall = (Button) item.findViewById(R.id.btnCall);
            holder.btnMsg = (Button) item.findViewById(R.id.btnMessage);

            holder.btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",PhoneNo,null));
                    context.startActivity(callIntent);
                }
            });

            holder.btnMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",PhoneNo,null));
                    context.startActivity(msgIntent);
                }
            });

            item.setTag(holder);


        }else{

            holder  = (ContactPersonHolder) item.getTag();
        }

        ContactPerson contactPerson = originalList.get(position);
        holder.imgView.setImageResource(R.mipmap.ic_launcher);
        holder.tvName.setText(contactPerson.name);
        holder.tvPhoneNo.setText(contactPerson.phone_no);


        return item;
    }

    static class ContactPersonHolder {
        ImageView imgView;
        TextView tvName;
        TextView tvPhoneNo;
        Button btnCall, btnMsg;
    }

    @Override
    public Filter getFilter() {

        if (filter == null){
            filter  = new PersonFilter();
        }
        return filter;

    }

    private class PersonFilter extends  Filter{

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                FilterResults filterResults = new FilterResults();
                ArrayList<ContactPerson> filterItems = new ArrayList<>();




                if(constraint == " " && constraint.toString().length() == 0){

                    synchronized (this){

                        filterResults.values = originalList;
                        filterResults.count = originalList.size();
                    }
                }else  {
                    constraint = constraint.toString().toLowerCase();
                    Log.d(TAG, "To Compare:: " + constraint);

                    for (int l = 0; l< originalList.size(); l++) {

                            //ContactPerson person =  originalList.get(l);
                            String name = originalList.get(l).name;
                            Log.d("TextWatcher", "Name: " + name);

                            if (name.toLowerCase().startsWith(constraint.toString().toLowerCase())){
                                Log.d(TAG, "Added: " + name);
                                filterItems.add(originalList.get(l));
                            }

                        }

                    filterResults.count = filterItems.size();
                    filterResults.values = filterItems;
                }

                Log.d(TAG, "Count: " +filterResults.count);
                Log.d(TAG, "Values: " + filterResults);

                return filterResults;
            }


            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (results.count == 0) {
                    notifyDataSetInvalidated();
                }else {
                    nameList = (ArrayList<ContactPerson>) results.values;
                    notifyDataSetChanged();
                    clear();

                    for(int i = 0, l = nameList.size(); i < l; i++)
                        add(nameList.get(i));
                    notifyDataSetInvalidated();
                }
            }
        }

}


