package com.ero.jadwalpengajaran;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.ero.jadwalpengajaran.adapter.JadwalAdapter;
import com.ero.jadwalpengajaran.api.BaseApiService;
import com.ero.jadwalpengajaran.api.UtilsApi;
import com.ero.jadwalpengajaran.model.DataListJadwal;
import com.ero.jadwalpengajaran.model.ResultJadwal;
import com.ero.jadwalpengajaran.model.ValueJadwal;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CompactCalendarView compactCalendarView;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Context context;

    private SQLiteDatabaseHandler db;

    ArrayList<DataListJadwal> jadwal = new ArrayList<>();

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    BaseApiService mApiService;

    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = UtilsApi.getAPIService();
        db = new SQLiteDatabaseHandler(this);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(null);

        Intent intent = getIntent();
        int key = intent.getIntExtra("KEY", 0);
        day = intent.getIntExtra("DAY", 0);
        month = intent.getIntExtra("MONTH", 0);
        year = intent.getIntExtra("YEAR",0);

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if (key == 1){
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEventActivity.class));
            }
        });

        layoutManager = new LinearLayoutManager(this);

        compactCalendarView.drawSmallIndicatorForEvents(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                List<DataListJadwal> jadwal = db.getJadwal(dateFormatForDay.format(dateClicked));


                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);

                adapter = new JadwalAdapter(jadwal, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Date : " + dateFormatForDay.format(dateClicked), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));

            }

        });


        addDummyEvents(day, month, year);

    }

    private void addDummyEvents(int day, int month, int year) {

        int mon = 0;
        if (month == 1){
            mon = Calendar.JANUARY;
        }else if (month == 2){
            mon = Calendar.FEBRUARY;
        }else if (month == 3){
            mon = Calendar.MARCH;
        }else if (month == 4){
            mon = Calendar.APRIL;
        }else if (month == 5){
            mon = Calendar.MAY;
        }else if (month == 6){
            mon = Calendar.JUNE;
        }else if (month == 7){
            mon = Calendar.JULY;
        }else if (month == 8){
            mon = Calendar.AUGUST;
        }else if (month == 9){
            mon = Calendar.SEPTEMBER;
        }else if (month == 10){
            mon = Calendar.OCTOBER;
        }else if (month == 11){
            mon = Calendar.NOVEMBER;
        }else if (month == 12){
            mon = Calendar.DECEMBER;
        }
        int desember = Calendar.DECEMBER;

        addEvents(compactCalendarView, mon, day, year);
        compactCalendarView.invalidate();
    }


    private void addEvents(CompactCalendarView compactCalendarView, int month, int day, int year) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
//        for (int i = 0; i < 1; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (year > -1) {
                currentCalender.set(Calendar.YEAR, year);
            }
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            currentCalender.add(Calendar.DATE, day-1);
            setToMidnight(currentCalender);
            compactCalendarView.addEvent(new CalendarDayEvent(currentCalender.getTimeInMillis(), Color.argb(255, 255, 255, 255)), false);
//        }
    }


    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }


    public void gotoToday() {

        // Set any date to navigate to particular date
        compactCalendarView.setCurrentDate(Calendar.getInstance(Locale.getDefault()).getTime());


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.login:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }
}
