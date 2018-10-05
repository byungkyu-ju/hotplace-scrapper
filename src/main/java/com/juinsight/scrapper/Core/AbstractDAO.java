package com.juinsight.scrapper.Core;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class AbstractDAO {

    private final SqlSession sqlSession;

    public AbstractDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object insert(String queryId, Object params){
        return sqlSession.insert(queryId, params);
    }

    public Object update(String queryId, Object params){
        return sqlSession.update(queryId, params);
    }

    public Object delete(String queryId, Object params){
        return sqlSession.delete(queryId, params);
    }

    public Object selectOne(String queryId, Object params){
        return sqlSession.selectOne(queryId, params);
    }

    public List<Map<String, Object>> selectList(String queryId, Object params) {
        return sqlSession.selectList(queryId, params);
    }
}
