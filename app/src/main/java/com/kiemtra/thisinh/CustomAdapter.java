package com.kiemtra.thisinh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private int resoure;
    private List<ThiSinh> thiSinhList;
    private ArrayList<ThiSinh> backupData;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<ThiSinh> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.thiSinhList = objects;
        this.backupData = new ArrayList<ThiSinh>();
        this.backupData.addAll(thiSinhList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.items_thisinh,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvSBD = (TextView)convertView.findViewById(R.id.item_sbd);
            viewHolder.tvHoTen = (TextView)convertView.findViewById(R.id.item_hoTen);
            viewHolder.tvDiem = (TextView)convertView.findViewById(R.id.item_diem);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ThiSinh thiSinh = thiSinhList.get(position);
        viewHolder.tvSBD.setText(thiSinh.getmSBD());
        viewHolder.tvHoTen.setText(thiSinh.getmHoTen());
        viewHolder.tvDiem.setText(String.valueOf(thiSinh.getmToan()+thiSinh.getmLy()+thiSinh.getmHoa()));
        return convertView;
    }

    public class ViewHolder{
        private TextView tvSBD;
        private TextView tvHoTen;
        private TextView tvDiem;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        thiSinhList.clear();
        if (charText.length() == 0) {
            thiSinhList.addAll(backupData);
        } else {
            for (ThiSinh wp : backupData) {
                if (wp.getmSBD().toLowerCase().contains(charText)) {
                    thiSinhList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
