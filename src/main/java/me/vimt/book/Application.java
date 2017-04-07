package me.vimt.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/10/24 20:15
 * Description:
 图书管理
 5
 完成一个B/S架构的数据库管理系统 (客户端Window/图形界面)	完成图书资料的管理，处理的信息包括图书信息、读者信息、出版社、图书分类、图书借阅等。
 软件系统/技术文档
 DB:SQL Server /MySQL   程序：.Net
 数据库/程序设计	专注/踏实/自学能力。
 要求具有数据库和.net做B/S程序的进阶知识	程序演示与教师运行程序检查。
 采用数据库或者图形界面难度等级为调整3级。
 郑莉华

 */
@SpringBootApplication
//@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
