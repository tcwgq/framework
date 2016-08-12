package com.tcwgq.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor2 implements HandlerInterceptor {
	// 进入handler之前
	// 身份认证
	// false表示拦截
	// true表示放行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("CustomInterceptor2...preHandle");
		return true;
	}

	// 进入handler之后，返回ModelAndView之前
	// 将公用的模型数据统一指定到视图，比如菜单导航
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("CustomInterceptor2...postHandle");
	}

	// 返回ModelAndView
	// 统一的异常处理，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("CustomInterceptor2...afterCompletion");
	}
}
