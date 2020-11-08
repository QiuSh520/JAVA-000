package io.github.kimmking.netty.server;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    /**
     * Get请求
     * @param url   URL地址
     * @return  返回结果
     */
    public static String get(String url){
        String result=null;
        try {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            result=response.body().string();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
    }

    public static void main(String[] args) {
        String result = get(" http://localhost:8801");
        System.out.println("the result is:"+result);
    }
}
