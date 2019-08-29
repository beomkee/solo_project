package mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnector {

	String resource;
	String dbname;

	public SqlSession sqlSession() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream).openSession();
	}

	public void setDbname(String dbname) {
		this.resource = "mybatis/mybatis-configMysql.xml";
		this.dbname = dbname;
	}

	public String getDbname() {
		return dbname;
	}
}
