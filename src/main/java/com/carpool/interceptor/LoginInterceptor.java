package com.carpool.interceptor;

import com.carpool.base.BaseController;
import com.carpool.utils.Assist;
import com.carpool.utils.AuthUtil;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Logger;

import static com.carpool.utils.Assist.print;

/**
 * @author sunLei on 2018/11/20 20:24
 * @version 1.0
 * @apiNote
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("authorization");
        print(token);
        if(token!=null && AuthUtil.authLogin(token)!=null){
            return true;
        }else{
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out=response.getWriter();
            BaseController.Result responseResult=new BaseController.Result();
            responseResult.setStatusCode(0);
            responseResult.setMessage("unAuthorization");
            out.print(JSONObject.fromObject(responseResult));
            return false;
        }
    }
}
