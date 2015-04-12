//package com.renovator.weixin;
//
///**
// * Created by tangld on 2015/4/7.
// * <p/>
// * <p/>
// * 微信公共账号发送给账号
// *
// * @param content 文本内容
// * @param toUser 微信用户
// * @return
// */
//
///**
// * 微信公共账号发送给账号
// *
// * @param content 文本内容
// * @param toUser  微信用户
// * @return
// */
//class WeixinHelper{
//
//        public void sendTextMessageToUser(String content, String toUser) {
//            String json = "{\"touser\": \"" + toUser + "\",\"msgtype\": \"text\", \"text\": {\"content\": \"" + content + "\"}}";
//            //获取access_token
//            GetExistAccessToken getExistAccessToken = GetExistAccessToken.getInstance();
//
//            String accessToken = getExistAccessToken.getExistAccessToken();
//
//            //获取请求路径
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
//         * 微信公共账号发送给账号(本方法限制使用的消息类型是语音或者图片)
//         *
//         * @param mediaId     图片或者语音内容
//         * @param toUser      微信用户
//         * @param messageType 消息类型
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
//            //获取access_token
//
//            GetExistAccessToken getExistAccessToken = GetExistAccessToken.getInstance();
//
//            String accessToken = getExistAccessToken.getExistAccessToken();
//
//            //获取请求路径
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
//         * 发送图文给所有的用户
//         *
//         * @param openId 用户的id
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
//            //获取access_token
//
//            String access_token = mediaUtil.getAccess_token();
//
//            //获取请求路径
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
//         * 连接请求微信后台接口
//         *
//         * @param action 接口url
//         * @param json   请求接口传送的json字符串
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
//                System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//
//                System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
//
//                http.connect();
//
//                OutputStream os = http.getOutputStream();
//
//                os.write(json.getBytes("UTF-8"));// 传入参数
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
//                System.out.println("请求返回结果:" + result);
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
//        // 上传文件请求路径
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
//            http.setRequestMethod("GET"); // 必须是get方式请求
//
//            http.setRequestProperty("Content-Type",
//
//                    "application/x-www-form-urlencoded");
//
//            http.setDoOutput(true);
//
//            http.setDoInput(true);
//
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
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