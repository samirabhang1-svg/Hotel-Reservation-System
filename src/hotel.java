import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class hotel {
    private static final String url = "jdbc:mysql://localhost:3306/hotel";
    private static final String username = "root";
    private static final String password = "Samir@123";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            while(true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner scanner = new Scanner(System.in);
                System.out.println("1.Reserve the room");
                System.out.println("2.View the Reservation");
                System.out.println("3.Get Room Number");
                System.out.println("4.Update Reservation");
                System.out.println("5.Delete Reservation");
                System.out.println("6.Exit");
                System.out.println("Enter your choice");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        reserveRoom(connection, scanner);
                        break;
                    case 2:
                        viewReservation(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, scanner);
                        break;
                    case 4:
                        updateReservation(connection, scanner);
                        break;
                    case 5:
                        deleteReservation(connection, scanner);
                        break;
                    case 6:
                        exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("invalid choice, try again");

                }
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e){
            throw new RuntimeException();
        }


    }

    private static void reserveRoom(Connection connection, Scanner scanner )  {
        System.out.println("Enter guest name");
        String guestName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter room number");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter contact number");
        String contactNumber = scanner.next();
        scanner.nextLine();
        String sql = "INSERT INTO Reservations(guest_name, room_no, contact_no) VALUES('" +
                guestName + "'," +
                roomNumber + ",'" +
                contactNumber + "')";
        try( Statement statement=connection.createStatement()) {
            int affactedRows= statement.executeUpdate(sql);
            if(affactedRows>0){
                System.out.println("reservaion successful");

            }
            else  {
                System.out.println("reservation failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void viewReservation( Connection connection )  throws SQLException {
        String sql="select reservation_id,guest_name,room_no,contact_no,reservation_date from reservations";
        try(Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql)) {
            System.out.println("Current Reservations");
            System.out.println("+------------------+---------------+----------------+----------------+------------------+");
            System.out.println("| Reservations id  | Guest Name    | Room Number    | Contact Number | Reservation date | ");
            System.out.println("+------------------+---------------+----------------+----------------+------------------+");
            while(rs.next()){
                int reservationid=rs.getInt("reservation_id");
                String guestName=rs.getString("guest_name");
                int roomNumber=rs.getInt("room_no");
                int contactNumber=rs.getInt("contact_no");
                String reservationDate=rs.getTimestamp("reservation_date").toString();
                System.out.printf("| %-14d | %-15s | %-13d |%-20s |%-19s |%n",reservationid,guestName,roomNumber,contactNumber,reservationDate);
                System.out.println("+-------------------------------------------------------------------------------------+");


            }
        }

    }
    private static void getRoomNumber(Connection connection, Scanner scanner ) throws SQLException {
        try {
            System.out.println("Enter Reservation ID");
            int reservationID = scanner.nextInt();
            System.out.println("Enter Guest Name");
            String guestName = scanner.next();
            String sql = "SELECT room_no FROM Reservations WHERE reservation_id = "
                    + reservationID +
                    " AND guest_name = '" + guestName + "'";
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    int roomNumber = rs.getInt("room_no");
                    System.out.println("room number for Reservation Id " + reservationID + " and guest " + guestName + " is " + roomNumber);


                } else {
                    System.out.println("room number not found for given room number and guest name");


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateReservation(Connection connection, Scanner scanner ) throws SQLException {
        try {
            System.out.println("Enter reservation ID to Update");
            int reservationID = scanner.nextInt();
            scanner.nextLine();
            if(!reservationExists(connection,reservationID)){
                System.out.println("reservation id not found");
                return;

            }
            System.out.println("Enter new guest name");
            String guestName = scanner.next();
            System.out.println("Enter new room number");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter new contact number");
            int contactNumber = scanner.nextInt();
            String sql = "UPDATE Reservations SET guest_name='" + guestName +
                    "', room_no=" + roomNumber +
                    ", contact_no='" + contactNumber +
                    "' WHERE reservation_id=" + reservationID;
            try (Statement statement=connection.createStatement()){
                int affactedRows= statement.executeUpdate(sql);
                if(affactedRows>0){
                    System.out.println("reservation successful");
                }
                else{
                    System.out.println("reservation failed");
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private static void deleteReservation(Connection connection, Scanner scanner ) throws SQLException {
        try {
            System.out.println("Enter reservation ID to Delete");
            int reservationID = scanner.nextInt();
            scanner.nextLine();
            if(!reservationExists(connection,reservationID)){
                System.out.println("reservation id not found");
                return;
            }
            String sql="delete from Reservations where reservation_id='"+reservationID+"'";
            try (Statement statement=connection.createStatement()){
                int affactedRows= statement.executeUpdate(sql);
                if(affactedRows>0){
                    System.out.println("reservation deletion successful");
                }
                else{
                    System.out.println("reservation deletion failed");

                }

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static boolean reservationExists(Connection connection,int reservationID) throws SQLException {
        try {
            String sql = "select reservation_id from Reservations where reservation_id='" + reservationID + "'";
            try (Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static void exit() throws InterruptedException {
        System.out.println("Are you sure you want to exit?");
        int i = 5;
        while (i != 0) {
            System.out.println(".");
            Thread.sleep(1000);
            i--;

        }
        System.out.println();
        System.out.println("thank you for using hotel Reservation System");}}

















