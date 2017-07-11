package com.example.bthea.htttsvtdt.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bthea.htttsvtdt.Objects.news.ThongBao;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

/**
 * Created by nguye on 4/20/2017.
 */

public class NewsAdapter extends ArrayAdapter<ThongBao> {

    private Context context;
    private List<ThongBao> data;

    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ThongBao> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_news, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.tvthongbao);
        ThongBao item = data.get(position);
        LinearLayout bgnews = (LinearLayout) view.findViewById(R.id.bgnews);
        tvName.setText(item.getTieude());

        int backgroundColor = ContextCompat.getColor(context, R.color.red);

        if (item.isread() == false) {
            bgnews.setBackgroundColor(backgroundColor);
        }

        return view;
    }
}
