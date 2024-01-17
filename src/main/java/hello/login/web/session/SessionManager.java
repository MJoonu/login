package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "MY_SESSION_ID";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();


    /**
     * create session
     */

    public void createSession(Object value, HttpServletResponse response) {
        // create session id and save session
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);


        //create Cookie
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
    }


    /**
     * check session
     *
     * @param sessionId
     * @return
     */
    public Object getSession(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            return null;
//        }
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals(SESSION_COOKIE_NAME)) {
//                return sessionStore.get(cookie.getValue());
//            }
//        }

        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }


        return sessionStore.get(cookie.getValue());
    }


    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    /**
     * expire session
     */
    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if(cookie != null) {
            sessionStore.remove(cookie.getValue());
        }
    }
}
