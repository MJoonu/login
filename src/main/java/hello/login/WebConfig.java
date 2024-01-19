package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        filterFilterRegistrationBean.setFilter(new LogFilter());
        // 등록할 필터 지정
        filterFilterRegistrationBean.setOrder(1);
        // 필터는 체인으로 동작하는데 이 때 순서가 필요하다. 낮을 수록 먼저 동작하게 된다.
        filterFilterRegistrationBean.addUrlPatterns("/*");
        // 필터를 적용할 URL패턴을 지정. 한번에 여러 패턴을 지정할 수 있다.


        /**
         * 팁 : 실무에서 HTTP 요청시 같은 요청의 로그에 모두 같은 식별자를 자동으로 남기는 방법은 logback mdc로 검색해보자.
         */
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter());
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
