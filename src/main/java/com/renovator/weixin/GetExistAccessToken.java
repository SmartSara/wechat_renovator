package com.renovator.weixin;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangld on 2015/4/7.
 */
public class GetExistAccessToken {

    //����һ��˽�еľ�̬ȫ�ֱ�������������Ψһʵ��

    private static GetExistAccessToken getExistAccessToken;

    /// ���캯��������˽�е�

    /// �������ⲿ���޷�ʹ�� new �����������ʵ��

    private GetExistAccessToken()

    {

    }

    /// ����һ��ȫ�ַ��ʵ�

    /// ����Ϊ��̬����

    /// ��������ⲿ������ʵ�����Ϳ��Ե��ø÷���

    public static GetExistAccessToken getInstance()

    {

        //������Ա�ֻ֤ʵ����һ��

        //���ڵ�һ�ε���ʱʵ����

        //�Ժ���ñ㲻����ʵ����

        if (getExistAccessToken == null)

        {

            getExistAccessToken = new GetExistAccessToken();

        }

        return getExistAccessToken;

    }

    public String getExistAccessToken() {

        AccessTokenHelper accessTokenHelper = new AccessTokenHelper();

        String accessToken = null;

        // ��ȡXML�ļ��е�����

        String filepath = GetExistAccessToken.class.getClassLoader().getResource("accessToken.xml").getPath();

        try {

            Document document = new SAXReader().read(filepath);

            // ����������洢��HashMap��

            Map<String, String> map = new HashMap<String, String>();

            // �õ�xml��Ԫ��

            Element root = document.getRootElement();

            // �õ���Ԫ�ص�ȫ���ӽڵ�

            List<Element> elementList = root.elements();

            // ����ȫ���ӽڵ�

            for (Element e : elementList) {

                map.put(e.getName(), e.getText());

            }

            accessToken = map.get("AccessToken");

            String lastTime = map.get("AccessExpires");

            Date now = new Date();

            Date accessExpires = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTime);

            if (now.getTime() > accessExpires.getTime()) {
                accessToken = accessTokenHelper.getAccess_token();

                accessExpires = new Date(now.getTime() + 7200000);

                String nextTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(accessExpires);

                root.selectSingleNode("AccessToken").setText(accessToken);

                root.selectSingleNode("AccessExpires").setText(nextTime);

                XMLWriter writer = new XMLWriter(new FileWriter(new File(filepath)));

                writer.write(document);

                writer.close();

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return accessToken;

    }

}
