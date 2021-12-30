package emaillist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class EmaillistDAOImpl extends JDBConnect implements EmaillistDAO{
	
	// DB 연결
	public EmaillistDAOImpl(ServletContext application) {
		super(application);
	} 
	public List<EmaillistDTO> getList(){
		List<EmaillistDTO> lists = new ArrayList<>();
		
		String sql = "SELECT * FROM emaillist";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmaillistDTO dto = new EmaillistDTO();
				dto.setLastName(rs.getString("last_name"));
				dto.setfirstName(rs.getString("first_name"));
				dto.setEmail(rs.getString("email"));
				
				lists.add(dto);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return lists;
	}
	public int insert(String lastName, String firstName, String email) {
		int result = 0;
		
		String sql = " INSERT INTO emaillist "
					+ " VALUES(seq_emaillist_no.nextval, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, lastName);
			psmt.setString(2, firstName);
			psmt.setString(3, email);
			
			result = psmt.executeUpdate();
		} catch(SQLException se) {
			se.printStackTrace();
		}
		return result;
	}

}
