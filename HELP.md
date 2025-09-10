# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Introducing Spring security with JWT token - Creation - getUsername - validate ( Bearer ) 

1. We create a JwtTokenUtil Class in order to manage the generateToken, extractUsername and validateToken. Remember previously we have to set the SECRET(key) and then encrypt the secretKey and also set a expiration time for this key.
2. Today, 10/09/25, normally we use Jwts to build the Jwt with the subject, IssuedAt, Expiration, SignWith to generate the token.
3. In order to extractUsername, we need first to signwiththeKey and then parse with the Claim if these match we could get the body and then the subject.
4. In order to validate the token, we have to sign with the SAMEKEY and matching with the parse ClaimJws and if these two are "equals" will return true.

### Creation of JwtRequestFilter

1. We have to extend from OncePerRequestFilter in order to override doFilterInternal, in which we will check the header that contains Authorization and verify if we have the Bearer. If this is ok, we have to extractThe username. Then, we verify that the username is not null and we have to User UserDetails to map the user with the class of Spring Security. After that, we validate the token, if success we use the UsernamePasswordAuthenticationToken to fill in the SecurityContextHolder and setAuthentication.
2. Finally, we do the filter if everything goes success we add into the filterChain this new Filter with the request and the response.


### Creation of SecurityConfig

1. We mainly make available the endpoint in order to get the Bearer token, this is the main entrance for the app. Here we have created the session without a state. Basically, every request is processed independently, with no necessity to make the server store the information or make it to remember every interaction. 
