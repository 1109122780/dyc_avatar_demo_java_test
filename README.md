<div align="center">
   <h1> AI分身服务 java demo</h1>
该系统代码主要实现的是简单的AI分身服务调用样例，包括大模型调用，开场白，外域api等。
</div>

## 说明：

本demo使用开源springboot框架编写，仅仅只实现了最简单的demo调用，代码仅做参考。创作者可根据官方文档实现符合自己业务场景的代码，后续自行保证系统稳定性。

## ChangeLog：

版本: v1.0.0

- 提供AI分身服务调用样例。

## 项目结构

```shell
. #篇幅有限，只展示主要部分
├── src
│   └── main          
│     └── java              
│       └── douyin.avatar.avatar_demo_java
│         └── aspect                     切面代码
│         └── caller                     调用外部api的代码
│         └── config                     系统配置代码
│         └── controller                 控制层服务代码
│         └── Enum                       枚举
│         └── filter                     系统过滤器
│         └── request                    控制层的请求结构体
│         └── response                   控制层的返回结构体
│         └── service                    系统服务层，主要是写服务代码逻辑
│         └── util                       公共工具代码
│         └── AvatarDemoJavaApplicatioin 启动代码
├── pom.xml                 maven仓库管理代码
├── build-and-deploy.sh     编译和部署脚本 
└── Dockerfile

```

# 项目部署

> 部署方式有两种，一种是直接在本地编译和部署推送，另一种是在抖音云平台配置git hook进行自动部署。

#### 部署方式1

1. 本地先安装npm命令，并安装docker。

```shell
2. npm install -g @open-dy/cloud-cli --registry=https://registry.npmmirror.com/
    # 安装完成后，终端会支持 `dycloud` 的根命令。
    # 使用 dycloud --version 校验是否安装成功。
    dycloud --version
```

```shell 
3. sh build-and-deploy.sh
```

#### 部署方式2

[抖音云平台git部署](https://cloud.douyin.com/app/deploy/publish)
