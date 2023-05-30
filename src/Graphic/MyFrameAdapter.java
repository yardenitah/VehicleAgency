package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

public class MyFrameAdapter implements Runnable{
    private MyFrame myFrame;

    public MyFrameAdapter() {
        this.myFrame = new MyFrame();
    }

    @Override
    public void run() {
    }
}
//
//    private void openAddVehicleWindow() {
//        AddVehicleWindowFrame = new JFrame();
//        AddVehicleWindowFrame.setResizable(false);
//        AddVehicleWindowFrame.setTitle("Add vehicle");
//        AddVehicleWindowFrame.setBackground(Color.gray);
//        AddVehicleWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        AddVehicleWindowFrame.setSize(width, height);
//        AddVehicleWindowFrame.setLayout(null);
//
//        AddVehicleWindowPanel = new JPanel();
//        AddVehicleWindowPanel.setSize(width, height);
//        AddVehicleWindowPanel.setBackground(Color.gray);
//
//        addvehicleBox();
//        AddVehicleWindowFrame.add(AddVehicleWindowPanel);
//        AddVehicleWindowFrame.setVisible(true);
//    }
//
////
//private void addvehicleBox() {
////        resetAllTextFields();
////        resetInnerPanel();
////        resetPanel();
//    resetPanel(AddVehicleWindowPanel);
//    typeLabel = new JLabel("Please select vehicle type :");
//    typeLabel.setForeground(new Color(255, 255, 255, 255));
//    typeLabel.setBounds(45, 20, 180, 100);
//    AddVehicleWindowPanel.add(typeLabel);
//
//    vehicleListBox = new JComboBox<>(new Vector<>(java.util.List.of("Land Vehicle", "Water Vehicle", "Air Vehicle")));
//    vehicleListBox.setBounds(235, 50, 150, 45);
//    vehicleListBox.setBackground(new Color(255, 255, 255, 255));
//    vehicleListBox.setForeground(Color.blue);
//    vehicleListBox.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            addVehicle();
//        }
//    });
//    AddVehicleWindowPanel.add(vehicleListBox);
//    goToMenuButton(this.panel);
//}
//

//    private void addVehicle() {
//        System.out.println("the tread in add vehicle function is :"+ Thread.currentThread().getName());
//        int option = vehicleListBox.getSelectedIndex();
//
//        switch (option) {
//            case 0 -> {
//                vehicleListBox.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        addLandVehicleBox();
//                    }
//                });
//                System.out.println("Land vehicle successfully added");
//            }
//            case 1 -> {
//                vehicleListBox.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        addWaterVehicleBox();
//                    }
//                });
//                System.out.println("Water vehicle successfully added");
//            }
//            case 2 -> {
//                vehicleListBox.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        addAirVehicleBox();
//                    }
//                });
//                System.out.println("Air vehicle successfully added");
//            }
//        }
//    }


//
//    private void addWaterVehicleBox() {
//        resetPanel(AddVehicleWindowPanel);
//        waterTypeLabel = new JLabel("What type of water vehicle :");
//        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
//        waterTypeLabel.setBounds(45, 20, 180, 100);
//        AddVehicleWindowPanel.add(waterTypeLabel);
//
//        waterVehicleBox = new JComboBox<>(new Vector<>(java.util.List.of(" Frigate", "Cruise ship", "amphibious", "Hybrid plain")));
//        waterVehicleBox.setBounds(235, 50, 150, 45);
//        waterVehicleBox.setBackground(new Color(255, 255, 255, 255));
//        waterVehicleBox.setForeground(Color.blue);
//        waterVehicleBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addWaterVehicle();
//            }
//        });
//        AddVehicleWindowPanel.add(waterVehicleBox);
//        goToMenuButton(this.panel);
//    }
//
//    private void addAirVehicleBox() {
//        resetPanel(AddVehicleWindowPanel);
//        waterTypeLabel = new JLabel("What type of air vehicle :");
//        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
//        waterTypeLabel.setBounds(45, 20, 180, 100);
//        AddVehicleWindowPanel.add(waterTypeLabel);
//
//        airVehicleBox = new JComboBox<>(new Vector<>(java.util.List.of("Play glider", "Spay glider", "Hybrid plane")));
//        airVehicleBox.setBounds(228, 50, 150, 45);
//        airVehicleBox.setBackground(new Color(255, 255, 255, 255));
//        airVehicleBox.setForeground(Color.blue);
//        airVehicleBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addAirVehicle();
//            }
//        });
//        AddVehicleWindowPanel.add(airVehicleBox);
//        goToMenuButton(this.panel);
//    }
//
//    private void addLandVehicleBox() {
////        resetAllTextFields();
////        resetInnerPanel();
////        resetPanel();
//        resetPanel(AddVehicleWindowPanel);
//        waterTypeLabel = new JLabel("What type of land vehicle :");
//        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
//        waterTypeLabel.setBounds(45, 20, 180, 100);
//        AddVehicleWindowPanel.add(waterTypeLabel);
//
//        landVehicleBox = new JComboBox<>(new Vector<>(List.of("Jeep", "Bicycle", "amphibious", "Hybrid Plain", "Electric bicycle")));
//        landVehicleBox.setBounds(228, 50, 150, 45);
//        landVehicleBox.setBackground(new Color(255, 255, 255, 255));
//        landVehicleBox.setForeground(Color.blue);
//        landVehicleBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addLandVehicle();
//            }
//        });
//        AddVehicleWindowPanel.add(landVehicleBox);
//        goToMenuButton(this.panel);
//    }
//
//    private void addHybridPlain() {
//        JLabel tmpLabel = new JLabel();
//        tmpLabel.setText("enter Hybrid Plain data :");
//        boxContainer.add(tmpLabel);
//        boxContainer.add(civilianOrMilitaryRadioButtons());
//        boxContainer.add(modelLabel());
//        boxContainer.add(subMudelLabel());
//        boxContainer.add(maxKmLabel());
//        boxContainer.add(maxPassengersLabel());
//        boxContainer.add(msxSpeedLabel());
//        boxContainer.add(avgConsumptionLabel());
//        boxContainer.add(averageLifeSpanLabel());
//        boxContainer.add(numOfWheelsLabel());
//        boxContainer.add(windRadioButtons());
//        boxContainer.add(addPicture());
//        boxContainer.add(addVehicleBtn(MyFrame.VehicleType.HybridPlane)); // creat this object
//        innerPanel.add(boxContainer, BorderLayout.CENTER);
//        innerPanel.setBounds(220, 50, width / 2, (height / 2) + 190);
//        AddVehicleWindowPanel.add(innerPanel);
//    }
//
//    private void addAmphibious() {
//        JLabel tmpLabel = new JLabel();
//        tmpLabel.setText("enter amphibious data :");
//        boxContainer.add(tmpLabel);
//        boxContainer.add(modelLabel());
//        boxContainer.add(subMudelLabel());
//        boxContainer.add(maxKmLabel());
//        boxContainer.add(maxPassengersLabel());
//        boxContainer.add(msxSpeedLabel());
//        boxContainer.add(avgConsumptionLabel());
//        boxContainer.add(numOfWheelsLabel());
//        boxContainer.add(windRadioButtons());
//        boxContainer.add(addPicture());
//        boxContainer.add(addVehicleBtn(MyFrame.VehicleType.Amphibious)); // creat this object
//        innerPanel.add(boxContainer, BorderLayout.CENTER);
//        innerPanel.setBounds(220, 50, width / 2, (height + 90) / 2);
//        AddVehicleWindowPanel.add(innerPanel);
//    }
//
//    private void addLandVehicle() {
//        resetPanel(AddVehicleWindowPanel);
//        boxContainer = Box.createVerticalBox();
//        boxContainer.setSize(width, height);
//        goToMenuButton(this.panel);
//        JLabel tmpLabel = new JLabel();
//        tmpLabel.setForeground(Color.WHITE);
//        tmpLabel.setBackground(Color.cyan);
//        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
//
//        int option = landVehicleBox.getSelectedIndex();
//        switch (option) {
//            case 0 -> { // Jeep
//                tmpLabel.setText("enter Jeep data :");
//                boxContainer.add(tmpLabel);
//                boxContainer.add(modelLabel());
//                boxContainer.add(subMudelLabel());
//                boxContainer.add(maxKmLabel());
////                boxContainer.add(maxPassengersLabel());
//                boxContainer.add(msxSpeedLabel());
//                boxContainer.add(avgConsumptionLabel());
//                boxContainer.add(averageLifeSpanLabel());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.Jeep)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 2);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//            case 1 -> { //Bicycle
//                tmpLabel.setText("enter Bicycle data :");
//                boxContainer.add(tmpLabel);
//                boxContainer.add(modelLabel());
//                boxContainer.add(subMudelLabel());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.Bicycle)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 3);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//            case 2 -> {  // amphibious
//                addAmphibious();
//            }
//            case 3 -> {  // HybridPlain
//                addHybridPlain();
//            }
//            case 4 -> { // ElectricBicycle
//                tmpLabel.setText("enter Electric bicycle data :");
//                boxContainer.add(tmpLabel);
//                boxContainer.add(modelLabel());
//                boxContainer.add(subMudelLabel());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.ElectricBicycle)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 3);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//        }
//    }
//
//    public void addAirVehicle() { // reset inner panel
//        resetPanel(AddVehicleWindowPanel);
//        boxContainer = Box.createVerticalBox();
//        boxContainer.setSize(width, height);
//        goToMenuButton(this.panel);
//        JLabel tmpLabel = new JLabel();
//        tmpLabel.setForeground(Color.WHITE);
//        tmpLabel.setBackground(Color.cyan);
//        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
//        int option = airVehicleBox.getSelectedIndex();
//
//        switch (option) {
//            case 0 -> { // Play glider
//                tmpLabel.setText("enter Play glider data :");
//                boxContainer.add(tmpLabel);
//                boxContainer.add(maxKmLabel());
//                boxContainer.add(averageLifeSpanLabel());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.PlayGlider)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 2);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//            case 1 -> { // Spay glider
//                tmpLabel.setText("enter Spay glider data :");
//                boxContainer.add(tmpLabel);
//                boxContainer.add(maxKmLabel());
//                boxContainer.add(averageLifeSpanLabel());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.SpyGlider)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 2);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//            case 2 -> { // hybridPlain
//                addHybridPlain();
//            }
//        }
//    }
//
//    public void addWaterVehicle() {
//        resetPanel(AddVehicleWindowPanel);
//        boxContainer = Box.createVerticalBox(); // Makes all the boxes be in the middle
//        boxContainer.setSize(width, height);
//        goToMenuButton(this.panel);
//        JLabel tmpLabel = new JLabel();
//        tmpLabel.setForeground(Color.WHITE);
//        tmpLabel.setBackground(Color.cyan);
//        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
//        int option = waterVehicleBox.getSelectedIndex();
//
//        switch (option) {
//            case 0 -> { // Frigate
//                tmpLabel.setText("enter Frigate data :");
//                boxContainer.add(tmpLabel);
//                boxContainer.add(modelLabel());
//                boxContainer.add(maxKmLabel());
//                boxContainer.add(maxPassengersLabel());
//                boxContainer.add(msxSpeedLabel());
//                boxContainer.add(windRadioButtons());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.Frigate)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 2);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//            case 1 -> { // Cruise ship
//                tmpLabel.setText("enter Cruise ship data :");
//                boxContainer.add(modelLabel());
//                boxContainer.add(maxKmLabel());
//                boxContainer.add(maxPassengersLabel());
//                boxContainer.add(msxSpeedLabel());
//                boxContainer.add(averageLifeSpanLabel());
//                boxContainer.add(countryFlagLabel());
//                boxContainer.add(addPicture());
//                boxContainer.add(addVehicleBtn(MyFrame.VehicleType.CruiseShip)); // creat this object
//                innerPanel.add(boxContainer, BorderLayout.CENTER);
//                innerPanel.setBounds(220, 50, width / 2, height / 2);
//                AddVehicleWindowPanel.add(innerPanel);
//            }
//            case 2 -> { // amphibious
//                addAmphibious();
//            }
//            case 3 -> { // HybridPlain
//                addHybridPlain();
//            }
//        }
//    }


















// my menu :



//
//    private void goToMenu() {  // menu
//        resetPanel();
//        resetInnerPanel();
//        boxContainer = Box.createHorizontalBox();
//        boxContainer.setSize(width / 7, height / 7);
//        goToMenuButton();
//
//        buyVehicles = new JButton("Buy vehicles");
//        buyVehicles.setBounds(290, 70, 250, 50);
//        panel.add(buyVehicles);
//        buyVehicles.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resetPanel();
//                goToMenuButton();
//                if (arr.size() == 0) {
//                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
//                    goToMenu();
//                } else {
//                    resetInnerPanel();
//                    resetPanel();
//                    goToMenuButton();
//                    buttonVehiclesGroup = new ButtonGroup();
//                    innerPanel.setBounds(0, 0, width, height);
//                    innerPanel.setBackground(new Color(255, 255, 255, 65));
//                    innerPanel.add(buyButton());
//                    innerPanel.add(printAllVehiclesPictures());
//                    panel.add(innerPanel);
//                }
//            }
//        });
//
//        testDrive = new JButton("Test drive");
//        testDrive.setBounds(290, 140, 250, 50);
//        panel.add(testDrive);
//        testDrive.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resetPanel();
//                if (arr.size() == 0) {
//                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
//                    goToMenu();
//                } else {
//                    resetInnerPanel();
//                    resetPanel();
//                    goToMenuButton();
//                    buttonVehiclesGroup = new ButtonGroup();
//                    innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
//                    innerPanel.setBounds(0, 0, width, height);
//                    innerPanel.setBackground(new Color(255, 255, 255, 65));
//                    innerPanel.add(kmLabel());
//                    innerPanel.add(printAllVehiclesPictures());
//                    innerPanel.add(testDriveButton());
//                    panel.add(innerPanel);
//                }
//            }
//        });
//
//        resetkm = new JButton("Reset kilometer");
//        resetkm.setBounds(290, 210, 250, 50);
//        panel.add(resetkm);
//        resetkm.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (arr.size() == 0) {
//                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
//                } else resetKm();
//            }
//        });
//
//        changeFlagsn = new JButton("Change flags");
//        changeFlagsn.setBounds(290, 280, 250, 50);
//        panel.add(changeFlagsn);
//        changeFlagsn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (arr.size() == 0) {
//                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
//                } else {
//                    changeAllFlags();
//                }
//            }
//        });
//
//        addVehicle = new JButton("Add vehicle");
//        addVehicle.setBounds(290, 350, 250, 50);
//        panel.add(addVehicle);
//        addVehicle.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resetInnerPanel();
//                innerPanel.setBackground(new Color(0, 0, 0, 224));
//                innerPanel.setLayout(new BorderLayout());
//                innerPanel.setSize(width / 2, height / 2);
//                addvehicleBox();
//            }
//        });
//
//        currentInventoryReportBtn = new JButton("Current inventory report");
//        currentInventoryReportBtn.setBounds(290, 420, 250, 50);
//        panel.add(currentInventoryReportBtn);
//        currentInventoryReportBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                resetPanel();
//                goToMenuButton();
//                if (arr.size() == 0) {
//                    JOptionPane.showMessageDialog(frame, "No vehicles in stock.");
//                    goToMenu();
//                } else {
//                    resetInnerPanel();
//                    resetPanel();
//                    goToMenuButton();
//                    buttonVehiclesGroup = new ButtonGroup();
//                    innerPanel.setBounds(0, 0, width, height);
//                    innerPanel.setBackground(new Color(255, 255, 255, 65));
//                    innerPanel.add(printAllVehiclesPictures());
//                    panel.add(innerPanel);
//                    // Hide or disable the radio buttons in the button group
//                    Enumeration<AbstractButton> buttons = buttonVehiclesGroup.getElements();
//                    while (buttons.hasMoreElements()) {
//                        AbstractButton button = buttons.nextElement();
//                        button.setVisible(false); // or button.setEnabled(false) to disable
//                    }
//
//                }
//            }
//        });
//
//    } // menu
