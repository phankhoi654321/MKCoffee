/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import mkcoffee.model.Customer;
import mkcoffee.model.CustomerBuy;
import mkcoffee.model.EmployeeSale;
import mkcoffee.model.IssueProduct;
import mkcoffee.model.OrderList;
import mkcoffee.model.Product;
import mkcoffee.model.ProductSales;
import mkcoffee.model.ReceiptProduct;
import mkcoffee.model.Register;
import mkcoffee.model.User;
import mkcoffee.model.WorkingUser;

/**
 *
 * @author maikhanh
 */
public class Datasource {

    private static Connection conn = null;
//    private static PreparedStatement preparedStatement = null;
    private static PreparedStatement queryOrder;
    private static PreparedStatement perpareStatement = null;
    private static PreparedStatement statementUserWorkingById;
    private static PreparedStatement statementUserById;
    private static PreparedStatement statementOrderId;
    private static PreparedStatement statementEmployeeWorkingMonth;
    private static PreparedStatement statementCustomerBuy;
    private static PreparedStatement statementEmployeeSale;
    private static PreparedStatement statementBestCustomerBuyMonth;
    private static PreparedStatement statementReceiptProductByName;
    private static PreparedStatement statementReceiptProductByNameKind;
    private static PreparedStatement statementIssueProductByName;
    private static PreparedStatement statementIssueProductByNameKind;
    private static PreparedStatement statementBestEmployeeBuyMonth;
    private static PreparedStatement statementBestSaleMonth;
    private static PreparedStatement searchCustomerIdByPhone;
    private static PreparedStatement updateProductQuantityState;
    private static PreparedStatement searchByNameKind;
    private static PreparedStatement statementInsertProductSale;
    private static PreparedStatement statementInsertOrderList;
    private static PreparedStatement statementInsertProduct;
    private static PreparedStatement statementDeleteProduct;
    private static PreparedStatement stateSearchProductByID;
    private static PreparedStatement stateUpdateProduct;
    private static PreparedStatement stateInsertCustomer;
    private static PreparedStatement stateInsertUserPromote;
    private static PreparedStatement stateInsertReceipt;
    private static PreparedStatement searchCustomerIdByNamePhone;
    private static PreparedStatement searchCustomerNameById;
    private static PreparedStatement stateDeleteCustomer;
    private static PreparedStatement stateUpdateCustomer;
    private static PreparedStatement searchCustomerByID;
    private static PreparedStatement stateDeleteEmployee;
    private static PreparedStatement stateDeleteUserPromote;
    private static PreparedStatement stateUpdateEmployee;
    private static PreparedStatement searchEmployeeByID;
    private static PreparedStatement stateCheckStock;
    private static PreparedStatement stateInsertStartOfDate;
    private static PreparedStatement searchEmployeeByName;
    private static PreparedStatement searchCustomerByName;
    private static PreparedStatement searchIssueByDate;
    private static PreparedStatement searchIssueByBetweenDate;
    private static PreparedStatement searchIssueByEmployeeId;
    private static PreparedStatement searchReceiptByEmployeeId;
    private static PreparedStatement searchReceiptByDate;
    private static PreparedStatement searchOrderListByDate;
    private static PreparedStatement searchOrderListByDateTime;
    private static PreparedStatement searchOrderListByEmployeeID;
    private static PreparedStatement searchOrderByBetweenDate;
    private static PreparedStatement searchReceiptByBetweenDate;
    private static PreparedStatement searchProductByName;
    private static PreparedStatement searchProductByNameKind;
    private static PreparedStatement searchOrderId;

    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost;";
    private static final String DATABASENAME = "databaseName=mkcoffee;";
    private static final String USER = "user=SA;";
    private static final String PASS = "password=Sqldemo2017";

    private static final String QUERY_PRODUCTS = "SELECT productID, productName, productKind, productUnit, productPrice, productCategory, productStock FROM Products";
    private static final String QUERY_PRODUCTSNAME = "SELECT productName FROM Products";
    private static final String QUERY_ORDERLIST = "SELECT orderId, employeeId, customerId, timeOrder, totalInvoice FROM OrderList";
    private static final String QUERY_CUSTOMERS = "SELECT customerId, customerName, customerAdd, customerPhone, customerEmail FROM Customers";
    private static final String QUERY_USER = "SELECT userId, fullName, email, phoneNumber, birthday, gender, userName, userPass FROM users";
    private static final String QUERY_USERPROMOTE = "SELECT userId, userName FROM employeePromote";
    private static final String QUERY_USER_BYID = "SELECT userId, fullName, email, phoneNumber, birthday, gender, userName, userPass FROM users WHERE userId = ?";
    private static final String QUERY_REGISTER = "SELECT userId, userName, userPass FROM register";
    private static final String QUERY_WORKING_USER = "SELECT userId, startOfDate, endOfDate FROM Userworking";
    private static final String QUERY_WORKING_USER_BYID = "SELECT userId, startOfDate, endOfDate FROM Userworking WHERE userId=?";
    private static final String QUERY_WORKING_USER_MONTH = "SELECT userId, startOfDate, endOfDate FROM Userworking WHERE MONTH(startOfDate) = ?";
    private static final String QUERY_PRODUCTSALES = "SELECT transactionId, orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice FROM ProductSales";
    private static final String QUERY_PRODUCTSALES_MONTH = "SELECT MONTH(dateOfTransaction), totalPrice FROM ProductSales;";
    private static final String QUERY_ORDERLIST_MONTH = "SELECT MONTH(timeOrder), totalInvoice FROM OrderList;";
    private static final String QUERY_ORDERID = "SELECT orderId FROM OrderList WHERE ID=?";
    private static final String QUERY_CUSTOMERBUY = "SELECT Customers.customerid, Customers.customerName, Customers.customerAdd,Customers.customerPhone,Customers.customerEmail,\n"
            + "ProductSales.dateOfTransaction,ProductSales.productName,ProductSales.kind,ProductSales.unit,ProductSales.price,ProductSales.quantity,ProductSales.totalPrice\n"
            + "FROM Customers\n"
            + "INNER JOIN ProductSales ON Customers.customerid=ProductSales.customerId\n"
            + "WHERE Customers.customerid = ?";
    private static final String QUERY_EMPLOYEESALE = "SELECT users.userId, users.fullName, users.phoneNumber, users.email, \n"
            + "ProductSales.dateOfTransaction,ProductSales.productName,ProductSales.kind,ProductSales.unit,ProductSales.price,ProductSales.quantity,ProductSales.totalPrice\n"
            + "FROM users\n"
            + "INNER JOIN ProductSales ON users.userId=ProductSales.employeeId\n"
            + "WHERE users.userId = ?";

    private static final String QUERY_BESTCUSTOMER_MONTH = "SELECT Customers.customerid, Customers.customerName, Customers.customerAdd,Customers.customerPhone,Customers.customerEmail,\n"
            + "ProductSales.dateOfTransaction,ProductSales.productName,ProductSales.kind,ProductSales.unit,ProductSales.price,ProductSales.quantity,ProductSales.totalPrice,\n"
            + "MONTH(dateOfTransaction) AS monthBuy\n"
            + "FROM Customers\n"
            + "INNER JOIN ProductSales ON Customers.customerid=ProductSales.customerId\n"
            + "WHERE Customers.customerid = ? AND MONTH(dateOfTransaction) = ?";
    private static final String QUERY_BESTEMLOYEE_MONTH = "SELECT users.userId, users.fullName, users.phoneNumber, users.email, \n"
            + "ProductSales.dateOfTransaction,ProductSales.productName,ProductSales.kind,ProductSales.unit,ProductSales.price,ProductSales.quantity,ProductSales.totalPrice,\n"
            + "MONTH(dateOfTransaction) AS monthSale\n"
            + "FROM users\n"
            + "INNER JOIN ProductSales ON users.userId=ProductSales.employeeId\n"
            + "WHERE users.userId = ? AND MONTH(dateOfTransaction) = ?";

//    public static final String QUERY_BESTRECEIPT_MONTH = "SELECT Customers.customerid, Customers.customerName, Customers.customerAdd,Customers.customerPhone,Customers.customerEmail,\n"
//            + "ProductSales.dateOfTransaction,ProductSales.productName,ProductSales.kind,ProductSales.unit,ProductSales.price,ProductSales.quantity,ProductSales.totalPrice,\n"
//            + "MONTH(dateOfTransaction) AS monthBuy\n"
//            + "FROM Customers\n"
//            + "INNER JOIN ProductSales ON Customers.customerid=ProductSales.customerId\n"
//            + "WHERE Customers.customerid = ? AND MONTH(dateOfTransaction) = ?";
    private static final String QUERY_ORDERLIST_ECNAME = "SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice, Users.fullName, Customers.customerName\n"
            + "FROM OrderList\n"
            + "INNER JOIN users ON OrderList.employeeId = Users.userId\n"
            + "INNER JOIN Customers ON OrderList.customerId = Customers.customerId";
    private static final String QUERY_BESTSALE_MONTH = "SELECT transactionId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice FROM ProductSales WHERE MONTH(dateOfTransaction) = ?";
    private static final String QUERY_BESTSALE_YEAR = "SELECT transactionId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice FROM ProductSales WHERE YEAR(dateOfTransaction) = ?";
    private static final String FIND_PRODUCT_NAME_KIND = "SELECT productID FROM Products WHERE productName = ? AND productKind = ?";
    private static final String FIND_ISSUE_DATE = "SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction FROM ProductSales WHERE CAST (dateOfTransaction AS DATE) = ?;";
    private static final String FIND_ISSUE_BETWEENDATE = "SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction FROM ProductSales WHERE dateOfTransaction >= CONVERT(datetime,?) and dateOfTransaction < CONVERT(datetime,?) +1 ";
    private static final String FIND_RECEIPT_BETWEENDATE = "SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit,receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct WHERE receiptProductdateOfTransaction >= CONVERT(datetime,?) and receiptProductdateOfTransaction < CONVERT(datetime,?) +1";
    private static final String FIND_ISSUE_EMPLOYEEID = "SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction FROM ProductSales WHERE employeeId = ?;";
    private static final String FIND_RECEIPT_DATE = "SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit,receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct WHERE CAST (receiptProductdateOfTransaction AS DATE) = ?;";
    private static final String FIND_RECEIPT_EMPLOYEEID = "SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit,receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct WHERE receiptEmployeeId = ?;";
    private static final String FIND_ORDERLIST_DATE = "SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice\n"
            + "FROM OrderList\n"
            + "WHERE CAST (timeOrder AS DATE) = ?;";
//    private static final String FIND_ORDERLIST_DATETIME = "SELECT OrderList.orderId FROM OrderList WHERE CAST (timeOrder AS DATE) = ? AND  CAST (timeOrder AS TIME) = ?";
    private static final String FIND_ORDERLIST_DATETIME = "SELECT TOP 1 orderId FROM OrderList ORDER BY ID DESC";
    private static final String FIND_ORDERLIST_EMPLOYEEID = "SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice, Users.fullName, Customers.customerName\n"
            + "FROM OrderList\n"
            + "INNER JOIN users ON OrderList.employeeId = Users.userId\n"
            + "INNER JOIN Customers ON OrderList.customerId = Customers.customerId WHERE  OrderList.employeeId = ?";

//            "SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice\n"
//            + "FROM OrderList\n"
//            + "WHERE employeeId = ?;";
    private static final String FIND_ORDERLIST_BETWEENDATE = "SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice, Users.fullName, Customers.customerName \n"
            + "FROM OrderList\n"
            + "INNER JOIN users ON OrderList.employeeId = Users.userId\n"
            + "INNER JOIN Customers ON OrderList.customerId = Customers.customerId \n"
            + "WHERE timeOrder >= CONVERT(date, ?) and timeOrder < CONVERT(date, DATEADD(day, 1, ?))";

//            = "SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice FROM OrderList WHERE timeOrder >= CONVERT(datetime,?) and timeOrder < CONVERT(datetime,?) +1";
    private static final String INSERT_RECEIPT_PRODUCT = "INSERT INTO ReceiptProduct(receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    private static final String QUERY_ISSUE_PRODUCT_FROMPRODUCTSALES = "SELECT transactionId, employeeId, productName, kind, unit, price, quantity,dateOfTransaction FROM ProductSales";
    private static final String QUERY_ISSUE_PRODUCT_FROMPRODUCTSALESNAME = "SELECT productName FROM ProductSales";
    private static final String QUERY_RECEIPT_PRODUCT = "SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct";
    private static final String QUERY_RECEIPT_PRODUCTNAME = "SELECT receiptProductName FROM ReceiptProduct";
    private static final String QUERY_RECEIPT_PRODUCT_BYNAME = "SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct WHERE receiptProductName = ? AND receiptProductKind = ? AND MONTH(receiptProductdateOfTransaction) = ?";
    private static final String QUERY_RECEIPT_PRODUCT_BYNAMEKIND = "SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct WHERE receiptProductName = ? AND receiptProductKind = ?";
    private static final String QUERY_ISSUE_PRODUCT_BYNAME = "SELECT transactionId, employeeId, productName, kind, unit, price, quantity,dateOfTransaction FROM ProductSales WHERE productName = ? AND kind = ? AND MONTH(dateOfTransaction) = ?";
    private static final String QUERY_ISSUE_PRODUCT_BYNAMEKIND = "SELECT transactionId, employeeId, productName, kind, unit, price, quantity,dateOfTransaction FROM ProductSales WHERE productName = ? AND kind = ?";
    private static final String INSERT_PRODUCTSALE = "INSERT INTO ProductSales(orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private static final String INSERT_ORDERLIST = "INSERT INTO OrderList(employeeId, customerId, timeOrder, totalInvoice) VALUES ( ?, ?, ?, ? )";
    public static final String FIND_CUSTOMERID_PHONE = "SELECT customerId FROM Customers WHERE customerPhone = ?";
    public static final String UPDATE_PRODUCT_QUANTITY = "UPDATE Products SET productStock= ? WHERE productName = ? AND productKind = ?";
    private static String INSERT_PRODUCT = "INSERT INTO Products(productName, productKind, productUnit, productPrice, productCategory, productStock) VALUES ( ?, ?, ?, ?, ?, ? )";
    public static final String DELETE_PRODUCT = "DELETE FROM Products WHERE productName = ? AND productKind = ?";
    public static final String FIND_UPDATE_PRODUCT = "SELECT productID, productName, productKind, productPrice, productCategory, productStock FROM Products WHERE productID = ?";
    public static final String UPDATE_PRODUCT = "UPDATE Products SET productName = ?, productKind = ?, productPrice= ?, productCategory= ?, productStock= ? WHERE productID = ?";
    private static String INSERT_CUSTOMER = "INSERT INTO Customers(customerName, customerAdd, customerPhone, customerEmail) VALUES ( ?, ?, ?, ?)";
//    private static String INSERT_CUSTOMER = "INSERT INTO Customers(customerName, customerAdd, customerPhone, customerEmail) VALUES ( ?, ?, ?, ?)";
    private static final String FIND_CUSTOMERID_NAME_PHONE = "SELECT customerId FROM Customers WHERE customerName = ? AND customerPhone = ?";
    private static final String FIND_CUSTOMERNAME_BYID = "SELECT customerName FROM Customers WHERE customerId = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customers WHERE customerName = ? AND customerPhone = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE Customers SET customerName = ?, customerAdd = ?, customerPhone= ?, customerEmail= ? WHERE customerId = ?";
    private static final String FIND_UPDATE_CUSTOMER = "SELECT customerId, customerName, customerAdd, customerPhone, customerEmail FROM Customers WHERE customerId = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM users WHERE userId = ?";
    private static final String UPDATE_USER = "UPDATE users SET fullName = ?, email = ?, phoneNumber= ?, birthday= ?, gender = ?, userName = ?, userPass= ?  WHERE userId = ?";
    private static final String FIND_UPDATE_EMPLOYEE = "SELECT userId, fullName, email, phoneNumber, birthday, gender, userName, userPass FROM users WHERE userId = ?";
    private static final String FIND_EMPLOYEE_BYNAME = "SELECT userId, fullName, email, phoneNumber, birthday, gender, userName, userPass FROM users WHERE fullName LIKE ?";
    private static final String FIND_CUSTOMER_BYNAME = "SELECT customerId, customerName, customerAdd, customerPhone, customerEmail FROM Customers WHERE customerName LIKE ?";
    private static final String FIND_PRODUCT_BYNAME = "SELECT productID, productName, productKind, productPrice, productCategory, productStock FROM Products WHERE productName LIKE ?";
    private static final String FIND_PRODUCT_BYNAMEKIND = "SELECT productID, productName, productKind, productUnit, productPrice, productCategory, productStock FROM Products WHERE productName = ? AND productKind = ?";
    private static final String FIND_ORDERID = "SELECT orderId, employeeId, customerId, dateOfTransaction, productName, kind, unit, price, quantity, totalprice FROM ProductSales WHERE orderId = ?";
    private static final String CHECK_STOCK_PRODUCT = "SELECT productStock FROM Products WHERE productName = ? AND productKind = ? ";
    private static String INSERT_STARTDATE = "INSERT INTO Userworking(userId, startOfDate, endOfDate) VALUES ( ?, ?, ? )";
    private static final String INSERT_USERPROMOTE = "INSERT INTO employeePromote(userId, userName) VALUES (?, ?)";
    private static final String DELETE_USERPROMOTE = "DELETE FROM employeePromote WHERE userId = ?";
    
    private static Datasource instance = new Datasource();

    public Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() throws ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if (queryOrder != null) {
                queryOrder.close();
            }
            if (perpareStatement != null) {
                perpareStatement.close();
            }
            if (statementCustomerBuy != null) {
                statementCustomerBuy.close();
            }
            if (statementUserById != null) {
                statementUserById.close();
            }
            if (statementUserWorkingById != null) {
                statementUserWorkingById.close();
            }
            if (statementEmployeeWorkingMonth != null) {
                statementEmployeeWorkingMonth.close();
            }
            if (statementEmployeeSale != null) {
                statementEmployeeSale.close();
            }
            if (statementBestCustomerBuyMonth != null) {
                statementBestCustomerBuyMonth.close();
            }
            if (statementBestEmployeeBuyMonth != null) {
                statementBestEmployeeBuyMonth.close();
            }
            if (statementBestSaleMonth != null) {
                statementBestSaleMonth.close();
            }
            if (searchCustomerIdByPhone != null) {
                searchCustomerIdByPhone.close();
            }
            if (updateProductQuantityState != null) {
                updateProductQuantityState.close();
            }
            if (searchByNameKind != null) {
                searchByNameKind.close();
            }
            if (statementInsertProductSale != null) {
                statementInsertProductSale.close();
            }
            if (statementInsertProduct != null) {
                statementInsertProduct.close();
            }
            if (statementDeleteProduct != null) {
                statementDeleteProduct.close();
            }
            if (stateSearchProductByID != null) {
                stateSearchProductByID.close();
            }
            if (stateUpdateProduct != null) {
                stateUpdateProduct.close();
            }
            if (stateInsertCustomer != null) {
                stateInsertCustomer.close();
            }
            if (searchCustomerIdByNamePhone != null) {
                searchCustomerIdByNamePhone.close();
            }
            if (stateDeleteCustomer != null) {
                stateDeleteCustomer.close();
            }
            if (stateUpdateCustomer != null) {
                stateUpdateCustomer.close();
            }
            if (searchCustomerByID != null) {
                searchCustomerByID.close();
            }
            if (stateDeleteEmployee != null) {
                stateDeleteEmployee.close();
            }
            if (stateUpdateEmployee != null) {
                stateUpdateEmployee.close();
            }
            if (searchEmployeeByID != null) {
                searchEmployeeByID.close();
            }
            if (stateCheckStock != null) {
                stateCheckStock.close();
            }
            if (stateInsertStartOfDate != null) {
                stateInsertStartOfDate.close();
            }
            if (searchEmployeeByName != null) {
                searchEmployeeByName.close();
            }
            if (searchCustomerByName != null) {
                searchCustomerByName.close();
            }
            if (searchProductByName != null) {
                searchProductByName.close();
            }
            if (statementInsertOrderList != null) {
                statementInsertOrderList.close();
            }
            if (statementOrderId != null) {
                statementOrderId.close();
            }
            if (searchOrderId != null) {
                searchOrderId.close();
            }
            if (searchCustomerNameById != null) {
                searchCustomerNameById.close();
            }
            if (stateInsertReceipt != null) {
                stateInsertReceipt.close();
            }
            if (searchIssueByDate != null) {
                searchIssueByDate.close();
            }
            if (searchReceiptByDate != null) {
                searchReceiptByDate.close();
            }
            if (statementReceiptProductByName != null) {
                statementReceiptProductByName.close();
            }
            if (statementIssueProductByName != null) {
                statementIssueProductByName.close();
            }
            if (searchReceiptByEmployeeId != null) {
                searchReceiptByEmployeeId.close();
            }
            if (searchIssueByBetweenDate != null) {
                searchIssueByBetweenDate.close();
            }
            if (searchOrderListByEmployeeID != null) {
                searchOrderListByEmployeeID.close();
            }
            if (searchOrderListByDate != null) {
                searchOrderListByDate.close();
            }
            if (searchOrderByBetweenDate != null) {
                searchOrderByBetweenDate.close();
            }
            if (statementReceiptProductByNameKind != null) {
                statementReceiptProductByNameKind.close();
            }
            if (statementIssueProductByNameKind != null) {
                statementIssueProductByNameKind.close();
            }
            if (searchProductByNameKind != null) {
                searchProductByNameKind.close();
            }
            if (searchOrderListByDateTime != null) {
                searchOrderListByDateTime.close();
            }
            if (stateInsertUserPromote != null) {
                stateInsertUserPromote.close();
            }
            if (stateDeleteUserPromote != null) {
                stateDeleteUserPromote.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<OrderList> queryOrderList() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_ORDERLIST)) {

            List<OrderList> orderLists = new ArrayList<>();
            while (results.next()) {
                OrderList orderList = new OrderList();
                orderList.setOrderId(results.getString(1));
                orderList.setEmployeeId(results.getString(2));
                orderList.setCustomerId(results.getString(3));
                orderList.setTimeOrder(results.getString(4));
                orderList.setTotalInvoice(results.getInt(5));

                orderLists.add(orderList);
            }

            return orderLists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    
    

    public List<Product> queryProducts() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_PRODUCTS)) {

            List<Product> products = new ArrayList<>();
            while (results.next()) {
                Product product = new Product();
                product.setProductID(results.getString(1));
                product.setProductName(results.getString(2));
                product.setProductKind(results.getString(3));
                product.setProductUnit(results.getString(4));
                product.setProductPrice(results.getInt(5));
                product.setProductCategory(results.getString(6));
                product.setProductStock(results.getInt(7));

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryProductsName() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_PRODUCTSNAME)) {

            List<String> productsName = new ArrayList<>();
            while (results.next()) {
                productsName.add(results.getString(1));
            }

            return productsName;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Customer> queryCustomers() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_CUSTOMERS)) {

            List<Customer> customers = new ArrayList<>();
            while (results.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(results.getString(1));
                customer.setCustomerName(results.getString(2));
                customer.setCustomerAdd(results.getString(3));
                customer.setCustomerPhone(results.getString(4));
                customer.setCustomerEmail(results.getString(5));
                customers.add(customer);
            }

            return customers;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<OrderList> queryOrderListECName() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_ORDERLIST_ECNAME)) {

            List<OrderList> orderLists = new ArrayList<>();
            while (results.next()) {
                OrderList orderList = new OrderList();
                orderList.setOrderId(results.getString(1));
                orderList.setEmployeeId(results.getString(2));
                orderList.setCustomerId(results.getString(3));
                orderList.setDateOrder(results.getDate(4).toString());
                orderList.setTimeOrder(results.getTime(4).toString());
                orderList.setTotalInvoice(results.getInt(5));
                orderList.setEmployeeName(results.getString(6));
                orderList.setCustomerName(results.getString(7));

                orderLists.add(orderList);
            }

            return orderLists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<User> queryUsers() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_USER)) {

            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setUserId(results.getString(1));
                user.setFullName(results.getString(2));
                user.setEmail(results.getString(3));
                user.setPhoneNumber(results.getString(4));
                user.setBirthday(results.getDate(5).toString());
                user.setGender(results.getString(6));
                user.setUserName(results.getString(7));
                user.setUserPass(results.getString(8));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    
    public List<User> queryUsersPromote() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_USERPROMOTE)) {

            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setUserId(results.getString(1));
                user.setUserName(results.getString(2));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    
    public void insertUsersPromote(String userId, String userName) {
        try {

            stateInsertUserPromote = conn.prepareStatement(INSERT_USERPROMOTE);
            //customerName, customerAdd, customerPhone, customerEmail
            stateInsertUserPromote.setString(1, userId);
            stateInsertUserPromote.setString(2, userName);
            stateInsertUserPromote.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> queryUsersById(String inputId) {

        try {
            statementUserById = conn.prepareStatement(QUERY_USER_BYID);
            statementUserById.setString(1, inputId);
            ResultSet results = statementUserById.executeQuery();
            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setUserId(results.getString(1));
                user.setFullName(results.getString(2));
                user.setEmail(results.getString(3));
                user.setPhoneNumber(results.getString(4));
                user.setBirthday(results.getDate(5).toString());
                user.setGender(results.getString(6));
                user.setUserName(results.getString(7));
                user.setUserPass(results.getString(8));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Register> queryRegister() {
        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_REGISTER)) {
            List<Register> registers = new ArrayList<>();
            while (results.next()) {
                Register register = new Register();
                register.setUserId(results.getString(1));
                register.setUserName(results.getString(2));
                register.setUserPass(results.getString(3));
                registers.add(register);
            }
            return registers;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<WorkingUser> queryWorkingUser() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_WORKING_USER)) {

            List<WorkingUser> workingUsers = new ArrayList<>();
            while (results.next()) {
                WorkingUser workingUser = new WorkingUser();
                workingUser.setUserId(results.getString(1));
                workingUser.setStartOfDate(results.getDate(2).toString());
                workingUser.setStartOfTime(results.getTime(2).toString());
                workingUser.setEndOfDate(results.getDate(3).toString());
                workingUser.setEndOfTime(results.getTime(3).toString());

                workingUsers.add(workingUser);
            }

            return workingUsers;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<WorkingUser> queryWorkingUserById(String inputId) {

        try {

            statementUserWorkingById = conn.prepareStatement(QUERY_WORKING_USER_BYID);
            statementUserWorkingById.setString(1, inputId);
            ResultSet rs = statementUserWorkingById.executeQuery();
            List<WorkingUser> workingUsers = new ArrayList<>();
            while (rs.next()) {
                WorkingUser workingUser = new WorkingUser();
                workingUser.setUserId(rs.getString(1));
                workingUser.setStartOfDate(rs.getDate(2).toString());
                workingUser.setStartOfTime(rs.getTime(2).toString());
                workingUser.setEndOfDate(rs.getDate(3).toString());
                workingUser.setEndOfTime(rs.getTime(3).toString());

                workingUsers.add(workingUser);
            }

            return workingUsers;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<WorkingUser> queryWorkingUserMonth(String inputId) {

        try {

            statementEmployeeWorkingMonth = conn.prepareStatement(QUERY_WORKING_USER_MONTH);
            statementEmployeeWorkingMonth.setString(1, inputId);
            ResultSet rs = statementEmployeeWorkingMonth.executeQuery();

            List<WorkingUser> workingUsers = new ArrayList<>();
            while (rs.next()) {
                WorkingUser workingUser = new WorkingUser();
                workingUser.setUserId(rs.getString(1));
                workingUser.setStartOfDate(rs.getDate(2).toString());
                workingUser.setStartOfTime(rs.getTime(2).toString());
                workingUser.setEndOfDate(rs.getDate(3).toString());
                workingUser.setEndOfTime(rs.getTime(3).toString());

                workingUsers.add(workingUser);
            }

            return workingUsers;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<ProductSales> queryProductSales() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_PRODUCTSALES)) {

            List<ProductSales> productSaleses = new ArrayList<>();
            while (results.next()) {
                ProductSales productSales = new ProductSales();
                productSales.setTransactionId(results.getString(1));
                productSales.setOrderId(results.getString(2));
                productSales.setEmployeeId(results.getString(3));
                productSales.setCustomerId(results.getString(4));
                productSales.setProductId(results.getString(5));
                productSales.setDateOfTransaction(results.getDate(6).toString());
                productSales.setTimeOfTransaction(results.getTime(6).toString());
                productSales.setProductName(results.getString(7));
                productSales.setKind(results.getString(8));
                productSales.setUnit(results.getString(9));
                productSales.setPrice(results.getInt(10));
                productSales.setQuantity(results.getInt(11));
                productSales.setTotalPrice(results.getInt(12));

                productSaleses.add(productSales);
            }

            return productSaleses;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<ProductSales> queryProductSalesMonth() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_PRODUCTSALES_MONTH)) {

            List<ProductSales> productSaleses = new ArrayList<>();
            while (results.next()) {
                ProductSales productSales = new ProductSales();
                productSales.setMonthOfTransaction(results.getInt(1));
                productSales.setTotalPrice(results.getInt(2));

                productSaleses.add(productSales);
            }

            return productSaleses;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<OrderList> queryOrderListMonth() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_ORDERLIST_MONTH)) {

            List<OrderList> orderLists = new ArrayList<>();
            while (results.next()) {
                OrderList orderList = new OrderList();
                orderList.setMonthOfTransaction(results.getInt(1));
                orderList.setTotalInvoice(results.getInt(2));

                orderLists.add(orderList);
            }

            return orderLists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<CustomerBuy> queryCustomerBuy(String inputId) {

        try {
            statementCustomerBuy = conn.prepareStatement(QUERY_CUSTOMERBUY);
//                ResultSet results = statement.executeQuery(QUERY_CUSTOMERBUY)
            statementCustomerBuy.setString(1, inputId);
            ResultSet rs = statementCustomerBuy.executeQuery();
            List<CustomerBuy> customerBuys = new ArrayList<>();
            while (rs.next()) {
                CustomerBuy customerBuy = new CustomerBuy();

                customerBuy.setCustomerId(rs.getString(1));
                customerBuy.setCustomerName(rs.getString(2));
                customerBuy.setCustomerAdd(rs.getString(3));
                customerBuy.setCustomerPhone(rs.getString(4));
                customerBuy.setCustomerEmail(rs.getString(5));
                customerBuy.setDateOfTransaction(rs.getDate(6).toString());
                customerBuy.setTimeOfTransaction(rs.getTime(6).toString());
                customerBuy.setProductName(rs.getString(7));
                customerBuy.setKind(rs.getString(8));
                customerBuy.setUnit(rs.getString(9));
                customerBuy.setPrice(rs.getInt(10));
                customerBuy.setQuantity(rs.getInt(11));
                customerBuy.setTotalPrice(rs.getInt(12));

                customerBuys.add(customerBuy);
            }

            return customerBuys;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

    }

    public List<EmployeeSale> queryEmployeeSale(String inputId) {

        try {
            statementEmployeeSale = conn.prepareStatement(QUERY_EMPLOYEESALE);
            statementEmployeeSale.setString(1, inputId);
            ResultSet rs = statementEmployeeSale.executeQuery();
            List<EmployeeSale> employeeSales = new ArrayList<>();
            while (rs.next()) {
                EmployeeSale employeeSale = new EmployeeSale();

                employeeSale.setEmployeeId(rs.getString(1));
                employeeSale.setEmployeeName(rs.getString(2));
                employeeSale.setEmployeePhone(rs.getString(3));
                employeeSale.setEmployeeEmail(rs.getString(4));
                employeeSale.setDateOfTransaction(rs.getDate(5).toString());
                employeeSale.setTimeOfTransaction(rs.getTime(5).toString());
                employeeSale.setProductName(rs.getString(6));
                employeeSale.setKind(rs.getString(7));
                employeeSale.setUnit(rs.getString(8));
                employeeSale.setPrice(rs.getInt(9));
                employeeSale.setQuantity(rs.getInt(10));
                employeeSale.setTotalPrice(rs.getInt(11));

                employeeSales.add(employeeSale);
            }

            return employeeSales;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

    }

    public List<ReceiptProduct> queryReceiptByProductName(String name, String kind, int month) {

        try {
            statementReceiptProductByName = conn.prepareStatement(QUERY_RECEIPT_PRODUCT_BYNAME);
//                ResultSet results = statement.executeQuery(QUERY_CUSTOMERBUY)
            statementReceiptProductByName.setString(1, name);
            statementReceiptProductByName.setString(2, kind);
            statementReceiptProductByName.setInt(3, month);
            ResultSet results = statementReceiptProductByName.executeQuery();
            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            while (results.next()) {
                ReceiptProduct receiptProduct = new ReceiptProduct();
                receiptProduct.setReceiptProductID(results.getString(1));
                receiptProduct.setReceiptEmployeeId(results.getString(2));
                receiptProduct.setReceiptProductName(results.getString(3));
                receiptProduct.setReceiptProductKind(results.getString(4));
                receiptProduct.setReceiptProductUnit(results.getString(5));
                receiptProduct.setReceiptProductPrice(results.getInt(6));
                receiptProduct.setReceiptProductStock(results.getInt(7));
                receiptProduct.setReceiptProductdateOfTransaction(results.getDate(8).toString());
                receiptProducts.add(receiptProduct);
            }

            return receiptProducts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<ReceiptProduct> queryReceiptByProductName(String name, String kind) {

        try {
            statementReceiptProductByNameKind = conn.prepareStatement(QUERY_RECEIPT_PRODUCT_BYNAMEKIND);
//                ResultSet results = statement.executeQuery(QUERY_CUSTOMERBUY)
            statementReceiptProductByNameKind.setString(1, name);
            statementReceiptProductByNameKind.setString(2, kind);
            ResultSet results = statementReceiptProductByNameKind.executeQuery();
            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            while (results.next()) {
                ReceiptProduct receiptProduct = new ReceiptProduct();
                receiptProduct.setReceiptProductID(results.getString(1));
                receiptProduct.setReceiptEmployeeId(results.getString(2));
                receiptProduct.setReceiptProductName(results.getString(3));
                receiptProduct.setReceiptProductKind(results.getString(4));
                receiptProduct.setReceiptProductUnit(results.getString(5));
                receiptProduct.setReceiptProductPrice(results.getInt(6));
                receiptProduct.setReceiptProductStock(results.getInt(7));
                receiptProduct.setReceiptProductdateOfTransaction(results.getDate(8).toString());
                receiptProduct.setProductId(searchProductByNameKind(receiptProduct.getReceiptProductName(), receiptProduct.getReceiptProductKind()).get(0).getProductID());

                receiptProducts.add(receiptProduct);
            }

            return receiptProducts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<IssueProduct> queryIssueByProductName(String name, String kind, int month) {

        try {
            statementIssueProductByName = conn.prepareStatement(QUERY_ISSUE_PRODUCT_BYNAME);
//                ResultSet results = statement.executeQuery(QUERY_CUSTOMERBUY)
            statementIssueProductByName.setString(1, name);
            statementIssueProductByName.setString(2, kind);
            statementIssueProductByName.setInt(3, month);
            ResultSet results = statementIssueProductByName.executeQuery();
            List<IssueProduct> issueProducts = new ArrayList<>();
            while (results.next()) {
                IssueProduct issueProduct = new IssueProduct();
                issueProduct.setTransactionId(results.getString(1));
                issueProduct.setEmployeeId(results.getString(2));
                issueProduct.setProductName(results.getString(3));
                issueProduct.setKind(results.getString(4));
                issueProduct.setUnit(results.getString(5));
                issueProduct.setPrice(results.getInt(6));
                issueProduct.setQuantity(results.getInt(7));
                issueProduct.setDateOfTransaction(results.getDate(8).toString());

                issueProducts.add(issueProduct);
            }

            return issueProducts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<IssueProduct> queryIssueByProductNameKind(String name, String kind) {

        try {
            statementIssueProductByNameKind = conn.prepareStatement(QUERY_ISSUE_PRODUCT_BYNAMEKIND);

            statementIssueProductByNameKind.setString(1, name);
            statementIssueProductByNameKind.setString(2, kind);

            ResultSet results = statementIssueProductByNameKind.executeQuery();
            List<IssueProduct> issueProducts = new ArrayList<>();
            while (results.next()) {
                IssueProduct issueProduct = new IssueProduct();
                issueProduct.setTransactionId(results.getString(1));
                issueProduct.setEmployeeId(results.getString(2));
                issueProduct.setProductName(results.getString(3));
                issueProduct.setKind(results.getString(4));
                issueProduct.setUnit(results.getString(5));
                issueProduct.setPrice(results.getInt(6));
                issueProduct.setQuantity(results.getInt(7));
                issueProduct.setDateOfTransaction(results.getDate(8).toString());

                issueProducts.add(issueProduct);
            }

            return issueProducts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<CustomerBuy> queryBestCustomerBuyMonth(String inputId, int month) {

        try {
            statementBestCustomerBuyMonth = conn.prepareStatement(QUERY_BESTCUSTOMER_MONTH);
//                ResultSet results = statement.executeQuery(QUERY_CUSTOMERBUY)
            statementBestCustomerBuyMonth.setString(1, inputId);
            statementBestCustomerBuyMonth.setInt(2, month);
            ResultSet rs = statementBestCustomerBuyMonth.executeQuery();
            List<CustomerBuy> customerBuys = new ArrayList<>();
            while (rs.next()) {
                CustomerBuy customerBuy = new CustomerBuy();

                customerBuy.setCustomerId(rs.getString(1));
                customerBuy.setCustomerName(rs.getString(2));
                customerBuy.setCustomerAdd(rs.getString(3));
                customerBuy.setCustomerPhone(rs.getString(4));
                customerBuy.setCustomerEmail(rs.getString(5));
                customerBuy.setDateOfTransaction(rs.getDate(6).toString());
                customerBuy.setTimeOfTransaction(rs.getTime(6).toString());
                customerBuy.setProductName(rs.getString(7));
                customerBuy.setKind(rs.getString(8));
                customerBuy.setUnit(rs.getString(9));
                customerBuy.setPrice(rs.getInt(10));
                customerBuy.setQuantity(rs.getInt(11));
                customerBuy.setTotalPrice(rs.getInt(12));

                customerBuys.add(customerBuy);
            }

            return customerBuys;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<EmployeeSale> queryBestEmployeeSaleMonth(String inputId, int month) {

        try {
            statementBestEmployeeBuyMonth = conn.prepareStatement(QUERY_BESTEMLOYEE_MONTH);
            statementBestEmployeeBuyMonth.setString(1, inputId);
            statementBestEmployeeBuyMonth.setInt(2, month);
            ResultSet rs = statementBestEmployeeBuyMonth.executeQuery();
            List<EmployeeSale> customerBuys = new ArrayList<>();
            while (rs.next()) {
                EmployeeSale employeeSale = new EmployeeSale();
                employeeSale.setEmployeeId(rs.getString(1));
                employeeSale.setEmployeeName(rs.getString(2));
                employeeSale.setEmployeePhone(rs.getString(3));
                employeeSale.setEmployeeEmail(rs.getString(4));
                employeeSale.setDateOfTransaction(rs.getDate(5).toString());
                employeeSale.setTimeOfTransaction(rs.getTime(5).toString());
                employeeSale.setProductName(rs.getString(6));
                employeeSale.setKind(rs.getString(7));
                employeeSale.setUnit(rs.getString(8));
                employeeSale.setPrice(rs.getInt(9));
                employeeSale.setQuantity(rs.getInt(10));
                employeeSale.setTotalPrice(rs.getInt(11));

                customerBuys.add(employeeSale);
            }

            return customerBuys;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<ProductSales> queryBestSaleMonth(int month) {

        try {
            statementBestSaleMonth = conn.prepareStatement(QUERY_BESTSALE_MONTH);
            statementBestSaleMonth.setInt(1, month);
            ResultSet rs = statementBestSaleMonth.executeQuery();
            List<ProductSales> bestProductSaleses = new ArrayList<>();
            while (rs.next()) {
                ProductSales productSales = new ProductSales();
                productSales.setTransactionId(rs.getString(1));
                productSales.setEmployeeId(rs.getString(2));
                productSales.setCustomerId(rs.getString(3));
                productSales.setProductId(rs.getString(4));
                productSales.setDateOfTransaction(rs.getDate(5).toString());
                productSales.setTimeOfTransaction(rs.getTime(5).toString());
                productSales.setProductName(rs.getString(6));
                productSales.setKind(rs.getString(7));
                productSales.setUnit(rs.getString(8));
                productSales.setPrice(rs.getInt(9));
                productSales.setQuantity(rs.getInt(10));
                productSales.setTotalPrice(rs.getInt(11));

                bestProductSaleses.add(productSales);
            }

            return bestProductSaleses;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public String searchCustomerIDByPhone(String phone) {
        String result = null;
        try {
            searchCustomerIdByPhone = conn.prepareStatement(FIND_CUSTOMERID_PHONE);

            searchCustomerIdByPhone.setString(1, phone);
            ResultSet rs = searchCustomerIdByPhone.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
                return result;
            } else {
                System.out.println("Can't find customer");

            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Can't find customer - Check Connection: " + e.getMessage());
            return result;
        }
    }

    public void updateproductQuantity(TableView<ProductSales> orderTable) {
        List<Product> products = Datasource.getInstance().queryProducts();
        ObservableList<ProductSales> allProducts = orderTable.getItems();
        try {
            updateProductQuantityState = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY);

            for (int i = 0; i < allProducts.size(); i++) {
                int quantity = allProducts.get(i).getQuantity();
                String productName = allProducts.get(i).getProductName();
                String productKind = allProducts.get(i).getKind();
                int productQuantity = allProducts.get(i).getQuantity();

                for (int j = 0; j < products.size(); j++) {
                    if (productName.equalsIgnoreCase(products.get(j).getProductName()) && productKind.equalsIgnoreCase(products.get(j).getProductKind())) {
                        updateProductQuantityState.setInt(1, (products.get(j).getProductStock() - productQuantity));
                        updateProductQuantityState.setString(2, productName);
                        updateProductQuantityState.setString(3, productKind);
                        updateProductQuantityState.executeUpdate();
//                        System.out.println("quantity: " + (products.get(j).getProductStock() - productQuantity));
                        break;
                    }

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String searchByNameAndKind(String name, String kind) {
        String result = null;
        try {
            searchByNameKind = conn.prepareStatement(FIND_PRODUCT_NAME_KIND);
            searchByNameKind.setString(1, name);
            searchByNameKind.setString(2, kind);
            ResultSet rs = searchByNameKind.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
//                orderTable.refresh();
//                System.out.println(result);
                return result;
            } else {
                System.out.println("Can't find product");

            }

            rs.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Can't find product - Check Connection: " + e.getMessage());
            return result;
        }
    }

    public int insertOrderListDB(String employeeId, String customerPhoneProductSale, String time, Integer totalInvoice) {
        try {
            statementInsertOrderList = conn.prepareStatement(INSERT_ORDERLIST, Statement.RETURN_GENERATED_KEYS);
            statementInsertOrderList.setString(1, employeeId);
            statementInsertOrderList.setString(2, searchCustomerIDByPhone(customerPhoneProductSale));
            statementInsertOrderList.setTimestamp(3, java.sql.Timestamp.valueOf(time));
            statementInsertOrderList.setInt(4, totalInvoice);
            statementInsertOrderList.executeUpdate();

            ResultSet generatedKeys = statementInsertOrderList.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public String queryOrderIdDB(int orderid) {
        String orderIdDB = null;
        try {
            statementOrderId = conn.prepareStatement(QUERY_ORDERID);
            statementOrderId.setInt(1, orderid);
            ResultSet results = statementOrderId.executeQuery();

            while (results.next()) {
                orderIdDB = results.getString(1);
                return orderIdDB;
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return orderIdDB;
    }

    public void insertProductSalesDB(String employeeId, String customerPhoneProductSale, String time, TableView<ProductSales> orderTable, Integer totalInvoice) {
        try {
            statementInsertProductSale = conn.prepareStatement(INSERT_PRODUCTSALE);
            //employeeId, customerPhoneProductSale, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice
            ObservableList<ProductSales> allProducts = orderTable.getItems();
            String orderId = queryOrderIdDB(insertOrderListDB(employeeId, customerPhoneProductSale, time, totalInvoice));
            for (int i = 0; i < allProducts.size(); i++) {
                String product = searchByNameAndKind(allProducts.get(i).getProductName(), allProducts.get(i).getKind());
//                System.out.println("ID product: " + product);

                statementInsertProductSale.setString(1, orderId);
                statementInsertProductSale.setString(2, employeeId);
                statementInsertProductSale.setString(3, searchCustomerIDByPhone(customerPhoneProductSale));
                statementInsertProductSale.setString(4, product);
                statementInsertProductSale.setTimestamp(5, java.sql.Timestamp.valueOf(time));
                statementInsertProductSale.setString(6, allProducts.get(i).getProductName());
                statementInsertProductSale.setString(7, allProducts.get(i).getKind());
                statementInsertProductSale.setString(8, allProducts.get(i).getUnit());
                statementInsertProductSale.setInt(9, allProducts.get(i).getPrice());
                statementInsertProductSale.setInt(10, allProducts.get(i).getQuantity());
                statementInsertProductSale.setInt(11, allProducts.get(i).getTotalPrice());
                statementInsertProductSale.executeUpdate();

            }
            updateproductQuantity(orderTable);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertProductDB(String productName, String productKind, String productUnit, String productPrice, String productCategory, String productStock) {

        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            statementInsertProduct = conn.prepareStatement(INSERT_PRODUCT);

            statementInsertProduct.setString(1, productName);
            statementInsertProduct.setString(2, productKind);
            statementInsertProduct.setString(3, productUnit);
            statementInsertProduct.setInt(4, Integer.parseInt(productPrice));
            statementInsertProduct.setString(5, productCategory);
            statementInsertProduct.setInt(6, Integer.parseInt(productStock));
            statementInsertProduct.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insert Product");
            alert.setHeaderText("Insert Product");
            alert.setContentText("You already insert product name: " + productName);
            Optional<ButtonType> insert = alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (NumberFormatException exN) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Product");
            alert.setHeaderText("Product input form");
            alert.setContentText("Please fill in product input form!");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    public void deleteProductDB(String productNameDelete, String productKindDelete) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Delete Product");
            alert.setContentText("Are you sure to delete product name: " + productNameDelete + " Kind: " + productKindDelete);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                statementDeleteProduct = conn.prepareStatement(DELETE_PRODUCT);
                statementDeleteProduct.setString(1, productNameDelete);
                statementDeleteProduct.setString(2, productKindDelete);
                statementDeleteProduct.executeUpdate();

                //show in tree table view
//                    showProductTreeTableView();
            }

            //clear text field
//                productNameDelete.clear();
//                productKindDelete.clear();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Product searchProductById(String productIdUpdate) {
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            stateSearchProductByID = conn.prepareStatement(FIND_UPDATE_PRODUCT);
            stateSearchProductByID.setString(1, productIdUpdate);
            ResultSet rs = stateSearchProductByID.executeQuery();
            Product product = new Product();
            if (rs.next()) {
                product.setProductID(rs.getString(1));
                product.setProductName(rs.getString(2));
                product.setProductKind(rs.getString(3));
                product.setProductPrice(rs.getInt(4));
                product.setProductCategory(rs.getString(5));
                product.setProductStock(rs.getInt(6));

            } else {
                System.out.println("Can't find product");
                return null;
            }

            rs.close();
            return product;
        } catch (SQLException e) {
            System.out.println("Can't find product - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public void updateProductDb(Product updateProduct, String productIdUpdate) {
        try {

            stateUpdateProduct = conn.prepareStatement(UPDATE_PRODUCT);
            stateUpdateProduct.setString(1, updateProduct.getProductName());
            stateUpdateProduct.setString(2, updateProduct.getProductKind());
            stateUpdateProduct.setInt(3, updateProduct.getProductPrice());
            stateUpdateProduct.setString(4, updateProduct.getProductCategory());
            stateUpdateProduct.setInt(5, updateProduct.getProductStock());
            stateUpdateProduct.setString(6, searchProductById(productIdUpdate).getProductID());
            stateUpdateProduct.executeUpdate();

//            showProductTreeTableView();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Product");
            alert.setHeaderText("Update Product");
            alert.setContentText("You already update product name: " + updateProduct.getProductName() + "\n Product ID: " + productIdUpdate);
            Optional<ButtonType> ok = alert.showAndWait();
//            productIdUpdate.clear();

        } catch (SQLException e) {
            System.out.println("Can't Update - Check Connection: " + e.getMessage());

        }
    }

    public void insertCustomerBb(String customerName, String customerAdd, String customerPhone, String customerEmail) {
        try {

            stateInsertCustomer = conn.prepareStatement(INSERT_CUSTOMER);
            //customerName, customerAdd, customerPhone, customerEmail
            stateInsertCustomer.setString(1, customerName);
            stateInsertCustomer.setString(2, customerAdd);
            stateInsertCustomer.setString(3, customerPhone);
            stateInsertCustomer.setString(4, customerEmail);
            stateInsertCustomer.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertReceiptProductDB(String receiptEmployeeId, String receiptProductName, String receiptProductKind, String receiptProductUnit, Integer receiptProductPrice, Integer receiptProductStock, String receiptProductdateOfTransaction) {
        try {

            stateInsertReceipt = conn.prepareStatement(INSERT_RECEIPT_PRODUCT);
            //customerName, customerAdd, customerPhone, customerEmail
            stateInsertReceipt.setString(1, receiptEmployeeId);
            stateInsertReceipt.setString(2, receiptProductName);
            stateInsertReceipt.setString(3, receiptProductKind);
            stateInsertReceipt.setString(4, receiptProductUnit);
            stateInsertReceipt.setInt(5, receiptProductPrice);
            stateInsertReceipt.setInt(6, receiptProductStock);
            stateInsertReceipt.setTimestamp(7, java.sql.Timestamp.valueOf(receiptProductdateOfTransaction));
            stateInsertReceipt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<ReceiptProduct> queryReceiptProductDB() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_RECEIPT_PRODUCT)) {

            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            while (results.next()) {
                //SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct";
                ReceiptProduct receiptProduct = new ReceiptProduct();
                receiptProduct.setReceiptProductID(results.getString(1));
                receiptProduct.setReceiptEmployeeId(results.getString(2));
                receiptProduct.setReceiptProductName(results.getString(3));
                receiptProduct.setReceiptProductKind(results.getString(4));
                receiptProduct.setReceiptProductUnit(results.getString(5));
                receiptProduct.setReceiptProductPrice(results.getInt(6));
                receiptProduct.setReceiptProductStock(results.getInt(7));
                receiptProduct.setReceiptProductdateOfTransaction(results.getDate(8).toString());
                receiptProduct.setProductId(searchProductByNameKind(receiptProduct.getReceiptProductName(), receiptProduct.getReceiptProductKind()).get(0).getProductID());

                receiptProducts.add(receiptProduct);
            }

            return receiptProducts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryReceiptProductNameDB() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_RECEIPT_PRODUCTNAME)) {

//            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            List<String> productNames = new ArrayList<>();
            while (results.next()) {
                //SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction FROM ReceiptProduct";
//                ReceiptProduct receiptProduct = new ReceiptProduct();
//                receiptProduct.setReceiptProductID(results.getString(1));
//                receiptProduct.setReceiptEmployeeId(results.getString(2));
//                receiptProduct.setReceiptProductName(results.getString(3));
//                receiptProduct.setReceiptProductKind(results.getString(4));
//                receiptProduct.setReceiptProductUnit(results.getString(5));
//                receiptProduct.setReceiptProductPrice(results.getInt(6));
//                receiptProduct.setReceiptProductStock(results.getInt(7));
//                receiptProduct.setReceiptProductdateOfTransaction(results.getDate(8).toString());

                productNames.add(results.getString(1));
//                receiptProducts.add(receiptProduct);
            }

            return productNames;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<IssueProduct> queryIssueProductDB() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_ISSUE_PRODUCT_FROMPRODUCTSALES)) {

            List<IssueProduct> issueProducts = new ArrayList<>();
            while (results.next()) {
                IssueProduct issueProduct = new IssueProduct();
                issueProduct.setTransactionId(results.getString(1));
                issueProduct.setEmployeeId(results.getString(2));
                issueProduct.setProductName(results.getString(3));
                issueProduct.setKind(results.getString(4));
                issueProduct.setUnit(results.getString(5));
                issueProduct.setPrice(results.getInt(6));
                issueProduct.setQuantity(results.getInt(7));
                issueProduct.setDateOfTransaction(results.getDate(8).toString());

                issueProducts.add(issueProduct);
            }

            return issueProducts;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryIssueProductNameDB() {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(QUERY_ISSUE_PRODUCT_FROMPRODUCTSALESNAME)) {

            List<String> productNames = new ArrayList<>();
            while (results.next()) {

                productNames.add(results.getString(1));
            }

            return productNames;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public String searchCustomerIDByNameAndPhone(String name, String phone) {
        String result = null;
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            searchCustomerIdByNamePhone = conn.prepareStatement(FIND_CUSTOMERID_NAME_PHONE);
            searchCustomerIdByNamePhone.setString(1, name);
            searchCustomerIdByNamePhone.setString(2, phone);
            ResultSet rs = searchCustomerIdByNamePhone.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
//                System.out.println(result);
                return result;
            } else {
                System.out.println("Can't find customer");

            }

            rs.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Can't find customer - Check Connection: " + e.getMessage());
            return result;
        }
    }

    public void deleteCustomerDb(String customerNameDelete, String customerPhoneDelete) {
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            stateDeleteCustomer = conn.prepareStatement(DELETE_CUSTOMER);
            stateDeleteCustomer.setString(1, customerNameDelete);
            stateDeleteCustomer.setString(2, customerPhoneDelete);
            stateDeleteCustomer.executeUpdate();

//            showCustomerTableView();
            //clear text field
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateCustomerDb(Customer updateCustomer, String inputId) {
        try {
//                    conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            stateUpdateCustomer = conn.prepareStatement(UPDATE_CUSTOMER);
            stateUpdateCustomer.setString(1, updateCustomer.getCustomerName());
            stateUpdateCustomer.setString(2, updateCustomer.getCustomerAdd());
            stateUpdateCustomer.setString(3, updateCustomer.getCustomerPhone());
            stateUpdateCustomer.setString(4, updateCustomer.getCustomerEmail());
            stateUpdateCustomer.setString(5, searchCutomerById(inputId).getCustomerId());
            stateUpdateCustomer.executeUpdate();

//                    showCustomerTableView();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Customer");
            alert.setHeaderText("Update Customer");
            alert.setContentText("You already update Customer name: " + updateCustomer.getCustomerName());
            Optional<ButtonType> ok = alert.showAndWait();
//                    customerIdUpdate.clear();

        } catch (SQLException e) {
            System.out.println("Can't Update - Check Connection: " + e.getMessage());

        }
    }

    public Customer searchCutomerById(String inputId) {
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            searchCustomerByID = conn.prepareStatement(FIND_UPDATE_CUSTOMER);
            searchCustomerByID.setString(1, inputId);
            ResultSet rs = searchCustomerByID.executeQuery();
            Customer customer = new Customer();
            if (rs.next()) {
                customer.setCustomerId(rs.getString(1));
                customer.setCustomerName(rs.getString(2));
                customer.setCustomerAdd(rs.getString(3));
                customer.setCustomerPhone(rs.getString(4));
                customer.setCustomerEmail(rs.getString(5));

            } else {
                System.out.println("Can't find Customer");
                return null;
            }

            rs.close();
            return customer;
        } catch (SQLException e) {
            System.out.println("Can't find Customer - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public void deleteEmployeeDb(String employeeIdDelete) {
        try {
            stateDeleteEmployee = conn.prepareStatement(DELETE_EMPLOYEE);
            stateDeleteEmployee.setString(1, employeeIdDelete);
            stateDeleteEmployee.executeUpdate();
            //clear text field
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void deleteUserPromote(String userIdDelete) {
        try {
            stateDeleteUserPromote = conn.prepareStatement(DELETE_USERPROMOTE);
            stateDeleteUserPromote.setString(1, userIdDelete);
            stateDeleteUserPromote.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateEmployeeDb(User updateUser, String inputId) {
        try {

            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            stateUpdateEmployee = conn.prepareStatement(UPDATE_USER);
            stateUpdateEmployee.setString(1, updateUser.getFullName());
            stateUpdateEmployee.setString(2, updateUser.getEmail());
            stateUpdateEmployee.setString(3, updateUser.getPhoneNumber());
            stateUpdateEmployee.setDate(4, java.sql.Date.valueOf(updateUser.getBirthday()));
            stateUpdateEmployee.setString(5, updateUser.getGender());
            stateUpdateEmployee.setString(6, updateUser.getUserName());
            stateUpdateEmployee.setString(7, updateUser.getUserPass());
            stateUpdateEmployee.setString(8, searchEmployeeById(inputId).getUserId());
            stateUpdateEmployee.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Employee");
            alert.setHeaderText("Update Employee");
            alert.setContentText("You already update Employee name: " + updateUser.getFullName());
            Optional<ButtonType> ok = alert.showAndWait();
        } catch (SQLException e) {
            System.out.println("Can't Update - Check Connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User searchEmployeeById(String inputId) {
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            searchEmployeeByID = conn.prepareStatement(FIND_UPDATE_EMPLOYEE);
            searchEmployeeByID.setString(1, inputId);
            ResultSet rs = searchEmployeeByID.executeQuery();
            User user = new User();
            if (rs.next()) {
                user.setUserId(rs.getString(1));
                user.setFullName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setBirthday(rs.getDate(5).toString());
                user.setGender(rs.getString(6));
                user.setUserName(rs.getString(7));
                user.setUserPass(rs.getString(8));

            } else {
                System.out.println("Can't find User");
                return null;
            }

            rs.close();
            return user;
        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public int checkStock(String name, String kind) {
        int result = 0;
        try {
            stateCheckStock = conn.prepareStatement(CHECK_STOCK_PRODUCT);
            stateCheckStock.setString(1, name);
            stateCheckStock.setString(2, kind);
            ResultSet rs = stateCheckStock.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
//                orderTable.refresh();
//                System.out.println(result);
                return result;
            } else {
                System.out.println("Can't find product");

            }

            rs.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Can't find product - Check Connection: " + e.getMessage());
            return result;
        }
    }

    public void insertUserWorking(String employeeId, String dateStart, String time) {
        try {
            stateInsertStartOfDate = conn.prepareStatement(INSERT_STARTDATE);
            stateInsertStartOfDate.setString(1, employeeId);
            stateInsertStartOfDate.setString(2, dateStart);
            stateInsertStartOfDate.setString(3, time);
            stateInsertStartOfDate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> searchEmployeeByName(String name) {
        try {
            searchEmployeeByName = conn.prepareStatement(FIND_EMPLOYEE_BYNAME);
            searchEmployeeByName.setString(1, "%" + name + "%");
            ResultSet rs = searchEmployeeByName.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString(1));
                user.setFullName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setBirthday(rs.getDate(5).toString());
                user.setGender(rs.getString(6));
                user.setUserName(rs.getString(7));
                user.setUserPass(rs.getString(8));

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<Customer> searchCustomerByName(String name) {
        try {
            searchCustomerByName = conn.prepareStatement(FIND_CUSTOMER_BYNAME);
            searchCustomerByName.setString(1, "%" + name + "%");
            ResultSet rs = searchCustomerByName.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString(1));
                customer.setCustomerName(rs.getString(2));
                customer.setCustomerAdd(rs.getString(3));
                customer.setCustomerPhone(rs.getString(4));
                customer.setCustomerEmail(rs.getString(5));

                customers.add(customer);
            }
            return customers;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }
    
    

    public List<IssueProduct> searchIssueProductByDate(String date) {
        try {
            searchIssueByDate = conn.prepareStatement(FIND_ISSUE_DATE);
            searchIssueByDate.setString(1, date);
            ResultSet rs = searchIssueByDate.executeQuery();
            List<IssueProduct> issueProducts = new ArrayList<>();
            while (rs.next()) {
                IssueProduct issueProduct = new IssueProduct();
                issueProduct.setTransactionId(rs.getString(1));
                issueProduct.setEmployeeId(rs.getString(2));
                issueProduct.setProductName(rs.getString(3));
                issueProduct.setKind(rs.getString(4));
                issueProduct.setUnit(rs.getString(5));
                issueProduct.setPrice(rs.getInt(6));
                issueProduct.setQuantity(rs.getInt(7));
                issueProduct.setDateOfTransaction(rs.getDate(8).toString());
                issueProducts.add(issueProduct);
            }
            return issueProducts;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<IssueProduct> searchIssueProductByBetweenDate(String date1, String date2) {
        try {
            searchIssueByBetweenDate = conn.prepareStatement(FIND_ISSUE_BETWEENDATE);
            searchIssueByBetweenDate.setString(1, date1);
            searchIssueByBetweenDate.setString(2, date2);
            ResultSet rs = searchIssueByBetweenDate.executeQuery();
            List<IssueProduct> issueProducts = new ArrayList<>();
            while (rs.next()) {
                IssueProduct issueProduct = new IssueProduct();
                issueProduct.setTransactionId(rs.getString(1));
                issueProduct.setEmployeeId(rs.getString(2));
                issueProduct.setProductName(rs.getString(3));
                issueProduct.setKind(rs.getString(4));
                issueProduct.setUnit(rs.getString(5));
                issueProduct.setPrice(rs.getInt(6));
                issueProduct.setQuantity(rs.getInt(7));
                issueProduct.setDateOfTransaction(rs.getDate(8).toString());
                issueProducts.add(issueProduct);
            }
            return issueProducts;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<IssueProduct> searchIssueProductByEmployeeId(String employeeId) {
        try {
            searchIssueByEmployeeId = conn.prepareStatement(FIND_ISSUE_EMPLOYEEID);
            searchIssueByEmployeeId.setString(1, employeeId);
            ResultSet rs = searchIssueByEmployeeId.executeQuery();
            List<IssueProduct> issueProducts = new ArrayList<>();
            while (rs.next()) {
                IssueProduct issueProduct = new IssueProduct();
                issueProduct.setTransactionId(rs.getString(1));
                issueProduct.setEmployeeId(rs.getString(2));
                issueProduct.setProductName(rs.getString(3));
                issueProduct.setKind(rs.getString(4));
                issueProduct.setUnit(rs.getString(5));
                issueProduct.setPrice(rs.getInt(6));
                issueProduct.setQuantity(rs.getInt(7));
                issueProduct.setDateOfTransaction(rs.getDate(8).toString());
                issueProducts.add(issueProduct);
            }
            return issueProducts;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<ReceiptProduct> searchReceiptProductByEmployeeId(String employeeId) {
        try {
            searchReceiptByEmployeeId = conn.prepareStatement(FIND_RECEIPT_EMPLOYEEID);
            searchReceiptByEmployeeId.setString(1, employeeId);
            ResultSet rs = searchReceiptByEmployeeId.executeQuery();
            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            while (rs.next()) {
                ReceiptProduct receiptProduct = new ReceiptProduct();
                receiptProduct.setReceiptProductID(rs.getString(1));
                receiptProduct.setReceiptEmployeeId(rs.getString(2));
                receiptProduct.setReceiptProductName(rs.getString(3));
                receiptProduct.setReceiptProductKind(rs.getString(4));
                receiptProduct.setReceiptProductUnit(rs.getString(5));
                receiptProduct.setReceiptProductPrice(rs.getInt(6));
                receiptProduct.setReceiptProductStock(rs.getInt(7));
                receiptProduct.setReceiptProductdateOfTransaction(rs.getDate(8).toString());
                receiptProduct.setProductId(searchProductByNameKind(receiptProduct.getReceiptProductName(), receiptProduct.getReceiptProductKind()).get(0).getProductID());

                receiptProducts.add(receiptProduct);
            }
            return receiptProducts;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<ReceiptProduct> searchReceiptProductByDate(String date) {
        try {
            searchReceiptByDate = conn.prepareStatement(FIND_RECEIPT_DATE);
            searchReceiptByDate.setString(1, date);
            ResultSet rs = searchReceiptByDate.executeQuery();
            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            while (rs.next()) {
                ReceiptProduct receiptProduct = new ReceiptProduct();
                receiptProduct.setReceiptProductID(rs.getString(1));
                receiptProduct.setReceiptEmployeeId(rs.getString(2));
                receiptProduct.setReceiptProductName(rs.getString(3));
                receiptProduct.setReceiptProductKind(rs.getString(4));
                receiptProduct.setReceiptProductUnit(rs.getString(5));
                receiptProduct.setReceiptProductPrice(rs.getInt(6));
                receiptProduct.setReceiptProductStock(rs.getInt(7));
                receiptProduct.setReceiptProductdateOfTransaction(rs.getDate(8).toString());

                receiptProducts.add(receiptProduct);
            }
            return receiptProducts;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<ReceiptProduct> searchReceiptProductByBetweenDate(String date1, String date2) {
        try {
            searchReceiptByBetweenDate = conn.prepareStatement(FIND_RECEIPT_BETWEENDATE);
            searchReceiptByBetweenDate.setString(1, date1);
            searchReceiptByBetweenDate.setString(2, date2);
            ResultSet rs = searchReceiptByBetweenDate.executeQuery();
            List<ReceiptProduct> receiptProducts = new ArrayList<>();
            while (rs.next()) {
                ReceiptProduct receiptProduct = new ReceiptProduct();
                receiptProduct.setReceiptProductID(rs.getString(1));
                receiptProduct.setReceiptEmployeeId(rs.getString(2));
                receiptProduct.setReceiptProductName(rs.getString(3));
                receiptProduct.setReceiptProductKind(rs.getString(4));
                receiptProduct.setReceiptProductUnit(rs.getString(5));
                receiptProduct.setReceiptProductPrice(rs.getInt(6));
                receiptProduct.setReceiptProductStock(rs.getInt(7));
                receiptProduct.setReceiptProductdateOfTransaction(rs.getDate(8).toString());
                receiptProduct.setProductId(searchProductByNameKind(receiptProduct.getReceiptProductName(), receiptProduct.getReceiptProductKind()).get(0).getProductID());
                

                receiptProducts.add(receiptProduct);
            }
            return receiptProducts;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<OrderList> searchOrderListByDate(String date) {
        try {
            searchOrderListByDate = conn.prepareStatement(FIND_ORDERLIST_DATE);
            searchOrderListByDate.setString(1, date);
            ResultSet rs = searchOrderListByDate.executeQuery();
            List<OrderList> orderLists = new ArrayList<>();
            while (rs.next()) {
                OrderList orderList = new OrderList();
                orderList.setOrderId(rs.getString(1));
                orderList.setEmployeeId(rs.getString(2));
                orderList.setCustomerId(rs.getString(3));
                orderList.setDateOrder(rs.getDate(4).toString());
                orderList.setTotalInvoice(rs.getInt(5));

                orderLists.add(orderList);
            }
            return orderLists;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }
    
     public String searchOrderIDByDateTime() {
        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(FIND_ORDERLIST_DATETIME)) {
            String orderID = null; 
            while (results.next()) {
                OrderList orderList = new OrderList();
                orderList.setOrderId(results.getString(1));

                
                return orderList.getOrderId();
            }

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
        return null;
    }

    public List<OrderList> searchOrderListByEmployeeID(String employeeId) {
        try {
            searchOrderListByEmployeeID = conn.prepareStatement(FIND_ORDERLIST_EMPLOYEEID);
            searchOrderListByEmployeeID.setString(1, employeeId);
            ResultSet rs = searchOrderListByEmployeeID.executeQuery();
            List<OrderList> orderLists = new ArrayList<>();
            while (rs.next()) {
                OrderList orderList = new OrderList();
                orderList.setOrderId(rs.getString(1));
                orderList.setEmployeeId(rs.getString(2));
                orderList.setCustomerId(rs.getString(3));
                orderList.setDateOrder(rs.getDate(4).toString());
                orderList.setTimeOrder(rs.getTime(4).toString());
                orderList.setTotalInvoice(rs.getInt(5));
                orderList.setEmployeeName(rs.getString(6));
                orderList.setCustomerName(rs.getString(7));

                orderLists.add(orderList);
            }
            return orderLists;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<OrderList> searchOrderListByBetweenDate(String date1, String date2) {
        try {
            searchOrderByBetweenDate = conn.prepareStatement(FIND_ORDERLIST_BETWEENDATE);
            searchOrderByBetweenDate.setString(1, date1);
            searchOrderByBetweenDate.setString(2, date2);
            ResultSet rs = searchOrderByBetweenDate.executeQuery();
            List<OrderList> orderLists = new ArrayList<>();
            while (rs.next()) {
                OrderList orderList = new OrderList();
                orderList.setOrderId(rs.getString(1));
                orderList.setEmployeeId(rs.getString(2));
                orderList.setCustomerId(rs.getString(3));
                orderList.setDateOrder(rs.getDate(4).toString());
                orderList.setTimeOrder(rs.getTime(4).toString());
                orderList.setTotalInvoice(rs.getInt(5));
                orderList.setEmployeeName(rs.getString(6));
                orderList.setCustomerName(rs.getString(7));

                orderLists.add(orderList);
            }
            return orderLists;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<Product> searchProductByName(String name) {
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            searchProductByName = conn.prepareStatement(FIND_PRODUCT_BYNAME);
            searchProductByName.setString(1, "%" + name + "%");
            ResultSet rs = searchProductByName.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getString(1));
                product.setProductName(rs.getString(2));
                product.setProductKind(rs.getString(3));
                product.setProductPrice(rs.getInt(4));
                product.setProductCategory(rs.getString(5));
                product.setProductStock(rs.getInt(6));

                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<Product> searchProductByNameKind(String name, String kind) {
        try {
//            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            searchProductByNameKind = conn.prepareStatement(FIND_PRODUCT_BYNAMEKIND);
            searchProductByNameKind.setString(1, name);
            searchProductByNameKind.setString(2, kind);
            ResultSet rs = searchProductByNameKind.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getString(1));
                product.setProductName(rs.getString(2));
                product.setProductKind(rs.getString(3));
                product.setProductUnit(rs.getString(4));
                product.setProductPrice(rs.getInt(5));
                product.setProductCategory(rs.getString(6));
                product.setProductStock(rs.getInt(7));

                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.out.println("Can't find Product - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public List<ProductSales> searchOrderInvoice(String orderId) {
        try {
            searchProductByName = conn.prepareStatement(FIND_ORDERID);
            searchProductByName.setString(1, orderId);
            ResultSet rs = searchProductByName.executeQuery();
            List<ProductSales> productSaleses = new ArrayList<>();
            while (rs.next()) {
                ProductSales productSales = new ProductSales();
                productSales.setOrderId(rs.getString(1));
                productSales.setEmployeeId(rs.getString(2));
                productSales.setCustomerId(rs.getString(3));
                productSales.setDateOfTransaction(rs.getDate(4).toString() + " " + rs.getTime(4).toString());
                productSales.setProductName(rs.getString(5));
                productSales.setKind(rs.getString(6));
                productSales.setUnit(rs.getString(7));
                productSales.setPrice(rs.getInt(8));
                productSales.setQuantity(rs.getInt(9));
                productSales.setTotalPrice(rs.getInt(10));

                productSaleses.add(productSales);
            }
            return productSaleses;

        } catch (SQLException e) {
            System.out.println("Can't find User - Check Connection: " + e.getMessage());
            return null;
        }
    }

    public String searchCustomerNameById(String customerId) {
        String result = null;
        try {
            searchCustomerNameById = conn.prepareStatement(FIND_CUSTOMERNAME_BYID);
            searchCustomerNameById.setString(1, customerId);

            ResultSet rs = searchCustomerNameById.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
//                System.out.println(result);
                return result;
            } else {
                System.out.println("Can't find customer");
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Can't find customer - Check Connection: " + e.getMessage());
            return result;
        }
    }
}
