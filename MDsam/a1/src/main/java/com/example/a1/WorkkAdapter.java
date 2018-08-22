package com.example.a1;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class WorkkAdapter extends RecyclerView.Adapter<WorkkAdapter.merwViewHolder>{
    //数据
    private List<Taskk2> mDatas2;
    //上下文环境
    private Context context;
    //点击事件
    private AdapterView.OnItemClickListener onItemClickListener;
    //构造函数
    public WorkkAdapter(List<Taskk2>mDatas2,Context context) {
        this.mDatas2 = mDatas2;
        this.context = context;
    }

    @Override
    public WorkkAdapter.merwViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //关联相关样式

        View v = LayoutInflater.from(context).inflate(R.layout.item_3,parent,false);
        WorkkAdapter.merwViewHolder rViewHolder = new WorkkAdapter.merwViewHolder(v);
        return rViewHolder;
    }

    @Override
    public void onBindViewHolder(final merwViewHolder holder, int position) {
        holder.task_w.setText(mDatas2.get(position).getTask_w());
        holder.time_w.setText(mDatas2.get(position).getTime_w());
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    public void addItem(int position,String string,String string2) {
        mDatas2.add(position,new Taskk2(string,string2));
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

    class merwViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView task_w;
        TextView time_w;

        public merwViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cv_car3);
            task_w = (TextView)itemView.findViewById(R.id.tv_des3);
            time_w = itemView.findViewById(R.id.tv_des4);
        }

    }
    /**
     * 适配器的点击事件接口
     */
    public interface OnItemClickListener{
        void OnItemClick(View v, int position);
        void onItemLongClick(View view,int position);
    }
    private  HotFgListAdapter.OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(HotFgListAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
