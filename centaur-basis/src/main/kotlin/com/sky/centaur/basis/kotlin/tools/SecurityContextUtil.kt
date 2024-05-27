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
package com.sky.centaur.basis.kotlin.tools

import com.sky.centaur.basis.enums.TokenClaimsEnum
import org.apiguardian.api.API
import org.springframework.cglib.beans.BeanMap
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.ClaimAccessor
import org.springframework.security.oauth2.jwt.Jwt
import java.util.*

/**
 * 认证上下文工具类
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
object SecurityContextUtil {
    /**
     * Account领域模型id属性名
     */
    private const val ID = "id"

    @get:API(status = API.Status.STABLE, since = "1.0.0")
    @JvmStatic
    val loginAccountId: Optional<Long?>
        /**
         * 获取当前登录账户ID
         *
         * @return 登录账户ID
         */
        get() = Optional.ofNullable(SecurityContextHolder.getContext().authentication)
            .map { authentication: Authentication ->
                if (authentication.isAuthenticated) {
                    val principal = authentication.principal
                    if (principal is UserDetails) {
                        val beanMap = BeanMap.create(principal)
                        if (beanMap.containsKey(ID)) {
                            return@map beanMap[ID].toString().toLong()
                        }
                    } else if (principal is ClaimAccessor) {
                        val claims: Map<String, Any> = principal.claims
                        return@map claims[TokenClaimsEnum.ACCOUNT_ID.name].toString().toLong()
                    }
                }
                null
            }

    @get:API(status = API.Status.STABLE, since = "1.0.0")
    @JvmStatic
    val tokenValue: Optional<String?>
        /**
         * 获取当前token
         *
         * @return token
         */
        get() = Optional.ofNullable(SecurityContextHolder.getContext().authentication)
            .map { authentication: Authentication ->
                if (authentication.isAuthenticated) {
                    val principal = authentication.principal
                    if (principal is Jwt) {
                        return@map principal.tokenValue
                    }
                }
                null
            }
}
