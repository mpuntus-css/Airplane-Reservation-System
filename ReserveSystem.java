
import java.util.Scanner;

public class ReserveSystem {

    



        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Created airplane with 50 seats
        int numberOfSeats = 50;
        boolean[] seats = new boolean[numberOfSeats];
        String[] passengerNames = new String[numberOfSeats];
        
        
        final int SEAT_CAPACITY_LIMIT = 5;

        // Created passenger names array with empty strings
        for (int i = 0; i < numberOfSeats; i++) {
            passengerNames[i] = "";  
        }

         

        while (true) {
            System.out.println("1. Display Seat Map");
            System.out.println("2. Display Available Seats");
            System.out.println("3. Display Reserved Seats");
            System.out.println("4. Reserve a Seat");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            // Input validation
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Consume the invalid input
                System.out.print("Enter your choice: ");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (choice == 1) {
                
                    displaySeatMap(seats);
            } else if (choice == 2) {       
             
                    displayAvailableSeats(seats);
                }        
                else if (choice == 3) {
                    displayReservedSeats(seats, passengerNames);
                }
                else if (choice == 4) {
                    System.out.print("Enter seat number to reserve: ");
                
                    // Input validation
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();  // Consume the invalid input
                        System.out.print("Enter seat number to reserve: ");
                    }

                    int seatToReserve = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.nextLine();
                    reserveSeat(seats, passengerNames, seatToReserve, passengerName, SEAT_CAPACITY_LIMIT);
                }
                else if (choice == 5) {
                    System.out.print("Enter seat number to cancel reservation: ");

                    // Input validation
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();  // Consume the invalid input
                        System.out.print("Enter seat number to cancel reservation: ");
                    }

                    int seatToCancel = scanner.nextInt();
                    cancelReservation(seats, passengerNames, seatToCancel);
                }
                else if (choice == 6) {
                    System.out.println("Exiting the program. Thank you!");
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    

    private static void displaySeatMap(boolean[] seats) {
        System.out.println("Seat Map:");
        System.out.println("A: Available | R: Reserved");

        for (int i = 0; i < seats.length; i++) {
            // Start a new row after every 10 seats
            if (i % 10 == 0 && i != 0) {
                System.out.println();  
            }

            if (seats[i]) {
                System.out.print(" R ");
            } else {
                System.out.print(" A ");
            }
        }
        System.out.println();
    }

    private static void displayAvailableSeats(boolean[] seats) {
        System.out.println("Available Seats:");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }

    private static void displayReservedSeats(boolean[] seats, String[] passengerNames) {
        System.out.println("Reserved Seats:");
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                System.out.println("Seat " + (i + 1) + ": " + passengerNames[i]);
            }
        }
    }

    private static void reserveSeat(boolean[] seats, String[] passengerNames, int seatNumber, String passengerName, int seatCapacityLimit) {
        if (seatNumber <= 0 || seatNumber > seats.length || seats[seatNumber - 1]) {
            System.out.println("Unable to reserve seat. Please check seat availability and passenger name.");
            return;
        }

        if (passengerNames[seatNumber - 1].equals("") && !passengerName.equals("")) {
            if (countReservedSeatsForPassenger(passengerNames, passengerName) >= seatCapacityLimit) {
                System.out.println("Unable to reserve seat. Exceeded seat capacity limit for the passenger.");
                return;
            }

            passengerNames[seatNumber - 1] = passengerName;
            seats[seatNumber - 1] = true;
            System.out.println("Seat " + seatNumber + " reserved successfully for " + passengerName);
        } else {
            System.out.println("Unable to reserve seat. Please check seat availability and passenger name.");
        }
    }

    private static void cancelReservation(boolean[] seats, String[] passengerNames, int seatNumber) {
        if (seatNumber <= 0 || seatNumber > seats.length || !seats[seatNumber - 1]) {
            System.out.println("Seat " + seatNumber + " is not reserved.");
        } else {
            seats[seatNumber - 1] = false;
            passengerNames[seatNumber - 1] = "";
            System.out.println("Reservation canceled for seat " + seatNumber);
        }
    }

    private static int countReservedSeatsForPassenger(String[] passengerNames, String passengerName) {
        int count = 0;
        for (String name : passengerNames) {
            if (!name.equals("") && name.equals(passengerName)) {
                count++;
            }
        }
        return count;
    }
}
