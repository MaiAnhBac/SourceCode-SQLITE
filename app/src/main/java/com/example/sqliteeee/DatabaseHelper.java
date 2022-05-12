package com.example.sqliteeee;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME ="cafebox.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MYLOG", "onCreate: DatabaseHelper");

        //tạo bảng Mon
        String sqlCreateTableMon = "CREATE TABLE IF NOT EXISTS " +
                "Mon(MaMon INTEGER PRIMARY KEY AUTOINCREMENT, TenMon VARCHAR(200), Gia REAL)";
        db.execSQL(sqlCreateTableMon);
        //Tạo dữ liệu bản đầu
        db.execSQL("INSERT INTO Mon VALUES(null, 'Cafe', 20000)");
        db.execSQL("INSERT INTO Mon VALUES(null, 'Sinh Tố Dâu', 30000)");
        db.execSQL("INSERT INTO Mon VALUES(null, 'Nước Cam Ep', 25000)");
        db.execSQL("INSERT INTO Mon VALUES(null, 'Trà bí đao', 10000)");
    }
    //để thực hiện phương pháp này
    @Override
    public void onUpgrade(SQLiteDatabase db, int olaVersion, int newVersion) {
    Log.d("MYLOG", "onUpgrade: DatabaseHelper");
    //bỏ bảng cũ hơn nếu tồn tại
    db.execSQL("DROP TABLE IF EXISTS Mon");
    onCreate(db);
    }
    //phương thức
    public void ThemMon(String tenMon, double gia){
        String sqlInsert =  String.format("INSERT INTO Mon VALUES(null, '%s', %f)", tenMon, gia);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }
    public void capNhapMon (int maMon, String tenMon, double gia){
        String sqlUpdate =  String.format("UPDATE Mon SET TenMon = '%s', Gia = %f WHERE MaMon = %d", tenMon, gia, maMon);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }
    public void xoaMon(int maMon){
        String sqlDelete =  String.format("DELETE FROM Mon WHERE MaMon = %d", maMon);
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
    public List<Mon> layDanhSachMon(){
        List<Mon> listMon = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlSelect = "SELECT * FROM Mon";

        Cursor cursor = db.rawQuery(sqlSelect, null );
        while (cursor.moveToNext()){
            int maMon = cursor.getInt(0);
            String tenMon = cursor.getString(1);
            double gia = cursor.getDouble(2);
            listMon.add(new Mon(maMon, tenMon, gia));
        }
        cursor.close();
        return listMon;
    }

}
