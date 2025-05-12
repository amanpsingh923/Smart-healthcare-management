package com.healthcare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorManagement extends JFrame {
    private JTextField nameField, specializationField, infoField;

    public DoctorManagement() {
        setTitle("Doctor Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Form elements for adding doctor
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(130, 30, 200, 30);
        add(nameField);

        JLabel specializationLabel = new JLabel("Specialization:");
        specializationLabel.setBounds(30, 80, 100, 30);
        add(specializationLabel);
        specializationField = new JTextField();
        specializationField.setBounds(130, 80, 200, 30);
        add(specializationField);

        JLabel infoLabel = new JLabel("Contact Info:");
        infoLabel.setBounds(30, 130, 100, 30);
        add(infoLabel);
        infoField = new JTextField();
        infoField.setBounds(130, 130, 200, 30);
        add(infoField);

        JButton addButton = new JButton("Add Doctor");
        addButton.setBounds(130, 180, 120, 30);
        add(addButton);

        // Add button action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDoctor();
            }
        });
    }

    // Function to add a doctor to the database
    private void addDoctor() {
        String name = nameField.getText();
        String specialization = specializationField.getText();
        String info = infoField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO doctors (name, specialization, info) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, specialization);
            pstmt.setString(3, info);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Doctor added successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding doctor: " + ex.getMessage());
        }
    }
}
