package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;

import javax.swing.*;

public class AddManufacturerPanel extends MainPanel {

	public AddManufacturerPanel(SouvenirController controller) {
		super(controller);

		JLabel nameLabel = new JLabel("Enter the name of manufacturer");
		JTextField nameField = new JTextField(25);
		this.add(nameLabel);
		this.add(nameField);

		JLabel countryLabel = new JLabel("Enter the country of manufacturer");
		JTextField countryField = new JTextField(25);
		this.add(countryLabel);
		this.add(countryField);

		JLabel emailLabel = new JLabel("Enter the email address");
		JTextField emailField = new JTextField(25);
		this.add(emailLabel);
		this.add(emailField);

		JLabel phoneLabel = new JLabel("Enter the phone");
		JTextField phoneField = new JTextField(25);
		this.add(phoneLabel);
		this.add(phoneField);

		JButton saveManufacturer = new JButton("Save manufacturer");
		saveManufacturer.addActionListener(e -> {
			if (controller.addManufacturer(nameField.getText(), countryField.getText(),
					emailField.getText(), phoneField.getText())) {
				saveManufacturer.setText("Saved!");
				saveManufacturer.setEnabled(false);
			} else {
				saveManufacturer.setText("Wrong data!");
			}
		});
		this.add(saveManufacturer);
	}
}
