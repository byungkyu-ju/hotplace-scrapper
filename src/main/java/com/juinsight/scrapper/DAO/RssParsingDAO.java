package com.juinsight.scrapper.DAO;

import com.juinsight.scrapper.VO.CrawlNewsSmryVO;
import com.juinsight.scrapper.Core.AbstractDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("RssParsingDao")
public class RssParsingDAO extends AbstractDAO {

    public RssParsingDAO(SqlSession sqlSession) {
        super(sqlSession);
    }

    public void insertCrawlNewsSmryList(CrawlNewsSmryVO crawlNewsSmryVO) {
        insert("insertCrawlNewsSmryList", crawlNewsSmryVO);
    }

    public List<Map<String, Object>> selectCrawlNewsTargetUrlList(CrawlNewsSmryVO crawlNewsSmryVO){
        return selectList("selectCrawlNewsTargetUrlList",crawlNewsSmryVO);
    }
}
