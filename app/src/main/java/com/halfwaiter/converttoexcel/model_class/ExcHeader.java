package com.halfwaiter.converttoexcel.model_class;

import java.util.List;

public class ExcHeader {
    private String title0;
    private String title1;
    private List<CellData> cellDataList;

    public ExcHeader(String title0, String title1, List<CellData> cellDataList) {
        this.title0 = title0;
        this.title1 = title1;
        this.cellDataList = cellDataList;
    }

    @Override
    public String toString() {
        return "ExcHeader{" +
                "title0='" + title0 + '\'' +
                ", title1='" + title1 + '\'' +
                ", cellDataList=" + cellDataList +
                '}';
    }

    public String getTitle0() {
        return title0;
    }

    public void setTitle0(String title0) {
        this.title0 = title0;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public List<CellData> getCellDataList() {
        return cellDataList;
    }

    public void setCellDataList(List<CellData> cellDataList) {
        this.cellDataList = cellDataList;
    }
}
