package gui;

import graphic.MyFrame;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWindowSingleton { // singleton DP
    private static volatile MainWindowSingleton instance = null;
    private volatile MyFrame myFrame = null;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private MainWindowSingleton() {
        // Private constructor
    }

    public MyFrame getMyFrame() {
        return myFrame;
    }

    private void initializeMyFrame() {
        myFrame = new MyFrame();
        myFrame.goToMenu();
    }

    public void startMainWindow() { // double check lock DP
        if (myFrame == null) {
            synchronized (this) {
                if (myFrame == null) {
                    initializeMyFrame();
                    executorService.submit(myFrame);
                }
            }
        }
    }

    public static MainWindowSingleton getInstance() {
        if (instance == null) {
            synchronized (MainWindowSingleton.class) {
                if (instance == null) {
                    instance = new MainWindowSingleton();
                }
            }
        }
        return instance;
    }
}
