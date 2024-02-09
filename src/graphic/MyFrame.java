package graphic;
import gui.GetInformationFromTheUser;
import utils.*;
import addVehicle.AddVehicleClass;
import vehicles.*;
import vehicles.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class MyFrame implements ActionListener ,Runnable {
    private final AtomicInteger reportFrameCounter = new AtomicInteger(0);
    private final List<JPanel> buyPanelList;
    private final List<JPanel> inventoryPanelList;
    private final List<JPanel> testDrivePanelList;
    private final JFrame menuFrame;
    private final JPanel menuPanel;
    private final JPanel reportPanel;
    private BuyVehicleFrame buyVehiclesFrame;
    private final JTextField globalKmText;
    private final ButtonGroup flagButtonGroup;
    private  JButton menuButton;
    private static JMenuItem currentInventoryReportBtn;
    private final Vector<Ivehicle> vehicleList;
    private Thread buyTrd, testDriveTrd, addVehicleTrd, resetKmTrd, flagTrd, inventory;
    private final ImageIcon vehiclesImage;
    private final Map<String, Boolean> addImageMap;
    private final GetInformationFromTheUser getInfo;

    private final Originator originator;
    private final Caretaker caretaker;

    private final ExecutorService testDrivePool;

    private enum E_country {
        Greece, Israel, Somalia, Italy, UnitedStates, Germany, Pirates
    }
    private boolean isChangeFlagsWindowOpen = false; // Flag variable to track if the window is open
    private static final int width = 800;
    private static final int height = 600;

     public  MyFrame() {
        this.vehicleList = new Vector<>(); // changed to  Vector for working threads safely
        vehiclesImage = new ImageIcon();
        flagButtonGroup = new ButtonGroup();

        menuFrame = new JFrame();
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(new Color(0, 0, 0, 224));
        menuFrame.setTitle("My vehicle agency");
        menuFrame.setSize(width, height);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.add(menuPanel);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);  // Center the frame on the screen
        reportPanel = new JPanel();

        buyPanelList = new ArrayList<>();
        testDrivePanelList = new ArrayList<>();
        inventoryPanelList = new ArrayList<>();

        originator = new Originator();
        caretaker = new Caretaker();

        addImageMap = new HashMap<>();
        globalKmText = new JTextField();
        testDrivePool = Executors.newFixedThreadPool(7);
        getInfo = new GetInformationFromTheUser();
    }

    public void goToMenu() { // menu
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/pictures/Cityscape.jpg"));
        backgroundLabel.setBounds(0, 0, width, height);

        globalKmText.setText("The total kilometer is: " + Vehicle.getGlobalKilometer());
        globalKmText.setEditable(false);
        globalKmText.setBackground(Color.lightGray);
        globalKmText.setBounds(580, 27, 220, 30);
        backgroundLabel.add(globalKmText);
        // Create an observer that updates globalKmText
        globalKilometerObserver observer = new globalKilometerObserver() { // Create an observer that updates globalKmText
            @Override
            public void update(int globalKilometer) {
                globalKmText.setText("The total kilometer is: " + globalKilometer);
            }
        };
        Vehicle.registerObserver(observer); // Register the observer with the Vehicles class

        JButton buyVehicles = new JButton("Buy vehicles");
        buyVehicles.setBounds(290, 70, 250, 50);
        backgroundLabel.add(buyVehicles);
        buyVehicles.addActionListener(e -> {
            if (vehicleListIsNotEmpty()) {
                buyTrd = new Thread(this::openBuyVehiclesWindow);
                buyTrd.start();
            }

        }); // buy vehicle case

        JButton testDrive = new JButton("Test drive");
        testDrive.setBounds(290, 140, 250, 50);
        backgroundLabel.add(testDrive);
        testDrive.addActionListener(e -> {
            if (vehicleListIsNotEmpty()) {
                testDriveTrd = new Thread(() -> openTestDriveWindow());
                testDriveTrd.start();
            }
        }); // test drive case

        JButton resetkm = new JButton("Reset kilometer");
        resetkm.setBounds(290, 210, 250, 50);
        backgroundLabel.add(resetkm);
        resetkm.addActionListener(e -> {
            if (vehicleListIsNotEmpty()) {
                resetKmTrd = new Thread(() -> openResetKmWindow());
                resetKmTrd.start();
            }

        }); // reset km case

        JButton changeFlagsn = new JButton("Change flags");
        changeFlagsn.setBounds(290, 280, 250, 50);
        backgroundLabel.add(changeFlagsn);
        changeFlagsn.addActionListener(e -> {
            if (vehicleListIsNotEmpty()) {
                flagTrd = new Thread(() -> openChangeAllFlagsWindow());
                flagTrd.start();
            }
        }); // Change flags case

        JButton addVehicle = new JButton("Add vehicle");
        addVehicle.setBounds(290, 350, 250, 50);
        backgroundLabel.add(addVehicle);
        addVehicle.addActionListener(e -> {
            addVehicleTrd = new Thread(() -> openAddVehicleBoxWindow());
            addVehicleTrd.start();
        }); // add vehicle case

        JMenuBar menuBar = new JMenuBar();
        menuBar.setForeground(Color.white);
        menuBar.setForeground(Color.blue);
        menuFrame.setJMenuBar(menuBar);

        JMenu reportMenu = new JMenu("Current inventory report");
        menuBar.add(reportMenu);

        currentInventoryReportBtn = new JMenuItem("Viewing inventory");
        reportMenu.add(currentInventoryReportBtn);
        currentInventoryReportBtn.addActionListener(e -> {
            if (vehicleListIsNotEmpty()) {
                inventory = new Thread (() ->openReportWindow());
                inventory.start();
            }
        }); // open anew option to see the inventory in app left side on menu

        JButton saveBtn = new JButton("Save"); // use memento DP to save 3 states
        saveBtn.setBounds(55, 480, 80, 40);
        backgroundLabel.add(saveBtn);
        saveBtn.addActionListener(e -> {
            saveState(); 
        });

        JButton loadState = new JButton("Load last state");
        loadState.setBounds(290, 420, 250, 50);
        backgroundLabel.add(loadState);
        loadState.addActionListener(e -> {
            loadLastState();
        });

        menuPanel.setLayout(null);
        menuPanel.add(backgroundLabel);
    } // menu

    private void saveState() {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the current state ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            if (vehicleList.size() == 0) JOptionPane.showMessageDialog(null, "No data was found to save");
            else {
                originator.setState(new Vector<>(this.vehicleList)); // Create a new Vector with copies of vehicles
                Memento memento = originator.createNewMemento();
                caretaker.addMemento(memento);
                JOptionPane.showMessageDialog(null, "The current state has been saved successfully");
            }
        } else JOptionPane.showMessageDialog(null, "No changes were made");

    }

    private void loadLastState(){
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the current state ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            Memento memento = caretaker.getMemento();
            if (memento == null)  JOptionPane.showMessageDialog(null, "No data was found to load");
            else {
                originator.setMemento(memento);
                vehicleList.clear();
                vehicleList.addAll(new Vector<>(memento.getState())); // Create a new Vector with copies of vehicles , addAll is synchronized
                JOptionPane.showMessageDialog(null, "Successfully loaded status");
            }
        }else JOptionPane.showMessageDialog(null, "No changes were made");
    }

    private void openResetKmWindow() {
        JFrame resetKmFrame = new JFrame();
        resetKmFrame.setTitle("reset kilometer for all vehicles");
        resetKmFrame.setResizable(false);
        resetKmFrame.setBackground(Color.gray);
        resetKmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resetKmFrame.setSize(width, height);
        resetKmFrame.setLayout(null);

        JPanel resetKmPanel = new JPanel();
        resetKmPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        resetKmPanel.setBackground(Color.gray);
        resetKmPanel.setSize(width, height);
        resetKm(resetKmFrame);
        resetKmFrame.add(resetKmPanel);
        resetKmPanel.setVisible(true);
    }

    private void openAddVehicleBoxWindow() { // call to addVehicle class
        System.out.println("the Tread in openAddVehicleBoxWindow now is:" + Thread.currentThread().getName() + "\n");
        addImageMap.put(Thread.currentThread().getName(), false);
        JFrame AddVehicleFrame = new JFrame();
        AddVehicleFrame.setResizable(false);
        AddVehicleFrame.setTitle("Add vehicle");
        AddVehicleFrame.setBackground(Color.gray);
        AddVehicleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AddVehicleFrame.setSize(width, height);
        AddVehicleFrame.setLayout(null);

        JPanel addVehiclePanel = new JPanel();
        addVehiclePanel.setSize(width, height);
        addVehiclePanel.setBackground(Color.gray);

        AddVehicleClass addVehicle = new AddVehicleClass(addVehiclePanel, this.vehicleList);
        addVehicle.addVehicle();
        AddVehicleFrame.add(addVehiclePanel);
        AddVehicleFrame.setVisible(true);
    }

    private void openReportWindow() {
        System.out.println("the Tread in openReportWindow now is:" + Thread.currentThread().getName() + "\n");
        // Open the current inventory report window as a separate dialog
        JFrame reportFrame = new JFrame();
        reportFrame.setResizable(false);
        reportFrame.setTitle("Current inventory report");
        reportFrame.setBackground(Color.gray);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reportFrame.setSize(width, height);
        reportFrame.setLayout(null);

        reportFrame.setBackground(Color.gray);
        reportPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        reportPanel.setSize(width, height);

        updateVehicleList();
        if (reportFrameCounter.get() == 0) {
            Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
            Enumeration<AbstractButton> buttons = btnAndBox.first.getElements();
            while (buttons.hasMoreElements()) {
                AbstractButton button = buttons.nextElement();
                button.setVisible(false);
            }
            reportPanel.add(btnAndBox.second, BorderLayout.CENTER);
            reportFrameCounter.incrementAndGet(); // like ++
        }
        inventoryPanelList.add(reportPanel);

        reportFrame.add(reportPanel);
        reportFrame.setVisible(true);
    }

    private void openTestDriveWindow() {  // Open the test drive window as a separate frame
        System.out.println("the Tread in openTestDriveWindow now is:" + Thread.currentThread().getName());
        JFrame testDriveFrame = new JFrame();
        testDriveFrame.setResizable(false);
        testDriveFrame.setTitle("Test drive");
        testDriveFrame.setBackground(Color.gray);
        testDriveFrame.setSize(width, height);
        testDriveFrame.setLayout(null);

        JPanel testDrivePanel = new JPanel();
        testDrivePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        testDrivePanel.setBackground(Color.gray);
        testDrivePanel.setSize(width, height);

        Tuple<JTextField, Box> kmTextAndBox = getInfo.kmLabel();
        testDrivePanel.add(kmTextAndBox.second, BorderLayout.NORTH);

        Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
        testDrivePanel.add(btnAndBox.second, BorderLayout.CENTER);
        testDrivePanel.add(addKmButton(kmTextAndBox.first, btnAndBox.first), BorderLayout.SOUTH);
        testDrivePanelList.add(testDrivePanel);
        testDriveFrame.addWindowListener(new WindowAdapter() { // Listens to her pressing the X button on the frame
            @Override
            public void windowClosing(WindowEvent e) { // Remove the frame from the List and Call dispose() to close the frame
                testDrivePanelList.remove(testDrivePanel);
                testDriveFrame.dispose();
            }
        });

        testDriveFrame.add(testDrivePanel);
        testDriveFrame.setVisible(true);
    }

    private void openBuyVehiclesWindow() { // Open the test buy window as a separate frame
        System.out.println("the Tread in openBuyVehiclesWindow now is:" + Thread.currentThread().getName() + "\n");
        buyVehiclesFrame = new BuyVehicleFrame();
        buyVehiclesFrame.setTitle("Buy vehicles");
        buyVehiclesFrame.setResizable(false);
        buyVehiclesFrame.setBackground(Color.gray);

        buyVehiclesFrame.setSize(width, height);
        buyVehiclesFrame.setLayout(null);

        JPanel buyVehiclesPanel = new JPanel();
        buyVehiclesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buyVehiclesPanel.setBackground(Color.gray);
        buyVehiclesPanel.setSize(width, height);

        Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
        buyVehiclesPanel.add(btnAndBox.second, BorderLayout.CENTER);

        buyVehiclesPanel.add(buyButton(btnAndBox.first), BorderLayout.SOUTH);
        buyPanelList.add(buyVehiclesPanel);
        buyVehiclesFrame.addWindowListener(new WindowAdapter() { // Listens to her pressing the X button on the frame
            @Override
            public void windowClosing(WindowEvent e) { // Remove the frame from the buyPanelList and Call dispose() to close the frame
                buyPanelList.remove(buyVehiclesPanel);
                buyVehiclesFrame.dispose();
            }
        });
        buyVehiclesFrame.add(buyVehiclesPanel);
        buyVehiclesFrame.setVisible(true);
    }

    private JButton buyButton(ButtonGroup buttonGroup) { // need to get -> ButtonModel selectedButton = buttonVehiclesGroup.getSelection();
        System.out.println("the Tread in buyButton now is:" + Thread.currentThread().getName() + "\n");
        JButton buyButton = new JButton("Buy Vehicle"); // Create the Buy Vehicle button
        buyButton.setBounds(670, 500, 105, 65);
        buyButton.setBackground(Color.white);
        buyButton.addActionListener(e -> {
            ButtonModel selectedButton = buttonGroup.getSelection();
            if (selectedButton == null) {
                JOptionPane.showMessageDialog(null, "Please select a vehicle");
                return;
            }
            String actionCommand = buttonGroup.getSelection().getActionCommand(); // Get the action command of the selected radio button
            int index = Integer.parseInt(actionCommand); // Convert the action command to an integer index
            VehicleColorAndStatusDecorator vehicleColorAndStatusDecorator = (VehicleColorAndStatusDecorator) vehicleList.get(index);
            Vehicle vehicle = (Vehicle) vehicleColorAndStatusDecorator.makeVehicleDecorated().first;
            if (vehicle.getOnTestDrive()) {
                int option = JOptionPane.showConfirmDialog(null, "This vehicle is on a test drive. Would you like to wait for him to return to complete the purchase?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    vehicle.getCarsObserverList().add(buyVehiclesFrame);
                } else JOptionPane.showMessageDialog(null, "select other vehicle");
            }
            else {
                synchronized (getLockObject(vehicle)) { // Prevents other Threads from accessing this vehicle in the array
                    new Thread(() -> { // anonymous Tread
                        vehicleColorAndStatusDecorator.updateStatus(VehicleColorAndStatusDecorator.currentStatus.ON_BUYING_PROCESS);
                        int sleepTime = (int) (Math.random() * 5) + 10; // Sleep for a random time between 5 and 10 seconds
                        try {
                            Thread.sleep(sleepTime * 1000L);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        // Show confirmation window
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this vehicle?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            if (vehicleList.get(index) != null) {
                                vehicleList.remove(index); // Remove the vehicle from the vector
                                updatingDataPleaseWait();
                                JOptionPane.showMessageDialog(null, "Vehicle bought successfully"); // Show a message to the user
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "The purchase is cancelled");
                            vehicleColorAndStatusDecorator.updateStatus(VehicleColorAndStatusDecorator.currentStatus.IN_STOCK);
                        }
                    }).start();
                }
            }
        });
        return buyButton;
    }

    private JButton addKmButton(JTextField kmText, ButtonGroup buttonGroup) {
        System.out.println("the Tread in addKmButton now is:" + Thread.currentThread().getName() + "\n");
        JButton setKmButton = new JButton("Add kilometer"); // Create the Buy Vehicle button
        setKmButton.setBounds(500, 900, 85, 100);
        setKmButton.setBackground(Color.white);
        setKmButton.addActionListener(e -> {
            if (kmText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter kilometer to add");
                return;
            }
            ButtonModel selectedButton = buttonGroup.getSelection();
            if (selectedButton == null) {
                JOptionPane.showMessageDialog(null, "Please select a vehicle");
                return;
            }
            String actionCommand = buttonGroup.getSelection().getActionCommand(); // Get the action command of the selected radio button
            int index = Integer.parseInt(actionCommand); // Convert the action command to an integer index
            VehicleColorAndStatusDecorator vehicleColorAndStatusDecorator = (VehicleColorAndStatusDecorator) vehicleList.get(index);
            Vehicle vehicle = (Vehicle) vehicleColorAndStatusDecorator.makeVehicleDecorated().first;
            if (vehicle.getOnTestDrive()) {
                JOptionPane.showMessageDialog(null, "This vehicle is already on a test drive");
                return;
            }
            Runnable task = () -> {
                vehicleColorAndStatusDecorator.updateStatus(VehicleColorAndStatusDecorator.currentStatus.ON_TEST_DRIVE);
                int km = Integer.parseInt(kmText.getText());
                Vehicle.addToGlobalKilometer(km);
                vehicle.addToKm(km);
                JOptionPane.showMessageDialog(null, "The kilometer has been successfully added"); // Show a message to the user
                vehicle.setOnTestDrive(true);
                try {
                    Thread.sleep(100L * Integer.parseInt(kmText.getText())); // Sleep the thread for the entered distance multiplied by 100
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    vehicle.setOnTestDrive(false);
                    vehicleColorAndStatusDecorator.updateStatus(VehicleColorAndStatusDecorator.currentStatus.IN_STOCK);
                }
            };
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) testDrivePool; //  to get the number of active threads and see they not mor for the size of my testDrivePool
            if (threadPoolExecutor.getActiveCount() < threadPoolExecutor.getMaximumPoolSize()) {
                testDrivePool.execute(task); // mace run of the task
                vehicle.getCarsObserverList().forEach(carsObserver -> carsObserver.update(vehicle, vehicleList, index)); // update each observer that this vehicle is now available
                updateVehicleList();
            } else JOptionPane.showMessageDialog(null, "all employees are on a test drive, there are no compromises to go on a test drive at the moment "); // Show a message to the user
        });
        return setKmButton;
    }

    private void updateVehicleList() {
        System.out.println("the Tread in updateVehicleList now is:" + Thread.currentThread().getName() + "\n");
        buyPanelList.forEach(myPanel -> { // like for i = 0 to size
            if (myPanel != null) {
                myPanel.removeAll();
                myPanel.revalidate();
                myPanel.repaint();
                Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
                myPanel.add(btnAndBox.second, BorderLayout.CENTER);
                myPanel.add(buyButton(btnAndBox.first));
            }
        });
        inventoryPanelList.forEach(myPanel -> { // like for i = 0 to size
            if (myPanel != null) {
                myPanel.removeAll();
                myPanel.revalidate();
                myPanel.repaint();
                Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
                Enumeration<AbstractButton> buttons = btnAndBox.first.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    button.setVisible(false);
                }
                myPanel.add(btnAndBox.second, BorderLayout.CENTER);
            }
        });
        testDrivePanelList.forEach(myPanel -> { // like for i = 0 to size
            if (myPanel != null) {
                myPanel.removeAll();
                myPanel.revalidate();
                myPanel.repaint();
                Tuple<JTextField, Box> kmTextandBox = getInfo.kmLabel();
                myPanel.add(kmTextandBox.second, BorderLayout.NORTH);

                Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
                myPanel.add(btnAndBox.second, BorderLayout.CENTER);
                myPanel.add(addKmButton(kmTextandBox.first, btnAndBox.first), BorderLayout.SOUTH);
            }
        });
    }

    private Object getLockObject(Vehicle vehicle) {
        return vehicle;
    } // Create and return a separate lock object for the given vehicle

    public void showOrHide(boolean x) {
        menuFrame.setVisible(x);
    }

    private void resetMenuPanel() {
        if (menuPanel != null) {
            menuPanel.removeAll();
            menuPanel.revalidate();
            menuPanel.repaint();
        }
    }

    private void resetPanel(JPanel panel) {
        if (panel != null) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
        }
    }

    private boolean vehicleListIsNotEmpty() {
        if (vehicleList.size() == 0) {
            JOptionPane.showMessageDialog(menuFrame, "No vehicles in stock.");
            goToMenu();
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // menu
        if (e.getSource() == menuButton) {
            goToMenu();
        }
    }

    private void checkWhatPanel(JPanel panel, Box boxContainer) {
        if (panel == this.menuPanel) {
            JPanel tempPanel = new JPanel();
            tempPanel.setBackground(Color.black);
            tempPanel.setBounds(220, 50, width / 2, height / 2);
            tempPanel.add(boxContainer, BorderLayout.CENTER);
            panel.add(tempPanel);
        } else panel.add(boxContainer, BorderLayout.CENTER);
    }

    public void resetKm(JFrame frame) {
        if (vehicleList.size() == 0) {
            JOptionPane.showMessageDialog(null, "No vehicle in stock");
        } else {
            Vehicle.setGlobalKilometer(0);
//            for (Vehicle vehicle : vehicleList) vehicle.setKm(0);
            for (int i = 0 ; i < vehicleList.size(); ++i) {
                Vehicle vehicles = (Vehicle) vehicleList.get(i);
                vehicles.setKm(0);
            }
            int sleepTime = (int) (Math.random() * 3) + 5;
            try {
                Thread.sleep(100L * sleepTime); // Sleep the thread for the entered distance multiplied by 100
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "reset Km succeeded");
            updatingDataPleaseWait();
        }
    }

    public synchronized void openChangeAllFlagsWindow() {
        if (isChangeFlagsWindowOpen) {
            JOptionPane.showMessageDialog(null, "Change flags window is already open.");
            return;
        }
        isChangeFlagsWindowOpen = true;
        JFrame flagFrame = new JFrame();
        flagFrame.setTitle("Change all flags");
        flagFrame.setBackground(Color.gray);
        flagFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        flagFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                isChangeFlagsWindowOpen = false; // Reset the flag when the window is closed
            }
        });
        flagFrame.setSize(width, height);
        flagFrame.setLayout(null);

        JPanel flagsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        flagsPanel.setBackground(new Color(255, 255, 255, 65));
        flagsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // add a 10-pixel border
        flagsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        flagsPanel.add(getFlagForFile("src/pictures/israel.png", E_country.Israel));
        flagsPanel.add(getFlagForFile("src/pictures/Greece.png", E_country.Greece));
        flagsPanel.add(getFlagForFile("src/pictures/UnitedStates.png", E_country.UnitedStates));
        flagsPanel.add(getFlagForFile("src/pictures/Italy.png", E_country.Italy));
        flagsPanel.add(getFlagForFile("src/pictures/Somalia.png", E_country.Somalia));
        flagsPanel.add(getFlagForFile("src/pictures/ Germany.png", E_country.Germany));
        flagsPanel.add(getFlagForFile("src/pictures/Pirates.png", E_country.Pirates));


        changeAllFlagsBtn(flagsPanel);
        flagsPanel.setBounds(0, 0, width, height);
        flagFrame.add(flagsPanel);
        flagFrame.setVisible(true);
    }

    private void changeAllFlagsBtn(JPanel panel) {  // change all the flags
        final boolean[] flag = {false};
        JButton resetFlagBtn = new JButton("Change flags");
        resetFlagBtn.setBounds(200, 200, 190, 50);
        resetFlagBtn.setBackground(new Color(255, 255, 255, 255));
        panel.add(resetFlagBtn);
        resetFlagBtn.addActionListener(e -> {
            JRadioButton selectedButton = null;
            Enumeration<AbstractButton> buttons = flagButtonGroup.getElements();
            while (buttons.hasMoreElements()) {
                JRadioButton button = (JRadioButton) buttons.nextElement();
                if (button.isSelected()) {
                    selectedButton = button;  // Get the currently selected radio button
                    break;
                }
            }
            if (selectedButton != null) {  // If a radio button is selected, set the flags for all water vehicles
                E_country selectedCountry = E_country.valueOf(selectedButton.getText());
                for (Ivehicle value : vehicleList) {
                    VehicleColorAndStatusDecorator vehicleColorAndStatusDecorator = (VehicleColorAndStatusDecorator) value;
                    Vehicle vehicle = (Vehicle) vehicleColorAndStatusDecorator.makeVehicleDecorated().first;
                    if ((vehicle instanceof WaterVehicle)) {
                        ((WaterVehicle) vehicle).setCountryFlag(selectedCountry.name());
                        flag[0] = true;
                    }
                }
                if (flag[0]) {
                    JOptionPane.showMessageDialog(null, "Flags have been successfully reset.");
                    updatingDataPleaseWait();
                } else
                    JOptionPane.showMessageDialog(null, "No marine vehicles in stock.");
                goToMenu();
            }
        });
    }

    private void goToMenuButton(JPanel panel) {
        menuButton = new JButton("Go to menu.");
        menuButton.setBounds(49, 450, 90, 50);
        menuButton.setBackground(new Color(255, 255, 255, 255));
        menuButton.addActionListener(this);
        panel.add(menuButton);
    }

    private Tuple<ButtonGroup, Box> printAllVehiclesPictures() {
        ButtonGroup buttonGroup = new ButtonGroup();
        System.out.println("the Tread in printAllVehiclesPictures now is:" + Thread.currentThread().getName());
        Box box = Box.createHorizontalBox();
        box.add(Box.createGlue());
        box.setBounds(0, 0, width, height);
        box.add(printAllLandVehiclesPictures(buttonGroup));
        box.add(Box.createHorizontalStrut(50)); // Add a 50-pixel gap
        box.add(printAllWaterVehiclesPictures(buttonGroup));
        box.add(Box.createHorizontalStrut(50)); // Add another 50-pixel gap
        box.add(printAllAirVehiclesPictures(buttonGroup));
        return new Tuple<>(buttonGroup, box);
    } // Arranges the pictures of the vehicles in columns

    private Box printAllLandVehiclesPictures(ButtonGroup buttonGroup) {
        System.out.println("the Tread in printAllLandVehiclesPictures now is:" + Thread.currentThread().getName());
        boolean flag = false;
        Box box = Box.createVerticalBox();
        ImageIcon imageIcon;
        JLabel title = new JLabel("All the land vehicles :");
        title.setForeground(Color.black);
        box.add(title);
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < vehicleList.size(); ++i) {
            VehicleColorAndStatusDecorator vehicleColorAndStatusDecorator = (VehicleColorAndStatusDecorator) vehicleList.get(i);
            Ivehicle vehicle =  vehicleColorAndStatusDecorator.makeVehicleDecorated().first;
            if (vehicle instanceof LandVehicle || vehicle instanceof iLandVehicle) {
                counter.incrementAndGet();
                flag = true;
                imageIcon = vehicle.getVehiclesImage();
                JRadioButton radioButton = new JRadioButton("Option " + counter); // Create a radio button
                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
                buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
                JLabel imageLabel = new JLabel(imageIcon);
                String tooltipText =  vehicleColorAndStatusDecorator.toString(); // Store tooltip text in final variable
                imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
                vehicleColorAndStatusDecorator.setTooltipObservable(imageLabel);

                Color frameColor = vehicleColorAndStatusDecorator.getColor();
                imageLabel.setBorder(BorderFactory.createLineBorder(frameColor, 3)); // Set the frame color

                Box radioButtonBox = Box.createVerticalBox(); // Create a box for the radio button and the image label
                radioButtonBox.add(radioButton);
                radioButtonBox.add(imageLabel);
                box.add(Box.createVerticalStrut(12)); // Add a 12-pixel gap
                box.add(radioButtonBox);
            }
        }
        if (!flag) {
            JLabel label = new JLabel("No land Vehicles on stock");
            box.add(label);
        }
        return box;
    }

    private Box printAllAirVehiclesPictures(ButtonGroup buttonGroup) {
        boolean flag = false;
        Box box = Box.createVerticalBox();
        ImageIcon imageIcon;
        JLabel title = new JLabel("All the air vehicles :");
        title.setForeground(Color.black);
        box.add(title);
        AtomicInteger cont = new AtomicInteger(0);
        for (int i = 0; i < vehicleList.size(); ++i) {
            VehicleColorAndStatusDecorator vehicleColorAndStatusDecorator = (VehicleColorAndStatusDecorator) vehicleList.get(i);
            Vehicle vehicle = (Vehicle) vehicleColorAndStatusDecorator.makeVehicleDecorated().first;
            if (vehicle instanceof AirVehicle || vehicle instanceof iAirVehicle) {
                cont.incrementAndGet(); // like ++
                flag = true;
                imageIcon = vehicle.getVehiclesImage();
                JRadioButton radioButton = new JRadioButton("Option" + cont); // Create a radio button
                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
                buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
                JLabel imageLabel = new JLabel(imageIcon);
                String tooltipText = vehicleColorAndStatusDecorator.toString(); // Store tooltip text in final variable
                imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
                vehicleColorAndStatusDecorator.setTooltipObservable(imageLabel);

                Color frameColor = vehicleColorAndStatusDecorator.getColor();
                imageLabel.setBorder(BorderFactory.createLineBorder(frameColor, 3)); // Set the frame color

                Box radioButtonBox = Box.createVerticalBox(); // Create a box for the radio button and the image label
                radioButtonBox.add(radioButton);
                radioButtonBox.add(imageLabel);
                box.add(Box.createVerticalStrut(12)); // Add a 12-pixel gap
                box.add(radioButtonBox);
            }
        }
        if (!flag) {
            JLabel label = new JLabel("No air vehicles on stock");
            box.add(label);
        }
        return box;
    }

    private Box printAllWaterVehiclesPictures(ButtonGroup buttonGroup) {
        boolean flag = false;
        Box box = Box.createVerticalBox();
        ImageIcon imageIcon;
        JLabel title = new JLabel("All the water vehicles :");
        title.setForeground(Color.black);
        box.add(title);
        AtomicInteger cont = new AtomicInteger(0);
        for (int i = 0; i < vehicleList.size(); ++i) {
            VehicleColorAndStatusDecorator vehicleColorAndStatusDecorator = (VehicleColorAndStatusDecorator) vehicleList.get(i);
            Vehicle vehicle = (Vehicle) vehicleColorAndStatusDecorator.makeVehicleDecorated().first;
            if (vehicle instanceof WaterVehicle || vehicle instanceof iWaterVehicle) {
                cont.incrementAndGet();
                flag = true;
                imageIcon = vehicle.getVehiclesImage();
                JRadioButton radioButton = new JRadioButton("Option " + cont); // Create a radio button
                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
                buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
                JLabel imageLabel = new JLabel(imageIcon);
                String tooltipText = vehicleColorAndStatusDecorator.toString(); // Store tooltip text in final variable
                imageLabel.setToolTipText(tooltipText);
                vehicleColorAndStatusDecorator.setTooltipObservable(imageLabel);

                Color frameColor = vehicleColorAndStatusDecorator.getColor();
                imageLabel.setBorder(BorderFactory.createLineBorder(frameColor, 3)); // Set the frame color

                Box radioButtonBox = Box.createVerticalBox(); // Create a box for the radio button and the image label
                radioButtonBox.add(radioButton);
                radioButtonBox.add(imageLabel);
                box.add(Box.createVerticalStrut(12)); // Add a 12-pixel gap
                box.add(radioButtonBox);
            }
        }
        if (!flag) {
            JLabel label = new JLabel("No water Vehicles on stock");
            box.add(label);
        }
        return box;
    }

    private Box getFlagForFile(String filePath, E_country eCountry) {
        Box box = Box.createVerticalBox();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            bufferedImage = resizePic(bufferedImage, 100, 70);
            ImageIcon icon = new ImageIcon(bufferedImage);
            JLabel label = new JLabel(icon);
            box.add(label);
            JRadioButton radioFlagBtn = new JRadioButton(eCountry.name());
            flagButtonGroup.add(radioFlagBtn); // Add the radio button to the ButtonGroup
            radioFlagBtn.setBorder(BorderFactory.createEmptyBorder(0, 42, 0, 0));  // Add an EmptyBorder with 5 pixels of padding on the right side
            box.add(radioFlagBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    public void updatingDataPleaseWait() {
        System.out.println("the thread in updatingData now is: " + Thread.currentThread().getName());
        JOptionPane optionPane = new JOptionPane("Updating database... Please wait", JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Success"); // Create a dialog with the message

        // Set the dialog to be not closable by the user
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);

        // Remove the OK button from the dialog
        optionPane.setOptions(new Object[]{});

        // Create a javax.swing.Timer to automatically close the dialog after a specified delay
        int sleepTime = (int) (Math.random() * 3) + 5;
        javax.swing.Timer timer = new javax.swing.Timer(sleepTime * 1000, e -> {
            dialog.dispose(); // Close the dialog when the timer fires
        });
        timer.setRepeats(false); // Make the timer fire only once
        timer.start();

        dialog.setVisible(true); // Show the dialog to the user
        updateVehicleList();
    }

    public static BufferedImage resizePic(BufferedImage img, int newW, int newH) {  // Resize the image as needed
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } // resize the size of image

    private boolean isContains(Vehicle vehicle) {
        return indexOf(vehicle) != -1;
    }
    private int indexOf(Vehicle vehicle) {
        int ans = -1;
        for (int i = 0; i < vehicleList.size(); i++) {
            if (this.vehicleList.get(i).equals(vehicle)) {
                ans = i;
                break;
            }
        }
        return ans;
    }
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        showOrHide(true);
    }

    public JPanel getMenuPanel() {
        return this.menuPanel;
    }

    public Vector<Ivehicle> getArrayList() {
        return this.vehicleList;
    }
    public ImageIcon getVehiclesImage() {
        return vehiclesImage;
    }

    public static Tuple<Integer, Integer> getWidthAndHeight() {
        return new Tuple<>(width, height);
    }

}

