package com.cos.board.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.board.config.DBConn;
import com.cos.board.domain.user.dto.JoinReqDto;

public class UserDao {
	
	public int save(JoinReqDto dto) {
		String sql = "INSERT INTO user(username, password, email, userRole) VALUES(?,?,?,'USER')";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
}
