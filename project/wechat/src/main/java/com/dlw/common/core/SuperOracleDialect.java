package com.dlw.common.core;

import java.sql.Types;

import org.hibernate.type.StandardBasicTypes;
import org.hibernate.dialect.Oracle10gDialect;

/**
 * 自定义oracle数据库方言，加入类型映射
 * @author diaoliwei
 * @date 2016-2-22
 */
public class SuperOracleDialect extends Oracle10gDialect {
	public SuperOracleDialect() {
		super();
		registerHibernateType(Types.CHAR, StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.LONGNVARCHAR, StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.DECIMAL, StandardBasicTypes.DOUBLE.getName());
	}
}
