package com.example.dolphin.acomm.infrastructure.support;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 自定义Id生成器
 *
 * @author ankelen
 * @date 2022-01-13 11:27
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {
    public final static String TYPE = "com.example.dolphin.acomm.infrastructure.support.SnowflakeIdGenerator";

    final static Snowflake SNOWFLAKE = IdUtil.getSnowflake();

    @Override
    public Serializable generate(SharedSessionContractImplementor implementor, Object o) throws HibernateException {
        return SNOWFLAKE.nextIdStr();
    }
}
