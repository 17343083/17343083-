# 区块链大作业

* 罗逸群 17343083

### 工程结构

```
| - front-end // 前端页面(React框架)
| - back-end // 后端服务器(java spring boot框架)
| - chain-end // fisco-bcos
```

### 合约代码位置

* `back-end/src/main/resources`

### 启动方式

链端：

*  `cd fisco/nodes/127.0.0.1`
* `bash start_all.sh`
* 链端的端口设置与fisco-bcos官网搭建链的端口设置一致

前端：

* 打开`front-end`文件夹下的`index.html`
* 点开后会跳转到进入页面，点击右上角图标 -> log out后，注册账户
* 登录

后端：

* 启动的端口在4500，可通过修改`application.properties`文件修改服务器端口
* 执行`./gradlew build`编译程序
* 或者推荐直接将项目通过`eclipse`导入后，将包`org.fisco.bcos.repict.client`下的`RepictClient.java`右键选择`Run `即可



# 17343083-
# 17343083-
