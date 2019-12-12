package DormitoryProgram;


public class Tuber {	
	private String studentid;
	private String filename;
	private String submitdate;
	private String diagnosisdate;
	
	public Tuber(String studentid, String filename, String submitdate, String diagnosisdate) {
		this.studentid = studentid;
		this.filename = filename;
		this.submitdate = submitdate;
		this.diagnosisdate = diagnosisdate;
	}
	
	public Tuber() {};
	
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public String getDiagnosisdate() {
		return diagnosisdate;
	}

	public void setDiagnosisdate(String diagnosisdate) {
		this.diagnosisdate = diagnosisdate;
	}

}
