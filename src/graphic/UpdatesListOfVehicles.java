//package graphic;
//
//import gui.GetInformationFromTheUser;
//import utils.Tuple;
//import vehicless.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.lang.reflect.Method;
//import java.util.Enumeration;
//import java.util.Vector;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.List;
//
//public class UpdatesListOfVehicles implements UpdatingVehiclesList {
//    private final List<JPanel> buyPanelList;
//    private final List<JPanel> inventoryPanelList;
//    private final List<JPanel> testDrivePanelList;
//    private final Vector<Vehicle> vehicleList;
//    private final GetInformationFromTheUser getInfo;
//    private final MyFrame myFrame;
//    private final BuyVehicle buyVehicle;
//    public UpdatesListOfVehicles(List<JPanel> buyPanelList, List<JPanel> inventoryPanelList, List<JPanel> testDrivePanelList, Vector<Vehicle> vehicleList, MyFrame myFrame, BuyVehicle buyVehicle){
//        this.buyPanelList = buyPanelList;
//        this.inventoryPanelList = inventoryPanelList;
//        this.testDrivePanelList = testDrivePanelList;
//        this.vehicleList = vehicleList;
//        this.myFrame = myFrame;
//        this.buyVehicle = buyVehicle;
//        this.getInfo = new GetInformationFromTheUser();
//    }
//    @Override
//    public void updateVehicleList() {
//        System.out.println("the Tread in updateVehicleList now is:" + Thread.currentThread().getName() + "\n");
//        buyPanelList.forEach(myPanel -> { // like for i = 0 to size
//            if (myPanel != null) {
//                myPanel.removeAll();
//                myPanel.revalidate();
//                myPanel.repaint();
//                Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
//                myPanel.add(btnAndBox.second, BorderLayout.CENTER);
//                myPanel.add(buyVehicle.buyButton(btnAndBox.first)); // getting error here
//            }
//        });
//        inventoryPanelList.forEach(myPanel -> { // like for i = 0 to size
//            if (myPanel != null) {
//                myPanel.removeAll();
//                myPanel.revalidate();
//                myPanel.repaint();
//                Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
//                ButtonGroup buttonGroup = btnAndBox.first;
//                Enumeration<AbstractButton> buttons = buttonGroup.getElements();
//                while (buttons.hasMoreElements()) {
//                    AbstractButton button = buttons.nextElement();
//                    button.setVisible(false);
//                }
//                myPanel.add(btnAndBox.second, BorderLayout.CENTER);
//            }
//        });
//        testDrivePanelList.forEach(myPanel -> { // like for i = 0 to size
//            if (myPanel != null) {
//                myPanel.removeAll();
//                myPanel.revalidate();
//                myPanel.repaint();
//                Tuple<JTextField, Box> kmTextandBox = getInfo.kmLabel();
//                myPanel.add(kmTextandBox.second, BorderLayout.NORTH);
//
//                Tuple<ButtonGroup, Box> btnAndBox = printAllVehiclesPictures();
//                myPanel.add(btnAndBox.second, BorderLayout.CENTER);
//                myPanel.add(myFrame.addKmButton(kmTextandBox.first, btnAndBox.first), BorderLayout.SOUTH); // getting error here
//            }
//        });
//    }
//
//    @Override
//    public void updatingDataPleaseWait(Thread thread) {
//        System.out.println("the thread in updatingData now is: " + Thread.currentThread().getName());
//        JOptionPane optionPane = new JOptionPane("Updating database... Please wait", JOptionPane.INFORMATION_MESSAGE);
//        JDialog dialog = optionPane.createDialog(null, "Success"); // Create a dialog with the message
//
//        // Set the dialog to be not closable by the user
//        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        dialog.setModal(true);
//
//        optionPane.setOptions(new Object[]{});  // Remove the OK button from the dialog
//
//        // Create a javax.swing.Timer to automatically close the dialog after a specified delay
//        int sleepTime = (int) (Math.random() * 3) + 5;
//        javax.swing.Timer timer = new javax.swing.Timer(sleepTime * 1000, e -> {
//            dialog.dispose(); // Close the dialog when the timer fires
//        });
//        timer.setRepeats(false); // Make the timer fire only once
//        timer.start();
//
//        dialog.setVisible(true); // Show the dialog to the user
//        updateVehicleList();
//    }
//
//    @Override
//    public Tuple<ButtonGroup, Box> printAllVehiclesPictures() {
//        ButtonGroup buttonGroup = new ButtonGroup();
//        System.out.println("the Tread in printAllVehiclesPictures now is:" + Thread.currentThread().getName());
//        Box box = Box.createHorizontalBox();
//        box.add(Box.createGlue());
//        box.setBounds(0, 0, MyFrame.getWidthAndHeight().first, MyFrame.getWidthAndHeight().second);
//        box.add(printAllLandVehiclesPictures(buttonGroup));
//        box.add(Box.createHorizontalStrut(50)); // Add a 50-pixel gap
//        box.add(printAllWaterVehiclesPictures(buttonGroup));
//        box.add(Box.createHorizontalStrut(50)); // Add another 50-pixel gap
//        box.add(printAllAirVehiclesPictures(buttonGroup));
//        return new Tuple<>(buttonGroup, box);
//    } // Arranges the pictures of the vehicles in columns
//
//    @Override
//    public Box printAllLandVehiclesPictures(ButtonGroup buttonGroup) {
//        System.out.println("the Tread in printAllLandVehiclesPictures now is:" + Thread.currentThread().getName());
//        boolean flag = false;
//        Box box = Box.createVerticalBox();
//        ImageIcon imageIcon;
//        JLabel title = new JLabel("All the land vehicles :");
//        title.setForeground(Color.black);
//        box.add(title);
//        AtomicInteger counter = new AtomicInteger(0);
//        for (int i = 0; i < vehicleList.size(); ++i) {
//            if (vehicleList.get(i) instanceof LandVehicle || vehicleList.get(i) instanceof iLandVehicle) {
//                counter.incrementAndGet();
//                flag = true;
//                imageIcon = vehicleList.get(i).getVehiclesImage();
//                JRadioButton radioButton = new JRadioButton("Option " + counter); // Create a radio button
//                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
//                buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
//                JLabel imageLabel = new JLabel(imageIcon);
//                final String tooltipText = vehicleList.get(i).toString(); // Store tooltip text in final variable
//                imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
//
//                Box radioButtonBox = Box.createVerticalBox(); // Create a box for the radio button and the image label
//                radioButtonBox.add(radioButton);
//                radioButtonBox.add(imageLabel);
//                box.add(Box.createVerticalStrut(12)); // Add a 12-pixel gap
//                box.add(radioButtonBox);
//            }
//        }
//        if (!flag) {
//            JLabel label = new JLabel("No land Vehicles on stock");
//            box.add(label);
//        }
//        return box;
//    }
//
//    @Override
//    public Box printAllAirVehiclesPictures(ButtonGroup buttonGroup) {
//        boolean flag = false;
//        Box box = Box.createVerticalBox();
//        ImageIcon imageIcon;
//        JLabel title = new JLabel("All the air vehicles :");
//        title.setForeground(Color.black);
//        box.add(title);
//        AtomicInteger cont = new AtomicInteger(0);
//        for (int i = 0; i < vehicleList.size(); ++i) {
//            if (vehicleList.get(i) instanceof AirVehicle || vehicleList.get(i) instanceof iAirVehicle) {
//                cont.incrementAndGet();
//                flag = true;
//                imageIcon = vehicleList.get(i).getVehiclesImage();
//                JRadioButton radioButton = new JRadioButton("Option" + cont); // Create a radio button
//                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
//                buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
//                JLabel imageLabel = new JLabel(imageIcon);
//                final String tooltipText = vehicleList.get(i).toString(); // Store tooltip text in final variable
//                imageLabel.setToolTipText(tooltipText); // Set tooltip text when mouse enters label
//                Box radioButtonBox = Box.createVerticalBox(); // Create a box for the radio button and the image label
//                radioButtonBox.add(radioButton);
//                radioButtonBox.add(imageLabel);
//                box.add(Box.createVerticalStrut(12)); // Add a 12-pixel gap
//                box.add(radioButtonBox);
//            }
//        }
//        if (!flag) {
//            JLabel label = new JLabel("No air vehicles on stock");
//            box.add(label);
//        }
//        return box;
//    }
//
//    @Override
//    public Box printAllWaterVehiclesPictures(ButtonGroup buttonGroup) {
//        boolean flag = false;
//        Box box = Box.createVerticalBox();
//        ImageIcon imageIcon;
//        JLabel title = new JLabel("All the water vehicles :");
//        title.setForeground(Color.black);
//        box.add(title);
//
//        AtomicInteger cont = new AtomicInteger(0);
//        for (int i = 0; i < vehicleList.size(); ++i) {
//            if (vehicleList.get(i) instanceof WaterVehicle || vehicleList.get(i) instanceof iWaterVehicle) {
//                cont.incrementAndGet();
//                flag = true;
//                imageIcon = vehicleList.get(i).getVehiclesImage();
//                JRadioButton radioButton = new JRadioButton("Option " + cont); // Create a radio button
//                radioButton.setActionCommand(Integer.toString(i)); // Set action command to the index of the vehicle in the array
//                buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
//                JLabel imageLabel = new JLabel(imageIcon);
//                final String tooltipText = vehicleList.get(i).toString(); // Store tooltip text in final variable
//                imageLabel.setToolTipText(tooltipText);
//                Box radioButtonBox = Box.createVerticalBox(); // Create a box for the radio button and the image label
//                radioButtonBox.add(radioButton);
//                radioButtonBox.add(imageLabel);
//                box.add(Box.createVerticalStrut(12)); // Add a 12-pixel gap
//                box.add(radioButtonBox);
//            }
//        }
//        if (!flag) {
//            JLabel label = new JLabel("No water Vehicles on stock");
//            box.add(label);
//        }
//        return box;
//    }
//
//}
