package guestbook;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class GuestBookDAOImpl extends JDBConnect implements GuestBookDAO{
	// DB 연결
	public GuestBookDAOImpl(ServletContext application) {
		super(application);
	} 
	@Override
	public List<GuestBookDTO> getList() {
	List<GuestBookDTO> lists = new ArrayList<>();
		String sql = "SELECT * FROM guestbook order by no";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				GuestBookDTO dto = new GuestBookDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setContent(rs.getString("content"));
				dto.setRegDate(rs.getDate("reg_date"));
				
				lists.add(dto);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return lists;
	}

	@Override
	public int insert(GuestBookDTO dto) {
		int result = 0;
		String sql = " INSERT INTO guestbook "
					+ " VALUES(seq_guestbook_no.nextval, ?, ?, ?, sysdate)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getContent());
			
			result = psmt.executeUpdate();
		} catch(SQLException se) {
			se.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int no, String password) {
		int result = 0;
		
		String sql = " DELETE FROM guestbook "
				+ " WHERE no = ? and password = ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			psmt.setString(2, password);
			result = psmt.executeUpdate();
		} catch(SQLException se) {
			se.printStackTrace();
		}
		return result;
	}

}
