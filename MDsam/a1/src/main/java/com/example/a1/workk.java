package com.example.a1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class workk extends Fragment {
    private View mwView;
    private RecyclerView mwRecyclerView;
    private List<Taskk1> mDatas2;
    private HotFgListAdapter mwAdapter;
    private Context context_w;
    public String add_mData2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_work,container,false);
        return rootView;
    }

}
