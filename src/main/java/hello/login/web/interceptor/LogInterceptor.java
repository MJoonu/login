package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override // controller 호출 전에 실행 (핸들러 어댑터 실행 전)
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String reqURI = request.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, uuid);


        //@RequestMapping : HandlerMethod
        // 정적 리소스 : ResourceHttpRequestHandler
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 호출할 컨트롤러의 메서드의 모든 정보
        }

        log.info("REQ [{}][{}][{}]",uuid, reqURI,handler);


        return true;
    }

    @Override // controller 호출 후에 실행 (핸들러 어댑터 호출 후)
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    @Override // view의 랜더링 이후 실행
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String reqURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
        log.info("RES [{}][{}]", logId, reqURI);
        if (ex != null) {
            log.error("afterCompletion error!", ex);
        }
    }
}
