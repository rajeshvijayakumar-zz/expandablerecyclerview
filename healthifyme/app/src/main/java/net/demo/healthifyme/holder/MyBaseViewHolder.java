package net.demo.healthifyme.holder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import net.demo.healthifyme.R;

/**
 * Created by rajesh5kumar on 15/7/16.
 */
public abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
    public FrameLayout mContainer;

    public MyBaseViewHolder(View v) {
        super(v);
        mContainer = (FrameLayout) v.findViewById(R.id.container);

    }
}