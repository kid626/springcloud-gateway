package com.bruce.demo.gateway.model.enums;

/**
 * 过滤器类型
 *
 * CreateTime: 2021-05-28
 *
 * @author HuangZhongHui
 **/
public enum FilterType {
  /**
   * 过滤器的类型
   */
  AddRequestHeader,
  AddRequestParameter,
  AddResponseHeader,
  DedupeResponseHeader,
  CircuitBreaker,
  FallbackHeaders,
  MapRequestHeader,
  PrefixPath,
  PreserveHostHeader,
  RequestRateLimiter,
  RedirectTo,
  RemoveRequestHeader,
  RemoveResponseHeader,
  RemoveRequestParameter,
  RewritePath,
  RewriteLocationResponseHeader,
  RewriteResponseHeader,
  SaveSession,
  SetPath,
  SetRequestHeader,
  SetResponseHeader,
  SetStatus,
  StripPrefix,
  Retry,
  RequestSize,
  SetRequestHostHeader,
  TokenRelay;
}
