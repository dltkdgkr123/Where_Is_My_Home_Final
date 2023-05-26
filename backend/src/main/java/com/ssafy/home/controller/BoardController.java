package com.ssafy.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.dto.Board;
import com.ssafy.home.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@GetMapping
	public ResponseEntity<List<Board>> listArticle() throws Exception {
		log.debug("listArticle() 호출");
		return new ResponseEntity<List<Board>>(boardService.listArticle(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody Board board) throws Exception {
		log.debug("writeArticle() 호출");
		if (boardService.writeArticle(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody Board board) throws Exception {
		log.debug("modifyArticle() 호출");
		if (boardService.modifyArticle(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") String articleno) throws Exception {
		log.debug("deleteArticle() 호출");
		
		int no = Integer.parseInt(articleno);
		if (boardService.deleteArticle(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}	

	@GetMapping("/{articleno}")
	public ResponseEntity<Board> detailArticle(@PathVariable("articleno") String articleno) throws Exception {
		log.debug("detailArticle() 호출");
		
		int no = Integer.parseInt(articleno);
		boardService.updateHit(no);
		return new ResponseEntity<Board>(boardService.detailArticle(no), HttpStatus.OK);
	}
}