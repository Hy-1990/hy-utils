package com.bigdata.hygateway.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/** @Author huyi @Date 2020/8/6 9:33 @Description: ip地址限流器 */
public class HostAddrKeyResolver implements KeyResolver {

  @Override
  public Mono<String> resolve(ServerWebExchange exchange) {
    return Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
  }
}
