package com.juinsight.scrapper.Service;

import com.google.gson.*;
import com.juinsight.scrapper.DAO.RssParsingDAO;
import com.juinsight.scrapper.VO.CrawlNewsSmryVO;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RssParsingService {

    @Autowired
    private RssParsingDAO rssParsingDao;

    public void parseDOMObject(String channel, String url){
        try {
            URL realUrl = new URL(url);
            InputStream inputStream = realUrl.openStream();


            //1.JSON포멧이 맞는지 검증
            //1.1.그안에 원하는 포멧이 있는지 검증
            //
            try {
                String parsedData = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
                JSONObject parsedJsonObject = XML.toJSONObject(parsedData);

                JSONArray jsonArray = parsedJsonObject.getJSONObject("rss")
                                                      .getJSONObject("channel")
                                                      .getJSONArray("item");

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DateTimeFormatter pubsDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm:ss");
                    LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(jsonObject.get("pubDate").toString()));

                    String pubDate = localDateTime.format(pubsDateFormatter).toString();
                    jsonObject.put("pubDate",pubDate);

                    //http://www.javased.com/?post=4322541
                    GsonBuilder builder = new GsonBuilder();

                    Gson gson = builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        @Override
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                throws JsonParseException {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
                            String date = json.getAsJsonPrimitive().getAsString();
                            try {
                                return format.parse(date);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).create();

                    CrawlNewsSmryVO crawlNewsSmryVO = gson.fromJson(jsonObject.toString(),CrawlNewsSmryVO.class);
                    //to DAO
                    rssParsingDao.insertCrawlNewsSmryList(crawlNewsSmryVO);
                }
            }catch (Exception e){
                //xml parsing error was occured. check RSS url and fixed it.
            }finally {

            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> selectCrawlNewsTargetUrlList(CrawlNewsSmryVO crawlNewsSmryVO){
        List<Map<String, Object>> selectCrawlNewsTargetUrlList = rssParsingDao.selectCrawlNewsTargetUrlList(crawlNewsSmryVO);
        for(int i=0; i< selectCrawlNewsTargetUrlList.size(); i++){
            Map<String,Object> testmap = selectCrawlNewsTargetUrlList.get(i);
        }
        return rssParsingDao.selectCrawlNewsTargetUrlList(crawlNewsSmryVO);
    }
}
