create table case_detail
(
    id            int auto_increment comment '主键ID'
        primary key,
    group_id      int                                                   not null comment '关联用例ID',
    comment       text                                                  null comment '备注/用例步骤',
    is_run        tinyint(1)                          default 0         not null comment '是否运行',
    case_type     enum ('AFTER', 'BEFORE', 'DEFAULT') default 'DEFAULT' not null,
    protocol_type enum ('HTTP', 'MQ', 'RPC', 'TIMER') default 'HTTP'    not null comment '用例接口渠道',
    host_id       int                                                   null comment '请求地址ID',
    request_data  json                                                  null comment '参数信息',
    pre_result    varchar(100)                                          null comment '结果断言',
    is_cache      tinyint(1)                          default 0         not null comment '是否缓存',
    creator       bigint                                                null comment '创建人',
    create_time   datetime                                              null comment '创建时间',
    update_time   datetime                                              null comment '更新时间',
    deleted       tinyint(1)                          default 0         not null
)
    comment '用例详细表';

INSERT INTO joey.case_detail (id, group_id, comment, is_run, case_type, protocol_type, host_id, request_data, pre_result, is_cache, creator, create_time, update_time, deleted) VALUES (1, 4, '测试auth', 1, 'BEFORE', 'HTTP', 1, '{"data": {"code": "${system.code}", "appId": "2019093067954418"}}', '$.code=200', 1, null, null, null, 0);
INSERT INTO joey.case_detail (id, group_id, comment, is_run, case_type, protocol_type, host_id, request_data, pre_result, is_cache, creator, create_time, update_time, deleted) VALUES (2, 4, '测试用例', 1, 'DEFAULT', 'HTTP', 2, '{"data": {}, "header": {"Authorization": "Bearer ${cache.1.data.zsAccessToken}"}}', null, 0, null, null, null, 0);