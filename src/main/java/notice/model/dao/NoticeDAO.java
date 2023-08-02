package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import notice.model.vo.Notice;

public class NoticeDAO {

	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE_TBL VALUES(SEQ_NOTICENO.NEXTVAL, ?, ?, 'admin', DEFAULT, DEFAULT, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE_TBL SET NOTICE_SUBJECT = ?, NOTICE_CONTENT = ? WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteNoticeByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Notice> selectNoticeList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> nList = new ArrayList<Notice>();
//		String query = "SELECT * FROM NOTICE_TBL ORDER BY NOTICE_DATE DESC";
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY NOTICE_NO DESC) ROW_NUM, NOTICE_TBL.* FROM NOTICE_TBL) WHERE ROW_NUM BETWEEN ? AND ?";
		int recordCountPerPage = 15;
		// currentPage		start
		// 		1			  1
		//		2			 11
		// 		3			 21
		// 		4			 31
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);		
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = rsetToNotice(rset);
				nList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nList;
	}
	
	public String generagePageNavi(int currentPage) {
		// 전체 게시물의 갯수 : 37
		// 한 페이지당 보여줄 게시물 수 : 10
		// 범위의 갯수(네비게이터의 수) : ? -> 4

		// 전체 게시물의 갯수 : 55
		// 한 페이지당 보여줄 게시물 수 : 10
		// 범위의 갯수(네비게이터의 수) : ? -> 6
		
		int totalCount = 206; // 전체 게시물의 수
		int recordCountPerPage = 15; // 한 페이지당 수
		int naviTotalCount = 0; // 네비게이터의 수
		
		if(totalCount % recordCountPerPage > 0)
			naviTotalCount = totalCount / recordCountPerPage + 1;
		else
			naviTotalCount = totalCount / recordCountPerPage;
		
		int naviCountPerPage = 5; // 1, 2, 3, 4, 5 : 5개의 페이지를 보여줌
		
		// currentPage		startNavi		endNavi
		// 1,2,3,4,5			1			   5
		// 6,7,8,9,10			6			   10
		// 11,12,13,14,15		11			   15
		// 16,17,18,19,20		16			   20
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// endNavi값이 총 범위의 갯수보다 커지는 것을 막아주는 코드
		if(endNavi > naviTotalCount) { 
			endNavi = naviTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		
		if(endNavi == naviTotalCount) {
			needPrev = false;
		}
//		String result = "";
		StringBuilder result = new StringBuilder(); // String보다 메모리를 아끼면서 누적합 실행 가능
		if(needPrev) {
			result.append("<a href='/notice/list.do?currentPage=" + (startNavi - 1) + "'>[이전]</a> ");
		}
		for(int i = startNavi; i <= endNavi; i++) {
//			result += "<a href=\"#\">1</a>";
			result.append("<a href=\"/notice/list.do?currentPage=" + i + "\">" + i + "</a>&nbsp;&nbsp;"); // \" : "를 문자열로 인식하기 위한 escape가 포함 (그냥 '로 써도 된다)
		}
		if(needNext) {
			result.append("<a href='/notice/list.do?currentPage=" + (endNavi + 1) + "'>[다음]</a>");
		}
		return result.toString();
	}
	
	public Notice selectOneByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null; // null 체크를 하기 위해 new Notice가 아니라 null로 초기화해준다.
		String query = "SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = rsetToNotice(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return notice;
	}

	private Notice rsetToNotice(ResultSet rset) throws SQLException {
		Notice notice = new Notice();
		notice.setNoticeNo(rset.getInt("NOTICE_NO"));
		notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
		notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
		notice.setNoticeWriter(rset.getString("NOTICE_WRITER"));
		notice.setNoticeDate(rset.getTimestamp("NOTICE_DATE"));
		notice.setUpdateDate(rset.getTimestamp("UPDATE_DATE"));
		notice.setViewCount(rset.getInt("VIEW_COUNT"));
		
		return notice;
	}

}
