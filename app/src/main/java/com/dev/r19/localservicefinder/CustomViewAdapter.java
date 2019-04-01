/*
 *** Created by Pranjal Das on 16/03/2019
 */

package com.dev.r19.localservicefinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomViewAdapter extends ArrayAdapter<ServiceProviderModel> {

    CustomViewAdapter(Context context, ArrayList<ServiceProviderModel> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ServiceProviderModel model = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_view, parent, false);
        }

        TextView tvname = (TextView) convertView.findViewById(R.id.name);
        TextView tvemail = (TextView) convertView.findViewById(R.id.email);
        TextView tvmobile = (TextView) convertView.findViewById(R.id.mobile);
        TextView tvcity = (TextView) convertView.findViewById(R.id.city);
        TextView tvaddress = (TextView) convertView.findViewById(R.id.address);
        TextView tvdistrict = (TextView) convertView.findViewById(R.id.district);
        TextView tvcompany =  (TextView) convertView.findViewById(R.id.company);
        TextView tvdescription = (TextView) convertView.findViewById(R.id.description);


        tvname.setText(model.getSp_name().toString());
        tvmobile.setText(model.getSp_mobile().toString());
        tvemail.setText(model.getSp_email().toString());
        tvcity.setText(model.getSp_city().toString());
        tvaddress.setText(model.getSp_address().toString());
        tvdistrict.setText(model.getSp_district().toString());
        tvcompany.setText(model.getSp_companyname().toString());
        tvdescription.setText(model.getSp_companydescription().toString());


        return convertView;
    }
}
