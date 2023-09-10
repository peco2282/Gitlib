# Gitlib

This library is an api-wrapper for github.

# install
On DSL

```kotlin
repositories {  
    maven {
        url = uri("https://maven.pkg.github.com/peco2282/gitlib")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
  implementation("com.github.peco2282:gitlib:1.+")
}
```
