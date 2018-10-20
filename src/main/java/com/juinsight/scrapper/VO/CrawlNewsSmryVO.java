package com.juinsight.scrapper.VO;

import com.juinsight.scrapper.Core.AbstractVO;

import java.util.Date;

public class CrawlNewsSmryVO extends AbstractVO {

    private String companyId;
    private String link;
    private String title;
    private Date pubDate;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}
