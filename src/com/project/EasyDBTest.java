package com.project;
import java.sql.Connection;
import java.sql.DriverManager;
public class EasyDBTest {

		public final String _URL ="jdbc:oracle:thin:@192.168.0.9:1521:orcl11";
		public final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
		public final String _USER = "easy";
		public final String _PW = "486";	
		Connection con = null;
		//객체주입을 해주는 메소드를 따로 구현해 보기
		//클래스 메소드 이니까 인스턴스화 없이 바로 호출 가능
		public static EasyDBTest getInstance() {
			EasyDBTest dbTest = null;
			if(dbTest==null) {
				dbTest = new EasyDBTest();
			}
			return dbTest;
		}	//오라클서버의 드라이버 클래스가 메모리에 정상적으로 로딩 되는지 체크
		public boolean driverTest() {
			//insert here
			boolean isOk = false;
			try {
				Class.forName(_DRIVER);//여기 실행됨
				isOk = true;
			} catch (ClassNotFoundException ce) {
				// TODO: handle exception
				System.out.println("드라이버 클래스를 찾을 수 없습니다.");
				isOk = false;
			}
			return isOk;
		}
		//물리적으로 떨어져 있는 서버에 연결통로 확보 되었는지 체크
		public boolean connectTest() {
			//insert here
			boolean isOk = false;
			try {
				//예외가 발생할 가능성이 있는 코드를 쓴다.
				Class.forName(_DRIVER);
				con = DriverManager.getConnection(_URL, _USER, _PW);	
				if(con!=null) {
					isOk = true;
				}
			} catch (Exception e) {
				//예외상황이 발생했을 때만 실행기회를 갖게됨.
				System.out.println(e.toString());
			}
			return isOk;
		}
		//물리적으로 떨어져 있는 서버에 연결통로 확보하기
		public Connection getConnection() {
			try {
				//예외가 발생할 가능성이 있는 코드를 쓴다.
				Class.forName(_DRIVER);
				con = DriverManager.getConnection(_URL, _USER, _PW);	
			} catch (Exception e) {
				//예외상황이 발생했을 때만 실행기회를 갖게됨.
				System.out.println(e.toString());
			}
			return con;
		}	
		public static void main(String args[]) {
			EasyDBTest dbtest= new EasyDBTest();
			MgrBookDao mbdao= new MgrBookDao();
			//bDao.getBookList(null);
			//같은 서버를 바라보고 있으므로 같은 값을 넣으면 테스트 불가함.
		//	int result = bDao.bookDelete("5438415");//값에 의한 호출임. 참조에 의한 호출
		//	System.out.println("처리 결과는..."+result);
	/*		boolean isOk2 = dbTest.connectTest();
			System.out.println("true이면 성공한거..."+isOk2);
			boolean isOk = dbTest.driverTest();
			if(isOk) {
				System.out.println("드라이버를 찾았습니다.");
			}else {
				System.out.println("드라이버를 못 찾았습니다.");
			}*/
		}
	}


