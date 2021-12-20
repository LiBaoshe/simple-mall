# simple-mall
简易商城学习 Demo，仅仅作为各种分库分表、一些工具的使用等学习使用，关注点不是商城具体逻辑。

## 模块说明：

- common ：公共模块，公共操作，mybatis-plus 代码生成器等。
- goods：商品模块，使用 AbstractRoutingDataSource 实现读写分离配置。
- order：订单模块，测试不同方向插入 100 万订单的效率。
- user：用户模块，配置 mybatis-generator maven 插件生成代码。
- shardingsphere-jdbc-demo 使用 ShardingSphere-JDBC 实现读写分离配置。

