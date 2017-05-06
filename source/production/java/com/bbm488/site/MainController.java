package com.bbm488.site;

import com.bbm488.site.customer.Customer;
import com.bbm488.site.customer.OrderController;
import com.bbm488.site.owner.CustomerDao;
import com.bbm488.site.owner.Owner;
import com.bbm488.site.owner.ProductController;
import com.bbm488.site.owner.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

@Controller
public class MainController
{
    @PostConstruct
    public void init() {
        //defining username/password for the administrator of the system.
        Owner.setUname("patron");
        Owner.setUpass("patron");
    }

    @Autowired
    private CustomerDao customerDao;


    @RequestMapping("/")
    public ModelAndView index()
    {
        return this.customerPageRedirect();
    }

    @RequestMapping(value = "logout")
    public RedirectView logout(HttpSession session)
    {
        session.invalidate();
        return new RedirectView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(Map<String, Object> model, HttpSession session)
    {
        if(session.getAttribute("username") != null) {
            if (session.getAttribute("username").equals(customerDao.getOwnerUname()))
                return this.ownerPageRedirect();
            else
                return this.customerPageRedirect();
        }

        model.put("loginFailed", false);
        model.put("loginForm", new Form());

        return new ModelAndView("/login");
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(Map<String, Object> model, HttpSession session,
                              HttpServletRequest request, Form form)
    {
        if(session.getAttribute("username") != null) {
            if (session.getAttribute("username").equals(customerDao.getOwnerUname()))
                return this.ownerPageRedirect();
            else
                return this.customerPageRedirect();
        }


        if( form.getUsername().equals(customerDao.getOwnerUname()) && form.getPassword().equals(customerDao.getOwnerUpass()) ) {
            System.out.println("itshere");
            session.setAttribute("username", form.getUsername());
            request.changeSessionId();
            return this.ownerPageRedirect();
        }

        if(form.getUsername() == null || form.getPassword() == null ||
                customerDao.findByUname(form.getUsername()) == null ||
                !form.getPassword().equals(customerDao.findByUname(form.getUsername()).getUpass()) )
        {
            form.setPassword(null);
            model.put("loginFailed", true);
            model.put("loginForm", form);
            return new ModelAndView("login");
        }

        session.setAttribute("username", form.getUsername());
        request.changeSessionId();
        return this.customerPageRedirect();
    }


    private ModelAndView customerPageRedirect()
    {

        return new ModelAndView(new RedirectView("/customer", true, false));
    }

    private ModelAndView ownerPageRedirect()
    {
        return new ModelAndView(new RedirectView("/owner/customer_crud", true, false));
    }

    public static class Form
    {
        private String username;
        private String password;

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
    }
}
