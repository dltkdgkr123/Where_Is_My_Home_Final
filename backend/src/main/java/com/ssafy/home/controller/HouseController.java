package com.ssafy.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.dto.House;
import com.ssafy.home.dto.HouseDetail;
import com.ssafy.home.dto.SidoGugunCode;
import com.ssafy.home.model.service.HouseService;

import io.swagger.annotations.Api;

@CrossOrigin("*")
@RestController
@RequestMapping("/house")
@Api("Map 컨트롤러  API V1")
public class HouseController {

	private final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	private HouseService houseService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCode>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCode>>(houseService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCode>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCode>>(houseService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunCode>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunCode>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping(value = "/{regCode}/{searchWord}/{deal_y}/{deal_m}")
	public ResponseEntity<List<House>> aptList(@PathVariable("regCode") String regCode,
			@PathVariable("searchWord") String searchWord, @PathVariable("deal_y") String dealY,
			@PathVariable("deal_m") String dealM) throws Exception {

		Map<String, String> map = new HashMap<>();
		map.put("regCode", regCode);
		map.put("searchWord", searchWord);
		map.put("dealY", dealY);
		map.put("dealM", dealM);

		return new ResponseEntity<List<House>>(houseService.getAptList(map), HttpStatus.OK);
	}

	@GetMapping("/{aptCode}")
	public ResponseEntity<HouseDetail> detailHouse(@PathVariable("aptCode") String aptCode) throws Exception {

		return new ResponseEntity<HouseDetail>(houseService.detailHouse(aptCode), HttpStatus.OK);
	}
}
