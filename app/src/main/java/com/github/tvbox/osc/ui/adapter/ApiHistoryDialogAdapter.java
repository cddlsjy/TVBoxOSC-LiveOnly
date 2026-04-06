package com.github.tvbox.osc.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ApiHistoryDialogAdapter extends ListAdapter<String, ApiHistoryDialogAdapter.SelectViewHolder> {

    class SelectViewHolder extends RecyclerView.ViewHolder {

        public SelectViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }

    public interface SelectDialogInterface {
        void click(String value);

        void del(String value, ArrayList<String> data);
    }


    private ArrayList<String> data = new ArrayList<>();

    private String select = "";

    private SelectDialogInterface dialogInterface = null;

    public ApiHistoryDialogAdapter(SelectDialogInterface dialogInterface) {
        super(new DiffUtil.ItemCallback<String>() {
            @Override
            public boolean areItemsTheSame(@NonNull @NotNull String oldItem, @NonNull @NotNull String newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull @NotNull String oldItem, @NonNull @NotNull String newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.dialogInterface = dialogInterface;
    }

    public void setData(List<String> newData, int defaultSelect) {
        data.clear();
        data.addAll(newData);
        select = data.get(defaultSelect);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public ApiHistoryDialogAdapter.SelectViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ApiHistoryDialogAdapter.SelectViewHolder(new View(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ApiHistoryDialogAdapter.SelectViewHolder holder, int position) {
        // 简化实现
    }
}
