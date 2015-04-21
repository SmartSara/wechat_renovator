package com.renovator.weixin;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;

import com.renovator.util.weixin.GroupUtil;


/** 
 * @author wade 
 * @version 2015年4月21日 上午12:30:14 
 */
public class WeixinUtilTest {
    
    
    @Test
    public void groupTest() throws JSONException{
        List<String> openids = Arrays.asList("osNcxs4tQCi6d0a1jjX2idof_wdg");
        int batchMoveToRandomGroup = GroupUtil.batchMoveToRandomGroup(openids);
        
        System.out.println(GroupUtil.getGroups().toString(2));
        
    }
    
    @Test
    public void groupDeleteTest() throws JSONException{
        
        int [] groupids = {102,103,104,105,106,107};
        
        for (int groupid : groupids) {
            
           GroupUtil.deleteGroup(groupid);
            
        }
        System.out.println(GroupUtil.getGroups().toString(2));
        
    }
    
    
    
    

}
