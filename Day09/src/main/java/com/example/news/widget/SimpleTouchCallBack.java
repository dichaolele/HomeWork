package com.example.news.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleTouchCallBack extends ItemTouchHelper.Callback {

    private final TouchCallBack mCallBack;
    private boolean mDrag = true;
    private boolean mSwipe = true;

    public SimpleTouchCallBack(TouchCallBack callBack) {
        mCallBack = callBack;
    }

    /**
     * 返回可以滑动的方向,一般使用makeMovementFlags(int,int)或makeFlag(int, int)
     * 来构造我们的返回值
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //允许上下拖拽
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许向左滑动
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag,swipe);
    }

    /**
     *     上下拖动item时回调,可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder
     *     的位置，最后返回true，表示被拖动的ViewHolder已经移动到了目的位置,如:
     * @param recyclerView
     * @param viewHolder
     * @param viewHolder1
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        mCallBack.onItemMove(viewHolder.getAdapterPosition(),
                viewHolder1.getAdapterPosition());
        return true;
    }

    /**
     *      当用户左右滑动item时达到删除条件就会调用,一般为一半,条目继续滑动删除,否则弹回
     * @param viewHolder
     * @param i
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    /**
     *  支持长按拖动,默认是true
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return mDrag;
    }

    /**
     *  支持滑动删除,即可以调用到onSwiped()方法,默认是true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipe;
    }

    //设置是否支持拖拽移动
    public void setDragEnable(boolean drag){
        this.mDrag = drag;
    }

    //是指是否支持侧滑删除
    public void setSwipeEnable(boolean swipe){
        this.mSwipe = swipe;
    }
}
