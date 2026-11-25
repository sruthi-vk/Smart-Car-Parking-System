
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ParkingManagement {

    static Car[] slots = new Car[5];
    static Scanner sc = new Scanner(System.in);

    // Display parking slots
    static void displaySlots() {

        System.out.println("\n--- PARKING STATUS ---");

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] == null) {
                System.out.println("Slot " + (i + 1) + " : Available");
            } else {
                System.out.println("Slot " + (i + 1) +
                        " : Occupied - " + slots[i].carNumber);
            }
        }
    }

    // Park a car
    static void parkCar() {

        System.out.print("Enter Car Number: ");
        String number = sc.nextLine().trim().toUpperCase();

        if (number.isEmpty()) {
            System.out.println("Invalid car number!");
            return;
        }

        for (Car car : slots) {
            if (car != null && car.carNumber.equals(number)) {
                System.out.println("Car is already parked!");
                return;
            }
        }

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] == null) {

                slots[i] = new Car(number);

                System.out.println("Car parked successfully!");
                System.out.println("Assigned Slot: " + (i + 1));
                return;
            }
        }

        System.out.println("Parking Full! No slots available.");
    }

    // Remove a car and calculate fee
    static void removeCar() {

        System.out.print("Enter Car Number: ");
        String number = sc.nextLine().trim().toUpperCase();

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] != null &&
                slots[i].carNumber.equals(number)) {

                LocalDateTime exitTime = LocalDateTime.now();

                long minutes = Math.max(1,
                    Duration.between(
                        slots[i].entryTime, exitTime
                    ).toMinutes());

                long hours = (minutes + 59) / 60;

                long fee = hours * 20;

                System.out.println("\n--- PARKING BILL ---");
                System.out.println("Car Number: " + number);
                System.out.println("Parking Duration: " + minutes + " minutes");
                System.out.println("Total Hours: " + hours);
                System.out.println("Parking Fee: Rs." + fee);

                slots[i] = null;

                System.out.println("Car removed successfully!");
                return;
            }
        }

        System.out.println("Car not found!");
    }

    // Display parked cars
    static void displayParkedCars() {

        System.out.println("\n--- PARKED VEHICLES ---");

        boolean found = false;

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] != null) {

                System.out.println("Slot " + (i + 1) +
                        " : " + slots[i].carNumber);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No cars are currently parked.");
        }
    }

    // Main method
    public static void main(String[] args) {

        int choice=0;

        do {

            System.out.println("\n==============================");
            System.out.println(" SMART CAR PARKING SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Park a Car");
            System.out.println("2. Remove a Car");
            System.out.println("3. View Parking Slots");
            System.out.println("4. View Parked Cars");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    parkCar();
                    break;

                case 2:
                    removeCar();
                    break;

                case 3:
                    displaySlots();
                    break;

                case 4:
                    displayParkedCars();
                    break;

                case 5:
                    System.out.println("Thank you for using our system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
