package com.bbm488.config;

import com.bbm488.site.customer.CustomerFilter;
import com.bbm488.site.owner.OwnerFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@SuppressWarnings("unused")
public class Bootstrap implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext container) throws ServletException
    {
        container.getServletRegistration("default").addMapping("/resources/*");

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletContext =
                new AnnotationConfigWebApplicationContext();
        servletContext.register(ServletContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springDispatcher", new DispatcherServlet(servletContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic ownerFilter = container.addFilter(
                "ownerFilter", new OwnerFilter()
        );
        ownerFilter.addMappingForUrlPatterns(
                null, false,  "/owner", "/owner/*"
        );

        FilterRegistration.Dynamic customerFilter = container.addFilter(
                "customerFilter", new CustomerFilter()
        );
        customerFilter.addMappingForUrlPatterns(
                null, false, "/customer", "/customer/*"
        );

    }
}
