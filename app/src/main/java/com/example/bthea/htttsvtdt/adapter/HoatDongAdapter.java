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

import com.example.bthea.htttsvtdt.Objects.hdqt.DiemHoatDong;
import com.example.bthea.htttsvtdt.Objects.hdqt.DiemQuaTrinh;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

/**
 * Created by nguye on 4/12/2017.
 */

public class HoatDongAdapter extends ArrayAdapter<DiemHoatDong> {

    private Context context;
    private List<DiemHoatDong> data;

    public HoatDongAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<DiemHoatDong> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_qtrl, parent, false);

        TextView tvSukien = (TextView) view.findViewById(R.id.tvnamesukien);
        TextView tvDiem = (TextView) view.findViewById(R.id.tvdiem);




        DiemHoatDong item = data.get(position);
        tvSukien.setText(item.getNoiDung());
        tvDiem.setText(item.getDiem()+"đ");


        return view;
    }



}
