package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.dto.Board;

public interface BoardService {

	List<Board> listArticle() throws Exception;
	boolean writeArticle(Board board) throws Exception;
	boolean modifyArticle(Board board) throws Exception;
	boolean deleteArticle(int articleno) throws SQLException;
	Board detailArticle(int articleno) throws SQLException;
	void updateHit(int articleno) throws SQLException;
}
