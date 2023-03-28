create table command
(
    id                bigserial not null,
    color             varchar(255),
    number_of_repeats varchar(255),
    text              varchar(255),
    type              varchar(255),
    primary key (id)
);
create table group_task
(
    group_id int8 not null,
    task_id  int8 not null
);
create table groups
(
    id         bigserial not null,
    name       varchar(255),
    teacher_id int8,
    primary key (id)
);
create table task
(
    id       bigserial not null,
    angle    int4,
    grid     varchar(255),
    m        int4,
    n        int4,
    x        int4,
    y        int4,
    name     varchar(255),
    owner_id int8,
    primary key (id)
);
create table task_command
(
    task_id    int8 not null,
    command_id int8 not null
);
create table user_task
(
    task_id  int8 not null,
    user_id  int8 not null,
    solution varchar(255),
    solved   boolean,
    mark     int4,
    primary key (task_id, user_id)
);
create table users
(
    id       bigserial not null,
    name     varchar(255),
    password varchar(255),
    role     varchar(255),
    group_id int8,
    primary key (id)
);
alter table group_task
    add constraint FKtoec9whtcyj4conk0vhmesfua foreign key (task_id) references task;
alter table group_task
    add constraint FKtqvm1xo0r2cfheblv43gkxf4l foreign key (group_id) references groups;
alter table groups
    add constraint FKfbehr2v3qarkb2wxo7ex18fi4 foreign key (teacher_id) references users;
alter table task
    add constraint FKphl46nwqwa5kw3dn00l4cj93w foreign key (owner_id) references users;
alter table task_command
    add constraint FKsccrrw43df7mw7sst8vbgum36 foreign key (command_id) references command;
alter table task_command
    add constraint FK9dxtkc6ao4g8qcxe5gabicnn8 foreign key (task_id) references task;
alter table user_task
    add constraint FKvs34bjkmpbk2e54qlrol3ilt foreign key (task_id) references task;
alter table user_task
    add constraint FKj6lai3y87ttxldkysg1549etg foreign key (user_id) references users;
alter table users
    add constraint FKemfuglprp85bh5xwhfm898ysc foreign key (group_id) references groups;
