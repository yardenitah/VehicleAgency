package addVehicle;

import graphic.ImageFilter;
import graphic.MyFrame;
import gui.GetInformationFromTheUser;
import gui.MainWindowSingleton;
import utils.Triple;
import utils.Tuple;
import vehicles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;


public class AddVehicleClass {
    public static final int LAND_VEHICLE = 0;
    public static final int WATER_VEHICLE = 1;
    public static final int AIR_VEHICLE = 2;
    //land
    public static final int JEEP = 0;
    public static final int NORMAL_BICYCLE = 1;
    public static final int AMPHIBIOUS = 2;
    public static final int HYBRID_PLAIN = 3;
    public static final int ELECTRIC_BICYCLE = 4;
    //water
    public static final int FRIGATE = 0;
    public static final int CRUISE_SHIP = 1;
    //air
    public static final int PLAY_GLIDER = 0;
    public static final int SPAY_GLIDER = 1;
    private final JPanel ColorPanel;
    private final Color noColor;

    protected enum VehicleType {
        Jeep, Frigate, Bicycle, CruiseShip, PlayGlider, SpyGlider, Amphibious, HybridPlane, ElectricBicycle
    }
    GetInformationFromTheUser getInfo;
    protected final Map<String, Boolean> addImageMap;
    protected final ImageIcon vehiclesImage;
    private final JPanel panel;
    private final Vector<Vehicle> vehicleList;

    public AddVehicleClass(JPanel panel, Vector<Vehicle> vehicleList) {
        this.addImageMap = new HashMap<>();
        addImageMap.put(Thread.currentThread().getName(), false);
        this.vehiclesImage = new ImageIcon();
        this.vehicleList = vehicleList;

        this.panel = panel;
        this.getInfo = new GetInformationFromTheUser();

        ColorPanel  = new JPanel();
        ColorPanel.setBackground(Color.lightGray);
        noColor = Color.getHSBColor(0, 0, 0);
    }
    public void addVehicle(){
        addVehicleBox(this.panel);
    }
    private void addVehicleBox(JPanel panel) {
        System.out.println("the Tread in addVehicleBox now is:" + Thread.currentThread().getName() + "\n");

        resetPanel(panel);
        JLabel typeLabel = new JLabel("Please select vehicle type :");
        typeLabel.setForeground(new Color(255, 255, 255, 255));
        typeLabel.setBounds(45, 20, 180, 100);
        panel.add(typeLabel);

        JComboBox<String> vehicleListBox = new JComboBox<>(new Vector<>(List.of("Land Vehicle", "Water Vehicle", "Air Vehicle")));
        vehicleListBox.setBounds(235, 50, 150, 45);
        vehicleListBox.setBackground(new Color(255, 255, 255, 255));
        vehicleListBox.setForeground(Color.blue);
        vehicleListBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicle(panel, vehicleListBox.getSelectedIndex());
            }
        });
        panel.add(vehicleListBox);
    }

    private void addVehicle(JPanel panel, int index) {
        System.out.println("the Tread in addVehicle now is:" + Thread.currentThread().getName() + "\n");
        switch (index) {
            case 0 -> {
                addLandVehicleBox(panel);
            }
            case 1 -> {
                addWaterVehicleBox(panel);
            }
            case 2 -> {
                addAirVehicleBox(panel);
            }
        }
    }

    private void addLandVehicleBox(JPanel panel) {
        System.out.println("the Tread in addLandVehicleBox now is:" + Thread.currentThread().getName() + "\n");
        resetPanel(panel);
        JLabel waterTypeLabel = new JLabel("What type of land vehicle :");
        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
        waterTypeLabel.setBounds(45, 20, 180, 100);
        panel.add(waterTypeLabel);

        JComboBox<String> landVehicleBox = new JComboBox<>(new Vector<>(List.of("Jeep", "Bicycle", "amphibious", "Hybrid Plain", "Electric bicycle")));
        landVehicleBox.setBounds(228, 50, 150, 45);
        landVehicleBox.setBackground(new Color(255, 255, 255, 255));
        landVehicleBox.setForeground(Color.blue);
        landVehicleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLandVehicle(panel, landVehicleBox.getSelectedIndex());
            }
        });
        panel.add(landVehicleBox);
    }

    private void addWaterVehicleBox(JPanel panel) {
        resetPanel(panel);
        JLabel waterTypeLabel = new JLabel("What type of water vehicle :");
        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
        waterTypeLabel.setBounds(45, 20, 180, 100);
        panel.add(waterTypeLabel);

        JComboBox<String> waterVehicleBox = new JComboBox<>(new Vector<>(List.of(" Frigate", "Cruise ship", "amphibious", "Hybrid plain")));
        waterVehicleBox.setBounds(235, 50, 150, 45);
        waterVehicleBox.setBackground(new Color(255, 255, 255, 255));
        waterVehicleBox.setForeground(Color.blue);
        waterVehicleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWaterVehicle(panel, waterVehicleBox.getSelectedIndex());
            }
        });
        panel.add(waterVehicleBox);
    }

    private void addAirVehicleBox(JPanel panel) {
        resetPanel(panel);
        JLabel waterTypeLabel = new JLabel("What type of air vehicle :");
        waterTypeLabel.setForeground(new Color(255, 255, 255, 255));
        waterTypeLabel.setBounds(45, 20, 180, 100);
        panel.add(waterTypeLabel);

        JComboBox<String> airVehicleBox = new JComboBox<>(new Vector<>(List.of("Play glider", "Spay glider", "Hybrid plane")));
        airVehicleBox.setBounds(228, 50, 150, 45);
        airVehicleBox.setBackground(new Color(255, 255, 255, 255));
        airVehicleBox.setForeground(Color.blue);
        airVehicleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAirVehicle(panel, airVehicleBox.getSelectedIndex());
            }
        });
        panel.add(airVehicleBox);
    }

    private void addLandVehicle(JPanel panel, int index) {
        System.out.println("the Tread in addLandVehicle now is:" + Thread.currentThread().getName() + "\n");
        resetPanel(panel);
        Box boxContainer = Box.createVerticalBox();
        boxContainer.setSize(MyFrame.getWidthAndHeight().first, MyFrame.getWidthAndHeight().second);
        JLabel tmpLabel = new JLabel();
        tmpLabel.setForeground(Color.WHITE);
        tmpLabel.setBackground(Color.cyan);
        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        switch (index) {
            case 0 -> { // Jeep
                tmpLabel.setText("enter Jeep data :");
                boxContainer.add(tmpLabel);

                Tuple<JTextField, Box> textAndBoxModel = getInfo.modelLabel(); // first = text , second = box
                boxContainer.add(textAndBoxModel.second);

                Tuple<JTextField, Box> textAndBoxSubModel = getInfo.subModelLabel();
                boxContainer.add(textAndBoxSubModel.second);

                Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
                boxContainer.add(textAndBoxMaxKm.second);

                Tuple<JTextField, Box> textAndBoxMaxSpeed = getInfo.msxSpeedLabel();
                boxContainer.add(textAndBoxMaxSpeed.second);

                Tuple<JTextField, Box> textAndBoxAvgCon = getInfo.avgConsumptionLabel();
                boxContainer.add(textAndBoxAvgCon.second);

                Tuple<JTextField, Box> textAndBoxLifeSpan = getInfo.averageLifeSpanLabel();
                boxContainer.add(textAndBoxLifeSpan.second);

                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);

                boxContainer.add(addPicture());

                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxModel.first, textAndBoxSubModel.first, textAndBoxMaxKm.first, textAndBoxMaxSpeed.first, textAndBoxAvgCon.first, textAndBoxLifeSpan.first));


                boxContainer.add(addVehicleBtn(VehicleType.Jeep, holderTextDataList, panel, null, btnAndColor.second)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);

            }
            case 1 -> { //Bicycle
                System.out.println("the Tread in add Bicycle now is:" + Thread.currentThread().getName() + "\n");
                tmpLabel.setText("enter Bicycle data :");
                boxContainer.add(tmpLabel);

                Tuple<JTextField, Box> textAndBoxModel = getInfo.modelLabel();
                boxContainer.add(textAndBoxModel.second);

                Tuple<JTextField, Box> textAndBoxSubModel = getInfo.subModelLabel();
                boxContainer.add(textAndBoxSubModel.second);

                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);

                boxContainer.add(addPicture());

                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxModel.first, textAndBoxSubModel.first));

                boxContainer.add(addVehicleBtn(VehicleType.Bicycle, holderTextDataList, panel, null, btnAndColor.second)); // creat this object

                panel.add(boxContainer, BorderLayout.CENTER);
            }
            case 2 -> {  // amphibious
                addAmphibious(panel, boxContainer);
            }
            case 3 -> {  // HybridPlain
                addHybridPlain(panel, boxContainer);
            }
            case 4 -> { // ElectricBicycle
                tmpLabel.setText("enter Electric bicycle data :");
                boxContainer.add(tmpLabel);
                Tuple<JTextField, Box> textAndBoxModel = getInfo.modelLabel(); // first = text , second = box
                boxContainer.add(textAndBoxModel.second);
                Tuple<JTextField, Box> textAndBoxSubModel = getInfo.subModelLabel();
                boxContainer.add(textAndBoxSubModel.second);
                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);
                boxContainer.add(addPicture());
                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxModel.first, textAndBoxSubModel.first));
                boxContainer.add(addVehicleBtn(VehicleType.ElectricBicycle, holderTextDataList, panel, null, btnAndColor.second)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);
            }
        }
    }
    public void addWaterVehicle(JPanel panel, int index) {
        resetPanel(panel);
        Box boxContainer = Box.createVerticalBox(); // Makes all the boxes be in the middle
        boxContainer.setSize(MyFrame.getWidthAndHeight().first, MyFrame.getWidthAndHeight().second);
        JLabel tmpLabel = new JLabel();
        tmpLabel.setForeground(Color.WHITE);
        tmpLabel.setBackground(Color.cyan);
        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        switch (index) {
            case 0 -> { // Frigate
                tmpLabel.setText("enter Frigate data :");
                boxContainer.add(tmpLabel);

                Tuple<JTextField, Box> textAndBoxModel = getInfo.modelLabel();
                boxContainer.add(textAndBoxModel.second);

                Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
                boxContainer.add(textAndBoxMaxKm.second);

                Tuple<JTextField, Box> textFieldBoxMaxPassengers = getInfo.maxPassengersLabel();
                boxContainer.add(textFieldBoxMaxPassengers.second);


                Tuple<JTextField, Box> textAndBoxMaxSpeed = getInfo.msxSpeedLabel();
                boxContainer.add(textAndBoxMaxSpeed.second);

                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);

                Triple<Boolean, Boolean, Box> tripleWind = getInfo.windRadioButtons(); // first = withTheWind , second = againstTheWind, third = Box
                boxContainer.add(tripleWind.third);

                Tuple<Tuple<Boolean, Boolean>, Tuple<Boolean, Boolean>> twoTuples = new Tuple<>(null, new Tuple<>(tripleWind.first, tripleWind.second));// first tuple = civilianAndMilitary  , second tuple  =  withTheWindOrAgainstTheWind


                boxContainer.add(addPicture());

                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxModel.first, textAndBoxMaxKm.first, textFieldBoxMaxPassengers.first, textAndBoxMaxSpeed.first));
                boxContainer.add(addVehicleBtn(VehicleType.Frigate, holderTextDataList, panel, twoTuples, btnAndColor.second)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);
            }
            case 1 -> { // Cruise ship
                tmpLabel.setText("enter Cruise ship data :");
                boxContainer.add(tmpLabel);

                Tuple<JTextField, Box> textFieldBoxModel = getInfo.modelLabel();
                boxContainer.add(textFieldBoxModel.second);

                Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
                boxContainer.add(textAndBoxMaxKm.second);

                Tuple<JTextField, Box> textFieldBoxMaxPassengers = getInfo.maxPassengersLabel();
                boxContainer.add(textFieldBoxMaxPassengers.second);


                Tuple<JTextField, Box> textAndBoxMaxSpeed = getInfo.msxSpeedLabel();
                boxContainer.add(textAndBoxMaxSpeed.second);

                Tuple<JTextField, Box> textAndBoxLifeSpan = getInfo.averageLifeSpanLabel();
                boxContainer.add(textAndBoxLifeSpan.second);

                Tuple<JTextField, Box> textAndBoxFlag = getInfo.countryFlagLabel();
                boxContainer.add(textAndBoxFlag.second);

                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);

                boxContainer.add(addPicture());
                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textFieldBoxModel.first, textAndBoxMaxKm.first, textFieldBoxMaxPassengers.first, textAndBoxMaxSpeed.first, textAndBoxLifeSpan.first, textAndBoxFlag.first));
                boxContainer.add(addVehicleBtn(VehicleType.CruiseShip, holderTextDataList, panel, null, btnAndColor.second)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);
            }
            case 2 -> { // amphibious
                addAmphibious(panel, boxContainer);
            }
            case 3 -> { // HybridPlain
                addHybridPlain(panel,boxContainer);
            }
        }
    }
    public void addAirVehicle(JPanel panel, int index) {
        resetPanel(panel);
        Box boxContainer = Box.createVerticalBox();
        boxContainer.setSize(MyFrame.getWidthAndHeight().first, MyFrame.getWidthAndHeight().second);
        JLabel tmpLabel = new JLabel();
        tmpLabel.setForeground(Color.WHITE);
        tmpLabel.setBackground(Color.cyan);
        tmpLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        switch (index) {
            case 0 -> { // Play glider
                tmpLabel.setText("enter Play glider data :");
                boxContainer.add(tmpLabel);

                Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
                boxContainer.add(textAndBoxMaxKm.second);

                Tuple<JTextField, Box> textAndBoxLifeSpan = getInfo.averageLifeSpanLabel();
                boxContainer.add(textAndBoxLifeSpan.second);

                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);

                boxContainer.add(addPicture());
                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxMaxKm.first, textAndBoxLifeSpan.first));
                boxContainer.add(addVehicleBtn(VehicleType.PlayGlider, holderTextDataList, panel, null, btnAndColor.second)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);
            }
            case 1 -> { // Spay glider
                tmpLabel.setText("enter Spay glider data :");
                boxContainer.add(tmpLabel);

                Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
                boxContainer.add(textAndBoxMaxKm.second);

                Tuple<JTextField, Box> textAndBoxLifeSpan = getInfo.averageLifeSpanLabel();
                boxContainer.add(textAndBoxLifeSpan.second);

                Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
                boxContainer.add(btnAndColor.first);

                boxContainer.add(addPicture());
                List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxMaxKm.first, textAndBoxLifeSpan.first));
                boxContainer.add(addVehicleBtn(VehicleType.SpyGlider, holderTextDataList, panel, null, btnAndColor.second)); // creat this object
                panel.add(boxContainer, BorderLayout.CENTER);
            }
            case 2 -> { // hybridPlain
                addHybridPlain(panel, boxContainer);
            }
        }
    }
    private void addAmphibious(JPanel panel, Box boxContainer) {
        JLabel tmpLabel = new JLabel();
        tmpLabel.setText("enter amphibious data :");
        boxContainer.add(tmpLabel);

        Tuple<JTextField, Box> textAndBoxModel = getInfo.modelLabel();
        boxContainer.add(textAndBoxModel.second);

        Tuple<JTextField, Box> textAndBoxSubModel = getInfo.subModelLabel();
        boxContainer.add(textAndBoxSubModel.second);

        Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
        boxContainer.add(textAndBoxMaxKm.second);


        Tuple<JTextField, Box> textFieldBoxMaxPassengers = getInfo.maxPassengersLabel();
        boxContainer.add(textFieldBoxMaxPassengers.second);


        Tuple<JTextField, Box> textAndBoxMaxSpeed = getInfo.msxSpeedLabel();
        boxContainer.add(textAndBoxMaxSpeed.second);

        Tuple<JTextField, Box> textAndBoxCountyFlag = getInfo.countryFlagLabel();
        boxContainer.add(textAndBoxCountyFlag.second);

        Tuple<JTextField, Box> textAndBoxNumOfWheels = getInfo.numOfWheelsLabel();
        boxContainer.add(textAndBoxNumOfWheels.second);

        Triple<Boolean, Boolean, Box> tripleWind = getInfo.windRadioButtons(); // first = withTheWind , second = againstTheWind, third = Box
        boxContainer.add(tripleWind.third);

        Tuple<Tuple<Boolean, Boolean>, Tuple<Boolean, Boolean>> twoTuples = new Tuple<>(null, new Tuple<>(tripleWind.first, tripleWind.second));// first tuple = civilianAndMilitary  , second tuple  =  withTheWindOrAgainstTheWind

        Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
        boxContainer.add(btnAndColor.first);
        boxContainer.add(addPicture());

        List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(textAndBoxModel.first, textAndBoxSubModel.first, textAndBoxMaxKm.first, textFieldBoxMaxPassengers.first, textAndBoxMaxSpeed.first, textAndBoxCountyFlag.first, textAndBoxNumOfWheels.first));
        boxContainer.add(addVehicleBtn(VehicleType.Amphibious, holderTextDataList, panel, twoTuples, btnAndColor.second)); // creat this object
        panel.add(boxContainer, BorderLayout.CENTER);
    }

    private void addHybridPlain(JPanel panel, Box boxContainer) {
        JLabel tmpLabel = new JLabel();
        tmpLabel.setText("enter Hybrid Plain data :");
        boxContainer.add(tmpLabel);

        Triple<Boolean, Boolean, Box> civilianAndMilitaryTriple = getInfo.civilianOrMilitaryRadioButtons(); // first = civilian , second = military, third = box
        boxContainer.add(civilianAndMilitaryTriple.third);

        Tuple<JTextField, Box> tupleModel = getInfo.modelLabel();
        boxContainer.add(tupleModel.second);

        Tuple<JTextField, Box> tupleSubModel = getInfo.subModelLabel();
        boxContainer.add(tupleSubModel.second);

        Tuple<JTextField, Box> textAndBoxMaxKm = getInfo.maxKmLabel();
        boxContainer.add(textAndBoxMaxKm.second);

        Tuple<JTextField, Box> textFieldBoxMaxPassengers = getInfo.maxPassengersLabel();
        boxContainer.add(textFieldBoxMaxPassengers.second);

        Tuple<JTextField, Box> textAndBoxMaxSpeed = getInfo.msxSpeedLabel();
        boxContainer.add(textAndBoxMaxSpeed.second);

        Tuple<JTextField, Box> textAndBoxLifeSpan = getInfo.averageLifeSpanLabel();
        boxContainer.add(textAndBoxLifeSpan.second);

        Tuple<JTextField, Box> textAndBoxNumOfWheels = getInfo.numOfWheelsLabel();
        boxContainer.add(textAndBoxNumOfWheels.second);

        Tuple<JTextField, Box> textAndBoxNumOfWheelsFlag = getInfo.countryFlagLabel();
        boxContainer.add(textAndBoxNumOfWheelsFlag.second);

        Triple<Boolean, Boolean, Box> tripleWind = getInfo.windRadioButtons(); // first = withTheWind , second = againstTheWind, third = Box
        boxContainer.add(tripleWind.third);

        Tuple<Tuple<Boolean, Boolean>, Tuple<Boolean, Boolean>> twoTuple = new Tuple<>(new Tuple<>(civilianAndMilitaryTriple.first, civilianAndMilitaryTriple.second), new Tuple<>(tripleWind.first, tripleWind.second)); // first tuple = civilianAndMilitary  , second tuple  =  withTheWindOrAgainstTheWind

        Tuple<Box, JPanel> btnAndColor = getInfo.colorChooserBtn();
        boxContainer.add(btnAndColor.first);

        boxContainer.add(addPicture());
        List<JTextField> holderTextDataList = new ArrayList<>(Arrays.asList(tupleModel.first, tupleSubModel.first, textAndBoxMaxKm.first, textFieldBoxMaxPassengers.first, textAndBoxMaxSpeed.first, textAndBoxLifeSpan.first, textAndBoxNumOfWheels.first, textAndBoxNumOfWheelsFlag.first));
        boxContainer.add(addVehicleBtn(VehicleType.HybridPlane, holderTextDataList, panel, twoTuple, btnAndColor.second)); // creat this object
        panel.add(boxContainer, BorderLayout.CENTER);
    }

    private Box addVehicleBtn(VehicleType vehicleType, List<JTextField> vehicleDataList, JPanel panel, Tuple<Tuple<Boolean, Boolean>, Tuple<Boolean, Boolean>> optionalParams, JPanel colorPanel) {
        System.out.println("the Thread in addVehicleBtn is: " + Thread.currentThread().getName());
        Box box = Box.createHorizontalBox();
        JButton addVehicleBtn = new JButton("Add Vehicle.");
        addVehicleBtn.setBackground(Color.white);
        addVehicleBtn.setSize(100, 100);
        addVehicleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (vehicleType) {
                    case Frigate -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            boolean windDirection = optionalParams.second.second;
                            Frigate frigate = (Frigate) FactoryProvider.getFactory(WATER_VEHICLE).create(FRIGATE, vehicleDataList.get(0).getText(), "0", vehicleDataList.get(1).getText(), vehicleDataList.get(2).getText(), vehicleDataList.get(3).getText(), String.valueOf(windDirection));
                            frigate.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(frigate, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the Frigate");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case CruiseShip -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            CruiseShip cruiseShip = (CruiseShip) FactoryProvider.getFactory(WATER_VEHICLE).create(CRUISE_SHIP, vehicleDataList.get(0).getText(), "0", vehicleDataList.get(1).getText(), vehicleDataList.get(2).getText(), vehicleDataList.get(3).getText(), vehicleDataList.get(5).getText(), vehicleDataList.get(4).getText());
                            cruiseShip.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(cruiseShip, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the CruiseShip");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case Bicycle -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            NormalBicycle bicycle = (NormalBicycle) FactoryProvider.getFactory(LAND_VEHICLE).create(NORMAL_BICYCLE, vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText());
                            bicycle.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(bicycle, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the Bicycle");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case PlayGlider -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            PlayGlider playGlider = (PlayGlider) FactoryProvider.getFactory(AIR_VEHICLE).create(PLAY_GLIDER, "0", vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText());
                            playGlider.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(playGlider, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the Play Glider");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case SpyGlider -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            SpyGlider spyGlider = (SpyGlider) FactoryProvider.getFactory(AIR_VEHICLE).create(SPAY_GLIDER, "0", vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText());
                            spyGlider.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(spyGlider, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the Spy Glider");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case Amphibious -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            boolean windDirection = optionalParams.second.second;
                            // TODO Random random = new Random();
                            Amphibious amphibious = (Amphibious) FactoryProvider.getFactory(LAND_VEHICLE).create(AMPHIBIOUS, vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText(), "0", vehicleDataList.get(2).getText(), vehicleDataList.get(3).getText(), vehicleDataList.get(4).getText(), String.valueOf(windDirection), vehicleDataList.get(5).getText(), vehicleDataList.get(6).getText());
                            amphibious.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(amphibious, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the amphibious");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case Jeep -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (Only one or more empty field will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields !!");
                        else {
                            Jeep jeep = (Jeep) FactoryProvider.getFactory(LAND_VEHICLE).create(JEEP, vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText(), "0", vehicleDataList.get(2).getText(), "5", vehicleDataList.get(3).getText(), vehicleDataList.get(4).getText(), vehicleDataList.get(5).getText());
                            jeep.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(jeep, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the Jeep");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case HybridPlane -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            boolean windDirection = optionalParams.second.second;
                            boolean civilian = optionalParams.first.first;
                            boolean military = optionalParams.first.second;
                            HybridPlane hybridPlane = (HybridPlane) FactoryProvider.getFactory(LAND_VEHICLE).create(HYBRID_PLAIN, vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText(), "0", vehicleDataList.get(2).getText(), vehicleDataList.get(3).getText(), vehicleDataList.get(4).getText(), String.valueOf(civilian), String.valueOf(military), vehicleDataList.get(5).getText(), vehicleDataList.get(6).getText(), String.valueOf(windDirection), vehicleDataList.get(7).getText());
                            hybridPlane.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(hybridPlane, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the hybrid Plane");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                    case ElectricBicycle -> {
                        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
                        Color color = new Color(colorPanel.getBackground().getRGB());
                        if (!inputNotEmpty || (addImageMap.get(Thread.currentThread().getName()) != null && !addImageMap.get(Thread.currentThread().getName())) || color.equals(noColor))
                            JOptionPane.showMessageDialog(null, "Please fill all the empty fields!");
                        else {
                            ElectricBicycle electricBicycle = (ElectricBicycle) FactoryProvider.getFactory(LAND_VEHICLE).create(ELECTRIC_BICYCLE, vehicleDataList.get(0).getText(), vehicleDataList.get(1).getText());
                            electricBicycle.setVehiclesImage(vehiclesImage);
                            Vehicle vehicle = new VehicleColorAndStatusDecorator(electricBicycle, color);
                            vehicleList.add(vehicle);
                            JOptionPane.showMessageDialog(null, "You have successfully added the electric bicycle");
                            addVehicleBox(panel);
                            addImageMap.put(Thread.currentThread().getName(), false);
                            MainWindowSingleton.getInstance().getMyFrame().updatingDataPleaseWait();
                        }
                    }
                }
            }
        });
        box.add(addVehicleBtn);
        return box;
    } // to add Vehicle to array

    private boolean someDetailIsEmpty(List<JTextField> vehicleDataList, Thread thread, Color color) {
        boolean inputNotEmpty = vehicleDataList.stream().noneMatch(textField -> textField.getText().trim().isEmpty()); // like for i= 0 to size if (none is empty it will return true)
        if (color.equals(noColor) || !inputNotEmpty || !addImageMap.get(thread) || addImageMap.get(thread) == null) return false;

        return true;
    }

    protected Box addPicture() { // to add cars image when user add car
        System.out.println("the Tread in addPicture() now is:" + Thread.currentThread().getName() + "\n");
        Box box = Box.createHorizontalBox();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/pictures/placeHolder.png"));
            bufferedImage = resizePic(bufferedImage, 100, 70);
            vehiclesImage.setImage(bufferedImage);
            JLabel label = new JLabel(vehiclesImage);
            box.add(label);
            JButton addPicBtn = new JButton("Add Picture ");
            addPicBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final String currentTread = Thread.currentThread().getName();
                    // Get the current frame
                    Component component = (Component) e.getSource();
                    Window window = SwingUtilities.getWindowAncestor(component);

                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new ImageFilter());
                    fileChooser.setAcceptAllFileFilterUsed(false);

                    int option = fileChooser.showOpenDialog(window); // Pass the window reference here
                    if (option == JFileChooser.APPROVE_OPTION) {
                        new Thread(() -> {
                            File file = fileChooser.getSelectedFile();
                            try {
                                BufferedImage bufferedImage1 = ImageIO.read(file);
                                bufferedImage1 = resizePic(bufferedImage1, 100, 70);
                                vehiclesImage.setImage(bufferedImage1);
                                addImageMap.put(currentTread, true);
                            } catch (Exception x) {
                                x.printStackTrace();
                            }
                            label.setText("File Selected: " + file.getName());
                        }).start();
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

    public static BufferedImage resizePic(BufferedImage img, int newW, int newH) {  // Resize the image as needed
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } // resize the size of ima

    protected void resetPanel(JPanel panel) {
        if (panel != null) {
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
        }
    }

}
