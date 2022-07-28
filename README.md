# ProgressButton

[![Jitpack](https://jitpack.io/v/ioki-mobility/ProgressButton.svg)](https://jitpack.io/#ioki-mobility/ProgressButton)
[![MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ioki-mobility/ProgressButton/blob/main/LICENSE)

## What?

We needed an Button that uses a progress state as background.
As we didn't found something like that in Jetpack Compose we decided to build it on our own and open source it.

<p align="center">
    <img src="art/demo.gif" width=180 />
</p>

## How?

The following creates a `ProgressButton` which starts the progress after 1 seconds while the progress will take 10 seconds.

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

You can also checkout the [`SampleActivity`](sample/src/main/java/com/ioki/progressbutton/sample/SampleActivity.kt) for more code snippets.

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
) : Unit
```

| Parameter | Description | 
|---|---|
| Modifier | Modifier to be applied to the button |
| backgroundColor  | The color *behind* the progress |
| progressColor  | The progress color which which will animate away after `duration` reaches zero |
| contentColor  | Color of the content of the button |
| contentColorDisabled  | Color of the content of the button when its disabled |
| enabled  | Enables or disable the button |
| startDelay  | An delay before the progress actually starts |
| duration  | The duration of the progress |
| restDuration | The duration which is left of `duration` to reach zero | 
| onClick  | Will be called when the button got clicked and `enabled` is `true` |
| onFinished  | Will be called when the `duration` reaches zero |
| content  | The content of the button |

## Download

ProgressButton is hosted on JitPack. 
Here's how you include it in your gradle project:

**Step 1.** Add the JitPack repository to your build file:

```groovy
allprojects {
    repositories {
        // Other repositories
        maven { 
            url 'https://jitpack.io'
            content {
                // A bit extra safety
                includeGroup("com.github.ioki-mobility")
            }
        }
    }
}
```

**Step 2.** Add the dependency:

```groovy
dependencies {
    implementation 'com.github.ioki-mobility:ProgressButton:<latest-version>'
}
```

<!-- 
## Releasing
// TODO
Probably just create a github release
-->