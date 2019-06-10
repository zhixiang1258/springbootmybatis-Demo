-- 权限表
INSERT INTO `module` VALUES ('1', 'add');
INSERT INTO `module` VALUES ('2', 'delete');
INSERT INTO `module` VALUES ('3', 'query');
INSERT INTO `module` VALUES ('4', 'update');
-- 角色表
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'customer');
-- 用户角色关联表
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
-- 权限角色关联表
INSERT INTO `module_role` VALUES ('1', '1');
INSERT INTO `module_role` VALUES ('1', '2');
INSERT INTO `module_role` VALUES ('1', '3');
INSERT INTO `module_role` VALUES ('1', '4');
INSERT INTO `module_role` VALUES ('2', '1');
INSERT INTO `module_role` VALUES ('2', '3');
