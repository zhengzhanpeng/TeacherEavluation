package com.hdxy.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hdxy.pojo.CheckUrls;

@Controller
public class UserVisitInterceptor implements HandlerInterceptor {

	@Autowired
	private CheckUrls checkUrls;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURL().toString();
		if(!needCheck(url)) {
			return true;
		}
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId != null && userId != 0) {
			return true;
		}
		request.getRequestDispatcher("login").forward(request, response);
		return false;
	}

	private boolean needCheck(String url) {
		String str;
		List<String> checkUrl= this.checkUrls.getUserUrls();
		for (int i = 0; i < checkUrl.size(); i++) {
			str = checkUrl.get(i);
			if(url.indexOf(str) >= 0) {
				return true;
			}
		}
		return false;
	}
}
