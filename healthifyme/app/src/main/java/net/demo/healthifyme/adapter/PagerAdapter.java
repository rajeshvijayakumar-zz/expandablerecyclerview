package net.demo.healthifyme.adapter;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import net.demo.healthifyme.fragment.DefaultFragment;
import net.demo.healthifyme.fragment.SlotFragment;
import net.demo.healthifyme.model.Data;
import net.demo.healthifyme.model.DaySlot;

import java.util.List;
import java.util.Map;

/**
 * Created by rajesh5kumar on 3/7/16.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Data> TITLES;
    private Map<String, DaySlot> availableSlot;

    public PagerAdapter(FragmentManager fm, List<Data> titles, Map<String, DaySlot> availableSlot) {
        super(fm);
        this.TITLES = titles;
        this.availableSlot = availableSlot;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String date = (TITLES != null && TITLES.size() > 0) ? TITLES.get(position).getDate() : null;
        String day = (TITLES != null && TITLES.size() > 0) ? TITLES.get(position).getDay().substring(0, 3) : null;
        SpannableString titleStr = new SpannableString(date + "\n" + day);
        int dateLen = (date != null) ? date.length() : 0;
        titleStr.setSpan(new RelativeSizeSpan(4f), 0, dateLen, 0);
        titleStr.setSpan(new StyleSpan(Typeface.BOLD), 0, dateLen, 0);
        return (null != date && null != day) ? titleStr : "";
    }

    @Override
    public int getCount() {
        return (TITLES == null) ? 0 : TITLES.size();
    }

    @Override
    public Fragment getItem(int position) {

        DaySlot slot = (availableSlot != null &&  availableSlot.values() != null) ?
                (DaySlot) availableSlot.values().toArray()[position] : null;

        if (slot != null) {
            return SlotFragment.newInstance(slot);
        } else {
            return DefaultFragment.newInstance(position);
        }


    }
}
