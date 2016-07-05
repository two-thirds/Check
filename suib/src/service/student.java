package service;

public class student {

	private String Sno;
	private String Rno;
	private String Password;
	private String Schname;
	private String Sname;
	private String Cname;
	private String Dname;
	private int SignInTimes;
	private int NoSignInTimes;
	private int AskforleaveTimes;

	public student(String Sno, String Sname, String Password, String Schname,
			String Cname, String Dname, String Rno) {
		super();

		this.Sno = Sno;
		this.Sname = Sname;
		this.Password = Password;
		this.Schname = Schname;
		this.Cname = Cname;
		this.Dname = Dname;
		this.Rno = Rno;

	}



	public String getRno() {
		return Rno;
	}

	public void setRno(String rno) {
		Rno = rno;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public int getSignInTimes() {
		return SignInTimes;
	}

	public void setSignInTimes(int signInTimes) {
		SignInTimes = signInTimes;
	}

	public int getNoSignInTimes() {
		return NoSignInTimes;
	}

	public void setNoSignInTimes(int noSignInTimes) {
		NoSignInTimes = noSignInTimes;
	}

	public int getAskforleaveTimes() {
		return AskforleaveTimes;
	}

	public void setAskforleaveTimes(int askforleaveTimes) {
		AskforleaveTimes = askforleaveTimes;
	}

	public student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSchname() {
		return Schname;
	}

	public void setSchname(String schname) {
		Schname = schname;
	}

	@Override
	public String toString() {
		return "student [Sno=" + Sno + ",Sname=" + Sname + ", Password="
				+ Password + ", Schname=" + Schname + ", Cname=" + Cname
				+ ",Dname=" + Dname + ", Rno=" + Rno + "SignInTimes="
				+ SignInTimes + ",NoSignInTimes=" + NoSignInTimes
				+ ",AskforleaveTimes=" + AskforleaveTimes + "]";
	}
}
