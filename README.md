# 毕业设计选题系统

## 项目简介

毕业设计选题系统是一个为高校学生、教师和管理员提供毕业设计选题管理服务的平台。系统旨在解决传统选题方式中的信息不透明、匹配效率低下和管理复杂等问题，提供一个高效、透明、规范的选题平台。

## 功能概述

### 学生端功能

- 浏览和搜索可用毕业设计题目
- 提交选题申请
- 查看选题结果和历史记录
- 管理个人信息

### 教师端功能

- 发布和管理毕业设计题目
- 审核学生选题申请
- 查看和管理选题学生
- 管理个人信息

### 管理员端功能

- 管理用户账户
- 审核题目
- 配置系统参数
- 生成和查看统计报表

## 技术栈

- **前端**：Vue.js 2
- **后端**：SpringBoot2 + mybatis-plus
- **数据库**：MySQL
- **版本控制**：Git
- **代码托管**：GitHub

## 环境配置

### 开发环境

- **操作系统**：Windows 10/11
- **IDE**：Visual Studio Code、IntelliJ IDEA
- **数据库**：MySQL 8.0

### 运行环境

- **服务器操作系统**：CentOS 7 或 Windows10/11
- **数据库服务器**：MySQL 8.0
- **java**：1.8
- **node.js**：18.16.0
- **npm**：9.5.1

## 安装与启动

### 1. 克隆项目

```shell
git clone https://github.com/yi-shui-a/Thesis-Selection-System.git
cd Thesis-Selection-System
```

### 2. 配置数据库

在 `application.yml` 文件中配置数据库连接信息：

```yaml
datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/thesis_select_system
    username: root
    password: root
```

### 3. 启动后端服务

使用 Maven 构建并启动后端服务：

```shell
# 使用 Maven
mvn clean install
mvn spring-boot:run
```

### 4. 启动前端服务

进入前端项目目录，安装依赖并启动前端服务：

```shell
cd sys_view
npm install
npm run serve
```

### 5. 访问系统

打开浏览器，访问 `http://localhost:8080`，即可进入毕业设计选题系统。

## 测试

### 测试账号

**管理员账号**：

- 用户名：1
- 密码：1

**教师账号**：

- 用户名：1
- 密码：1

+ 账号可通过管理员端系统创建，密码默认为123456。

**学生账号**：

+ 通过管理员端系统创建，密码默认为123456。