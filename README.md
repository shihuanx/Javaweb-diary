# Javaweb-diary
一个基于springboot框架的日记网站

技术栈：JavaSpringBoot Mysql Mybatis Jwt
采用 Controller Service Mapper 三层架构 
下面为接口说明：
1.登录接口
参数：手机号（11位）  密码：20位以下
![image](https://github.com/user-attachments/assets/2d89255c-a8db-47db-b9e0-a63095d2ce90)
2.添加日记接口 
参数：日记内容
![image](https://github.com/user-attachments/assets/0e5f65b6-b327-4c52-b2df-ee5602591868)
![image](https://github.com/user-attachments/assets/050a2e75-eff4-4ac9-9b2b-1b2d5bdd0a50)
3.修改日记接口
参数；修改后的内容     日记id
![image](https://github.com/user-attachments/assets/e2702257-f28e-40d1-be5d-5ccc3132381e)
4.删除日记接口 
参数：日记id 可批量删除
![image](https://github.com/user-attachments/assets/b4fced3f-53ba-4525-ac1a-3a5122172923)
5.查询日记接口
![image](https://github.com/user-attachments/assets/1beb7252-cdd3-410d-8d5f-f950cf4fbed8)
![image](https://github.com/user-attachments/assets/160af9ee-68f0-464e-9c6c-c4dbbdb8eb91)
6.添加标签接口
参数：日记id 标签内容 json格式 可批量添加标签
![image](https://github.com/user-attachments/assets/baf17a4b-0b2b-4563-b17c-36bef6888c74)
7.修改标签接口
参数：标签id 修改后的标签内容 
![image](https://github.com/user-attachments/assets/9e9848cc-8054-4c09-8ba0-b9865314779b)
8.删除标签接口
参数：标签id 可批量删除
![image](https://github.com/user-attachments/assets/0ea26fdc-5cc8-4869-a75d-8a768c86a9a5)
