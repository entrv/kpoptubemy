package com.suncaption.kpoptubemy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.suncaption.kpoptubemy.R;
import com.suncaption.kpoptubemy.Videos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class VideosAdapter1 extends RealmBaseAdapter<Videos> implements ListAdapter {
    private static class ViewHolder {
        TextView list_title;
        CheckBox deleteCheckBox;
        ImageView list_image;

        TextView list_date;
        TextView list_time;
        ImageView   bookmark_add;

                /*
                * Glide
    .with(myFragment)
    .load(url)
    .centerCrop()
    .placeholder(R.drawable.loading_spinner)
    .into(myImageView);
                * */
    }

    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<Integer>();

    public VideosAdapter1(OrderedRealmCollection<Videos> realmResults) {
        super(realmResults);
    }

    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }

    Set<Integer> getCountersToDelete() {
        return countersToDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.list_title = (TextView) convertView.findViewById(R.id.list_title);
            viewHolder.list_date = (TextView) convertView.findViewById(R.id.list_date);
            viewHolder.list_time = (TextView) convertView.findViewById(R.id.list_time);
            viewHolder.list_image = (ImageView) convertView.findViewById(R.id.list_image);
            viewHolder.bookmark_add = (ImageView) convertView.findViewById(R.id.bookmark_add);


            //viewHolder.deleteCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            final Videos item =  adapterData.get(position);
            viewHolder.list_title.setText(item.getTitle());
            Timestamp ts = new Timestamp(item.getDate());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            viewHolder.list_date.setText(formatter.format(ts));
            viewHolder.list_time.setText(item.getTime());
            //viewHolder.list_image.setText(item.getTime());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.bookmark_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            /*
            if (inDeletionMode) {
                viewHolder.deleteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        countersToDelete.add(item.getId());
                    }
                });
            } else {
                viewHolder.deleteCheckBox.setOnCheckedChangeListener(null);
            }
            viewHolder.deleteCheckBox.setChecked(countersToDelete.contains(item.getId()));
            viewHolder.deleteCheckBox.setVisibility(inDeletionMode ? View.VISIBLE : View.GONE);
            */
        }
        return convertView;
    }

    public long getItemId(int i) {
        OrderedRealmCollection orderedRealmCollection = this.adapterData;
        return super.getItemId(i);
    }
}
