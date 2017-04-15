package com.bbm488.site.owner;

import com.bbm488.site.Order;
import com.bbm488.site.OrderDao;
import com.bbm488.site.customer.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.Map;

/**
 * Created by Aybars on 12.04.2017.
 */
@Controller
@RequestMapping("owner/delivery")
public class DeliveryController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pendingOrder(Map<String, Object> model, HttpSession session)
    {
        model.put("orderDB", orderDao.getDatabase());
        model.put("deliveryForm", new Form());
        return new ModelAndView("owner/delivery");
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public View deliver(Form form) {
        Order order = orderDao.find(form.getOrderID());
        order.setIsSent(true);
        order.setSentDate(Instant.now());
        orderDao.update(form.getOrderID(),order);
        return new RedirectView("/owner/delivery", true, false);
    }

    public static class Form {
        private int orderID;

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }
    }
}