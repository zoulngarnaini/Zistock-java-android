package com.example.zistock;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Classecontenu> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<Classecontenu> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.exemplaire, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.nomv);
            holder.quant = (TextView) convertView.findViewById(R.id.prix_achat);
            holder.prixunit = (TextView) convertView.findViewById(R.id.prixunitv);
            holder.total = (TextView) convertView.findViewById(R.id.totalv);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Classecontenu classecontenu = this.listData.get(position);
        holder.name.setText(" "+classecontenu.getNom());
        holder.prixunit.setText(""+classecontenu.getPrixunit());
        holder.quant.setText(""+classecontenu.getQuant());
        holder.total.setText(""+classecontenu.getTotal());
        holder.date.setText(""+classecontenu.getDate());


        return convertView;
    }




    static class ViewHolder {
        TextView total;
        TextView prixunit;
        TextView quant;
        TextView name;
        TextView date;

    }
}

