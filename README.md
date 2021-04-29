### 一、技术栈

**1.后端：**

- 核心框架：SpringBoot 2.4.4
- 项目构建：JDK 1.8、Maven 3
- 持久层框架：MyBatis 2.1.2
- 模版框架：Thymeleaf
- 加密：MD5加密（目前运用在管理员登陆的密码上，有待优化）

**2.数据库**

- MySQL：5.7

**3.前端**

- JS框架：JQuery
- CSS框架：Semantic UI

前端技术主要在于运用。

### 二、数据库设计


**1.数据表：**

- b_blog (博客管理表)
- b_blog_tags (博客与标签关联表)
- b_comment (评论表)
- b_tag (标签表)
- b_type (类型表)
- b_user (用户表，目前仅支持管理员)

**2.实体关系**

博客实体关系

![image-20210423141217917](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423141217917.png)

评论实体关系

![image-20210423143409903](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423143409903.png)

- 博客和分类是多对一的关系：一个博客对应一个分类，一个分类可以对应多个博客
- 博客和用户是多对一的关系：一个博客对应一个用户，一个用户可以对应多个博客
- 博客和评论是一对多的关系：一个博客可以对应多个评论，一个评论对应一个博客
- 评论和回复是一对多的关系：一个评论可以对应多个回复，一个回复对应一个评论

**3.实体属性**

![image-20210423142403003](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423142403003.png)

![image-20210423142454660](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423142454660.png)

![image-20210423142550260](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423142550260.png)

![image-20210423142755580](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423142755580.png)

![image-20210423143032690](https://github.com/BeforeOne7/Blog/blob/main/src/main/resources/static/images/README/image-20210423143032690.png)

- 博客属性：标题、内容、首图、标记、浏览次数、赞赏开启、版权开启、评论开启、是否发布、创建时间、更新时间、描述
- 分类属性：分类名称
- 标签属性：标签名称
- 用户属性：昵称、用户名、密码、邮箱、类型、头像、创建时间、更新时间
- 评论属性：昵称、邮箱、头像、评论内容、创建时间