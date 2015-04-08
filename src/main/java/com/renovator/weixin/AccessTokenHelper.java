package com.renovator.weixin;

import com.renovator.util.PropertyHolder;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * Created by tangld on 2015/4/7.
 */
public class AccessTokenHelper {
//    @Test
    public String getAccess_token() {

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + PropertyHolder.APPID + "&secret=" + PropertyHolder.APPSECRET;

        String accessToken = null;

        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // ������get��ʽ����
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ӳ�ʱ30��
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ��ȡ��ʱ30��
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");

            JSONObject demoJson = new JSONObject(message);

            accessToken = demoJson.getString("access_token");

            System.out.println(accessToken);

            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;

    }
}
