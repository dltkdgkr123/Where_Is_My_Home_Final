package com.ssafy.home.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDetail {

	@ApiModelProperty(value = "No")
    private String no;
	@ApiModelProperty(value = "면적")
	private String area;
	@ApiModelProperty(value = "층수")
	private String floor;
	@ApiModelProperty(value = "설립년도")
	private String buildYear;
	@ApiModelProperty(value = "도로명")
	private String roadName;
	@ApiModelProperty(value = "본도로명번")
	private String roadNameBonbun;
}
