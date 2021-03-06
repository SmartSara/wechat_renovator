package com.renovator.controller;

import com.renovator.service.RenovatorTestService;
import com.renovator.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by darlingtld on 2015/2/10.
 */
@Scope("prototype")
@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private RenovatorTestService renovatorService;

    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
    }


    @RequestMapping(method = RequestMethod.POST)
    public void respond(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String respXml = renovatorService.processRequest(request);
        ServletOutputStream outputStream = response.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printStream.write(respXml.getBytes("utf-8"));
        printStream.close();
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public @ResponseBody String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
       return "test";
    }

}
