# Jerrold Lam - Project Portfolio Page

## Overview

PlanNUS is a greenfield, CLI-based project which aims to solve the gap in undergraduate academic planning in NUS.
It aims to assist students in NUS by providing them an avenue to easily plan their academic calendar, both for the past and the future. The CAP calculator is by far the most convenient implementation as it leverages on existing data in the academic planner to obtain grade statistics, allowing for hassle-free and worry-free calculations.

### Summary of Contributions

* Code contributed: [Reposense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jerroldlam "Link to contributed code")
* **New Feature:** Added the ability to add modules into their unique academic planner.
    * What it does: Allows the user to add a module which is offered by NUS into their academic calendar, one at a time.
    * Justification: This feature is one of the core features of PlanNUS and forms the basis of which PlanNUS is built upon.
    * Highlights: The implementation of this feature needed to be foolproof and complex to implement as it required multiple interactions with multiple classes in order to validate and properly store the required variables.
* **New Feature:** Added the ability to view modules from a specific semester of the user's academic calendar.
    * What it does: Allows the user to view all modules, and their grades from a specific semester that they input.
    * Justification: This feature improves PlanNUS significantly as the user need not be overloaded with the information of their whole academic calendar should they only want to view a semester's information.
    * Highlights: This enhancement affects the existing view command and needed to be neatly and coherently integrated with the current view command. This required in depth analysis of the possible integration solutions to make the more intuitive program flow for the ease of use for the user.
* **New Functionality:** Created the main object classes to be used in PlanNUS.
    * What it does: Through the `Person` and `PartialModule` object classes, PlanNUS is able to store required variables efficiently to be utilised throughout the program.
    * Justification: By encapsulating the variables and creating a facade for the private variables, it reduces the risk of a rogue function modifying the variables. This would make our program more robust and reduces the opportunity for bugs to appear.
    * Highlights: The implementation of these object classes need to be thought of at a high level as it forms the foundation of the functionality of PlanNUS. This meant that uses cases needed to be thought out to account for the various usage type. These classes also need to be easily modifiable as the classes can be easily augmented to encapsulate more data should the program require.
* **New Functionality:** Created the parser class to be used in the academic planner app.
    * What it does: Processes user input data and returns the correct command to be executed.
    * Justification: Raises the coding standard of the PlanNUS code base as more object-oriented-programming can be implemented.
    * Highlights: The parser needs to be able to handle all sorts of output and handle them gracefully without fail. Hence, much thought was put into designing the parser to account for the various possible different inputs.
    * Credits: Referenced from teammate Khenus's app parser.
* **New Functionality:** Added `ModuleValidator` class to easily validate inputs from user.
    * What it does: Easily validates critical information such as Module Codes, Semester indexes and Grade values.
    * Justification: This enhances the credibility of PlanNUS by rejecting random inputs, maintaining the data integrity of the information that will be stored in PlanNUS.
    * Highlights: As there are many parameters that need to be verified, careful implementation of the verification methods is conducted. This forms the core of PlanNUS' data integrity checks, as a mistake here would result in unintended values to be accepted as valid.
* **New Functionality:** Added `LoggingTool` class to easily initialise a `Logger` for use within PlanNUS.
    * What it does: Easy initialisation of `Logger` to assist in logging the runtime execution of PlanNUS.
    * Justification: Logging will be widely used in PlanNUS to assist in tracking its events, hence by creating this class, developers can easily initialise a standardised logger which will output `.log` files for the developer to understand what is going on in the program. 
    * Highlights: Following careful consideration of how logging will be used, the parameters are set up to allow different codes to easily log without requiring a huge chunk of code to initialise it.
* **New Functionality:** Support retaking of modules.
    * [#253](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/253)
* **Documentation**
    * User Guide
        * Added documentation for `AddModuleCommand` 
            * [#37](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/37)
        * Added Overview and introduction to user guide 
            * [#137](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/152)
        * Wrote appendix for relevant information regarding NUS modules and semester indexes 
            * [#57](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/57)
    * Developer Guide
        * Added documentation for `AddModuleCommand` and its flow in the program 
            * [#114](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/114)
        * Added details of the `Ui` class 
            * [#172](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/172), [#175](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/175)
        * Added Logging guide 
            * [#130](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/130)
* **Team-Based Tasks**
    * Set up Team Organisation and Team Repository
    * Maintained issue tracker on the team's repository
    * Made necessary code quality enhancements 
        * [#36](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/59) ,[#65](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/65), [#100](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/100), [#101](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/101), [#262](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/262), [#275](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/275)
    * Resolved coding standard violations
    * Made numerous bug fixes for the code and found bugs to be resolved
        * [#43](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/43), [#59](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/59), [#72](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/72), [#103](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/103), [#111](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/111), [#115](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/115), [#127](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/127), [#147](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/147), \
        [#170](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/170), [#220](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/220), [#222](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/222), [#255](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/255), [#314](https://github.com/AY2021S1-CS2113T-F12-1/tp/pull/314)
* **Contributions to the User Guide (Extracts without pictures)**
    * Semester Naming Conventions
        * For the purpose of this planning software, we have decided to limit the maximum number of semesters an undergraduate can take to 10, which is equivalent to 5 academic years. Each semester is indexed by an integer between 1 and 10. For a fresh undergraduate, they will begin at semester index of 1. For a year 3 student who is currently in semester 1, the semester index will be 5. Below is a table which illustrates the corresponding semester indexes for undergraduates.
          
          | Academic Year | Semester (as of Academic Year) | Semester Index |
          | :-----------: | :----------------------------: | :------------: |
          |       1       |               1                |       1        |
          |       1       |               2                |       2        |
          |       2       |               1                |       3        |
          |       2       |               2                |       4        |
          |       3       |               1                |       5        |
          |       3       |               2                |       6        |
          |       4       |               1                |       7        |
          |       4       |               2                |       8        |
          |       5       |               1                |       9        |
          |       5       |               2                |       10       |
    * Grade Value Covention
        * As per NUS [Grade Policy](http://www.nus.edu.sg/registrar/academic-information-policies/undergraduate-students/modular-system "NUS Official Site"), letter grades will have a corresponding Academic Point attached to them. Special cases such as Satisfactory/Unsatisfactory (SU) grades can be captured by our software as well. Below is a table of grades and their corresponding grade value. Do note that the grades of forecasted modules should be declared as NT, short for Not Taken.
          
          |            Grade            | Academic Points |
          | :-------------------------: | :-------------: |
          |             A+              |       5.0       |
          |              A              |       5.0       |
          |             A-              |       4.5       |
          |             B+              |       4.0       |
          |              B              |       3.5       |
          |             B-              |       3.0       |
          |             C+              |       2.5       |
          |              C              |       2.0       |
          |             D+              |       1.5       |
          |              D              |       1.0       |
          |              F              |       0.0       |
          | Additional Grading Options* |        -        |
          
          *Additional Grading options include S,U,CS,CU,IC,IP,AUD,EXE,W,WU. They hold no Academic Point Value.
    * Introduction
        * Hello user of PlanNUS! Welcome to our user guide for our software. In this user guide, you can find information regarding how to use PlanNUS and the common questions asked with regards to the usage of PlanNUS. We hope you find this software
         useful and we wish you all the best for your academic journey in NUS.
        * PlanNUS is a __desktop application for planning academic journey__ with modules offered by National University of Singapore (NUS). The __Command Line Interface (CLI)__ will be required for the program to work.
          
          PlanNUS aims to patch the gap for students in NUS by providing an avenue for students to plan their full academic journey in NUS. 
          
          PlanNUS can also generate CAP statistics and even suggest to user which modules to mark as satisfactory/unsatisfactory(S/U) to have the highest CAP possible. 
          
          PlanNUS also has saving and loading features which would reduce the hassle of entering the full academic calendar for each use.
          
          The sections below explain how PlanNUS should be used, and the common errors faced by users. Sections can be easily navigated to by clicking on the hyperlinks in the table of contents.
* **Contributions to the Developer Guide (Extracts without pictures)**
    * Implementation for `AddModuleCommand`
        * Add module command is executed by `AcademicPlannerParser`. It allows users to add modules into their Academic Planner by instantiating a new `PartialModule` object and adding it into the `userModuleList` and `userModuleMap`. Both the list and hashmap are the _java API_, which are used by importing them. The `Person` object is used to encapsulate both `userModuleList` and `userModuleMap`.
          
          Additionally, the add module command extends the `Command` class and overrides its `execute()` command. An external class, `ModuleValidator` is called upon to validate the various parameters that the user has entered, as to only allow valid modules to be added to the user.
          
          Given below is an example usage scenario and how add module command behaves at each step.
  
          __Step 1__ : The user calls the add module command from the `AcademicPlannerParser`, which will initialise a 
          `AddModuleCommand`. `AddModuleCommand`'s constructor takes in parameters of `ModuleLoader`, `Person`,`Ui`, 
          and `String`. Below is a table of what each parameter corresponds to in the state diagram of the program.
          
          |Parameter<br />(Class Name)|Corresponds to<br />(Function of Class)|Referred to as<br />(Variable Name)|
          |:---:|:---:|:---:|
          |`ModuleLoader`| Class representing all modules offered by NUS | `allModules` |
          |`Person`| Class representing current user's information | `currentPerson`|
          |`Ui`| Class representing java's default scanner class | `in`|
          |`String` | Class representing the module code to be added | `moduleCode`|
          
          __Step 2__ : `execute()` is called from the instance of `AddModuleCommand`. It can throw `AcademicException` 
          or `IOException`. `FileHandler` and `Logger` classes from the _java API_ are instantiated to handle logging for the remainder of the `execute()` method. 
          
          __Step 3__ : `in` then reads in the next two lines of input, which is the user's input for the desired semester for the `moduleCode` and `moduleCode`'s grades.
          
          __Step 4__ : `validateInputs()` is called from `ModuleValidator` to validate the user entered data against `allModules`.
          
          __Step 5__ : `AddUtils` is called upon to return module credit for `moduleCode` by `getModuleCreditForModule()`.
          
          __Step 6__ :  `AddUtils` is called upon again to add the module's data to the user, by instantiating a new
          `PartialModule` and storing it in both `userModuleList` and `userModuleMap` via `Person`.
          
          __Step 7__ : `FileHandler`, `Logger`, `PartialModule`, `ModuleValidator`, `AddUtils` and `AddModuleCommand` are terminated.
          
          The following sequence diagram shows how the `AddModuleCommand` works:
          
          The following activity diagram summarizes what happens when the user executes a `AddModuleCommand` :
        
        * Design Considerations
            * Option 1 (Current Implementation): Implementing each command as a class by itself
              * Pros: Increases modularity of code, higher overall code quality 
              * Cons: More complicated to implement
          * Option 2: Implementing each command as a method in a class
              * Pros: Easier to implement
              * Cons: Class needs to be instantiated and increases coupling, reducing testability. This method also decreases SLAP.
        
    * Logging guide
       * `LoggingTool` is available to use for accessible logging at the package `src.main.java.seedu.duke.global`. `LoggingTool`
              constructor takes in a parameter `String` and `FileHandler`. `String` represents `loggerName` and `FileHandler` represents
              the object `fh`, which is the _java API_. `initialise()` will automatically return a fully configured `Logger` object
              with the following parameters :
              
          * `addHandler(fh)`
            * `logger` will log to an external file as defined by `fh`
          * `setUseParentHandlers(false)`
            * Disables logging on the console output
          * `setLevel(Level.INFO)`
            * Any message logged `Level.INFO` and above will be logged
              
          After initialising, the `logger` can be used as per _java API_ constraints. Below shows an example code snippet that can be used to initialise a `logger`:
              
          ```
          FileHandler fh = new FileHandler(<YOUR_LOG_FILE_NAME>);
          Logger logger = new LoggingTool(<YOUR_LOGGER_NAME>,fh).initialize();
          ```