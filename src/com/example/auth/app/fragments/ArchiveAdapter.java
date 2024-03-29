package com.example.auth.app.fragments;
/**
 * Developed for Aalto-university course T-110.5241 Network Security.
 * Copyright (C) 2014 Jere Vaara
 */
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.auth.app.main.FileManager;
import com.example.auth.app.main.MyActivity;
import com.example.vaarajer1.auth.R;

import java.util.ArrayList;

public class ArchiveAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList values;
    private boolean paintSelected = false;

    public ArchiveAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.fragment_list_item, values);
        this.context = context;
        this.values = values;
    }

    public void setPaintSelected(boolean paintSelected) {
        this.paintSelected = paintSelected;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (rowView == null)
            rowView = inflater.inflate(R.layout.fragment_list_item, parent, false);

        TextView date_view = (TextView) rowView.findViewById(R.id.date_string);
        TextView uid_view = (TextView) rowView.findViewById(R.id.key_string);
        ImageView icon = (ImageView) rowView.findViewById(R.id.list_item_icon);

        String value = values.get(position).toString();

        if (value.contains("log")) {
            icon.setImageDrawable(rowView.getResources().getDrawable(R.drawable.ic_log_file));
            uid_view.setText("Generated log file");
        } else {
            icon.setImageDrawable(rowView.getResources().getDrawable(R.drawable.ic_file));
            String UID = FileManager.getUID(MyActivity.outer, value);

            uid_view.setText(UID);
        }
        if (paintSelected && ArchiveFragment.selected.contains(getItem(position))) {
            rowView.setBackgroundColor(rowView.getResources().getColor(R.color.selected));
        } else {
            rowView.setBackgroundColor(rowView.getResources().getColor(android.R.color.transparent));

        }

        date_view.setText(value);

        uid_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        uid_view.setSelected(true);

        date_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        date_view.setSelected(true);

        return rowView;

    }


}
