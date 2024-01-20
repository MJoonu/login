package hello.login;

import hello.login.web.argumentresolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // Filter 방식
//    @Bean
//    public FilterRegistrationBean logFilter() {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//
//        filterFilterRegistrationBean.setFilter(new LogFilter());
//        // 등록할 필터 지정
//        filterFilterRegistrationBean.setOrder(1);
//        // 필터는 체인으로 동작하는데 이 때 순서가 필요하다. 낮을 수록 먼저 동작하게 된다.
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        // 필터를 적용할 URL패턴을 지정. 한번에 여러 패턴을 지정할 수 있다.
//
//
//        /**
//         * 팁 : 실무에서 HTTP 요청시 같은 요청의 로그에 모두 같은 식별자를 자동으로 남기는 방법은 logback mdc로 검색해보자.
//         */
//        return filterFilterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean loginCheckFilter() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoginCheckFilter());
//        registrationBean.setOrder(2);
//        registrationBean.addUrlPatterns("/*");
//
//        return registrationBean;
//    }


    //Interceptor 방식

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // logInterceptor register
//        registry.addInterceptor(new LogInterceptor()) // 인터셉터 등록
//                .order(1) // 인터셉터의호출 순서를 지정, 낮을수록 먼저 호출
//                .addPathPatterns("/**") // 인터셉터를 적용할 URL패턴 지정
//                .excludePathPatterns("/css/**", "/*.ico", "/error"); //인터셉터에서 제외할 패턴 지정
//
//        /**
//         * PathPatterns 공식 문서
//         * ? 한 문자 일치
//         * * 경로(/) 안에서 0개 이상의 문자 일치
//         * ** 경로 끝까지 0개 이상의 경로(/) 일치
//         * {spring} 경로(/)와 일치하고 spring이라는 변수로 캡처
//         * {spring:[a-z]+} matches the regexp [a-z]+ as a path variable named "spring" {spring:[a-z]+} regexp [a-z]+ 와 일치하고, "spring" 경로 변수로 캡처
//         * {*spring} 경로가 끝날 때 까지 0개 이상의 경로(/)와 일치하고 spring이라는 변수로 캡처
//         */
//
//        // loginCheckInterceptor register
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/members/add", "/login", "logout"
//                                    ,"/css/**", "/*.ico", "/error");
//
//    }



    // ArgumentResolver 방식
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
}
