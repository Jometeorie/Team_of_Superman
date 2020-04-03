# Team_of_Superman
项目管理课程大作业

## 项目简介
本项目为一个简单的图书管理系统，分为Admin，Librarian和Reader三个模块，具体要求见[Mandarin_2020XDU (optimized).pdf](https://github.com/frozenlalala/Team_of_Superman/blob/master/Mandarin_2020XDU%20(optimized).pdf)

## 技术实现
目前前端打算采用静态页面，运用thymeleaf渲染，前端太难了（逃...，SpringBoot作为后端，MySQL作为数据库开发。

开发过程中，遇到问题及时回滚。数据库的相关操作，包括建表，直接的增删改查等请将sql语言写成.sql文件并及时保存在sql文件夹中，且注意区分大小写（有些版本的MySQL大小写敏感），且格式尽量保持一致（见sql中的demo）

## 注意事项
### Git使用
开发过程中，尽量不要直接在主分支上进行开发，先用'git checkout dev'切换到dev分支，再'git push origin dev'提交， **千万不要强制push到master分支上，这样很有可能会造成不必要的版本冲突。** 提交完成后上github上对比一下dev分支和master分支并合并，若合并时出现冲突需要即使选择正确的版本并解决冲突，或者也可以私聊我，反正无论如何（更新README除外）不要直接在master分支上提交qaq。

### 项目主体情况
* 项目没有采用前后端分离的架构方式，而是使用较为传统的后端渲染前段的手法
* 前段代码放在[library/src/main/resources/templates](https://github.com/frozenlalala/Team_of_Superman/blob/master/library/src/main/resources/templates)中（css/js/image文件放在同级static文件夹下）
* 后端路由代码在[libary/src/main/java/com/example/library/control](https://github.com/frozenlalala/Team_of_Superman/blob/master/library/src/main/java/com/example/library/control)中（尽量一个路由一个文件）
* JDBC接口代码放在[library/src/main/java/com/example/library/database/src/team/library](https://github.com/frozenlalala/Team_of_Superman/blob/master/library/src/main/java/com/example/library/database/src/team/library)中
* 数据库脚本文件在library同级的[sql文件夹](https://github.com/frozenlalala/Team_of_Superman/blob/master/sql)中
* 全局变量在[library/src/main/resources](https://github.com/frozenlalala/Team_of_Superman/blob/master/library/src/main/resources)下的application.properties和druid.properties中

### 其他事项
尽量保持格式统一整齐，例如路由为/login，就将文件写在control/LoginControl.java中，前段代码也应对应为login.html放在对应templates文件夹中

端口暂时用的是8888，如果显示端口占用了可以先把8888端口的进程kill掉，本地运行时需要安装MySQL8，Java环境以及SpringBoot框架，需要的依赖都写在pom.xml中，如果你需要添加新的依赖，也请将内容写在里面。运行前需要修改一些全局变量，例如数据库的密码等。

第一次运行时，你的IDE会自动帮你安装pom.xml里的一些必要依赖，因此可能会等较长一段时间，与此同时，你也需要在MySQL中执行create_database.sql脚本，执行命令为souce ./create_database.sql，或者也可以复制粘贴进去。之后便可以运行DemoApplication.java，如果运行仍有什么问题可以私聊我。

另外，对github不太熟悉的话可以在test文件夹中进行尝试

冲冲冲！

![image](https://github.com/frozenlalala/Team_of_Superman/raw/master/images/渴望力量.png)
