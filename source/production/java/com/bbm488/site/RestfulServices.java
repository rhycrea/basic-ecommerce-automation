package com.bbm488.site;

import com.bbm488.site.customer.OrderController;
import com.bbm488.site.owner.CustomerDao;
import com.bbm488.site.owner.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Aybars on 26.04.2017.
 */

@RestController
public class RestfulServices {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/restapi/list", params = {"username"}, method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrders(@RequestParam(value = "username") String uname) {
        if (!orderDao.findAll().isEmpty() && customerDao.findByUname(uname) != null) {
            List<Order> results = new ArrayList<Order>();
            Iterator<Order> it = orderDao.findAll().iterator();
            while (it.hasNext()) {
                Order order = it.next();
                if(order.getBuyer().equals(uname)) {
                    results.add(order);
                }
            }
            return new ResponseEntity<List<Order>>(results, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/restapi/order", params = {"productid","pcs","username"}, method = RequestMethod.POST)
    public ResponseEntity<Order> newOrder(
            @RequestParam("productid") int productid,
            @RequestParam("pcs") int pcs,
            @RequestParam("username") String uname
    ) {
        if (productDao.findById(productid) != null && customerDao.findByUname(uname) != null) {
            Order order = new Order();
            order.setBuyer(uname);
            order.setPcs(pcs);
            order.setProduct(productDao.findById(productid));
            order.setTotalPrice(pcs * productDao.findById(productid).getPrice());
            order.setIsSent(false);
            order.setSentDate(null);
            order.setProductName(productDao.findById(productid).getName());
            order.setOrderDate(Instant.now());
            orderDao.save(order);
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }

        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);

    }

}
