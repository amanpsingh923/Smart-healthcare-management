package com.healthcare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentManager extends JFrame {
    private JTextField patientNameField, doctorIdField, dateField;

    public AppointmentManager() {
        setTitle("Appointment Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Form elements for adding an appointment
        JLabel patientLabel = new JLabel("Patient Name:");
        patientLabel.setBounds(30, 30, 100, 30);
        add(patientLabel);
        patientNameField = new JTextField();
        patientNameField.setBounds(130, 30, 200, 30);
        add(patientNameField);

        JLabel doctorLabel = new JLabel("Doctor ID:");
        doctorLabel.setBounds(30, 80, 100, 30);
        add(doctorLabel);
        doctorIdField = new JTextField();
        doctorIdField.setBounds(130, 80, 200, 30);
        add(doctorIdField);

        JLabel dateLabel = new JLabel("Appointment Date:");
        dateLabel.setBounds(30, 130, 120, 30);
        add(dateLabel);
        dateField = new JTextField();
        dateField.setBounds(150, 130, 180, 30);
        add(dateField);

        JButton addButton = new JButton("Add Appointment");
        addButton.setBounds(130, 180, 150, 30);
        add(addButton);

        // Add button action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });
    }

    // Function to add an appointment to the database
    private void addAppointment() {
        String patientName = patientNameField.getText();
        String doctorId = doctorIdField.getText();
        String date = dateField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO appointments (patient_name, doctor_id, appointment_date) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, patientName);
            pstmt.setString(2, doctorId);
            pstmt.setString(3, date);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Appointment added successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding appointment: " + ex.getMessage());
        }
    }
}
