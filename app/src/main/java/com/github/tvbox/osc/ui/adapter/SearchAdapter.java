package com.github.tvbox.osc.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.tvbox.osc.bean.Movie;

import java.util.ArrayList;

public class SearchAdapter extends BaseQuickAdapter<Movie.Video, BaseViewHolder> {
    public SearchAdapter() {
        super(0, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Movie.Video item) {
        // 简化实现
    }
}