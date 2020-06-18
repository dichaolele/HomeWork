package com.example.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CalendarActivity";

    /**
     * 确定
     */
    private Button mBtn;
    private MaterialCalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();

    }

    private void initView() {
        mCalendarView = findViewById(R.id.calendarView);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);

        //获取当前的日期
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "year: " + year + ",month:" + month + ",day:" + day);
        //对日历进行设置的
        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)//设置一周的第一天是星期几
                .setMinimumDate(CalendarDay.from(2016, 4, 3))//设置日历的最早日期
                .setMaximumDate(CalendarDay.from(year, month, day))//设置日历的最大日期
                .setCalendarDisplayMode(CalendarMode.MONTHS)//日历的模式
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                sendDate();
                break;
        }
    }
    private void sendDate() {
       CalendarDay selectedDate = mCalendarView.getSelectedDate();
        EventBus.getDefault().post(selectedDate);
        finish();
    }
}
