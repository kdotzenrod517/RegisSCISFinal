package com.example.kdotz.regisscis;

import android.widget.ArrayAdapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

import java.util.List;

/**
 * Created by kdotz on 6/30/2016.
 */

public class ProgramAdapter extends ArrayAdapter<ProgramDetail> {

    public ProgramAdapter(Context context) {
        this(context, 0);
    }

    public ProgramAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder.name = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getProgramName());

        return convertView;
    }

    public void add(List<ProgramDetail> course) {

    }

    class ViewHolder {
        TextView name;
    }

}
