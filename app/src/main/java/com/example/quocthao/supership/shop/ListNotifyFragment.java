package com.example.quocthao.supership.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quocthao.supership.R;

/**
 * Created by Quoc Thao on 3/31/2017.
 */

public class ListNotifyFragment extends Fragment {

    public ListNotifyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_notify, container, false);

        return view;
    }
}
