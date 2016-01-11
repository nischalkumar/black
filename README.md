# Project Black
This is to crate a REST based feed upload/download API's so that developers can forget the pain of uploading the feed

Tech Stack used: Java Spring Boot, Hibernate, MySQL.


Setup instruction:
This codebase needs java 1.7 and maven. Make sure the java home is set and echo $JAVA_HOME prints soemthing similar to /usr/lib/jvm/java-7-openjdk-amd64


1)In black/src/main/resources/application.yaml update the below properties
    jdbc:
        driverClassName: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/black
        username: black
        password: mypassword
    where
        3306  is the default port mysql jdbc driber. If the port has been changed manually in mysql then only change else there is no need to change it.
        black is the database name.
        root is the username of the database which has all permissions over all the database.
        phappyinbit is the password of above mentioned user


2)create the mysql database and insert the dump in the database by executing the below command
    mysql -u root -p happyinbit


2)cd to the repo. For ex: cd black


3)Compile the codebase by running.
    mvn clean install -DskipTests


4)Run the codebase by running the below command.
    mvn spring-boot:run


Repeat step 3 whenever you do a git pull. Step 3 is for compiling.

POINT TO NOTE: Only step 4 is required to run the app. The before mentioned steps are for setting the codebase.


5)Say hari_om hari_om and contact the same for further queries.




