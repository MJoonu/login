package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "logout", "/css/*"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String requestURI = httpReq.getRequestURI();

        HttpServletResponse httpRes = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);

            if(isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpReq.getSession(false);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    // 로그인으로 redirect
                    httpRes.sendRedirect("/login?redirectURL=" + requestURI);

                    return; // 중요! 미인증 사용자는 다음으로 보내지않고 끝내야함.
                }
                chain.doFilter(request, response);
            }

        } catch (Exception e) {
            throw e; // 예외 로깅도 가능하나, 톰켓으로 예외 보내주기
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }



    /**
     * whitelist 인 경우 체크하지 않음
     * @param requestURI
     * @return
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }


}
