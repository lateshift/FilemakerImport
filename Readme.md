### Simple Filemaker JDBC / Spring Boot Gradle project (Kotlin)

Tiny CLI tool to bulk insert data (Directory listing) into a Filemaker Database using JDBC.

I've included an ODBC/JDBC enabled Filemaker database file in _db/filesdemo.fmp12_ + the Filemaker JDBC driver (delivered with FmPA) in the "libs" directory (fmjdbc.jar) see the **build.gradle** file.

The Filemaker Hibernate dialect (see the included FileMakerDialect.java file) was taken from [https://github.com/johnkeates](https://github.com/johnkeates).

The "only" interesting files are:

- src/main/kotlin/de/iwu/fmi/FmiApplication.kt
- src/main/resources/application.properties
- and maybe the build.gradle 🙂

Steps to run this:

- Open filesdemo.fmp12 in Filemaker
- Run the Project via Gradle or in my case from IntelliJ Idea 🔥
