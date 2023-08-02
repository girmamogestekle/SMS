# SMS
Student Management System(SMS) is a sample project using for practicing frontend, backend, security, database, CICD and deploying technologies.

**Technologies and Tools**

**Tools:** Intellij, Postman, MongoDB, H2Database, Splunk, Swagger, Jenkins, Docker, MailTrap and AWS S3

**Technologies:** Java, SpringBoot, SpringSecurity, sql and nosql 


**Setup**
    
the minimal setup you need to get up the project

        A. download or clone the repository,
        B. Install mongodb,postman,splunk,jenkins and docker on your local machine
        C. Create an account of mailtrap and aws then goto application.properties file and change the creaditals and its config file.
        D. some endpoints are secured and role based. Please see security config file to understand the config. 
        E. It is basic auth login just by pass username and pwa. 
        F. Create a creaditials in mongodb to login.
            1. make uncomment in security config file on line 23
            2. make comment in security config file on line 37
            3. open postman and goto register http file. 
            4. make save this code consquetively through postman
                =>  "username":"TestAdmin",
                    "firstName":"TestAdminFirstName",
                    "middleName":"TestAdminMiddleName",
                    "lastName":"TestAdminLastName",
                    "email":"TesteAdminMail@gmail.com",
                    "password":"1234",
                    "role":"ADMIN"
                => "username":"TestTeacher",
                    "firstName":"TestTeacherFirstName",
                    "middleName":"TestTeacherMiddleName",
                    "lastName":"TestTeacherLastName",
                    "email":"TesteTeacherMail@gmail.com",
                    "password":"1234",
                    "role":"TEACHER"
                => "username":"TestStudent",
                    "firstName":"TestStudentFirstName",
                    "middleName":"TestStudentMiddleName",
                    "lastName":"TestStudentLastName",
                    "email":"TesteStudentMail@gmail.com",
                    "password":"1234",
                    "role":"STUDENT"
                => "username":"TestParent",
                    "firstName":"TestParentFirstName",
                    "middleName":"TestParentMiddleName",
                    "lastName":"TestParentLastName",
                    "email":"TesteParentMail@gmail.com",
                    "password":"1234",
                    "role":"PARENT"
            5. make comment in security config file on line 23
            6. make uncomment in security config file on line 37
        G. Get Postman collection on version1 branch

**Status**

SMS is still in progress. 
1. Version 1 is done. you can found the source code at version1 branch and the diagram as well.
2. Version 2 is in progress.

**Credits**

List of contributors:
1. azemedkun-git
2. code2buptodate
3. GirmaMogesTekle12


**License**

SMS@2023


**Diagram**
![SMSClassDiagram drawio](https://github.com/GirmaMogesTekle12/SMS/assets/126026753/28a54cd6-4b08-4225-9de3-1929ab576325)
