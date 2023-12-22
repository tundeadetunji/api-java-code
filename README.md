# Code Repertoire
A project containing handy functions for daily use.
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


Where TAG is synchronized with that of the maven counterpart (<a href="https://central.sonatype.com/artifact/io.github.tundeadetunji/code/1.0.0" target="_blank">Maven Central</a>).


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

There's a lot of WIP, but it's only expanding, and will also tend toward optimization in future releases.

For more, please check individual documentation or the <a href="https://tundeadetunji.github.io/api-java-code/" target="_blank">main documentation site</a>.
<br />
<br />
For the Android Framework version of the project, see <a href="https://github.com/tundeadetunji/api-android-general_module" target="_blank">here</a>.

