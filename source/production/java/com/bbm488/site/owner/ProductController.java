package com.bbm488.site.owner;

import com.bbm488.site.Product;
import com.bbm488.site.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    private static volatile int PRODUCT_ID_SEQUENCE = 1;

    @Autowired
    private ProductDao dao;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model)
    {
        model.put("productDB", dao.getDatabase());
        return "owner/product/list";
    }

    @RequestMapping(value = {"create"}, method = RequestMethod.GET)
    public String create(Map<String, Object> model)
    {
        model.put("productCrudForm", new ProductController.Form());
        return "owner/product/create";
    }

    @RequestMapping(value = {"create"}, method = RequestMethod.POST)
    public View create(Form form)
    {
        Product product = new Product();
        product.setID(getNextProductID());
        product.setName(form.getName());
        product.setPrice(Integer.parseInt(form.getPrice()));
        dao.create(product.getID(),product);
        return new RedirectView("/owner/product/list", true, false);
    }


    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(Map<String, Object> model,
                             @PathVariable("id") int id)
    {
        Product product = dao.find(id);
        if(product == null)
            return this.getListRedirectModelAndView();
        model.put("product", product);
        model.put("id", id);
        model.put("productCrudForm", new ProductController.Form());

        return new ModelAndView("owner/product/edit");
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public View edit(ProductController.Form form)
    {
        if(form.getCheckbox()) {
            return new RedirectView("/owner/product/delete/" + form.oldKey, true, false);
        }
        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(Integer.parseInt(form.getPrice()));
        product.setName(form.getName());
        dao.update(Integer.parseInt(form.getOldKey()),product);
        return new RedirectView("/owner/product/list", true, false);

    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public View delete(@PathVariable("id") String id)
    {
        dao.delete(Integer.parseInt(id));
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

    private synchronized int getNextProductID()
    {return this.PRODUCT_ID_SEQUENCE++;}

    
    public static class Form
    {
        private String ID;
        private String name;
        private String price;
        private String oldKey;
        private Boolean checkbox;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
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

    public static int getProductIdSequence() {
        return PRODUCT_ID_SEQUENCE;
    }

    public static void setProductIdSequence(int productIdSequence) {
        PRODUCT_ID_SEQUENCE = productIdSequence;
    }
}
