package biz;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MemberDao;
import vo.MemberMoneyVO;
import vo.MemberVO;

public class MemberService {
	MemberDao dao = new MemberDao();
	public ArrayList<MemberVO> memberList(){
			ArrayList<MemberVO> list = dao.memberList();
			return list;
	}
	public ArrayList<MemberMoneyVO> memberMoneyList(){
		ArrayList<MemberMoneyVO> list = dao.memberMoneyList();
		return list;
}
	public int getMaxCustNo() {
		int custno = dao.getMaxCustNo();
		return custno;
	}
	public int memberInsert(MemberVO member) throws SQLException{
		int n = dao.memberInsert(member);
		return n;
	}
	public MemberVO getMember(int custno) {
		MemberVO memberVO = dao.getMember(custno);
		return memberVO;
	}
	public int memberUpdate(MemberVO member) throws SQLException{
		int n = dao.memberUpdate(member);
		return n;
	}
}
