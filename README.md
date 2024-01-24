# Code Repertoire
A programmer's toolkit - contains handy functions for daily use.
<br />

```html
<dependency>
    <groupId>io.github.tundeadetunji</groupId>
    <artifactId>code</artifactId>
    <version>check_maven_central_for_latest_version_using_link_below</version>
</dependency>
```


Check it out on <a href="https://mvnrepository.com/artifact/io.github.tundeadetunji/code" target="_blank">Maven Central</a>


If using Gradle:

Add this in your root build.gradle at the end of repositories:

```html
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Then Add the dependency:

```html
dependencies {
        implementation 'com.github.tundeadetunji:api-java-code:TAG'
}
```


Where TAG is synchronized with that of the maven counterpart (<a href="https://central.sonatype.com/artifact/io.github.tundeadetunji/code/1.0.0" target="_blank">Maven Central</a>) or simply the latest release's tag.


<br />
For static imports (as required):
<br />
<br />
<pre>
import static io.github.tundeadetunji.General.*;
import static io.github.tundeadetunji.Machine.*;
import static io.github.tundeadetunji.InCodeSpringBootGui.*;
import static io.github.tundeadetunji.InCodeSpringBootApi.*;
</pre>

To check if a string is valid email, for example:
```java
boolean theEmailIsValid = isEmail("exam@example.com"); //true
```

To strip text of HTML:
```java
String htmlFull = "<h3>howdy!</h3>";
String htmlFree = removeHtmlFromText(htmlFull); //howdy!
```

For more, please check individual documentation or the <a href="https://tundeadetunji.github.io/api-java-code-javadoc/" target="_blank">main documentation site</a>.
<br />
<br />
For the Android Framework version of the project, see <a href="https://github.com/tundeadetunji/api-android-general_module" target="_blank">here</a>.
<br />
<br />


### Working with Jwt (an example)

Assuming you have a User class (annotated with @Entity, with email and password attributes, and an Id attribute of type long or Long) that implements UserDetails (but in this example, email is used in place of username wherever applicable), then

Your Jwt Service class (to be referenced from the UserController class) would look like this:

```java
@Service
public class JwtService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private io.github.tundeadetunji.jwt.JwtService jwtService;

    @Override
    public AuthenticatedUser authenticate(AnonymousUser anonymousUser, Map<String, Object> claims) {
        if(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Email.asString(anonymousUser.getEmail()), Password.asString(anonymousUser.getPassword()))).isAuthenticated()){
            return AuthenticatedUser.from(jwtService.generateToken(anonymousUser.getEmail(), claims));
        }
        throw new RuntimeException(Phrase.ERROR_DURING_AUTHENTICATION);
    }
}
```

Your config would look like this:

```java
@Configuration
@EnableWebSecurity
public class AppConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new io.github.tundeadetunji.jwt.JwtAuthFilter(applicationContext.getBean(AuthUserDetailsService.class));
    }

    @Bean
    public JwtService jwtService() {
        return new io.github.tundeadetunji.jwt.JwtService(Vault.ENCRYPTION_KEY, Phrase.TOKEN_VALIDITY_IN_MINUTES);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(applicationContext.getBean(AuthUserDetailsService.class));
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable()
                .authorizeRequests().requestMatchers("api/v1/user/root", "api/v1/user/register", "api/v1/user/token").permitAll()
                .and()
                .authorizeRequests().requestMatchers("api/v1/user/find").hasAnyAuthority(Noun.Role.ROLE_USER.toString())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```

The AuthUserDetailsService would look like this:

```java
@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(applicationContext.getBean(UserRepository.class).findByEmail(Email.asString(username))).orElseThrow();
    }

}
```

The AnonymousUser (a DTO) class would look like this:

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnonymousUser {
    private String email;
    private String password;

    public static AnonymousUser cleaned(AnonymousUser anonymousUser){
        return AnonymousUser.builder()
                .email(Email.asString(anonymousUser.getEmail()))
                .password(Password.asString(anonymousUser.getPassword()))
                .build();
    }
}
```

The UserController would look like this:

```java
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping({"/root"})
    public ResponseEntity<String> root(){
        return ResponseEntity.ok("Welcome!");
    }

    @PostMapping(value = "/register", consumes = Phrase.JSON_MEDIA_TYPE, produces = Phrase.JSON_MEDIA_TYPE)
    public ResponseEntity<?> register(@RequestBody ApplicantUser applicantUser){
        return userService.findByEmail(applicantUser.getEmail()) != null ?
                ResponseEntity.status(HttpStatus.CONFLICT).body(Phrase.USER_EXISTS) :
                ResponseEntity.status(HttpStatus.CREATED).body(userService.save(User.from(applicantUser)));
    }

    @GetMapping(value = "/find", produces = Phrase.JSON_MEDIA_TYPE)
    public ResponseEntity<?> find(@RequestParam String email){
        User found = userService.findByEmail(Email.asString(email));
        return ResponseEntity.status(found != null ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(found);
    }

    @PostMapping(value = "/token", consumes = Phrase.JSON_MEDIA_TYPE, produces = Phrase.JSON_MEDIA_TYPE)
    public ResponseEntity<?> auth(@RequestBody AnonymousUser anonymousUser){
        User alreadyInDb = userService.findByEmail(Email.asString(anonymousUser.getEmail()));
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(AnonymousUser.cleaned(anonymousUser), Map.of("role", alreadyInDb.getRole().name())));
    }
}
```

The AuthenticatedUser (a DTO) class would look like this:

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser {
    private String token;

    public static AuthenticatedUser from(String token){
        return new AuthenticatedUser(token);
    }
}
```

The ApplicantUser (a DTO) class would look like this:

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantUser {
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

    public static ApplicantUser cleaned(ApplicantUser applicantUser){
        return ApplicantUser.builder()
                .title(Title.asString(applicantUser.getTitle()))
                .firstName(FirstName.asString(applicantUser.getFirstName()))
                .lastName(LastName.asString(applicantUser.getLastName()))
                .email(Email.asString(applicantUser.getEmail()))
                .phone(Phone.asString(applicantUser.getPhone()))
                .password(Password.asString(applicantUser.getPassword()))
                .build();
    }
}
```

The Role enum:

```java
public class Noun {

    public enum Role{
        ROLE_USER,
        ROLE_ADMIN
    }
}
```

The UserService class:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository db;

    @Override
    public User save(User user) {
        return db.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return db.findByEmail(email);
    }
}
```

The UserRepository class:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findByEmail(String email);
}
```

Constants (in an example Phrase class):

```java
public class Phrase {

    public static final String USER_EXISTS = "User with given email already exists.";
    public static final String JSON_MEDIA_TYPE = "application/json";
    public static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";

    public static final String ENCRYPTION_KEY = "ENCRYPTION_KEY";
    public static final String ERROR_DURING_AUTHENTICATION = "Error while authenticating.";
    public static final int TOKEN_VALIDITY_IN_MINUTES = 30;

}
```

Constants (in an example Vault class):

```java
public class Vault {
    public static final String ENCRYPTION_KEY = System.getenv(Phrase.ENCRYPTION_KEY); //ENCRYPTION_KEY must be generated/encoded with minimum of 32 Bytes / 256 bits, found one at https://generate-random.org/encryption-key-generator (no affiliations whatsoever)
}
```

Note that 

```java
Email.asString(String email)
```

and

```java
Password.asString(String password)
```

are assumed to be convenience methods to clean/sanitize the input (e.g. turn email characters to lowercase) before use.
<br />
<br />
Lombok is assumed to be on the classpath, otherwise, you may need to recreate Getters/Setters/Contstructors and the like.
