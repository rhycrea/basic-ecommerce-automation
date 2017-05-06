package com.bbm488.site.owner;

import com.bbm488.site.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * Created by Aybars on 9.04.2017.
 */

@Controller
@RequestMapping("owner/customer_crud")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model)
    {
        model.put("customerDB", customerDao.findAll());
        return "owner/customer_crud/list";
    }

    @RequestMapping(value = {"create"}, method = RequestMethod.GET)
    public String create(Map<String, Object> model)
    {
        model.put("customerCrudForm", new Form());
        return "owner/customer_crud/create";
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = {"create"}, method = RequestMethod.POST)
    public View create(Form form)
    {
        Customer customer = new Customer();
        customer.setUname(form.getUname());
        customer.setUpass(form.getUpass());
        customer.setName(form.getName());
        customer.setSurname(form.getSurname());
        customer.setFloor(form.getFloor());
        customer.setApt(form.getApt());
        customer.setRoom(form.getRoom());
        customerDao.save(customer);
        return new RedirectView("/owner/customer_crud/list", true, false);
    }

    @RequestMapping(value = "edit/{uname}", method = RequestMethod.GET)
    public ModelAndView edit(Map<String, Object> model,
                             @PathVariable("uname") String uname)
    {
        Customer customer = customerDao.findByUname(uname);
        if(customer == null)
            return this.getListRedirectModelAndView();
        model.put("customer", customer);
        model.put("uname", uname);
        model.put("customerCrudForm", new Form());

        return new ModelAndView("owner/customer_crud/edit");
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "edit/*", method = RequestMethod.POST)
    public View edit(Form form)
    {
        if(form.getCheckbox()) {
            return new RedirectView("/owner/customer_crud/delete/" + form.oldKey, true, false);
        }
        Customer customer = new Customer();
        customer.setUname(form.getUname());
        customer.setUpass(form.getUpass());
        customer.setName(form.getName());
        customer.setSurname(form.getSurname());
        customer.setFloor(form.getFloor());
        customer.setApt(form.getApt());
        customer.setRoom(form.getRoom());
        customerDao.saveOrUpdate(customer);
        return new RedirectView("/owner/customer_crud/list", true, false);

    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "delete/{uname}", method = RequestMethod.GET)
    public View delete(@PathVariable("uname") String uname)
    {
        customerDao.delete(customerDao.findByUname(uname));
        return new RedirectView("/owner/customer_crud/list", true, false);

    }

    private ModelAndView getListRedirectModelAndView()
    {
        return new ModelAndView(this.getListRedirectView());
    }

    private View getListRedirectView()
    {
        return new RedirectView("/owner/customer_crud/list", true, false);
    }


    public static class Form
    {
        private String uname;
        private String upass;
        private String name;
        private String surname;
        private String floor;
        private String apt;
        private String room;
        private String oldKey;
        private Boolean checkbox;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

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

        public String getOldKey() {
            return oldKey;
        }

        public void setOldKey(String oldKey) {
            this.oldKey = oldKey;
        }

        public Boolean getCheckbox() {
            return checkbox;
        }

        public void setCheckbox(Boolean checkbox) {
            this.checkbox = checkbox;
        }
    }

}
