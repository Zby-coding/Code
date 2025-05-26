package cn.jeefast.common.xss;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XSS过滤
 */
public class XssFilter implements Filter {

	//不拦截的地址
	private List<String> excludedList = new ArrayList<String>();

	@Override
	public void init(FilterConfig config) throws ServletException {

		/*
		 * 这里只处理了不拦截的url地址，如果想不拦截某个字段，比如富文本字段，
		 * 需要自己在XssHttpServletRequestWrapper类中去添加逻辑
		 */
		excludedList.add("/sysXinwen/update");
		excludedList.add("/sysXinwen/save");

		excludedList.add("/sysZnhd/update");
		excludedList.add("/sysZnhd/save");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		String url = xssRequest.getServletPath();
		if(isExcluded(url)){
			chain.doFilter(request, response);
		}else{
			//使用XSS过滤
			chain.doFilter(xssRequest, response);
		}
	}

	@Override
	public void destroy() {
	}

	/**
	 * 是否不拦截
	 * @param url	请求地址
	 * @return	true不拦截，false拦截
	 */
	private boolean isExcluded(String url){
		if(StringUtils.isBlank(url)){
			return false;
		}

		for (String excluded : excludedList) {
			if(url.contains(excluded)){
				return true;
			}
		}
		return false;
	}

}