# gray-blog

## 环境
- [JDK 11.0.12](https://www.oracle.com/java/technologies/downloads/#java11)
- [mysql-community 8.0.26.0](https://dev.mysql.com/downloads/windows/installer/8.0.html)
- [Redis 3.2.100](https://github.com/microsoftarchive/redis/releases)
- [canal.deployer 1.1.5](https://github.com/alibaba/canal/releases)

1. 2021.10.13  

Question : 多对多实体类在存入redis时，无限序列化问题
Solution : 尝试JackSon中的 @JsonBackReference/@JsonManagedReference/@JsonIdentityInfo，在多对一和一对多中有效，但多对多依然无法解决，最终决定使用@JsonIngore忽略相应field