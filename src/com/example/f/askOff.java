package com.example.f;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.e.R;

import service.AskforLeave;
import service.UserService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class askOff extends Activity {
	private int year;
	private int month;
	private int day;
	private EditText date,reason;
//	private TextView time,textreason;
	private String Date,Reason;
	private Button btnAskoff;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏全屏
	    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
	    		     WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.askoff);
	        DatePicker Chdate = (DatePicker) findViewById(R.id.datePicker1);
	        
	        final EditText date = (EditText) findViewById(R.id.date);
	        final EditText reason = (EditText) findViewById(R.id.reason);
//	        final TextView date = (TextView) findViewById(R.id.time);
//	        final TextView textreason = (TextView) findViewById(R.id.textreason);
	        
	        
	        
//	        TextView askoff = (TextView) findViewById(R.id.askoff);
	        
	        Button btnAskoff = (Button) findViewById(R.id.btnAskoff);
	        
	        Calendar ca = Calendar.getInstance();
	        year = ca.get(Calendar.YEAR);
	        month = ca.get(Calendar.MONTH);
	        day = ca.get(Calendar.DAY_OF_MONTH);
	        
			Chdate.init(year, month, day,new OnDateChangedListener(){
	        	
				@Override
				public void onDateChanged(DatePicker view, int year, int month,
						int day) {
					// TODO Auto-generated method stub
	                date.setText("                    "+year+"年"+(month+1)+"月"+day+"日");
				}
	        });	
			
			btnAskoff.setOnClickListener(btnAskoffListener);
			
			Date = date.getText().toString().trim();
			Reason = reason.getText().toString().trim();
			System.out.println(date.getText());
			System.out.println(reason.getText());
	}
	private Button.OnClickListener btnAskoffListener = new Button.OnClickListener() {
		
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(askOff.this);
				builder.setTitle("提示");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setMessage("确定提交申请？");
				
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
//
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
//						MainActivity ma=new MainActivity();
//						num = SchoolNum.getText().toString().trim();
						String num=logIn.num;
						Log.i("TAG", num + "_" + Date + "_" + Reason);
						UserService uService = new UserService(askOff.this);
						AskforLeave as = new AskforLeave();
						as.setSno(num);
					    as.setLdate(Date);
					    as.setLreason(Reason);


                     	boolean flag=uService.askforleave(as);

						    	
						    	Toast.makeText(askOff.this, "申请成功！", Toast.LENGTH_SHORT).show();


//					    }
					    
						
						
					}
//					
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(askOff.this, "取消申请！", Toast.LENGTH_SHORT).show();
					}
					
				});
				builder.show();
			}
//			}
	};

	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	}
