package com.nacos.alibaba.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class WechatUtils {
    private static Log logger = LogFactory.getLog(WechatUtils.class);
    private final static String DSB_APP_PATH = "/config/wechat/mp/mp-dsb.properties";

    public static String getAccessToken() throws Exception {
//        Properties prop = PropertiesConfigUtils.getWxProperties(DSB_APP_PATH);
        String secret = "1afec64c302724cbf5ee0dd9073ef815";
        String appId = "wxdb94abfa6ecef34d";
//        String secret = prop.getProperty("wechat.mp.secret");
//        String appId = prop.getProperty("wechat.pay.appId");
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
        try {
            HttpURLConnection connection = getConnection(accessTokenUrl, "GET");

            //获取返回的字符
            InputStream inputStream = connection.getInputStream();
            String message = inputStreamToString(inputStream);

            //获取access_token
            JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
            logger.info("换取access token 成功" + jsonObject);
            return jsonObject.getString("access_token");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("换取access token 失败" + e);
            throw  new Exception("换取access token失败" , e);
        }

    }

    public static AjaxJson createCustomMenu(String menu){
        AjaxJson resultmap = new AjaxJson();
        String custmMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

        //获取access_token
        String accessToken = null;
        logger.info("发送自定义菜单:" + menu);
        try {
            accessToken = getAccessToken();
            custmMenuUrl = custmMenuUrl + accessToken;
            HttpURLConnection connection = getConnection(custmMenuUrl, "POST");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(menu.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            String message = inputStreamToString(inputStream);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
            logger.info("返回信息:" + jsonObject);
            if(0 != jsonObject.getInteger("errcode")){
                logger.info("发送自定菜单失败:" + jsonObject.getString("errmsg"));
                resultmap.setSuccess(false);
                resultmap.setMsg("发送自定义菜单失败:" + jsonObject.getString("errmsg"));
            } else {
                logger.info("发送自定菜单成功:");
                resultmap.setSuccess(true);
                resultmap.setMsg("自定义菜单已发送成功,您可以在24小时后或重新关注公主号查看");
            }
        } catch (Exception e) {
            resultmap.setSuccess(false);
            resultmap.setMsg("发送自定义菜单失败");
            logger.error("发送自定义菜单失败:" + e);
            e.printStackTrace();
        }
        return resultmap;
    }

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");
        return message;
    }

    public static HttpURLConnection getConnection(String surl, String method) throws IOException {
        URL url = new URL(surl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        return connection;
    }
}