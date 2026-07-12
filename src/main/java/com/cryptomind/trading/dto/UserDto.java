package com.cryptomind.trading.dto;

import lombok.*;

import java.sql.Timestamp;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private Long id;
	private String email;
	private String phone;
	private String nick_name;
	private Timestamp create_time;
	private Integer roleId;
	private String current_market;
}
