//package com.renovator.weixin;
//
///**
// * Created by tangld on 2015/4/7.
// * <p/>
// * <p/>
// * ΢�Ź����˺ŷ��͸��˺�
// *
// * @param content �ı�����
// * @param toUser ΢���û�
// * @return
// */
//
///**
// * ΢�Ź����˺ŷ��͸��˺�
// *
// * @param content �ı�����
// * @param toUser  ΢���û�
// * @return
// */
//class WeixinHelper{
//
//        public void sendTextMessageToUser(String content, String toUser) {
//            String json = "{\"touser\": \"" + toUser + "\",\"msgtype\": \"text\", \"text\": {\"content\": \"" + content + "\"}}";
//            //��ȡaccess_token
//            GetExistAccessToken getExistAccessToken = GetExistAccessToken.getInstance();
//
//            String accessToken = getExistAccessToken.getExistAccessToken();
//
//            //��ȡ����·��
//
//            String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
//
//            System.out.println("json:" + json);
//
//            try {
//
//                connectWeiXinInterface(action, json);
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }
//
//        }
//
//        /**
//         * ΢�Ź����˺ŷ��͸��˺�(����������ʹ�õ���Ϣ��������������ͼƬ)
//         *
//         * @param mediaId     ͼƬ������������
//         * @param toUser      ΢���û�
//         * @param messageType ��Ϣ����
//         * @return
//         */
//
//        public void sendPicOrVoiceMessageToUser(String mediaId, String toUser, String msgType) {
//
//            String json = null;
//
//            if (msgType.equals(REQ_MESSAGE_TYPE_IMAGE)) {
//
//                json = "{\"touser\": \"" + toUser + "\",\"msgtype\": \"image\", \"image\": {\"media_id\": \"" + mediaId + "\"}}";
//
//            } else if (msgType.equals(REQ_MESSAGE_TYPE_VOICE)) {
//
//                json = "{\"touser\": \"" + toUser + "\",\"msgtype\": \"voice\", \"voice\": {\"media_id\": \"" + mediaId + "\"}}";
//
//            }
//
//            //��ȡaccess_token
//
//            GetExistAccessToken getExistAccessToken = GetExistAccessToken.getInstance();
//
//            String accessToken = getExistAccessToken.getExistAccessToken();
//
//            //��ȡ����·��
//
//            String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
//
//            try {
//
//                connectWeiXinInterface(action, json);
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }
//
//        }
//
//        /**
//         * ����ͼ�ĸ����е��û�
//         *
//         * @param openId �û���id
//         */
//
//        public void sendNewsToUser(String openId) {
//
//            MediaUtil mediaUtil = MediaUtil.getInstance();
//
//            ArrayList<Object> articles = new ArrayList<Object>();
//
//            Article a = new Article();
//
//            articles.add(a);
//
//            String str = JsonUtil.getJsonStrFromList(articles);
//
//            String json = "{\"touser\":\"" + openId + "\",\"msgtype\":\"news\",\"news\":" +
//
//                    "{\"articles\":" + str + "}" + "}";
//
//            json = json.replace("picUrl", "picurl");
//
//            System.out.println(json);
//
//            //��ȡaccess_token
//
//            String access_token = mediaUtil.getAccess_token();
//
//            //��ȡ����·��
//
//            String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access_token;
//
//            try {
//
//                connectWeiXinInterface(action, json);
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }
//
//        }
//
//        /**
//         * ��������΢�ź�̨�ӿ�
//         *
//         * @param action �ӿ�url
//         * @param json   ����ӿڴ��͵�json�ַ���
//         */
//
//        public void connectWeiXinInterface(String action, String json) {
//
//            URL url;
//
//            try {
//
//                url = new URL(action);
//
//                HttpURLConnection http = (HttpURLConnection) url.openConnection();
//
//                http.setRequestMethod("POST");
//
//                http.setRequestProperty("Content-Type",
//
//                        "application/x-www-form-urlencoded");
//
//                http.setDoOutput(true);
//
//                http.setDoInput(true);
//
//                System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ӳ�ʱ30��
//
//                System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ��ȡ��ʱ30��
//
//                http.connect();
//
//                OutputStream os = http.getOutputStream();
//
//                os.write(json.getBytes("UTF-8"));// �������
//
//                InputStream is = http.getInputStream();
//
//                int size = is.available();
//
//                byte[] jsonBytes = new byte[size];
//
//                is.read(jsonBytes);
//
//                String result = new String(jsonBytes, "UTF-8");
//
//                System.out.println("���󷵻ؽ��:" + result);
//
//                os.flush();
//
//                os.close();
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }
//
//        }
//
//    public  List<String> getAllWeiXinUser() {
//
//        GetExistAccessToken getExistAccessToken = GetExistAccessToken.getInstance();
//
//        String accessToken = getExistAccessToken.getExistAccessToken();
//
//        List<String> openIds = new ArrayList<String>();
//
//        // �ϴ��ļ�����·��
//
//        String action = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
//
//                + accessToken;
//
//        try {
//
//            URL urlGet = new URL(action);
//
//            HttpURLConnection http = (HttpURLConnection) urlGet
//
//                    .openConnection();
//
//            http.setRequestMethod("GET"); // ������get��ʽ����
//
//            http.setRequestProperty("Content-Type",
//
//                    "application/x-www-form-urlencoded");
//
//            http.setDoOutput(true);
//
//            http.setDoInput(true);
//
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ӳ�ʱ30��
//
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ��ȡ��ʱ30��
//
//            http.connect();
//
//            InputStream is = http.getInputStream();
//
//            int size = is.available();
//
//            byte[] jsonBytes = new byte[size];
//
//            is.read(jsonBytes);
//
//            String result = new String(jsonBytes, "UTF-8");
//
//            JSONObject jsonObj = new JSONObject(result);
//
//            System.out.println("users" + jsonObj.get("data"));
//
//            JSONObject json1 = new JSONObject(jsonObj.get("data").toString());
//
//            System.out.println(json1.toString());
//
//            JSONArray json2 = new JSONArray(json1.get("openid").toString());
//
//            for (int i = 0; i < json2.length(); i++) {
//
//                openIds.add(i, json2.getString(i));
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        return openIds;
//
//    }
//}