# ChessViewer

## Copyright
This program is made as an answer to an assignment. Some parts of this program are subject to Copyright by Victoria University of Wellington. 

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

## Build an executable using IntelliJ IDEA

1. Go to **File** → **Project Structure** → **Artifacts**.
2. Click the green plus (**+**) button, select **JAR**, and choose **From modules with dependencies...**
3. In the **Main Class** field, click the folder icon and select the application's entry point class.
4. Under **JAR files from libraries**, select **extract to the target JAR** (this creates the single Fat JAR).
5. Click **OK**, then click **Apply**.
6. From the top menu bar, go to **Build** → **Build Artifacts...** and click **Build**.
7. The executable jar file will be generated inside the project directory under `out/artifacts/`.

### Run the executable JAR file using the command line:

```bash
java -jar path/to/executable.jar
```

## Live Demo

You can run this application directly in your web browser via the link below:

**[Launch Live Demo](https://rjperez94.github.io/ChessViewer/)**

### Loading Local Data

If you are trying to pick a file from your physical hard drive, you cannot browse your local folders through the Java window. You must use the bridge upload feature.

1. Look at the very top right of the Java window's title bar for a small **Up Arrow (Upload)** button.
2. Click it to trigger your **native browser file picker** (this one can see your real computer folders).
3. Select your local file. The app will silently drop it into the virtual folder named `/files/uploads/`.
4. Now, inside your Java file picker, type `/files/uploads/` into the file path bar and press **Enter** to find your uploaded file.


## Notes
<strong>This is a viewer only</strong>
<p>
  Click <a href='https://github.com/rjperez94/ChessViewer/blob/master/brief.pdf'>here</a> for more information about this project
