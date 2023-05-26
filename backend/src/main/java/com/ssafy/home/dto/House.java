package com.ssafy.home.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
	
	@ApiModelProperty(value = "No")
    private String no;
	@ApiModelProperty(value = "아파트번호")
	private String aptCode;
	@ApiModelProperty(value = "아파트이름")
	private String aptName;
	@ApiModelProperty(value = "매매년")
	private int dealYear;
	@ApiModelProperty(value = "매매월")
	private int dealMonth;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "매매가격")
	private String price;
}
