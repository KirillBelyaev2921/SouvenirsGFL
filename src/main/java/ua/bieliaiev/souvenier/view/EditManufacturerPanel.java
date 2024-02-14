package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;

import javax.swing.*;
import java.util.Collection;

public class EditManufacturerPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public EditManufacturerPanel(SouvenirController controller) {
		super(controller);

		JLabel manufacturersLabel = new JLabel("All manufacturers:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Collection<Manufacturer> manufacturersList = controller.getManufacturers();
		manufacturers.setListData(manufacturersList.toArray(new Manufacturer[0]));
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);

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

		JButton editManufacturer = new JButton("Edit manufacturer");
		editManufacturer.addActionListener(e -> {
			if (controller.editManufacturer(selectedManufacturer, nameField.getText(), countryField.getText(),
					emailField.getText(), phoneField.getText())) {
				editManufacturer.setText("Edited!");
				editManufacturer.setEnabled(false);
			} else {
				editManufacturer.setText("Wrong data!");
			}
		});
		this.add(editManufacturer);

		manufacturers.addListSelectionListener(e -> {
			selectedManufacturer = manufacturers.getSelectedValue();
			nameField.setText(selectedManufacturer.name());
			countryField.setText(selectedManufacturer.country());
			emailField.setText(selectedManufacturer.email());
			phoneField.setText(selectedManufacturer.phone());
		});
	}
}
