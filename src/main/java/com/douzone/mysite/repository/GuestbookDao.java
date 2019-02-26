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

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
//	public boolean delete( GuestbookVo vo ) {
//		int no = 10000;
//		boolean flag= false;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = 
//				" delete" + 
//				"   from guestbook" + 
//				"  where no=?" +
//				"    and password=?";
//			pstmt = conn.prepareStatement( sql );
//
//			pstmt.setLong( 1, vo.getNo() );
//			pstmt.setString( 2, vo.getPassword() );
//
//			pstmt.executeUpdate();
//
//			sql =
//				"   select count(*)" + 
//				"     from guestbook"+ 
//			    "   where no = "+vo.getNo(); 
//			
//			pstmt = conn.prepareStatement(sql);		
//			rs = pstmt.executeQuery( sql );
//			
//			if(rs.next()) {
//				no = rs.getInt(1);
//			}
//			
//			if(no == 0) flag = true;
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
//
//		return flag;		
//	}
	
//	public int insert(GuestbookVo vo) {
//		int no = 0;
//		Connection conn = null;
//		Statement stmt = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = 
//				" insert" + 
//				"   into guestbook" + 
//				" values ( null, ?, ?, ?, now() )";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getMessage());
//
//			pstmt.executeUpdate();
//			
//			/*
//			 * 방금 들어간 row에 Primary Key 받아오는 방법
//			 * "select last_insert_id()" <--sql
//			 */
//			stmt = conn.createStatement();
//			sql = "select max(last_insert_id(no)) from guestbook";	
//			rs = stmt.executeQuery( sql );
//			
//			if(rs.next()) {
//				no = rs.getInt(1);
//			}
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
//
//		return no;
//	}

//	public List<GuestbookVo> getList() {
//		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
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
//			String sql =
//				"   select no," + 
//				"          name," + 
//				"	       message," + 
//				"     	   date_format(reg_date, '%Y-%m-%d %h:%i:%s')" + 
//				"     from guestbook" + 
//				" order by reg_date desc";
//			rs = stmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			while (rs.next()) {
//				int no = rs.getInt(1);
//				String name = rs.getString(2);
//				String message = rs.getString(3);
//				String regDate = rs.getString(4);
//
//				GuestbookVo vo = new GuestbookVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setMessage( message );
//				vo.setRegDate( regDate );
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

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "com.mysql.jdbc.Driver" );
			
			//2. 연결하기
			String url="jdbc:mysql://localhost/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch( ClassNotFoundException e ) {
			System.out.println( "드러이버 로딩 실패:" + e );
		} 
		
		return conn;
	}

//	public List<GuestbookVo> getList(int page) {
//		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//				
//		try {
//			conn = getConnection();
//
//			// SQL문 준비
//			String sql =
//				"   select no," + 
//				"          name," + 
//				"	       message," + 
//				"     	   date_format(reg_date, '%Y-%m-%d %h:%i:%s')" + 
//				"     from guestbook" + 
//				" order by reg_date desc" +
//				" limit "+((page-1)*5)+", 5 ";
//
//			pstmt = conn.prepareStatement(sql);		
//			rs = pstmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			while (rs.next()) {
//				int no = rs.getInt(1);
//				String name = rs.getString(2);
//				String message = rs.getString(3);
//				String regDate = rs.getString(4);
//
//				GuestbookVo vo = new GuestbookVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setMessage( message );
//				vo.setRegDate( regDate );
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
//
//		return list;
//	}

//	public GuestbookVo get(int buffer_no) {
//		
//		GuestbookVo vo = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//				
//		try {
//			conn = getConnection();
//
//			// SQL문 준비
//			String sql =
//				"   select name," + 
//				"	       message," + 
//				"     	   date_format(reg_date, '%Y-%m-%d %h:%i:%s')" + 
//				"     from guestbook" +
//				" where no = "+buffer_no;
//
//			pstmt = conn.prepareStatement(sql);		
//			rs = pstmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			if (rs.next()) {
//				String name = rs.getString(1);
//				String message = rs.getString(2);
//				String regDate = rs.getString(3);
//
//				vo = new GuestbookVo();
//				vo.setName(name);
//				vo.setMessage( message );
//				vo.setRegDate( regDate );
//			}
//						
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//			// 자원 정리
//			try {
//				if (rs != null) {
//					rs.close();
//				}
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
//
//		return vo;
//	}	
	
	
	public int insert(GuestbookVo vo) { 
		sqlSession.insert("guestbook.insert",vo); 
		int no = vo.getNo();
		return no;
	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getlist");
		return list;
	}
	
	public void delete(GuestbookVo vo) {
		sqlSession.delete("guestbook.delete",vo);
		return;
	}

	public List<GuestbookVo> getList(int page) {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getlist-ajax",(page-1)*5);
		return list;
	}

	public GuestbookVo ajaxinsert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert",vo);
		GuestbookVo guestbookvo = sqlSession.selectOne("guestbook.getOne-ajax");
		return guestbookvo;
	}
	
}
