package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.dto.Member;

@Mapper
public interface MemberMapper {
	
	Member login(Member member) throws SQLException;
	List<Member> listMember() throws SQLException;
	int registMember(Member member) throws SQLException;
	int modifyMember(Member member) throws SQLException;
	int deleteMember(String userid) throws SQLException;
	Member detailMember(String userid) throws SQLException;
}
