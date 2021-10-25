# gray-blog

## 环境
- [JDK 11.0.12](https://www.oracle.com/java/technologies/downloads/#java11)
- [mysql-community 8.0.26.0](https://dev.mysql.com/downloads/windows/installer/8.0.html)
- [Redis 3.2.100](https://github.com/microsoftarchive/redis/releases)
- [canal.deployer 1.1.5](https://github.com/alibaba/canal/releases)

1. 2021.10.13  
Question : 多对多实体类在存入redis时，无限序列化问题
Solution : 尝试JackSon中的 @JsonBackReference/@JsonManagedReference/@JsonIdentityInfo，在多对一和一对多中有效，但多对多依然无法解决，最终决定使用     @JsonIngore忽略相应field

2. 2021.10.22
Question : 缓存(redis)与数据库(mysql)的一致性问题  (增删改)
Solution : 综合比较多种方案，包括：加锁串行化、设置缓存有效期、先更新数据库后删除缓存 + 消息队列、先更新数据库后删除缓存 + 订阅数据库变更日志。最终采用 “canal服务订阅变更日志 + 先更新后删” 实现第4种方案

3. 2021.10.25
Task     : 重构redis缓存服务，基于AOP分离缓存查询代码（@Aspect等）（查）
Question ：切点在服务层，出于代码复用的考虑，类内调用无法避免。然而，由于AOP底层为动态代理，当类内调用切点时，无法触发切点通知（advice）
Solution : 将被切方法指向dao层

4. 2021.10.25
Task     : 文章访问量统计，要求同一ip同一文章在一天内不重复统计
Question : 频繁访问并更新数据库，且在canal的监听下文章的更新导致对应缓存频繁被删除，整体效率差
Solution : 总访问量 = 数据库记录访问量 + 缓存中记录访问量。使用redis的HyperLogLog高级数据结构，存储并统计各文章的当日访问ip，内存占用低且速度快，每日0点统一回写至数据库。  