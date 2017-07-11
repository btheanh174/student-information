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

import com.example.bthea.htttsvtdt.Objects.kqht.DiemMonHoc;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

/**
 * Created by nguye on 4/12/2017.
 */

public class KqhtAdapter extends ArrayAdapter<DiemMonHoc> {

    private Context context;
    private List<DiemMonHoc> data;

    public KqhtAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<DiemMonHoc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_kqht, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.tvnamemonhoc);
        TextView tvDiem1 = (TextView) view.findViewById(R.id.tvdiem1);

        TextView tvDiem2 = (TextView) view.findViewById(R.id.tvdiem2);

        TextView tvDiemGiuaKy = (TextView) view.findViewById(R.id.tvgiuaky);
        TextView tvDiemCuoiKy = (TextView) view.findViewById(R.id.tvcuoiky);

        TextView tvTongKet = (TextView) view.findViewById(R.id.tvtongket);
        TextView tvTinChi = (TextView) view.findViewById(R.id.tvtinchi);



        DiemMonHoc item = data.get(position);
        tvName.setText(item.getTenMH());
        if(item.getDiem1().equals("null"))
        {
            tvDiem1.setVisibility(View.GONE);
        }
        else {
            tvDiem1.setText("Điểm lần 1 :                                   " + item.getDiem1());
        }
        if(item.getDiem2().equals("null"))
        {
            tvDiem2.setVisibility(View.GONE);
        }
        else {
            tvDiem2.setText("Điểm lần 2 :                                   "+item.getDiem2());

        }
        if(item.getGiuaky().equals("null"))
        {
            tvDiemGiuaKy.setVisibility(View.GONE);
        }
        else {
            tvDiemGiuaKy.setText("Điểm giữa kì:                                 "+ item.getGiuaky());

        }
        if(item.getCuoiky().equals("null"))
        {
            tvDiemCuoiKy.setVisibility(View.GONE);
        }
        else {
            tvDiemCuoiKy.setText("Điểm cuối kì :                                " + item.getCuoiky());


        }
        if(item.getTongket().equals("null"))
        {
            tvTongKet.setText("Trung Bình :                     ");
        }
        else
        {
            tvTongKet.setText("Trung Bình : "+item.getTongket());
        }
        tvTinChi.setText("Số tín chỉ : "+ item.getSoTinChi());


        return view;
    }

}
