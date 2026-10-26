package ir.maktabsharif.filter;

import ir.maktabsharif.service.auxiliaryServices.logService.LogService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoggingFilter implements Filter {

         private LogService logService ;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logService = new LogService();
        String prefix = filterConfig.getInitParameter("logPrefix");
        if (prefix == null) {
            prefix = "DEFAULT-PREFIX"; // مقدار پیش‌فرض برای جلوگیری از نال‌پوینتر
        }
        logService.setPrefix(prefix);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String method = req.getMethod();
        String uri = req.getRequestURI();

        logService.log("Before " + method + " " + uri);
        logService.log("Request received");
        chain.doFilter(req, resp);
        logService.log("After " + method + " " + uri);

    }
}
