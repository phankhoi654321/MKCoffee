USE master
GO
-- 1.Create database
IF  EXISTS (
	SELECT name
FROM sys.databases
WHERE name = N'mkcoffee'
)

DROP DATABASE mkcoffee
GO
CREATE DATABASE mkcoffee
GO
-- check database 
SELECT name
from sys.databases
-- create table user 
USE mkcoffee
GO
IF OBJECT_ID('dbo.users', 'U') IS NOT NULL
  DROP TABLE dbo.users
GO
CREATE TABLE users
(
  ID int IDENTITY ,
  userId AS 'EM' + RIGHT('00000' + CAST(ID AS VARCHAR(10)), 5) PERSISTED,
  fullName varchar(255) null,
  email varchar(255) null,
  phoneNumber varchar(255) null,
  birthday date null,
  gender varchar(255) null,
  userName varchar(255) null,
  userPass varchar(255) null,
  CONSTRAINT mkcoffee_userId_pk PRIMARY KEY(userId)
)
SELECT *
FROM users
INSERT INTO users
  (fullName, email, phoneNumber, birthday, gender, userName, userPass )
VALUES
  ('Mai Van Khanh', 'maikhanh@gmail.com', '0905455798', '1989-05-19', 'male', 'maikhanh', '123');
INSERT INTO users
  (fullName, email, phoneNumber, birthday, gender, userName, userPass )
VALUES
  ('Tran Van Tuan', 'vantuan@gmail.com', '0905455888', '1988-05-20', 'male', 'vantuan', '123');
INSERT INTO users
  (fullName, email, phoneNumber, birthday, gender, userName, userPass )
VALUES
  ('Quach Tinh', 'quachtinh@gmail.com', '0903555666', '1986-05-13', 'male', 'quachtinh', '123');
UPDATE users SET phoneNumber = 12 WHERE userId = 'EM00001';
SELECT userId, fullName, email, phoneNumber, birthday, gender, userName, userPass
from users
where userName='maikhanh' and userPass='123';
UPDATE users SET fullName = 'Tran Van', email = 'abc@gmail.com', phoneNumber= '0905455678', birthday= '1989-05-19', gender = 'male', userName = 'vanhao', userPass= '123'  WHERE userId = 'EM00003';
SELECT users.userId, users.fullName, users.phoneNumber, users.email, ProductSales.dateOfTransaction, ProductSales.productName, ProductSales.kind, ProductSales.unit, ProductSales.price, ProductSales.quantity, ProductSales.totalPrice, MONTH(dateOfTransaction) AS monthSale
FROM users INNER JOIN ProductSales ON users.userId=ProductSales.employeeId
WHERE users.userId = 'EM00001' AND MONTH(dateOfTransaction) = 6
SELECT userId, fullName, email, phoneNumber, birthday, gender, userName, userPass
FROM users
WHERE fullName  LIKE '%a%'
-- working date
USE mkcoffee
GO
IF OBJECT_ID('dbo.Userworking', 'U') IS NOT NULL
  DROP TABLE dbo.Userworking
GO
CREATE TABLE Userworking
(
  userId varchar(30) not null,
  startOfDate DATETIME null,
  endOfDate DATETIME null,
)
SELECT *
FROM Userworking;
SELECT userId, startOfDate, endOfDate
FROM Userworking
WHERE MONTH(startOfDate) = 5;
SELECT Users.userId, Users.fullName, Userworking.startOfDate, Userworking.endOfDate
FROM Users INNER JOIN Userworking ON Users.userId=Userworking.userId;
-- check query table users
SELECT *
FROM users;
DELETE FROM users WHERE userId=2
-- Reset the identity column value. 
DBCC CHECKIDENT ('users', RESEED, 3)
-- delete constraint key
USE mkcoffee
GO
ALTER TABLE users
DROP CONSTRAINT mkcoffee_IdLogin_Fk;
-- create table register 
USE mkcoffee
GO
IF OBJECT_ID('dbo.register', 'U') IS NOT NULL
  DROP TABLE dbo.register
GO
CREATE TABLE register
(
  userId varchar(30) not null,
  userName varchar(30) null,
  userPass varchar(30) null
)
USE mkcoffee
GO
select name
from sys.tables
INSERT INTO register
  (userId, userName, userPass)
VALUES
  ('111', 'khanh', '123');
SELECT *
FROM register;
DELETE FROM register WHERE userName = 'khanh';
DBCC CHECKIDENT ('register', RESEED, 3)
select *
from register
where userName= 'khanh' and userPass= '123'
-- create table employeePromote
USE mkcoffee
GO
IF OBJECT_ID('dbo.employeePromote', 'U') IS NOT NULL
  DROP TABLE dbo.employeePromote
GO
CREATE TABLE employeePromote
(
  userId varchar(10) not null,
  userName varchar(30) null
)
SELECT *
FROM employeePromote
DELETE FROM employeePromote WHERE userId = 'EM00001'
-- create table Products 
USE mkcoffee
GO
IF OBJECT_ID('dbo.Products', 'U') IS NOT NULL
  DROP TABLE dbo.Products
GO
CREATE TABLE Products
(
  ID int IDENTITY,
  productID AS 'P' + RIGHT('00000' + CAST(ID AS VARCHAR(10)), 5) PERSISTED,
  productName varchar(50) null,
  productKind varchar(50) null,
  productUnit varchar(50) null,
  productPrice int null,
  productCategory varchar(50) null,
  productStock int null,
  CONSTRAINT mkcoffee_Products_pk PRIMARY KEY(productID)
)
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Coffee Epresso', 'small', 'pcs', 35000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Coffee Epresso', 'medium', 'pcs', 45000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Caramel Macchiato', 'small', 'pcs', 55000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Caramel Macchiato', 'medium', 'pcs', 65000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('White Coffee', 'small', 'pcs', 29000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('White Coffee', 'medium', 'pcs', 39000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Cappuccino Coffee', 'small', 'pcs', 45000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Cappuccino Coffee', 'medium', 'pcs', 55000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Black Milk Coffee', 'small', 'pcs', 29000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Black Milk Coffee', 'medium', 'pcs', 39000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Epresso Milk', 'small', 'pcs', 35000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Epresso Milk', 'medium', 'pcs', 45000, 'Epresso', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Black Tea', 'small', 'pcs', 28000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Black Tea', 'medium', 'pcs', 38000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Flavoured Tea', 'small', 'pcs', 25000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Flavoured Tea', 'medium', 'pcs', 35000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Raspberry Macchiato', 'small', 'pcs', 32000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Raspberry Macchiato', 'medium', 'pcs', 42000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Matcha Latte', 'small', 'pcs', 45000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Matcha Latte', 'medium', 'pcs', 55000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Macchiato Tea', 'small', 'pcs', 28000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Macchiato Tea', 'medium', 'pcs', 38000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Peach Tea Mania', 'small', 'pcs', 42000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Peach Tea Mania', 'medium', 'pcs', 55000, 'Special Tea', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Raspberry Soda', 'small', 'pcs', 35000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Raspberry Soda', 'medium', 'pcs', 45000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Berry Smoothie', 'small', 'pcs', 49000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Berry Smoothie', 'medium', 'pcs', 59000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Mango Smoothie', 'small', 'pcs', 49000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Mango Smoothie', 'medium', 'pcs', 59000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Green Apple', 'small', 'pcs', 35000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Green Apple', 'medium', 'pcs', 45000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Berry Soda', 'small', 'pcs', 35000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Berry Soda', 'medium', 'pcs', 45000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Mojito Lemon', 'small', 'pcs', 35000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Mojito Lemon', 'medium', 'pcs', 45000, 'Smoothies', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Mocha Ice Blended', 'small', 'pcs', 49000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Mocha Ice Blended', 'medium', 'pcs', 59000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Caramel Ice Blended', 'small', 'pcs', 49000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Caramel Ice Blended', 'medium', 'pcs', 59000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Cookie Ice Blend', 'small', 'pcs', 49000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Cookie Ice Blend', 'medium', 'pcs', 59000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Ice Chocotale Mocha', 'small', 'pcs', 49000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Ice Chocotale Mocha', 'medium', 'pcs', 59000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Double Chocolate Ice Blended', 'small', 'pcs', 49000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Double Chocolate Ice Blended', 'medium', 'pcs', 59000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Vanilla Ice Blended', 'small', 'pcs', 49000, 'Blended Coffee', 5);
INSERT INTO Products
  (productName, productKind, productUnit, productPrice, productCategory, productStock)
VALUES
  ('Vanilla Ice Blended', 'medium', 'pcs', 59000, 'Blended Coffee', 5);
SELECT *
FROM Products
SELECT productStock
FROM Products
WHERE productName = 'Coffee Epresso' AND productKind = 'small';
DELETE FROM Products WHERE productName='keo'
DELETE FROM Products WHERE productID=1
DBCC CHECKIDENT ('Products', RESEED, 48)
DBCC CHECKIDENT ('Products',NORESEED)
DBCC CHECKIDENT ('Products', RESEED, 3)
UPDATE Products SET productName = 'kemxoi', productPrice= 12, productCategory= 'loto', productStock= 2 WHERE productID = 4
SELECT productStock
FROM Products
WHERE productName = 'Vanilla Ice Blended' AND productKind = 'Small'
-- create table receipt product
USE mkcoffee
GO
IF OBJECT_ID('dbo.ReceiptProduct', 'U') IS NOT NULL
  DROP TABLE dbo.ReceiptProduct
GO
CREATE TABLE ReceiptProduct
(
  ID int IDENTITY,
  receiptProductID AS 'RP' + RIGHT('00000' + CAST(ID AS VARCHAR(10)), 5) PERSISTED,
  receiptEmployeeId varchar(50) null,
  receiptProductName varchar(50) null,
  receiptProductKind varchar(50) null,
  receiptProductUnit varchar(50) null,
  receiptProductPrice int null,
  receiptProductStock int null,
  receiptProductdateOfTransaction DATETIME null,
  CONSTRAINT mkcoffee_ReceiptProduct_pk PRIMARY KEY(receiptProductID)
)
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Coffee Epresso', 'small', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Coffee Epresso', 'medium', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Caramel Macchiato', 'small', 'pcs', 55000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Caramel Macchiato', 'medium', 'pcs', 65000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'White Coffee', 'small', 'pcs', 29000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'White Coffee', 'medium', 'pcs', 39000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Cappuccino Coffee', 'small', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Cappuccino Coffee', 'medium', 'pcs', 55000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Black Milk Coffee', 'small', 'pcs', 29000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Black Milk Coffee', 'medium', 'pcs', 39000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Epresso Milk', 'small', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Epresso Milk', 'medium', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Black Tea', 'small', 'pcs', 28000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Black Tea', 'medium', 'pcs', 38000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Flavoured Tea', 'small', 'pcs', 25000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Flavoured Tea', 'medium', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Raspberry Macchiato', 'small', 'pcs', 32000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Raspberry Macchiato', 'medium', 'pcs', 42000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Matcha Latte', 'small', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Matcha Latte', 'medium', 'pcs', 55000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Macchiato Tea', 'small', 'pcs', 28000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Macchiato Tea', 'medium', 'pcs', 38000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Peach Tea Mania', 'small', 'pcs', 42000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Peach Tea Mania', 'medium', 'pcs', 55000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Raspberry Soda', 'small', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Raspberry Soda', 'medium', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Berry Smoothie', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Berry Smoothie', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Mango Smoothie', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Mango Smoothie', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Green Apple', 'small', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Green Apple', 'medium', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Berry Soda', 'small', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Berry Soda', 'medium', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Mojito Lemon', 'small', 'pcs', 35000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Mojito Lemon', 'medium', 'pcs', 45000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Mocha Ice Blended', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Mocha Ice Blended', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Caramel Ice Blended', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Caramel Ice Blended', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Cookie Ice Blend', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Cookie Ice Blend', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Ice Chocotale Mocha', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Ice Chocotale Mocha', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Double Chocolate Ice Blended', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Double Chocolate Ice Blended', 'medium', 'pcs', 59000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Vanilla Ice Blended', 'small', 'pcs', 49000, 5, '2018-05-01');
INSERT INTO ReceiptProduct
  (receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction)
VALUES
  ('EM00001', 'Vanilla Ice Blended', 'medium', 'pcs', 59000, 5, '2018-05-01');
SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction
FROM ReceiptProduct
WHERE CAST (receiptProductdateOfTransaction AS DATE) = '2018-05-28';
SELECT *
FROM ReceiptProduct
SELECT Products.productName, Products.productKind, Products.productStock, ReceiptProduct.receiptProductName, ReceiptProduct.receiptProductID, ReceiptProduct.receiptProductStock
FROM Products INNER JOIN ReceiptProduct ON Products.productName = ReceiptProduct.receiptProductName
SELECT ReceiptProduct.receiptProductName, ReceiptProduct.receiptProductKind, ReceiptProduct.receiptProductStock
FROM ReceiptProduct INNER JOIN Products ON Products.productName = ReceiptProduct.receiptProductName
SELECT receiptProductID, receiptEmployeeId, receiptProductName, receiptProductKind, receiptProductUnit, receiptProductPrice, receiptProductStock, receiptProductdateOfTransaction
FROM ReceiptProduct
WHERE receiptProductdateOfTransaction >= CONVERT(datetime,'2018-04-29') and receiptProductdateOfTransaction < CONVERT(datetime,'2018-05-11') +1
-- create table ProductSales 
USE mkcoffee
GO
IF OBJECT_ID('dbo.ProductSales', 'U') IS NOT NULL
  DROP TABLE dbo.ProductSales
GO
CREATE TABLE ProductSales
(
  ID int IDENTITY,
  transactionId AS 'PS' + RIGHT('00000' + CAST(ID AS VARCHAR(10)), 5) PERSISTED,
  orderId varchar(30) null,
  employeeId varchar(30) null,
  customerId varchar(30) null,
  productId varchar(30) null,
  dateOfTransaction DATETIME null,
  productName varchar(50) null,
  kind varchar(50) null,
  unit varchar(50) null,
  price int null,
  quantity int null,
  totalPrice int null,
  CONSTRAINT mkcoffee_ProductSales_pk PRIMARY KEY(transactionId)
)
select *
from ProductSales;
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00001', 'EM00001', 'C00001', 'P00035', '2018-03-05', 'Mojito Lemon', 'small', 'pcs', 60000, 2, 120000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00001', 'EM00001', 'C00001', 'P00035', '2018-03-05', 'Mojito Lemon', 'small', 'pcs', 60000, 2, 120000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00001', 'EM00001', 'C00001', 'P00035', '2018-03-05', 'Mojito Lemon', 'small', 'pcs', 60000, 2, 120000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00002', 'EM00002', 'C00002', 'P00009', '2018-04-05', 'Black Milk Coffee', 'small', 'pcs', 29000, 2, 58000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00002', 'EM00002', 'C00002', 'P00009', '2018-04-05', 'Black Milk Coffee', 'small', 'pcs', 29000, 2, 58000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00002', 'EM00002', 'C00002', 'P00009', '2018-04-05', 'Black Milk Coffee', 'small', 'pcs', 29000, 2, 58000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00003', 'EM00003', 'C00003', 'P00007', '2018-05-05', 'Cappuccino Coffee', 'small', 'pcs', 45000, 2, 90000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00003', 'EM00003', 'C00003', 'P00007', '2018-05-05', 'Cappuccino Coffee', 'small', 'pcs', 45000, 2, 90000);
INSERT INTO ProductSales
  (orderId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice)
VALUES
  ('OD00003', 'EM00003', 'C00003', 'P00007', '2018-05-05', 'Cappuccino Coffee', 'small', 'pcs', 45000, 2, 90000);
DELETE FROM ProductSales WHERE transactionId='PS00009'
DBCC CHECKIDENT ('ProductSales', RESEED, 1)
SELECT SUM(totalPrice)
FROM ProductSales
WHERE customerId = 202;
DELETE FROM ProductSales WHERE ID = 7
SELECT transactionId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice, MONTH(dateOfTransaction)
FROM ProductSales;
SELECT Customers.customerid, Customers.customerName, Customers.customerAdd, Customers.customerPhone, Customers.customerEmail, ProductSales.dateOfTransaction, ProductSales.productName, ProductSales.kind, ProductSales.unit, ProductSales.price, ProductSales.quantity, ProductSales.totalPrice, MONTH(dateOfTransaction) AS monthBuy
FROM Customers INNER JOIN ProductSales ON Customers.customerid=ProductSales.customerId
WHERE Customers.customerid = 'C00002' AND MONTH(dateOfTransaction) = 6
SELECT CONVERT(DATE, dateOfTransaction)
FROM ProductSales
select month(dateOfTransaction)
FROM ProductSales
SELECT SUM(ProductSales.totalPrice)
FROM Customers INNER JOIN ProductSales ON Customers.customerid=ProductSales.customerId
WHERE Customers.customerid = 'C00002'
SELECT transactionId, employeeId, customerId, productId, dateOfTransaction, productName, kind, unit, price, quantity, totalPrice
FROM ProductSales
WHERE YEAR(dateOfTransaction) = 2018;
-- issue table from productSales
SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction
FROM ProductSales
WHERE CAST (dateOfTransaction AS DATE) = '2018-06-06';
SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction
FROM ProductSales
WHERE CONVERT(date,dateOfTransaction) BETWEEN '2018-06-07' AND '2018-06-04';
SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction
FROM ProductSales
WHERE CAST(dateOfTransaction AS DATE) BETWEEN '2018-06-07' AND '2018-06-04';
SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction
FROM ProductSales
WHERE dateOfTransaction >= CONVERT(datetime,'2018-06-04') and dateOfTransaction < CONVERT(datetime,'2018-06-07') +1
SELECT transactionId, employeeId, productName, kind, unit, price, quantity, dateOfTransaction
FROM ProductSales
WHERE quantity BETWEEN 2 AND 4;
-- Create table Order List
USE mkcoffee
GO
IF OBJECT_ID('dbo.OrderList', 'U') IS NOT NULL
  DROP TABLE dbo.OrderList
GO
CREATE TABLE OrderList
(
  ID int IDENTITY,
  orderId AS 'OD' + RIGHT('00000' + CAST(ID AS VARCHAR(10)), 5) PERSISTED,
  employeeId varchar(50) null,
  customerId varchar(50) null,
  timeOrder DATETIME null,
  totalInvoice int null,
  CONSTRAINT mkcoffee_OrderList_pk PRIMARY KEY(orderId)
)
INSERT INTO OrderList
  (employeeId, customerId, timeOrder, totalInvoice)
VALUES
  ('EM00001', 'C00001', '2018-03-05', 360000);
INSERT INTO OrderList
  (employeeId, customerId, timeOrder, totalInvoice)
VALUES
  ('EM00002', 'C00002', '2018-04-05', 174000);
INSERT INTO OrderList
  (employeeId, customerId, timeOrder, totalInvoice)
VALUES
  ('EM00003', 'C00003', '2018-05-05', 270000);
SELECT OrderList.orderId
FROM OrderList
WHERE timeOrder = '2018-03-05 00:00:00.000';
SELECT OrderList.orderId
FROM OrderList
WHERE timeOrder = CONVERT(date,'2018-03-05') AND timeOrder = CONVERT(time,'00:00:00')
SELECT OrderList.orderId
FROM OrderList
WHERE timeOrder = CONVERT(time,'00:00:00')
SELECT OrderList.orderId
FROM OrderList
WHERE CAST (timeOrder AS DATE) = '2018-03-05' AND CAST (timeOrder AS TIME) = '00:00:00'
SELECT *
FROM OrderList
SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice, Users.fullName, Customers.customerName
FROM OrderList INNER JOIN users ON OrderList.employeeId = Users.userId INNER JOIN Customers ON OrderList.customerId = Customers.customerId
SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice
FROM OrderList
WHERE CAST (timeOrder AS DATE) = '2018-06-07';
DELETE FROM OrderList WHERE ID = 8
-- create table Customer
USE mkcoffee
GO
IF OBJECT_ID('dbo.Customers', 'U') IS NOT NULL
  DROP TABLE dbo.Customers
GO
CREATE TABLE Customers
(
  ID int IDENTITY,
  customerId AS 'C' + RIGHT('00000' + CAST(ID AS VARCHAR(10)), 5) PERSISTED,
  customerName varchar(50) null,
  customerAdd varchar(100) null,
  customerPhone varchar(100) null,
  customerEmail varchar(50) null,
  CONSTRAINT mkcoffee_Customers_pk PRIMARY KEY(customerId)
)
INSERT INTO Customers
  (customerName, customerAdd, customerPhone, customerEmail)
VALUES
  ('Khach Le', 'No Add', 00000, 'noname@gmail')
INSERT INTO Customers
  (customerName, customerAdd, customerPhone, customerEmail)
VALUES
  ('Ung Hoang Phuc', '33 Ngu Hanh Son', '0905111222', 'hoangphuc@gmail.com')
INSERT INTO Customers
  (customerName, customerAdd, customerPhone, customerEmail)
VALUES
  ('Do Van Teo', '22 Yen bai', '0905888999', 'vanteo@gmail.com')
INSERT INTO Customers
  (customerName, customerAdd, customerPhone, customerEmail)
VALUES
  ('Truong Sa', '24 Nguyen Chi Thanh', '0905333444', 'truongsa@gmail.com')
INSERT INTO Customers
  (customerName, customerAdd, customerPhone, customerEmail)
VALUES
  ('Hoang Sa', '30 Hung Vuong', '0905666777', 'hoangsa@gmail.com')
SELECT *
FROM Customers;
DELETE FROM Customers WHERE ID=1
DBCC CHECKIDENT ('Customers', RESEED, 2)
SELECT OrderList.orderId, OrderList.employeeId, OrderList.customerId, OrderList.timeOrder, OrderList.totalInvoice, Users.fullName, Customers.customerName
FROM OrderList INNER JOIN users ON OrderList.employeeId = Users.userId INNER JOIN Customers ON OrderList.customerId = Customers.customerId
WHERE timeOrder >= CONVERT(date, '2018-06-07') and timeOrder < CONVERT(date, DATEADD(day, 1, '2018-06-10')) +1



