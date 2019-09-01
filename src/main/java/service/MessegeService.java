package service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Messege;
import mybatis.MybatisConnector;

@Service
public class MessegeService {

	private final String namespace = "mybatis.Messege";

	@Autowired
	public MybatisConnector mybatisConnentor;
	
	public List<Messege> getMessage(String id) {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			return sqlSession.selectList(namespace + ".getMessage", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	
	public Messege getMsDetail(String num) {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("num", num);
		try {
			sqlSession.update(namespace + ".updateReadcount", map);
			return sqlSession.selectOne(namespace + ".getMsDetail", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
}
