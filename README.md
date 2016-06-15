# bexchange

### Build the application

```
mvn clean install
```

### Run the application

```
cd bexchange; 
mvn package spring-boot:run
```

Access the application: [http://localhost:8080]()

#### Login example

```
POST /login

Content-Type: application/x-www-form-urlencoded
```
Request body:
`username=0000000000&password=pwd`

Response will contain header:
```
Set-Cookie: JSESSIONID=5897C22A36A88C0E3F3FE76EE0F40646; Path=/; HttpOnly
```

Use `cookie` header in each following request to access secured endpoints
```
GET /users

cookie: JSESSIONID=5897C22A36A88C0E3F3FE76EE0F40646
 ```
