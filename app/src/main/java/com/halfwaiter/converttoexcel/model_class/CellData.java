package com.halfwaiter.converttoexcel.model_class;

public class CellData {
    private String d_cell0;
    private String d_cell1;

    @Override
    public String toString() {
        return "CellData{" +
                "d_cell0='" + d_cell0 + '\'' +
                ", d_cell1='" + d_cell1 + '\'' +
                '}';
    }

    public String getD_cell0() {
        return d_cell0;
    }

    public void setD_cell0(String d_cell0) {
        this.d_cell0 = d_cell0;
    }

    public String getD_cell1() {
        return d_cell1;
    }

    public void setD_cell1(String d_cell1) {
        this.d_cell1 = d_cell1;
    }

    public CellData(String d_cell0, String d_cell1) {
        this.d_cell0 = d_cell0;
        this.d_cell1 = d_cell1;
    }
}
