 package com.example.zistock;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapterinventaire extends BaseAdapter {
    private List<classeinventaire> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public adapterinventaire( Context aContext, List<classeinventaire> listData) {
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
        adapterinventaire.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.exemplaireinvent, null);
            holder = new ViewHolder();
            holder.quant = (TextView) convertView.findViewById(R.id.prix_achat);
            holder.name = (TextView) convertView.findViewById(R.id.nomv);
            holder.prixv = (TextView) convertView.findViewById(R.id.prixunitv);
            holder.total = (TextView) convertView.findViewById(R.id.totalv);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (adapterinventaire.ViewHolder) convertView.getTag();
        }

        classeinventaire classecontenu = this.listData.get(position);
        holder.name.setText(" "+classeinventaire.getNom());
        holder.total.setText(""+classeinventaire.getTotal());
        holder.quant.setText(""+classeinventaire.getQuant());
        holder.prixv.setText(""+classeinventaire.getPrix());
        holder.date.setText(""+classeinventaire.getDate());

        return convertView;
    }




    public class ViewHolder {

        TextView total;
        TextView quant;
        TextView name;
        TextView prixv;
        TextView date;
    }
}

