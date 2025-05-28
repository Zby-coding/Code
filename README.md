# 用户管理中心后端系统

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)

基于 Spring Boot + MyBatis-Plus 构建的用户管理系统，提供用户注册、登录、权限管理等功能。

## 功能特性
- 🛡️ 用户注册与密码加密（MD5加盐）
- 🔑 会话管理（基于HTTP Session）
- 👮 管理员权限控制（用户搜索/删除）
- 📦 数据脱敏处理（敏感信息过滤）
- 🧪 参数校验与统一异常处理

## 技术栈
- **核心框架**: Spring Boot 2.7
- **数据层**: MyBatis-Plus + MySQL
- **安全校验**: Apache Commons Lang3
- **工具库**: Lombok + Hutool

## 快速开始

### 环境要求
- JDK 1.8+
- MySQL 8.0+
- Maven 3.6+

### 数据库配置
1. 创建数据库：
```sql
CREATE DATABASE usercenter_db;
