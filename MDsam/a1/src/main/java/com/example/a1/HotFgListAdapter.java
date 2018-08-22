package com.example.a1;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

class HotFgListAdapter extends RecyclerView.Adapter<HotFgListAdapter.merViewHolder>{
    //数据
    private List<Taskk1>mDatas1;
    //上下文环境
    private LayoutInflater mInflater;
    private Context context;
    //点击事件
    private AdapterView.OnItemClickListener onItemClickListener;
    //构造函数
    public HotFgListAdapter(List<Taskk1> mDatas1,Context context) {
        this.mDatas1 = mDatas1;
        this.context = context;
    }

    @Override
    public merViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //关联相关样式
//        mInflater = LayoutInflater.from(parent.getContext());
//        View v = LayoutInflater.from(context).inflate(R.layout.item_2,parent,false);
        View v = LayoutInflater.from(context).inflate(R.layout.item_2,parent,false);
        merViewHolder rViewHolder = new merViewHolder(v);
        return rViewHolder;
    }

    @Override
    public void onBindViewHolder(final merViewHolder holder, int position) {
        holder.des.setText(mDatas1.get(position).getDes());

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

    public void addItem(int position,String string) {
        mDatas1.add(position,new Taskk1(string));
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mDatas1.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return mDatas1.size();
    }

    public void onItemMove(int fromPosition,int toPosition){
        Collections.swap(mDatas1,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    public void onItemDelete(int positon){
        mDatas1.remove(positon);
        notifyItemRemoved(positon);
    }

//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = (AdapterView.OnItemClickListener) onItemClickListener;
//    }
    class merViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView des;

        public merViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cv_car2);
            des = (TextView)itemView.findViewById(R.id.tv_des2);
        }

    }
    /**
     * ItemTouchHelperAdapter接口
     */
    public interface ItemTouchHelperAdapter{
        //移动Item
        public void onItemMove(int fromPosition,int toPosition);
        //删除Item
        public void onItemDelete(int positon);
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
