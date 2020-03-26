# How to run the app locally

## 1.  Running the database and keycloak containers

    cd <path to cloned repo>  
    docker-compose up  
    
## 2. Setup keycloak

#### 1. go to http://localhost:8081/auth/ and login with the credential :
    username : admin
    password : admin

![admin dashboard](https://imgur.com/djHTFNp.png)

#### 2. Add a new realm and name it Dakibot 
![admin dashboard](https://imgur.com/JY5MKqE.png)

#### 3. import the realm-export.json file that's included at the source of the  project
![admin dashboard](https://imgur.com/E1PZpWS.png)

![admin dashboard](https://imgur.com/TMDijb6.png)
#### 4. create a  user in the this realm with the credential:
    username : admin
    password : admin
#### 5. map all the realm and realm-management roles to the created user:
![admin dashboard](https://imgur.com/LRJoqm7.png)
![admin dashboard](https://imgur.com/CpNLkMl.png)




