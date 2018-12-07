package com.dahai.draglist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者： 大海
 * 时间： 2018/12/7
 * 描述：
 */
public class DragListAdapter extends RecyclerView.Adapter<DragListHolder> {

    private List<String> list;

    public DragListAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DragListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DragListHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DragListHolder dragListHolder, int i) {
        dragListHolder.bindData(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
