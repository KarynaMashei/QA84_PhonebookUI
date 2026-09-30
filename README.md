# PhoneBook UI Test Automation

Training project developed during the QA Automation Engineer program at AIT Technology School. It demonstrates cross-browser UI test automation for the [PhoneBook](https://telranedu.web.app/home) web application with Java, Selenium WebDriver, TestNG and a helper-based framework organized around `ApplicationManager` and feature helpers.

## Covered scenarios

- user registration
- positive and negative login checks
- adding contacts with CSV-driven test data
- removing contacts
- home-page checks
- quick and regression TestNG suites
- screenshots and logging on test failure

## Technology stack

- Java 21
- Selenium WebDriver 4
- TestNG
- Gradle
- WebDriverManager
- SLF4J and Logback
- helper-based test framework (`ApplicationManager` with user, contact and home-page helpers)

## Project structure

- `src/main/java/de/phonebook/core` — browser lifecycle and shared actions
- `src/main/java/de/phonebook/fw` — feature helpers for users, contacts and home-page interactions
- `src/main/java/de/phonebook/model` — test-data models
- `src/test/java/de/phonebook/tests` — TestNG test classes
- `src/test/resources` — suites, CSV data and logging configuration

## Run the tests

Prerequisites: JDK 21 and at least one supported browser installed locally.

Run the complete suite in Chrome:

```bash
./gradlew test
```

Run the quick suite in Firefox:

```bash
./gradlew quick -Pbrowser=firefox
```

Run the regression suite in Edge:

```bash
./gradlew regr -Pbrowser=edge
```

Supported browser values: `chrome`, `firefox`, `edge`.

## Notes

This is a training project, not a production or client application. Screenshots and logs are generated locally and excluded from version control.
