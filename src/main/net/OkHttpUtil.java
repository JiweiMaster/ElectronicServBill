package main.net;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.google.gson.reflect.TypeToken;
import main.log4j.MyLog;
import main.util.GsonUtil;
import okhttp3.*;

public class OkHttpUtil {
	private static OkHttpUtil OkHttpUtil = null;
	private static OkHttpClient client = null;
	private OkHttpUtil() {
	}
	
	public static synchronized OkHttpUtil getInstance(){
		if(OkHttpUtil == null){
			OkHttpUtil = new OkHttpUtil();
			client = new OkHttpClient.Builder()
					.writeTimeout(30,TimeUnit.SECONDS)
					.connectTimeout(30,TimeUnit.SECONDS)
					.readTimeout(30,TimeUnit.SECONDS)
					.addInterceptor(new Interceptor() {
						@Override
						public Response intercept(Chain chain) throws IOException {
							Request request = chain.request();
							//是不是上传文件的类，如果是上传文件的类才这样操作,上传文件使用的就是MultipartBody
							if(request.body() instanceof  MultipartBody){
								MultipartBody multipartBody = (MultipartBody) request.body();
								try{
									Field field = multipartBody.getClass().getDeclaredField("parts");
									field.setAccessible(true);
									List<MultipartBody.Part> object = (List<MultipartBody.Part>) field.get(multipartBody);
									//目标需要修改的part
									MultipartBody.Part filenamePart = object.get(1);
									String fileNameHeaders = GsonUtil.ObjectToJson(filenamePart.headers());
									//查找header里面中文头参数的位置进行修改
									int fileIndex = fileNameHeaders.indexOf("filename\\u003d\\\"");
									int png = fileNameHeaders.indexOf(".pdf");//根据实际上传的文件名来确定
									if(fileIndex == -1 || png == -1){
										//直接跳出返回
										Response response = chain.proceed(request);
										return response;
									}
									String fileNameURLEncodeStr = fileNameHeaders.substring(fileIndex+"filename\\u003d\\\"".length(),png);
									String fileNameURLDecodeStr = URLDecoder.decode(fileNameURLEncodeStr,"utf-8");
//								System.out.println("fileNameURLEncodeStr:"+fileNameURLEncodeStr);
//								System.out.println("fileNameURLDecodeStr:"+fileNameURLDecodeStr);
									//修改文件头
									fileNameHeaders = fileNameHeaders.replace(fileNameURLEncodeStr,fileNameURLDecodeStr);
									Headers filenamePartHeader = GsonUtil.JsonToObject(fileNameHeaders,
											new TypeToken<Headers>(){}.getType());
									//置换filenamePart的header
									Field headerField = filenamePart.getClass().getDeclaredField("headers");
									headerField.setAccessible(true);
									headerField.set(filenamePart,filenamePartHeader);
//								System.out.println(fileNameHeaders);
									field.set(multipartBody,object);
								}catch(Exception e){
//								e.printStackTrace();
									MyLog.info("反射解决文件名发生异常错误："+e.toString());
								}
							}
							Response response = chain.proceed(request);
							return response;
						}
					})
					.build();
		}
		return OkHttpUtil;
	}
	//post json格式的请求
	public String esbRequest(String url, Map<String,String> headers, Map<String,Object> body){
		try{
			// 使用JSONObject封装参数
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("data",body);
			System.out.println("esbRequest headers:"+GsonUtil.ObjectToJson(headers));
			System.out.println("esbRequest data:"+GsonUtil.ObjectToJson(dataMap));
			//创建RequestBody对象，将参数按照指定的MediaType封装
			RequestBody requestBody =
					RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
							GsonUtil.ObjectToJson(dataMap));
			Request.Builder builder = new Request
					.Builder()
					.post(requestBody)
					.url(url);
			if (headers != null){
				for(String key:headers.keySet()){
					builder = builder.addHeader(key, headers.get(key));
				}
			}
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			response.body().close();
			return result;
		}catch (Exception e){
			System.out.println(e.toString());
			return null;
		}
	}

	public String uploadFile(String url, File file){
		try{
			MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
			String fileName = file.getName();
			builder.addFormDataPart("folderid","9435309")
					.addFormDataPart("file", URLEncoder.encode(fileName,"utf-8"),
							RequestBody.create(MediaType.parse("multipart/form-data"), file));
			MultipartBody requestBody = builder.build();

			Request request = new Request.Builder()
					.header("Content-Type", "multipart/form-data")
					.header("ClientId", "com.primeton.esb.consumer.mis")
					.header("OperationCode", "esb.nrec.ecm.edoc.file.upload")
					.header("EmpNo", "10037")
					.url(url)
					.post(requestBody)
					.build();
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()){
				return response.body().string();
			}else{
				System.out.println(response.body().string());
				return null;
			}
		}catch (Exception e){
//			e.printStackTrace();
//			System.out.println(""+e.toString());
			MyLog.info("uploadFile:"+e.toString());
		}
			return null;
		}
}




