use consult;

-- adjust sys_model_id_enhanced_gen
INSERT INTO sys_model_id_enhanced_gen(segment_name, next)
  VALUES('emp_seq', 101);

-- init system_default_property  test
INSERT INTO `system_default_property` (`id`, `created`, `active`, `last_modified`, `app_prefix`, `hardware_prefix`, `news_prefix`, `storage_file_prefix`, `system_host_url`, `website_static_url`) VALUES
	(111, 1436327745446, b'1', 1436327745446, '/apk', '/hardware', '/news', '/data/opt/safeguard', 'http://121.41.119.195:8084', 'http://121.41.119.195/safeguard/static');

-