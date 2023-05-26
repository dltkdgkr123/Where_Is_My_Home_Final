package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.Board;
import com.ssafy.home.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<Board> listArticle() throws Exception {
		return boardMapper.listArticle();
	}

	@Override
	public boolean writeArticle(Board board) throws Exception {
		// 프론트에서 처리
//		if(board.getSubject() == null || board.getContent() == null) {
//			throw new Exception();
//		}
		
		// 반영된 행 개수가 1개 => 글쓰기 성공 => true 반환
		// 반영된 행 개수가 0개 => 글쓰기 실패 => false 반환
		return boardMapper.writeArticle(board) == 1;
	}

	@Override
	public boolean modifyArticle(Board board) throws Exception {
		return boardMapper.modifyArticle(board) == 1;
	}

	@Override
	public boolean deleteArticle(int articleno) throws SQLException {
		return boardMapper.deleteArticle(articleno) == 1;
	}

	@Override
	public Board detailArticle(int articleno) throws SQLException {
		return boardMapper.detailArticle(articleno);
	}

	@Override
	public void updateHit(int articleno) throws SQLException {
		boardMapper.updateHit(articleno);
	}

}