# News Portal
##### By Gilbert Korir

## Description
News Portal is a project where we practice using REST API for querying and retrieving information. As a user, you should be able to view all all departments present, news articles (general or department-specific) and create users within a department as well as view information regarding a particular department or user.

## Prerequisites
1. PostgreSQL
2. Java JDK
3. Gradle
4. Git
5. Some prior knowledge of Java
6. Some prior knowledge of Spark framework
7. Postman
8. (optional) Java IDE

## Built With
1. Java & Gradle
2. Spark
3. Junit for testing
4. PostgreSQL database

## Setup Guide
##### PostgreSQL
###### Installation
+ Run `sudo apt-get install postgresql postgresql-contrib libpq-dev` in the terminal to install PostgreSQL in your device.
+ Enter y when prompted `Do you want to continue? [Y/n]` and wait for the installation to complete.
+ Create your own credentials with superuser capabilities with the same name as our login name by running `sudo -u postgres createuser --superuser $USER`
+ Next, we’ll have to create a database with the same name as our login name by running `sudo -u postgres createdb $USER`
+ Finally run `psql` in the terminal to connect to the server
###### Structure
+ After running the `psql` command to connect to the server, proceed to create the database wildlife-tracker by executing the following command: `create database news_portal;`
+ Follow it up with this following command to connect to the newly created database`\c news_portal;`
+ Once connected, create the following tables by running these commands:
```
CREATE DATABASE news_portal;
\c news_portal;
CREATE TABLE departments (id SERIAL PRIMARY KEY,name VARCHAR,description VARCHAR,size int);
CREATE TABLE news (id SERIAL PRIMARY KEY,news_type VARCHAR,department_id INT,user_id INT,title VARCHAR,description VARCHAR);
CREATE TABLE staff (id SERIAL PRIMARY KEY,name VARCHAR,position VARCHAR,staff_role VARCHAR);
CREATE TABLE users_departments (id SERIAL PRIMARY KEY,user_id INT,department_id INT);
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
```
+ The last command creates the test database that shall be used to run your tests on. insert `\q` to exit psql server.

## API Documentation

### General News
{
"id": 1,\
"user_id": 4,\
"department_id": 0,\
"title": "dff",\
"news_type": "general",\
"description": "dfff"\
}

### Department news
{
{
"id": 2,\
"name": "Engineering",\
"description": "Check updates",\
"size": 16\
},
}
### user
{
"id": 1,
"name": "Japhet",
"position": "Assistant manager",
"staff_role": "Mail check"
}
### Department
{
"name":"IT",\
"description":"Technology Related News"\
}

##### Java
+ Run `java --version` to check which version of java you have installed in your device. If you have java installed, you should see an output slightly similar to the one below...  
  _**openjdk 10.0.2 2018-07-17**_  
  _**OpenJDK Runtime Environment (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.4)**_  
  ... then you need to install java by running this : `sudo apt install default-jre`

+ Clone the repository into a desired folder by running the following link in your terminal: `https://github.com/Nicholas-Muturi/news-portal.git`

###### (for users with an IDE such as IntelliJ)
+ Open the project using the IDE you have installed
+ Build and Run the project.
+ Open your browser or postman (preferred) in order to interact with the API
+ Use the path `/sitemap` in the url when running the project to get a list of all paths in a summarized form

###### (for users without an IDE)
+ Navigate to the folder containing the project
+ Build the gradle by running: `gradle build`.
+ Compile the code by running: `gradle compileJava`
+ Navigate to the App class file located at build/classes/java/main and run: `java App`.
+ Open your browser or postman (preferred) in order to interact with the API
+ Use the path `/sitemap` in the url when running the project to get a list of all paths in a summarized form

[//]: # (## Live Site)

[//]: # ([Visit API website]&#40;https://news-portal-0047.herokuapp.com/sitemap&#41;)

## License

MIT This project is licensed under the terms of the MIT license. Copyright (c) 2022 Gilbert Korir

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.