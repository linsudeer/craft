## 包结构
com.czht.smartpark

com.czht.smartpark.common

com.czht.smartpark.provider

com.czht.smartpark.provider.api

com.czht.smartpark.[servicename]

### 服务架构模块
1. 注册中心
2. 权限验证中心
3. 统一配置中心
4. 统一监控
5. 统一日志收集
6. 各个服务模块
-- 用户中心
-- 消息服务中心
-- TODO


### 权限认证方式

Oauth2 统一服务认证

1. 微服务之间的认证使用 client_credentials(客户端模式)。如果需要调用其他服务的api需配置 security.client 中的信息。后续需要把客户端授权放到数据库中配置，并且加载到缓存中
2. 用户授权采用 password(密码模式)


### 遇到的问题以及解决方法

1. 在做微服务鉴权的时候，使用feign调用服务，发现oauth/token获取token一直返回null
 解决：经过源码跟踪发现，被调用的服务返回有token信息，但是调用方接受不到，最后发现是 服务方配置文件增加了

```
http:
     converters:
       preferred-json-mapper: fastjson
```
导致返回与接受数据格式不一致导致，去掉配置解决
