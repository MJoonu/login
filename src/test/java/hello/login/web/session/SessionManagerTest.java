package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        //create session
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        // save cookie about request
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());


        //check session
        Object resultSession =  sessionManager.getSession(request);
        assertThat(resultSession).isEqualTo(member);

        //expire
        sessionManager.expire(request);
        Object expiredSession = sessionManager.getSession(request);
        assertThat(expiredSession).isNull();
    }
}
