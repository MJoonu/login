package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;


    //    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * V1
     */
//    @GetMapping("/")
//    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
//
//        if (memberId == null) {
//            return "home";
//        }
//
//        //로그인
//        Member loginMember = memberRepository.findById(memberId);
//        if (loginMember == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }


    /**
     * V2
     */
//    @GetMapping("/")
//    public String homeLoginV2(HttpServletRequest request, Model model) {
//
//        // checking saved data  in session manager
//        Member member = (Member) sessionManager.getSession(request);
//
//        //로그인
//        if (member == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", member);
//        return "loginHome";
//    }

    /**
     * V3
     */
//    @GetMapping("/")
//    public String homeLoginV3(HttpServletRequest request, Model model) {
//
//        // there is no session page move to 'home'
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "home";
//        }
//
//        // check member data in HTTPSession
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        // there is no member data move to 'home'
//        if (member == null) {
//            return "home";
//        }
//
//        // success login
//        model.addAttribute("member", member);
//        return "loginHome";
//    }

    /**
     * V3 Spring
     */
//    @GetMapping("/")
//    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
//            Member loginMember,
//            Model model) {
//
//        if (loginMember == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }

    /**
     * V3 Spring ArgumentResolver
     */
    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model) {

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}