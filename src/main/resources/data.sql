--
--
-- user_id: 1
INSERT INTO t_preference (user_id, practice_id, preference_name) VALUES (1, 123456789, 'chris pref 1'); -- preference_id : 1
INSERT INTO t_preference (user_id, practice_id, preference_name) VALUES (1, 123456788, 'chris pref 2'); -- preference_id : 2
--
-- 'chris pref 1'
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (1, 'report1', 'widget1', '2022-01-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (1, 'report1', 'widget2', '2022-02-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (1, 'report2', 'widget1', '2022-03-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (1, 'report2', 'widget2', '2022-04-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (1, 'report2', 'widget3', '2022-05-01');
-- 'chris pref 2'
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (2, 'report1', 'widget1', '2022-06-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (2, 'report1', 'widget2', '2022-07-05');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (2, 'report2', 'widget1', '2022-08-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (2, 'report2', 'widget2', '2022-09-01');
--
--
-- user_id: 2
INSERT INTO t_preference (user_id, practice_id, preference_name) VALUES (2, 123456787, 'aayush pref 1'); -- preference_id : 3
INSERT INTO t_preference (user_id, practice_id, preference_name) VALUES (2, 123456786, 'aayush pref 2'); -- preference_id : 4
--
-- 'aayush pref 1'
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (3, 'report1', 'widget1', '2021-01-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (3, 'report1', 'widget2', '2021-02-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (3, 'report2', 'widget1', '2021-03-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (3, 'report2', 'widget2', '2021-04-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (3, 'report2', 'widget3', '2021-05-01');
-- 'aayush pref 2'
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (4, 'report1', 'widget1', '2021-06-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (4, 'report1', 'widget2', '2021-07-05');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (4, 'report2', 'widget1', '2021-08-01');
INSERT INTO t_preference_item (preference_id, report_name, item_name, item_value) VALUES (4, 'report2', 'widget2', '2021-09-01');