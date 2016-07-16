package net.demo.healthifyme.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.demo.healthifyme.R;

/**
 * Created by rajesh5kumar on 15/7/16.
 */
public class MyGroupViewHolder extends MyBaseViewHolder {

    public TextView mDaySlotNameView;
    public TextView mSlotAvailableView;
    public ImageView mIndicator;

    public MyGroupViewHolder(View v) {
        super(v);
        mDaySlotNameView = (TextView) v.findViewById(R.id.day_slot_name);
        mSlotAvailableView = (TextView) v.findViewById(R.id.slot_count_view);
        mIndicator = (ImageView) v.findViewById(R.id.indicator);
    }
}
