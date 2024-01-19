package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.zip.DataFormatException;

@Slf4j
@RestController
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "no session";
        }

        // get session data
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name = {} , value = {}", name, session.getAttribute(name)));

        // JSESSIONID ex) 34B14F008AA3527C9F8ED620EFD7A4E1
        log.info("sessionId = {}", session.getId());
        // session active time ex)1800초, (30분)
        log.info("maxInactiveInterval = {}", session.getMaxInactiveInterval());
        // session create time
        log.info("creationTime = {}", new Date(session.getCreationTime()));
        // user access time(session's user)
        log.info("lastAccessedTime = {}", new Date(session.getLastAccessedTime()));
        // client request to server sending sessionId and server will check session it's old one or new one
        log.info("isNew = {}", session.isNew());

        return "print session";
    }
}
