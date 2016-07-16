package net.demo.healthifyme;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.demo.healthifyme.adapter.PagerAdapter;
import net.demo.healthifyme.constants.ApiConstants;
import net.demo.healthifyme.handler.APIRequestCreator;
import net.demo.healthifyme.handler.ApiHandler;
import net.demo.healthifyme.model.AvailableSlot;
import net.demo.healthifyme.model.Data;
import net.demo.healthifyme.model.DaySlot;
import net.demo.healthifyme.utils.IConnectionUtils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mParentView;
    private Toolbar mToolbar;
    private TextView mMonthView;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabLayout;
    private ProgressBar mProgressBarView;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initConnection();
//        initViewPagerAndTabs();
    }

    private void initConnection() {
        if (IConnectionUtils.isNetworkAvailable(getApplicationContext())) {
            createRequest();
        } else {
            Snackbar.make(mParentView, getResources().getString(R.string.no_network_available),
                    Snackbar.LENGTH_LONG)
                    .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            initConnection();
                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.background_dark))
                    .show();
        }
    }

    private void createRequest() {
        APIRequestCreator apiService =
                ApiHandler.getClient().create(APIRequestCreator.class);

        Call<AvailableSlot> call = apiService.getAvailableSlots(
                ApiConstants.VALUE_USERNAME,
                ApiConstants.VALUE_API_KEY,
                ApiConstants.VALUE_VC,
                ApiConstants.VALUE_EXPERTUSERNAME,
                ApiConstants.VALUE_FORMAT
        );

        call.enqueue(new Callback<AvailableSlot>() {

            @Override
            public void onResponse(Call<AvailableSlot> call, Response<AvailableSlot> response) {
                if (response != null && response.body() != null) {
                    updateUI(response.body());
                }
            }

            @Override
            public void onFailure(Call<AvailableSlot> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void initUI() {

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mParentView = (RelativeLayout) findViewById(R.id.parent_view);
        mMonthView = (TextView) findViewById(R.id.month_name_view);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (PagerSlidingTabStrip) findViewById(R.id.tabLayout);
        mProgressBarView = (ProgressBar) findViewById(R.id.progress_dialog);
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    private void updateUI(AvailableSlot slot) {
        mProgressBarView.setVisibility(View.GONE);
        String month = (slot.getSlotsMonthName() != null) ?
                slot.getSlotsMonthName() : "";
        mMonthView.setText(month);
        initViewPagerAndTabs(slot.getTitles(), slot.getAvailableSlots());
    }

    private void initViewPagerAndTabs(List<Data> dataLst, Map<String, DaySlot> availableSlot) {

        int count = (availableSlot != null && availableSlot.size() > 0) ? availableSlot.size() : 0;
        mTabLayout.setShouldExpand((count < 5));
        mAdapter = new PagerAdapter(getSupportFragmentManager(), dataLst, availableSlot);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
