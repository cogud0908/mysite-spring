package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.exception.UserDaoException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
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
		
//	public UserVo login(UserVo userVo) {
//		UserVo result = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = 
//				" select no, name, email" + 
//				"   from user " + 
//				" where email = ? and password = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, userVo.getEmail());
//			pstmt.setString(2, userVo.getPassword());
//
//			rs = pstmt.executeQuery();
//			
//			if(rs.next())
//			{
//				int no = rs.getInt(1);
//				String name = rs.getString(2);
//				String email = rs.getString(3);
//				
//				result = new UserVo();
//				result.setNo(no);
//				result.setName(name);
//				result.setEmail(email);
//			}
//			
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
//		return result;
//	}
		
//	public int insert(UserVo vo) throws UserDaoException{
//		int count = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = 
//				" insert" + 
//				"   into user" + 
//				" values ( null, ?, ?, ?, ?, now() )";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getEmail());
//			pstmt.setString(3, vo.getPassword());
//			pstmt.setString(4, vo.getGender());
//
//			count = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new UserDaoException("회원정보 저장 실패");
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
//				throw new UserDaoException("회원정보 저장 실패");
//			}
//		}
//
//		return count;
//	}

//	public List<UserVo> getList() {
//		List<UserVo> list = new ArrayList<UserVo>();
//
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//
//			// Statement 객체 생성
//			stmt = conn.createStatement();
//
//			// SQL문 실행
//			String sql =
//				"   select no," + 
//				"          name," + 
//				"	       email, password, gender" + 
//				"     	   date_format(reg_date, '%Y-%m-%d %h:%i:%s')" + 
//				"     from user" + 
//				" order by reg_date desc";
//			rs = stmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			while (rs.next()) {
//				int no = rs.getInt(1);
//				String name = rs.getString(2);
//				String email = rs.getString(3);
//				String password = rs.getString(4);
//				String gender = rs.getString(5);
//				String join_date = rs.getString(6);
//
//				UserVo vo = new UserVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setEmail(email);
//				vo.setPassword(password);
//				vo.setGender(gender);
//				vo.setJoin_date(join_date);
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

//	public int update(UserVo vo) {
//		int count = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = 
//				" update" + 
//				" user" + 
//				" set name = ?, password = ?, gender = ? where no = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getGender());
//			pstmt.setInt(4, vo.getNo());
//
//			count = pstmt.executeUpdate();
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
//		return count;
//	}

//	public UserVo get(String email) {
//		
//		UserVo result = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = 
//				" select no, name" + 
//				"   from user " + 
//				" where email = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, email);
//
//			rs = pstmt.executeQuery();
//			
//			if(rs.next())
//			{
//				int no = rs.getInt(1);
//				String name = rs.getString(2);
//				
//				result = new UserVo();
//				result.setNo(no);
//				result.setName(name);
//			}
//			
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
//		return result;
//		
//	}
	
	public UserVo login(String email, String password) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		
		return userVo;
	}
	
	public UserVo get(int no) {
		return sqlSession.selectOne("user.getByEmail",no);
	}

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	public int update(UserVo vo) {
		return sqlSession.update("user.modify", vo);
	}

	public UserVo get(String email) {
		return sqlSession.selectOne("user.getEmail",email);
	}
}
