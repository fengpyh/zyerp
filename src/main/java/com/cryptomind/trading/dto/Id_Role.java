package com.cryptomind.trading.dto;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Id_Role {


	private Long userId;
	private Integer roleId;
	private String email;
	
	public String getEmail() {
		if(email!=null) {
			StringBuilder sb = new StringBuilder(email!=null?email.length():10);
			int j  =email.indexOf('@');
			int k = j/2;
			for(int i=0;i<email.length();i++) {
				char c = email.charAt(i);
				if(i>=k && i<j) {
					sb.append('*');
				}else {
					sb.append(c);
				}
			}
			return sb.toString();
		}else {
			return null;
		}
	}
}
