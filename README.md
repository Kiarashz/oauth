## Modified sample code of [OAuth 2.0 Identity and Access Management Patterns](https://www.packtpub.com/mapt/book/application_development/9781783285594)

Updated the original code with the new DropBox endpoints and dockerized containers  
supporting Spring 3.x framework with JDK 7.

To run the application via Maven run the following commands:

    # Start the containers
    docker-compose up -d
    
    # Run bash inside the jdk container
    docker exec -it java7 bash

    # Go to source code location
    [root@java7 /]# cd /source/dbx-code-grant/
    
    # Run web application
    [root@java7 dbx-code-grant]# mvn tomcat7:run


If successful you should be able to browse the home using the following URL:

[http://localhost:8090](http://localhost:8090)