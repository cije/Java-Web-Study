# web服务器软件-tomcat

## web相关概念

  1. 软件架构
      1. C/S：客户端/服务器端
      2. B/S：浏览器/服务器端
  2. 资源分类
      1. 静态资源：所有用户访问后，得到的结果是一样的，成为静态资源。可以直接被浏览器解析。
          - 如：html，css，JavaScript
      2. 动态资源：每个用户访问相同资源后，得到的结果可能不一样，称为动态资源。动态资源被访问后，需要先转换为静态资源，在返回给浏览器。
          - 如：servlet/jsp，php，asp......
  3. 网络通信三要素
      1. IP：电子设备（计算器）在网络中的唯一标识
      2. 端口：应用程序在计算机中的唯一标识。
      3. 传输协议：规定了数据传输的规则
          - 基础协议：
            1. tcp：安全协议，三次握手。速度稍慢
            2. udp：不安全协议。速度快

## web服务器软件

- 服务器：安装了服务器软件的计算机

- 服务器软件：接受用户的请求，处理请求，做出响应

- web服务器软件：接受用户的请求，处理请求，做出响应

	- 在web服务器软件中，可以部署web项目，让用户通过浏览器来访问这些项目
	- web容器

- 常见的java相关的web服务器软件

	- webLogic：oracle公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费
	- webSphere：IBM公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费
	- JBOSS：JBOSS公司的，大型的JavaEE服务器，支持所有的JavaEE规范，收费
	- Tomcat：Apache基金组织，中小型的JavaEE服务器，仅仅支持少量的JavaEE规范，开源，免费

- JavaEE：Java语言在企业开发中使用的技术规范的总和，一共规定了13项大的规范

- Tomcat：web服务器软件

	![image-20200702190300662](https://raw.githubusercontent.com/cije/images/master/img/20200702190503.png)

	- 静态形目合动态目录

		- 目录结构

			- java动态项目的目录结构

			```
			  -- 项目的根目录
			
			  ​	  -- WEB-INF目录
			
			  ​		  -- web.xml：web项目的核心配置文件
			
			  ​		-- classes目录：放置字节码文件的目录
			
			  ​		-- lib目录：防止依赖的jar包
			```