package org.engdream.ueditor.web.controller;

import com.alibaba.fastjson.JSONException;
import org.engdream.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by heyx on 2017/4/2.
 */
@Controller
public class UEditorController {

    @RequestMapping("ueditor")
    @ResponseBody
    public String exec(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String rootPath = request.getServletContext().getRealPath("/");
        response.setHeader("Content-Type" , "text/html");
        try {
            return new ActionEnter( request, rootPath ).exec();
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
