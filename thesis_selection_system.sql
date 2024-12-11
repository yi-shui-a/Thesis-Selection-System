/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : thesis_selection_system

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 14/02/2023 16:50:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建仓库
DROP DATABASE IF EXISTS `thesis_select_system`;
CREATE DATABASE `thesis_select_system`;

-- 使用新创建的数据库
USE `thesis_select_system`;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `uid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '男',
  `tel` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `role` enum('ROLE_ROOT') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'ROLE_ROOT',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '修改测试', '$2a$10$WucA8iSR292WNUowvgaeb.UaGJdA47C/ejUtW39ZlIKO6hdDxafzO', '男', '18618161389', 'c.sdsa@qq.com', 'ROLE_ROOT');

-- ----------------------------
-- Table structure for education_type
-- ----------------------------
DROP TABLE IF EXISTS `education_type`;
CREATE TABLE `education_type`  (
  `education` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`education`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of education_type
-- ----------------------------
INSERT INTO `education_type` VALUES ('博士');
INSERT INTO `education_type` VALUES ('学士');
INSERT INTO `education_type` VALUES ('硕士');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `log` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `type` enum('ROOT','STUDENT','TEACHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '枚举类型（ROOT，TEACHER，STUDENT）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `pno` int(100) NOT NULL AUTO_INCREMENT COMMENT '此表为论文选题表',
  `tno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选题的描述信息',
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '枚举类型（论文型，工程型）（弃用，改为varcahr23.02.04）',
  `check_status` enum('CHECK','NOT_CHECK','REFUSE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'NOT_CHECK' COMMENT '审核状态（NOT_CHECK，CHECK,REFUSE）默认NOT_CHECK,只有审核状态为CHECK而且选择状态为NOT_SELECT才能被学生选择',
  `select_status` enum('NOT_SELECT','HAS_SELECT') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'NOT_SELECT' COMMENT '选择状态（NOT_SELECT，HAS_SELECT）默认NOT_SELECT',
  `reject_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `schoolyear` int(100) NOT NULL,
  PRIMARY KEY (`pno`, `tno`) USING BTREE,
  INDEX `sch->paper.schoolyear`(`schoolyear`) USING BTREE,
  INDEX `pno111`(`pno`) USING BTREE,
  INDEX `tea->paper.pno`(`tno`) USING BTREE,
  CONSTRAINT `sch->paper.schoolyear` FOREIGN KEY (`schoolyear`) REFERENCES `schoolyear` (`schoolyear`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tea->paper.pno` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (3, '1', '787848545', '修改选题测试', '工程型', 'CHECK', 'HAS_SELECT', NULL, 2020);
INSERT INTO `paper` VALUES (4, '1', 'test', NULL, '论文型', 'NOT_CHECK', 'HAS_SELECT', NULL, 2020);
INSERT INTO `paper` VALUES (5, '1', '41324235', '543653456', '论文型', 'CHECK', 'HAS_SELECT', NULL, 2020);
INSERT INTO `paper` VALUES (6, '1', 'test111', '拒绝测试', '论文型', 'CHECK', 'HAS_SELECT', NULL, 2020);
INSERT INTO `paper` VALUES (7, '1', 'test111', '这是关于选题的描述信息', '论文型', 'CHECK', 'HAS_SELECT', NULL, 2020);
INSERT INTO `paper` VALUES (8, '1', '5454515', '121548', '666', 'NOT_CHECK', 'NOT_SELECT', NULL, 2020);
INSERT INTO `paper` VALUES (9, '1', '2019', '2019', '666', 'NOT_CHECK', 'NOT_SELECT', NULL, 2019);

-- ----------------------------
-- Table structure for schoolyear
-- ----------------------------
DROP TABLE IF EXISTS `schoolyear`;
CREATE TABLE `schoolyear`  (
  `schoolyear` int(100) NOT NULL,
  PRIMARY KEY (`schoolyear`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schoolyear
-- ----------------------------
INSERT INTO `schoolyear` VALUES (2019);
INSERT INTO `schoolyear` VALUES (2020);
INSERT INTO `schoolyear` VALUES (2021);
INSERT INTO `schoolyear` VALUES (2022);

-- ----------------------------
-- Table structure for select_deadline
-- ----------------------------
DROP TABLE IF EXISTS `select_deadline`;
CREATE TABLE `select_deadline`  (
  `select_deadline` date NOT NULL,
  PRIMARY KEY (`select_deadline`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of select_deadline
-- ----------------------------
INSERT INTO `select_deadline` VALUES ('2023-02-15');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(100) NOT NULL AUTO_INCREMENT,
  `sno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '枚举类型男和女',
  `tel` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `schoolyear` int(100) NOT NULL,
  `able_topic_num` int(100) NOT NULL DEFAULT 1 COMMENT '学生可选题目数（默认为1）',
  `selected_topic_num` int(100) NOT NULL DEFAULT 0 COMMENT '已选题目数（默认为0）',
  `role` enum('ROLE_STUDENT') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ROLE_STUDENT',
  PRIMARY KEY (`sno`) USING BTREE,
  INDEX `student_sid`(`sid`) USING BTREE,
  INDEX `student.year->schoolyear`(`schoolyear`) USING BTREE,
  CONSTRAINT `student.year->schoolyear` FOREIGN KEY (`schoolyear`) REFERENCES `schoolyear` (`schoolyear`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '1', '哒哒哒', '$2a$10$Anm5RfRNuJjnT9WPSdzVoObTCzS/H6RekyJjCxgEMS7DjF1vg3Fg2', '男', '18197587385', 'l.ptnjl@qq.com', '2023-02-02', 2020, 8, 8, 'ROLE_STUDENT');
INSERT INTO `student` VALUES (4, '11', '1123', '$2a$10$2FpwYme49RaN8tRUbgwbTOUGdlFTXiqlCd3M1zdUyH6c8R8/3Rg5G', '男', NULL, NULL, NULL, 2020, 99, 0, 'ROLE_STUDENT');
INSERT INTO `student` VALUES (3, '12', 'hhhh', '$2a$10$XPrHWRSwaxV5VOJ3UCgLrOpMQyRkEXzicarE.7RerxCJ3a6sfxyUq', '女', NULL, NULL, NULL, 2020, 7, 0, 'ROLE_STUDENT');
INSERT INTO `student` VALUES (5, '132', '送达', '$2a$10$MpOdahOiNkI0nYIkRg40gOYy9TJwfBEpwi/IKQMnhmCkZgr/exRdS', '男', NULL, NULL, NULL, 2019, 1, 0, 'ROLE_STUDENT');
INSERT INTO `student` VALUES (2, '2', '22', '2', '男', '22', '2', '2023-02-02', 2020, 9, 0, 'ROLE_STUDENT');
INSERT INTO `student` VALUES (6, '324', '1321', '$2a$10$HyzMJ4VpR/QlyS7qfEo.8uyIQT/J4YbS99qqPe2lCFtuJdULNwT/i', '男', NULL, NULL, NULL, 2022, 1, 0, 'ROLE_STUDENT');

-- ----------------------------
-- Table structure for student_paper
-- ----------------------------
DROP TABLE IF EXISTS `student_paper`;
CREATE TABLE `student_paper`  (
  `spid` int(100) NOT NULL AUTO_INCREMENT,
  `sno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `pno` int(100) NOT NULL,
  `accept_status` enum('ACCEPT','REFUSE','NOT_CHECK') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'NOT_CHECK' COMMENT '枚举类型（NOT_CHECK表示未审核默认为它，REFUSE表示拒绝，ACCEPT表示接受',
  `refuse_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`sno`, `pno`, `spid`) USING BTREE,
  INDEX `sp_spid`(`spid`) USING BTREE,
  INDEX `paper.pno->sp.pno`(`pno`) USING BTREE,
  CONSTRAINT `paper.pno->sp.pno` FOREIGN KEY (`pno`) REFERENCES `paper` (`pno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student.sno->sp.sno` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_paper
-- ----------------------------
INSERT INTO `student_paper` VALUES (1, '1', 3, 'ACCEPT', '', '2023-02-04 15:18:13');
INSERT INTO `student_paper` VALUES (15, '1', 5, 'ACCEPT', '', '2023-02-13 15:43:44');
INSERT INTO `student_paper` VALUES (8, '1', 6, 'NOT_CHECK', '', '2023-02-04 15:26:39');
INSERT INTO `student_paper` VALUES (6, '1', 7, 'ACCEPT', '已被其他学生选择', '2023-02-04 15:18:23');
INSERT INTO `student_paper` VALUES (12, '2', 7, 'REFUSE', NULL, '2023-02-05 21:35:35');

-- ----------------------------
-- Table structure for student_paper_history
-- ----------------------------
DROP TABLE IF EXISTS `student_paper_history`;
CREATE TABLE `student_paper_history`  (
  `id` int(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `pno` int(100) NOT NULL,
  `refuse_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_paper_history
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` int(100) NOT NULL AUTO_INCREMENT,
  `tno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `teacher_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '讲师' COMMENT '职称(改为varchar，23.02.04)',
  `education` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '研究生' COMMENT '学历（改为varchar，23.02.04)',
  `role` enum('ROLE_TEACHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'ROLE_TEACHER',
  PRIMARY KEY (`tno`) USING BTREE,
  INDEX `teacher_tid`(`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '1', '修改测试1', '$2a$10$pQ6WacAjwTZlXv4c/cx.Q.kFlhH3eeb8Gqj1hOIsAYZ/tCJvSh.4W', '男', '12345', '666@qq.com', '2023-02-07', '讲师', '博士', 'ROLE_TEACHER');
INSERT INTO `teacher` VALUES (2, '3', '心前的', '$2a$10$2tgGV3qxp01yEb/gXDgOmeJYO0PuebCOZ9d0brvSmf0RmtnikbHqW', '女', '13213', '1234@qq.co', NULL, '教授', '研究生', 'ROLE_TEACHER');
INSERT INTO `teacher` VALUES (3, '33', '33', '$2a$10$ZxNYx0J8UcHl6PnfG9pF0OeLgBXUhEKRw5sPy8rAf72j3n.OhGrNG', '男', NULL, NULL, NULL, '副教授', '学士', 'ROLE_TEACHER');

-- ----------------------------
-- Table structure for teacher_student
-- ----------------------------
DROP TABLE IF EXISTS `teacher_student`;
CREATE TABLE `teacher_student`  (
  `tno` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '该表用于查询每个学年每个老师能指导的学生个数和已经指导的学生个数',
  `schoolyear` int(100) NOT NULL,
  `can_teach_num` int(100) NOT NULL DEFAULT 5,
  `has_teach_num` int(100) NOT NULL DEFAULT 0,
  PRIMARY KEY (`tno`, `schoolyear`) USING BTREE,
  INDEX `schoolyear->st.schoolyear`(`schoolyear`) USING BTREE,
  CONSTRAINT `schoolyear->st.schoolyear` FOREIGN KEY (`schoolyear`) REFERENCES `schoolyear` (`schoolyear`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher.tno->st.tno` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_student
-- ----------------------------
INSERT INTO `teacher_student` VALUES ('1', 2020, 33, 2);
INSERT INTO `teacher_student` VALUES ('3', 2019, 334, 0);
INSERT INTO `teacher_student` VALUES ('3', 2020, 334, 0);
INSERT INTO `teacher_student` VALUES ('3', 2021, 33, 0);
INSERT INTO `teacher_student` VALUES ('3', 2022, 33, 0);
INSERT INTO `teacher_student` VALUES ('33', 2019, 5, 0);
INSERT INTO `teacher_student` VALUES ('33', 2020, 5, 0);
INSERT INTO `teacher_student` VALUES ('33', 2021, 5, 0);
INSERT INTO `teacher_student` VALUES ('33', 2022, 5, 0);

-- ----------------------------
-- Table structure for teacher_title
-- ----------------------------
DROP TABLE IF EXISTS `teacher_title`;
CREATE TABLE `teacher_title`  (
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职称',
  PRIMARY KEY (`title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_title
-- ----------------------------
INSERT INTO `teacher_title` VALUES ('副教授');
INSERT INTO `teacher_title` VALUES ('助教');
INSERT INTO `teacher_title` VALUES ('教授');
INSERT INTO `teacher_title` VALUES ('讲师');

-- ----------------------------
-- Table structure for title_type
-- ----------------------------
DROP TABLE IF EXISTS `title_type`;
CREATE TABLE `title_type`  (
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of title_type
-- ----------------------------
INSERT INTO `title_type` VALUES ('666');
INSERT INTO `title_type` VALUES ('工程型');
INSERT INTO `title_type` VALUES ('论文型');

SET FOREIGN_KEY_CHECKS = 1;
