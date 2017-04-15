package com.bbm488.site.customer;

/**
 * Created by Aybars on 7.04.2017.
 */

import com.bbm488.site.owner.Owner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        if(session == null || session.getAttribute("username") == null)
        {
            ((HttpServletResponse)response).sendRedirect(
                    ((HttpServletRequest) request).getContextPath() + "/login"
            );
        }

        else if ( Owner.getUname().equals(session.getAttribute("username")) ) {
            ((HttpServletResponse)response).sendRedirect(
                    ((HttpServletRequest) request).getContextPath() + "/owner"
            );
        }

        else
            chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException { }

    @Override
    public void destroy() { }
}
