use safeguard;
-- staff
INSERT INTO `customer` (`id`,`created`,`active`,`last_modified`,`hash`,`salt`,`token`,`username`,`locked`,`no_login`,`last_signed`) VALUES (1,1418089500000,1,1418089500000,'$6$f4b53ed2$3jPj46oXiAJBszCykXCZ2yAjUpGwM6U0z7.ScbxYiQyIRF17zE4nUwldfkuPi2w/.5V/Evho.sqMUi94v2TvV/','$6$f4b53ed2-fd1b-4240-b1d8-941cef23d325',NULL,'admin',0,0,0);

INSERT INTO `admin_staff` (`cell_no_auth`,`cell_no`,`cell_no_token`,`id_card_auth`,`id_card`,`id_card_token`,`is_root`,`real_name_auth`,`real_name`,`real_name_token`,`id`) VALUES (1,'13408460635',NULL,1,'510105198607102278',NULL,1,1,'李向棱',NULL,1);

-- role
INSERT INTO `admin_role` (`id`,`created`,`active`,`last_modified`,`role_code`,`role_name`) VALUES (1,1418089500000,1,1418089500000,'ROLE_ADMIN','超级管理员');


-- staff-role
INSERT INTO `admin_staff_role` (`staff_id`,`role_id`) VALUES (1,1);


-- resources
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (1,1418089500000,1,1418089500000,'首页资源管理','/admin/common/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (2,1418089500000,1,1418089500000,'账户管理','/admin/staff/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (3,1418089500000,1,1418089500000,'咨询管理','/admin/news/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (4,1418089500000,1,1418089500000,'版本管理','/admin/version/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (5,1418089500000,1,1418089500000,'系统配置信息','/admin/system/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (6,1418089500000,1,1418089500000,'设备管理','/admin/equipment/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (7,1418089500000,1,1418089500000,'生产管理','/admin/production/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (8,1418089500000,1,1418089500000,'红外编码管理','/admin/infrared/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (9,1418089500000,1,1418089500000,'渠道管理','/admin/pipeline/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (10,1418089500000,1,1418089500000,'用户管理','/admin/customer/**');
INSERT INTO `admin_resources` (`id`,`created`,`active`,`last_modified`,`name`,`pattern_url`) VALUES (11,1418089500000,1,1418089500000,'接口管理','/admin/interface/**');

-- resources-role
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,1);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,2);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,3);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,4);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,5);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,6);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,7);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,8);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,9);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,10);
INSERT INTO `admin_role_resources` (`role_id`,`resources_id`) VALUES (1,11);