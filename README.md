# Reggie 外卖平台

## 项目简介
该项目是一个外卖管理平台，分为后端和前端两部分。后端使用 Java 编写，采用 Spring Boot 框架构建；前端包含后台管理系统和用户端页面。

## 技术栈

* 后端: Java, Spring Boot, MyBatis Plus, MySQL
* 前端: Vue.js, Element UI, Vant
* 其他: Axios、JWT、Redis 等相关技术组件

## 目录结构说明

**reggie_take_out/
├── main/
│   ├── java/com/zby/reggie/        # Java 源码目录
│   │   ├── common/                 # 公共工具类及异常处理
│   │   ├── config/                 # 配置类（如跨域配置、MyBatis Plus 配置等）
│   │   ├── controller/             # 控制器层
│   │   ├── dto/                    # 数据传输对象
│   │   ├── entity/                 # 实体类
│   │   ├── filter/                 # 过滤器
│   │   ├── mapper/                 # Mapper 接口
│   │   ├── service/                # 业务逻辑层
│   │   ├── utils/                  # 工具类
│   │   └── ReggieApplication.java  # 启动类
│   └── resources/
│       ├── backend/                # 后台管理系统的静态资源（HTML、JS、CSS）
│       ├── front/                  # 用户端页面的静态资源
│       ├── img/                    # 图片资源
│       └── application.yml         # 配置文件
└── test/
└── java/com/zby/test/          # 测试代码目录**

## 功能模块

### 后端功能

* 用户权限管理
* 菜品分类管理
* 套餐管理
* 订单管理
* 地址管理
* 登录认证与权限控制


### 前端功能

#### 后台管理系统：

* 菜单管理
* 订单处理
* 用户管理
* 数据统计等

#### 用户端：

* 商品浏览
* 下单支付
* 订单跟踪
* 收货地址管理等

## 安装与启动

### 后端启动

1. 导入数据库脚本到 MySQL。
2. 修改 application.yml 中的数据库连接信息。
3. 使用 IDE 或 Maven 构建并运行 ReggieApplication.java。

#### 前端启动

1. 确保 Node.js 已安装。
2. 进入前端项目目录，执行 npm install 安装依赖。
3. 执行 npm run dev 启动开发服务器。

## API 文档

详见 /resources/backend/api/ 目录下的 .js 文件，这些文件定义了后端接口请求方式和参数

## 注意事项

* 确保 Redis 和 MySQL 服务已启动。
* 前端访问路径：http://localhost:8080/index.html
* 后端 API 地址：默认为 /api/**

## 开发者

* 作者: ZBY
* 邮箱: zby@example.com

## 版本记录

* v1.0 初始版本