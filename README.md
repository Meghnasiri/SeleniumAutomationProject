# Java Selenium BDD Framework for E-commerce Flows
## 🚀 Project Overview
This project is a scalable UI automation framework built using **Selenium WebDriver**, **Java**, **Cucumber BDD**, and **TestNG**.
It automates real-world e-commerce user journeys such as login, product selection, cart, checkout, and order confirmation.
And it is a robust, maintainable, and scalable web automation framework.It uses the **Page Object Model (POM)** for clean code organization, supports data-driven testing with JSON, and is ready for CI/CD integration with webhook triggers.

## 🧪 Business Flows Covered
- User Login
- Product Selection
- Add to Cart
- Checkout Flow
- Order Confirmation
- Negative Scenarios

---
## Features
- **Page Object Model (POM)** for maintainable and reusable code
- **Cucumber BDD** for business-readable, Gherkin-based test scenarios
- **TestNG** for test orchestration and parallel execution
- **Data-driven testing** using JSON files
- **Rich HTML reporting** with ExtentReports
- **CI/CD ready**: Easily integrates with Jenkins, GitHub Actions, GitLab CI, etc.
- **Automatic WebDriver management** via WebDriverManager

---

## Project Structure
```
SeleniumProject/
│
├── Seleniumframework/
│   ├── pom.xml
│   ├── reports/                # HTML and screenshot reports
│   ├── src/
│   │   ├── main/java/Practice/
│   │   │   ├── Abstractcomponents/   # Base and utility classes
│   │   │   └── Pageobjectclasses/    # Page Object Model classes
│   │   └── test/java/Practice/
│   │       ├── Seleniumframework/    # TestNG test classes
│   │       ├── Cucumber/             # Cucumber step defs, runner, features
│   │       ├── stepDefination/       # Step definition implementations
│   │       └── TestData/             # JSON test data files
│   └── target/               # Compiled classes and test reports
│
└── test-output/              # TestNG output reports
```

---

## Key Components
- **Pageobjectclasses/**: Page Object Model classes for each web page (Login, Registration, Product, Cart, Checkout, Confirmation, Order).
- **Abstractcomponents/**: Common Selenium actions and navigation utilities.
- **Cucumber/**: 
  - `.feature` files (Gherkin scenarios)
  - `TestNGTestRunner.java` (Cucumber runner)
- **TestData/**: JSON files for registration and purchase data.
- **Seleniumframework/**: TestNG-based test classes for end-to-end and regression tests.

---

## Cucumber BDD Usage
- **Feature files**: Located in `src/test/java/Practice/Cucumber/` (e.g., `SubmitOrder.feature`, `ErrorValidation.feature`)
- **Step definitions**: Implemented in `Practice/stepDefination/`
- **Runner**: `TestNGTestRunner.java` uses `@CucumberOptions` to link features and step definitions

### Example Feature File
```gherkin
Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | name                   | password      | productName   |
      | ##########@gmail.com   | ############  | ZARA COAT 3   |
```

### Running Cucumber Tests
You can run Cucumber tests via Maven:
```sh
mvn test -Dcucumber.options="--tags @Regression"
```
Or by running the `TestNGTestRunner` class from your IDE.

---

## Data-Driven Testing
- All registration and purchase data is managed in `TestData/RegistrationData.json`.
- Update or add new user/product data in this file for dynamic test coverage.

---

## Reporting
- After test execution, open `reports/index.html` for a detailed HTML report with pass/fail status and screenshots.

---

## CI/CD Integration
This project is **CI/CD ready** and can be integrated with any modern pipeline tool (Jenkins, GitHub Actions, GitLab CI, Azure DevOps, etc.).

### Example CI/CD Steps
1. **Clone the repository** (triggered by webhook on push/PR)
2. **Install dependencies**:
    ```sh
    mvn clean install
    ```
3. **Run tests**:
    ```sh
    mvn test
    ```
4. **Publish reports**:
    - Archive `reports/index.html` and screenshots as build artifacts
    - Optionally, send notifications on failure/success

### Webhook Triggers
- Configure your Git hosting (GitHub, GitLab, Bitbucket, etc.) to trigger your CI/CD pipeline on push, PR, or schedule.
- In Jenkins, use the **GitHub plugin** or **Generic Webhook Trigger**.
- In GitHub Actions, use the `on: [push, pull_request]` trigger in your workflow YAML.

### Where to Add Your Pipeline
- For Jenkins: add a `Jenkinsfile` at the project root.
- For GitHub Actions: add a workflow YAML in `.github/workflows/`.
- For GitLab: add `.gitlab-ci.yml` at the project root.

---

## How to Extend
- **Add new features:** Create new `.feature` files in the Cucumber directory.
- **Add new step definitions:** Implement new steps in the `stepDefination` package.
- **Add new page objects:** Create new classes in `Pageobjectclasses/`.
- **Add new test data:** Update the JSON files in `TestData/`.

---

## 📊 Test Report

![Report](assets/screenshots/report.png)


## Contributors
- **Meghana** (Lead Tester & Developer)

---

## License
This project is for educational and demonstration purposes. For commercial use, please add an appropriate license.

---

**Happy Testing & Automating!**

---

*Last updated: March 30, 2026*

