# DatabaseBenchmark

- [Why was this project created?](#why-was-this-project-created)
- [Technologies included in this project](#technologies-included-in-this-project)
    - [Hibernate and JPA](#hibernate-and-jpa)
    - [Spring JDBC Template](#spring-jdbc-template)
    - [jOOQ](#jOOQ)
    - [R2DBC](#r2dbc)
- [Technologies used for benchmarks](#technologies-used-for-benchmarks)    
    - [JMH](#jmh)
    - [JMeter](#jmeter)

## Why was this project created?

The main idea is including in a project a way to test different technologies used to access and get information from/to database. Adding some benchmarks to help in the decision
about which one will provide a better performance.

## Technologies included in this project

Using Spring microsystem as base of the used technologies, the following ones have been included in the current project:

### Hibernate and JPA

Inside the package `jpa` has been included the required classes to work with the option: **Hibernate** and **JPA**:  

* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

### Spring JDBC Template

In the package `jdbc` has been included the required classes to work with **JDBC templates** of Spring:

* [Spring JDBC Template](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#reference)

### jOOQ

Inside the package `jooq` has been included the required classes to work with **jOOQ** Spring integration:

* [Spring jOOQ](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-jooq)
* [jOOQ](https://www.jooq.org)

### R2DBC

In the package `r2dbc` has been included the required classes to work with **R2DBC** Spring integration:

* [Spring R2DBC](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#reference)
* [R2DBC](https://r2dbc.io)

## Technologies used for benchmarks

The following are the tools used to execute the benchmarks:

### JMH

Java harness for building, running, and analysing nano/micro/milli/macro benchmarks written in Java and other languages targeting the JVM.

* [JMH](https://github.com/openjdk/jmh)

The benchmarks have been included in the test class: `DatabaseBenchmarkApplicationTests` and there are several *CSV files* with some local
ones into the folder: `benchmark-result`.

### JMeter

Application designed to load test functional behavior and measure performance. It was originally designed for testing Web Applications but has since expanded to other test
functions.

* [JMeter](https://jmeter.apache.org)

A plan for every technology used has been added into the folder: `Jmeter`, including the results of a local benchmark in:

![Alt text](/Jmeter/JMeter_Results.png?raw=true "Encryption endpoint")
