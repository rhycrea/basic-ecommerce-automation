package com.bbm488.site.customer;

/**
 * Created by Aybars on 12.04.2017.
 */
import com.bbm488.site.Order;
import com.bbm488.site.OrderDao;
import com.bbm488.site.owner.CustomerDao;
import com.bbm488.site.owner.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Aybars on 9.04.2017.
 */

@Controller
@RequestMapping("customer")
public class OrderController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;


    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ModelAndView order(Map<String, Object> model, HttpSession session)
    {
        model.put("productDB", productDao.findAll());
        model.put("orderForm", new Form());
        return new ModelAndView("customer/order");
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "order", method = RequestMethod.POST)
    public View order(Form form, HttpSession session)
    {
        Order order = new Order();
        order.setBuyer((String)session.getAttribute("username"));
        order.setPcs(form.pcs);
        order.setProduct(productDao.findById(form.productID));
        order.setTotalPrice(form.pcs * productDao.findById(form.productID).getPrice());
        order.setIsSent(false);
        order.setSentDate(null);
        order.setProductName(productDao.findById(form.productID).getName());
        order.setOrderDate(Instant.now());
        orderDao.save(order);
        return new RedirectView("/customer/myorders", true, false);

    }

    @RequestMapping(value = "myorders", method = RequestMethod.GET)
    public ModelAndView listorders(Map<String, Object> model, HttpSession session)
    {
        if (!orderDao.findAll().isEmpty()) {
            Hashtable results = new Hashtable<String,Order>();
            Iterator<Order> it = orderDao.findAll().iterator();
            while (it.hasNext()) {
                Order order = it.next();
                 if(order.getBuyer().equals(session.getAttribute("username"))) {
                     results.put(order.getID(),order);
                 }

            }
            model.put("orderForm", new Form());
            model.put("results", results);
        }
        return new ModelAndView("customer/myorders");
    }

    private ModelAndView getListRedirectModelAndView()
    {
        return new ModelAndView(this.getListRedirectView());
    }

    private View getListRedirectView()
    {
        return new RedirectView("/customer/home", true, false);
    }

    public static class Form {
        private int productID;
        private int pcs;

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public int getPcs() {
            return pcs;
        }

        public void setPcs(int pcs) {
            this.pcs = pcs;
        }
    }
}