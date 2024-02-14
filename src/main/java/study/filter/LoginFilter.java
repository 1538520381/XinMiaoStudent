package study.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.util.AntPathMatcher;
import study.common.BaseContext;
import study.entity.result.R;
import study.enums.HttpCodeEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Persolute
 * @version 1.0
 * @description 登录过滤器
 * @email 1538520381@qq.com
 * @date 2024/2/14 16:08
 */
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private static final String[] ALL = new String[]{
            "/school/login",
            "/student/login"
    };
    private static final String[] SCHOOL = new String[]{
            "/**"
    };
    private static final String[] STUDENT = new String[]{
            "/common/**",
            "/student/login",
            "/student",
            "/student/{id}"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        if (check(ALL, requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute("school") != null) {
            if (check(SCHOOL, requestURI)) {
                BaseContext.setCurrentId((Long) request.getSession().getAttribute("school"));
                filterChain.doFilter(request, response);
            } else {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(R.error(HttpCodeEnum.NO_PERMISSION)));
            }
            return;
        }

        if (request.getSession().getAttribute("student") != null) {
            if (check(STUDENT, requestURI)) {
                BaseContext.setCurrentId((Long) request.getSession().getAttribute("student"));
                filterChain.doFilter(request, response);
            } else {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(R.error(HttpCodeEnum.NO_PERMISSION)));
            }
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(R.error(HttpCodeEnum.NO_PERMISSION)));
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 权限许可判断
     * @email 1538520381@qq.com
     * @date 2024/2/14 16:17
     */
    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }
}
