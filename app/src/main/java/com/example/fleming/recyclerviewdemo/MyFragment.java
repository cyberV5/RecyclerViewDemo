package com.example.fleming.recyclerviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * MyFragment
 * Created by Fleming on 2016/11/15.
 */

public class MyFragment extends Fragment {

    private static final int LINEARLAYOUT_MODE        = 0;
    private static final int GRIDLAYOUT_MODE          = 1;
    private static final int STAGGEREDGRIDLAYOUT_MODE = 2;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isStaggered;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        changeMode(LINEARLAYOUT_MODE);
    }

    private void changeMode(int mode) {

        switch (mode) {
            case LINEARLAYOUT_MODE:
                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                isStaggered = false;
                break;
            case GRIDLAYOUT_MODE:
                mLayoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
                isStaggered = false;
                break;
            case STAGGEREDGRIDLAYOUT_MODE:
                mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                isStaggered = true;
                break;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new MyAdapter(getDataset(), isStaggered));
    }

    private String[] getDataset() {
        String[] dataset = new String[50];
        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = "photo " + i;
        }
        return dataset;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.linear) {
            changeMode(LINEARLAYOUT_MODE);
            return true;
        } else if (id == R.id.grid) {
            changeMode(GRIDLAYOUT_MODE);
            return true;
        } else if (id == R.id.staggered) {
            changeMode(STAGGEREDGRIDLAYOUT_MODE);
            return true;
        }
        return false;
    }
}
