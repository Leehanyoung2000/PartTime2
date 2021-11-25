package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn; //�옄諛붿� �뜲�씠�꽣踰좎씠�뒪瑜� �뿰寃�
	private PreparedStatement pstmt; //荑쇰━臾� ��湲� 諛� �꽕�젙
	private ResultSet rs; //寃곌낵媛� 諛쏆븘�삤湲�
	
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/abs";
			String user = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, user, password);
			System.out.println("연동확인");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String sql = "select userPassword from user where userID = ?";
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, userID); 
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //濡쒓렇�씤 �꽦怨�
				}else
					return 0; //鍮꾨�踰덊샇 ��由�
			}
			return -1; //�븘�씠�뵒 �뾾�쓬
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //�삤瑜�
	}
	
	public int join(User user) {
		  String sql = "insert into user values(?, ?, ?, ?, ?)";
		  try {
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, user.getUserID());
		    pstmt.setString(2, user.getUserPassword());
		    pstmt.setString(3, user.getUserName());
		    pstmt.setString(4, user.getUserGender());
		    pstmt.setString(5, user.getUserEmail());
		    return pstmt.executeUpdate();
		  }catch (Exception e) {
		 	e.printStackTrace();
		  }
		  return -1;
		}
}
