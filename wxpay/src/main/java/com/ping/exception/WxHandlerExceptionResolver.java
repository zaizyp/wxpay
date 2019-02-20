package com.ping.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * <p>
 * Title: WxHandlerException
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 周运平
 * @date 2018年11月21日上午12:21:08
 * @version 1.0
 */
public class WxHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 上边代码变为
		WxException wxException = null;
		if (ex instanceof WxException) {
			wxException = (WxException) ex;
		} else {
			wxException = new WxException("未知错误");
		}
		// 错误信息
		String message = wxException.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		// 将错误信息传到页面
		modelAndView.addObject("message", message);
		// 指向错误页面
		modelAndView.setViewName("error");

		return modelAndView;
	}

}
