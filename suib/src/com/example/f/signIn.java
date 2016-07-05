package com.example.f;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.e.R;

import service.UserService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class signIn extends Activity {
	
	private Button btnAskOff;//实现界面跳转
	private TextView num5;
	private TextView num1,num2,num3;//签到，未签到，请假情况	
	public String SchoolNum;//学生学号
	private int num;//随机数
	int Num1 = 0;
    int Num2 = 0;
    int Num3 = 0;
//	private int signinnum = 0;//签到，未签到，请假初始值？
//	private int signoutnum = 0;
//	private int askoffnum = 0;
//	private SimpleAdapter sa;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
		 
		 requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏全屏
	    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
	    		     WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.signin);
	       
			
	        SchoolNum = logIn.num;
			UserService uService = new UserService(signIn.this);
			Num1=uService.getnum1(SchoolNum);
			Num2=uService.getnum2(SchoolNum);
			Num3=uService.getnum3(SchoolNum);
			String N1=String.valueOf(Num1);
			String N2=String.valueOf(Num2);
			String N3=String.valueOf(Num3);
			Log.i("TAG", Num1 + "_" + Num2 +"_"+Num3);
			
//获取随机数	        
	        int numcode = (int) ((Math.random()*9+1));
	        num = (int) ((Math.random()*9+1)*(Math.pow(10, 9)));
			
			btnAskOff = (Button) findViewById(R.id.btnAskOff);
			num1 = (TextView) findViewById(R.id.num1);
			num2 = (TextView) findViewById(R.id.num2);
			num3 = (TextView) findViewById(R.id.num3);
			num5 = (TextView) findViewById(R.id.askoff);
			

			
			num1.setText(N1);
			num2.setText(N2);
			num3.setText(N3);

		   
//		    int b = Integer.parseInt(studentNum);
//		    int b = Integer.valueOf(studentNum).intValue(); 
//		    num5.getTag(b);
//赋值			
//			num1.setText("0");
//			num2.setText(signoutnum);
//			num3.setText(askoffnum);
			
//			sa = new SimpleAdapter(s3.this, li, R.layout.ss3,
//					new String[] { "userid", "username" }, new int[] { R.id.userid,
//							R.id.username });
//			setListAdapter(sa);
				        
	        btnAskOff.setOnClickListener(btnAskOffListener);
//判断时间	        
	        SimpleDateFormat formatterd = new SimpleDateFormat ("dd");
	        SimpleDateFormat formatterh = new SimpleDateFormat ("HH");
			SimpleDateFormat formatterm = new SimpleDateFormat ("mm");
			Date curDate = new Date(System.currentTimeMillis());//获取当前时间
			String strd=formatterd.format(curDate);
			String strh = formatterh.format(curDate);
			String strm = formatterm.format(curDate);
			int d=Integer.parseInt(strd);
			float hh = Float.parseFloat(strh);
			float m = Float.parseFloat(strm);
			float mm=(float) (m*0.01);
			float time=hh+mm;
			System.out.println(time);

			if(time>=00.00&&time<=23.50)
			{
//数据库调取学生学号		
//			MainActivity ac = new MainActivity();	
//		    studentNum = ac.getNum();
				
		    int b = Integer.parseInt(SchoolNum);
		    int c=b%6;
			int random=d%6;	
//				a = 2;
				if(random == c){//判断随机数是否和学号一样
				//一样，输出签到码	
					
					AlertDialog.Builder builder = new AlertDialog.Builder(signIn.this);
					builder.setTitle("您的签到码是：");
					builder.setIcon(R.drawable.ic_launcher);
					builder.setMessage("               "+num);
					
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
					{
	//
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(signIn.this, "签到成功！", Toast.LENGTH_SHORT).show();
//						    int SI=Num1+1;	
//						    String si=String.valueOf(SI);
//						    num1.setText(si);
						//	String str = "UPDATEtablesignSETnum1 = signinnum+1,num2 = signoutnum,num3 = askoffnum";
//							db.execSQL(str);
							UserService uService = new UserService(signIn.this);
							uService.updateNum1(SchoolNum);
							int uNum1=uService.getnum1(SchoolNum);
							Log.i("TAG", uNum1+"_");
							num1.setText(uNum1+"");

							
						}					
					});
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(signIn.this, "签到失败！", Toast.LENGTH_SHORT).show();
//							signoutnum = signoutnum + 1;
  						  //  String str = "UPDATEtablesignSETnum1 = signinnum,num2 = signoutnum+1,num3 = askoffnum";
//							db.execSQL(str);
							UserService uService = new UserService(signIn.this);
							uService.updateNum2(SchoolNum);
							int uNum2=uService.getnum2(SchoolNum);
							Log.i("TAG", uNum2+"_");
							num2.setText(uNum2+"");
						}					
					});
					builder.show();
				}
				else
				{
					//不一样，输入签到码
					 //设置exidtext的id
					LayoutInflater inflater = LayoutInflater.from(signIn.this);
					final View textEntryView = inflater.inflate(R.layout.code,
							null);
					final EditText exitNum = (EditText) textEntryView.findViewById(R.id.editNum);
					
					final AlertDialog.Builder builder1 = new AlertDialog.Builder(signIn.this);
					builder1.setTitle("请输入签到码：");
					builder1.setIcon(R.drawable.ic_launcher);
//					builder1.setView(new EditText(this));
					builder1.setView(textEntryView);
					
					builder1.setPositiveButton("确定", new DialogInterface.OnClickListener()                                                                            
					{
						@Override
						public void onClick(DialogInterface dialog, int which) {
	                   //判断验证码是否正确	 
							int x = Integer.parseInt(exitNum.getText().toString());	
							if(exitNum == null || exitNum.equals("")){
								
								Toast.makeText(signIn.this, "签到失败！", Toast.LENGTH_SHORT).show();
								UserService uService = new UserService(signIn.this);
    							uService.updateNum2(SchoolNum);
    							int uNum2=uService.getnum2(SchoolNum);
    							Log.i("TAG", uNum2+"_");
    							num2.setText(uNum2+"");
							}
                            if(x == num){
                            	
//                            //	String str = "UPDATEtablesignSETnum1 = signinnum+1,num2 = signoutnum,num3 = askoffnum";
////    							db.execSQL(str);
    							Toast.makeText(signIn.this, "签到成功！", Toast.LENGTH_SHORT).show();
//    							signinnum = signinnum+1;
    							UserService uService = new UserService(signIn.this);
    							uService.updateNum1(SchoolNum);
    							int uNum1=uService.getnum1(SchoolNum);
    							Log.i("TAG", uNum1+"_");
    							num1.setText(uNum1+"");

                            }
						else{
//                            //	String str = "UPDATEtablesignSETnum1 = signinnum,num2 = signoutnum+1,num3 = askoffnum";
////    							db.execSQL(str);
    							Toast.makeText(signIn.this, "签到失败！", Toast.LENGTH_SHORT).show();
//    							signoutnum = signoutnum + 1;
    							UserService uService = new UserService(signIn.this);
    							uService.updateNum2(SchoolNum);
    							int uNum2=uService.getnum2(SchoolNum);
    							Log.i("TAG", uNum2+"_");
    							num2.setText(uNum2+"");
                            }                                                                                                                                                                                                                                                                         
							
						}
					});
					builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() 
					{
						@Override
						public void onClick(DialogInterface dialog, int which) {	 
						Toast.makeText(signIn.this, "签到失败！", Toast.LENGTH_SHORT).show();
						}
					});
					builder1.show();
				}
			
			}
	 }
	 
	// 查
//		public List chaxun() {
//
////			ArrayList list = new ArrayList();
////			//游标选择操作
////			Cursor cursor = db.rawQuery("select * from user", null);
////			while (cursor.moveToNext()) {
////				HashMap hm = new HashMap();//hashmap读取并存储数据库内容
////				hm.put("userid", cursor.getString(0));
////				hm.put("username", cursor.getString(1));
////				list.add(hm);
////			}
////
////			// cursor.close();
////			 //db.close();
////
//			return list;
//		}
	 
//	private void chooseTime(){
//		//判断时间	        
//	     SimpleDateFormat formatterh = new SimpleDateFormat ("HH");
//			SimpleDateFormat formatterm = new SimpleDateFormat ("mm");
//			Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//			String strh = formatterh.format(curDate);
//			String strm = formatterm.format(curDate);
//			float hh = Float.parseFloat(strh);
//			float m = Float.parseFloat(strm);
//			float mm=(float) (m*0.01);
//			float time=hh+mm;
//			System.out.println(time);
//	} 
		   	
	private Button.OnClickListener btnAskOffListener = new Button.OnClickListener() {
		   	public void onClick(View v) {
		   			 Intent intent = new Intent();
		   			 intent.setClass(signIn.this, askOff.class);
		   			 startActivity(intent);
		   		
		   }
		   };

	    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
}