package com.example.android_study.ui_custom.calendar;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.android_study.R;
import com.example.android_study.ui_custom.calendar.view.HotelPriceCalendarBean;
import com.example.android_study.ui_custom.calendar.view.TripCalendarList;
import com.example.android_study._base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class CalendarAy extends BaseActivity {

    @BindView(R.id.calendar)
    TripCalendarList calendarList;

    @Override
    protected void initViewAndData(@Nullable Bundle savedInstanceState) {
        List<HotelPriceCalendarBean> priceCalendarList = (List<HotelPriceCalendarBean>) getIntent().getSerializableExtra("hotelPriceCalendar");
        if (priceCalendarList != null) {
            calendarList.setGoodsPriceList(priceCalendarList);
        }
        calendarList.setOnDateSelected(new TripCalendarList.OnDateSelected() {
            @Override
            public void selected(String startDate, String endDate) {
                try {
                    String[] startDateArray = startDate.split("-");
                    String[] endDateArray = endDate.split("-");

                    String startDateForYear = startDateArray[0];
                    String startDateForMonth = startDateArray[1];
                    String startDateForDay = startDateArray[2];

                    String endDateForYear = endDateArray[0];
                    String endDateForMonth = endDateArray[1];
                    String endDateForDay = endDateArray[2];

                    showToast(startDateForYear + "年" + startDateForMonth + "月" + startDateForDay + "日" + " - " + endDateForYear + "年" + endDateForMonth + "月" + endDateForDay + "日");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void singleDateSelected(String startDate) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar_ay;
    }

}
