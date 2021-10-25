create table case_api
(
    id                int auto_increment comment '主键ID'
        primary key,
    host_id           int                                                                                         not null comment 'host ID',
    api               varchar(100)                                                                                not null comment 'API地址',
    request_method    enum ('OPTIONS', 'GET', 'HEAD', 'POST', 'PUT', 'DELETE', 'TRACE', 'CONNECT') default 'GET'  not null comment '请求方式',
    request_data_type enum ('NONE', 'FORM', 'JSON', 'XML')                                         default 'NONE' not null comment '请求数据类型'
)
    comment '用例接口API';

INSERT INTO joey.case_api (id, host_id, api, request_method, request_data_type) VALUES (1, 1, '/oauth2/v2/oauth2/province/accessToken', 'GET', 'JSON');
INSERT INTO joey.case_api (id, host_id, api, request_method, request_data_type) VALUES (2, 1, '/order/v1/guangxi/orders/user/${system.uid}/_search', 'POST', 'JSON');