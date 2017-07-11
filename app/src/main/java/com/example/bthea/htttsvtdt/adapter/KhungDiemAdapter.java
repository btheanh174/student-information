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

import com.example.bthea.htttsvtdt.Objects.hdqt.DiemQuaTrinh;
import com.example.bthea.htttsvtdt.Objects.hdqt.KhungDiem;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

/**
 * Created by nguye on 4/12/2017.
 */

public class KhungDiemAdapter extends ArrayAdapter<KhungDiem> {

    private Context context;
    private List<KhungDiem> data;

    public KhungDiemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<KhungDiem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_khungdiem, parent, false);

        TextView tvKhungDiem= (TextView) view.findViewById(R.id.tvnoidungdiem);
        TextView tvMaxDiem = (TextView) view.findViewById(R.id.tvmaxdiem);
        TextView tvMuc= (TextView) view.findViewById(R.id.tvMuc);




        KhungDiem item = data.get(position);
        tvKhungDiem.setText(item.getName()+"");
        tvMaxDiem.setText("Tối đa "+item.getMaxdiem()+" điểm");
        tvMuc.setText(item.getMuc());


        return view;
    }



}
