USE `module_2_db`;

INSERT INTO `tag` (`id`, `name`)
VALUES
(1, 'tag_1'),
(2, 'tag_2'),
(3, 'tag_3'),
(4, 'tag_4');


INSERT INTO `certificate` (`id`, `name`, `description`, `price`, `duration`, `create_date`, `last_update_date`)
VALUES
(1, 'certificate_1', 'certificate with #1', 100, 7, '2023-04-02T11:30:14.868', '2023-04-02T11:30:14.868'),
(2, 'certificate_2', 'certificate with #2', 200, 15, '2023-04-03T11:30:14.868', '2023-04-03T11:30:14.868'),
(3, 'certificate_3', 'certificate with #3', 300, 30, '2023-04-04T11:30:14.868', '2023-04-04T11:30:14.868'),
(4, 'certificate_4', 'certificate with #4', 400, 45, '2023-04-05T11:30:14.868', '2023-04-05T11:30:14.868');

INSERT INTO `certificate_with_tag` (`tag_id`, `certificate_id`)
VALUES
(1, 1),
(1, 4),
(2, 3),
(3, 3),
(3, 2),
(3, 4),
(4, 1),
(4, 2);