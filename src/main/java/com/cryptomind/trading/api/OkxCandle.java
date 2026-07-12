package com.cryptomind.trading.api;

import java.math.BigDecimal;
import java.util.List;

import com.cryptomind.dto.CancelDetail;

import lombok.Data;

@Data
public class OkxCandle {

	private String code;
	private String msg;
	List<String[] > data;
}
