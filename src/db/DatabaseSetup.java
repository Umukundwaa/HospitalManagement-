package db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {

    private Connection conn;

    public DatabaseSetup() {
        this.conn = DatabaseConnection.getConnection();
    }

    public void createTables() {
        try {
            Statement st = conn.createStatement();

            String tableDoctors = "CREATE TABLE IF NOT EXISTS doctors (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(200)," +
                "last_name VARCHAR(255)," +
                "specialty VARCHAR(255)," +
                "phone_number VARCHAR(10)," +
                "email VARCHAR(255) UNIQUE," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

            String tablePatients = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(200)," +
                "last_name VARCHAR(255)," +
                "gender ENUM('Male','Female')," +
                "date_of_birth DATE," +
                "phone_number VARCHAR(10)," +
                "email VARCHAR(255) UNIQUE," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

            String tableAppointments = "CREATE TABLE IF NOT EXISTS appointments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "doctor_id INT," +
                "patient_id INT," +
                "appointment_date DATE," +
                "status ENUM('Scheduled','Completed','Canceled')," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (doctor_id) REFERENCES doctors(id)," +
                "FOREIGN KEY (patient_id) REFERENCES patients(id)" +
                ")";

            String tableMedicalRecords = "CREATE TABLE IF NOT EXISTS medical_records (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "patient_id INT NOT NULL," +
                "doctor_id INT NOT NULL," +
                "diagnosis VARCHAR(255) NOT NULL," +
                "treatment VARCHAR(255) NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (patient_id) REFERENCES patients(id)," +
                "FOREIGN KEY (doctor_id) REFERENCES doctors(id)" +
                ")";

            st.executeUpdate(tableDoctors);
            System.out.println("Doctors table created successfully.");

            st.executeUpdate(tablePatients);
            System.out.println("Patients table created successfully.");

            st.executeUpdate(tableAppointments);
            System.out.println("Appointments table created successfully.");

            st.executeUpdate(tableMedicalRecords);
            System.out.println("Medical Records table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}