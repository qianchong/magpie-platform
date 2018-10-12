/*
MySQL Backup
Source Server Version: 5.5.6
Source Database: platform-user
Date: 2018/10/12 15:58:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `clientdetails`
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT '	该字段的值是将access_token的值通过MD5加密后存储的',
  `token` blob COMMENT '存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.',
  `authentication_id` varchar(128) NOT NULL COMMENT '该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 具体实现请参考DefaultAuthenticationKeyGenerator.java类.',
  `user_name` varchar(256) DEFAULT NULL COMMENT '登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id',
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.',
  `refresh_token` varchar(256) DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在项目中,主要操作oauth_access_token表的对象是JdbcTokenStore.java. 更多的细节请参考该类.';

-- ----------------------------
--  Table structure for `oauth_approvals`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL COMMENT '用于唯一标识每一个客户端',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙;',
  `scope` varchar(256) DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括read,write,trust;',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '客户端的重定向URI',
  `authorities` varchar(256) DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". ',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '	设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时). ',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). ',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '设置用户是否自动Approval操作, 默认值为 ''false'', 可选值包括 ''true'',''false'', ''read'',''write''. \r\n该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为''true''或支持的scope值,则会跳过用户Approve的页面, 直接授权. \r\n该字段与 trusted 有类似的功能, 是 spring-security-oauth2 的 2.0 版本后添加的新属性.',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 在项目中,主要操作oauth_client_details表的类是JdbcClientDetailsService.java, 更多的细节请参考该类. \r\n也可以根据实际的需要,去扩展或修改该类的实现.';

-- ----------------------------
--  Table structure for `oauth_client_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT '从服务器端获取到的access_token的值.',
  `token` blob COMMENT '这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.',
  `authentication_id` varchar(128) NOT NULL COMMENT '该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. \r\n具体实现请参考DefaultClientKeyGenerator.java类.',
  `user_name` varchar(256) DEFAULT NULL COMMENT '登录时的用户名',
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 该表用于在客户端系统中存储从服务端获取的token数据, 在spring-oauth-server项目中未使用到. \r\n对oauth_client_token表的主要操作在JdbcClientTokenServices.java类中, 更多的细节请参考该类.';

-- ----------------------------
--  Table structure for `oauth_code`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL COMMENT '存储服务端系统生成的code的值(未加密).',
  `authentication` blob COMMENT '存储将AuthorizationRequestHolder.java对象序列化后的二进制数据.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在项目中,主要操作oauth_code表的对象是JdbcAuthorizationCodeServices.java. 更多的细节请参考该类. \r\n只有当grant_type为"authorization_code"时,该表中才会有数据产生; 其他的grant_type没有使用该表.';

-- ----------------------------
--  Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  `token` blob COMMENT '存储将OAuth2RefreshToken.java对象序列化后的二进制数据.',
  `authentication` blob COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 在项目中,主要操作oauth_refresh_token表的对象是JdbcTokenStore.java. (与操作oauth_access_token表的对象一样);更多的细节请参考该类. \r\n如果客户端的grant_type不支持refresh_token,则不会使用该表.';

-- ----------------------------
--  Table structure for `sys_permission_entity`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_entity`;
CREATE TABLE `sys_permission_entity` (
  `oid` varchar(64) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `icon` varchar(128) DEFAULT 'fa fa-bars',
  `order_num` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','resource') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKer7lssqpsgobqmglngx7labpm` (`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_entity`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_entity`;
CREATE TABLE `sys_role_entity` (
  `oid` varchar(64) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  UNIQUE KEY `UK_4mpw108jpuabhdm9457jupj1f` (`role_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permission_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  KEY `FKlo4iwb06rh2ajcpjdrwg8yuba` (`permission_id`),
  KEY `FK1btcc55mjkfymh6liyevrblsb` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_entity`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_entity`;
CREATE TABLE `sys_user_entity` (
  `oid` varchar(64) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `login_name` varchar(100) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  UNIQUE KEY `UK_b4r9n2tfkl3d1k0ts39xr748d` (`login_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  KEY `FK1wcd4vqn19blfmdhp3qndb6yr` (`role_id`),
  KEY `FK4ppl32phrgljgcoe849npdc30` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `oauth_client_details` VALUES ('TestClient',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sys_role_entity` VALUES ('1',NULL,NULL,'ROLE_USER',NULL,'ROLE_USER'), ('2',NULL,NULL,'ROLE_ADMIN',NULL,'ROLE_ADMIN');
INSERT INTO `sys_user_entity` VALUES ('1','2018-09-11 15:05:03','2018-09-11 15:05:08','ryan','123456','1','ryan'), ('2','2018-09-11 15:05:42','2018-09-11 15:05:46','admin','123456','1','admin'), ('297e70bc65e5e0890165e5e0a7950000',NULL,NULL,NULL,NULL,'0',NULL), ('297e70bc65e5e1cb0165e5e1e8a20000',NULL,NULL,NULL,NULL,'0',NULL), ('297e70bc65e5e3360165e5e35b5d0000',NULL,NULL,NULL,NULL,'0',NULL), ('297e70bc65e5e3c30165e60a426b0000',NULL,NULL,NULL,NULL,'0',NULL), ('297e70bc65e60aba0165e60b645a0000',NULL,NULL,NULL,NULL,'0',NULL);
INSERT INTO `sys_user_role` VALUES ('1','1'), ('2','1'), ('2','2');
