-- 1.	Design a storage model and data structure for Users and Groups where
-- a User can be member of a Group and a Group can be member of another Group.

-- Note: in current implementation, I did not consider the case, when some group
-- is a member of multiple other groups and queries should return all groups recursively,
-- however, this could be implemented using recursive queries, if required.

-- Note2: also, in current implementation, when linking two groups, it is required to
-- check if cycles are not introduced in group relations. I did not include this check, to
-- keep solution simple.

create table test.user
(
  id   bigint primary key,
  name varchar(255)
);

create table test.group
(
  id              bigint primary key,
  name            varchar(255),
  parent_group_id bigint references test.group

);
create table test.users_groups
(
  id       bigint primary key,
  user_id  bigint references test.user,
  group_id bigint references test.group
);
INSERT INTO test.user (id, name) VALUES
  (1, 'user1'),
  (2, 'user2'),
  (3, 'user3'),
  (4, 'user4'),
  (5, 'user5'),
  (6, 'user6'),
  (7, 'user7');

INSERT INTO test.group (id, name, parent_group_id) VALUES
  (1, 'group', null),
  (2, 'group', 1),
  (3, 'group', 1),
  (4, 'group', 1),
  (5, 'group', 2),
  (6, 'group', 2),
  (7, 'group', 3);

INSERT INTO test.users_groups (id, user_id, group_id) VALUES
  (1, 1, 1),
  (2, 1, 2),
  (3, 1, 3),
  (4, 1, 4),
  (5, 1, 5),
  (6, 2, 1),
  (7, 3, 2),
  (8, 4, 3),
  (9, 5, 4),
  (10, 5, 5),
  (11, 6, 1),
  (12, 6, 2),
  (13, 6, 3),
  (14, 7, 1),
  (15, 7, 2);

-- a.	How would you optimize for finding all Groups that a User is member of
select
  g.id as group_id,
  ug.user_id
from test.group g left join test.users_groups ug on g.id = ug.group_id
where ug.user_id = 1;

-- b.	All Users who are members of a Group
select
  u.id        as user_id,
  ug.group_id as group_id
from test.user u left join test.users_groups ug on u.id = ug.user_id
where ug.group_id = 1;

-- c.	Adding a new Group and Adding Users and Groups to that Group
INSERT INTO test.group (id, name, parent_group_id) VALUES (8, 'group', 1);

INSERT INTO test.users_groups (id, user_id, group_id) VALUES
  (16, 1, 8),
  (17, 2, 8);

-- c.	Adding a new Group and Adding Users and Groups to that Group
INSERT INTO test.group (id, name, parent_group_id) VALUES
  (9, 'group', 8),
  (10, 'group', 8);

-- d. Modifying the Groups that a User belongs to
update test.group g
set (name) = ('super-group')
from test.users_groups ug
where ug.user_id = 6 and g.id = ug.group_id;