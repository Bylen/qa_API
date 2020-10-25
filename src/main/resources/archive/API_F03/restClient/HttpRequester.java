package com.qa.API_F03.restClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
/**
 * @author urPaPa
 * @date 2020/9/19 10:28
 */
public class HttpRequester {
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param method
     *            指定请求方法：GET, POST 等
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数是 name1=value1&name2=value2 的形式。
     * @return result 返回结果
     */
    public static Map<String, String> sendPost(String method, String url,
                                               String param) {
        PrintWriter out = null;
        BufferedReader br = null;
        String result = "";
        int responseCode = 0;
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 打开和URL之间的连接
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url)
                    .openConnection();

            // 发送POST请求必须设置如下两行
            // 设置可输入、 可输出
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            httpConn.setReadTimeout(150000);
            httpConn.setConnectTimeout(15000);

            // 连接后不自动跳转
            httpConn.setInstanceFollowRedirects(false);

            // 设置通用的请求属性
            httpConn.setRequestProperty("Accept-Charset", "utf-8");
            httpConn.setRequestProperty("User-Agent", "systempatch");
            httpConn.setRequestProperty("Accpet-Encoding", "gzip");

            // 设置提交方式
            httpConn.setRequestMethod(method);

            // httpConn.connect();

            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());

            // 发送请求参数
            out.print(param);
            out.flush();
            responseCode = httpConn.getResponseCode();
            map.put("code", String.valueOf(responseCode));
            // 打印 http 状态码
            // System.out.println("responseCode: " + responseCode);

            if (HttpURLConnection.HTTP_OK == responseCode) {
                // 定义BufferedReader输入流来读取URL的响应
                br = new BufferedReader(new InputStreamReader(
                        httpConn.getInputStream(), "utf-8"));
                String strLine;
                StringBuffer responseBuf = new StringBuffer();

                while ((strLine = br.readLine()) != null) {
                    responseBuf.append(strLine);
                }

                result = responseBuf.toString();
                map.put("result", result);
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}
