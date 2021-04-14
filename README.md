# spring_jwtauth
jwt authentication for spring

---
#### Spring initializr
```
https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.4.3.RELEASE&packaging=jar&jvmVersion=11&groupId=springjwt&artifactId=jwtauth&name=jwtauth&description=jwt%20authentication%20for%20spring&packageName=springjwt.jwtauth&dependencies=web,devtools,data-jpa,postgresql,security,lombok
```
---

| url | description | input  | output |
| --- | ----------- | ------ | ------ |
| /api/register | register new user | {"username", "password", "email"} | UserResponsePayload {"username", "email"} |
| /api/login | login | {"username", "password"} | Jwt token |
| /api/index | Read Jwt from cookie and try to authorize | na | Current user UserResponsePayload {"username", "email"} if Jwt is valid |




