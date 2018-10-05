package com.juinsight.scrapper.Controller;
import com.juinsight.scrapper.Service.RssParsingService;
import com.juinsight.scrapper.VO.CrawlNewsSmryVO;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RssParsingController {

    @Autowired
    RssParsingService rssParsingService;

    @PostMapping("/{target}")
    public String init(@PathVariable String target, @RequestBody(required = false) Map<String,String> body){
        String CHANNEL = body.get("channel");
        String RSS_URL = body.get("site");
        try {
            rssParsingService.parseDOMObject(CHANNEL, RSS_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @PostMapping("/selectCrawlNewsTargetUrlList")
    public String init(HttpServletRequest request, HttpServletResponse response,
                       @RequestBody(required = false) Map<String,String> body){
        CrawlNewsSmryVO crawlNewsSmryVO = new CrawlNewsSmryVO();
        crawlNewsSmryVO.setChannel(body.get("channel"));
        rssParsingService.selectCrawlNewsTargetUrlList(crawlNewsSmryVO);
        return "success";
    }

    @PostMapping("/selectCrawlNewsTargetUrlList2")
    public String init(HttpServletRequest request, HttpServletResponse response){
        CrawlNewsSmryVO crawlNewsSmryVO = new CrawlNewsSmryVO();
        List<Map<String, Object>> selectCrawlNewsTargetUrlList = rssParsingService.selectCrawlNewsTargetUrlList(crawlNewsSmryVO);

        return new JSONArray(selectCrawlNewsTargetUrlList).toString();
    }

}
