package com.ivan.healthcare.healthcare_android.ui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import com.ivan.healthcare.healthcare_android.AppContext;
import com.ivan.healthcare.healthcare_android.R;
import com.ivan.healthcare.healthcare_android.customobj.Time;
import com.ivan.healthcare.healthcare_android.util.Compat;
import com.ivan.healthcare.healthcare_android.view.AlarmView;
import java.util.ArrayList;

/**
 * 闹钟设置页面
 * Created by Ivan on 16/4/9.
 */
public class AlarmActivity extends AppCompatActivity {

    private static final int ADD_ALARM_ITEM_ID = 0x31;

    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private ListView mListView;
    private BaseAdapter mAlarmAdapter;
    private ArrayList<Time> mAlarmArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADD_ALARM_ITEM_ID, 0, R.string.alarm_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case ADD_ALARM_ITEM_ID:
                addAlarm();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        refreshAlarms();

        View rootView = View.inflate(this, R.layout.activity_alarm, null);

        mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.alarm_CoordinatorLayout);
        mToolbar = (Toolbar) rootView.findViewById(R.id.alarm_toolbar);
        mToolbar.setTitle(R.string.alarm_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mListView = (ListView) rootView.findViewById(R.id.alarm_listView);
        mAlarmAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mAlarmArrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return mAlarmArrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                AlarmView alarmView;
                if (convertView != null) {
                    alarmView = (AlarmView) convertView;
                } else {
                    alarmView = new AlarmView(AlarmActivity.this);
                    alarmView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppContext.dp2px(60)));
                }
                alarmView.setTime(mAlarmArrayList.get(position));
                final int pos = position;
                alarmView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeAlarm(pos);
                    }
                });
                return alarmView;
            }
        };
        mListView.setAdapter(mAlarmAdapter);

        setContentView(rootView);
    }

    private void refreshAlarms() {
        if (mAlarmArrayList == null) {
            mAlarmArrayList = new ArrayList<>();
        } else {
            mAlarmArrayList.clear();
        }

        mAlarmArrayList.add(new Time(9, 30, 0, true));
        mAlarmArrayList.add(new Time(10, 30, 1, false));
        mAlarmArrayList.add(new Time(11, 10, 2, true));
    }

    private void addAlarm() {

    }

    private void changeAlarm(final int position) {
        Time time = mAlarmArrayList.get(position);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Time t = mAlarmArrayList.get(position);
                t.setHour(hourOfDay);
                t.setMinute(minute);
                mAlarmAdapter.notifyDataSetChanged();
                Snackbar.make(mCoordinatorLayout, t + "", Snackbar.LENGTH_SHORT).show();
            }
        }, time.getHour(), time.getMinute(), true);
        timePickerDialog.show();
        Compat.fixDialogStyle(timePickerDialog);
    }
}