create table case_host
(
    id       int auto_increment comment '主键ID'
        primary key,
    protocol varchar(20)  not null comment '协议',
    host     varchar(100) not null comment '接口host',
    port     varchar(5)   null comment '端口号',
    comment  varchar(100) null comment '备注'
)
    comment '用例接口地址';

INSERT INTO joey.case_host (id, protocol, host, port, comment) VALUES (1, 'https', 'test02.gdzskj.tech', null, 'test02');