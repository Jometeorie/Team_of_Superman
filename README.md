# Team_of_Superman
项目管理课程大作业

## 项目简介
本项目为一个简单的图书管理系统，分为Admin，Librarian和Reader三个模块

## 技术实现
目前前端打算采用静态页面，运用thymeleaf渲染，前端太难了（逃...，SpringBoot作为后端，MySQL作为数据库开发。

开发过程中，遇到问题及时回滚。数据库的相关操作，包括建表，直接的增删改查等请将sql语言写成.sql文件并及时保存在sql文件夹中，且注意区分大小写（有些版本的MySQL大小写敏感），且格式尽量保持一致（见sql中的demo）

## 注意事项
开发过程中，尽量不要直接在主分支上进行开发，先用'git checkout dev'切换到dev分支，再'git push origin dev'提交。提交前用git diff查看一下提交的东西是不是都是自己需要修改的内容。

项目主体在library中，前段代码放在library/src/main/resources/templates中（css文件放在同级static文件夹下），后端路由代码在library/src/main/java/com/example/library/control中（尽量一个路由一个文件）

尽量保持格式统一整齐，例如路由为/login，就将文件写在control/LoginControl.java中，前段代码也应对应为login.html放在对应templates文件夹中

端口暂时用的是8888，如果显示端口占用了可以先把8888端口的进程kill掉

对github不太熟悉的话可以在test文件夹中进行尝试

最后，冲冲冲！

![image](https://github.com/frozenlalala/Team_of_Superman/raw/master/images/渴望力量.png)
