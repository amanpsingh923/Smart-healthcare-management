package com.healthcare;

import javax.swing.*;
import java.awt.*;

public class HealthcareSystemGUI extends JFrame {
    public HealthcareSystemGUI() {
        // Set up the main frame
        setTitle("Healthcare Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  // Use BorderLayout for better placement

        // Create a panel to hold buttons and align them in the center
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));  // Arrange buttons vertically

        // Create buttons
        JButton btnDoctorManagement = new JButton("Doctor Management");
        JButton btnManageAppointments = new JButton("Manage Appointments");
        JButton btnSearchAppointments = new JButton("Search Appointments");

        // Align buttons in the center
        btnDoctorManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnManageAppointments.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchAppointments.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the panel with gaps between them
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Gap before the first button
        buttonPanel.add(btnDoctorManagement);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Gap between buttons
        buttonPanel.add(btnManageAppointments);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Gap between buttons
        buttonPanel.add(btnSearchAppointments);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Gap after the last button

        // Add an action listener to each button
        btnDoctorManagement.addActionListener(e -> new DoctorManagement().setVisible(true));
        btnManageAppointments.addActionListener(e -> new AppointmentManager().setVisible(true));
        btnSearchAppointments.addActionListener(e -> new SearchAppointments().setVisible(true));

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Launch the GUI
        HealthcareSystemGUI gui = new HealthcareSystemGUI();
        gui.setVisible(true);
    }
}
