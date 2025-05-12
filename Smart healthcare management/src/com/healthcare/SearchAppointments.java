package com.healthcare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchAppointments extends JFrame {
    private JTextField patientNameField;
    private JTextArea resultArea;

    public SearchAppointments() {
        setTitle("Search Appointments");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Search field for patient name
        JLabel patientLabel = new JLabel("Patient Name:");
        patientLabel.setBounds(30, 30, 100, 30);
        add(patientLabel);
        patientNameField = new JTextField();
        patientNameField.setBounds(130, 30, 200, 30);
        add(patientNameField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(130, 80, 120, 30);
        add(searchButton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setBounds(30, 130, 300, 120);
        add(resultArea);

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchAppointment();
            }
        });
    }

    // Function to search an appointment in the database
    private void searchAppointment() {
        String patientName = patientNameField.getText();
        resultArea.setText("");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM appointments WHERE patient_name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, patientName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String result = "Appointment ID: " + rs.getInt("appointment_id") +
                        ", Doctor ID: " + rs.getInt("doctor_id") +
                        ", Date: " + rs.getString("appointment_date") + "\n";
                resultArea.append(result);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching appointment: " + ex.getMessage());
        }
    }
}
