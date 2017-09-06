package com.devsenses.minebea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devsenses.minebea.R;
import com.devsenses.minebea.model.documentmodel.Datum;

import java.util.List;

/**
 * Created by Administrator on 3/6/2015.
 */
public class GridDocumentAdapter extends BaseAdapter{
    Context context;
   // DocumentModel documentModel;
//    private int intImage[] = {R.drawable.pi,R.drawable.pe,R.drawable.pi_pr,R.drawable.pi_set};
    private List<Datum> listData;
    public GridDocumentAdapter(Context context , List<Datum> listData) {
        this.context = context;
        this.listData = listData;
    }

//    public void changDataList(List<Datum> listData){
//        this.listData.clear();
//        this.listData.addAll(listData);
//        notifyDataSetChanged();
//    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            holder = new Holder();
            LayoutInflater inflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_grid_document, null);
           // holder.imageDocument = (ImageView)convertView.findViewById(R.id.imageDocument);
            holder.textDocument = (TextView)convertView.findViewById(R.id.textDocument);
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }

       // holder.imageDocument.setImageResource(intImage[position]);
        holder.textDocument.setText(listData.get(position).getTitle());

        return convertView;
    }
    class Holder{
       // ImageView imageDocument;
        TextView textDocument;
    }
}
