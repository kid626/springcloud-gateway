package com.bruce.demo.gateway.model.enums;

/**
 * 路由断言类型
 *
 * CreateTime: 2021-05-28
 *
 * @author HuangZhongHui
 **/
public enum PredicateType {
  /**
   * 断言语句的类型
   */
  After,
  Before,
  Between,
  Cookie,
  Header,
  Host,
  Method,
  Path,
  Query,
  RemoteAddr,
  Weight;
}
