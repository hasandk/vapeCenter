package com.vapecenter.demo.repositories;

import com.vapecenter.demo.models.AboutUs;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.ShipingMethod;
import com.vapecenter.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CustomerRepoImpl implements CustomerRepo {

    @Autowired
    JdbcTemplate template;

    Logger log = Logger.getLogger(CustomerRepoImpl.class.getName());

    @Override
    public Users getUser(int id) {
        String sql = "SELECT * FROM VapeCenter.Users WHERE userId="+id;

        return this.template.query(sql, new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet rs) throws SQLException, DataAccessException {

                Users user = new Users();

                int userId, userType, zipcode, phone;
                String firstName, lastName, streetName, city, email, password;

                while (rs.next()) {
                    userId = rs.getInt("userId");
                    userType = rs.getInt("userType");
                    zipcode = rs.getInt("zipcode");
                    phone = rs.getInt("phone");

                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastNAme");
                    streetName = rs.getString("streetName");
                    city = rs.getString("city");
                    email = rs.getString("email");
                    password = rs.getString("password");

                    user.setUserId(userId);
                    user.setUserType(userType);
                    user.setZipcode(zipcode);
                    user.setPhone(phone);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setStreetName(streetName);
                    user.setCity(city);
                    user.setEmail(email);
                    user.setPassword(password);
                }
                return user;
            }
        });
    }

    @Override
    public ArrayList<Products> getProducts() {
        String sql = "SELECT * FROM Products WHERE active =1";
        return this.template.query(sql, new ResultSetExtractor<ArrayList<Products>>() {
            @Override
            public ArrayList<Products> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int productId, stock;
                String name, description, pictureLink;
                double price;
                int active;
                ArrayList<Products> products = new ArrayList<>();

                while (rs.next()) {
                    productId = rs.getInt("productId");
                    name = rs.getString("name");
                    description = rs.getString("description");
                    price = rs.getDouble("price");
                    pictureLink = rs.getString("pictureLink");
                    active = rs.getInt("active");
                    stock = rs.getInt("stock");

                    products.add(new Products(productId, stock, name, description, pictureLink, active, price));
                }
                return products;
            }
        });

    }

    @Override
    public Products getProductById(int productId) {
        String sql = "SELECT * FROM VapeCenter.Products WHERE active = 1 AND productId = ?";
        RowMapper<Products> rowMapper = new BeanPropertyRowMapper<>(Products.class);

        Products product = template.queryForObject(sql, rowMapper, productId);


        return product;
    }

    @Override
    public List<ShipingMethod> getShippingMethods() {
        String sql = "SELECT * FROM shippingMethod";
        return this.template.query(sql, new ResultSetExtractor<ArrayList<ShipingMethod>>() {
            @Override
            public ArrayList<ShipingMethod> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int shippingId;
                String companyName;
                double price;
                List<ShipingMethod> shippingMethods = new ArrayList<>();

                while (rs.next()) {
                    shippingId = rs.getInt("shippingId");
                    companyName = rs.getString("companyName");
                    price = rs.getDouble("price");

                    shippingMethods.add(new ShipingMethod(shippingId, companyName, price));
                }
                return (ArrayList<ShipingMethod>) shippingMethods;
            }
        });
    }

    public AboutUs getAboutInfo(int aboutUsId) {
        String sql = "SELECT * FROM VapeCenter.AboutUs WHERE aboutUsId = ?";
        RowMapper<AboutUs> rowMapper = new BeanPropertyRowMapper<>(AboutUs.class);

        AboutUs aboutUs = template.queryForObject(sql, rowMapper, aboutUsId);

        return aboutUs;
    }

    @Override
    public Products addProduct(Products product) {
        String sql = "INSERT INTO VapeCenter.Products VALUES(default,?,?,?,?,?,?)";
        String name = product.getName();
        String description = product.getDescription();
        double price = product.getPrice();
        String pictureLink = product.getPictureLink();
        int active = product.getActive();
        int stock = product.getStock();

        this.template.update(sql, name, description, price, pictureLink, active, stock);

        return product;
    }

}

