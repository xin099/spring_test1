# spring_test1
https://blog.csdn.net/wwzzzzzzzzzzzzz/article/details/126283912?spm=1001.2100.3001.7377&utm_medium=distribute.pc_feed_blog_category.none-task-blog-classify_tag-8-126283912-null-null.nonecase&depth_1-utm_source=distribute.pc_feed_blog_category.none-task-blog-classify_tag-8-126283912-null-null.nonecase

## 问题描述：
前端发送username和password，后端接收为null
前端：
```javascript
$.ajax({
    url: "/login",
    method: "POST",
    data: JSON.stringify({username: username.value.trim(), password: password.value.trim()}),
    contentType: "application/json;charset=utf-8",
    success: function (data, status) {
        if (data.status == 1) {
            location.assign("index.html");
        } else {
            alert(data.message);
            username.value = "";
            password.value = "";
            username.focus();
        }
    }
})
```
后端：
```java
    @RequestMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request) {
        User user = userService.selectByName(username);

        if (user == null) {
            log.info("登录失败！");
            System.out.println("登录失败！");
            return new User();
        } else {
            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return new User();
            }
            //匹配成功
            request.getSession().setAttribute(Constant.USER_SESSION_KEY, user);
            return user;
        }

    }
```
## 问题分析
### 1、contentType
前端ajax中的contentType 默认为：application/x-www-form-urlencoded
修改为：contentType: "application/json;charset=utf-8",

```java
//传JSON字符串+@RequestBody接收 切记只有加了这个才能正常的接受json字符串
@RequestMapping(value="insertShop", method = RequestMethod.POST)
@ResponseBody
public void insertShop( @RequestBody String shopName,
HttpServletRequest request){
System.out.println(shopName);
}
```

### 2、@RequestBody 和 @RequestParam

注解**@RequestBody**接收的参数是来自requestBody中，即请求体。一般用于处理非 Content-Type: application/x-www-form-urlencoded编码格式的数据，比如：application/json、application/xml等类型的数据。

就application/json类型的数据而言，使用注解@RequestBody可以将body里面所有的json数据传到后端，后端再进行解析。

注解**@RequestParam**接收的参数是来自requestHeader中，即请求头。通常用于GET请求
```java

```

# 目前两个问题
## Canvas画布问题

## WebSocket 连接问题
