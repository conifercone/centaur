-- @formatter:off
-- noinspection SqlConstantExpressionForFile
-- noinspection SqlResolveForFile
-- 添加注释
comment on table broadcast_text_message is '文本广播消息表';
comment on column broadcast_text_message.id is '消息ID';
comment on column broadcast_text_message.sender_id is '发送者ID';
comment on column broadcast_text_message.receiver_ids is '接收者ID集合';
comment on column broadcast_text_message.read_quantity is '已读数量';
comment on column broadcast_text_message.unread_quantity is '未读数量';
comment on column broadcast_text_message.message is '消息内容';
comment on column broadcast_text_message.message_status is '消息状态';
comment on column broadcast_text_message.creation_time is '创建时间';
comment on column broadcast_text_message.founder is '创建人';
comment on column broadcast_text_message.modifier is '修改人';
comment on column broadcast_text_message.modification_time is '修改时间';
comment on column broadcast_text_message.read_receiver_ids is '已读接收者ID集合';
comment on column broadcast_text_message.unread_receiver_ids is '未读接收者ID集合';
comment on table broadcast_text_message_archived is '文本广播消息归档表';
comment on column broadcast_text_message_archived.id is '消息ID';
comment on column broadcast_text_message_archived.sender_id is '发送者ID';
comment on column broadcast_text_message_archived.receiver_ids is '接收者ID集合';
comment on column broadcast_text_message_archived.read_quantity is '已读数量';
comment on column broadcast_text_message_archived.unread_quantity is '未读数量';
comment on column broadcast_text_message_archived.message is '消息内容';
comment on column broadcast_text_message_archived.message_status is '消息状态';
comment on column broadcast_text_message_archived.creation_time is '创建时间';
comment on column broadcast_text_message_archived.founder is '创建人';
comment on column broadcast_text_message_archived.modifier is '修改人';
comment on column broadcast_text_message_archived.modification_time is '修改时间';
comment on column broadcast_text_message_archived.read_receiver_ids is '已读接收者ID集合';
comment on column broadcast_text_message_archived.unread_receiver_ids is '未读接收者ID集合';
comment on table subscription_text_message is '文本订阅消息表';
comment on column subscription_text_message.id is '消息ID';
comment on column subscription_text_message.sender_id is '发送者ID';
comment on column subscription_text_message.receiver_id is '接收者ID';
comment on column subscription_text_message.message is '消息内容';
comment on column subscription_text_message.message_status is '消息状态';
comment on column subscription_text_message.creation_time is '创建时间';
comment on column subscription_text_message.founder is '创建人';
comment on column subscription_text_message.modifier is '修改人';
comment on column subscription_text_message.modification_time is '修改时间';
comment on table subscription_text_message_archived is '文本订阅消息归档表';
comment on column subscription_text_message_archived.id is '消息ID';
comment on column subscription_text_message_archived.sender_id is '发送者ID';
comment on column subscription_text_message_archived.receiver_id is '接收者ID';
comment on column subscription_text_message_archived.message is '消息内容';
comment on column subscription_text_message_archived.message_status is '消息状态';
comment on column subscription_text_message_archived.creation_time is '创建时间';
comment on column subscription_text_message_archived.founder is '创建人';
comment on column subscription_text_message_archived.modifier is '修改人';
comment on column subscription_text_message_archived.modification_time is '修改时间';
