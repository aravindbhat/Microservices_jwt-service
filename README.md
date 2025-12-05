This JWT-SERVICE application is used generate and validate JWT tokens.
It also maintains account and their secret key in its own database.
**Admin** users should be able to add new account and renew expired secret keys.

Dockerfile is used to create docker image with the help of below command. 
NOTE:prefix it with keyword **sudo** if you get access related error while executing docker commands. 
**docker build -t jwt-service:1.0 .**

To check the successfully created images, use below command 
**docker images**

To run this image using docker, 2 parameters needs to be passed in the docker command.
If local.env file is in .gitignore and not present in the github repository,
Then the env file with database credentials needs to be passed as shown below

**sudo docker run --env-file <ENV FILENAME> -p <PORT>:<PORT> <IMAGE>**
example:
**docker run --env-file local.env -p 8080:8080 jwt-service:1.0**
 
If in local there is issue with connecting to local host of database, then include **--network=host** 
exapmle:
**docker run --env-file local.env --network=host -p 8080:8080 jwt-service:1.0**