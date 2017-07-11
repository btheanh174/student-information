package com.example.bthea.htttsvtdt.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bthea.htttsvtdt.Objects.MonHoc;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

/**
 * Created by nguye on 4/12/2017.
 */

public class TkbAdapter extends ArrayAdapter<MonHoc> {

    private Context context;
    private List<MonHoc> data;

    public TkbAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MonHoc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_tkb, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.tvTentkb);
        TextView tvTiet = (TextView) view.findViewById(R.id.tvTiet);

        TextView tvLich = (TextView) view.findViewById(R.id.tvlich);

        TextView tvPhong = (TextView) view.findViewById(R.id.tvphong);
        TextView tvNhom = (TextView) view.findViewById(R.id.tvNhommh);


        MonHoc item = data.get(position);
        int tietkethuc = item.getTietbatdau() +item.getSotiet() -1;
        tvName.setText(item.getTenMH());
        tvTiet.setText("Tiết: "+item.getTietbatdau()+" - " + tietkethuc);
        tvLich.setText("Tuần: "+item.getTuan());
        tvPhong.setText("Phòng: "+item.getPhong());
        tvNhom.setText("Nhóm " + item.getNhom() );

        return view;
    }

}
