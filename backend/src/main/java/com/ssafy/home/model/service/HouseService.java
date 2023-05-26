package com.ssafy.home.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.home.dto.House;
import com.ssafy.home.dto.HouseDetail;
import com.ssafy.home.dto.SidoGugunCode;

public interface HouseService {

	List<SidoGugunCode> getSido() throws Exception;
	List<SidoGugunCode> getGugunInSido(String sido) throws Exception;
	List<SidoGugunCode> getDongInGugun(String gugun) throws Exception;
	List<House> getAptList(Map<String, String> map) throws Exception;
	HouseDetail detailHouse(String aptCode) throws Exception;
}
