package com.cryptomind.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/*
CREATE TABLE user_base
  (
     id          BIGINT(20) NOT NULL auto_increment,
     email       VARCHAR(255) NOT NULL,
     phone       VARCHAR(30) NOT NULL,
     passwd_str  VARCHAR(1000) NOT NULL,
     salt        VARCHAR(100) NOT NULL,
     frole_id    INT(11) NOT NULL,
     srole_id    INT(11) NOT NULL,
     trole_id    INT(11) NOT NULL,
     nick_name   VARCHAR(255) NOT NULL,
     create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
  )
engine=innodb
auto_increment=3
DEFAULT charset=utf8 
 */

@Entity
@Table(name = "user_base")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class UserBase {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "passwd_str")
	private String passwd_str;
	
	@Column(name = "nick_name")
	private String nick_name;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "frole_id")
	private Integer frole_id;
	
	@Column(name = "srole_id")
	private Integer srole_id;
	
	@Column(name = "trole_id")
	private Integer trole_id;
	
	@Column(name = "create_time")
	private Timestamp create_time;
}
