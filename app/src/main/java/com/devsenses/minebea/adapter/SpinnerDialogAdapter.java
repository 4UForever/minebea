package com.devsenses.minebea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.devsenses.minebea.R;

import java.util.List;

/**
 * Created by Horus on 1/28/2015.
 */
public class SpinnerDialogAdapter extends ArrayAdapter<String> {
    private Context context;
    List<String> listModel;
    public SpinnerDialogAdapter(Context context, int resource, List<String> listModel) {
        super(context, resource, listModel);
        this.context = context;
        this.listModel = listModel;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position ,convertView , parent );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //return super.getView(position, convertView, parent);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.item_spiner, parent, false);
        TextView label=(TextView)row.findViewById(R.id.text);
        label.setText(listModel.get(position));



        return row;
    }

}
