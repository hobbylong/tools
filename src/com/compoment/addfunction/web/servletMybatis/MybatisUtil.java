package com.compoment.addfunction.web.servletMybatis;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class MybatisUtil {

	public void mybatisUtil()
	{
		String m="";
		m+="import org.apache.ibatis.io.Resources;\n";
		m+="import org.apache.ibatis.session.SqlSessionFactory;\n";
		m+="import org.apache.ibatis.session.SqlSessionFactoryBuilder;\n";

		m+="import java.io.IOException;\n";
		m+="import java.io.Reader;\n";


		m+="public class MybatisUtil {\n";
		m+="    private static SqlSessionFactory sessionFactory;\n";
		m+="    private static Reader reader;\n";

		m+="    static {\n";
		m+="        try {\n";
		m+="            //mybatis的配置文件\n";
		m+="            String resource = \"mybatisConfig.xml\";\n";
		m+="            //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）\n";
		m+="            //InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);\n";
		m+="            reader = Resources.getResourceAsReader(resource);\n";

		m+="            //构建sqlSession的工厂\n";
		m+="            sessionFactory = new SqlSessionFactoryBuilder().build(reader);\n";

		m+="        } catch (IOException e) {\n";
		m+="            e.printStackTrace();\n";
		m+="        }\n";
		m+="    }\n";

		m+="    public static SqlSessionFactory getInstance() {\n";
		m+="        return sessionFactory;\n";
		m+="    }\n";
		m+="}\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				  "MybatisUtil", "java", m);
		
		m="";
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		m+="<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n";
		m+="<configuration>\n";
		m+="    <!-- 引用db.properties配置文件 -->\n";
		m+="    <properties resource=\"jdbc.properties\"/>\n";

		 m+=" <settings>\n";
	       m+=" <setting name=\"logImpl\" value=\"LOG4J\"/>\n";
		m+="</settings>\n";
		m+="    <environments default=\"development\">\n";
		m+="        <environment id=\"development\">\n";
		m+="            <transactionManager type=\"JDBC\"/>\n";
		m+="            <!-- 配置数据库连接信息 -->\n";
		m+="            <dataSource type=\"POOLED\">\n";
		m+="                <property name=\"driver\" value=\"${driver}\"/>\n";
		m+="                <property name=\"url\" value=\"${url}\"/>\n";
		m+="                <property name=\"username\" value=\"${name}\"/>\n";
		m+="                <property name=\"password\" value=\"${password}\"/>\n";
		m+="            </dataSource>\n";
		m+="        </environment>\n";
		m+="    </environments>\n";
		m+="    <mappers>\n";
		m+="        <mapper resource=\"com/yyr/mapping/userMapper.xml\"/>\n";
		m+="    </mappers>\n";
		m+="</configuration>\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				  "mybatisConfig", "xml", m);
		
		m="";
		m+="driver=com.mysql.jdbc.Driver\n";
		m+="url=jdbc:mysql://localhost:3306/yyr_test\n";
		m+="name=root\n";
		m+="password=jialin89086\n";
		
		m+="driver=oracle.jdbc.OracleDriver\n";
		m+="url=jdbc:oracle:thin:@192.168.248.62:1521:orcl\n";
		m+="name=root\n";
		m+="password=jialin89086\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				  "jdbc", "properties", m);
		
		
		
		
		
m="";
m+="log4j.rootLogger=DEBUG,console,file\n";
m+="\n\n";
m+="#-----------------------------------#\n";
m+="#1 \u5B9A\u4E49\u65E5\u5FD7\u8F93\u51FA\u76EE\u7684\u5730\u4E3A\u63A7\u5236\u53F0\n";
m+="log4j.appender.console = org.apache.log4j.ConsoleAppender\n";
m+="log4j.appender.console.Target = System.out\n";
m+="log4j.appender.console.Threshold=DEBUG\n";

m+="log4j.appender.console.layout = org.apache.log4j.PatternLayout\n";
m+="log4j.appender.console.layout.ConversionPattern=[%c]-%m%n\n";
m+="\n\n";
m+="#-----------------------------------#\n";
m+="#2 \u6587\u4EF6\u5927\u5C0F\u5230\u8FBE\u6307\u5B9A\u5C3A\u5BF8\u7684\u65F6\u5019\u4EA7\u751F\u4E00\u4E2A\u65B0\u7684\u6587\u4EF6 \n";
m+="log4j.appender.file = org.apache.log4j.RollingFileAppender\n";
m+="log4j.appender.file.File=log/tibet.log\n";
m+="log4j.appender.file.MaxFileSize=10mb\n";
m+="log4j.appender.file.Threshold=ERROR\n";
m+="log4j.appender.file.layout=org.apache.log4j.PatternLayout\n";
m+="log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n\n";

m+="\n\n";
m+="log4j.logger.org.mybatis=DEBUG\n";
m+="log4j.logger.org.mybatis.common.jdbc.SimpleDataSource=DEBUG\n";
m+="log4j.logger.org.mybatis.common.jdbc.ScriptRunner=DEBUG\n";
m+="log4j.logger.org.mybatis.sqlmap.engine.impl.SqlMapClientDelegate=DEBUG\n";
m+="log4j.logger.java.sql.Connection=DEBUG\n";
m+="log4j.logger.java.sql=DEBUG\n";
m+="log4j.logger.java.sql.Statement=DEBUG\n";
m+="log4j.logger.java.sql.ResultSet=DEBUG\n";
m+="log4j.logger.java.sql.PreparedStatement=DEBUG\n";
FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
		  "log4j", "properties", m);


	}
}
