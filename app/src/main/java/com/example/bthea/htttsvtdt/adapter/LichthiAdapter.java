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
import com.example.bthea.htttsvtdt.Objects.MonThi;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;

/**
 * Created by nguye on 4/12/2017.
 */

public class LichthiAdapter extends ArrayAdapter<MonThi> {

    private Context context;
    private List<MonThi> data;

    public LichthiAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MonThi> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_lichthi, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.tvTentkb);
        TextView tvTiet = (TextView) view.findViewById(R.id.tvTiet);
        TextView tvNhom = (TextView) view.findViewById(R.id.tvNhom);
        TextView tvLich = (TextView) view.findViewById(R.id.tvlich);

        TextView tvPhong = (TextView) view.findViewById(R.id.tvphong);


        MonThi item = data.get(position);
        tvName.setText(item.getTenMH());
        tvTiet.setText("Giờ Thi: "+item.getGioThi());
        tvLich.setText("Thứ "+item.getThư()+" Ngày " + item.getNgaythi());
        tvPhong.setText("Phòng "+item.getPhongThi());
        tvNhom.setText("Nhóm " + item.getNhomthi());

        return view;
    }



}
