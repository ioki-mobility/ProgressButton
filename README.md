# ProgressButton

[![Build](https://github.com/ioki-mobility/ProgressButton/actions/workflows/build.yml/badge.svg)](https://github.com/ioki-mobility/ProgressButton/actions/workflows/build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.ioki.progressbutton/progressbutton?labelColor=%2324292E&color=%233246c8)](https://central.sonatype.com/namespace/com.ioki.progressbutton) <!-- Disabled because of: https://github.com/badges/shields/pull/10997
[![Snapshot](https://img.shields.io/nexus/s/com.ioki.progressbutton/progressbutton?labelColor=%2324292E&color=%234f78ff&server=https://s01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/ioki/progressbutton/)
-->
[![javadoc](https://javadoc.io/badge2/com.ioki.progressbutton/progressbutton/javadoc.svg?labelColor=%2324292E&color=%236eaaff)](https://javadoc.io/doc/com.ioki.progressbutton/progressbutton) 
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg?labelColor=%2324292E&color=%23d11064)](https://github.com/ioki-mobility/ProgressButton/blob/main/LICENSE.md)

## What?

We needed an Button that uses a progress state as background.
As we didn't found something like that in Jetpack Compose 
we decided to build it on our own and open source it.

<p align="center">
    <img src="art/demo.gif" width=180 />
</p>

## How?

The following creates a `ProgressButton` which starts the progress after 1 seconds while the
progress will take 10 seconds.

```kotlin
ProgressButton(
    modifier = Modifier,
    startDelay = 1.seconds,
    duration = 10.seconds,
    onClick = {
        // Do something on click 
    },
    onFinished = {
        // Do something after progress finished
    }
) {
    Box(
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Hello World")
    }
}
```

You can also checkout
the [`SampleActivity`](sample/src/main/java/com/ioki/progressbutton/sample/SampleActivity.kt) 
for more code snippets.

## Docs

```kotlin
fun ProgressButton(
    modifier: Modifier,
    backgroundColor: Color = Color.Transparent,
    progressColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = contentColorFor(progressColor),
    contentColorDisabled: Color = MaterialTheme.colors.primaryVariant,
    enabled: Boolean = true,
    startDelay: Duration = Duration.ZERO,
    duration: Duration = 10L.seconds,
    restDuration: Duration = duration,
    onClick: () -> Unit = {},
    onFinished: () -> Unit = {},
    content: @Composable () -> Unit
)
```

| Parameter            | Description                                                                    | 
|----------------------|--------------------------------------------------------------------------------|
| Modifier             | Modifier to be applied to the button                                           |
| backgroundColor      | The color *behind* the progress                                                |
| progressColor        | The progress color which which will animate away after `duration` reaches zero |
| contentColor         | Color of the content of the button                                             |
| contentColorDisabled | Color of the content of the button when its disabled                           |
| enabled              | Enables or disable the button                                                  |
| startDelay           | An delay before the progress actually starts                                   |
| duration             | The duration of the progress                                                   |
| restDuration         | The duration which is left of `duration` to reach zero                         | 
| onClick              | Will be called when the button got clicked and `enabled` is `true`             |
| onFinished           | Will be called when the `duration` reaches zero                                |
| content              | The content of the button                                                      |

## Download

ProgressButton is hosted on Maven Central.
Here's how you include it in your gradle project:

**Step 1.** Add the Maven Central repository to your build file:

```groovy
repositories {
    // Other repositories
    mavenCentral()
}
```

<details>
<summary>Snapshot versions can be download over the sonatype snapshot repository:</summary>
    
```kotlin
repositories {
    // Other repositories
    maven(url = "https://central.sonatype.com/repository/maven-snapshots")
}
```

</details>

**Step 2.** Add the dependency:

```groovy
dependencies {
    implementation 'com.ioki.progressbutton:progressbutton:<latest-version>'
}
```

## Releasing

### Continuous release

By default, each merge to the `main` branch will create a new SNAPSHOT release. 
If you want to use the latest and greatest use the SNAPSHOT version of the library.
But please be aware that they might contain bugs or behaviour changes.

To use the SNAPSHOT version you have to include the sonatype snapshot repository which in the *Download* section above.

### Proper release

1. Checkout `main` branch and pull latest changes.
2. Add the changes to the top of the [`CHANGELOG.md`](CHANGELOG.md) file.
3. Update the version in the [`progressbutton/build.gradle.kts`](progressbutton/build.gradle.kts)
   file.
4. Commit.
    * `git commit -m "Prepare next relaese" .`
5. Create a git tag with the version of the [`CHANGELOG.md`](CHANGELOG.md) and push.
    * `git tag [X.Y.Z]`
    * `git push origin [X.Y.Z]`
6. Update the version [`progressbutton/build.gradle.kts`](progressbutton/build.gradle.kts) to the **next patch version** +`-SNAPSHOT`
7. Commit and push the changes
   * `git push origin main .`
