package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookDTO;

public class GuestBookDAOImpl implements GuestBookDAO {

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@192.168.3.52:1521:xe";
			conn = DriverManager.getConnection(dburl, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 로드 실패!");
		}
		return conn;
	}

	@Override
	public List<GuestBookDTO> getList() {
		List<GuestBookDTO> lists = new ArrayList<>();
		Connection con = null;
		Statement stmt;
		ResultSet rs;

		String sql = "SELECT * FROM guestbook order by no desc";

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				GuestBookDTO dto = new GuestBookDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPassword(rs.getString("password"));
				dto.setContent(rs.getString("content"));
				dto.setRegDate(rs.getDate("reg_date"));

				lists.add(dto);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return lists;
	}

	@Override
	public int insert(GuestBookDTO dto) {
		int result = 0;
		Connection con = null;
		PreparedStatement psmt = null;
		String sql = " INSERT INTO guestbook " + " VALUES(seq_guestbook_no.nextval, ?, ?, ?, sysdate)";
		try {
			con = getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getContent());

			result = psmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int no, String password) {
		int result = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = " DELETE FROM guestbook " + " WHERE no = ? and password = ?";
		try {
			con = getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			psmt.setString(2, password);
			result = psmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return result;
	}

}
