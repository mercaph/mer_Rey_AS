package com.example.a1;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class myItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private HotFgListAdapter.ItemTouchHelperAdapter itemTouchHelperAdapter;

    public myItemTouchHelperCallBack(HotFgListAdapter.ItemTouchHelperAdapter itemTouchHelperAdapter){
        this.itemTouchHelperAdapter = itemTouchHelperAdapter;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //允许上下滑动
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        //左右
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        itemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperAdapter.onItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
