package com.example.a1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
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
import java.util.Locale;

import static com.example.a1.a1dMerca.mDatas1;
import static com.example.a1.a1dMerca.mDatas2;
import static com.example.a1.a1dMerca.newTextRecord;
import static com.example.a1.a1dMerca.tab_c;
import static com.example.a1.a1dMerca.wor_pos;
import static com.example.a1.a1dMerca.writeListIntoSDcard;
import static com.example.a1.a1dMerca.mNFC_Adp;
import static com.example.a1.a1dMerca.mMessage;
import static com.example.a1.workk.init_wk;


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

    //private NfcAdapter mNFC_Adp2;
   // private NdefMessage mMessage_r;

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
        mNFC_Adp = NfcAdapter.getDefaultAdapter(this.getContext());
        context3 = this.getContext();
        btn1 = mView.findViewById(R.id.btn_remain_1);

        /*设置管理器*/
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.hot_fragment_rcv);
        /*设置适配器*/
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(llm);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        initListData();
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

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        //mMessage_r = new NdefMessage(new NdefRecord[]{newTextRecord(init_re(), Locale.ENGLISH, true)});

        switch (tab_c){
            case 0:
                mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_re(), Locale.ENGLISH, true)});
                break;
            case 1:
                if (wor_pos!=-1 || wor_pos<=mDatas2.size()){
                    mMessage = new NdefMessage(new NdefRecord[]{newTextRecord(init_wk(), Locale.ENGLISH, true)});
                    break;
                }else {
                    break;
                }

            default:mMessage = new NdefMessage(new NdefRecord[]{newTextRecord("none_defult", Locale.ENGLISH, true)});break;
        }
        if (mNFC_Adp != null) mNFC_Adp.enableForegroundNdefPush((Activity) this.getContext(), mMessage);
    }

    public void onPause() {
        super.onPause();
        if (mNFC_Adp != null) mNFC_Adp.disableForegroundNdefPush((Activity) this.getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        writeListIntoSDcard("remainRes",mDatas1);
    }

    private void initListData() {
        readListFromSDcard("remainRes");
    }

    public static String init_re(){
        StringBuffer sb = new StringBuffer();
        String remain_1;
        if(mDatas1 == null){
            return "none";
        }else{
            for (int i = 0; i < mDatas1.size(); i++) {
                sb.append(mDatas1.get(i).getTask_w());
                sb.append("@");
            }
            remain_1 = sb.toString();
            return remain_1;
        }
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
            Toast.makeText(getContext(), "read succeed", Toast.LENGTH_SHORT).show();
        }
    }
}


