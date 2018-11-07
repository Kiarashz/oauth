## Modified sample code of [OAuth 2.0 Identity and Access Management Patterns](https://www.packtpub.com/mapt/book/application_development/9781783285594)

Updated the original code with the new Dropbox endpoints and dockerized containers  
supporting Spring 4.x framework with JDK 7.

#### Build and run the code

To run the application via Maven run the following commands:

    # Start the containers
    docker-compose up -d
    
    # Run bash inside the Redis container
    docker exec -it redis bash

    # Set client id/secret in Redis
    root@redis:/data# redis-cli          
    127.0.0.1:6379> set clientId xxxxxxxxxx
    127.0.0.1:6379> set clientSecret xxxxxxxxx

    # Run bash inside the jdk container
    docker exec -it java7 bash

    # Go to source code location
    [root@java7 /]# cd /source/dbx-code-grant/
    
    # Run web application
    [root@java7 dbx-code-grant]# mvn tomcat7:run


You need to update docker-compose.yml file if not running local Maven repository on your host machine. 
Once tomcat started successfully you should be able to browse the home page using the following URL:

[http://localhost:8090](http://localhost:8090)


#### Stopping the containers
Tomcat can be stopped by pressing Ctrl-C but to stop the containers run below command:

    # Drop the containers
    docker-compose down
    
    # Change ownership of downloaded Maven artifacts 
    sudo chown -R $(whoami) $HOME/.m2

*Alternatively, you can use [SDK Manager](https://sdkman.io/) to install and switch to required version of JDK, and 
Maven on your machine and avoid using Docker.* 