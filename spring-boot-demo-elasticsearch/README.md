

将logback.xml添加到resources目录下，application.yml 添加配置

```yml
logging:
	config: classpath:logback.xml
```

以上配置会导致默认的日志配置实效，开发中对日志有需求需要在xml文件中添加配置。

添加依赖：

```xml
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>5.3</version>
</dependency>
```



在需要打审计日志的controller方法上添加注解 @Audit()

注解参数 operate 表示方法执行了什么操作，string类型，需要手动指定



审计日志通过logstashAppender写入到logstash，logstash处理之后上传到es服务器，

es可视化web站点kibana：http://10.0.12.72:5601



由于上传流程中没有配置kafka，当并发量大时，可能会出现tcp拆包现象，从而导致日志在logstash中json解析失败，在kibana中也能看到拆包之后的日志信息，后续如有需要再考虑加入kafka。

