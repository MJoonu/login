package hello.login.web.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 필터를 사용하기 위한 필터 인터페이스 구현
 */
@Slf4j
public class LogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        log.info("log filter init");
    }

    /**
     * HTTP요청 시 doFilter 가 호출
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // public void doFilter(ServletRequest request...) 이 부분은 HTTP요청이 아닌 경우 (HTTPS 등) 까지 고려해서 만든 인터페이스이다.
        // HttpServletRequest httpServletRequest = (HttpServletRequest) request; 로 다운케스팅 해서 사용하면 된다.

        String requestUrl = httpServletRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        //요청 구분을 위한 임의의 uuid 생성

        try {

            log.info("REQ [{}] [{}]", uuid, requestUrl);
            chain.doFilter(request, response);
            // 다음 필터가 존재하면 필터를 호출, 필터가 없으면 서블릿을 호출
            // 만일 이부분이 없으면 다음 단계로 진행되지 않음.

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RES [{}] [{}]", uuid, requestUrl);
        }
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        log.info("log filter destroy");
    }
}
