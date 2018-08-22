package com.example.a1;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class WorkAdapter_1 extends RecyclerView.Adapter<WorkAdapter_1.meraViewHolder> {
    private  List<Taskk2>mDatas2;
    private Context context;
    private AdapterView.OnItemClickListener onItemClickListener;

    public WorkAdapter_1(List<Taskk2> mDatas2, Context context) {
        this.mDatas2 = mDatas2;
        this.context = context;
    }

    @Override
    public meraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_3,parent,false);
        meraViewHolder rwwViewHolder = new meraViewHolder(v);

        return rwwViewHolder;
    }

    @Override
    public void onBindViewHolder(final WorkAdapter_1.meraViewHolder holder, int position) {
        holder.tv_task.setText(mDatas2.get(position).getTask_w());
        holder.tv_time.setText(mDatas2.get(position).getTime_w());
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });
        }
    }


    public void addItem(int position,String str1,String str2) {
        mDatas2.add(position,new Taskk2(str1,str2));
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mDatas2.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDatas2.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(View v,int position);
        void onItemLongClick(View view,int position);
    }
    private WorkAdapter_1.OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(WorkAdapter_1.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }


    class meraViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_task;
        TextView tv_time;
        public meraViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_car3);
            tv_task = itemView.findViewById(R.id.tv_des2);
            tv_time = itemView.findViewById(R.id.tv_des4);
        }
    }
}
