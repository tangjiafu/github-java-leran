# 初始化数据库
drop table if exists `red_record`;
create table `red_record`
(
    `id`          int(32)                            not null auto_increment,
    `user_id`     int(32)                            not null comment '用户id',
    `red_packet`  varchar(255) character set utf8mb4 not null comment '红包唯一标识串',
    `total`       int(11)                            not null comment '红包总人数',
    `amount`      decimal(10, 2) default null comment '总金额（单位为分）',
    `is_active`   tinyint(4)     default '1' comment '是否有效',
    `create_time` datetime       default null comment '创建时间',
    primary key (`id`)
) engine = InnoDB
  auto_increment = 11
  default charset = utf8
    comment ='发红包记录';
#
drop table if exists `red_detail`;
create table `red_detail`
(
    `id`          int(32) not null auto_increment,
    `record_id`   int(32) not null comment '红包记录id',
    `amount`      decimal(8, 2) default NULL comment '金额（单位为分）',
    `is_active`   tinyint(4)    default '1' comment '是否有效',
    `create_time` datetime      default null,
    primary key (`id`)
) engine = InnoDB
  auto_increment = 83
  default charset = utf8 comment ='红包明细金额';
#

drop table if exists `red_rob_record`;
create table `red_rob_record`
(
    `id`         int(32) not null auto_increment,
    `user_id`    int(32)                            default null comment '用户账户',
    `red_packet` varchar(255) character set utf8mb4 default null comment '红包标识串',
    `amount`     decimal(8, 2)                      default null comment '红包金额（单位为分）',
    `rob_time`   datetime                           default null comment '抢红包的时间',
    `is_active`  tinyint(4)                         default '1' comment '是否有效',
    primary key (`id`)
) engine = InnoDB
  auto_increment = 72
  default charset = utf8 comment ='抢红包记录';
