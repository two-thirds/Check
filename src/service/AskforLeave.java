package service;

public class AskforLeave {
	private String Sno;
	private String Ldate;
	private String Lreason;
	private String Approved;

	public AskforLeave(String Sno, String Ldate,String Lreason,String Approved) {
		super();
		this.Sno = Sno;
		this.Ldate=Ldate;
		this.Lreason=Lreason;
		this.Approved=Approved;

	}
	public String getSno() {
		return Sno;
	}



	public void setSno(String sno) {
		Sno = sno;
	}



	public String getLdate() {
		return Ldate;
	}



	public void setLdate(String ldate) {
		Ldate = ldate;
	}



	public String getLreason() {
		return Lreason;
	}



	public void setLreason(String lreason) {
		Lreason = lreason;
	}



	public String getApproved() {
		return Approved;
	}



	public void setApproved(String approved) {
		Approved = approved;
	}




	public AskforLeave() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "student [Sno=" + Sno + ",Ldate=" + Ldate + ", Lreason="
				+ Lreason + ", Approved=" + Approved + "]";
	}
}
