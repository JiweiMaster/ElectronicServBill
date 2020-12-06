package main.ElecServBill.model;

import java.util.List;
import java.util.Map;

public class ElecServBill {
    public String SNONUM;
    public String ISJJSM;
    public String XCLXDH;
    public String SERVGROUP;
    public String SERVCOMMAND;
    public String SERVCONTENT;
    public List<String> TRANSMEMOS;
    public String ARRIVETIME;
    public String ISTYGZ;
    public String TTYPE;
    public String YDSJ;
    public String XCLXR;
    public String CONTRACTNO;
    public String LEAVETIME;
    public String SNO;
    public String GDQK;
    public String STATIONNAME;
    public String TRANSMEMO;
    public String SERVNAME;

    public String ISPXGZ;
    public String GDQKMEMO;
    public String TTYPEKIND;

    public String SERVDAYS;
    public String SUGGESTION;

    public Map<String, Object> getREMARK() {
        return REMARK;
    }

    public void setREMARK(Map<String, Object> REMARK) {
        this.REMARK = REMARK;
    }

    public Map<String,Object> REMARK;

    public ElecServBill(String SNONUM, String ISJJSM, String XCLXDH, String SERVGROUP, String SERVCOMMAND, String SERVCONTENT, List<String> TRANSMEMOS, String ARRIVETIME, String ISTYGZ, String TTYPE, String YDSJ, String XCLXR, String CONTRACTNO, String LEAVETIME,
                        String SNO, String GDQK, String STATIONNAME, String TRANSMEMO, String SERVNAME, String ISPXGZ,String GDQKMEMO,String TTYPEKIND) {
        this.SNONUM = SNONUM;
        this.ISJJSM = ISJJSM;
        this.XCLXDH = XCLXDH;
        this.SERVGROUP = SERVGROUP;
        this.SERVCOMMAND = SERVCOMMAND;
        this.SERVCONTENT = SERVCONTENT;
        this.TRANSMEMOS = TRANSMEMOS;
        this.ARRIVETIME = ARRIVETIME;
        this.ISTYGZ = ISTYGZ;
        this.TTYPE = TTYPE;
        this.YDSJ = YDSJ;
        this.XCLXR = XCLXR;
        this.CONTRACTNO = CONTRACTNO;
        this.LEAVETIME = LEAVETIME;
        this.SNO = SNO;
        this.GDQK = GDQK;
        this.STATIONNAME = STATIONNAME;
        this.TRANSMEMO = TRANSMEMO;
        this.SERVNAME = SERVNAME;

        this.ISPXGZ = ISPXGZ;
        this.GDQKMEMO = GDQKMEMO;
        this.TTYPEKIND = TTYPEKIND;
    }

    public String getISPXGZ() {
        return ISPXGZ;
    }

    public void setISPXGZ(String ISPXGZ) {
        this.ISPXGZ = ISPXGZ;
    }

    public String getSNONUM() {
        return SNONUM;
    }

    public void setSNONUM(String SNONUM) {
        this.SNONUM = SNONUM;
    }

    public String getISJJSM() {
        return ISJJSM;
    }

    public void setISJJSM(String ISJJSM) {
        this.ISJJSM = ISJJSM;
    }

    public String getXCLXDH() {
        return XCLXDH;
    }

    public void setXCLXDH(String XCLXDH) {
        this.XCLXDH = XCLXDH;
    }

    public String getSERVGROUP() {
        return SERVGROUP;
    }

    public void setSERVGROUP(String SERVGROUP) {
        this.SERVGROUP = SERVGROUP;
    }

    public String getSERVCOMMAND() {
        return SERVCOMMAND;
    }

    public void setSERVCOMMAND(String SERVCOMMAND) {
        this.SERVCOMMAND = SERVCOMMAND;
    }

    public String getSERVCONTENT() {
        return SERVCONTENT;
    }

    public void setSERVCONTENT(String SERVCONTENT) {
        this.SERVCONTENT = SERVCONTENT;
    }

    public List<String> getTRANSMEMOS() {
        return TRANSMEMOS;
    }

    public void setTRANSMEMOS(List<String> TRANSMEMOS) {
        this.TRANSMEMOS = TRANSMEMOS;
    }

    public String getARRIVETIME() {
        return ARRIVETIME;
    }

    public void setARRIVETIME(String ARRIVETIME) {
        this.ARRIVETIME = ARRIVETIME;
    }

    public String getISTYGZ() {
        return ISTYGZ;
    }

    public void setISTYGZ(String ISTYGZ) {
        this.ISTYGZ = ISTYGZ;
    }

    public String getTTYPE() {
        return TTYPE;
    }

    public void setTTYPE(String TTYPE) {
        this.TTYPE = TTYPE;
    }

    public String getYDSJ() {
        return YDSJ;
    }

    public void setYDSJ(String YDSJ) {
        this.YDSJ = YDSJ;
    }

    public String getXCLXR() {
        return XCLXR;
    }

    public void setXCLXR(String XCLXR) {
        this.XCLXR = XCLXR;
    }

    public String getCONTRACTNO() {
        return CONTRACTNO;
    }

    public void setCONTRACTNO(String CONTRACTNO) {
        this.CONTRACTNO = CONTRACTNO;
    }

    public String getLEAVETIME() {
        return LEAVETIME;
    }

    public void setLEAVETIME(String LEAVETIME) {
        this.LEAVETIME = LEAVETIME;
    }

    public String getSNO() {
        return SNO;
    }

    public void setSNO(String SNO) {
        this.SNO = SNO;
    }

    public String getGDQK() {
        return GDQK;
    }

    public void setGDQK(String GDQK) {
        this.GDQK = GDQK;
    }

    public String getSTATIONNAME() {
        return STATIONNAME;
    }

    public void setSTATIONNAME(String STATIONNAME) {
        this.STATIONNAME = STATIONNAME;
    }

    public String getTRANSMEMO() {
        return TRANSMEMO;
    }

    public void setTRANSMEMO(String TRANSMEMO) {
        this.TRANSMEMO = TRANSMEMO;
    }

    public String getSERVNAME() {
        return SERVNAME;
    }

    public void setSERVNAME(String SERVNAME) {
        this.SERVNAME = SERVNAME;
    }
}
