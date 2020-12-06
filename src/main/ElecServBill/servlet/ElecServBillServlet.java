package main.ElecServBill.servlet;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.ElecServBill.model.ElecServBill;
import main.model.ESBData;
import main.model.ResponseObject;
import main.net.OkHttpUtil;
import main.util.GsonUtil;
import main.util.ImageBase64;
import main.util.ResponseBuilder;
import main.wordUtil.WriteWordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "ElecServBillServlet", value = {"/ElecServBillServlet"})
public class ElecServBillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    private int getLines(String esrContent){
        return esrContent.length()/45+1;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseObject responseObject = new ResponseObject();
        String sno = request.getParameter("sno");
        try{
            Map<String,String> headers = new HashMap<>();
            headers.put("OperationCode","esb.nrec.mis.engineer.service.getElectricSnoInfo");
            headers.put("Content-Type","application/json");
            headers.put("ClientId","com.primeton.esb.consumer.mis");
            Map<String,Object> body = new HashMap<>();
            body.put("sno",sno);
            String esbdataStr  = OkHttpUtil.getInstance()
                    .esbRequest("http://118.31.168.98:9680/esb.nrec.mis.engineer.service",headers,body);
//            String esbdataStr  = OkHttpUtil.getInstance()
//                    .esbRequest("http://192.168.20.39:9680/esb.nrec.mis.engineer.service",headers,body);
            ESBData<List<ElecServBill>> esbData = GsonUtil.JsonToObject(esbdataStr,new TypeToken<ESBData<List<ElecServBill>>>(){}.getType());
            if(esbData.getData().size() == 0){
                responseObject.isSuccess = false;
                responseObject.message = "服务单不存在";
                ResponseBuilder.response(response,GsonUtil.ObjectToJson(responseObject));
                return;
            }
            ElecServBill elecServBill = esbData.getData().get(0);
            String modelFilepath = getModelPath(elecServBill.getCONTRACTNO());
            System.out.println("model路径："+modelFilepath);
            if(modelFilepath == null){
                responseObject.isSuccess = false;
                responseObject.message = "文件路径null";
                ResponseBuilder.response(response,GsonUtil.ObjectToJson(responseObject));
                return;
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("SNO",sno);
            params.put("SERVNAME",elecServBill.SERVNAME);
            params.put("SERVGROUP",elecServBill.SERVGROUP);
            params.put("STATIONNAME",elecServBill.STATIONNAME);
            params.put("CONTRACTNO",elecServBill.CONTRACTNO);
            params.put("XCLXR",elecServBill.XCLXR);
            params.put("XCLXDH",elecServBill.XCLXDH);
            params.put("YDSJ",elecServBill.YDSJ);
            //处理服务要求
            if(elecServBill.TTYPEKIND.equals("调试类")){
                params.put("isDebug","√");
            }
            if(elecServBill.TTYPEKIND.equals("运维类")){
                params.put("isMaintain","√");
            }
            if(elecServBill.TTYPEKIND.equals("技术类")){
                params.put("isTech","√");
            }
            if(elecServBill.TTYPEKIND.equals("其他")){
                params.put("isOthers","√");
            }
            //服务要求
            String SERVCOMMANDSTR = elecServBill.SERVCOMMAND.replace("\n","");
            params.put("SERVCOMMAND",SERVCOMMANDSTR);

            int SERVCOMMANDLINES =  getLines(SERVCOMMANDSTR);
            if(SERVCOMMANDLINES == 1){
                params.put("SERVCOMMANDLINE1"," \n ");
                params.put("SERVCOMMANDLINE2"," \n ");
            }
            if(SERVCOMMANDLINES == 2){
                params.put("SERVCOMMANDLINE1"," ");
                params.put("SERVCOMMANDLINE2"," \n ");
            }
            if(SERVCOMMANDLINES == 3){
                params.put("SERVCOMMANDLINE1"," ");
                params.put("SERVCOMMANDLINE2"," ");
            }

            //服务实施情况
            params.put("ARRIVETIME",elecServBill.ARRIVETIME);
            params.put("LEAVETIME",elecServBill.LEAVETIME);
            params.put("SNONUM",elecServBill.SNONUM);

            if(elecServBill.SERVDAYS !=null){
                params.put("SNONUM",elecServBill.SERVDAYS);
            }

            String SERVCONTENT = elecServBill.SERVCONTENT.replace("\n","");
            int lines = getLines(SERVCONTENT);
            params.put("SERVCONTENT",SERVCONTENT);
            if(lines==1){
                params.put("LINE2"," \n ");
                params.put("LINE3"," \n ");
                params.put("LINE4"," \n ");
                params.put("LINE5"," \n ");
                params.put("LINE6"," \n ");
                params.put("LINE7"," \n ");
            }
            if(lines==2){
                params.put("LINE2"," ");
                params.put("LINE3"," \n ");
                params.put("LINE4"," \n ");
                params.put("LINE5"," \n ");
                params.put("LINE6"," \n ");
                params.put("LINE7"," \n ");
            }
            if(lines==3){
                params.put("LINE2"," ");
                params.put("LINE3"," ");
                params.put("LINE4"," \n ");
                params.put("LINE5"," \n ");
                params.put("LINE6"," \n ");
                params.put("LINE7"," \n ");
            }
            if(lines==4){
                params.put("LINE2"," ");
                params.put("LINE3"," ");
                params.put("LINE4"," ");
                params.put("LINE5"," \n ");
                params.put("LINE6"," \n ");
                params.put("LINE7"," \n ");
            }
            if(lines==5){
                params.put("LINE2"," ");
                params.put("LINE3"," ");
                params.put("LINE4"," ");
                params.put("LINE5"," ");
                params.put("LINE6"," ");
                params.put("LINE7"," \n ");
            }
            if(lines==6){
                params.put("LINE2"," ");
                params.put("LINE3"," ");
                params.put("LINE4"," ");
                params.put("LINE5"," ");
                params.put("LINE6"," ");
                params.put("LINE7"," ");
            }
            if(lines > 6){
                if(SERVCONTENT.length() > 320){
                    params.put("SERVCONTENT",SERVCONTENT.substring(0,320)+"...");
                }else{
                    params.put("SERVCONTENT",SERVCONTENT);
                }
            }else{
                params.put("SERVCONTENT",SERVCONTENT);
            }

            //说明
            if(elecServBill.ISTYGZ != null && elecServBill.ISTYGZ.equals("是")){
                params.put("ISTYGZ","√");
            }else{
                params.put("ISTYGZ","×");
            }
            if(elecServBill.ISJJSM != null && elecServBill.ISJJSM.equals("是")){
                params.put("ISJJSM","√");
            }else{
                params.put("ISJJSM","×");
            }
            if(elecServBill.ISPXGZ != null && elecServBill.ISPXGZ.equals("是")){
                params.put("ISPXGZ","√");
            }else{
                params.put("ISPXGZ","×");
            }
            //归档
            List<String> TRANSMEMOS = Arrays.asList(elecServBill.GDQKMEMO.split(","));
            if(elecServBill.GDQK.equals("有")){
                params.put("GDQK","√");
            }else{
                params.put("NOGDQK","√");
            }
            if(TRANSMEMOS.contains("后台系统")){
                params.put("ht","√");
            }
            if(TRANSMEMOS.contains("远动系统")){
                params.put("yd","√");
            }
            if(TRANSMEMOS.contains("装置配置")){
                params.put("zz","√");
            }
            if(TRANSMEMOS.contains("保信系统")){
                params.put("bx","√");
            }
            if(TRANSMEMOS.contains("通讯系统")){
                params.put("tx","√");
            }
            if(TRANSMEMOS.contains("五防系统")){
                params.put("wf","√");
            }
            if(TRANSMEMOS.contains("在线监测")){
                params.put("zxjc","√");
            }
            if(TRANSMEMOS.contains("相关资料")){
                params.put("xgzl","√");
            }
            //对于其他字段的处理
            for(String str : TRANSMEMOS){
                if(str.contains("其他")){
                    params.put("isOther","√");
                    params.put("otherMeno",str.replace("其他",""));
                }
            }
            //顾客意见
            Map<String,Object> remarkMap = elecServBill.getREMARK();
            params.put("SUGGESTION",remarkMap.get("SUGGESTION"));
            if(remarkMap != null){
                if(remarkMap.keySet().size() >0){
                    int TIMELINESS = (int) Double.parseDouble(remarkMap.get("TIMELINESS")+"");
                    int ATTITUDE = (int) Double.parseDouble(remarkMap.get("ATTITUDE")+"");
                    int SKILL = (int) Double.parseDouble(remarkMap.get("SKILL")+"");
                    int PERFORMANCE = (int) Double.parseDouble(remarkMap.get("PERFORMANCE")+"");
                    int EFFECT = (int) Double.parseDouble(remarkMap.get("EFFECT")+"");

                    switch (TIMELINESS){
                        case 5: params.put("xy5","√"); break;
                        case 4: params.put("xy4","√"); break;
                        case 3: params.put("xy3","√"); break;
                        case 2: params.put("xy2","√"); break;
                        case 1: params.put("xy1","√"); break;
                    }
                    switch (ATTITUDE){
                        case 5: params.put("td5","√"); break;
                        case 4: params.put("td4","√"); break;
                        case 3: params.put("td3","√"); break;
                        case 2: params.put("td2","√"); break;
                        case 1: params.put("td1","√"); break;
                    }
                    switch (SKILL){
                        case 5: params.put("jn5","√"); break;
                        case 4: params.put("jn4","√"); break;
                        case 3: params.put("jn3","√"); break;
                        case 2: params.put("jn2","√"); break;
                        case 1: params.put("jn1","√"); break;
                    }
                    switch (PERFORMANCE){
                        case 5: params.put("xn5","√"); break;
                        case 4: params.put("xn4","√"); break;
                        case 3: params.put("xn3","√"); break;
                        case 2: params.put("xn2","√"); break;
                        case 1: params.put("xn1","√"); break;
                    }
                    switch (EFFECT){
                        case 5: params.put("px5","√"); break;
                        case 4: params.put("px4","√"); break;
                        case 3: params.put("px3","√"); break;
                        case 2: params.put("px2","√"); break;
                        case 1: params.put("px1","√"); break;
                    }
                }
            }
            Calendar calendar = Calendar.getInstance();
            params.put("curYear",calendar.get(Calendar.YEAR));
            params.put("curMonth",calendar.get(Calendar.MONTH)+1);
            params.put("curDay",calendar.get(Calendar.DATE));
            String basePath = this.getClass().getResource("/").getPath();
            if(basePath.contains("E:")){
                basePath = basePath.substring(1,basePath.length());
            }
            System.out.println(basePath);
            String tempDocx = basePath+"test2.docx";
            String tempPng = basePath+"test2.png";
            WriteWordUtil.templateWrite2(modelFilepath,params, tempDocx);
            WriteWordUtil.doc2pdf(tempDocx,tempPng);
            String based4ImgStr= ImageBase64.ImageToBase64(tempPng);
            based4ImgStr = "data:image/png;base64,"+based4ImgStr.replace("\r\n","")
                    .replace("\n","");
            responseObject.isSuccess = true;
            responseObject.message = "成功";
            responseObject.object = based4ImgStr;
            //删除不必要的文件
            File deleteFile1 = new File(modelFilepath);
            if(deleteFile1.exists()){
                deleteFile1.delete();
            }
            File deleteFile2 = new File(tempDocx);
            if(deleteFile2.exists()){
                deleteFile2.delete();
            }
            File deleteFile3 = new File(tempPng);
            if(deleteFile3.exists()){
                deleteFile3.delete();
            }
            ResponseBuilder.response(response,GsonUtil.ObjectToJson(responseObject));
        }catch (Exception e){
            responseObject.isSuccess = false;
            responseObject.message = "失败！"+ e.toString();
            ResponseBuilder.response(response,GsonUtil.ObjectToJson(responseObject));
            e.printStackTrace();
            System.out.println("error:"+e.toString());
        }
    }


    public synchronized String getModelPath(String contractNo){
        try{
            String path= this.getClass().getResource("/").getPath();
            String modelPath = "";
            if(contractNo.split(",").length > 3){
                modelPath = path+"model2.docx";
            }else{
                modelPath = path+"model.docx";
            }
            File file = new File(modelPath);
            if(file.exists()){
                System.out.println("文件存在");
                String newFilePath = path+System.currentTimeMillis()+".docx";
                File newFile = new File(newFilePath);
                if(!newFile.exists()){
                    newFile.createNewFile();
                }
                copy(modelPath,newFilePath);
                return newFilePath;
            }else{
                System.out.println("文件不存在");
                return null;
            }
        }catch (Exception e){
            System.out.println("downloadByGet:"+e.toString());
            return null;
        }
    }

    //复制文件
    public void copy(String source, String dest) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(new File(source));
            out = new FileOutputStream(new File(dest));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
        }
    }
}
