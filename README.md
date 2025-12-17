🚀 Test Automation Framework (TAF)
Selenium WebDriver • Java • TestNG
📌 Overview

This repository contains a Test Automation Framework (TAF) built with Java and Selenium WebDriver.
The project demonstrates a scalable, maintainable, and CI-ready automation solution, designed according to industry best practices.

The framework follows a 3-layer architecture and applies multiple design patterns, making it suitable for real-world automation projects.

🏗️ Architecture

The framework is divided into three logical layers:

🔹 Core Layer

Framework infrastructure and technical components:

WebDriver initialization & lifecycle management

Configuration handling

TestNG listeners

Screenshot capturing on test failure

WebElement highlighting (Decorator pattern)

Wait strategies and time utilities

Base test class

Logging setup (SLF4J)

🔹 Business Layer

Application abstraction and business logic:

Page Object Model (POM)

Page Factory implementation

Business flows (e.g. authentication)

Navigation actions

Test data models

🔹 Test Layer

Test execution and scenarios:

TestNG test classes

Smoke test suites

Parallel execution support

🧩 Design Patterns

Page Object Model (POM)

Page Factory

Decorator Pattern (WebElement highlighter)

Strategy Pattern (waits & execution behavior)

Factory Pattern (WebDriver creation)

Singleton Pattern (driver & configuration)

⚙️ Key Features

✔ Selenium WebDriver with Java
✔ TestNG framework
✔ Parallel test execution
✔ Explicit & implicit waits
✔ Multiple locator strategies
✔ Screenshots on test failure
✔ SLF4J logging
✔ Logs stored as build artifacts
✔ ReportPortal integration
✔ CI/CD ready (Jenkins)

▶️ Running Tests

Run all tests:

mvn clean test


Run smoke tests:

mvn clean test -DsuiteXmlFile=testng-smoke.xml


Parallel execution is configured via TestNG.

📊 Reporting & Logs

SLF4J for logging

Logs saved in the logs directory

Screenshots automatically captured on failures

Integration with ReportPortal for real-time reporting

🚀 CI/CD Ready

The project is prepared for CI pipelines:

Maven-based structure

Jenkins-friendly configuration

Parallel execution support

Test artifacts and logs available after execution

🎯 Purpose

This project was created to:

Demonstrate advanced Selenium & Java automation skills

Showcase Test Automation Framework design

Apply common design patterns in practice

Present a CI-ready, real-world solution
