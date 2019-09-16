package com.suncaption.kpoptubemy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suncaption.kpoptubemy.R;
import com.suncaption.kpoptubemy.Videos;
import com.suncaption.kpoptubemy.adapter.VideosAdapter1;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class FragmentNew extends Fragment {
    private final Realm realm = Realm.getDefaultInstance();

    public final Realm getRealm() {
        return this.realm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.content_like, container, false);
        ListView listView = (ListView) inflate.findViewById(R.id.listView);
        Date date = new Date();
        Realm realm = this.realm;

        RealmQuery where = realm.where(Videos.class);
        RealmResults findAll = where.greaterThan("date", date.getTime() - ((long) 86400000))
                .sort("date", Sort.DESCENDING).limit(100).findAll();


        VideosAdapter1 videosAdapter1 = new VideosAdapter1(findAll);

        listView.setAdapter(videosAdapter1);

        return inflate;
    }

    public FragmentNew() {
    }

    public void onDestroy() {
        super.onDestroy();
        this.realm.close();
    }
}
