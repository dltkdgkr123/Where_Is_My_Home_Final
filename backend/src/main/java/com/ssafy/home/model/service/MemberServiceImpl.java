package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.Member;
import com.ssafy.home.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public Member login(Member member) throws SQLException {
		return memberMapper.login(member);
	}
	
	@Override
	public List<Member> listMember() throws SQLException {
		return memberMapper.listMember();
	}

	@Override
	public boolean registMember(Member member) throws SQLException {
		return memberMapper.registMember(member) == 1;
	}

	@Override
	public boolean modifyMember(Member member) throws SQLException {
		return memberMapper.modifyMember(member) == 1;
	}

	@Override
	public boolean deleteMember(String userid) throws SQLException {
		return memberMapper.deleteMember(userid) == 1;
	}

	@Override
	public Member detailMember(String userid) throws SQLException {
		return memberMapper.detailMember(userid);
	}

}
