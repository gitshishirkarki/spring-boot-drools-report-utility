# Shishir Report Utility

[![Build Status](https://img.shields.io/github/workflow/status/gitshishirkarki/spring-boot-drools-report-utility/Build)](https://github.com/gitshishirkarki/spring-boot-drools-report-utility/actions)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/gitshishirkarki/spring-boot-drools-report-utility/blob/master/LICENSE)

The Report Utility is a library project written in Java 8 using the Spring Boot framework. It is integrated with the Drools Rule Engine for mapping report types to bean names. The utility provides a simple showcase of dynamically calling the mapped bean name method from the architectural design. Additionally, it includes an event-based system for generating CSV reports. Developers can leverage this utility by creating a component that generates a `Map<String, String>` and defining it in a Drools file. The Report Utility handles the rest of the process.

## Features

- **Report Type Mapping**: Utilizes the Drools Rule Engine to map report types to bean names, enabling dynamic method calls based on the report type.
- **CSV Report Generation**: Offers an event-based system for generating CSV reports. Developers need only create a component that generates a `Map<String, String>` and define it in a Drools file.
- **Spring Boot**: Built on top of the Spring Boot framework, providing a robust and scalable architecture for report generation.
- **Continuous Integration and Delivery (CI/CD)**: The project includes a CI/CD pipeline on GitHub, facilitating the deployment of library JARs generated from the repository.

![Shishir Report Util](https://github.com/gitshishirkarki/spring-boot-drools-report-utility/blob/main/images/CICD.png)

## Prerequisites

Before using the Report Utility, ensure that you have the following dependencies installed:

- Java 8
- Gradle

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/gitshishirkarki/spring-boot-drools-report-utility.git
   ```

2. Navigate to the project directory:

   ```bash
   cd spring-boot-drools-report-utility
   ```

3. Build the project using Gradle:

   ```bash
   gradle build
   ```

## Usage

To use the Report Utility as a library in your application, follow these steps:

1. Add the Report Utility as a dependency in your `build.gradle` file:

   ```groovy
   dependencies {
       implementation 'com.shishir:reports:1.1'
   }
   ```

2. Implement your custom report generation component by creating a class that generates a `Map<String, String>`.

3. Define the mapping between report types and bean names in a Drools file.

4. Invoke the report generation process using the Report Utility.

> **Note**: For code examples and detailed usage instructions, please refer to the documentation in the repository.

## License

The Report Utility is released under the [MIT License](https://github.com/gitshishirkarki/spring-boot-drools-report-utility/blob/master/LICENSE).
