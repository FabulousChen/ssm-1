package com.project.hrm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.project.hrm.domain.User;

public class Authorizedlnterceptor implements HandlerInterceptor{
   private static final String[] IGNORE_URI={"/index","/login"};
   /*
    * �÷�����������������ɺ�ִ��,��Ҫ����������Դ
    */
   @Override
   public void afterCompletion(HttpServletRequest request,
		   HttpServletResponse response,Object handler,Exception exception)throws Exception{
	   
   }
   /*
    * �÷�����Controller�ķ������ú�ִ��,��ModelAndView���в���
    */
   @Override
   public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView mv)throws Exception{
	   
   }
   /*
    * preHandle�������ڴ��������أ��÷�����Controller����ǰ����
    */
   @Override
   public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
	  boolean flag=false;
	  String servletPath=request.getServletPath();
	  for(String s:IGNORE_URI){
		  if(servletPath.contains(s))
			  flag=true;
		  break;
	  }
	  if(!flag){
		  User user=(User)request.getSession().getAttribute("user");
		  if(user==null){
			  System.out.println("��������");
			  request.getRequestDispatcher("/index.jsp").forward(request,response);
		  }else{
			  flag=true;
		  }
	  }
	  return flag;
   }
}
