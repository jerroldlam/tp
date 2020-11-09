# DevOps Guide for PlanNUS

<!-- @@author Khenus -->

<table><tr><td><div style="text-align:center">
    <img src="images/PlanNUSLogo.png" />
</div></td></tr></table>

## Table of contents

* Table of contents
{:toc}

## Build Automation

PlanNUS uses `Gradle` for build automation and dependency management. 

Below shows how `Gradle` is being used in PlanNUS for important project tasks

* **`test`**: Runs all available test
  * Command: `./gradlew clean test`: Cleans the project before running the test
  * Command: `./gradlew test`: Runs all the test
* **`shadowJar`**: ShadowJar plugin is responsible for packaging PlanNUS into a *fat* JAR file, which is located under `build/lib` folder. If applicable, ShadowJar will replace previous versions of `PlanNUS.jar` with the latest version.
  * Command: `./gradlew shadowJar`

* **`clean`**: Deletes the files created during the previous build (i.e. files and folders that are in the `build` folder)
  * Command: `./gradlew clean`
* **`checkstyle`**: Checks for styling compliance across the whole project
  * Command: `./gradlew checkstyleMain`: Checks for styling compliance in the `main` package of PlanNUS
  * Command: `./gradlew checkstyleTest`: Checks for styling compliance in the `test` package of PlanNUS
* **`build`**: Runs all available `tests` and `checkstyle` before packaging PlanNUS with `ShadowJar` if project passed all test cases and style check without issue.
  * Command: `./gradlew build`

## Continuous Integration (CI)

PlanNUS uses GitHub Actions for continuous integration. The necessary GitHub Actions configuration files exist as `.github/workflows/gradle.yml`. This file does not require further set up.

## I/O Testing

PlanNUS uses automated scripts located in the `./text-ui-test` folder to test for the correct output given a sequence of inputs. 

To test for the input/output of PlanNUS

1. Change needed input in `input.txt` located in `./text-ui-test` folder
2. Change the expected output in `EXPECTED.TXT` located in `./text-ui-test` folder
3. Run the scripts for testing
   * On windows, run `runtest.bat`
   * On UNIX systems, run `runtest.sh`
4. The scripts will run and throws a warning message if the actual output differs from the expected output
5. The actual output will be stored in `ACTUAL.TXT` located in `./text-ui-test` folder

## Making a release

Here are the steps to create a new release.

1. Generate a fat JAR file using Gradle with the command`gradlew build`.
2. Tag the repo with the version number. e.g. `V2.0`
3. [Create a new release using GitHub](https://help.github.com/articles/creating-releases/). Upload the JAR file you created.

## Useful Links

* [**About Us**](https://ay2021s1-cs2113t-f12-1.github.io/tp/AboutUs.html)
* [**Configuration guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/ConfigurationGuide.html)
* [**Developer guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/DeveloperGuide.html)
* [**Documentation guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/DocumentationGuide.html)
* [**Logging guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/LoggingGuide.html)
* [**Testing guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/TestingGuide.html)
* [**User guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/UserGuide.html)

