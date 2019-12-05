

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
	public String IP;
	public Connection conn;
	public LoginInfo info;

	public SQL() {
		try {
			IP = "127.0.0.1";
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/��Ȱ��?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "fss7182963");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SQL(String IP) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/��Ȱ��?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "fss7182963");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SQL(String IP, String id, String pw) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/��Ȱ��?useSSL=false&useUnicode=true&characterEncoding=UTF-8", id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setInfo(String id, String pw) {
		info = new LoginInfo(id, pw);
	}

	public void doLogin() {
		String SQL = "select pw, ���� from ����� where id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getId());

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString(1).equals(info.getPw())) {
					info.setName(rs.getString(2));
				} else {
					System.out.println("��й�ȣ Ʋ��");
				}
			} else {
				System.out.println("�α��� ���� ����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doLogin(String id, String pw) {
		String checkID = "select pw, ���� from ����� where id=?";


		try {
			PreparedStatement pstmt = conn.prepareStatement(checkID);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					info.setPw(rs.getString(1));
					info.setName(rs.getString(2));
				} else {
					System.out.println("��й�ȣ Ʋ��");
				}
			} else {
				System.out.println("�α��� ���� ����");
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void confirmApplication(String priority, String period, String dormID, String eatDays) {
		String SQL = "insert into ��û(`�л�id`, `����Ⱓ`, `��Ȱ������id`, `������ȣ`, `�Ļ籸���ڵ�`) values(?, ?, ?, ?, ?)";

		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getName());
			pstmt.setString(2, period);
			pstmt.setString(3, dormID);
			pstmt.setString(4, priority);
			pstmt.setString(5, eatDays);

			int r = pstmt.executeUpdate();

			System.out.println(r>0?"success":"fail");

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void InsertStudentAllocate() {
		String SQL = "insert into ����" 
				+"select  �л�id, ��Ȱ������id, ����Ⱓ" 
				+"from �ӽý�û���̺�" + 
				"where ����Ⱓ = '1��' and �����հ� is null";
		String SQL1 = "insert into ����" 
				+"select ��û.�л�id, ��û.��Ȱ������id, ��û.����Ⱓ" + 
				"from (select row_number() over(partition by ����Ⱓ,��Ȱ������id, ������ȣ order by �����հ� desc) as tempNumber, "
				+"�ӽý�û��.�л�id, �ӽý�û��.��Ȱ������id, �ӽý�û��.����Ⱓ from (select �л�id, ��Ȱ������id, ����Ⱓ, ������ȣ, �����հ� from �ӽý�û���̺�"
				+" where ����Ⱓ = '1�б�' and ������ȣ = 1) as �ӽý�û�� left outer join ���� on �ӽý�û��.�л�id = ����.�л�id where ����.�л�id is null)"
				+" as ��û, ��Ȱ��_��û" + "where ��û.����Ⱓ = ��Ȱ��_��û.����Ⱓ and ��û.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id and ��û.tempNumber "
				+"<= ��Ȱ��_��û.���ռ����ο�_�� + ��Ȱ��_��û.���ռ����ο�_�� - (select count(*) from ���� where ����.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id)";
		String SQL2 = "insert into ����" 
				+"select ��û.�л�id, ��û.��Ȱ������id, ��û.����Ⱓ" + 
				"from (select row_number() over(partition by ����Ⱓ,��Ȱ������id, ������ȣ order by �����հ� desc) as tempNumber, "
				+"�ӽý�û��.�л�id, �ӽý�û��.��Ȱ������id, �ӽý�û��.����Ⱓ from (select �л�id, ��Ȱ������id, ����Ⱓ, ������ȣ, �����հ� from �ӽý�û���̺�"
				+" where ����Ⱓ = '1�б�' and ������ȣ = 2) as �ӽý�û�� left outer join ���� on �ӽý�û��.�л�id = ����.�л�id where ����.�л�id is null)"
				+" as ��û, ��Ȱ��_��û" + "where ��û.����Ⱓ = ��Ȱ��_��û.����Ⱓ and ��û.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id and ��û.tempNumber "
				+"<= ��Ȱ��_��û.���ռ����ο�_�� + ��Ȱ��_��û.���ռ����ο�_�� - (select count(*) from ���� where ����.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id)";
		String SQL3 = "insert into ����" 
				+"select ��û.�л�id, ��û.��Ȱ������id, ��û.����Ⱓ" + 
				"from (select row_number() over(partition by ����Ⱓ,��Ȱ������id, ������ȣ order by �����հ� desc) as tempNumber, "
				+"�ӽý�û��.�л�id, �ӽý�û��.��Ȱ������id, �ӽý�û��.����Ⱓ from (select �л�id, ��Ȱ������id, ����Ⱓ, ������ȣ, �����հ� from �ӽý�û���̺�"
				+" where ����Ⱓ = '1�б�' and ������ȣ = 3) as �ӽý�û�� left outer join ���� on �ӽý�û��.�л�id = ����.�л�id where ����.�л�id is null)"
				+" as ��û, ��Ȱ��_��û" + "where ��û.����Ⱓ = ��Ȱ��_��û.����Ⱓ and ��û.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id and ��û.tempNumber "
				+"<= ��Ȱ��_��û.���ռ����ο�_�� + ��Ȱ��_��û.���ռ����ο�_�� - (select count(*) from ���� where ����.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id)";

		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			PreparedStatement pstmt1 = conn.prepareStatement(SQL1);
			PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
			PreparedStatement pstmt3 = conn.prepareStatement(SQL3);
			int r = pstmt.executeUpdate();
			int r1 = pstmt1.executeUpdate();
			int r2 = pstmt2.executeUpdate();
			int r3 = pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void InsertAwaitingStudent() {
		String SQL = "insert into ���" 
				+"select �ӽý�û���̺�.�л�id, row_number() over(order by �ӽý�û���̺�.�����հ� desc) as ����ȣ"
				+"from �ӽý�û���̺� left outer join ���� on �ӽý�û���̺�.�л�id = ����.�л�id "
				+"where ����.�л�id is null" + 
				"group by �ӽý�û���̺�.�л�id";



		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void InsertDepositStudent() {
		String SQL = "insert into �Ա� (`�л�id`, `�Ի���`) " + 
				"select ��û.�л�id," + 
				"case when ��û.�Ļ籸���ڵ� = 10 then ��Ȱ��_��û.7�Ͻĺ�� + ��Ȱ��_��û.��Ȱ������ + ��Ȱ��_��û.��Ÿ�������" + 
				"when ��û.�Ļ籸���ڵ� = 20 then ��Ȱ��_��û.5�Ͻĺ�� + ��Ȱ��_��û.��Ȱ������ + ��Ȱ��_��û.��Ÿ�������" + 
				"else 0" + 
				"end as ��Ȱ����" + 
				"from ��û, ����, ��Ȱ��_��û" + 
				"where ��û.�л�id = ����.�л�id and ��û.����Ⱓ = ����.����Ⱓ and ��û.��Ȱ������id = ����.��Ȱ������id and ����.��Ȱ������id = ��Ȱ��_��û.��Ȱ������id and ��û.����Ⱓ = ��Ȱ��_��û.����Ⱓ" + 
				"group by ��û.�л�id";
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void UpdateDepositToDefault() {
		String SQL = "UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939411', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20160011');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939403', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20160022');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939402', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20160298');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939401', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20171111');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939404', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20180003');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939405', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20180105');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939406', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20180179');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939407', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20180198');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939408', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20180457');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939409', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20180548');" + 
				"UPDATE `mydb`.`�Ա�` SET `������¹�ȣ` = '3521386939410', `���񳳺ο���` = '�̳�' WHERE (`�л�id` = '20191111');";
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertTuberOkStudent() {
		String SQL = "insert into �������ܼ� (`�л�id`)" + 
				"select �л�id from �Ա� where ���񳳺ο��� = '����';";
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertAssignStudent() {
		String SQL = "insert into ����(�л�id, ��Ȱ��id)" + 
				"" + 
				"select ��������.�л�id, case when (��������.��Ȱ������id = 10 or ��������.��Ȱ������id = 11 and ��������.num <= ȣ��.�����ο�) then 20" + 
				"when (��������.��Ȱ������id = 10 or ��������.��Ȱ������id = 11 and ��������.num > ȣ��.�����ο�) then 10" + 
				"when (��������.��Ȱ������id = 30 or ��������.��Ȱ������id = 31 or ��������.��Ȱ������id = 32) then 30 " + 
				"when (��������.��Ȱ������id = 60 and ��������.num <= ȣ��.�����ο�) then 70" + 
				"when (��������.��Ȱ������id = 80 or ��������.��Ȱ������id = 81 or ��������.��Ȱ������id = 82 or ��������.��Ȱ������id = 83) then 80" + 
				"else ��������.��Ȱ������id end as ��Ȱ��id" + 
				"from (select row_number() over(partition by ����.��Ȱ������id) as num ,����.�л�id, ����.��Ȱ������id " + 
				"from �������ܼ�, ���� where �������ܼ�.�����Ͻ� is not null and year(�������ܼ�.�����Ͻ�) = 2020 and �������ܼ�.�л�id = ����.�л�id) as ��������, ȣ��, ��Ȱ��" + 
				"where ��Ȱ��.��Ȱ��id = ȣ��.��Ȱ��id and ��������.��Ȱ������id = ��Ȱ��.��Ȱ������id" + 
				"" + 
				"group by ��������.�л�id;";
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertSchedule(String sName, String sCode, String startTime, String finishTime) {
		String SQL = "insert into ����(������ , ���������ڵ� , �����Ͻ� , �����Ͻ�) values (?,?,?,?)";
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sName);
			pstmt.setString(2, sCode);
			pstmt.setString(3, startTime);
			pstmt.setString(4, finishTime);
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String args[]) {
		SQL sql = new SQL();

		//sql.setInfo("20160011", "*******");
		sql.doLogin("2016001", "******");

		System.out.println(sql.info.getPw()); // ����ó�� �ʿ�
	}
}