# Ju Zihao - Project Portfolio Page

## Overview

PlanNUS is a greenfield, CLI-based project which aims to solve the gap in undergraduate academic planning in NUS.
With the Academic Calendar Planner, PlanNUS will be able to assist undergraduate students in forecasting their academic journey for their 4 or 5 years in NUS.
With the CAP Calculator, PlanNUS will be able to tell you your current CAP, forecast future grades needed to achieve your target CAP and even provide suggestions on S/U modules to achieve the best CAP possible.
With PlanNus, we aim to provide an one-stop solution to some of the most common problems that NUS students will face in their academic curriculum.

### Summary of Contributions

* [Code Contributed](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=Zihao&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=JuZihao&tabRepo=AY2021S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
* **New Feature:** Added the ability to view the user's current CAP, graded MCs and total MCs.
   * What it does: Allows the user to display his or her current CAP, graded MCs and total MCs after adding some modules inside Academic Planner.
   * Justification: This feature is the basic feature for CAP Calculator and it provides the details user needed for the rest of the other features.
   * Highlights: The implementation of this feature was initally set to have a time complexity of O(n) where n is the number of moudules that the user has added. The time complexity is then improved to be O(1) after some updates.
   * Credits: Improvements are made due to Khenus's `CalculatorUtils`.
   
* **New Feature:** Added the ability to set a target CAP given some graded MCs.
   * What it does: Informs the user what his or her CAP have to be for a given graded MCs in order to achieve his or her target CAP.
   * Justification: This feature is allows the user to plan for upcoming semester or even for the rest of his or her university curriculum.
   * Highlights: This enhancement takes CAP and MC information that is provided by the user in Acadamic Planner. Thus, it is important that this enhancement is extracting the correct information for its purpose to avoid a wrongly displayed result. 
   
* **New Feature:** Added the ability to provide suggestion on S/U to users.
   * What it does: Provide details on what modules to S/U in order to achieve the highest CAP possible.
   * Justification: This feature is allows the user to plan for S/U modules after result release.
   * Highlights: This enhancement is affected by the module list in the `Person` object. Thus, it is cruicial to filter out modules that cannot be S/Ued to provide user with the correct information and suggestion that allows them to achieve the best CAP.
   
* **Documentation**
    * User Guide
      * Added documentation for features `set target` and `set su`. [#16](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/16)
    * Developer Guide
      * Added documentation for `set su` commands and its flow. [#89](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/89)
      * Added documentation for `CurrentCommand` and its flow. [#296](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/296)
      * Added documentation for Glossary. [#177](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/171)
      * Added documentation for Configuration Guide.
      * Added use cases for  CAP Calculator commands. [#273](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/273)

      
* **Team-Based Tasks**
  * Made bug fixes to the code and bugs to be resolved.
    * [#25](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/25), [#38](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/38), [#71](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/71), [#107](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/107), [#162](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/162), [#184](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/184), [#185](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/185), [#194](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/194), [#195](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/195), [#202](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/202), [#203](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/203), [#216](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/216) , [#217](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/217)
   * Improve code quality and standards. [#45](https://github.com/AY2021S1-CS2113T-F12-1/tp/issues/45)
