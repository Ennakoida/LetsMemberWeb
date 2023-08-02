package notice.model.service;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {

	private NoticeDAO nDao;
	private JDBCTemplate jdbcTemplate;
	
	public NoticeService() {
		nDao = new NoticeDAO();
//		jdbcTemplate = new JDBCTemplate(); // 싱글톤이라 아무나 객체 만들 수 X
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	
	public int insertNotice(Notice notice) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.insertNotice(conn, notice);

		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int updateNotice(Notice notice) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.updateNotice(conn, notice);

		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int deleteNoticeByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnection();
		int result = nDao.deleteNoticeByNo(conn, noticeNo);

		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	// 공지사항 전체 목록 조회
	public PageData selectNoticeList(int currentPage) {
		Connection conn = jdbcTemplate.createConnection();
		List<Notice> nList = nDao.selectNoticeList(conn, currentPage);
		String pageNavi = nDao.generagePageNavi(currentPage);
		// 1. Map 이용
		// 2. VO클래스 이용
		PageData pd = new PageData(nList, pageNavi);
		jdbcTemplate.close(conn);
		return pd;
	}

	public Notice selectOneByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnection();
		Notice notice = nDao.selectOneByNo(conn, noticeNo);
		jdbcTemplate.close(conn);
		return notice;
	}

}
