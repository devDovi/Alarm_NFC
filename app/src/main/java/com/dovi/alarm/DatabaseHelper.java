package com.dovi.alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsm2016 on 2018-06-13.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AlarmItemDB";
    private static final String TABLE_DRINK = "Drink";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ALARMITEM =
                "CREATE TABLE" + DATABASE_NAME + "";

        db.execSQL(CREATE_TABLE_ALARMITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_ALARMITEM =
                "DROP TABLE IF EXISTS " + TABLE_DRINK + "" +
                        "";

        db.execSQL(DROP_TABLE_ALARMITEM);
        onCreate(db);
    }

    public void add(RecyclerItem recyclerItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recyclerItem.getItemNum());

        db.insert(TABLE_DRINK, null, values);
        db.close();
    }

    public List<RecyclerItem> getAll() {
        List<RecyclerItem> drinkList = new ArrayList<RecyclerItem>();

        String SELECT_ALL = "SELECT * FROM " + TABLE_DRINK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                //RecyclerItem drink = new RecyclerItem();
                //drink.setId(Integer.parseInt(cursor.getString(0)));
                //drink.setName(cursor.getString(1));
                //drinkList.add(drink);
            } while (cursor.moveToNext());
        }

        return drinkList;
    }
}
