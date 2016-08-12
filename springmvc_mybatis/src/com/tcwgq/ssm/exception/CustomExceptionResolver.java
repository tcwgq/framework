package com.tcwgq.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		// handler就是处理器适配器要处理的handler对象
		String message = null;
		// if (exception instanceof CustomException) {
		// message = exception.getMessage();
		// } else {
		// message = "未知错误";
		// }
		CustomException ex = null;
		if (exception instanceof CustomException) {
			ex = (CustomException) exception;
		} else {
			ex = new CustomException("未知错误");
		}
		message = ex.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("error", message);
		return modelAndView;
	}

}
