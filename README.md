# ChessViewer

## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`

## Running the program

1. Right click on your `Java Project` --> `Run As` --> `Java Application`
2. This will create an automatic `run configuration` on first run, but this will throw `ArrayIndexOutOfBoundsException: 0	at assignment3.chessview.Main.main(Main.java:12)`
3. Right click, again, on your `Java Project` --> `Run As` --> `Run Configurations`
4. In the `Arguments` tab, input `path` of any `game` file in `games` directory i.e. to view `game-001.txt`, put `games/game-001.txt` in `Program arguments`
5. Click `Apply`. Then click `Run`

## Notes
<strong>This is a viewer only</strong>
<p>
  Click <a href=''>here</a> for more
