# 🛒 E-Commerce Automation Testing Framework

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=flat&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=flat)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white)
![ExtentReports](https://img.shields.io/badge/ExtentReports-0A1AFF?style=flat)

---

## 📌 Project Overview

This repository contains an **E-Commerce Automation Testing Framework** developed using **Java, Selenium WebDriver, and TestNG**.  
The framework automates key user journeys such as **login, product search, add to cart, checkout, and logout**, with detailed **Extent Reports**, failure screenshots, and **CI/CD execution using Jenkins**.

This project is designed to demonstrate **real-world automation testing practices** and is suitable for **resume, interviews, and portfolio showcasing**.

---

## 🎯 Objectives

- Automate core E-commerce workflows
- Improve regression test coverage and execution efficiency
- Generate detailed HTML execution reports
- Enable CI/CD execution through Jenkins
- Follow scalable and maintainable automation design

---

## 🧰 Technology Stack

- **Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Reporting:** Extent Reports (HTML)  
- **CI/CD:** Jenkins  
- **Version Control:** Git & GitHub  

---Ecommerce-Automation-Testing/
│
├── src/
│ ├── main/
│ └── test/
│ └── java/
│ └── testcases/
│ ├── AddMultipleItemsTest.java
│ ├── AddToCartTest.java
│ ├── CheckoutTest.java
│ ├── LoginTest.java
│ ├── LogoutTest.java
│ └── SearchTest.java
│
├── test-output/
│ ├── ExtentReport_Checkout.html
│ ├── ExtentReport_Login.html
│ ├── ExtentReport_Logout.html
│ ├── ExtentReport_Search.html
│ └── testng-results.xml
│
├── screenshots/
│ └── (failure screenshots)
│
├── pom.xml
├── testng.xml
├── Jenkinsfile.groovy
├── .gitignore
└── README.md

---

## ✅ Test Scenarios Covered

- User Login (Valid & Invalid)
- Product Search
- Add Single & Multiple Items to Cart
- Cart Validation
- Checkout Flow
- Logout Functionality
- Regression Test Execution

---

## 📊 Reporting – Extent Reports

- Separate **HTML Extent Reports** generated for:
  - Login
  - Search
  - Add to Cart
  - Checkout
  - Logout
- Screenshots automatically attached on test failures
- Clear pass/fail status with step-level logs

📁 Reports Location:

---

## 🧪 Test Execution

### ▶️ Run via Maven
##mvn test
▶️ Run via TestNG

Execute using testng.xml

▶️ Run via Jenkins

Jenkins pipeline configured using Jenkinsfile.groovy

Automated execution with report generation

Suitable for CI/CD and regression runs

🔄 CI/CD Integration (Jenkins)

Jenkins pipeline triggers automation execution

Maven-based build and test execution

Extent Reports generated as build artifacts

Supports manual and scheduled runs

🧠 Key Highlights

Modular and scalable automation framework

Industry-standard reporting with Extent Reports

Screenshot capture on failures

CI/CD ready using Jenkins

Resume and interview friendly project

⚠️ Disclaimer

This is a practice automation project created for learning and portfolio purposes only.
No real payment gateways or production credentials are used.

👤 Author

Darshan Zore
QA / Automation Test Engineer

## 🗂️ Project Structure


