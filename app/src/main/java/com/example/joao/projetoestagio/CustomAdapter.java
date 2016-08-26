package com.example.joao.projetoestagio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joao.projetoestagio.Api.Models.Data;


class CustomAdapter extends ArrayAdapter {
    // this custom adapter is use to automaticaly create new item in
    // viewList for each data item found in the json file

    CustomAdapter(Context context, Data[] dataList) {
        super(context, R.layout.custom_row_list_view, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row_list_view, parent, false);

        TextView textName = (TextView) customView.findViewById(R.id.textName);
        TextView textId = (TextView) customView.findViewById(R.id.textId);
        TextView textPwd = (TextView) customView.findViewById(R.id.textPwd);

        // add a new item in to viewList for each item in dataList
        Data singleDataItem = (Data) getItem(position);

        textName.setText("Name: " + singleDataItem.name);
        textId.setText("Id: " + singleDataItem.id);
        textPwd.setText("Pwd: "+ singleDataItem.pwd);

        return customView;
    }

}
