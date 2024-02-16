package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;

public class AddManufacturerPanel extends MainPanel {

	public AddManufacturerPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextFieldPanel nameField = new LabelWithTextFieldPanel("Enter the name of manufacturer");
		this.add(nameField);

		LabelWithTextFieldPanel countryField = new LabelWithTextFieldPanel("Enter the country of manufacturer");
		this.add(countryField);

		LabelWithTextFieldPanel emailField = new LabelWithTextFieldPanel("Enter the email address");
		this.add(emailField);

		LabelWithTextFieldPanel phoneField = new LabelWithTextFieldPanel("Enter the phone");
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
