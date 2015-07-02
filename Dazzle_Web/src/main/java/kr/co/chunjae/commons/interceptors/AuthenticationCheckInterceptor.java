package kr.co.chunjae.commons.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.chunjae.constants.SessionCode;
import kr.co.chunjae.entities.user.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 인증정보를 체크한다.
 * @author session
 *
 */
public class AuthenticationCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		UserEntity admLoginSession = (UserEntity) request.getSession().getAttribute(SessionCode.ADMIN_LOGIN_SESSION);
		if (admLoginSession == null) {
			// Ajax 호출인 경우
			if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with")) || "Ajax".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
				response.sendRedirect(request.getContextPath() + "/error/commonErrorAjax?errorCode=sessionExpired");
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/login?resultCode=authFail");
			}
			return false;
		}
		logger.info("인터셉터 체크 : 인증체크");
		return true;
	}
}
