package com.renovator.util.weixin;


import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.renovator.util.WeixinUtil;
import com.renovator.util.task.AccessTokenService;


/** 
 * @author wade 
 * @version 2015年4月20日 下午11:46:34 
 */
public class GroupUtil extends WeixinUtil {
    
    
    private static String create_group_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    private static String delete_group_url = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
    private static String batch_move_to_group_url = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
    private static String show_groups = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    
    
   public static JSONObject  getGroups(){
       JSONObject result = httpRequest(show_groups.replace(ACCESS_TOKEN, AccessTokenService.getAccessToken()), GET, null);
       return result;
   }
    
    
    /**
     * 
     * @param groupName
     * @return group_id
     * @throws JSONException
     */
    public static int createGroup(String groupName) throws JSONException{
        
        JSONObject groupPostData = new JSONObject();
        JSONObject nameData = new JSONObject();
        nameData.put("name", groupName);
        groupPostData.put("group", nameData);
        
        JSONObject result= httpRequest(create_group_url.replace(ACCESS_TOKEN, AccessTokenService.getAccessToken()), POST ,groupPostData.toString());
        
        return result.getJSONObject("group").getInt("id");
    }
    
    public static String deleteGroup (int groupid) throws JSONException{
        JSONObject groupPostData = new JSONObject();
        JSONObject nameData = new JSONObject();
        nameData.put("id", groupid);
        groupPostData.put("group", nameData);
        JSONObject result= httpRequest(delete_group_url.replace(ACCESS_TOKEN, AccessTokenService.getAccessToken()), POST ,groupPostData.toString());
        
        return result.getString("errmsg");
    }
    
    
    public static String getGroupIdByname(String groupName){
        return null;
    }
    
    public static String moveToGroup(int groupId,List<String> openids) throws JSONException{
        JSONObject batchMoveToGroupData = new JSONObject();
        batchMoveToGroupData.put("openid_list", openids);
        batchMoveToGroupData.put("to_groupid", groupId);
        JSONObject result = httpRequest(batch_move_to_group_url.replace(ACCESS_TOKEN, AccessTokenService.getAccessToken()),POST, batchMoveToGroupData.toString());
        System.out.println(batch_move_to_group_url.toString());
        return result.getString("errmsg");
    }
    
    public static String moveToGroup(String groupName,List<String> openid){
        return null;
    }
    
    
    public static int batchMoveToRandomGroup(List<String> openids) throws JSONException{
        int groupId = createGroup(System.currentTimeMillis()+"");
         moveToGroup(groupId, openids);
        return groupId;
    }

}
