package com.ero.jadwalpengajaran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ero.jadwalpengajaran.model.DataListJadwal;

import java.util.ArrayList;
import java.util.List;

import static android.media.MediaFormat.KEY_HEIGHT;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JadwalPengajaranDB4";
    private static final String TABLE_NAME = "jadwal_pengajaran";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE jadwal_pengajaran ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "judul TEXT, "
                + "nim TEXT, "
                + "nama TEXT, "
                + "tanggal TEXT, "
                + "waktu TEXT, "
                + "ruang TEXT, "
                + "narasumber1 TEXT, "
                + "narasumber2 TEXT, "
                + "narasumber3 TEXT)"
                ;

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addJadwal(DataListJadwal jadwal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("judul", jadwal.getJudul());
        values.put("nim", jadwal.getNim());
        values.put("nama", jadwal.getNama());
        values.put("tanggal", jadwal.getTanggal());
        values.put("waktu", jadwal.getWaktu());
        values.put("ruang", jadwal.getRuang());
        values.put("narasumber1", jadwal.getNarasumber1());
        values.put("narasumber2", jadwal.getNarasumber2());
        values.put("narasumber3", jadwal.getNarasumber3());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public List<DataListJadwal> allJadwal() {
        List<DataListJadwal> students = new ArrayList<>();
        String selectQuery = "SELECT  * FROM jadwal_pengajaran";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DataListJadwal jadwal = new DataListJadwal();
                jadwal.setJudul(cursor.getString(1));
                jadwal.setNama(cursor.getString(2));
                jadwal.setNim(cursor.getString(3));
                jadwal.setTanggal(cursor.getString(4));
                jadwal.setWaktu(cursor.getString(5));
                jadwal.setRuang(cursor.getString(6));
                jadwal.setNarasumber1(cursor.getString(7));
                jadwal.setNarasumber2(cursor.getString(8));
                jadwal.setNarasumber3(cursor.getString(9));
                students.add(jadwal);
            } while (cursor.moveToNext());
        }

        db.close();
        return students;
    }

    public List<DataListJadwal> getJadwal(String tanggal) {
        List<DataListJadwal> students = new ArrayList<>();
        String selectQuery = "SELECT  * FROM jadwal_pengajaran WHERE tanggal='"+tanggal+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DataListJadwal jadwal = new DataListJadwal();
                jadwal.setJudul(cursor.getString(1));
                jadwal.setNama(cursor.getString(2));
                jadwal.setNim(cursor.getString(3));
                jadwal.setTanggal(cursor.getString(4));
                jadwal.setWaktu(cursor.getString(5));
                jadwal.setRuang(cursor.getString(6));
                jadwal.setNarasumber1(cursor.getString(7));
                jadwal.setNarasumber2(cursor.getString(8));
                jadwal.setNarasumber3(cursor.getString(9));
                students.add(jadwal);
            } while (cursor.moveToNext());
        }

        db.close();
        return students;
    }

}
