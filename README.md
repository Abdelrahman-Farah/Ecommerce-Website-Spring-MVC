# E-Commerce-Application

- The E-Commerce Application is built using Java and Spring Boot, with security, scalability, and ease of maintenance. The backend uses Spring Data JPA to interact with a MySQL database, making it easy to manage and store important entities such as users, products, categories, orders, and more. User authentication is handled by Auth0, providing secure and reliable means of REST APIs.
 
- ![image](https://github.com/Abdelrahman-Farah/ecommerce/blob/main/readme-assets/homepage.gif)

## Used Technologies:

* Spring (Data, Security, MVC etc)
* JPA / Hibernate
* PostgreSQL
* Thymeleaf
* HTML, CSS, Bootstrap
* JS, Swiper
* Maven
* SQL Query


## Quickstart

1. Clone the repository
2. Open the project in your IDE: IntelliJ IDEA (recommended) or Eclipse
3. Run PostgreSQL Scripts found in `PSQL-Scripts`
4. Configure the database connection in `application.properties` file (check the [Database](#database) section below for more info)
5. Run the project (by running the `main` method in `ECommerceApplication.java`)
6. Open http://localhost:8080/ in your browser!
   * If you ran the [`User and Roles.sql`](https://github.com/Abdelrahman-Farah/ecommerce/blob/main/PSQL-Scripts/User%20and%20Roles.txt)script on the database, you can log in with the following credentials as admin; otherwise you'll have to manually create an  user in the database:
     * Email: `admin@ecommerce.com`
     * Password: `pass123word`
    * Or you can [register a new user!](http://localhost:8080/auth/register)

### Database

PostgreSQL is used as the database for this project. The database connection can be configured in the `application.properties` file, with the appropriate values for the following properties:

```properties
    db.url=jdbc:postgresql://[ip address of db]:[port of db]/ecommjava?createDatabaseIfNotExist=true
    db.username=[username]
    db.password=[password, if any]
```


## MVC Implementation
- Controller
    * Registration
    * HomePage
    * Category 
    * Product
    * Cart
- Model
   * Roles
   * Customer
   * Category 
   * Product
   * Cart
   * CartItem   
   
   
## Database schema
![image](https://github.com/Abdelrahman-Farah/ecommerce/blob/main/readme-assets/schema.PNG)