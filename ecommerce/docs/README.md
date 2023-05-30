# F1dot5 java web application

## Reference Documentation

###Planned functionality:
* Authentication of the customers
* Order the article from the store
* Review and confirm the sales order
* Add delivery and payment details information
* Validate user input 

###Implemented functionality:
* Signup new customer using forms authentication
* Login the existing customer with forms authentication
* Select and order the desired articles
* Confirm the quantity and total price of the order
* Create a sale invoice with customer delivery information

###Ideas for future:
* Extend order confirmation page with Article related information (name, image)
* Extend currency support (Now the USD is hardcoded. The additional db table and related calculations are required)
* Synchronaze the article store quantity with the sales (Now the article quantity is not decreased after order confirmation)
* Add user roles for support Admin mode
* Add the Admin panel for managing Article store
* Improve the Web UI with more friendly UX (add navigations between sections, etc)
* Technical: Migrate to JPA from JDBC template (let Hybernate manage ORM instead of manual SQL)
* Technical: Improve source code documentation by following this rules https://www.jetbrains.com/help/idea/working-with-code-documentation.html  
* Technical: Create unit tests for implemented functionality


For further reference, please consider the following sections:
* [Project source code documentation](../documentation/index.html)

