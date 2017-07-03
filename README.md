# spring-boot
spring-boot  + mybatis + swagger2 + thymeleaf + 热部署

## mybatis自动生成(idea飘过)

generatorConfig.xml文件中
修改数据库连接信息,url,username,password; 最后一行table元素中修改自己的要生成的表名及实体名称<br>
选择pom.xml文件，击右键先择Run AS —> Maven Build…<br>
在Goals框中输入 mybatis-generator:generate —> Run就好了,在工程上刷新
