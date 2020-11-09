# Testing Guide for PlanNUS

<!-- @@author jerroldlam -->

<table><tr><td><div style="text-align:center">
    <img src="images/PlanNUSLogo.png" />
</div></td></tr></table>

## Table of content

* Table of contents
{:toc}

## Running Tests

There are 2 ways to run tests on the code base of PlanNUS.

* **Method 1:** Using IntelliJ JUnit test runner
    * To run all tests, right-click on the `src/test/java` folder and choose `Run All Tests`
    * To run a subset of tests, you can right-click on a test package, test class or a single test and choose `Run <TEST NAME>`
* **Method 2:** Using Gradle
    * Open a console and run the command 
        * `gradlew clean test` for Windows
        * `./gradlew clean test` for Mac and Linux
    * For more information about using Gradle, take a look at [this tutorial](https://se-education.org/guides/tutorials/gradle.html "Gradle Tutorial"). 
    
## Types of test

This project contains three types of tests:

* _Unit tests_ target the lowest level of methods or classes
    * e.g. `src.test.java.seedu.duke.apps.academicplanner.commons.AddUtilsTest`
* _Integration tests_ that are check the integration of multiple code units
    * e.g. `src.test.java.seedu.duke.apps.academicplanner.AcademicPlannerParserTest`
* Hybrids of unit and integration tests that check multiple code units as well as how they are connected together



## Useful Links

* [**About Us**](https://ay2021s1-cs2113t-f12-1.github.io/tp/AboutUs.html)
* [**Configuration guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/ConfigurationGuide.html)
* [**Developer guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/DeveloperGuide.html)
* [**DevOps guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/DevOpsGuide.html)
* [**Documentation guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/DocumentationGuide.html)
* [**Logging guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/LoggingGuide.html)
* [**User guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/UserGuide.html)