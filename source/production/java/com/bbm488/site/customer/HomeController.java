package com.bbm488.site.customer;

import com.bbm488.site.OrderDao;
import com.bbm488.site.owner.CustomerDao;
import com.bbm488.site.owner.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Aybars on 9.04.2017.
 */

@Controller
@RequestMapping("customer")
public class HomeController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = {"", "home"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model, HttpSession session)
    {
        model.put("customer", customerDao.find((String)session.getAttribute("username")));
        return "customer/home";
    }

    @RequestMapping(value = "edit/{uname}", method = RequestMethod.GET)
    public ModelAndView edit(Map<String, Object> model,
                             @PathVariable("uname") String uname)
    {
        Customer customer = customerDao.find(uname);
        if(customer == null)
            return this.getListRedirectModelAndView();
        model.put("customer", customer);
        model.put("uname", uname);
        model.put("customerCrudForm", new Form());

        return new ModelAndView("customer/edit");
    }

    @RequestMapping(value = "edit/{uname}", method = RequestMethod.POST)
    public View edit(Form form)
    {
        Customer customer = new Customer();
        customer.setUname(form.getKey());
        customer.setUpass(form.getUpass());
        customer.setName(form.getName());
        customer.setSurname(form.getSurname());
        customer.setFloor(form.getFloor());
        customer.setApt(form.getApt());
        customer.setRoom(form.getRoom());
        customerDao.update(form.getKey(),customer);
        return new RedirectView("/customer/home", true, false);

    }


    private ModelAndView getListRedirectModelAndView()
    {
        return new ModelAndView(this.getListRedirectView());
    }

    private View getListRedirectView()
    {
        return new RedirectView("/customer/home", true, false);
    }


    public static class Form
    {
        private String upass;
        private String name;
        private String surname;
        private String floor;
        private String apt;
        private String room;
        private String key;


        public String getUpass() {
            return upass;
        }

        public void setUpass(String upass) {
            this.upass = upass;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getApt() {
            return apt;
        }

        public void setApt(String apt) {
            this.apt = apt;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String oldKey) {
            this.key = oldKey;
        }
    }

}
