package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;

import javax.swing.*;

public class GetManufacturersByNameAndYearPanel extends MainPanel {
	public GetManufacturersByNameAndYearPanel(SouvenirController controller, JFrame frame) {
		super(controller);

		JLabel nameLabel = new JLabel("Enter the souvenir name:");
		JTextField nameField = new JTextField(25);
		this.add(nameLabel);
		this.add(nameField);

		JLabel yearLabel = new JLabel("Enter the souvenir release date:");
		JTextField yearField = new JTextField(25);
		this.add(yearLabel);
		this.add(yearField);

		JButton getManufacturersBySouvenirNameAndYear = new JButton("Get manufacturers by souvenir name and year");
		this.add(getManufacturersBySouvenirNameAndYear);

		JLabel manufacturersLabel = new JLabel("All manufacturers:");
		JTextArea manufacturers = new JTextArea();
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);

		getManufacturersBySouvenirNameAndYear.addActionListener(e -> {
			String manufacturerList = controller.getManufacturersBySouvenirNameAndYear(
					nameField.getText(), yearField.getText());
			manufacturers.setText(manufacturerList);
			frame.pack();
		});
	}
}
