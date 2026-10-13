# Jugger Tetris

A Tetris clone for Android in which the bricks carry the logos of
[Jugger](https://en.wikipedia.org/wiki/Jugger) teams. Every new block gets a
random team logo; cleared rows count towards your score and the game speeds up
as the score rises.

## Controls

| Button | Action                           |
| ------ | -------------------------------- |
| ⫷      | move the block left              |
| ⟱      | move the block down (hold to repeat) |
| ⫸      | move the block right             |
| ⟳      | rotate the block                 |

The highscore is shown in the menu and stored on the device.

## Requirements

- Android 6.0 (API 23) or newer; the app targets Android 15 (API 35)
- JDK 17 or newer
- Android SDK with platform 35 (Android Studio installs this for you)

## Build and install

Open the project in Android Studio and press Run, or use the command line:

```sh
# point Gradle at your SDK once (Android Studio creates this file itself)
echo "sdk.dir=$HOME/Library/Android/sdk" > local.properties

./gradlew assembleDebug          # APK ends up in app/build/outputs/apk/debug/
./gradlew installDebug           # build and install on the connected phone
adb shell am start -n de.jugger.tetris/com.jugger.tetris.Menu
```

The phone needs USB debugging enabled (Settings → Developer options).

## Project layout

```
app/src/main/java/com/jugger/tetris/
├── Menu.kt              start screen with the highscore
├── GameActivity.kt      game screen: grids, buttons, drawing
├── Game.kt              game loop (runs on its own thread)
├── TetrisField.kt       the playing field and row clearing
├── HighScore.kt         highscore persistence
├── RepeatListener.kt    press-and-hold repeat for buttons
├── Basic/               Brick and Point
├── Block/               block types, movement and rotation
└── BorderImageview/     brick view and the team logo images
```

Team logos live in `app/src/main/res/drawable/`.

## Tech

Kotlin 2.1, Android Gradle Plugin 8.9, Gradle 8.11, AndroidX (AppCompat,
ConstraintLayout, GridLayout) with view binding.
