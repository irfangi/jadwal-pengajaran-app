package com.ero.jadwalpengajaran;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ero.jadwalpengajaran.adapter.JadwalAdapter;
import com.ero.jadwalpengajaran.model.DataListJadwal;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {
    CompactCalendarView compactCalendarView;
    TextView tvBulan;
    EditText etJudul, etNama, etNim, etTanggal, etWaktu, etRuang, etNarasumber1, etNarasumber2, etNarasumber3;
    Button btnCancel, btnAdd;
    Context context;

    private SQLiteDatabaseHandler db;

    ArrayList<DataListJadwal> jadwal = new ArrayList<>();

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());

    private SimpleDateFormat dateFormatDay = new SimpleDateFormat("dd", Locale.getDefault());
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MM", Locale.getDefault());
    private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy", Locale.getDefault());

    private String day = "";
    private String month = "";
    private String year = "";

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        db = new SQLiteDatabaseHandler(this);

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        tvBulan = (TextView) findViewById(R.id.tv_bulan);
        etJudul = (EditText) findViewById(R.id.et_judul);
        etNama = (EditText) findViewById(R.id.et_nama);
        etNim = (EditText) findViewById(R.id.et_nim);
        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        etWaktu = (EditText) findViewById(R.id.et_waktu);
        etRuang = (EditText) findViewById(R.id.et_ruang);
        etNarasumber1 = (EditText) findViewById(R.id.et_narasumber1);
        etNarasumber2 = (EditText) findViewById(R.id.et_narasumber2);
        etNarasumber3 = (EditText) findViewById(R.id.et_narasumbe3);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        compactCalendarView.drawSmallIndicatorForEvents(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        tvBulan.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                etTanggal.setText(dateFormatForDay.format(dateClicked));
                day = dateFormatDay.format(dateClicked);
                month = dateFormatMonth.format(dateClicked);
                year = dateFormatYear.format(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                tvBulan.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }

        });

        etWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etWaktu.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Pilih Jam");
                mTimePicker.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                intent.putExtra("KEY", 1);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = etJudul.getText().toString();
                String nama = etNama.getText().toString();
                String nim = etNim.getText().toString();
                String tanggal = etTanggal.getText().toString();
                String waktu = etWaktu.getText().toString();
                String ruang = etRuang.getText().toString();
                String narasumber1 = etNarasumber1.getText().toString();
                String narasumber2 = etNarasumber2.getText().toString();
                String narasumber3 = etNarasumber3.getText().toString();

                DataListJadwal jadwal = new DataListJadwal(judul, nama, nim, tanggal, waktu, ruang, narasumber1, narasumber2, narasumber3);
                db.addJadwal(jadwal);

                compactCalendarView.invalidate();
                Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                intent.putExtra("KEY", 1);
                intent.putExtra("DAY", Integer.parseInt(day));
                intent.putExtra("MONTH", Integer.parseInt(month));
                intent.putExtra("YEAR", Integer.parseInt(year));
                startActivity(intent);
            }
        });


    }
}
