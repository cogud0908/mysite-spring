package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
//	public int insert(BoardVo vo) {
//		int count = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = 
//				"insert into board " + 
//				"values(null, ? , ? ,now(),0,ifnull((select max(g_no)+1 from board a),1),1,0,?)";
//			
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setInt(3, vo.getUser_no());
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

//	public BoardVo getBoard(int buffer_no) {
//				
//		BoardVo vo = null;
//		
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			// Statement 객체 생성
//			stmt = conn.createStatement();
//
//			// SQL문 실행
//			String sql =
//				"   select no, title, contents, user_no " + 
//				"     from board" + 
//				" where no = "+buffer_no;
//			
//			rs = stmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			if (rs.next()) {
//				int no = rs.getInt(1);
//				String title = rs.getString(2);
//				String contents = rs.getString(3);
//				int user_no = rs.getInt(4);
//
//				vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setContents(contents);
//				vo.setUser_no(user_no);
//			}
//			
//			sql = 
//					" update board" + 
//					"   set hit = hit+1" + 
//					"  where no = "+buffer_no;
//			
//				pstmt = conn.prepareStatement(sql);
//	
//				pstmt.executeUpdate();
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
//		return vo;
//	}

//	public int delete(int no) {
//		int count = 0;
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
//				"   from board" + 
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
//
//		return count;		
//	}

//	public boolean search(int buffer_no, int buffer_user_no) {
//		
//		int user_no = 0;
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
//				"select user_no from board where no = "+buffer_no;
//			rs = stmt.executeQuery( sql );
//
//			if(rs.next())
//			{
//				user_no = rs.getInt(1);
//			}
//			
//			if(user_no == buffer_user_no)
//			{
//				return true;
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
//		return false;
//	}

//	public BoardVo getReply(int buffer_no) {
//		
//		BoardVo vo = null;
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
//				"   select no, title" + 
//				"     from board" + 
//				" where no = "+buffer_no;
//			
//			rs = stmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			if (rs.next()) {
//				int no = rs.getInt(1);
//				String title = rs.getString(2);
//				
//
//				vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
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
//		return vo;
//	}
	
//	public int ReplyInsert(BoardVo vo, int buffer_no) {
//		
//		int count = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		try {	
//			
//			conn = getConnection();
//
//			stmt = conn.createStatement();
//
//			// SQL문 실행
//			String sql =
//				"   select g_no, o_no, depth" + 
//				"     from board" + 
//				" where no = "+ buffer_no;
//			
//			rs = stmt.executeQuery( sql );
//			
//			if(rs.next())
//			{
//				int g_no = rs.getInt(1);
//				int o_no = rs.getInt(2);
//				int depth = rs.getInt(3);
//				
//				vo.setG_no(g_no);
//				vo.setO_no(o_no);
//				vo.setDepth(depth);
//			}
//			
//			sql = 
//					"update board set o_no = o_no+1 where g_no = ? "
//					+ "and o_no >= ?";
//				
//				pstmt = conn.prepareStatement(sql);
//				
//				pstmt.setInt(1, vo.getG_no());
//				pstmt.setInt(2, vo.getO_no()+1);
//			
//				count = pstmt.executeUpdate();
//				
//			sql = 
//				"insert into board " + 
//				"values(null, ? , ? ,now(),0,?,?,?,?)";
//			
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setInt(3, vo.getG_no());
//			pstmt.setInt(4, vo.getO_no()+1);
//			pstmt.setInt(5, vo.getDepth()+1);
//			pstmt.setInt(6, vo.getUser_no());
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
//	
//	}

//	public void update(BoardVo vo) {
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = 
//				" update" + 
//				" board" + 
//				" set title = ?, contents = ? where no = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setInt(3, vo.getNo());
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

//	public List<BoardVo> getList(String search, int page) {
//		List<BoardVo> list = new ArrayList<BoardVo>();
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
//			String sql = "select a.no, title, contents, hit, g_no, o_no, depth, user_no, " + 
//					"date_format(write_date, '%Y-%m-%d %h:%i:%s'), b.name " + 
//					"from board a, user b " + 
//					"where a.user_no = b.no " + 
//					"and title like '%"+search+"%'" +
//					"group by a.no "+
//					"order by g_no desc , o_no asc " +
//					"limit "+10+" offset "+(page-1)*10;
//
//			rs = stmt.executeQuery( sql );
//
//			// 결과 가져오기(사용하기)
//			while (rs.next()) {
//				int no = rs.getInt(1);
//				String title = rs.getString(2);
//				String contents = rs.getString(3);
//				int hit = rs.getInt(4);
//				int g_no = rs.getInt(5);
//				int o_no = rs.getInt(6);
//				int depth = rs.getInt(7);
//				int user_no = rs.getInt(8);
//				String write_date = rs.getString(9);
//				String name = rs.getString(10);
//
//				BoardVo vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setContents(contents);
//				vo.setHit(hit);
//				vo.setG_no(g_no);
//				vo.setO_no(o_no);
//				vo.setDepth(depth);
//				vo.setUser_no(user_no);
//				vo.setWrite_date(write_date);
//				vo.setName(name);
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

//	public int getCount(String search) {
//		int count = 0;
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
//				"select count(*) from board where title like '%"+search+"%' or contents like '%"+search+"%'";
//			rs = stmt.executeQuery( sql );
//
//			if(rs.next())
//			count = rs.getInt(1);
//					
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
//		return count;
//	}
	
	public List<BoardVo> getList(Map<String, Object> map) {
		List<BoardVo> list = sqlSession.selectList("board.getlist",map);
		return list;
	}
	
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public void delete(int board_no) {
		sqlSession.delete("board.delete", board_no);
	}
	
	public BoardVo getBoard(int no) {
		BoardVo vo = sqlSession.selectOne("board.view", no);
		sqlSession.update("board.hit", no);
		return vo;
	}
	
	public void update(BoardVo vo) {
		sqlSession.update("board.modify",vo);
	}
	
	public BoardVo getReply(int no) {
		BoardVo vo = sqlSession.selectOne("board.getReply",no);
		return vo;
	}
	
	public void ReplyInsert(BoardVo vo, int no) {
		BoardVo bufferVo = sqlSession.selectOne("board.replyInsertSelect", no);
		
		sqlSession.update("board.replyInsertUpdate", bufferVo);
		vo.setO_no(bufferVo.getO_no()+1);
		vo.setG_no(bufferVo.getG_no());
		vo.setDepth(bufferVo.getDepth()+1);
		
		sqlSession.insert("board.replyInsert",vo);
	}
	
	public int getCount(String search) {
		
		int count = sqlSession.selectOne("board.getCount",search);
		return count;
	}
}
