package com.cryptomind.trading.dto;

import lombok.Data;

@Data
public class WRequestDto {

	private String fenId;
	private Long uid;
	private Integer ftrademapping;
	private Integer coinType;
	private String accountType;
	private String fcount;
	private String fee;
	private String uuid;
	private Long entrustId;
}
