package com.ssafy.home.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.dto.House;
import com.ssafy.home.dto.HouseDetail;
import com.ssafy.home.dto.SidoGugunCode;

@Mapper
public interface HouseMapper {

	List<SidoGugunCode> getSido() throws SQLException;
	List<SidoGugunCode> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunCode> getDongInGugun(String gugun) throws SQLException;
	List<House> getAptList(Map<String, String> map) throws SQLException;
	HouseDetail getDetailHouse(String aptCode) throws SQLException;
}
