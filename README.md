# Gray blog

---

## 环境
- [JDK 11.0.12](https://www.oracle.com/java/technologies/downloads/#java11)
- [mysql-community 8.0.26.0](https://dev.mysql.com/downloads/windows/installer/8.0.html)
- [Redis 3.2.100](https://github.com/microsoftarchive/redis/releases)
- [canal.deployer 1.1.5](https://github.com/alibaba/canal/releases)

## 项目配置
打开文件`src/main/resources/application.properties`
- 服务器配置
```yaml
# 端口号，调试时随意配置，部署时建议80
server.port = 8080
# http header 最大传输量，因为后台在线文章编辑的传输量较大
server.max-http-header-size = 102400
```

- 数据库MySQL配置
```yaml
# 数据库
spring.jpa.database = MYSQL
# 是否显示sql语句
spring.jpa.show-sql = true
# update为每次运行都更新，(create为每次运行都重新创建)
spring.jpa.hibernate.ddl-auto = update
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
# 修改数据库端口号(e.g 3306)，数据库名(e.g gray_blog_test)，其他可选
spring.datasource.url = jdbc:mysql://localhost:3306/gray_blog_test?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
# 账户名
spring.datasource.username = root
# 账户密码
spring.datasource.password = admin
spring.datasource.driverClassName = com.mysql.jdbc.Driver
```

- Redis配置
```yaml
# Redis数据库索引（默认为0）
spring.redis.database = 0
# Redis服务器地址
spring.redis.host = 127.0.0.1
# Redis服务器连接端口
spring.redis.port = 6379
# 连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle = 8
# 连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle = 0
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active = 8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait = 1
# 连接超时时间（毫秒）
spring.redis.timeout = 5000
```

- canal配置
```
# canal服务器地址
canal.client.host = 127.0.0.1
# canal服务器端口
canal.client.port = 11111
# 如果有账户密码的话
# canal.client.user-name = 
# canal.client.password = 
canal.client.destination = example
# 监听过滤器， .*\\..* 表示监听所有数据库的所有表
canal.client.filter = .*\\..*
# 重试次数，监听过程中出现异常时的重试次数
canal.client.retry-count = 10
# 监听间隔（毫秒）
canal.client.acquire-interval = 2000
# 单次提取日志数
canal.client.batch-size = 5
```

- 个人信息配置（显示在博客对应位置）
```yaml
# 昵称
web.config.name = Gray
# 爱好（数量可删减）
web.config.hobby[0] = Programming
web.config.hobby[1] = Reading
web.config.hobby[2] = Game
web.config.hobby[3] = Basketball
# 性格（数量可删减）
web.config.character[0] = Sunny
web.config.character[1] = Modest
# github网址
web.config.github = https://github.com/beself-zjh
# 微信
web.config.we-chat = zjh970830
# QQ
web.config.qq = 215301160
# 邮箱
web.config.email = 20120461@bjtu.edu.cn
# 博客存储路径（文件夹），用于存取博客文件
web.config.blog-dir-path = C:\\Users\\ZJH\\Desktop\\blog
# 文章配图存储路径（文件夹），用于存取文章中上传的图片
web.config.blog-img-dir-path = C:\\Users\\ZJH\\Desktop\\blog\\ref
# 网站名称
web.config.web-name = Gray
# 个人简介（由于乱码问题，此处将汉字从utf-8转Unicode）
web.config.introduction = \u4e07\u4e08\u9ad8\u697c\u5e73\u5730\u8d77\uff0c\u52ff\u5728\u6d6e\u6c99\u7b51\u9ad8\u53f0\u3002
# 最新文章推荐模块最大文章展示数量
web.config.new-blog-num = 5
# 单页文章数量
web.config.blog-num-per-page = 5
# 备案相关（显示在网页底部）
# 所在省简称 e.g 京（由于乱码问题，此处将汉字从utf-8转Unicode）
web.config.province = \u4eac
# ICP备案码
web.config.icp = 0000000000
# 公安备案码
web.config.code = 000000000000
```

## 资源配置
进入文件夹`src/main/resources/static/img`
- alipay.png 支付宝付款码
- avatar.png 头像
- webb.png 背景
- wechat.jpg 微信二维码
将以上图片替换为自己的图片，文件名不要变。

## 项目部署
以下为STS4（Spring Tool Suite 4）编译器上的打包过程
1. 打开项目；
2. 清理打包记录，右键项目 → Run As → Maven clean；
![](https://github.com/beself-zjh/gray-blog/blob/main/doc/maven_clean.png)
![](https://github.com/beself-zjh/gray-blog/blob/main/doc/maven_clean_success.png)
3. 打包，右键项目 → Run As → Maven install；
![](https://github.com/beself-zjh/gray-blog/blob/main/doc/maven_install.png)
![](https://github.com/beself-zjh/gray-blog/blob/main/doc/maven_install_success.png)
4. 如果出现类似下面这种打包失败的情况，请将`src/test/java`下的所有代码注释掉。
![](https://github.com/beself-zjh/gray-blog/blob/main/doc/maven_install_failure.png)

## [更新日志](https://github.com/beself-zjh/gray-blog/blob/main/doc/Log.md)