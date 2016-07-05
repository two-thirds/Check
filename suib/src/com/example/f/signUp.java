package com.example.f;

import com.example.e.R;

import service.UserService;
import service.student;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends Activity {

	private Button logon;
	private TextView school, dept, schoolnum, name, buildingnum, chambernum,password;
	private EditText SchoolNum, Name, ChamberNum, Password;
	private Spinner spnSchool, spnDept, spnBuilding;
	String[] Schools = new String[] { "³����ѧ", "�廪��ѧ", "�����ѧ", "����ش�ѧ" };
	String[] Depts = new String[] { "��ѧԺ", "��Ϣ���������ѧԺ", "��ľ����ѧԺ", "���˼����ѧԺ",
			"����ѧԺ" };
	String[] Buildings = new String[] { "��ʮ��", "��ʮ��", "����ʮ��", "����" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		// 2.ͨ��findViewById�ķ�����ȡ�ؼ����
		logon = (Button) findViewById(R.id.logon);

		schoolnum = (TextView) findViewById(R.id.schoolnum);
		name = (TextView) findViewById(R.id.name);
		school = (TextView) findViewById(R.id.school);
		dept = (TextView) findViewById(R.id.dept);
		buildingnum = (TextView) findViewById(R.id.buildingnum);
		chambernum = (TextView) findViewById(R.id.chambernum);
		password = (TextView) findViewById(R.id.password);

		SchoolNum = (EditText) findViewById(R.id.SchoolNum);
		Name = (EditText) findViewById(R.id.Name);
		ChamberNum = (EditText) findViewById(R.id.ChamberNum);
		Password = (EditText) findViewById(R.id.Password);
		// ����������
		spnSchool = (Spinner) findViewById(R.id.spnSchool);
		spnDept = (Spinner) findViewById(R.id.spnDept);
		spnBuilding = (Spinner) findViewById(R.id.spnBuilding);

		ArrayAdapter<String> adapterSchools = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Schools);// ��ʽ
		spnSchool.setAdapter(adapterSchools);// ��������Դ
		spnSchool.setOnItemSelectedListener(spnSchoolListener);

		ArrayAdapter<String> adapterDepts = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Depts);
		spnDept.setAdapter(adapterDepts);
		spnDept.setOnItemSelectedListener(spnDeptListener);

		ArrayAdapter<String> adapterBuildings = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Buildings);
		spnBuilding.setAdapter(adapterBuildings);
		spnBuilding.setOnItemSelectedListener(spnBuildingListener);

		// 3.Ϊbutton�������click�¼��ļ���������ʱִ�ж��巽��
		logon.setOnClickListener(btnlogonListener);
	}

	// ʵ��button�ļ�������
	private Button.OnClickListener btnlogonListener = new Button.OnClickListener() {
		public void onClick(View v) {
			final String school = spnSchool.getSelectedItem().toString().trim();
			final String dept = spnDept.getSelectedItem().toString().trim();
			final String num = SchoolNum.getText().toString().trim();
			final String name = Name.getText().toString().trim();
			final String buildingnum = spnBuilding.getSelectedItem().toString()
					.trim();
			final String chambernum = ChamberNum.getText().toString().trim();
			final String password = Password.getText().toString().trim();

			if (school == null || school.equals("") || dept == null
					|| dept.equals("") || num == null || num.equals("")
					|| name == null || name.equals("") || buildingnum == null
					|| buildingnum.equals("") || chambernum == null
					|| chambernum.equals("") || password == null
					|| password.equals("")) {
				/**
				 * �ݴ����жϿؼ���textֵ�Ƿ�Ϊ�գ�Ϊ���򵯳�������Ϊ��ִ���߳�
				 */
				Toast.makeText(getApplicationContext(), "�뽫��Ϣ��д������", 0).show();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(signUp.this);
				builder.setTitle("��ʾ");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setMessage("ȷ��ע����Ϣ��ȷ��");
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Log.i("TAG", num + "_" + name + "_" + password
										+ "_" + school + "_" + dept + "_"
										+ buildingnum + "_" + chambernum);
								UserService uService = new UserService(signUp.this);
								student stu = new student();
								stu.setSno(num);
								stu.setSname(name);
								stu.setPassword(password);
								stu.setSchname(school);
								stu.setCname(dept);
								stu.setDname(buildingnum);
								stu.setRno(chambernum);
								boolean flag=uService.judge(num, school);
								if(flag)
								{
									Toast.makeText(signUp.this, "��ѧУ��ѧ���ѱ�ע�ᣬ���������룡",
											Toast.LENGTH_LONG).show();
								}
								else
								{
									boolean flag1=uService.register(stu);
									if(flag1)
									{
										Toast.makeText(signUp.this, "ע��ɹ�",
												Toast.LENGTH_LONG).show();
										Intent intent = new Intent();
										intent.setClass(signUp.this, logIn.class);
										startActivity(intent);
									}
									
									else
									{
										Toast.makeText(signUp.this, "ע��ʧ�ܣ�������",
												Toast.LENGTH_LONG).show();
									}
								}
								
							}

						});
				builder.setNegativeButton("�ٿ���",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}

						});
				builder.show();
			}

		}
	};

	// spinner�ļ�������
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
	private Spinner.OnItemSelectedListener spnDeptListener = new Spinner.OnItemSelectedListener() {
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
	private Spinner.OnItemSelectedListener spnBuildingListener = new Spinner.OnItemSelectedListener() {
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
