package net.demo.healthifyme.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import net.demo.healthifyme.R;;
import net.demo.healthifyme.holder.MyChildViewHolder;
import net.demo.healthifyme.holder.MyGroupViewHolder;
import net.demo.healthifyme.model.DaySlot;
import net.demo.healthifyme.model.SlotEntity;
import net.demo.healthifyme.utils.DateTimeUtils;
import net.demo.healthifyme.utils.StringUtils;

import java.util.List;

public class ExpandableRecyclerAdapter
        extends AbstractExpandableItemAdapter<MyGroupViewHolder, MyChildViewHolder> {

    private DaySlot currentSlot;
    private Context context;

    public ExpandableRecyclerAdapter(Context context, DaySlot currentDaySlot) {
        this.currentSlot = currentDaySlot;
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildCount(int groupPosition) {

        return (currentSlot != null) ?
                currentSlot.getCurrentSlotsCount(groupPosition)  : 0;
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {

        String slotName = currentSlot.getCurrentSlotName(groupPosition);
        holder.mDaySlotNameView.setText(StringUtils.capitalize(slotName));

        int activeSlots = currentSlot.getActiveSlotCount(groupPosition);

        holder.mSlotAvailableView.setText(context.getResources()
                .getQuantityString(R.plurals.str_slots_available, activeSlots, activeSlots));
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {

        List<SlotEntity> slotEntityList = currentSlot.getCurrentSlot(groupPosition);
        SlotEntity slot = slotEntityList.get(childPosition);

        String startTime = DateTimeUtils.getFormattedTime(slot.getStartTime());
        String endTime = DateTimeUtils.getFormattedTime(slot.getEndTime());
        holder.mTimeText.setText(startTime+" - "+endTime);
        int colorResId = (slot.isBooked() || slot.isExpired()) ? R.color.healthify_dark_grey :
                android.R.color.white;
        holder.itemView.setBackgroundResource(colorResId);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {

        int angle = (expand) ? 180 : 0;
        holder.mIndicator.animate().rotation(angle).start();
        return true;
    }

}
