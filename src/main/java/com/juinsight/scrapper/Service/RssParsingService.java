package com.juinsight.scrapper.Service;

import com.juinsight.scrapper.DAO.RssParsingDAO;
import com.juinsight.scrapper.VO.CrawlNewsSmryVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RssParsingService {

    @Autowired
    private RssParsingDAO rssParsingDao;

    public List<Map<String, Object>> selectCrawlNewsTargetUrlList(CrawlNewsSmryVO crawlNewsSmryVO){
        List<Map<String, Object>> selectCrawlNewsTargetUrlList = rssParsingDao.selectCrawlNewsTargetUrlList(crawlNewsSmryVO);
        for(int i=0; i< selectCrawlNewsTargetUrlList.size(); i++){
            Map<String,Object> testmap = selectCrawlNewsTargetUrlList.get(i);
        }
        return rssParsingDao.selectCrawlNewsTargetUrlList(crawlNewsSmryVO);
    }

    public void parseDOMObjectJsoup(String companyId, String url){
        try {
            //1.JSON포멧이 맞는지 검증
            //1.1.그안에 원하는 포멧이 있는지 검증
            //
            Document doc = Jsoup.connect(url).get();
            Elements itemList = doc.select("item");
            for(Element e : itemList){
                String title = e.select("title").text();
                String link = e.select("link").text();
                String pubDate = e.select("pubDate").text();

                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
                Date parsedPubDate = sdf.parse(pubDate);

                CrawlNewsSmryVO crawlNewsSmryVO = new CrawlNewsSmryVO();
                crawlNewsSmryVO.setCompanyId(companyId);
                crawlNewsSmryVO.setLink(link);
                crawlNewsSmryVO.setTitle(title);
                crawlNewsSmryVO.setPubDate(parsedPubDate);
                System.out.println(crawlNewsSmryVO.getTitle());
                rssParsingDao.insertCrawlNewsSmryList(crawlNewsSmryVO);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
