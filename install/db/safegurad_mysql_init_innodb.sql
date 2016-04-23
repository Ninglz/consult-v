use safeguard;

-- adjust sys_model_id_enhanced_gen
INSERT INTO sys_model_id_enhanced_gen(segment_name, next)
  VALUES('emp_seq', 101);

-- init system_default_property  test
INSERT INTO `system_default_property` (`id`, `created`, `active`, `last_modified`, `app_prefix`, `hardware_prefix`, `news_prefix`, `storage_file_prefix`, `system_host_url`, `website_static_url`) VALUES
	(111, 1436327745446, b'1', 1436327745446, '/apk', '/hardware', '/news', '/data/opt/safeguard', 'http://121.41.119.195:8084', 'http://121.41.119.195/safeguard/static');

--
INSERT INTO `version_type` (`id`,`category`, `name`, `description`) VALUES
	(1,1, 'QHT-CAR', '车载'),
	(2,1,'QHT-COM','便携'),
	(3,1,'QHT-EXP','安利'),
	(5,1,'QHT-HOM','家用'),
	(6,1,'QHT-A01','测试'),
	(7,0,'android',''),
	(8,0,'IOS','');

INSERT INTO `xls_model` (`id`, `category`, `template_path`) VALUES
	(1, 0, 'export_equipment_statistics.xls'),
	(2, 1, 'export_equipmnet_records.xls'),
	(3, 2, 'export_equipmnet_use.xls'),
	(4, 3, 'export_equipment_consumer_records.xls'),
	(5, 4, 'export_consumer_statistics.xls'),
	(6, 5, 'export_consumer_detail.xls'),
	(7, 6, 'infrared_template.xls'),
	(8, 7, 'channel_template.xls'),
	(9, 8, 'adjust_template.xls');