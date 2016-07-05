package service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
	private DatabaseHelper dbHelper;

	public UserService(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	// 登录用
	// String Sno,String Sname,String Password,String Schname,String
	// Cname,String Dname,String Rno
	public boolean login(String Sno, String Password, String Schname) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from student where Sno=? and Password=? and Schname=?";
		Cursor cursor = sdb.rawQuery(sql,new String[] { Sno, Password, Schname });
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return true;
		}
		else{
		return false;
		}
	}

	//注册前判断是否用户存在
	public boolean judge(String Sno,String Schname)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from student where Sno=? and Schname=?";
		Cursor cursor = sdb.rawQuery(sql,new String[] { Sno, Schname });
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return true;
		}
		else
			{return false;}
	}
	
	// 注册用
	public boolean register(student student) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into student(Sno,Sname,Password,Schname,Cname,Dname,Rno) values(?,?,?,?,?,?,?)";
		Object obj[] = { student.getSno(), student.getSname(),
				student.getPassword(), student.getSchname(),
				student.getCname(), student.getDname(), student.getRno() };
		sdb.execSQL(sql, obj);
		return true;
	}
	
	public boolean askforleave(AskforLeave AskforLeave)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into askforleave(Sno,Ldate,Lreason) values(?,?,?)";
		Object obj[] = {AskforLeave.getSno(),AskforLeave.getLdate(),AskforLeave.getLreason()};
		sdb.execSQL(sql, obj);
		return true;
	}
	public int getnum1(String Sno)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from student where Sno=?";
		Cursor cursor= sdb.rawQuery(sql,new String[] {Sno});
		System.out.println(cursor.getColumnCount());
		int SIT=0;
		if(cursor!=null)
		{
			while(cursor.moveToNext()){
				SIT=cursor.getInt(cursor.getColumnIndex("SignInTimes"));
			}
		}
		System.out.println(SIT);
		return SIT;
	}
	public int getnum2(String Sno)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from student where Sno=?";
		Cursor cursor= sdb.rawQuery(sql,new String[] {Sno});
		int NSIT=0;
		if(cursor!=null)
		{
				while(cursor.moveToNext()){
			NSIT=cursor.getInt(cursor.getColumnIndex("NoSignInTimes"));
				}
		}
		return NSIT;
	}
	
	public int getnum3(String Sno)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();   //妈的看错
		String sql = "select * from student where Sno=?";
		Cursor cursor= sdb.rawQuery(sql,new String[] {Sno});
		int AFLT=0;
		if(cursor!=null)
		{
				while(cursor.moveToNext()){
			AFLT=cursor.getInt(cursor.getColumnIndex("AskforleaveTimes"));
				}
		}
		return AFLT;
	}
	public boolean updateNum1(String Sno)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		int signInTime = getnum1(Sno);
		signInTime ++;
		System.out.println("signInTime:  "+signInTime);
		String sql = "update student set SignInTimes='"+ signInTime +"' where Sno=?";
		sdb.execSQL(sql, new String[] {Sno});
		return true;
		
//		if (cursor.moveToFirst() == true)
//		{
//			System.out.println("-------updateNum1 success-----------");
//			return true;
//		}
//		else
//		{
//			System.out.println("-------updateNum1 fail-----------");
//			int signInTimetest = getnum1(Sno);
//			System.out.println("signInTime:  "+signInTimetest);
//			return false;
//		}
	}
	public boolean updateNum2(String Sno)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		int NosignInTime = getnum2(Sno);
		NosignInTime ++;
		System.out.println("NosignInTime:  "+NosignInTime);
		String sql = "update student set NoSignInTimes='"+ NosignInTime +"' where Sno=?";
		sdb.execSQL(sql, new String[] {Sno});
		return true;
	}
	
	public boolean updateNum3(String Sno)
	{
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		int AskforleaveTime = getnum3(Sno);
		AskforleaveTime ++;
		System.out.println("AskforleaveTime:  "+AskforleaveTime);
		String sql = "update student set AskforleaveTimes='"+ AskforleaveTime +"' where Sno=?";
		sdb.execSQL(sql, new String[] {Sno});
		return true;
	}
//	public boolean test(String Sno,String Ldate,String Lreason)
//	{
//		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
//		String sql = "select * from askforleave where Sno=? and Ldate=? and Lreason=?";
//		Cursor cursor = sdb.rawQuery(sql,new String[] { Sno, Ldate,Lreason });
//		if (cursor.moveToFirst() == true) {
//			cursor.close();
//			return true;
//		}
//		return false;
//	}
}
