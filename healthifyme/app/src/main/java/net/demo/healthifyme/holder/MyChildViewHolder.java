package net.demo.healthifyme.holder;

import android.view.View;
import android.widget.TextView;

import net.demo.healthifyme.R;

/**
 * Created by rajesh5kumar on 15/7/16.
 */
public class MyChildViewHolder extends MyBaseViewHolder {

    public TextView mTimeText;

    public MyChildViewHolder(View v) {
        super(v);
        mTimeText = (TextView) v.findViewById(R.id.time_text);
    }
}
