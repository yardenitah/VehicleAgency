package Graphic;
import Vehicless.*;
import Vehicless.Vehicles;
import utils.Tuple;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class MyFrame extends JFrame implements ActionListener ,Runnable {
    private AtomicInteger reportFrameCounter = new AtomicInteger(0);

    List<JPanel> buyPanelList, inventoryPanelList, testDrivePanelList;
    private Box boxContainer;

    private JFrame frame, reportFrame, testDriveFrame, resetKmFrame, AddVehicleBoxWindow;
    private BuyVehicleFrame buyVehiclesFrame;
    private JPanel menuPanel, innerPanel, buyVehiclesPanel, reportPanel, testDrivePanel, resetKmPanel, AddVehicleBoxPanel;
    private boolean withTheWind, againstTheWind, civilian, military;
    private JTextField   LicenseText, maxKmText, maxOfPassengerText, maxSpeedText, lifeTimeText, numOfWheelsText, countryFlagText, averageLifeSpanText, avgConsumptionText;
    private JRadioButton withTheWindButton, againstTheWindButton, civilianButton, militaryButton, radioFlagBtn;
    private ButtonGroup flagButtonGroup, buttonVehiclesGroup, buttonVehiclesTestDriveGroup, buttonVehiclesBuyGroup;
    private JLabel waterTypeLabel, typeLabel, modelLabel, SubmodelLabel, LicenseLabel, maxKmLabel, maxOfPassengerLabel, maxSpeedLabel, lifeTimeLabel, numOfWheelsLabel, countryFlagLabel, avgConsumptionLabel, averageLifeSpanLabel, kmLabel;
    private JComboBox<String> vehicleListBox, waterVehicleBox, airVehicleBox, landVehicleBox;
    private static JButton menuButton, buyVehicles, testDrive, resetkm, changeFlagsn, addVehicle, addVehicleBtn, resetFlagBtn;
    private static JMenuItem currentInventoryReportBtn;
    List<Vehicles> arr;
    private ReadWriteLock landVehicleLock, waterVehicleLock, airVehicleLock;
    private Thread buyTrd, testDriveTrd, addVehicleTrd, resetKmTrd, flagTrd;
    ImageIcon vehiclesImage;


    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        showOrHide(true);
    }

    private static enum VehicleType {
        Jeep, Frigate, Bicycle, CruiseShip, PlayGlider, SpyGlider, Amphibious, HybridPlane, ElectricBicycle
    }

    private static enum VehicleCategory {
        Land, Water, Air, Nothing
    }

    private static enum Country {
        Greece, Israel, Somalia, Italy, UnitedStates, Germany, Pirates
    }
    private boolean isChangeFlagsWindowOpen = false; // Flag variable to track if the window is open

    ExecutorService pool;
    private Image placeHolderImage;
    private boolean imageSelcted;
    private final int width = 800, height = 600;

    JTabbedPane testDriveTab;

    public MyFrame() {
        this.arr = new ArrayList<>(); // make ArrayList work with threads safely
        vehiclesImage = new ImageIcon();
        imageSelcted = false;
        flagButtonGroup = new ButtonGroup();
//        buttonVehiclesGroup = new ButtonGroup();
        frame = new JFrame();
        menuPanel = new JPanel();
        innerPanel = new JPanel();
        menuPanel.setLayout(null);
        innerPanel.setBackground(new Color(0, 0, 0, 224));
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setSize(width / 2, height / 2);
        frame.setTitle("My vehicleAgency");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menuPanel);
        frame.setResizable(false);
        menuPanel.setLayout(null);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        menuPanel.setBackground(new Color(0, 0, 0, 224));
        reportPanel = new JPanel();
        landVehicleLock = new ReentrantReadWriteLock();
        waterVehicleLock = new ReentrantReadWriteLock();
        airVehicleLock = new ReentrantReadWriteLock();
        buyPanelList = new ArrayList<>();
        testDrivePanelList = new ArrayList<>();
        inventoryPanelList = new ArrayList<>();

        addVehicleBox();
    }


    private void goToMenu() { // menu
        resetMenuPanel();
        resetInnerPanel();
        boxContainer = Box.createHorizontalBox();
        boxContainer.setSize(width / 7, height / 7);
        goToMenuButton(this.menuPanel);

        buyVehicles = new JButton("Buy vehicles");
        buyVehicles.setBounds(290, 70, 250, 50);
        menuPanel.add(buyVehicles);
        buyVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
                    goToMenu();
                } else {
                    buyTrd = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            openBuyVehiclesWindow();
                        }
                    });
                    buyTrd.start();
                }
            }
        }); // buy vehicle case

        testDrive = new JButton("Test drive");
        testDrive.setBounds(290, 140, 250, 50);
        menuPanel.add(testDrive);
        testDrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
                    goToMenu();
                } else {
                    testDriveTrd = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            openTestDriveWindow();
                        }
                    });
                    testDriveTrd.start();
                }
            }
        }); // test drive case

        resetkm = new JButton("Reset kilometer");
        resetkm.setBounds(290, 210, 250, 50);
        menuPanel.add(resetkm);
        resetkm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
                } else {
                    resetKmTrd = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            resetKmFrame = new JFrame();
                            resetKmFrame.setTitle("reset kilometer for all vehicles");
                            resetKmFrame.setResizable(false);
                            resetKmFrame.setBackground(Color.gray);
                            resetKmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            resetKmFrame.setSize(width, height);
                            resetKmFrame.setLayout(null);

                            resetKmPanel = new JPanel();
                            resetKmPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
                            resetKmPanel.setBackground(Color.gray);
                            resetKmPanel.setSize(width, height);
                            resetKm(resetKmFrame);
                            resetKmFrame.add(resetKmPanel);
                            resetKmPanel.setVisible(true);
                        }
                    });
                    resetKmTrd.start();
                }
            }
        }); // reset km case

        changeFlagsn = new JButton("Change flags");
        changeFlagsn.setBounds(290, 280, 250, 50);
        menuPanel.add(changeFlagsn);
        changeFlagsn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
                } else {
                    flagTrd = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            openChangeAllFlagsWindow();
                        }
                    });
                    flagTrd.start();
                }
            }
        }); // Change flags case

        addVehicle = new JButton("Add vehicle");
        addVehicle.setBounds(290, 350, 250, 50);
        menuPanel.add(addVehicle);
        addVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicleTrd = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openAddVehicleBoxWindow();
                    }
                });
                addVehicleTrd.start();
            }
        }); // add vehicle case

        JMenuBar menuBar = new JMenuBar();
        menuBar.setForeground(Color.white);
        menuBar.setForeground(Color.blue);
        frame.setJMenuBar(menuBar);

        JMenu reportMenu = new JMenu("Current inventory report");
        menuBar.add(reportMenu);

        currentInventoryReportBtn = new JMenuItem("Viewing inventory");
        reportMenu.add(currentInventoryReportBtn);
        currentInventoryReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
                    goToMenu();
                } else {
                    openReportWindow();
                }
            }
        }); // open anew option to see the inventory in app left side on menu
    } // menu

    private void openAddVehicleBoxWindow() {
        System.out.println("the Tread in openAddVehicleBoxWindow now is:" + Thread.currentThread().getName() + "\n");

        AddVehicleBoxWindow = new JFrame();
        AddVehicleBoxWindow.setResizable(false);
        AddVehicleBoxWindow.setTitle("Add vehicle");
        AddVehicleBoxWindow.setBackground(Color.gray);
        AddVehicleBoxWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AddVehicleBoxWindow.setSize(width, height);
        AddVehicleBoxWindow.setLayout(null);

        AddVehicleBoxPanel = new JPanel();
        AddVehicleBoxPanel.setSize(width, height);
        AddVehicleBoxPanel.setBackground(Color.gray);

        typeLabel = new JLabel("Please select vehicle type :");
        typeLabel.setForeground(new Color(255, 255, 255, 255));
        typeLabel.setBounds(45, 20, 180, 100);
        AddVehicleBoxPanel.add(typeLabel);

        vehicleListBox = new JComboBox<>(new Vector<>(List.of("Land Vehicle", "Water Vehicle", "Air Vehicle")));
        vehicleListBox.setBounds(235, 50, 150, 45);
        vehicleListBox.setBackground(new Color(255, 255, 255, 255));
        vehicleListBox.setForeground(Color.blue);
        vehicleListBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle(AddVehicleBoxPanel);
            }
        });
        AddVehicleBoxPanel.add(vehicleListBox);
        AddVehicleBoxWindow.add(AddVehicleBoxPanel);
        AddVehicleBoxWindow.setVisible(true);
    }

    private void openReportWindow() {
        System.out.println("the Tread in openReportWindow now is:" + Thread.currentThread().getName() + "\n");
        // Open the current inventory report window as a separate dialog
        reportFrame = new JFrame();
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
            reportPanel.add(printAllVehiclesPictures(), BorderLayout.CENTER);
            reportFrameCounter.incrementAndGet(); // like ++
        }
        inventoryPanelList.add(reportPanel);

//        Enumeration<AbstractButton> buttons = buttonVehiclesGroup.getElements();
//        while (buttons.hasMoreElements()) {
//            AbstractButton button = buttons.nextElement();
//            button.setVisible(false);
//        }
        reportFrame.add(reportPanel);
        reportFrame.setVisible(true);
    }

    private void openTestDriveWindow() {
        System.out.println("the Tread in openTestDriveWindow now is:" + Thread.currentThread().getName());
        // Open the test drive window as a separate dialog
        testDriveFrame = new JFrame();
        testDriveFrame.setResizable(false);
        testDriveFrame.setTitle("Test drive");
        testDriveFrame.setBackground(Color.gray);
        testDriveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testDriveFrame.setSize(width, height);
        testDriveFrame.setLayout(null);

        testDrivePanel = new JPanel();
        testDrivePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        testDrivePanel.setBackground(Color.gray);
        testDrivePanel.setSize(width, height);

        Tuple<JTextField, Box> kmTextAndBox = kmLabel();
        // Add components to the test drive panel
        testDrivePanel.add(kmTextAndBox.second, BorderLayout.NORTH);
        testDrivePanel.add(printAllVehiclesPictures(), BorderLayout.CENTER);
        testDrivePanel.add(addKmButton(kmTextAndBox.first), BorderLayout.SOUTH);
        testDrivePanelList.add(testDrivePanel);


        testDriveFrame.add(testDrivePanel);
        testDriveFrame.setVisible(true);
    }

    private void openBuyVehiclesWindow() {
        System.out.println("the Tread in openBuyVehiclesWindow now is:" + Thread.currentThread().getName() + "\n");
//        Enumeration<AbstractButton> buttons = buttonVehiclesGroup.getElements();
//        while (buttons.hasMoreElements()) {
//            AbstractButton button = buttons.nextElement();
//            button.setVisible(true);
//        }
        // Open the test drive window as a separate dialog
        buyVehiclesFrame = new BuyVehicleFrame();
        buyVehiclesFrame.setTitle("Buy vehicles");
        buyVehiclesFrame.setResizable(false);
        buyVehiclesFrame.setBackground(Color.gray);
        buyVehiclesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyVehiclesFrame.setSize(width, height);
        buyVehiclesFrame.setLayout(null);

        buyVehiclesPanel = new JPanel();
        buyVehiclesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buyVehiclesPanel.setBackground(Color.gray);
        buyVehiclesPanel.setSize(width, height);
//        buttonVehiclesGroup = new ButtonGroup();
        buyVehiclesPanel.add(printAllVehiclesPictures(), BorderLayout.CENTER);
        buyVehiclesPanel.add(buyButton(), BorderLayout.SOUTH);
        buyPanelList.add(buyVehiclesPanel);

        buyVehiclesFrame.add(buyVehiclesPanel);
        buyVehiclesFrame.setVisible(true);
    }

    private JButton buyButton() {
        System.out.println("the Tread in buyButton now is:" + Thread.currentThread().getName() + "\n");
        JButton buyButton = new JButton("Buy Vehicle"); // Create the Buy Vehicle button
        buyButton.setBounds(670, 500, 105, 65);
        buyButton.setBackground(Color.white);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonModel selectedButton = buttonVehiclesGroup.getSelection();
                if (selectedButton == null) {
                    JOptionPane.showMessageDialog(null, "Please select a vehicle");
                    return;
                }
                String actionCommand = buttonVehiclesGroup.getSelection().getActionCommand(); // Get the action command of the selected radio button
                int index = Integer.parseInt(actionCommand); // Convert the action command to an integer index
                if (arr.get(index).getOnTestDrive()) {
                    int option = JOptionPane.showConfirmDialog(null, "This vehicle is on a test drive. Would you like to wait for him to return to complete the purchase?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        arr.get(index).getCarsObserverList().add(buyVehiclesFrame);
                    } else JOptionPane.showMessageDialog(null, "select nuder vehicle");
                } else {
                    synchronized (arr.get(index)) {
                        // Sleep for a random time between 5 and 10 seconds
                        int sleepTime = (int) (Math.random() * 3) + 5;
                        try {
                            Thread.sleep(sleepTime * 1000L);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        // Show confirmation window
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this vehicle?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            arr.remove(index); // Remove the vehicle from the array
                            JOptionPane.showMessageDialog(null, "Vehicle bought successfully"); // Show a message to the user
                            updateVehicleList();
                            updatingData(Thread.currentThread());
                            goToMenu();
                        }
                    }
                }
            }
        });
        return buyButton;
    }

    private void updateVehicleList() {
        System.out.println("the Tread in updateVehicleList now is:" + Thread.currentThread().getName() + "\n");

        buyPanelList.forEach(panel1 -> { // like for i = 0 to size
            if (panel1 != null) {
                panel1.removeAll();
                panel1.revalidate();
                panel1.repaint();
                panel1.add(printAllVehiclesPictures(), BorderLayout.CENTER);
                panel1.add(buyButton());
            }
        });
        inventoryPanelList.forEach(panel1 -> { // like for i = 0 to size
            if (panel1 != null) {
                panel1.removeAll();
                panel1.revalidate();
                panel1.repaint();
                panel1.add(printAllVehiclesPictures(), BorderLayout.CENTER);
            }
        });
        testDrivePanelList.forEach(panel1 -> { // like for i = 0 to size
            if (panel1 != null) {
                panel1.removeAll();
                panel1.revalidate();
                panel1.repaint();
                Tuple<JTextField, Box> kmTextandBox = kmLabel();
                panel1.add(kmTextandBox.second, BorderLayout.NORTH);
                panel1.add(printAllVehiclesPictures(), BorderLayout.CENTER);
                panel1.add(addKmButton(kmTextandBox.first), BorderLayout.SOUTH);
            }
        });
    }

    private JButton addKmButton(JTextField kmText) {
        System.out.println("the Tread in addKmButton now is:" + Thread.currentThread().getName() + "\n");

        JButton setKmButton = new JButton("Add kilometer"); // Create the Buy Vehicle button
        setKmButton.setBounds(500, 900, 85, 100);
        setKmButton.setBackground(Color.white);
        setKmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kmText.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "please enter kilometer to add");
                    return;
                }
                ButtonModel selectedButton = buttonVehiclesGroup.getSelection();
                if (selectedButton == null) {
                    JOptionPane.showMessageDialog(null, "Please select a vehicle");
                    return;
                }
                String actionCommand = buttonVehiclesGroup.getSelection().getActionCommand(); // Get the action command of the selected radio button
                int index = Integer.parseInt(actionCommand); // Convert the action command to an integer index

                synchronized (arr.get(index)) {
                    boolean isCategoryBusy = isCategoryBusy(arr.get(index));
                    if (isCategoryBusy) {
                        JOptionPane.showMessageDialog(null, "A vehicle from the same category is already on a test drive. Please select a different category.");
                    } else {
                        toLockOrOpenVehicleCategory(arr.get(index), true);
                        arr.get(index).addToKm(Integer.parseInt(kmText.getText()));
                        JOptionPane.showMessageDialog(null, "The kilometer has been successfully added"); // Show a message to the user
                        arr.get(index).setOnTestDrive(true);
                        new Thread(() -> { // anonymous Tread
                            try {
                                Thread.sleep(100L * Integer.parseInt(kmText.getText())); // Sleep the thread for the entered distance multiplied by 100
                                arr.get(index).getCarsObserverList().forEach(carsObserver -> carsObserver.update(arr.get(index), arr, index)); // update each observer that this vehicle is now available
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            } finally { // There is a possibility that while the vehicle was old, vehicles were added or deleted from the array And then we changed values ​​in the wrong position in the array.
                                arr.get(index).setOnTestDrive(false);
                                toLockOrOpenVehicleCategory(arr.get(index), false);
                            }
                        }).start();
                    }
                }

            }
        });
        return setKmButton;
    }

    private void toLockOrOpenVehicleCategory(Vehicles vehicle, boolean lockOrOpen) {
        System.out.println("the Tread in toLockOrOpenVehicleCategory now is:" + Thread.currentThread().getName() + "\n");
        if (vehicle instanceof LandVehicle) {
            landVehicleLock.writeLock().lock();
            try {
                LandVehicle.landVehicleBusy = lockOrOpen;
            } finally {
                landVehicleLock.writeLock().unlock();
            }
        }
        if (vehicle instanceof WaterVehicle) {
            waterVehicleLock.writeLock().lock();
            try {
                WaterVehicle.waterVehicleBusy = lockOrOpen;
            } finally {
                waterVehicleLock.writeLock().unlock();
            }
        }
        if (vehicle instanceof AirVehicle) {
            airVehicleLock.writeLock().lock();
            try {
                AirVehicle.airVehicleBusy = lockOrOpen;
            } finally {
                airVehicleLock.writeLock().unlock();
            }
        }
    }

    private boolean isCategoryBusy(Vehicles vehicle) {
        System.out.println("the Tread in isCategoryBusy now is:" + Thread.currentThread().getName() + "\n");

        if (vehicle instanceof iLandVehicle) {
            landVehicleLock.readLock().lock();
            try {
                return LandVehicle.landVehicleBusy;
            } finally {
                landVehicleLock.readLock().unlock();
            }
        }
        if (vehicle instanceof iWaterVehicle) {
            waterVehicleLock.readLock().lock();
            try {
                return WaterVehicle.waterVehicleBusy;
            } finally {
                waterVehicleLock.readLock().unlock();
            }
        }
        if (vehicle instanceof iAirVehicle) {
            airVehicleLock.readLock().lock();
            try {
                return AirVehicle.airVehicleBusy;
            } finally {
                airVehicleLock.readLock().unlock();
            }
        }
        return false;
    }

    public void showOrHide(boolean x) {
        frame.setVisible(x);
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

    private void resetInnerPanel() {
        if (innerPanel != null) {
            innerPanel.removeAll();
            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }

    private void resetAllTextFields() {
        if (this.boxContainer != null)
            boxContainer.removeAll();
    }

    private void addVehicleBox() {
        System.out.println("the Tread in addVehicleBox now is:" + Thread.currentThread().getName() + "\n");

        resetAllTextFields();
        resetInnerPanel();
        //TODO
        resetMenuPanel();
        typeLabel = new JLabel("Please select vehicle type :");
        typeLabel.setForeground(new Color(255, 255, 255, 255));
        typeLabel.setBounds(45, 20, 180, 100);
        menuPanel.add(typeLabel);

        vehicleListBox = new JComboBox<>(new Vector<>(List.of("Land Vehicle", "Water Vehicle", "Air Vehicle")));
        vehicleListBox.setBounds(235, 50, 150, 45);
        vehicleListBox.setBackground(new Color(255, 255, 255, 255));
        vehicleListBox.setForeground(Color.blue);
        vehicleListBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle(menuPanel);
            }
        });
        menuPanel.add(vehicleListBox);
        goToMenuButton(this.menuPanel);
    }

    private void addVehicle(JPanel panel) {
        System.out.println("the Tread in addVehicle now is:" + Thread.currentThread().getName() + "\n");

        int option = vehicleListBox.getSelectedIndex();

        switch (option) {
            case 0 -> {
                vehicleListBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addLandVehicleBox(panel);
                    }
                });

            }
            case 1 -> {
                vehicleListBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addWaterVehicleBox();
                    }
                });

            }
            case 2 -> {
                vehicleListBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addAirVehicleBox();
                    }
                });

            }
        }
    }

    private void addWaterVehicleBox() {
        resetMenuPanel();
        waterTypeLabel = new JLabel("What type of water vehicle :");
        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
        waterTypeLabel.setBounds(45, 20, 180, 100);
        menuPanel.add(waterTypeLabel);

        waterVehicleBox = new JComboBox<>(new Vector<>(List.of(" Frigate", "Cruise ship", "amphibious", "Hybrid plain")));
        waterVehicleBox.setBounds(235, 50, 150, 45);
        waterVehicleBox.setBackground(new Color(255, 255, 255, 255));
        waterVehicleBox.setForeground(Color.blue);
        waterVehicleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWaterVehicle();
            }
        });
        menuPanel.add(waterVehicleBox);
        goToMenuButton(this.menuPanel);
    }

    private void addAirVehicleBox() {
        resetMenuPanel();
        waterTypeLabel = new JLabel("What type of air vehicle :");
        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
        waterTypeLabel.setBounds(45, 20, 180, 100);
        menuPanel.add(waterTypeLabel);

        airVehicleBox = new JComboBox<>(new Vector<>(List.of("Play glider", "Spay glider", "Hybrid plane")));
        airVehicleBox.setBounds(228, 50, 150, 45);
        airVehicleBox.setBackground(new Color(255, 255, 255, 255));
        airVehicleBox.setForeground(Color.blue);
        airVehicleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAirVehicle();
            }
        });
        menuPanel.add(airVehicleBox);
        goToMenuButton(this.menuPanel);
    }

    private void addLandVehicleBox(JPanel panel) {
        System.out.println("the Tread in addLandVehicleBox now is:" + Thread.currentThread().getName() + "\n");
//        resetAllTextFields();
//        resetInnerPanel();
        resetPanel(panel);
        waterTypeLabel = new JLabel("What type of land vehicle :");
        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
        waterTypeLabel.setBounds(45, 20, 180, 100);
        panel.add(waterTypeLabel);

        landVehicleBox = new JComboBox<>(new Vector<>(List.of("Jeep", "Bicycle", "amphibious", "Hybrid Plain", "Electric bicycle")));
        landVehicleBox.setBounds(228, 50, 150, 45);
        landVehicleBox.setBackground(new Color(255, 255, 255, 255));
        landVehicleBox.setForeground(Color.blue);
        landVehicleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLandVehicle(panel);
            }
        });
        panel.add(landVehicleBox);
//        goToMenuButton(this.menuPanel);
    }

    private void addHybridPlain() {
        JLabel tmpLabel = new JLabel();
        tmpLabel.setText("enter Hybrid Plain data :");
        boxContainer.add(tmpLabel);
        boxContainer.add(civilianOrMilitaryRadioButtons());
        boxContainer.add(modelLabel().second);
        boxContainer.add(subMudelLabel().second);
        boxContainer.add(maxKmLabel());
        boxContainer.add(maxPassengersLabel());
        boxContainer.add(msxSpeedLabel());
        boxContainer.add(avgConsumptionLabel());
        boxContainer.add(averageLifeSpanLabel());
        boxContainer.add(numOfWheelsLabel());
        boxContainer.add(windRadioButtons());
        boxContainer.add(addPicture());
        boxContainer.add(addVehicleBtn(VehicleType.HybridPlane, new ArrayList<>())); // creat this object
        innerPanel.add(boxContainer, BorderLayout.CENTER);
        innerPanel.setBounds(220, 50, width / 2, (height / 2) + 190);
        menuPanel.add(innerPanel);
    }

    private void addAmphibious() {
        JLabel tmpLabel = new JLabel();
        tmpLabel.setText("enter amphibious data :");
        boxContainer.add(tmpLabel);
        Tuple<JTextField, Box> tuple = modelLabel();
        boxContainer.add(tuple.second);
        boxContainer.add(subMudelLabel().second);
        boxContainer.add(maxKmLabel());
        boxContainer.add(maxPassengersLabel());
        boxContainer.add(msxSpeedLabel());
        boxContainer.add(avgConsumptionLabel());
        boxContainer.add(numOfWheelsLabel());
        boxContainer.add(windRadioButtons());
        boxContainer.add(addPicture());
        boxContainer.add(addVehicleBtn(VehicleType.Amphibious, new ArrayList<>())); // creat this object
        innerPanel.add(boxContainer, BorderLayout.CENTER);
        innerPanel.setBounds(220, 50, width / 2, (height + 90) / 2);
        menuPanel.add(innerPanel);
    }

    private void addLandVehicle(JPanel panel) {
        resetPanel(panel);
        boxContainer = Box.createVerticalBox();
        boxContainer.setSize(width, height);
        goToMenuButton(this.menuPanel);
        JLabel tmpLabel = new JLabel();
        tmpLabel.setForeground(Color.WHITE);
        tmpLabel.setBackground(Color.cyan);
        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        int option = landVehicleBox.getSelectedIndex();
        switch (option) {
            case 0 -> { // Jeep
                tmpLabel.setText("enter Jeep data :");
                boxContainer.add(tmpLabel);
                boxContainer.add(modelLabel().second);
                boxContainer.add(subMudelLabel().second);
                boxContainer.add(maxKmLabel());
//                boxContainer.add(maxPassengersLabel());
                boxContainer.add(msxSpeedLabel());
                boxContainer.add(avgConsumptionLabel());
                boxContainer.add(averageLifeSpanLabel());
                boxContainer.add(addPicture());
                boxContainer.add(addVehicleBtn(VehicleType.Jeep, new ArrayList<>())); // creat this object
                innerPanel.add(boxContainer, BorderLayout.CENTER);
                innerPanel.setBounds(220, 50, width / 2, height / 2);
                panel.add(innerPanel);
            }
            case 1 -> { //Bicycle
                tmpLabel.setText("enter Bicycle data :");
                boxContainer.add(tmpLabel);
                Tuple<JTextField, Box> textAndBoxModel = modelLabel();
                boxContainer.add(textAndBoxModel.second);
                Tuple<JTextField, Box> textAndBoxSubModel = subMudelLabel();
                boxContainer.add(textAndBoxSubModel.second);
                boxContainer.add(addPicture());
                List<JTextField> list = new ArrayList<>();
                if (textAndBoxModel.first != null && textAndBoxSubModel.first != null) {
                    list = List.of(textAndBoxModel.first, textAndBoxSubModel.first);
                }else System.out.println("textAndBoxModel or textAndBoxSubModel if null");
                List<JTextField> textFieldsLiist = new ArrayList<>(list);
                boxContainer.add(addVehicleBtn(VehicleType.Bicycle, textFieldsLiist)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 3);
//                panel.add(innerPanel);
            }
            case 2 -> {  // amphibious
                addAmphibious();
            }
            case 3 -> {  // HybridPlain
                addHybridPlain();
            }
            case 4 -> { // ElectricBicycle
                tmpLabel.setText("enter Electric bicycle data :");
                boxContainer.add(tmpLabel);
                boxContainer.add(modelLabel().second);
                boxContainer.add(subMudelLabel().second);
                boxContainer.add(addPicture());
                boxContainer.add(addVehicleBtn(VehicleType.ElectricBicycle, new ArrayList<>())); // creat this object
                innerPanel.add(boxContainer, BorderLayout.CENTER);
                innerPanel.setBounds(220, 50, width / 2, height / 3);
                panel.add(innerPanel);
            }
        }
    }

    public void addAirVehicle() {
        resetMenuPanel();
        boxContainer = Box.createVerticalBox();
        boxContainer.setSize(width, height);
        goToMenuButton(this.menuPanel);
        JLabel tmpLabel = new JLabel();
        tmpLabel.setForeground(Color.WHITE);
        tmpLabel.setBackground(Color.cyan);
        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        int option = airVehicleBox.getSelectedIndex();

        switch (option) {
            case 0 -> { // Play glider
                tmpLabel.setText("enter Play glider data :");
                boxContainer.add(tmpLabel);
                boxContainer.add(maxKmLabel());
                boxContainer.add(averageLifeSpanLabel());
                boxContainer.add(addPicture());
                boxContainer.add(addVehicleBtn(VehicleType.PlayGlider, new ArrayList<>())); // creat this object
                innerPanel.add(boxContainer, BorderLayout.CENTER);
                innerPanel.setBounds(220, 50, width / 2, height / 2);
                menuPanel.add(innerPanel);
            }
            case 1 -> { // Spay glider
                tmpLabel.setText("enter Spay glider data :");
                boxContainer.add(tmpLabel);
                boxContainer.add(maxKmLabel());
                boxContainer.add(averageLifeSpanLabel());
                boxContainer.add(addPicture());
                boxContainer.add(addVehicleBtn(VehicleType.SpyGlider, new ArrayList<>())); // creat this object
                innerPanel.add(boxContainer, BorderLayout.CENTER);
                innerPanel.setBounds(220, 50, width / 2, height / 2);
                menuPanel.add(innerPanel);
            }
            case 2 -> { // hybridPlain
                addHybridPlain();
            }
        }
    }

    public void addWaterVehicle() {
        resetMenuPanel();
        boxContainer = Box.createVerticalBox(); // Makes all the boxes be in the middle
        boxContainer.setSize(width, height);
        goToMenuButton(this.menuPanel);
        JLabel tmpLabel = new JLabel();
        tmpLabel.setForeground(Color.WHITE);
        tmpLabel.setBackground(Color.cyan);
        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        int option = waterVehicleBox.getSelectedIndex();

        switch (option) {
            case 0 -> { // Frigate
                tmpLabel.setText("enter Frigate data :");
                boxContainer.add(tmpLabel);
                boxContainer.add(modelLabel().second);
                boxContainer.add(maxKmLabel());
                boxContainer.add(maxPassengersLabel());
                boxContainer.add(msxSpeedLabel());
                boxContainer.add(windRadioButtons());
                boxContainer.add(addPicture());
                boxContainer.add(addVehicleBtn(VehicleType.Frigate, new ArrayList<>())); // creat this object
                innerPanel.add(boxContainer, BorderLayout.CENTER);
                innerPanel.setBounds(220, 50, width / 2, height / 2);
                menuPanel.add(innerPanel);
            }
            case 1 -> { // Cruise ship
                tmpLabel.setText("enter Cruise ship data :");
                boxContainer.add(modelLabel().second);
                boxContainer.add(maxKmLabel());
                boxContainer.add(maxPassengersLabel());
                boxContainer.add(msxSpeedLabel());
                boxContainer.add(averageLifeSpanLabel());
                boxContainer.add(countryFlagLabel());
                boxContainer.add(addPicture());
                boxContainer.add(addVehicleBtn(VehicleType.CruiseShip, new ArrayList<>())); // creat this object
                innerPanel.add(boxContainer, BorderLayout.CENTER);
                innerPanel.setBounds(220, 50, width / 2, height / 2);
                menuPanel.add(innerPanel);
            }
            case 2 -> { // amphibious
                addAmphibious();
            }
            case 3 -> { // HybridPlain
                addHybridPlain();
            }
        }
    }

    public Box addPicture() { // to add cars image when user add car
        System.out.println("the Tread in addPicture() now is:" + Thread.currentThread().getName() + "\n");

        Box box = Box.createHorizontalBox();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/placeHolder.png"));
            placeHolderImage = bufferedImage;
            bufferedImage = resizePic(bufferedImage, 100, 70);
            vehiclesImage.setImage(bufferedImage);
            JLabel label = new JLabel(vehiclesImage);
            box.add(label);
            JButton addPicBtn = new JButton("Add Picture ");
            addPicBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new ImageFilter());
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    int option = fileChooser.showOpenDialog(frame);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            BufferedImage bufferedImage = ImageIO.read(file);
                            bufferedImage = resizePic(bufferedImage, 100, 70);
                            vehiclesImage.setImage(bufferedImage);
                            imageSelcted = true;
                        } catch (Exception x) {
                            x.printStackTrace();
                        }
                        label.setText("File Selected: " + file.getName());
                    } else {
                        label.setText("Open command canceled");
                    }
                }
            });
            box.add(addPicBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    private Tuple<JTextField, Box> modelLabel() {
        Box box = Box.createHorizontalBox();
        modelLabel = new JLabel("Model :");
        modelLabel.setForeground(Color.WHITE);
        modelLabel.setBackground(Color.cyan);
        box.add(modelLabel);

        JTextField modelText = new JTextField();
        box.add(modelText);
        box.setSize(150, 10);
        return new Tuple<>(modelText, box);
    }

    private Box numOfWheelsLabel() {
        Box box = Box.createHorizontalBox();
        numOfWheelsLabel = new JLabel("number of wheels :");
        numOfWheelsLabel.setForeground(Color.WHITE);
        numOfWheelsLabel.setBackground(Color.cyan);
        box.add(numOfWheelsLabel);

        numOfWheelsText = new JTextField();
        numOfWheelsText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    numOfWheelsText.setEditable(true);
                } else numOfWheelsText.setEditable(false);
            }
        });
        box.add(numOfWheelsText);
        box.setSize(150, 10);
        return box;
    }

    private Box maxKmLabel() {
        Box box = Box.createHorizontalBox();
        maxKmLabel = new JLabel("max Km :");
        maxKmLabel.setForeground(Color.WHITE);
        maxKmLabel.setBackground(Color.cyan);
        box.add(maxKmLabel);

        maxKmText = new JTextField();
        maxKmText.setBackground(new Color(255, 255, 255, 255));
        maxKmText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    maxKmText.setEditable(true);
                } else maxKmText.setEditable(false);
            }
        });
        box.add(maxKmText);
        box.setSize(150, 10);
        return box;
    }

    private Tuple<JTextField, Box> kmLabel() {
        System.out.println("the Tread in kmLabel now is:" + Thread.currentThread().getName());
        Box box = Box.createHorizontalBox();
        kmLabel = new JLabel("Enter the number of kilometers traveled :");
        kmLabel.setForeground(Color.WHITE);
        kmLabel.setBackground(Color.cyan);
        box.add(kmLabel);

        JTextField kmText = new JTextField();
        kmText.setBackground(Color.WHITE);
        kmText.setPreferredSize(new Dimension(180, kmText.getPreferredSize().height));
        kmText.setPreferredSize(new Dimension(kmText.getPreferredSize().width, 20));
        kmText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_DELETE) {
                    kmText.setEditable(true);
                } else kmText.setEditable(false);
            }
        });
        box.add(kmText);
        Tuple<JTextField, Box> ans = new Tuple<>(kmText, box);

        return ans;
    }

    private Tuple<JTextField, Box> subMudelLabel() {
        Box box = Box.createHorizontalBox();
        SubmodelLabel = new JLabel("Sub model :");
        SubmodelLabel.setForeground(Color.WHITE);
        SubmodelLabel.setBackground(Color.cyan);
        box.add(SubmodelLabel);

        JTextField submodelText = new JTextField();
        submodelText.setBackground(new Color(255, 255, 255, 255));
        box.add(submodelText);
        box.setSize(150, 10);
        return new Tuple<>(submodelText, box);
    }

    private Box msxSpeedLabel() {
        Box box = Box.createHorizontalBox();
        modelLabel = new JLabel("max speed :");
        modelLabel.setForeground(Color.WHITE);
        modelLabel.setBackground(Color.cyan);
        box.add(modelLabel);

        maxSpeedText = new JTextField();
        maxSpeedText.setBounds(310, 173, 169, 25);
        maxSpeedText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || key == KeyEvent.VK_DELETE) {
                    maxSpeedText.setEditable(true);
                } else maxSpeedText.setEditable(false);
            }
        });
        box.add(maxSpeedText);
        box.setSize(150, 10);
        return box;
    }

    private Box countryFlagLabel() {
        Box box = Box.createHorizontalBox();
        countryFlagLabel = new JLabel("Country flag :");
        countryFlagLabel.setForeground(Color.WHITE);
        countryFlagLabel.setBackground(Color.cyan);
        box.add(countryFlagLabel);

        countryFlagText = new JTextField();
        box.add(countryFlagText);
        box.setSize(150, 10);
        return box;
    }

    private Box maxPassengersLabel() {
        Box box = Box.createHorizontalBox();
        maxOfPassengerLabel = new JLabel("Max passengers :");
        maxOfPassengerLabel.setForeground(Color.WHITE);
        maxOfPassengerLabel.setBackground(Color.cyan);
        box.add(maxOfPassengerLabel);

        maxOfPassengerText = new JTextField();
        maxOfPassengerText.setBounds(310, 173, 169, 25);
        maxOfPassengerText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    maxOfPassengerText.setEditable(true);
                } else maxOfPassengerText.setEditable(false);
            }
        });
        box.add(maxOfPassengerText);
        box.setSize(150, 10);
        return box;
    }

    private Box averageLifeSpanLabel() {
        Box box = Box.createHorizontalBox();
        averageLifeSpanLabel = new JLabel("Average lifeSpan :");
        averageLifeSpanLabel.setForeground(Color.WHITE);
        averageLifeSpanLabel.setBackground(Color.cyan);
        box.add(averageLifeSpanLabel);

        averageLifeSpanText = new JTextField();
        averageLifeSpanText.setBackground(new Color(255, 255, 255, 255));
        averageLifeSpanText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    averageLifeSpanText.setEditable(true);
                } else averageLifeSpanText.setEditable(false);
            }
        });
        box.add(averageLifeSpanText);
        box.setSize(150, 10);
        return box;
    }

    public Box avgConsumptionLabel() {
        Box box = Box.createHorizontalBox();
        avgConsumptionLabel = new JLabel("Average Consumption :");
        avgConsumptionLabel.setForeground(Color.WHITE);
        avgConsumptionLabel.setBackground(Color.cyan);
        box.add(avgConsumptionLabel);

        avgConsumptionText = new JTextField();
        avgConsumptionText.setBackground(new Color(255, 255, 255, 255));
        avgConsumptionText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    avgConsumptionText.setEditable(true);
                } else avgConsumptionText.setEditable(false);
            }
        });
        box.add(avgConsumptionText);
        box.setSize(150, 10);
        return box;
    }

    private Box windRadioButtons() {
        Box box = Box.createHorizontalBox();
        withTheWindButton = new JRadioButton("with the wind");
        againstTheWindButton = new JRadioButton("against the wind");

        withTheWindButton.setBackground(Color.white);
        withTheWindButton.setForeground(Color.blue);

        againstTheWindButton.setBackground(Color.white);
        againstTheWindButton.setForeground(Color.blue);

        box.add(againstTheWindButton);
        box.add(withTheWindButton);

        withTheWindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withTheWind = true;
            }
        });
        againstTheWindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                againstTheWind = true;
            }
        });
        return box;
    }

    private Box civilianOrMilitaryRadioButtons() {
        Box box = Box.createHorizontalBox();
        civilianButton = new JRadioButton("civilian");
        militaryButton = new JRadioButton("military");

        civilianButton.setBackground(Color.white);
        civilianButton.setForeground(Color.blue);
        militaryButton.setForeground(Color.blue);

        box.add(civilianButton);
        box.add(militaryButton);
        civilianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                civilian = true;
            }
        });
        militaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                military = true;
            }
        });
        return box;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // menu
        resetAllTextFields();
        if (e.getSource() == menuButton) {
            goToMenu();
        }
    }

    public synchronized void resetKm(JFrame frame) {
        if (arr.size() == 0) {
            JOptionPane.showMessageDialog(this.frame, "Updating database... Please wait");
        } else {
            for (Vehicles vehicles : arr) vehicles.setKm(0);
            JOptionPane.showMessageDialog(this.frame, "reset Km succeeded");
            int sleepTime = (int) (Math.random() * 3) + 5;
            try {
                Thread.sleep(100L * sleepTime); // Sleep the thread for the entered distance multiplied by 100
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this.frame, "reset Km succeeded");
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

        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/israel.png", Country.Israel));
        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/Greece.png", Country.Greece));
        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/UnitedStates.png", Country.UnitedStates));
        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/Italy.png", Country.Italy));
        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/Somalia.png", Country.Somalia));
        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/ Germany.png", Country.Germany));
        flagsPanel.add(getFlagForFile("/Users/yrdnqldrwn/Desktop/SOFTWARE/IntelliJ #Java/homeWork/p1/p1/src/Graphic/Pirates.png", Country.Pirates));


        changeAllFlagsBtn(flagsPanel);
        flagsPanel.setBounds(0, 0, width, height);
        flagFrame.add(flagsPanel);
        flagFrame.setVisible(true);
    }

    private void changeAllFlagsBtn(JPanel panel) {  // change all the flags
        final boolean[] flag = {false};
        resetFlagBtn = new JButton("Change flags");
        resetFlagBtn.setBounds(200, 200, 190, 50);
        resetFlagBtn.setBackground(new Color(255, 255, 255, 255));
        panel.add(resetFlagBtn);
        resetFlagBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the currently selected radio button
                JRadioButton selectedButton = null;
                Enumeration<AbstractButton> buttons = flagButtonGroup.getElements();
                while (buttons.hasMoreElements()) {
                    JRadioButton button = (JRadioButton) buttons.nextElement();
                    if (button.isSelected()) {
                        selectedButton = button;
                        break;
                    }
                }
                // If a radio button is selected, set the flags for all water vehicles
                if (selectedButton != null) {
                    Country selectedCountry = Country.valueOf(selectedButton.getText());
                    for (int i = 0; i < arr.size(); i++) {
                        if ((arr.get(i) instanceof WaterVehicle)) {
                            ((WaterVehicle) arr.get(i)).setCountryFlag(selectedCountry.name());
                            flag[0] = true;
                        }
                    }
                    if (flag[0]) {
                        JOptionPane.showMessageDialog(null, "Flags have been successfully reset.");
                        updatingData(Thread.currentThread());
                    }
                    else
                        JOptionPane.showMessageDialog(null, "No marine vehicles in stock.");

                    goToMenu();
                }
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

    private Box printAllVehiclesPictures() {
        buttonVehiclesGroup = new ButtonGroup();
        System.out.println("the Tread in printAllVehiclesPictures now is:" + Thread.currentThread().getName());
        Box box = Box.createHorizontalBox();
        box.add(Box.createGlue());
        box.setBounds(0, 0, width, height);
        box.add(printAllLandVehiclesPictures());
        box.add(Box.createHorizontalStrut(50)); // Add a 50-pixel gap
        box.add(printAllWaterVehiclesPictures());
        box.add(Box.createHorizontalStrut(50)); // Add another 50-pixel gap
        box.add(printAllAirVehiclesPictures());
        return box;
    } // Arranges the pictures of the vehicles in columns

    private Box printAllLandVehiclesPictures() {
        System.out.println("the Tread in printAllLandVehiclesPictures now is:" + Thread.currentThread().getName());

        boolean flag = false;
        Box box = Box.createVerticalBox();
        ImageIcon imageIcon;
        JLabel title = new JLabel("All the land vehicles :");
        title.setForeground(Color.black);
        box.add(title);
//        buttonVehiclesGroup = new ButtonGroup();

        int cont = 0;
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i) instanceof LandVehicle || arr.get(i) instanceof iLandVehicle) {
                cont ++;
                flag = true;
                imageIcon = arr.get(i).getVehiclesImage();
                JRadioButton radioButton = new JRadioButton("Option "+cont); // Create a radio button
                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
                buttonVehiclesGroup.add(radioButton); // Add the radio button to the ButtonGroup
                JLabel imageLabel = new JLabel(imageIcon);
                final String tooltipText = arr.get(i).toString(); // Store tooltip text in final variable
                imageLabel.setToolTipText(tooltipText);
//                imageLabel.addMouseListener(new MouseAdapter() { // Add mouse listener
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
//                    }
//                });
                imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label

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

    private Box printAllAirVehiclesPictures() {
        boolean flag = false;
        Box box = Box.createVerticalBox();
        ImageIcon imageIcon;
        JLabel title = new JLabel("All the air vehicles :");
        title.setForeground(Color.black);
        box.add(title);
//        buttonVehiclesGroup = new ButtonGroup();
        int cont = 0;
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i) instanceof AirVehicle || arr.get(i) instanceof iAirVehicle) {
                cont++;
                flag = true;
                imageIcon = arr.get(i).getVehiclesImage();
                JRadioButton radioButton = new JRadioButton("Option"+ cont); // Create a radio button
                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
                buttonVehiclesGroup.add(radioButton); // Add the radio button to the ButtonGroup
                JLabel imageLabel = new JLabel(imageIcon);
                final String tooltipText = arr.get(i).toString(); // Store tooltip text in final variable
                imageLabel.setToolTipText(tooltipText);
                imageLabel.addMouseListener(new MouseAdapter() { // Add mouse listener
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
                    }
                });
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

    private Box printAllWaterVehiclesPictures() {
        boolean flag = false;
        Box box = Box.createVerticalBox();
        ImageIcon imageIcon;
        JLabel title = new JLabel("All the water vehicles :");
        title.setForeground(Color.black);
        box.add(title);
//        buttonVehiclesGroup = new ButtonGroup();
        int cont = 0;
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i) instanceof WaterVehicle || arr.get(i) instanceof iWaterVehicle) {
                cont++;
                flag = true;
                imageIcon = arr.get(i).getVehiclesImage();
                JRadioButton radioButton = new JRadioButton("Option "+ cont); // Create a radio button
                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
                buttonVehiclesGroup.add(radioButton); // Add the radio button to the ButtonGroup
                JLabel imageLabel = new JLabel(imageIcon);
                final String tooltipText = arr.get(i).toString(); // Store tooltip text in final variable
                imageLabel.setToolTipText(tooltipText);
                imageLabel.addMouseListener(new MouseAdapter() { // Add mouse listener
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
                    }
                });
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

    private Box getFlagForFile(String filePath, Country country) {
        Box box = Box.createVerticalBox();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            bufferedImage = resizePic(bufferedImage, 100, 70);
            ImageIcon icon = new ImageIcon(bufferedImage);
            JLabel label = new JLabel(icon);
            box.add(label);
            radioFlagBtn = new JRadioButton(country.name());
            flagButtonGroup.add(radioFlagBtn); // Add the radio button to the ButtonGroup
            radioFlagBtn.setBorder(BorderFactory.createEmptyBorder(0, 42, 0, 0));  // Add an EmptyBorder with 5 pixels of padding on the right side
            box.add(radioFlagBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    private Box addVehicleBtn(VehicleType vehicleType, List<JTextField> textFieldsList) {

        Box box = Box.createHorizontalBox();
        addVehicleBtn = new JButton("Add Vehicle.");
        addVehicleBtn.setBackground(Color.white);
        addVehicleBtn.setSize(100, 100);
        addVehicleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (vehicleType) {
                    case Frigate -> {
//                        boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !maxKmText.getText().trim().isEmpty() && !maxOfPassengerText.getText().trim().isEmpty() && !maxSpeedText.getText().trim().isEmpty() && (withTheWindButton.isSelected() || againstTheWindButton.isSelected());
                        boolean inputNotEmpty = true;

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");

                        else {
//                            Frigate frigate = new Frigate(modelText.getText(), 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), withTheWind);
                            Frigate frigate = new Frigate("", 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), withTheWind);
                            frigate.setVehiclesImage(vehiclesImage);
                            arr.add(frigate);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the Frigate");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case CruiseShip -> {
//                        boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !maxKmText.getText().trim().isEmpty() && !maxOfPassengerText.getText().trim().isEmpty() && !maxSpeedText.getText().trim().isEmpty();
                        boolean inputNotEmpty = true;

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");
                        else {
//                            CruiseShip cruiseShip = new CruiseShip(modelText.getText(), 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), "israel", Integer.parseInt(averageLifeSpanText.getText()));
                            CruiseShip cruiseShip = new CruiseShip("", 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), "israel", Integer.parseInt(averageLifeSpanText.getText()));
                            cruiseShip.setVehiclesImage(vehiclesImage);
                            arr.add(cruiseShip);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the CruiseShip");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case Bicycle -> {
//                        boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !submodelText.getText().trim().isEmpty();
                        boolean inputNotEmpty = textFieldsList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (Only one empty field will return true)

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");
                        else {
                            NormalBicycle bicycle = new NormalBicycle(textFieldsList.get(0).getText(),textFieldsList.get(0).getText());
                            bicycle.setVehiclesImage(vehiclesImage);
                            arr.add(bicycle);
                            String str = bicycle.toString();
                            System.out.println(str);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the bicycle");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case PlayGlider -> {
                        boolean inputNotEmpty = !maxKmText.getText().trim().isEmpty() && !averageLifeSpanText.getText().trim().isEmpty();

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");

                        else {
                            PlayGlider playGlider = new PlayGlider(0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(averageLifeSpanText.getText()));
                            playGlider.setVehiclesImage(vehiclesImage);
                            arr.add(playGlider);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the Play Glider");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case SpyGlider -> {
                        boolean inputNotEmpty = !maxKmText.getText().trim().isEmpty() && !averageLifeSpanText.getText().trim().isEmpty();

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");

                        else {
                            SpyGlider spyGlider = new SpyGlider(0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(averageLifeSpanText.getText()));
                            spyGlider.setVehiclesImage(vehiclesImage);
                            arr.add(spyGlider);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the Spy Glider");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case Amphibious -> {
// TODO                       boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !submodelText.getText().trim().isEmpty() && !maxKmText.getText().trim().isEmpty() && !maxOfPassengerText.getText().trim().isEmpty() && !maxSpeedText.getText().trim().isEmpty() && !numOfWheelsText.getText().trim().isEmpty();
                        boolean inputNotEmpty = true;

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");
                        else {
// TODO                           Amphibious amphibious = new Amphibious(modelText.getText(), submodelText.getText(), 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), withTheWind, "israel", Integer.parseInt(numOfWheelsText.getText()));
                            Amphibious amphibious = new Amphibious("", "", 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), withTheWind, "israel", Integer.parseInt(numOfWheelsText.getText()));
                            amphibious.setVehiclesImage(vehiclesImage);
                            arr.add(amphibious);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the amphibious");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case Jeep -> {
// TODO                       boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !submodelText.getText().trim().isEmpty() && !maxKmText.getText().trim().isEmpty() && !maxSpeedText.getText().trim().isEmpty() && !avgConsumptionText.getText().trim().isEmpty() && !averageLifeSpanText.getText().trim().isEmpty();
                        boolean inputNotEmpty = true;

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Please fill all the empty fields !!");

                        else {
// TODO                           Jeep jeep = new Jeep(modelText.getText(), submodelText.getText(), 0, Integer.parseInt(maxKmText.getText()), 5, Integer.parseInt(maxSpeedText.getText()), Integer.parseInt(avgConsumptionText.getText()), Integer.parseInt(averageLifeSpanText.getText()));
                            Jeep jeep = new Jeep("", "submodelText.getText()", 0, Integer.parseInt(maxKmText.getText()), 5, Integer.parseInt(maxSpeedText.getText()), Integer.parseInt(avgConsumptionText.getText()), Integer.parseInt(averageLifeSpanText.getText()));
                            String str = jeep.toString();
                            System.out.println(str);
                            jeep.setVehiclesImage(vehiclesImage);
                            arr.add(jeep);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the Jeep");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                    case HybridPlane -> {
//  TODO                      boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !submodelText.getText().trim().isEmpty() && !maxKmText.getText().trim().isEmpty() && !maxOfPassengerText.getText().trim().isEmpty() && !maxSpeedText.getText().trim().isEmpty() && !avgConsumptionText.getText().trim().isEmpty() && !averageLifeSpanText.getText().trim().isEmpty() && !numOfWheelsText.getText().trim().isEmpty() && (withTheWindButton.isSelected() || againstTheWindButton.isSelected()) && (civilianButton.isSelected() || militaryButton.isSelected());
                        boolean inputNotEmpty = true;
                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");
                        else {
//  TODO                          HybridPlane hybridPlane = new HybridPlane(modelText.getText(), submodelText.getText(), 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), civilian, military, Integer.parseInt(averageLifeSpanText.getText()), Integer.parseInt(numOfWheelsText.getText()), withTheWind, "israel");
                            HybridPlane hybridPlane = new HybridPlane("", "submodelText.getText()", 0, Integer.parseInt(maxKmText.getText()), Integer.parseInt(maxOfPassengerText.getText()), Integer.parseInt(maxSpeedText.getText()), civilian, military, Integer.parseInt(averageLifeSpanText.getText()), Integer.parseInt(numOfWheelsText.getText()), withTheWind, "israel");
                            hybridPlane.setVehiclesImage(vehiclesImage);
                            arr.add(hybridPlane);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the hybrid Plane");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }

                    }
                    case ElectricBicycle -> {
//TODO                        boolean inputNotEmpty = !modelText.getText().trim().isEmpty() && !submodelText.getText().trim().isEmpty();
                        boolean inputNotEmpty = true;

                        if (!inputNotEmpty || !imageSelcted)
                            JOptionPane.showMessageDialog(frame, "Pleas fill all the empty fields !!");
                        else {
//  TODO                          ElectricBicycle eBicycle = new ElectricBicycle(modelText.getText(), modelText.getText());
                            ElectricBicycle eBicycle = new ElectricBicycle("","");
                            eBicycle.setVehiclesImage(vehiclesImage);
                            arr.add(eBicycle);
                            JOptionPane.showMessageDialog(frame, "You have successfully added the electric bicycle");
                            resetAllTextFields();
                            addVehicleBox();
                            imageSelcted = false;
                        }
                    }
                }
            }
        });
        box.add(addVehicleBtn);
        return box;
    } // to add Vehicle to array
    private void updatingData(Thread thread) {
        System.out.println("the thread in updatingData now is: " + Thread.currentThread().getName());
        JOptionPane optionPane = new JOptionPane("Updating database... Please wait", JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Success"); // Create a dialog with the message

        // Set the dialog to be not closable by the user
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setModal(true);

        // Remove the OK button from the dialog
        optionPane.setOptions(new Object[]{});

        // Create a javax.swing.Timer to automatically close the dialog after a specified delay
        int sleepTime = (int) (Math.random() * 3) + 8;
        javax.swing.Timer timer = new javax.swing.Timer(sleepTime * 1000, e -> {
            dialog.dispose(); // Close the dialog when the timer fires
        });
        timer.setRepeats(false); // Make the timer fire only once
        timer.start();

        dialog.setVisible(true); // Show the dialog to the user
    }
    private boolean isContains(Vehicles vehicles) {
        return indexOf(vehicles) != -1;
    }

    private int indexOf(Vehicles vehicles) {
        int ans = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (this.arr.get(i).equals(vehicles)) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static BufferedImage resizePic(BufferedImage img, int newW, int newH) {  // Resize the image as needed
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } // resize the size of image



}

