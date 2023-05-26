package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.dto.Member;

public interface MemberService {
	
	Member login(Member member) throws SQLException;
	List<Member> listMember() throws SQLException;
	boolean registMember(Member member) throws SQLException;
	boolean modifyMember(Member member) throws SQLException;
	boolean deleteMember(String userid) throws SQLException;
	Member detailMember(String userid) throws SQLException;
}
