/*
 * Copyright (c) 2024-2024, kaiyu.shan@outlook.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

-- 性别枚举
CREATE TYPE sex AS ENUM (
    'MALE',
    'FEMALE',
    'GREY',
    'SEXLESS'
    );
-- users新增列
-- 性别
ALTER TABLE users
    ADD COLUMN sex sex;
comment on column users.sex is '性别';
-- 头像地址
ALTER TABLE users
    ADD COLUMN avatar_url varchar(200);
comment on column users.avatar_url is '头像地址';
-- 手机号
ALTER TABLE users
    ADD COLUMN phone varchar(200);
comment on column users.phone is '手机号';

