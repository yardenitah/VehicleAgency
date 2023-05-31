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

//import Vehicless.VehicleAgency;
//import java.util.Scanner;

//        Scanner scanner = new Scanner(System.in);
//        VehicleAgency agency = new VehicleAgency();

//        String option;
//        agency.addVehicle();
//        label:
//        while (true) {
//            System.out.println("\n1. Buy Vehicle\n2. test drive\n3. reset kilometer\n4. Change of flag for marine vehicles\n5. Add a car to inventory\n6. Leve");
//            option = scanner.nextLine();
//
//            switch (option) {
//                case "1" -> {
//                    agency.printAllVehicles();
//                    agency.buy();
//                }
//                case "2" -> agency.testDrive();
//                case "3" -> agency.resetKm();
//                case "4" -> agency.resetFlag();
//                case "5" -> agency.addVehicle();
//                case "6" -> {
//                    System.out.println("Bye Bye....");
//                    break label;
//                }
//                default -> System.out.println("invalid input try again.");
//            }
//        }
//        scanner.close();
//    }