package com.example.ljh.accountbook.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.UserDictionary;

import com.example.ljh.accountbook.model.Tb_accout;
import com.example.ljh.accountbook.model.Tb_user;

/**
 * 创建数据库
 * Created by Administrator on 2017/5/14 0014.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final int VERSION = 1;//数据库版本
    private static final String DBNAME = "account.db"; //数据库名



    public DBOpenHelper(Context context) {
        super(context,DBNAME , null, VERSION);
    }

    public DBOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table tb_user(_id integer primary key autoincrement,username varchar(10)," +
                "password varchar(20),incomeToday decimal,spendingToday decimal," +
                "incomeMonth decimal,spendingMonth decimal)"); //创建用户表

    }

    /**
     * 插入tb_accout_spend表
     * @param tb_accout
     */

    public void insertSpendAccout(Tb_accout tb_accout){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("money",tb_accout.getMoney());
        cv.put("time",tb_accout.getData());
        cv.put("type",tb_accout.getType());
        cv.put("note",tb_accout.getNote());
        database.insert("tb_accout_spend",null,cv);
    }

    public Cursor getAllCostData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("tb_accout_spend",null,null,null,null,null,"time"+"ASC");
    }




    /**
     * 删除表
     */
    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("tb_user",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("--------onUpdate Called--------" + oldVersion + "----->" +newVersion);
    }
}
