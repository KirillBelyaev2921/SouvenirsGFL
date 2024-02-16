package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.compact.LabelTextFieldPanel;

import javax.swing.*;

public class AddManufacturerPanel extends MainPanel {

	public AddManufacturerPanel(SouvenirController controller) {
		super(controller);

		LabelTextFieldPanel nameField = new LabelTextFieldPanel("Enter the name of manufacturer");
		this.add(nameField);

		LabelTextFieldPanel countryField = new LabelTextFieldPanel("Enter the country of manufacturer");
		this.add(countryField);

		LabelTextFieldPanel emailField = new LabelTextFieldPanel("Enter the email address");
		this.add(emailField);

		LabelTextFieldPanel phoneField = new LabelTextFieldPanel("Enter the phone");
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
