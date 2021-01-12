package com.cos.board.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.board.config.DBConn;
import com.cos.board.domain.user.dto.JoinReqDto;
import com.cos.board.domain.user.dto.LoginReqDto;

public class UserDao {
	
	public int count() {
		String sql = "SELECT count(*) FROM user WHERE userRole='USER'";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public int deleteById(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	public List<User> findAll(int page) {
		String sql = "SELECT id, username, email, userRole FROM user WHERE NOT userRole = 'admin' ORDER BY id DESC LIMIT ?, 8 ";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, page*8);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.build();
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
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
	
	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id, username, email, userRole FROM user WHERE username=? AND password=?";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.userRole(rs.getString("userRole"))
						.build();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
}
