package member.model.service;

import java.sql.*;

import common.JDBCTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	// 연결생성
	// DAO 호출
	// 커밋 / 롤백
	
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
//		jdbcTemplate = new JDBCTemplate();
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO();
	}
	
	public int insertMember(Member member) {
		// 연결생성
//		JDBCTemplate jdbcTemplate = new JDBCTemplate();
		Connection conn = jdbcTemplate.createConnection();
		
		// DAO 호출
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(conn, member);
		
		// 커밋 / 롤백
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int updateMember(Member member) {
		// 연결생성
//		JDBCTemplate jdbcTemplate = new JDBCTemplate();
		Connection conn = jdbcTemplate.createConnection();
		
		// DAO 호출
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateMember(conn, member);
		
		// 커밋 / 롤백
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		// 연결 생성
		Connection conn = jdbcTemplate.createConnection();
		
		// DAO 호출 (연결 넘겨주기)
		int result = mDao.deleteMember(conn, memberId);
		// 커밋 / 롤백
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	// 연결생성
	// DAO 호출
	// 커밋 / 롤백
	
	public Member selectCheckLogin(Member member) {
		// 연결 생성
		Connection conn = jdbcTemplate.createConnection();
		
		// DAO 호출 (연결도 넘겨줘야함)
		Member mOne = mDao.selectCheckLogin(conn, member);
		jdbcTemplate.close(conn);
		return mOne;
	}
	
//	public int selectCheckLogin(Member member) {
//		// 연결 생성
//		Connection conn = jdbcTemplate.createConnection();
//		
//		// DAO 호출 (연결도 넘겨줘야함)
//		int result = mDao.selectCheckLogin(conn, member);
//		jdbcTemplate.close(conn);
//		return result;
//	}

	public Member selectOneById(String memberId) {
		// 연결 생성
		Connection conn = jdbcTemplate.createConnection();
		
		// DAO 호출 (연결도 넘겨줘야함)
		Member member = mDao.selectOneById(conn, memberId);
		jdbcTemplate.close(conn);
		return member;
	}
}
