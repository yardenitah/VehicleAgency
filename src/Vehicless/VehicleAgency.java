//package Vehicless;
//
//import javax.swing.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class VehicleAgency {
//    Scanner scanner = new Scanner(System.in);
//    private final ArrayList<Vehicles> arr;
//
//    public VehicleAgency() {
//        this.arr = new ArrayList<>();
//    }
//    public void addVehicle() {
//        boolean flag = true;
//        String option;
//        String option2;
//        do {
//            System.out.println("\n\n1. add vehicle\n2. go to menu\n");
//            option = scanner.nextLine();
//            if (option.equals("1")) {
//                do {
//                    System.out.println("what kind of vehicle: \n1. Water vehicle  2. AirVehicle  3. Land vehicle");
//                    option2 = scanner.nextLine();
//                    switch (option2) {
//                        case "1" -> {
//                            addWaterVehicle();
//                            System.out.println("Water vehicle successfully added");
//                        }
//                        case "2" -> {
//                            addAirVehicle();
//                            System.out.println("Air vehicle successfully added");
//                        }
//                        case "3" -> {
//                            addLandVehicle();
//                            System.out.println("Land vehicle successfully added");
//                        }
//                        default -> {
//                            System.out.println("Invalid input try again: ");
//                            flag = false;
//                        }
//                    }
//                } while (!flag);
//            } else if (!option.equals("2"))
//                System.out.println("Invalid input try again: ");
//        } while (!option.equals("2"));
//    }
//    public void addWaterVehicle() {
//        boolean flag, withTheWind = false;
//        String option, option2, model, countryFlag;
//        int maxKm, maxOfPassenger, maxSpeed, lifeTime;
//
//        do {
//            flag = true;
//            System.out.println("what kind of water vehicle: 1. Frigate  2. Cruise ship  3. amphibious");
//            option = scanner.nextLine();
//            switch (option) {
//                case "1" -> {
//                    System.out.println("enter: model, maxKilometers, maxOfPassengers, maxSpeed : ");
//                    model = scanner.nextLine();
//                    maxKm = scanner.nextInt();
//                    maxOfPassenger = scanner.nextInt();
//                    scanner.nextLine();
//                    maxSpeed = scanner.nextInt();
//                    System.out.println("This Frigate was sailing:  1. with the wind   2. against the wind");
//
//                    while (true) {
//                        option2 = scanner.nextLine();
//                        if (option2.equals("1")) {
//                            withTheWind = true;
//                            break;
//                        } else if (option2.equals("2")) break;
////                       else System.out.println("invalid input tray again");
//                    }
//                    Frigate frigate = new Frigate(model, 0, maxKm, maxOfPassenger, maxSpeed, withTheWind);
//                    arr.add(frigate);
//                }
//                case "2"-> {
//                    System.out.println("enter: model, country flag, maxKilometers, maxOfPassengers, maxSpeed, life time: ");
//                    model = scanner.nextLine();
//                    countryFlag = scanner.nextLine();
//                    maxKm = scanner.nextInt();
//                    maxOfPassenger = scanner.nextInt();
//                    maxSpeed = scanner.nextInt();
//                    lifeTime = scanner.nextInt();
//
//                    CruiseShip cruiseShip = new CruiseShip(model, 0, maxKm, maxOfPassenger, maxSpeed, countryFlag, lifeTime);
//                    arr.add(cruiseShip);
//                }
//                case "3"->{
//                    addAmphibious();
//                }
//                default -> {
//                    System.out.println("Invalid input try again: ");
//                    flag = false;
//                }
//            }
//        } while (!flag);
//    }
//    private void addAmphibious(){
//        boolean withTheWind = false;
//        String option2 = null, model, countryFlag, subModel;
//        int maxKm, maxOfPassenger, maxSpeed, numOfWheels, lifeTime;
//
//        System.out.println("enter: model, subModel, country flag, maxKilometers, maxOfPassengers, maxSpeed, number of wheels: ");
//        model = scanner.nextLine();
//        subModel = scanner.nextLine();
//        countryFlag = scanner.nextLine();
//        maxKm = scanner.nextInt();
//        maxOfPassenger = scanner.nextInt();
//        maxSpeed = scanner.nextInt();
//        numOfWheels = scanner.nextInt();
//        System.out.println("This Frigate was sailing:  1. with the wind   2. against the wind");
//        do {
//            option2 = scanner.nextLine();
//            if (option2.equals("1")) {
//                withTheWind = true;
//                break;
//            } else if (option2.equals("2")) {
//                break;
//            } else
//                System.out.println("invalid input try again");
//        } while (true);
//
//        Amphibious amphibious = new Amphibious(model,subModel, 0, maxKm, maxOfPassenger, maxSpeed,withTheWind, countryFlag, numOfWheels);
//        arr.add(amphibious);
//    }
//    public void addAirVehicle() {
//        boolean flag;
//        String option, model;
//        int maxKm;
//        float avg;
//
//        do {
//            flag = true;
//            System.out.println("what kind of air vehicle: 1. Play glider  2. Spay glider");
//            option = scanner.nextLine();
//            switch (option) {
//                case "1" -> {
//                    System.out.print("enter:  max kilometer and  averageLifeSpan: ");
//                    maxKm = scanner.nextInt();
//                    avg = scanner.nextInt();
//                    PlayGlider playGlider = new PlayGlider(0, maxKm, avg);
//                    arr.add(playGlider);
//                }
//                case "2" -> {
//                    System.out.print("enter:  max kilometer and  averageLifeSpan: ");
//                    maxKm = scanner.nextInt();
//                    avg = scanner.nextInt();
//                    SpyGlider spyGlider = new SpyGlider(0, maxKm, avg);
//                    arr.add(spyGlider);
//                }
//                default -> {
//                    System.out.println("Invalid input try again: ");
//                    flag = false;
//                }
//            }
//        } while (!flag);
//    }
//    public void addLandVehicle() {
//        boolean flag;
//        String option, model, subModel;
//        int maxKm, maxOfPassenger, maxSpeed;
//        float avgConsumption, lifeAvg;
//
//        do {
//            flag = true;
//            System.out.println("what kind of land vehicle: 1. Jeep  2. Bicycle");
//            option = scanner.nextLine();
//            switch (option) {
//                case "1" -> {
//                    System.out.print("enter: model, sub model, max Kilometers, max of passengers, max speed, Average consumption, average Life span : ");
//                    model = scanner.nextLine();
//                    subModel = scanner.nextLine();
//                    maxKm = scanner.nextInt();
//                    maxOfPassenger = scanner.nextInt();
//                    maxSpeed = scanner.nextInt();
//                    avgConsumption = scanner.nextFloat();
//                    lifeAvg = scanner.nextInt();
//
//                    Jeep jeep = new Jeep(model, subModel, 0, maxKm, maxOfPassenger, maxSpeed, avgConsumption, lifeAvg);
//                    arr.add(jeep);
//                }
//                case "2" -> {
//                    System.out.print("enter: model, sub model :");
//                    model = scanner.nextLine();
//                    subModel = scanner.nextLine();
//
//                    Bicycle bicycle = new Bicycle(model, subModel);
//                    arr.add(bicycle);
//                }
//                case "3"->{
//                    addAmphibious();
//                }
//                default -> {
//                    System.out.println("Invalid input try again: ");
//                    flag = false;
//                }
//            }
//        } while (!flag);
//    }
//    private boolean isContains(Vehicles vehicles) {
//        return indexOf(vehicles) != -1;
//    }
//    private int indexOf(Vehicles vehicles) {
//        int ans = -1;
//        for (int i = 0; i < arr.size(); i++) {
//            if (this.arr.get(i).equals(vehicles)) {
//                ans = i;
//                break;
//            }
//        }
//        return ans;
//    }
//    private void removeVehicle(Vehicles vehicle) {
//        String option;
//        System.out.println("1. remove vehicle \n 2.go to menu");
//        do {
//            option = scanner.nextLine();
//            if (option.equals("1")) {
//                if (indexOf(vehicle) != -1) {
//                    arr.remove(indexOf(vehicle));
//                    System.out.println("Vehicle removed successfully");
//                } else System.out.println("This vehicle is not available in stock.");
//            }
//            System.out.println("Invalid input try again: ");
//        } while (!option.equals("2"));
//    }
//    public void resetKm() {
//        for (Vehicles vehicles : arr) {
//            vehicles.kilometers = 0;
//        }
//    }
//    public void resetFlag() {
//        String newFlag;
//        System.out.println("to the flag of which country you would like to change");
//        newFlag = scanner.nextLine();
//        for (int i = 0; i < arr.size(); i++) {
//            if (!(arr.get(i) instanceof WaterVehicle)) continue;
//            ((WaterVehicle) arr.get(i)).setCountryFlag(newFlag);
//        }
//    }
//    public void testDrive() {
//        int testDriveKm;
//        int index = AskingVehicleDetails();
//        if (index != -3) {
//            do {
//                System.out.print("What is the distance of the test drive that you made :");
//                testDriveKm = scanner.nextInt();
//                if (testDriveKm <= 0) System.out.println("The travel distance must be positive");
//            } while (testDriveKm <= 0);
//            arr.get(index).move(arr.get(index).model, testDriveKm, arr.get(index).maxKilometers, arr.get(index).maxOfPassengers, arr.get(index).maxSpeed);
//        } else System.out.print("No matching vehicle found");
//    }
//    public void printAllVehicles() {
//        if (arr.size() == 0) {
//            System.out.println("No vehicles in stock");
//            return;
//        }
//        boolean flag = false;
//        System.out.println("All the land vehicle: \n");
//        for (Vehicles vehicles : arr) {
//            if (vehicles instanceof LandVehicle || vehicles instanceof Amphibious) {
//                System.out.println(vehicles.toString());
//                System.out.println();
//                flag = true;
//            }
//        }
//        if (!flag) System.out.println("No land vehicles in stock.");
//
//        flag = false;
//        System.out.println("\nAll the Air vehicle: \n");
//        for (Vehicles vehicles : arr) {
//            if (vehicles instanceof AirVehicle) {
//                System.out.println(vehicles.toString());
//                System.out.println();
//                flag = true;
//            }
//        }
//        if (!flag) System.out.println("No air vehicles in stock.");
//
//        flag = false;
//        System.out.println("\nAll the Water vehicle: \n");
//        for (Vehicles vehicles : arr) {
//            if (vehicles instanceof WaterVehicle) {
//                System.out.println(vehicles.toString());
//                System.out.println();
//                flag = true;
//            }
//        }
//        if (!flag) System.out.println("No water vehicles in stock.");
//    }
//    public void buy() {
//        String option;
//        int index;
//        if (arr.size() == 0) {
//            System.out.println("No vehicles in stock");
//            return;
//        }
//        System.out.println("What type of vehicle would you like to buy: \n1. Water vehicle  2. AirVehicle  3. Land vehicle");
//
//        label:
//        while (true) {
//            option = scanner.nextLine();
//            switch (option) {
//                case "1":
//                    index = AskingWaterVehicleDetails();
//                    if (index != -3) {
//                        arr.remove(index);
//                        System.out.println("You have successfully bought the vehicle");
//                    }
//                    break label;
//                case "2":
//                    index = AskingAirVehicleDetails();
//                    if (index != -3) {
//                        arr.remove(index);
//                        System.out.println("You have successfully bought the vehicle");
//                    }
//                    break label;
//                case "3":
//                    index = AskingLandVehicleDetails();
//                    if (index != -3) {
//                        arr.remove(index);
//                        System.out.println("You have successfully bought the vehicle");
//                    }
//                    break label;
//                default:
//                    System.out.println("invalid input try again");
//                    break;
//            }
//        }
//    }
//    private int AskingVehicleDetails() {
//        String option;
//        int index;
//        System.out.println("What type of vehicle would you like to buy: \n1. Water vehicle  2. AirVehicle  3. Land vehicle");
//
//        label:
//        while (true) {
//            option = scanner.nextLine();
//            switch (option) {
//                case "1":
//                    index = AskingWaterVehicleDetails();
//                    if (index != -3) {
//                        return index;
//                    }
//                    break label;
//                case "2":
//                    index = AskingAirVehicleDetails();
//                    if (index != -3) {
//                        return index;
//                    }
//                    break label;
//                case "3":
//                    index = AskingLandVehicleDetails();
//                    if (index != -3) {
//                        return index;
//                    }
//                    break label;
//                default:
//                    System.out.println("invalid input try again");
//                    break;
//            }
//        }
//        return -3;
//    }
//    private int AskingLandVehicleDetails() {
//        boolean flag;
//        String option, model, subModel;
//        int km, maxKm, maxOfPassenger, maxSpeed;
//        float avgConsumption, lifeAvg;
//
//        do {
//            flag = true;
//            System.out.println("what kind of land vehicle: 1. Jeep  2. Bicycle");
//            option = scanner.nextLine();
//            switch (option) {
//                case "1" -> {
//                    System.out.print("enter: model, sub model, Kilometer, max Kilometers, max of passengers, max speed, Average consumption, average Life span : ");
//                    model = scanner.nextLine();
//                    subModel = scanner.nextLine();
//                    km = scanner.nextInt();
//                    maxKm = scanner.nextInt();
//                    maxOfPassenger = scanner.nextInt();
//                    maxSpeed = scanner.nextInt();
//                    avgConsumption = scanner.nextFloat();
//                    lifeAvg = scanner.nextInt();
//
//                    Jeep temp = new Jeep(model, subModel, km, maxKm, maxOfPassenger, maxSpeed, avgConsumption, lifeAvg);
//                    if (isContains(temp)) {
//                        return indexOf(temp);
//                    } else System.out.println("The vehicle details you entered do not exist in the system try again");
//                }
//                case "2" -> {
//                    System.out.print("enter: model, sub model :");
//                    model = scanner.nextLine();
//                    subModel = scanner.nextLine();
//
//                    Bicycle temp = new Bicycle(model, subModel);
//                    if (isContains(temp)) {
//                        return indexOf(temp);
//                    } else System.out.println("The vehicle details you entered do not exist in the system try again");
//                }
//                case "3"->{
//                   return AskingAmphibiousVehicleDetails();
//                }
//                default -> {
//                    System.out.println("Invalid input try again: ");
//                    flag = false;
//                }
//            }
//        } while (!flag);
//        return -3;
//    }
//    private int AskingAirVehicleDetails() {
//        boolean flag;
//        String option;
//        int km, maxKm;
//        float avg;
//
//        do {
//            flag = true;
//            System.out.println("what kind of air vehicle: 1. Play glider  2. Spay glider");
//            option = scanner.nextLine();
//            switch (option) {
//                case "1" -> {
//                    System.out.print("enter kilometer, max kilometer and  averageLifeSpan: ");
//                    km = scanner.nextInt();
//                    maxKm = scanner.nextInt();
//                    avg = scanner.nextInt();
//                    PlayGlider temp = new PlayGlider(km, maxKm, avg);
//                    if (isContains(temp)) {
//                        return indexOf(temp);
//                    } else System.out.println("The vehicle details you entered do not exist in the system try again");
//                }
//                case "2" -> {
//                    System.out.print("enter kilometer, max kilometer and  averageLifeSpan: ");
//                    km = scanner.nextInt();
//                    maxKm = scanner.nextInt();
//                    avg = scanner.nextInt();
//                    SpyGlider temp = new SpyGlider(km, maxKm, avg);
//                    if (isContains(temp)) {
//                        return indexOf(temp);
//                    } else System.out.println("The vehicle details you entered do not exist in the system try again");
//                }
//                default -> {
//                    System.out.println("Invalid input try again: ");
//                    flag = false;
//                }
//            }
//        } while (!flag);
//        return -3;
//    }
//    private int AskingWaterVehicleDetails() {
//        Scanner scanner = new Scanner(System.in);
//        boolean flag, withTheWind;
//        String option, option2, model, countryFlag;
//        int km, maxKm, maxOfPassenger, maxSpeed, lifeTime;
//
//        do {
//            flag = true;
//            System.out.println("what kind of water vehicle: 1. Frigate  2. Cruise ship  3. Amphibious ");
//            option = scanner.nextLine();
//            switch (option) {
//                case "1" -> {
//                    System.out.println("enter: model, kilometers, maxKilometers, maxOfPassengers, maxSpeed : ");
//                    model = scanner.nextLine();
//                    km = scanner.nextInt();
//                    maxKm = scanner.nextInt();
//                    maxOfPassenger = scanner.nextInt();
//                    maxSpeed = scanner.nextInt();
//                    System.out.println("This Frigate was sailing:  1. with the wind   2. against the wind");
//                    do {
//                        option2 = scanner.nextLine();
//                        if (option2.equals("1")) {
//                            withTheWind = true;
//                            break;
//                        } else if (option2.equals("2")) {
//                            withTheWind = false;
//                            break;
//                        } else
//                            System.out.println("invalid input try again");
//                    } while (true);
//                    Frigate temp = new Frigate(model, km, maxKm, maxOfPassenger, maxSpeed, withTheWind);
//                    if (isContains(temp)) {
//                        return indexOf(temp);
//                    } else
//                        System.out.println("The vehicle details you entered do not exist in the system try again");
//                }
//                case "2" -> {
//                    System.out.println("enter: model, country flag, kilometers, maxKilometers, maxOfPassengers, maxSpeed, life time: ");
//                    model = scanner.nextLine();
//                    countryFlag = scanner.nextLine();
//                    km = scanner.nextInt();
//                    maxKm = scanner.nextInt();
//                    maxOfPassenger = scanner.nextInt();
//                    maxSpeed = scanner.nextInt();
//                    lifeTime = scanner.nextInt();
//
//                    CruiseShip temp = new CruiseShip(model, km, maxKm, maxOfPassenger, maxSpeed, countryFlag, lifeTime);
//                    if (isContains(temp)) {
//                        return indexOf(temp);
//                    } else
//                        System.out.println("The vehicle details you entered do not exist in the system try again");
//                }
//                case "3"->{
//                   return AskingAmphibiousVehicleDetails();
//                }
//                default -> {
//                    System.out.println("Invalid input try again: ");
//                    flag = false;
//                }
//            }
//        } while (!flag);
//        return -3;
//    }
//    private int AskingAmphibiousVehicleDetails(){
//        Scanner scanner = new Scanner(System.in);
//        boolean withTheWind;
//        String  option2, model, countryFlag, subModel;
//        int km, maxKm, maxOfPassenger, maxSpeed, numOfWheels;
//
//        System.out.println("enter: model, subModel, country flag, kilometers, maxKilometers, maxOfPassengers, maxSpeed, number of wheels: ");
//        model = scanner.nextLine();
//        subModel = scanner.nextLine();
//        countryFlag = scanner.nextLine();
//        km = scanner.nextInt();
//        maxKm = scanner.nextInt();
//        maxOfPassenger = scanner.nextInt();
//        maxSpeed = scanner.nextInt();
//        numOfWheels = scanner.nextInt();
//        System.out.println("This Frigate was sailing:  1. with the wind   2. against the wind");
//        do {
//            option2 = scanner.nextLine();
//            if (option2.equals("1")) {
//                withTheWind = true;
//                break;
//            } else if (option2.equals("2")) {
//                withTheWind = false;
//                break;
//            } else
//                System.out.println("invalid input try again");
//        } while (true);
//
//        Amphibious temp = new Amphibious(model,subModel, km, maxKm, maxOfPassenger, maxSpeed,withTheWind, countryFlag, numOfWheels);
//        if (isContains(temp)) {
//            return indexOf(temp);
//        } else
//            System.out.println("The vehicle details you entered do not exist in the system try again");
//        return -3;
//    }
//
//}