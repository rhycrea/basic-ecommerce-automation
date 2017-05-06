# basic-ecommerce-automation
Basic eCommerce automation with Spring-MVC and Hibernate.

Just open&run the project with IntelliJ IDEA. 
By default project settings, login page can be accessed via URL: http://localhost:8080/shop

Ever so project is self-explanatory, here is a little information:

### General
- .tag templates used as outline view modules of the project.
- Databases are handled by Hibernate
- Hibernate configured with PostgreSQL default in HibernateConfig.java
- GenericDao approach used for handling database operations.
- Authorization is handling by Spring-Filters

### Owner
- Owner hardcoded as uname/pass = patron/patron and can only be changed within Owner class.
- Owner can register a product or a new user, and can edit registered ones.
- Owner can confirm an order.
### Customer
- A customer can login with his uname/pass which was registered by owner.
- A customer can order a product, edit his information (except its username).
- A customer can display his past orders and their status(sent or not sent).



