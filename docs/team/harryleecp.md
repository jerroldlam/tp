# Harry Lee - Project Portfolio Page

## Overview

PlanNUS is a desktop application used for planning academic journey in NUS. This provides an offline platform for undergraduates to keep track of their grades and modules taken while also able to find out more information about other modules when drafting their schedules for upcoming semesters. The user interacts with PlanNUS using a CLI and the program is written in Java.

Given below are my contributions to the project.

* __New Feature:__ Added the ability to edit the module details within user's modules list.
  * What it does: Allows the user to change either the semester at which the selected module is taken, or its grade value.
  * Justification: This feature is essential for the user to make amendments to his/her list of modules as the semester and grade may not be made known beforehand.
  * Highlights: This feature incorporates functionalities of multiple classes to validate user's input for the values of semester and grade. Moreover, it is necessary to check that the module selected exists within the user's list before changing the details.
* __New Feature:__ Added the ability to remove a module within user's modules list.
  * What it does: Allows the user to discard unwanted module(s) within the list.
  * Justification: Assuming that the user added wrong module(s) on accident or failed to obtain specific module(s) due to lack of slots, these can be deleted using this feature.
  * Highlights: Similar to the previous feature, it is necessary to check that the module to be deleted exists within the user's list such that errors will not occur. 
* __New Functionality:__ Created the storage class to save user's selections.
  * What it does: Allows the user to save their list of modules into a text file such that he/she does not have to add in the configurations all over again. 
  * Justification: As this application is meant to provide ease of access for the user's personal academic record, there should be persistent storage for keeping data to be loaded whenever PlanNUS is launched in the CLI. Apart from that user can also access the text file without the need for using the application.
  * Highlights: User's list of modules interacts with the sorting class to arrange the modules according to semester before writing to a text file with a simpler format. This increases the efficiency in both loading and saving of the persistent data as it would be easier to read and write.

* __Code contributed:__ [**Reposense link**](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=harryleecp&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=harryleecp&tabRepo=AY2021S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)
* __Documentation:__ 
  * User Guide:
    * Added documentation for the features `edit` and `remove` 
    * Set up the basic structure of the guide [**#57**](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/57)
  * Developer Guide:
    * Added implementation info and sequence diagrams for `edit` and `remove` command [**#135**](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/135)
    * Added the documentation guide for the project [**#164**](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/164)