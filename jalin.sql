/*
 Navicat Premium Data Transfer

 Source Server         : Local 3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : jalin

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 31/07/2023 09:25:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill_payment
-- ----------------------------
DROP TABLE IF EXISTS `bill_payment`;
CREATE TABLE `bill_payment`  (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_admin` double(12, 0) NULL DEFAULT 0,
  `bill_amount` double(12, 0) NULL DEFAULT 0,
  `bill_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `bill_number` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `bill_status` int(1) NULL DEFAULT 0,
  `bill_total` double(12, 0) NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  UNIQUE INDEX `UK_c8nb55cb3wlt8u4ao3xwvhw3y`(`bill_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bill_payment
-- ----------------------------
INSERT INTO `bill_payment` VALUES (1, 2500, 50000, 'Pembayaran ABC', '100001', 1, 52500, '2023-07-30 18:04:18', '2023-07-30 18:04:18');
INSERT INTO `bill_payment` VALUES (2, 2500, 100000, 'Pembayaran BCD', '100002', 1, 102500, '2023-07-30 18:15:11', '2023-07-30 18:15:11');
INSERT INTO `bill_payment` VALUES (3, 2500, 100000, 'Pembayaran BCD', '100003', 1, 102500, '2023-07-30 18:15:58', '2023-07-30 18:15:58');
INSERT INTO `bill_payment` VALUES (4, 2500, 100000, 'Pembayaran BCD', '100004', 1, 102500, '2023-07-31 09:09:35', '2023-07-31 09:09:35');

-- ----------------------------
-- Table structure for trx_payment
-- ----------------------------
DROP TABLE IF EXISTS `trx_payment`;
CREATE TABLE `trx_payment`  (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `bill_admin` double(12, 0) NULL DEFAULT 0,
  `bill_amount` double(12, 0) NULL DEFAULT 0,
  `bill_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `bill_number` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `bill_total` double(12, 0) NULL DEFAULT 0,
  `chanel_id` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(1) NULL DEFAULT 0,
  `pay_reff_number` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `reff_number` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_id`) USING BTREE,
  UNIQUE INDEX `UK_d186jb7ly0ga6va0tt8ruqucd`(`reff_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of trx_payment
-- ----------------------------
INSERT INTO `trx_payment` VALUES (1, 'MDR', 2500, 50000, 'Pembayaran ABC', '100001', 52500, '1234', '2023-07-30 16:28:26', 1, '47c255c9e50b4f2ca6df3c285bc614c8', '141fd0191d3d4b5da9117b344a229088', '2023-07-30 16:28:26');
INSERT INTO `trx_payment` VALUES (2, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 16:32:23', 0, NULL, 'f52a7e105a21440eae9c3229a2e2245b', NULL);
INSERT INTO `trx_payment` VALUES (3, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 16:42:08', 0, NULL, 'f1f893e189a54f31a9550747bca37e21', NULL);
INSERT INTO `trx_payment` VALUES (4, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 16:48:43', 0, NULL, '94e2d776402d4e738f7e86bae1ddc25f', NULL);
INSERT INTO `trx_payment` VALUES (5, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 16:51:36', 0, NULL, 'f39285e6f8944c42babc822078c14c59', NULL);
INSERT INTO `trx_payment` VALUES (6, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 17:29:25', 1, 'c27d28eb9daa465d88f712a28c7deae2', 'c27d28eb9daa465d88f712a28c7deae2', '2023-07-30 17:29:25');
INSERT INTO `trx_payment` VALUES (7, 'MDR', 2500, 100000, 'Pembayaran BCD', '100003', 102500, '1234', '2023-07-30 17:58:54', 1, NULL, '44035f85a61c4c8799565a466a68bda0', '2023-07-30 17:58:54');
INSERT INTO `trx_payment` VALUES (8, 'MDR', 2500, 50000, 'Pembayaran ABC', '100001', 52500, '1234', '2023-07-30 18:04:18', 1, '08e7209c5f624858b109b57f8a95d7d5', '08e7209c5f624858b109b57f8a95d7d5', '2023-07-30 18:04:18');
INSERT INTO `trx_payment` VALUES (9, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 18:11:23', 0, NULL, '7aecde896f5a4871a59bad01124dfe39', NULL);
INSERT INTO `trx_payment` VALUES (10, 'MDR', 2500, 100000, 'Pembayaran BCD', '100002', 102500, '1234', '2023-07-30 18:15:11', 1, '79372096eec14137b0ec76808066a4ec', '79372096eec14137b0ec76808066a4ec', '2023-07-30 18:15:11');
INSERT INTO `trx_payment` VALUES (11, 'MDR', 2500, 100000, 'Pembayaran BCD', '100003', 102500, '1234', '2023-07-30 18:15:58', 1, '79372096eec14137b0ec76808066a4ec', 'd611dde612e947439bac2f8b849757f1', '2023-07-30 18:15:58');
INSERT INTO `trx_payment` VALUES (12, 'MDR', 2500, 100000, 'Pembayaran BCD', '100004', 102500, '1234', '2023-07-31 09:09:35', 1, '79372096eec14137b0ec76808066a4ec', '09e03c514926408089271f92fc6a1693', '2023-07-31 09:09:35');

-- ----------------------------
-- Table structure for ttes
-- ----------------------------
DROP TABLE IF EXISTS `ttes`;
CREATE TABLE `ttes`  (
  `test` double(12, 0) NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ttes
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
