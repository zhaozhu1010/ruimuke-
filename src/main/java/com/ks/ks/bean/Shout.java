package com.ks.ks.bean;

public class Shout {

    private long   currentTime;
    private  long  startTime;
    private long  endTime;
    private String   cpNo;
    private String   cpResult;

    public String getCpNo() {
        return cpNo;
    }

    public void setCpNo(String cpNo) {
        this.cpNo = cpNo;
    }

    public String getCpResult() {
        return cpResult;
    }

    public void setCpResult(String cpResult) {
        this.cpResult = cpResult;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
