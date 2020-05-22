URL 是什么? URI 又是什么？两者有什么区别？带着问题进入到下面的学习。

# 通过 @PathVairable 引发思考

项目开发过程中在 Spring MVC 中的 Controller 层接收参数的时候，有使用到 `@PathVairable ` 注解，顾名思义是可变路径的意思。看一下`@PathVairable `在[官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-requestmapping)里的使用例子：

```java
@GetMapping("/owners/{ownerId}/pets/{petId}")
public Pet findPet(@PathVariable Long ownerId, @PathVariable Long petId) {
    // ...
}
```

文档中有下面一段话

> Captured URI variables can be accessed with `@PathVariable`

翻译后

> 捕获的 URI 变量可以通过 @PathVariable 访问

我突然沉思，原来 RequestMapping 中的 value 是 `URI`，好家伙，我一直以为这个也是 URL 呢。原来我连 URI 和 URL 是什么都不知道，惭愧惭愧。痛定思痛后，我开始进行学习。

# URI 是什么

**URI** 是统一资源标识符，全拼（Uniform Resource **Idenitifier**）。允许用户对网络中的资源通过特定的协议进行交互操作。可以唯一标识一个资源。

比如上面的代码，路径的 `ownerId` 和 `petId` 就是 URI 变量，是唯一标识的。

[维基百科 URI](https://zh.wikipedia.org/wiki/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E6%A0%87%E5%BF%97%E7%AC%A6)



# URL 是什么

**URL** 是统一资源定位符，全拼 （Uniform Resource **Locator**）。可以提供找到该资源的路径，所以可以标识一个资源，所以 URL 又是 URI 的子集。

[维基百科 URL](https://zh.wikipedia.org/wiki/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%AC%A6#cite_note-2)

# URI 和 URL 的区别是什么

