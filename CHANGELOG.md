# Changelog

## 2.1.0

Maintenance release:
Just a bump of dependencies and tools.

Full diff: [`2.0.0...2.1.0`](https://github.com/ioki-mobility/ProgressButton/compare/2.0.0...2.1.0)

## 2.0.0

**New maven coordinates**
Since we are publishing to Maven Central, we updated our maven coordinates.
```diff
-  com.github.ioki-mobility:ProgressButton:$version
+  com.ioki.progressbutton:progressbutton:$version
```

You also might want to adjust your repository declaration:
```diff
repositories {
-  maven(url = "https://jitpack.io")
+  mavenCentral()
}
```

* Bump compose dependencies to 1.5.4/1.8.1
* Bump AGP to 8.2.0
* Upgrade Gradle to 8.5

Full diff: [`1.3.2...2.0.0`](https://github.com/ioki-mobility/ProgressButton/compare/1.3.2...2.0.0)

## 1.3.2

* Use SDPX license URL for license in the POM file
* Update target and compile sdk to 34
* Bump compose dependencies to 1.5.3/1.8.0/1.10.0

Full diff: [`1.3.1...1.3.2`](https://github.com/ioki-mobility/ProgressButton/compare/1.3.1...1.3.2)

## 1.3.1

* Use SDPX license identifier for license in the POM file
* Upgrade Gradle Wrapper (8.4)

Full diff: [`1.3.0...1.3.1`](https://github.com/ioki-mobility/ProgressButton/compare/1.3.0...1.3.1)

## 1.3.0

* Add license to POM file
* Upgrade Gradle Wrapper Plugin
* Upgrade Android Gradle Plugin (8.1.2)

Full diff: [`1.2...1.2.1`](https://github.com/ioki-mobility/ProgressButton/compare/1.2.1...1.3.0)

## 1.2.1

* Add JitPack config file

Full diff: [`1.2...1.2.1`](https://github.com/ioki-mobility/ProgressButton/compare/1.2...1.2.1)

## 1.2

**Maintenance release**:
* Use Gradle Version Catalog
* Add Dependabot
* Add (simple) CI
* Update a lot of dependencies
* Add Gradle Wrapper Upgrade plugin
* Upgrade Gradle Wrapper
* Add jvm toolchain support
* Document how to release

Full diff: [`1.1...1.2`](https://github.com/ioki-mobility/ProgressButton/compare/1.1...1.2)

## 1.1

Add the ability to add rest duration to the progress

## 1.0

Initial release