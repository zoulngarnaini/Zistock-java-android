package com.example.zistock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adaptercommande extends BaseAdapter {
    private List<classecommande> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adaptercommande(Context aContext, List<classecommande> listData) {
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
        Adaptercommande.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.exemplairecom, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.nomex);
            holder.quant = (TextView) convertView.findViewById(R.id.quantex);
            holder.alerte = (TextView) convertView.findViewById(R.id.alerte);
            convertView.setTag(holder);
        } else {
            holder = (Adaptercommande.ViewHolder) convertView.getTag();
        }

        classecommande classecontenu = this.listData.get(position);
        holder.name.setText(" "+classecontenu.getNom());
        //holder.num.setText(""+classecontenu.getNum());
        holder.quant.setText(""+classecontenu.getQuant());
        holder.alerte.setText(""+classecontenu.getAlerte());

        return convertView;
    }




    static class ViewHolder {

        TextView alerte;
        TextView quant;
        TextView name;
        TextView num;
    }
}

