package com.javaex.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.javaex.vo.GuestBookDTO;

public interface GuestBookDAO {
	public Connection getConnection() throws SQLException;
	public List<GuestBookDTO> getList();
	public int insert(GuestBookDTO dto);
	public int delete(int no, String password);
}
