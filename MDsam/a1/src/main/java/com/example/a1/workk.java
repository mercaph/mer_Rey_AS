package com.example.a1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class workk extends Fragment {
    private View mwView;
    private RecyclerView mwRecyclerView;
    private List<Taskk2> mDatas2;
    private HotFgListAdapter mwAdapter;
    private Context context_w;
    private Button btn3;//底部的添加按钮

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mwView = inflater.inflate(R.layout.fragment_work,container,false);
        return mwView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context_w = this.getContext();
        btn3 = mwView.findViewById(R.id.btn_work_1);

        //设置管理器
        mwRecyclerView = (RecyclerView) mwView.findViewById(R.id.work_rcv);
        //适配器
        LinearLayoutManager llm2 = new LinearLayoutManager(this.getContext());
        mwRecyclerView.setLayoutManager(llm2);
        initListData_w();
        mwAdapter = new HotFgListAdapter(mDatas2,this.getContext());
        mwRecyclerView.setAdapter(mwAdapter);
        mwRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(context_w);
//        /*设置条目点击事件*/
        mwAdapter.setOnItemClickListener(new HotFgListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Toast.makeText(getContext(), mDatas2.get(position).getTask_w(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                Toast.makeText(getContext(), "long click" + position, Toast.LENGTH_SHORT).show();
                localBuilder1.setTitle("ATTENTION");
                localBuilder1.setView(getLayoutInflater().inflate(R.layout.do_you_delete,null));
                localBuilder1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getContext(), "yes" , Toast.LENGTH_SHORT).show();
                        mwAdapter.removeItem(position);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "none" , Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder localBuilder = new AlertDialog.Builder(context_w);
                final View viewDialog=(View)getLayoutInflater().inflate(R.layout.dialog_w,null);
                /**
                 * 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；
                 * 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
                 */
                localBuilder.setTitle("Add Here");
                localBuilder.setView(viewDialog);
                localBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt_task = viewDialog.findViewById(R.id.edt_2);
                        EditText edt_time = viewDialog.findViewById(R.id.edt_3);
                        mwAdapter.addItem(0,edt_task.getText().toString(),edt_time.getText().toString()+"  mins");
                        Toast.makeText(getContext(), "item built succeed" , Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "none" , Toast.LENGTH_SHORT).show();

                    }
                }).create().show();
            }
        });


    }

    private void initListData_w() {
        mDatas2 = new ArrayList<>(7);
        for(int i=0;i<5;i++){
            Taskk2 dataBean = new Taskk2("work","15  mins");
            mDatas2.add(dataBean);
        }

    }
}



































