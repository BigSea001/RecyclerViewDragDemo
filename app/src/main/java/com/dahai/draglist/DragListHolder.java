package com.dahai.draglist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * 作者： 大海
 * 时间： 2018/12/7
 * 描述：
 */
public class DragListHolder extends RecyclerView.ViewHolder {

    private TextView tv;

    public DragListHolder(@NonNull View itemView) {
        super(itemView);

        tv = itemView.findViewById(R.id.tv);
    }

    public void bindData(String s) {
        tv.setText(s);
    }
}
