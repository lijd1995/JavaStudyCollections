[官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-requestmapping)

匹配 URI 中的指定字符

- 如果在路径中有两个相同的 URI，取后者

```java
@ResponseBody
    @RequestMapping(value = "/getSignPrizeInfo/{activeSn}/{channel}/{session}/{channel}",method = RequestMethod.POST)
    public Object getSignPrizeInfo(@PathVariable(value = "activeSn",required = true) String activeSn,
                                        @PathVariable(value = "session",required = false) String session,
                                        @PathVariable(value = "channel",required = false) Integer channel,
                                        @PathVariable(value = "outId",required = true) Long outId){
        MagicSignDTO magicSignDTO = new MagicSignDTO(activeSn,session,channel,outId);
```

执行下面的操作

/getSignPrizeInfo/20200508091005518f29e/11/10055/123，得到的 channel 是 123。



- @PathVariable 设置成 true，如果在路径中没有，返回下面内容。

outId 设置成了 true，但是没有对应的 URI。会返回下面的结果

```java
org.springframework.web.bind.MissingPathVariableException: Missing URI template variable 'outId' for method parameter of type Long
	at org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver.handleMissingValue(PathVariableMethodArgumentResolver.java:101)
	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.handleMissingValue(AbstractNamedValueMethodArgumentResolver.java:221)
	
```



