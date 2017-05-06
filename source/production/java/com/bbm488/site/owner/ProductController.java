package com.bbm488.site.owner;

import com.bbm488.site.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Aybars on 7.04.2017.
 */
@Controller
@RequestMapping("owner/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model)
    {
        model.put("productDB", productDao.findAll());
        return "owner/product/list";
    }


    @RequestMapping(value = {"create"}, method = RequestMethod.GET)
    public String create(Map<String, Object> model)
    {
        model.put("productCrudForm", new ProductController.Form());
        return "owner/product/create";
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = {"create"}, method = RequestMethod.POST)
    public View create(Form form)
    {
        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(Integer.parseInt(form.getPrice()));
        productDao.save(product);
        return new RedirectView("/owner/product/list", true, false);
    }


    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(Map<String, Object> model,
                             @PathVariable("id") int id)
    {
        Product product = productDao.findById(id);
        if(product == null)
            return this.getListRedirectModelAndView();
        model.put("product", product);
        model.put("productCrudForm", new ProductController.Form());

        return new ModelAndView("owner/product/edit");
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public View edit(ProductController.Form form)
    {
        if(form.getCheckbox()) {
            return new RedirectView("/owner/product/delete/" + form.ID, true, false);
        }
        Product product = new Product();
        product.setID(form.getID());
        product.setName(form.getName());
        product.setPrice(Integer.parseInt(form.getPrice()));
        productDao.saveOrUpdate(product);
        return new RedirectView("/owner/product/list", true, false);

    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public View delete(@PathVariable("id") String id)
    {
        productDao.delete(productDao.findById(Integer.parseInt(id)));
        return new RedirectView("/owner/product/list", true, false);

    }


    private ModelAndView getListRedirectModelAndView()
    {
        return new ModelAndView(this.getListRedirectView());
    }

    private View getListRedirectView()
    {
        return new RedirectView("/owner/product/list", true, false);
    }

    public static class Form
    {
        private int ID;
        private String name;
        private String price;
        private Boolean checkbox;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Boolean getCheckbox() {
            return checkbox;
        }

        public void setCheckbox(Boolean checkbox) {
            this.checkbox = checkbox;
        }
    }
}
