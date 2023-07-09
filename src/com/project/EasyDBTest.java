package com.project;
import java.sql.Connection;
import java.sql.DriverManager;
public class EasyDBTest {

		public final String _URL ="jdbc:oracle:thin:@192.168.0.9:1521:orcl11";
		public final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
		public final String _USER = "easy";
		public final String _PW = "486";	
		Connection con = null;
		//��ü������ ���ִ� �޼ҵ带 ���� ������ ����
		//Ŭ���� �޼ҵ� �̴ϱ� �ν��Ͻ�ȭ ���� �ٷ� ȣ�� ����
		public static EasyDBTest getInstance() {
			EasyDBTest dbTest = null;
			if(dbTest==null) {
				dbTest = new EasyDBTest();
			}
			return dbTest;
		}	//����Ŭ������ ����̹� Ŭ������ �޸𸮿� ���������� �ε� �Ǵ��� üũ
		public boolean driverTest() {
			//insert here
			boolean isOk = false;
			try {
				Class.forName(_DRIVER);//���� �����
				isOk = true;
			} catch (ClassNotFoundException ce) {
				// TODO: handle exception
				System.out.println("����̹� Ŭ������ ã�� �� �����ϴ�.");
				isOk = false;
			}
			return isOk;
		}
		//���������� ������ �ִ� ������ ������� Ȯ�� �Ǿ����� üũ
		public boolean connectTest() {
			//insert here
			boolean isOk = false;
			try {
				//���ܰ� �߻��� ���ɼ��� �ִ� �ڵ带 ����.
				Class.forName(_DRIVER);
				con = DriverManager.getConnection(_URL, _USER, _PW);	
				if(con!=null) {
					isOk = true;
				}
			} catch (Exception e) {
				//���ܻ�Ȳ�� �߻����� ���� �����ȸ�� ���Ե�.
				System.out.println(e.toString());
			}
			return isOk;
		}
		//���������� ������ �ִ� ������ ������� Ȯ���ϱ�
		public Connection getConnection() {
			try {
				//���ܰ� �߻��� ���ɼ��� �ִ� �ڵ带 ����.
				Class.forName(_DRIVER);
				con = DriverManager.getConnection(_URL, _USER, _PW);	
			} catch (Exception e) {
				//���ܻ�Ȳ�� �߻����� ���� �����ȸ�� ���Ե�.
				System.out.println(e.toString());
			}
			return con;
		}	
		public static void main(String args[]) {
			EasyDBTest dbtest= new EasyDBTest();
			MgrBookDao mbdao= new MgrBookDao();
			//bDao.getBookList(null);
			//���� ������ �ٶ󺸰� �����Ƿ� ���� ���� ������ �׽�Ʈ �Ұ���.
		//	int result = bDao.bookDelete("5438415");//���� ���� ȣ����. ������ ���� ȣ��
		//	System.out.println("ó�� �����..."+result);
	/*		boolean isOk2 = dbTest.connectTest();
			System.out.println("true�̸� �����Ѱ�..."+isOk2);
			boolean isOk = dbTest.driverTest();
			if(isOk) {
				System.out.println("����̹��� ã�ҽ��ϴ�.");
			}else {
				System.out.println("����̹��� �� ã�ҽ��ϴ�.");
			}*/
		}
	}


