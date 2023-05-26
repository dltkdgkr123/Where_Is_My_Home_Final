package com.ssafy.home.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafy.home.dto.Member;
import com.ssafy.home.model.service.EmailService;
import com.ssafy.home.model.service.JwtService;
import com.ssafy.home.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemberService memberService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) throws SQLException {

		Member userInfo = memberService.login(member);

		if (userInfo != null) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("userInfo", userInfo);
			resultMap.put("access-token", jwtService.createToken("userid", userInfo.getUserid(), "access-token"));
			resultMap.put("refresh-token", jwtService.createToken("userid", userInfo.getUserid(), "refresh-token"));
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Member>> listMember() throws SQLException {
		return new ResponseEntity<List<Member>>(memberService.listMember(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> registMember(@RequestBody Member member) throws SQLException {
		if (memberService.registMember(member)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	@PutMapping
	public ResponseEntity<String> modifyMember(@RequestBody Member member) throws SQLException {
		if (memberService.modifyMember(member)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteMember(@PathVariable("userid") String userid) throws SQLException {
		if (memberService.deleteMember(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{userid}")
	public ResponseEntity<Member> detailMember(@PathVariable("userid") String userid) throws SQLException {

		return new ResponseEntity<Member>(memberService.detailMember(userid), HttpStatus.OK);
	}

	@GetMapping("/email/{userid}")
	public ResponseEntity<String> sendEmail(@PathVariable("userid") String userid) throws SQLException {

		Member member = memberService.detailMember(userid);
		
		if (member != null) {
			try {
				emailService.sendMail(member);
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}

		}
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}
}
