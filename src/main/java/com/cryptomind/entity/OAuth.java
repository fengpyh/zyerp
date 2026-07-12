package com.cryptomind.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
 oauth | CREATE TABLE `oauth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `memo` varchar(100) NOT NULL,
  `api_memo` varchar(100) DEFAULT NULL,
  `api_key` varchar(40) NOT NULL,
  `api_key_hash` varchar(100) DEFAULT NULL,
  `api_secret` varchar(100) NOT NULL,
  `bearer_token` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `withdraw_enabled` tinyint(1) DEFAULT '0',
  `white_ip_enabled` tinyint(1) DEFAULT '0',
  `white_ip_list` varchar(200) DEFAULT NULL,
  `key_expire_day` int NOT NULL DEFAULT '0',
  `key_expire_time_at` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `status_remark` varchar(30) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`user_id`,`memo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3
 */

@Entity
@Table(name = "oauth")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class OAuth implements java.io.Serializable {

    private static final long serialVersionUID = -3832956482358288478L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    //序号
    @Column(name = "user_id")
    private Integer userId;

    //交易对id
    @Column(name = "memo")
    private String memo;

    @Column(name = "api_memo")
    private String apiMemo;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_key_hash")
    private String apiKeyHash;


    @Column(name = "api_secret")
    private String apiSecret;


    @Column(name = "bearer_token")
    private String bearerToken;


    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "last_update_time")
    private Timestamp lastUpdateTime;

    @Column(name = "withdraw_enabled") //, length = 1, columnDefinition = "BIT")
    private Integer withdrawEnabled;

    
    @Column(name = "white_ip_enabled") //, length = 1, columnDefinition = "BIT")
    private Integer whiteIpEnabled;


    @Column(name = "white_ip_list")
    private String whiteIpList;


    @Column(name = "key_expire_day")
    private Integer keyExpireDay;

    @Column(name = "key_expire_time_at")
    private LocalDateTime keyExpireTimeAt;


    @Column(name = "update_time")
    private Timestamp updateTime;


    @Column(name = "status")
    private Integer status;

    @Column(name = "status_remark")
    private String statusRemark;


    @Column(name = "version")
    private Integer version;

}
