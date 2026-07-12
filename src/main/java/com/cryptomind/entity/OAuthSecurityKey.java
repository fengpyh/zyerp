package com.cryptomind.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
CREATE TABLE `oauth_security_key` (
  `id` int NOT NULL auto_increment,
  `api_key` varchar(100) NOT NULL DEFAULT '',
  `salt` varchar(100) NOT NULL DEFAULT '',
  `user_id` bigint NOT NULL,
  `key1` text NOT NULL,
  `key2` text NOT NULL,
  `key3` varchar(1000) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  key(user_id, api_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3
 */

@Entity
@Table(name = "oauth_security_key")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class OAuthSecurityKey implements java.io.Serializable {

    private static final long serialVersionUID = -3832956482358288478L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    //序号
    @Column(name = "user_id")
    private Long userId;


    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "salt")
    private String salt;

    @Column(name = "key1",columnDefinition = "text")
    private String key1;


    @Column(name = "key2",columnDefinition = "text")
    private String key2;

    @Column(name = "key3",columnDefinition = "text")
    private String key3;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp update_time;


}
