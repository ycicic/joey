create table case_group
(
    id      int auto_increment comment '主键ID'
        primary key,
    module  varchar(30)          null comment '所属模块',
    comment varchar(100)         null comment '备注',
    is_run  tinyint(1) default 0 not null comment '是否执行',
    deleted tinyint(1) default 0 not null comment '删除标识'
)
    comment '用例表';

INSERT INTO joey.case_group (id, module, comment, is_run, deleted) VALUES (1, '审办', 'ETC小程序审办链路', 1, 0);
INSERT INTO joey.case_group (id, module, comment, is_run, deleted) VALUES (2, '激活', 'ETC小程序激活链路', 1, 0);
INSERT INTO joey.case_group (id, module, comment, is_run, deleted) VALUES (3, '二次激活', 'ETC小程序二次激活链路', 0, 0);
INSERT INTO joey.case_group (id, module, comment, is_run, deleted) VALUES (4, 'TestZS', '测试', 1, 0);