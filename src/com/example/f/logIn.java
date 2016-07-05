package com.example.f;

import com.example.e.R;

import service.UserService;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class logIn extends Activity {

	private Button btnLogin;
	private TextView textLogon;
	private EditText SchoolNum, Password;
//	private String Num;
	public static String num;
	private Spinner spnSchool;
	String[] Schools = new String[] { "³����ѧ", "�廪��ѧ", "�����ѧ", "����ش�ѧ" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		SQLiteDatabase db = openOrCreateDatabase("student.db",MODE_PRIVATE,null);
//		db.execSQL("create table if not exits student(Sno text primary key,Sname text,Password text,Schname text,Cname text,Dname text,Rno text,SignInTimes int,NoSignInTimes int,AskforleaveTimes int)");

		btnLogin = (Button) findViewById(R.id.btnLogin);
		textLogon = (TextView) findViewById(R.id.textLogon);

		SchoolNum = (EditText) findViewById(R.id.SchoolNum);
		Password = (EditText) findViewById(R.id.Password);

		spnSchool = (Spinner) findViewById(R.id.spnSchool);

		ArrayAdapter<String> adapterSchools = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Schools);// ��ʽ
		spnSchool.setAdapter(adapterSchools);// ��������Դ
		spnSchool.setOnItemSelectedListener(spnSchoolListener);

		btnLogin.setOnClickListener(btnLoginListener);
		textLogon.setOnClickListener(textLogonListener);
		


	}

	private ImageView.OnClickListener textLogonListener = new ImageView.OnClickListener() {
		public void onClick(View v) {

			Intent intent = new Intent();
			intent.setClass(logIn.this, signUp.class);
			startActivity(intent);

		}
	};

	private Button.OnClickListener btnLoginListener = new Button.OnClickListener() {
		public void onClick(View v) {
			final String name = spnSchool.getSelectedItem().toString().trim();
			 num = SchoolNum.getText().toString().trim();
//			try{
//			 Num = Integer.parseInt(SchoolNum.getText().toString());
//            }catch(Exception e){
//           return;
//             }
//			Num = Integer.valueOf(SchoolNum.getText().toString().trim()).intValue(); 
//			if (num != null && num.length() > 0) {
//                Num = Integer.parseInt(num);
//            }
//			Num =Integer.parseInt(num);
			
//			String a=e.getText()
//					  int b=Integer.valueOf(a);
//			
//			Num=Integer.valueOf(num);
//			 Num = num;
			final String psw = Password.getText().toString().trim();
			if (name == null || name.equals("") || num == null
					|| num.equals("") || psw == null || psw.equals("")) {
				/**
				 * �ݴ����жϿؼ���textֵ�Ƿ�Ϊ�գ�Ϊ���򵯳�������Ϊ��ִ���߳�
				 */
				Toast.makeText(getApplicationContext(), "�뽫��Ϣ��д����", 0).show();
			} else {

				Log.i("TAG", num + "_" + psw + "_" + name);
				UserService uService = new UserService(logIn.this);
				boolean flag = uService.login(num, psw, name);
				if (flag) {
					Toast.makeText(logIn.this, "��¼�ɹ�", Toast.LENGTH_LONG)
							.show();
					Intent intent = new Intent();
					intent.setClass(logIn.this, signIn.class);
					startActivity(intent);
				} else {

					Log.i("TAG", "��¼ʧ��");
					Toast.makeText(logIn.this, "��¼ʧ��", Toast.LENGTH_LONG)
							.show();
				}
				// Thread thread = new Thread() {
				// public void run() {
				// final String result = HttpUtils.requestHttp(name,num,psw);
				// if (result.equals("true")) {
				// Intent intent = new Intent();
				// intent.setClass(MainActivity.this, s3.class);
				// startActivity(intent);
				// } else {
				// Toast.makeText(getApplicationContext(),
				// "�����ڴ��û�����ע��", 0).show();
				// }
				// }
				// };
			}
			;

		}
	};
	
//	public  String getNum()
//	{
//		Num = 2;
//		return Num;
//	}

	// ����
	private Spinner.OnItemSelectedListener spnSchoolListener = new Spinner.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {

			String sel = parent.getSelectedItem().toString();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
	};
//	public Object num5;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static ContextWrapper getContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
