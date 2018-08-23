package com.example.a1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.a1.a1dMerca.mDatas1;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class remain extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    //public List<Taskk2> mDatas1;
    private HotFgListAdapter mAdapter;
    private Context context3;
    private Button btn1;
    public String add_mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_remain, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context3 = this.getContext();
        btn1 = mView.findViewById(R.id.btn_remain_1);

        /*设置管理器*/
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.hot_fragment_rcv);
        /*设置适配器*/
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(llm);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        initListData();
//        Log.i("ld","1");
        mAdapter = new HotFgListAdapter(mDatas1, this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        /*3,添加item的添加和移除动画, 这里我们使用系统默认的动画*/
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //item
//        ItemTouchHelper.Callback callback = new myItemTouchHelperCallBack((HotFgListAdapter.ItemTouchHelperAdapter) mAdapter);
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(mRecyclerView);


        final AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(context3);
//        /*设置条目点击事件*/
        mAdapter.setOnItemClickListener(new HotFgListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Toast.makeText(getContext(), mDatas1.get(position).getTask_w(), Toast.LENGTH_SHORT).show();
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
                        mAdapter.removeItem(position);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "none" , Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
            }
        });

        //添加对话框

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 AlertDialog.Builder localBuilder = new AlertDialog.Builder(context3);
                 final View viewDialog=(View)getLayoutInflater().inflate(R.layout.dialog,null);
                /**
                 * 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；
                 * 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
                 */
                localBuilder.setTitle("Add Here");
                localBuilder.setView(viewDialog);
                localBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                   @Override
                    public void onClick(DialogInterface dialog, int which) {
                       EditText edt_1 = viewDialog.findViewById(R.id.edt_1);
                       add_mData = null;
                       add_mData = edt_1.getText().toString();
                       mAdapter.addItem(0,add_mData,"don't forget");
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




    private void initListData() {
        readListFromSDcard("remainRes");
    }

    /**
     * rean from sdcard (集合)
     */
    public void readListFromSDcard(String str){
        mDatas1 = new InputUtil<Taskk2>().readListFromSdCard(str);
        if (mDatas1 == null) {
            mDatas1 = new ArrayList<>(10);
            Taskk2 dataBean = new Taskk2("Add Here :D","don't forget");
            mDatas1.add(dataBean);
        }else if(mDatas1.isEmpty()){
            Taskk2 dataBean = new Taskk2("Add Here :D","don't forget");
            mDatas1.add(dataBean);
        }
        else {
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < mDatas1.size(); i++) {
//                sb.append(mDatas1.get(i).getTask_w());
//                sb.append(mDatas1.get(i).getTime_w());
//            }
            Toast.makeText(getContext(), "read succeed", Toast.LENGTH_SHORT).show();
        }
    }
}


