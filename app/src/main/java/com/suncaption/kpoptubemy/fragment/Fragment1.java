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

import java.util.Date;

import io.realm.Realm;

public class Fragment1 extends Fragment {
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

        return inflate;
    }

    public Fragment1() {
    }
}
