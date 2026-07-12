package com.cryptomind.dto;

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
	private int[] role_id_arr;
	private int vip_level;
	private Long avator_img_id;

	private String sig;
}
