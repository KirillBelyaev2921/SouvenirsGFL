package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;

public class EditManufacturerPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public EditManufacturerPanel(SouvenirController controller) {
		super(controller);

		LabelWithListPanel<Manufacturer> manufacturers = new LabelWithListPanel<>("All manufacturers:",
				controller.getManufacturers().stream().toList());
		this.add(manufacturers);

		LabelWithTextFieldPanel nameField = new LabelWithTextFieldPanel("Enter the name of manufacturer");
		this.add(nameField);

		LabelWithTextFieldPanel countryField = new LabelWithTextFieldPanel("Enter the country of manufacturer");
		this.add(countryField);

		LabelWithTextFieldPanel emailField = new LabelWithTextFieldPanel("Enter the email address");
		this.add(emailField);

		LabelWithTextFieldPanel phoneField = new LabelWithTextFieldPanel("Enter the phone");
		this.add(phoneField);

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
