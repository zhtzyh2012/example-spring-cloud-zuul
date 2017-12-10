spring:
  application:
    name: eureka-server # 注册中心中会有提示显示,同时会作为服务调用使用

    profiles:
        active: $spring.profiles.active$　## application-{}.yml,动态获取配置文件内容

server:
  port: 8001  # 应用服务的调动接口

eureka:
  instance:
    hostname: eurekaserver1 # 域名,linux需要在etc/hosts下配置， windows需要在xxx/hosts配置
    lease-renewal-interval-in-seconds: 10   # 全部的心跳间隔时间,服务提供者向注册中心发送心跳的时间间隔,即续约时间间隔
    lease-expiration-duration-in-seconds: 120 # 注册中心服务到期时间,即如果服务提供者这段时间内联系不上服务提供者,那么进行服务下线处理

# register-with-eureka,fetch-registry是禁止自己注册自己使用，如果没有这两行会报出异常,但不会影响正常使用
# service-url.defaultZone 其他微服务注册地址, 打开http://${eureka.instance.hostname}:${server.port},就会出现注册中心网址
  client:
      register-with-eureka: false
      fetch-registry: false
      service-url:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/



