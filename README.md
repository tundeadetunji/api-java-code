# Code Repertoire
A programmer's toolkit - contains handy functions for daily use.
<br />
<br />
Check it out on <a href="https://central.sonatype.com/artifact/io.github.tundeadetunji/code/2.2.3" target="_blank">Maven Central</a>.
<br />
<br />
```html
<dependency>
    <groupId>io.github.tundeadetunji</groupId>
    <artifactId>code</artifactId>
    <version>check_maven_central_for_latest_version_using_link_below</version>
</dependency>
```
<br />
If using Gradle:
<br />

```html
dependencies {
        implementation 'com.github.tundeadetunji:api-java-code:TAG'
}
```
<br />
Where TAG is synchronized with that of the maven counterpart (<a href="https://central.sonatype.com/artifact/io.github.tundeadetunji/code/2.2.2" target="_blank">Maven Central</a>) or simply the latest release's tag.
<br />
<br />
<br />
Root namespace:
<pre>
io.github.tundeadetunji;
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
<br />

For the Android framework version of the project, see <a href="https://github.com/tundeadetunji/api-android-general_module" target="_blank">here</a>.
<br />
<br />

For the .Net framework version, see <a href="https://github.com/tundeadetunji/api-dot_net-all_modules" target="_blank">here</a>.
<br />
<br />

