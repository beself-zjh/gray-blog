# gray-blog

1. 2021.10.13  

Question : 多对多实体类在存入redis时，无限序列化问题
Solution : 尝试JackSon中的 @JsonBackReference/@JsonManagedReference/@JsonIdentityInfo，在多对一和一对多中有效，但多对多依然无法解决，最终决定使用@JsonIngore忽略相应field