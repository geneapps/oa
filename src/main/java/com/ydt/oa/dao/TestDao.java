package com.ydt.oa.dao;

import org.springframework.stereotype.Repository;

import com.giro.common.dao.BaseDaoHibernate;
import com.ydt.oa.entity.Test;


/**
 * 数据库操作实现，不使用接口方式
 * @author caochun
 *
 */
@Repository
public class TestDao extends BaseDaoHibernate<Test>{
}
