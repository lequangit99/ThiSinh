package com.kiemtra.thisinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "thisinh_manager";
    private static final String TABLE_NAME = "thisinh";
    private static final String SBD = "sbd";
    private static final String HOTEN = "hoten";
    private static final String TOAN = "toan";
    private static final String LY = "ly";
    private static final String HOA = "hoa";
    private static int VERSION = 1;

    private Context context;


    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            SBD + " TEXT primary key, " +
            HOTEN + " TEXT, " +
            TOAN + " REAL, " +
            LY + " REAL, " +
            HOA + " REAL)";

    public DBContext(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);

        String sql;

        //thêm dữ liệu
        sql = "INSERT INTO thisinh (sbd, hoten, toan, ly, hoa) VALUES ('GHA01', 'Lê Quang', '7.8', '7.5', '5.5')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO thisinh (sbd, hoten, toan, ly, hoa) VALUES ('GHA02', 'Kim Tuyến', '6.8', '2.5', '8.5')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO thisinh (sbd, hoten, toan, ly, hoa) VALUES ('GHA03', 'Long', '5.8', '7.1', '2.5')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO thisinh (sbd, hoten, toan, ly, hoa) VALUES ('GHA04', 'Lập', '4.8', '8.5', '5.7')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO thisinh (sbd, hoten, toan, ly, hoa) VALUES ('GHA05', 'Thắng', '7.1', '9.5', '9.5')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO thisinh (sbd, hoten, toan, ly, hoa) VALUES ('GHA06', 'Toàn', '6.8', '7.1', '5.2')";
        sqLiteDatabase.execSQL(sql);
    }

    public void addThiSinh(ThiSinh thiSinh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SBD, thiSinh.getmSBD());
        values.put(HOTEN, thiSinh.getmHoTen());
        values.put(TOAN, thiSinh.getmToan());
        values.put(LY, thiSinh.getmLy());
        values.put(HOA, thiSinh.getmHoa());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public List<ThiSinh> getAllThiSinh() {
        List<ThiSinh> listThiSinh = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                ThiSinh thiSinh = new ThiSinh();
                thiSinh.setmSBD(cursor.getString(0));
                thiSinh.setmHoTen(cursor.getString(1));
                thiSinh.setmToan(cursor.getFloat(2));
                thiSinh.setmLy(cursor.getFloat(3));
                thiSinh.setmHoa(cursor.getFloat(4));
                listThiSinh.add(thiSinh);

            } while (cursor.moveToNext());
        }
        db.close();
        return listThiSinh;
    }
    public int updateThiSinh(ThiSinh thiSinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SBD,thiSinh.getmSBD());
        contentValues.put(HOTEN,thiSinh.getmHoTen());
        contentValues.put(TOAN,thiSinh.getmToan());
        contentValues.put(LY,thiSinh.getmLy());
        contentValues.put(HOA,thiSinh.getmHoa());
        return db.update(TABLE_NAME,contentValues,SBD+"=?",new String[]{String.valueOf(thiSinh.getmSBD())});
    }
    public int deleteVeTau(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,SBD+"=?",new String[] {String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
