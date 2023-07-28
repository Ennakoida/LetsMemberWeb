package member.model.dao;

import java.sql.*;

import member.model.vo.Member;

public class MemberDAO {

	public int insertMember(Connection conn, Member member) {
		String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query); // try catch
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(4, member.getMemberAge());
			pstmt.setString(5, member.getMemberGender());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());
			pstmt.setString(9, member.getMemberHobby());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
//		String query = "UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ?, UPDATE_DATE = SYSDATE WHERE MEMBER_ID = ?";
		String query = "UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_GENDER = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ?, UPDATE_DATE = SYSDATE WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
//			pstmt = conn.prepareStatement(query); // try catch
//			pstmt.setString(1, member.getMemberPw());
//			pstmt.setString(2, member.getMemberEmail());
//			pstmt.setString(3, member.getMemberPhone());
//			pstmt.setString(4, member.getMemberAddress());
//			pstmt.setString(5, member.getMemberHobby());
//			pstmt.setString(6, member.getMemberId());
//			result = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query); // try catch
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberGender());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberAddress());
			pstmt.setString(6, member.getMemberHobby());
			pstmt.setString(7, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

	public Member selectCheckLogin(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		Member mOne = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId()); // member 객체로 넘겨받았기 때문에 get
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery(); // 누락 주의, 결과값 받기 주의
			if(rset.next()) {
				mOne = rsetToMember(rset);
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
		
		return mOne;
	}
	
//	public int selectCheckLogin(Connection conn, Member member) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String query = "SELECT COUNT(*) FROM MEMBER_TBL WHERE MEMBER_ID = ?";
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, member.getMemberId());
//			result = pstmt.executeUpdate(); // 누락 주의, 결과값 받기 주의
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(result);
//		return result;
//	}
	
	public Member selectOneById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		Member mOne = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery(); // 누락 주의, 결과값 받기 주의
			if(rset.next()) {
				mOne = rsetToMember(rset);
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
		
		return mOne;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString(1)); // add throws declaration -> throws SQLException
		member.setMemberPw(rset.getString(2));
		member.setMemberName(rset.getString(3));
		member.setMemberAge(rset.getInt("MEMBER_AGE"));
		member.setMemberGender(rset.getString("MEMBER_GENDER"));
		member.setMemberEmail(rset.getString(6));
		member.setMemberPhone(rset.getString("MEMBER_PHONE"));
		member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
		member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
		member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
		member.setUpdateDate(rset.getTimestamp(11));
		member.setMemberYn(rset.getString("MEMBER_YN"));
		return member;
	}

}
