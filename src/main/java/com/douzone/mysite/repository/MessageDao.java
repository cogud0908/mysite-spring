package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.MessageVo;

@Repository
public class MessageDao {

	@Autowired
	private SqlSession sqlSession;
	
//	public void Insert(MessageVo vo) {
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = 
//				"insert into message " + 
//				"values(null, ?, ?, ?)";
//			
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getContent());
//			pstmt.setInt(2, vo.getBoard_no());
//			pstmt.setInt(3, vo.getUser_no());
//			
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//			// 자원 정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			//1. 드라이버 로딩
//			Class.forName( "com.mysql.jdbc.Driver" );
//			
//			//2. 연결하기
//			String url="jdbc:mysql://localhost/webdb?characterEncoding=utf8&serverTimezone=UTC";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch( ClassNotFoundException e ) {
//			System.out.println( "드러이버 로딩 실패:" + e );
//		} 
//		
//		return conn;
//	}
	
//	public List<MessageVo> getList(int board_no) {
//		List<MessageVo> list = new ArrayList<MessageVo>();
//
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = getConnection();
//
//			// Statement 객체 생성
//			stmt = conn.createStatement();
//
//			// SQL문 실행
//			String sql = "select a.no, content, name, user_no " + 
//					"from message a, user b " + 
//					"where a.user_no = b.no " + 
//					"and board_no = "+board_no + 
//					" order by a.no desc";
//				
//			rs = stmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			while (rs.next()) {
//				int no = rs.getInt(1);
//				String content = rs.getString(2);
//				String name = rs.getString(3);
//				int user_no = rs.getInt(4);
//				
//				MessageVo vo = new MessageVo();
//				vo.setNo(no);
//				vo.setContent(content);
//				vo.setName(name);
//				vo.setUser_no(user_no);
//				
//				list.add(vo);
//			}
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//			// 자원 정리
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return list;
//	}

//	public void delete(int no) {
//		
//		Connection conn = null;
//		Statement stmt = null;
//
//		try {
//			conn = getConnection();
//
//			// Statement 객체 생성
//			stmt = conn.createStatement();
//
//			String sql = 
//				" delete" + 
//				"   from message" + 
//				"  where no="+no;
//			
//			stmt.executeUpdate( sql );
//			
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//			// 자원 정리
//			try {
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public void delete(int no) {
		sqlSession.delete("message.delete",no);
	}
	
	public List<MessageVo> getList(int no) {
		List<MessageVo> list = sqlSession.selectList("message.getlist",no);
		return list;
	}
	
	public void Insert(MessageVo vo) {
		sqlSession.insert("message.insert",vo);
	}
}
