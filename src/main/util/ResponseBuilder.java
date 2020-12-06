package main.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseBuilder {
    public static void response(HttpServletResponse response, String message){
        try{
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(message);
        }catch (Exception e){
            System.out.println("response数据出错："+e.toString());
        }

    }
}
