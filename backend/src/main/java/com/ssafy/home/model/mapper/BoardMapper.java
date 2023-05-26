package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.dto.Board;

@Mapper
public interface BoardMapper {
	
	List<Board> listArticle() throws SQLException;
	int writeArticle(Board board) throws SQLException;
	int modifyArticle(Board board) throws SQLException;
	int deleteArticle(int articleno) throws SQLException;
	Board detailArticle(int articleno) throws SQLException;
	void updateHit(int articleno) throws SQLException;
}
