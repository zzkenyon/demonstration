
### 1. 前言

在编写项目代码时，我们要求更灵活的配置，更好的模块化整合。在 Spring Boot 项目中，为满足以上要求，我们将大量的参数配置在 application.properties 或 application.yml 文件中，通过 `@ConfigurationProperties` 注解，我们可以方便的获取这些参数值

### 2. 使用 @ConfigurationProperties 配置模块

假设我们正在搭建一个发送邮件的模块。在本地测试，我们不想该模块真的发送邮件，所以我们需要一个参数来「开关」 disable 这个功能。另外，我们希望为这些邮件配置一个默认的主题，这样，当我们查看邮件收件箱，通过邮件主题可以快速判断出这是测试邮件

在 application.yml文件中创建这些参数:

```yaml
app: 
  mail:
    enable: true
	default-subject: This is a Test
```

我们可以使用 `@Value` 注解或着使用 Spring `Environment` bean 访问这些属性，是这种注入配置方式有时显得很笨重。我们将使用更安全的方式(`@ConfigurationProperties` )来获取这些属性

```java
@ConfigurationProperties(prefix = "app.mail")
@Data
public class MailProperties {
    private Boolean enable = Boolean.TRUE;
    private String defaultSubject;
    /**
     * 获取列表类型属性
     */
    private List<String> smtpServer;
}
```

`@ConfigurationProperties` 的基本用法非常简单:我们为每个要捕获的外部属性提供一个带有字段的类。请注意以下几点:

- 前缀定义了哪些外部属性将绑定到类的字段上
- 根据 Spring Boot 宽松的绑定规则，类的属性名称必须与外部属性的名称匹配
- 我们可以简单地用一个值初始化一个字段来定义一个默认值
- 类本身可以是包私有的
- 类的字段必须有公共 setter 方法

> **Spring 宽松绑定规则 (relaxed binding)**：
>
> Spring使用一些宽松的绑定属性规则。因此，以下变体都将绑定到 hostName 属性上:

```yaml
app:
  mail: 
    hostName: localhost
    host-name: localhost
    host_name: localhost
    HOST_NAME: localhost
```



如果我们将 MailProperties 类型的 bean 注入到另一个 bean 中，这个 bean 现在可以以类型安全的方式访问那些外部配置参数的值。

但是，我们仍然需要让 Spring 知道我们的 @ConfigurationProperties 类存在，以便将其加载到应用程序上下文中。

### 3. 激活 @ConfigurationProperties

对于 Spring Boot，创建一个 MailProperties 类型的 bean，我们可以通过下面几种方式将其添加到应用上下文中

#### 3.1 方式一

首先，我们可以通过添加 @Component 注解让 Component Scan 扫描到

```java
@ConfigurationProperties(prefix = "app.mail")
@Component
public class MailProperties {
    ...
}
```

很显然，只有当类所在的包被 Spring `@ComponentScan` 注解扫描到才会生效，默认情况下，该注解会扫描在主应用类下的所有包结构

#### 3.2 方式二

我们也可以通过 Spring 的 Java Configuration 特性实现同样的效果:

```java
@Configuration
public class MailConfiguration {
    @Bean
    public MailProperties mailProperties(){
        return new MailProperties();
    }
}
```

只要 MailConfiguration 类被 Spring Boot 应用扫描到，我们就可以在应用上下文中访问 MailProperties bean

#### 3.3 方式三

我们还可以使用 `@EnableConfigurationProperties` 注解让我们的类被 Spring Boot 所知道，在该注解中其实是用了`@Import(EnableConfigurationPropertiesImportSelector.class)` 实现，大家可以看一下

```java
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class Properties{
    
}
```

#### 3.4 最佳方式是什么

所有上述方法都同样有效。然而，我建议模块化你的应用程序，并让每个模块提供自己的`@ConfigurationProperties` 类，只提供它需要的属性，就像我们在上面的代码中对邮件模块所做的那样。这使得在不影响其他模块的情况下重构一个模块中的属性变得容易。

因此，我不建议在应用程序类本身上使用 `@EnableConfigurationProperties`，如许多其他教程中所示，是在特定于模块的 @Configuration 类上使用`@EnableConfigurationProperties`，该类也可以利用包私有的可见性对应用程序的其余部分隐藏属性。

所以是**第二种**。



### 4. 特殊情况操作

#### 4.1 类型不匹配的属性

如果我们在 application.properties 属性上定义的属性不能被正确的解析会发生什么？假如我们为原本应该为布尔值的属性提供的值为 'foo':

```yaml
app:
   mail:
     enable: foo
```

默认情况下，Spring Boot 将会启动失败，并抛出异常:

```
Failed to bind properties under 'myapp.mail.enabled' to java.lang.Boolean:

    Property: myapp.mail.enabled
    Value: foo
    Origin: class path resource [application.properties]:1:20
    Reason: failed to convert java.lang.String to java.lang.Boolean
```

当我们为属性配置错误的值时，而又不希望 Spring Boot 应用启动失败，我们可以设置 `ignoreInvalidFields` 属性为 true (默认为 false)，like this：

```java
@ConfigurationProperties(prefix = "app.mail",ignoreInvalidFields = true)
@Data
public class MailProperties {
    ...
}
```

这样，Spring Boot 将会设置 enabled 字段为我们在 Java 代码里设定好的默认值。如果我们没有设置默认值，enabled 将为 null，因为这里定义的是 boolean 的包装类 Boolean



#### 4.2 未知的属性

如果我们在 application.yml文件提供了 MailProperties 类中没有字段的属性会发生什么？

```yaml
app:
   mail:
     enable: true
     default-subject: adaf
     unknow-property: unknow
```

默认情况下，Spring Boot 会忽略那些不能绑定到 `@ConfigurationProperties` 类字段的属性

然而，当配置文件中有一个属性实际上没有绑定到 `@ConfigurationProperties` 类时，我们可能希望启动失败。也许我们以前使用过这个配置属性，但是它已经被删除了，这种情况我们希望被触发告知手动从 application.properties 删除这个属性

为了实现上述情况，我们仅需要将 `ignoreUnknownFields` 属性设置为 false (默认是 true)

```java
@ConfigurationProperties(prefix = "app.mail",ignoreUnknownFields = false)
@Data
public class MailProperties {
    ...
}
```

现在，应用启动时，控制台会反馈我们异常信息

```
Binding to target [Bindable@cf65451 type = com.example.configurationproperties.properties.MailModuleProperties, value = ‘provided‘, annotations = array<Annotation>[@org.springframework.boot.context.properties.ConfigurationProperties(value=myapp.mail, prefix=myapp.mail, ignoreInvalidFields=false, ignoreUnknownFields=false)]] failed:

    Property: myapp.mail.unknown-property
    Value: foo
    Origin: class path resource [application.properties]:3:29
    Reason: The elements [myapp.mail.unknown-property] were left unbound.
```

> 弃用警告??(Deprecation Warning)
> `ignoreUnknownFields` 在未来 Spring Boot 的版本中会被标记为 deprecated，因为我们可能有两个带有 `@ConfigurationProperties` 的类，同时绑定到了同一个命名空间 (namespace) 上，其中一个类可能知道某个属性，另一个类却不知道某个属性，这样就会导致启动失败

#### 4.3 启动时校验属性值

如果我们希望配置参数在传入到应用中时有效的，我们可以通过在字段上添加 `bean validation` 注解，同时在类上添加 `@Validated` 注解

```java
@ConfigurationProperties(prefix = "app.mail",ignoreInvalidFields = true)
@Data
@Validated
public class MailProperties {
    private Boolean enable = Boolean.TRUE;
    @NotEmpty
    private String defaultSubject;
   	...
}
```

如果我们忘记在 application.yml设置 defaultSubject 为空：

```yaml
app:
   mail:
     default-subject: 
```

应用启动时，我们将会得到 `BindValidationException`

```
Binding to target org.springframework.boot.context.properties.bind.BindException: Failed to bind properties under ‘myapp.mail‘ to com.example.configurationproperties.properties.MailModuleProperties failed:

    Property: myapp.mail.enabled
    Value: null
    Reason: must not be null

    Property: myapp.mail.defaultSubject
    Value: null
    Reason: must not be empty
```

当然这些默认的验证注解不能满足你的验证要求，我们也可以自定义注解



#### 4.4 复杂属性类型

##### 4.4.1 List 和 Set		

假如，我们为邮件模块提供了一个 SMTP 服务的列表，我们可以添加该属性到 MailModuleProperties 类中

```java
@ConfigurationProperties(prefix = "app.mail")
@Data
public class MailProperties {
    ...
    /**
     * 获取列表类型属性
     */
    private List<String> smtpServer;
}
```

我们应该在application.yml文件中这样配置：

```yaml
app:
  mail:
    smtp-server:
      - 10.0.23.12
      - 10.0.23.61
      - 10.0.23.89
```

set 集合也是这种方式的配置方式，不再重复书写。另外YAML 是更好的阅读方式，层次分明，所以在实际应用中更推荐大家使用该种方式做数据配置

##### 4.4.2 Duration

Spring Boot 内置支持从配置参数中解析 durations (持续时间)，[官网文档](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-conversion-duration) 给出了明确的说明

```java
@ConfigurationProperties(prefix = "app.mail")
@Data
public class MailProperties {
   	...
    private DataSize size;
    private Duration time;
}
```

我们既可以配置毫秒数数值，也可配置带有单位的文本:

```yaml
app:
  mail:
    enable: false
    default-subject: This is dev env
    smtp-server:
      - 10.0.23.12
      - 10.0.23.61
      - 10.0.23.89
    size: 20KB
    time: 2s
```

官网上已明确说明，配置 duration 不写单位，默认按照毫秒来指定，我们也可已通过 @DurationUnit 来指定单位:

```java
@ConfigurationProperties(prefix = "app.mail")
@Data
public class MailProperties {
   	...
    @DurationUnit(chronoUnit.SECONDS)
    private Duration time;
}
```

常用单位如下:

- `ns` for nanoseconds (纳秒)
- `us` for microseconds (微秒)
- `ms` for milliseconds (毫秒)
- `s` for seconds (秒)
- `m` for minutes (分)
- `h` for hours (时)
- `d` for days (天)

##### 4.4.3  DataSize

与 Duration 的用法一毛一样，默认单位是 byte (字节)，可以通过 @DataSizeUnit 单位指定:

```java
@ConfigurationProperties(prefix = "app.mail")
@Data
public class MailProperties {
   	...
    @DataSizeUnit(DataUnit.MEGABYTE)
    private DataSize size;   
}
```

但是，我测试的时候打印出来结果都是以 B (bytes) 来显示

常见单位如下:

- `B` for bytes
- `KB` for kilobytes
- `MB` for megabytes
- `GB` for gigabytes
- `TB` for terabytes

#### 4.5 自定义类型

有些情况，我们想解析配置参数到我们自定义的对象类型上，假设，我们我们设置最大包裹重量:

```yaml
app:
  mail:
    max-weight: 1KG
```

在 MailProperties 中添加 Weight 属性

```java
@ConfigurationProperties(prefix = "app.mail")
@Data
public class MailProperties {
   	...
    private Weight maxWeight;   
}
```

我们可以模仿 DataSize 和 Duration 创造自己的 converter (转换器)

```java
public class WeightConvert implements Converter<String, Weight> {
    @Override
    public Weight convert(String s) {
        /*
        ...
         */
        return null;
    }
}
```

将其注册到 Spring Boot 上下文中

```java
@Configuration
public class MailConfiguration {
    @Bean
    @ConfigurationPropertiesBinding
    public WeightConvert weightConvert(){
        return new WeightConvert();
    }
}
```

`@ConfigurationPropertiesBinding` 注解是让 Spring Boot 知道使用该转换器做数据绑定

### 5. 使用 Spring Boot Configuration Processor 完成自动补全

我们向项目中添加依赖:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

重新 build 项目之后，configuration processor 会为我们创建一个 JSON 文件：

```json
文件路径：target/classes/META-INF/spring-configuration-metadata.json
{
  "groups": [
    {
      "name": "app.mail",
      "type": "com.pd.properties.properties.MailProperties",
      "sourceType": "com.pd.properties.properties.MailProperties"
    },
    {
      "name": "app.message",
      "type": "com.pd.properties.properties.MessageProperties",
      "sourceType": "com.pd.properties.properties.MessageProperties"
    }
  ],
  "properties": [
    {
      "name": "app.mail.default-subject",
      "type": "java.lang.String",
      "description": "默认主题.",
      "sourceType": "com.pd.properties.properties.MailProperties"
    },
    {
      "name": "app.mail.enable",
      "type": "java.lang.Boolean",
      "description": "邮件功能开关.",
      "sourceType": "com.pd.properties.properties.MailProperties",
      "defaultValue": true
    },
    {
      "name": "app.mail.smtp-server",
      "type": "java.util.List<java.lang.String>",
      "description": "获取列表类型属性",
      "sourceType": "com.pd.properties.properties.MailProperties"
    },
    {
      "name": "app.message.from",
      "type": "java.lang.String",
      "description": "发送方.",
      "sourceType": "com.pd.properties.properties.MessageProperties"
    },
    {
      "name": "app.message.size",
      "type": "org.springframework.util.unit.DataSize",
      "description": "信息最大大小.",
      "sourceType": "com.pd.properties.properties.MessageProperties"
    },
    {
      "name": "app.message.time",
      "type": "java.time.Duration",
      "sourceType": "com.pd.properties.properties.MessageProperties"
    }
  ],
  "hints": []
}
```

这样，当我们在 application.properties 和 application.yml 中写配置的时候会有自动提醒

自动生成的peoperty信息有两种获取途径

- spring从properties bean中自动搜集，description对应字段注释，type对应字段类型，有默认值的字段生成default-vaule等等
- 程序员手动编写src\main\resources\META-INF\additional-spring-configuration-metadata.json文件

程序build的时候，spring将结合上面两种方式生成target/classes/META-INF/spring-configuration-metadata.json文件