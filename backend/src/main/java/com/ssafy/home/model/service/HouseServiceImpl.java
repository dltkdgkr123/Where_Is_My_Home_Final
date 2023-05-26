package com.ssafy.home.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.House;
import com.ssafy.home.dto.HouseDetail;
import com.ssafy.home.dto.SidoGugunCode;
import com.ssafy.home.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseMapper houseMapper;

	@Override
	public List<SidoGugunCode> getSido() throws Exception {
		return houseMapper.getSido();
	}

	@Override
	public List<SidoGugunCode> getGugunInSido(String sido) throws Exception {
		return houseMapper.getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunCode> getDongInGugun(String gugun) throws Exception {
		return houseMapper.getDongInGugun(gugun);
	}

	@Override
	public List<House> getAptList(Map<String, String> map) throws Exception {
		return houseMapper.getAptList(map);
	}

	@Override
	public HouseDetail detailHouse(String aptCode) throws Exception {
		return houseMapper.getDetailHouse(aptCode);
	}
}
