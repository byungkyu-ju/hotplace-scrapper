package com.juinsight.scrapper.Core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractVO {

    //임시 세션
    public String inptId;
    public Date inptDttm;
    public String modId;
    public Date modDttm;

    public String getInptId() {
        inptId = "admin";
        return inptId;
    }

    public void setInptId(String inptId) {
        this.inptId = inptId;
    }

    public Date getInptDttm() {
        return inptDttm;
    }

    public void setInptDttm(Date inptDttm) {
        this.inptDttm = inptDttm;
    }

    public String getModId() {
        modId = "admin";
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public Date getModDttm() {
        Date tmpDate = (Date) java.sql.Timestamp.valueOf(String.valueOf(System.currentTimeMillis()));
        return tmpDate;
    }

    public void setModDttm(Date modDttm) {
        this.modDttm = modDttm;
    }
}
