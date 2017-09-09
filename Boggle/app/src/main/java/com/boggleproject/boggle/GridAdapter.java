package com.boggleproject.boggle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by lukas on 09.09.2017.
 */

public class GridAdapter extends BaseAdapter {

    private String[] items;
    private int numberOfItems;

    public GridAdapter(String[] items) {
        this.items = items;
        this.numberOfItems = items.length;
    }

    @Override
    public int getCount() {
        return numberOfItems;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dice_text_view, parent, false);
            }

            final TextView text = view.findViewById(android.R.id.text1);
            text.setText(items[position]);

            return view;
    }
}
