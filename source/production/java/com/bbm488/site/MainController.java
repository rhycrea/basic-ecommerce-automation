package com.bbm488.site;

import com.bbm488.site.customer.Customer;
import com.bbm488.site.customer.OrderController;
import com.bbm488.site.owner.CustomerDao;
import com.bbm488.site.owner.Owner;
import com.bbm488.site.owner.ProductController;
import com.bbm488.site.owner.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

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
                !customerDao.getDatabase().containsKey(form.getUsername()) ||
                !form.getPassword().equals(customerDao.find(form.getUsername()).getUpass()) )
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

    @PostConstruct
    public void init() throws Exception {
        Owner.setUname("patron");
        Owner.setUpass("patron");

        //--------------------HANDLE CUSTOMER DATABASE--------------------
        try(
                InputStream file = new FileInputStream("customerDB2.ser");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
            //deserialize the List
            customerDao.setDatabase( (Hashtable<String,Customer>)input.readObject() );
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found error!");
        }
        catch(IOException e){
            System.out.println("Database file cannot found! New database initializing...");
            customerDao.initDatabase();
        }
        catch(NullPointerException e){
            System.out.println("Database file is empty! New database initializing...");
            customerDao.initDatabase();
        }

        //--------------------HANDLE PRODUCT DATABASE--------------------
        try(
                InputStream file = new FileInputStream("productDB5.ser");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
            //deserialize the List
            productDao.setDatabase( (Hashtable<Integer,Product>)input.readObject() );
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found error!");
        }
        catch(IOException e){
            System.out.println("Database file cannot found! New database initializing...");
            productDao.initDatabase();
        }
        catch(NullPointerException e){
            System.out.println("Database file is empty! New database initializing...");
            productDao.initDatabase();
        }

        //--------------------HANDLE ORDER DATABASE--------------------
        try(
                InputStream file = new FileInputStream("orderDB.ser");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
            //deserialize the List
            orderDao.setDatabase( (Hashtable<Integer,Order>)input.readObject() );
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found error!");
        }
        catch(IOException e){
            System.out.println("Order Database file cannot found! New database initializing...");
            orderDao.initDatabase();
        }
        catch(NullPointerException e){
            System.out.println("Order Database file is empty! New database initializing...");
            orderDao.initDatabase();
        }


        //Below, we are trying to find a valid ID sequence value for further order and product records.
        if(orderDao.getDatabase().isEmpty()){
            OrderController.setOrderIdSequence(1);
        }
        else {
            Iterator<Map.Entry<Integer, Order>> it = orderDao.getDatabase().entrySet().iterator();
            int minID=1;
            while (it.hasNext()) {
                Order order = it.next().getValue();
                if(order.getID()>minID) {
                    minID = order.getID();
                }
            }
            OrderController.setOrderIdSequence(++minID); //guarantee a minimum ID value that is higher than all others.
        }

        if(productDao.getDatabase().isEmpty()){
            ProductController.setProductIdSequence(1);
        }
        else {
            Iterator<Map.Entry<Integer, Product>> it = productDao.getDatabase().entrySet().iterator();
            int minID=1;
            while (it.hasNext()) {
                Product order = it.next().getValue();
                if(order.getID()>minID) {
                    minID = order.getID();
                }
            }
            ProductController.setProductIdSequence(++minID);
        }
    }

    @PreDestroy
    public void destroy() throws Exception {

        //--------------------DESTROY CUSTOMER DATABASE--------------------

        try (
                OutputStream file = new FileOutputStream("customerDB2.ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(customerDao.getDatabase());
        }
        catch(IOException ex){
            System.out.println("File error!");
        }

        //--------------------DESTROY PRODUCT DATABASE--------------------

        try (
                OutputStream file = new FileOutputStream("productDB5.ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(productDao.getDatabase());
        }
        catch(IOException ex){
            System.out.println("File error!");
        }
        
        //--------------------DESTROY ORDER DATABASE--------------------
        
        try (
                OutputStream file = new FileOutputStream("orderDB.ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(orderDao.getDatabase());
        }
        catch(IOException ex){
            System.out.println("File error!");
        }
    }

    //TODO: redirect hem customer hem ocaksahibi için yapılmalı. tasarım yap.
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
