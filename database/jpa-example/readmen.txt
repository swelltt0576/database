这是一个关于JPA的演示程序。
《Java Persistence with JPA》
【环境配置】
eclipselink

【执行步骤】
1、启动derby服务器
java  -Dderby.system.home=. -jar %DERBY_HOME%\lib\derbyrun.jar server start

2、执行建库脚本
java -cp %DERBY_HOME%\lib\derbyrun.jar -Dij.connection.jpatest=jdbc:derby://localhost:1527/jpatest;create=true;user=jpatest;password=jpatest org.apache.derby.tools.ij script.sql


3、执行程序
mvn

4、关闭derby服务器
java  -Dderby.system.home=. -jar %DERBY_HOME%\lib\derbyrun.jar server shutdown -user jpatest -password jpatest

5、结束

【注意】
windows执行
derby.properties设置用户名密码均为jpatest，如下:
derby.user.jpatest=jpatest