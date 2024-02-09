//  Name : yarden itah     ID : 315097527
//  Name : Roei Zaburoff   ID : 205685555

package main;
import gui.MainWindowSingleton;

public class MainApp {
    public static void main(String[] args) {
        MainWindowSingleton mainWindow = MainWindowSingleton.getInstance(); // Get the instance of MainWindowSingleton
        mainWindow.startMainWindow(); // Start the main window
    }
}
