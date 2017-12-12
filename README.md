# basic-ecommerce-webapp
Basic eCommerce web application using Spring-MVC and Hibernate. I published it for educational purposes.

By default project settings, login page can be accessed via URL: http://localhost:8080/shop

*Note: Currently, application is lack of a pretty gui.*

### General
- .tag templates used as outline view modules of the project.
- Databases are handled by Hibernate
- Hibernate configured with PostgreSQL default in HibernateConfig.java
- GenericDao approach used for handling database operations.
- Authorization is handling by Spring-Filters. (by username embedded in cache. a lazy way.)

### Owner
- Owner hardcoded as uname/pass = patron/patron and can only be changed within Owner class.
- Owner can register a product or a new user, and can edit registered ones.
- Owner can confirm an order.
### Customer
- A customer can login with his uname/pass which was registered by owner.
- A customer can order a product, edit his information (except its username).
- A customer can display his past orders and their status(sent or not sent).



