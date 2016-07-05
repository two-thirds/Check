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
	String[] Schools = new String[] { "鲁东大学", "清华大学", "哈佛大学", "加理敦大学" };
	String[] Depts = new String[] { "商学院", "信息与电气工程学院", "土木工程学院", "马克思主义学院",
			"金融学院" };
	String[] Buildings = new String[] { "北十三", "北十五", "北二十二", "南五" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		// 2.通过findViewById的方法获取控件组件
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
		// 设置下拉框
		spnSchool = (Spinner) findViewById(R.id.spnSchool);
		spnDept = (Spinner) findViewById(R.id.spnDept);
		spnBuilding = (Spinner) findViewById(R.id.spnBuilding);

		ArrayAdapter<String> adapterSchools = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Schools);// 格式
		spnSchool.setAdapter(adapterSchools);// 设置数据源
		spnSchool.setOnItemSelectedListener(spnSchoolListener);

		ArrayAdapter<String> adapterDepts = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Depts);
		spnDept.setAdapter(adapterDepts);
		spnDept.setOnItemSelectedListener(spnDeptListener);

		ArrayAdapter<String> adapterBuildings = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Buildings);
		spnBuilding.setAdapter(adapterBuildings);
		spnBuilding.setOnItemSelectedListener(spnBuildingListener);

		// 3.为button组件加入click事件的监听，触发时执行定义方法
		logon.setOnClickListener(btnlogonListener);
	}

	// 实现button的监听函数
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
				 * 容错处理，判断控件中text值是否为空，为空则弹出报错，不为空执行线程
				 */
				Toast.makeText(getApplicationContext(), "请将信息填写完整！", 0).show();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(signUp.this);
				builder.setTitle("提示");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setMessage("确定注册信息正确？");
				builder.setPositiveButton("确定",
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
									Toast.makeText(signUp.this, "该学校该学号已被注册，请重新输入！",
											Toast.LENGTH_LONG).show();
								}
								else
								{
									boolean flag1=uService.register(stu);
									if(flag1)
									{
										Toast.makeText(signUp.this, "注册成功",
												Toast.LENGTH_LONG).show();
										Intent intent = new Intent();
										intent.setClass(signUp.this, logIn.class);
										startActivity(intent);
									}
									
									else
									{
										Toast.makeText(signUp.this, "注册失败，请重试",
												Toast.LENGTH_LONG).show();
									}
								}
								
							}

						});
				builder.setNegativeButton("再看看",
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

	// spinner的监听函数
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
