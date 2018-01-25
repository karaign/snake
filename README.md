# Snake
by Kara I

## How to build
IntelliJ IDEA is used to build this project.

## How to run

### Linux/MacOS

Open a terminal in this folder and type:
`java -jar ./out/artifacts/snake_jar/snake.jar`

### Windows

Open a CMD or a PowerShell console in this folder and type:
`java -jar .\out\artifacts\snake_jar\snake.jar`
or simply double-click `LAUNCH.bat.

## Gameplay

Use W/A/S/D or arrow keys to control the snake.
The snake will wrap around the edges of the level if there's no walls there.

## Settings

Game settings are saved and can be changed in-game by pressing `Space` before
starting the game. There is an option to use an empty level of arbitrary
height and width or to load one of the levels stored in the `levels` folder.

The settings are stored in the file `.snake_config.txt`. If the settings are
messed up to the point where the game cannot start properly, delete that file to
reset them.

## Levels

Levels are stored in text files in the `levels` folder. You can edit
or add levels with a simple text editor. `#` stands for wall, and any
other character stands for empty space. Remember to keep all of the lines the 
same width.