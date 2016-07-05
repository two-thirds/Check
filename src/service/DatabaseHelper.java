package service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	

	public DatabaseHelper(Context context) {
		super(context, "sign.db", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table student(Sno text primary key,Sname text,Password text,Schname text,Cname text,Dname text,Rno text,SignInTimes int,NoSignInTimes int,AskforleaveTimes int)");
		db.execSQL("create table askforleave(Sno text primary key,Ldate text,Lreason text,Approved text)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.e("DBOpenHelper", "onUpgradeonUpgradeonUpgradeonUpgrade");
		db.execSQL("DROP TABLE IF EXISTS student");
		db.execSQL("DROP TABLE IF EXISTS askforleave"); 
	}

}
