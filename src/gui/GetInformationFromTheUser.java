package gui;

import utils.Triple;
import utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GetInformationFromTheUser {
    public GetInformationFromTheUser(){

    }
    public Tuple<Box, JPanel> colorChooserBtn() {
        Box box = Box.createHorizontalBox();
        JButton colorButton = new JButton("Choose Color");
        box.add(colorButton);

        final Color[] selectedColor = new Color[1];
        JPanel colorPanel = new JPanel();
        colorPanel.setSize(1, 1);
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        colorPanel.setVisible(false); // Hide the color panel initially
        colorPanel.setBackground(Color.lightGray);
        box.add(colorPanel);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor[0] = JColorChooser.showDialog(null, "Select a Color", Color.WHITE);
                if (selectedColor[0] != null) {
                    colorPanel.setBackground(selectedColor[0]);
                    colorPanel.setVisible(true); // Show the color panel after a color is chosen
                }
            }
        });
        return new Tuple<>(box, colorPanel);
    }

    public Tuple<JTextField, Box> modelLabel() {
        Box box = Box.createHorizontalBox();
        JLabel modelLabel = new JLabel("Model :");
        modelLabel.setForeground(Color.WHITE);
        modelLabel.setBackground(Color.cyan);
        box.add(modelLabel);

        JTextField modelText = new JTextField();
        box.add(modelText);
        box.setSize(150, 10);
        return new Tuple<>(modelText, box);
    }

    public Tuple<JTextField, Box> numOfWheelsLabel() {
        Box box = Box.createHorizontalBox();
        JLabel numOfWheelsLabel = new JLabel("number of wheels :");
        numOfWheelsLabel.setForeground(Color.WHITE);
        numOfWheelsLabel.setBackground(Color.cyan);
        box.add(numOfWheelsLabel);

        JTextField numOfWheelsText = new JTextField();
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
        return new Tuple<>(numOfWheelsText, box);
    }

    public Tuple<JTextField, Box> maxKmLabel() {
        Box box = Box.createHorizontalBox();
        JLabel maxKmLabel = new JLabel("max Km :");
        maxKmLabel.setForeground(Color.WHITE);
        maxKmLabel.setBackground(Color.cyan);
        box.add(maxKmLabel);

        JTextField maxKmText = new JTextField();
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
        return new Tuple<>(maxKmText, box);
    }

    public Tuple<JTextField, Box> subModelLabel() {
        Box box = Box.createHorizontalBox();
        JLabel subModelLabel = new JLabel("Sub model :");
        subModelLabel.setForeground(Color.WHITE);
        subModelLabel.setBackground(Color.cyan);
        box.add(subModelLabel);

        JTextField submodelText = new JTextField();
        submodelText.setBackground(new Color(255, 255, 255, 255));
        box.add(submodelText);
        box.setSize(150, 10);
        return new Tuple<>(submodelText, box);
    }

    public Tuple<JTextField, Box> msxSpeedLabel() {
        Box box = Box.createHorizontalBox();
        JLabel modelLabel = new JLabel("max speed :");
        modelLabel.setForeground(Color.WHITE);
        modelLabel.setBackground(Color.cyan);
        box.add(modelLabel);

        JTextField maxSpeedText = new JTextField();
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
        return new Tuple<>(maxSpeedText, box);
    }

    public Tuple<JTextField, Box> countryFlagLabel() {
        Box box = Box.createHorizontalBox();
        JLabel countryFlagLabel = new JLabel("Country flag :");
        countryFlagLabel.setForeground(Color.WHITE);
        countryFlagLabel.setBackground(Color.cyan);
        box.add(countryFlagLabel);

        JTextField countryFlagText = new JTextField();
        box.add(countryFlagText);
        box.setSize(150, 10);

        return new Tuple<>(countryFlagText, box);
    }

    public Tuple<JTextField, Box> maxPassengersLabel() {
        Box box = Box.createHorizontalBox();
        JLabel maxOfPassengerLabel = new JLabel("Max passengers :");
        maxOfPassengerLabel.setForeground(Color.WHITE);
        maxOfPassengerLabel.setBackground(Color.cyan);
        box.add(maxOfPassengerLabel);

        JTextField maxOfPassengerText = new JTextField();
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
        return new Tuple<>(maxOfPassengerText, box);
    }

    public Tuple<JTextField, Box> averageLifeSpanLabel() {
        Box box = Box.createHorizontalBox();
        JLabel averageLifeSpanLabel = new JLabel("Average lifeSpan :");
        averageLifeSpanLabel.setForeground(Color.WHITE);
        averageLifeSpanLabel.setBackground(Color.cyan);
        box.add(averageLifeSpanLabel);

        JTextField averageLifeSpanText = new JTextField();
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
        return new Tuple<>(averageLifeSpanText, box);
    }

    public Tuple<JTextField, Box> avgConsumptionLabel() {
        Box box = Box.createHorizontalBox();
        JLabel avgConsumptionLabel = new JLabel("Average Consumption :");
        avgConsumptionLabel.setForeground(Color.WHITE);
        avgConsumptionLabel.setBackground(Color.cyan);
        box.add(avgConsumptionLabel);

        JTextField avgConsumptionText = new JTextField();
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
        return new Tuple<>(avgConsumptionText, box);
    }

    public Triple<Boolean, Boolean, Box> windRadioButtons() {
        Box box = Box.createHorizontalBox();
        JRadioButton withTheWindButton = new JRadioButton("with the wind");
        JRadioButton againstTheWindButton = new JRadioButton("against the wind");

        withTheWindButton.setBackground(Color.white);
        withTheWindButton.setForeground(Color.blue);
        againstTheWindButton.setBackground(Color.white);
        againstTheWindButton.setForeground(Color.blue);

        box.add(againstTheWindButton);
        box.add(withTheWindButton);

        boolean[] withTheWind = new boolean[1];
        boolean[] againstTheWind = new boolean[1];
        withTheWindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withTheWind[0] = true;
            }
        });
        againstTheWindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                againstTheWind[0] = true;
            }
        });
        return new Triple<>(withTheWind[0], againstTheWind[0], box);
    }
    public Triple<Boolean, Boolean, Box> civilianOrMilitaryRadioButtons() {
        Box box = Box.createHorizontalBox();
        JRadioButton civilianButton = new JRadioButton("civilian");
        JRadioButton militaryButton = new JRadioButton("military");

        civilianButton.setBackground(Color.white);
        civilianButton.setForeground(Color.blue);
        militaryButton.setForeground(Color.blue);

        box.add(civilianButton);
        box.add(militaryButton);

        final boolean[] civilian = new boolean[1];
        final boolean[] military = new boolean[1];
        civilianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                civilian[0] = true;
            }
        });
        militaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                military[0] = true;
            }
        });
        return new Triple<>(civilian[0], military[0], box);
    }
    public Tuple<JTextField, Box> kmLabel() {
        System.out.println("the Tread in kmLabel now is:" + Thread.currentThread().getName());
        Box box = Box.createHorizontalBox();
        JLabel kmLabel = new JLabel("Enter the number of kilometers traveled :");
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

        return new Tuple<>(kmText, box);
    }


}
