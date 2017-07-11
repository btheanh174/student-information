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

import com.example.bthea.htttsvtdt.Objects.news.DonVi;
import com.example.bthea.htttsvtdt.R;

import java.util.List;

/**
 * Created by nguye on 4/12/2017.
 */

public class DayAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> data;

    public DayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_day, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.tvday);

        String item = data.get(position);

        tvName.setText(item);

        return view;


    }

}
