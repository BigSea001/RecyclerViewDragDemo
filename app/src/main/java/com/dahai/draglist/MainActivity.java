package com.dahai.draglist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> list;
    private DragListAdapter dragAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        for (int i = 15; i > 0; i--) {
            list.add("item-"+i);
        }
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        dragAdapter = new DragListAdapter(list);
        mRecyclerView.setAdapter(dragAdapter);
        new ItemTouchHelper(new TouchCallback()).attachToRecyclerView(mRecyclerView);
    }

    class TouchCallback extends ItemTouchHelper.Callback {

        /**
         * 设置拖动或滑动的方向
         */
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            Log.e("HHH", "getMovementFlags: " + (ItemTouchHelper.DOWN|ItemTouchHelper.UP|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) );
            return ItemTouchHelper.Callback.makeMovementFlags(ItemTouchHelper.DOWN|ItemTouchHelper.UP|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT, 0);
        }

        /**
         * 移动时回调
         * @param viewHolder 起始
         * @param target 到
         */
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            // 依次交换位置
            int i;
            if (fromPosition < toPosition) {
                for (i = fromPosition; i < toPosition; i++) {
                    Collections.swap(list, i, i + 1);
                }
            } else {
                for (i = fromPosition; i > toPosition; i--) {
                    Collections.swap(list, i, i - 1);
                }
            }
            dragAdapter.notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        }

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != 0 && viewHolder!=null) {
                viewHolder.itemView.setScaleX(1.1f);
                viewHolder.itemView.setScaleY(1.1f);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }
        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setScaleX(1.0f);
            viewHolder.itemView.setScaleY(1.0f);
        }
    }
}
