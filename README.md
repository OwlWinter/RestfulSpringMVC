# RestfulSpringMVC
学习实现一个符合 rest 接口规范的 SpringMVC 项目。

数据库：school    
建表 SQL：
```
/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 13/05/2020 00:10:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for registery_info
-- ----------------------------
DROP TABLE IF EXISTS `registery_info`;
CREATE TABLE `registery_info`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `student_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `qq_num` bigint(20) NOT NULL COMMENT 'QQ号',
  `major_subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '学习方向',
  `graduated_school` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '毕业院校',
  `daily_report` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日报链接',
  `slogan` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标',
  `brother` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '审核师兄',
  `come_from` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '了解到学院的渠道',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_student_id`(`student_id`) USING BTREE,
  INDEX `idx_student_name`(`student_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
```

接口文档：    
新增学员：(post)       
请求地址：/disciple
|字段	|说明	|类型	|备注	|是否必须|
|---|---|---|---|---|
|student_name	|名字	|string|		|是|
|major_subject	|学习方向	|string|		|是|
|qq_num	|QQ号码	|string|		|是|
|student_id	|线上学号	|string	|	|是|
|daily_report	|近期日报链接	|string|		|是|
|graduated_school	|毕业院校	|string|		|  |
|brother	|审核师兄	|string|		|  |
|come_from	|了解到学院的渠道	|string	|	|  |
|slogan	|口号	|string	|	|  |

返回参数：
|字段	|说明	|类型	|备注	|是否必填|
|---|---|---|---|---|
|code|	|number	|	|是|
|data|	|boolean/disicple| | |

****

修改状态：(put)     
请求地址：/disciple
|字段	|说明	|类型	|备注	|是否必须|
|---|---|---|---|---|
|id	|主键	|int|	|	是|
|student_name	|名字|	string	|	| |
|major_subject	|学习方向|	string|	|	|
|graduated_school	|毕业院校|	string|	|	|
|brother	|审核师兄	|string	|	| |
|come_from	|了解到学院的渠道|	string|		| |
|daily_report	|近期日报链接	|string|	|	|
|slogan	|口号	|string	|	| |
|qq_num	|QQ号码	|string	|	|  |
|student_id	|线上学号	|string|	| |	

返回参数：
|字段	|说明	|类型	|备注	|是否必填|
|---|---|---|---|---|
|code|	|number|	|是|

****

查看学员信息：(get)     
请求地址：/disciple/{id}   
返回参数：
|字段	|说明	|类型	|备注	|是否必填|
|---|---|---|---|---|
|code	|	|number	|	|是|
|data	|	|disciple|	|	|

data
|字段	|说明	|类型	|备注	|是否必须|
|---|---|---|---|---|
|id	|主键	|int	|	|是|
|student_name	|名字	|string|		|是|
|major_subject	|学习方向	|string|		|是|
|graduated_school	|毕业院校	|string|		|是|
|brother	|审核师兄	|string|		|是|
|come_from	|了解到学院的渠道	|string|		|是|
|daily_report	|近期日报链接	|string|		|是|
|slogan	|口号	|string|		|是|
|qq_num	|QQ号码	|string|		|是|
|student_id	|线上学号|	string|		|是|

****

删除学员：(delete)       
请求地址：/disicple/{id}    
返回参数：
|字段	|说明	|类型	|备注	|是否必填|
|---|---|---|---|---|
|code|	|	|number|		|是|

