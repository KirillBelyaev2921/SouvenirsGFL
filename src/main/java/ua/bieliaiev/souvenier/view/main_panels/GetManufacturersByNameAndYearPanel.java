package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextAreaPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;

public class GetManufacturersByNameAndYearPanel extends MainPanel {
	public GetManufacturersByNameAndYearPanel(SouvenirController controller, JFrame frame) {
		super(controller);

		LabelWithTextFieldPanel nameField = new LabelWithTextFieldPanel("Enter the name of souvenir");
		this.add(nameField);

		LabelWithTextFieldPanel yearField = new LabelWithTextFieldPanel("Enter the souvenir release date:");
		this.add(yearField);

		JButton getManufacturersBySouvenirNameAndYear = new JButton("Get manufacturers by souvenir name and year");
		this.add(getManufacturersBySouvenirNameAndYear);

		LabelWithTextAreaPanel manufacturers = new LabelWithTextAreaPanel("Manufacturers:");

		getManufacturersBySouvenirNameAndYear.addActionListener(e -> {
			String manufacturerList = controller.getManufacturersBySouvenirNameAndYear(
					nameField.getText(), yearField.getText());
			manufacturers.setText(manufacturerList);
			frame.pack();
		});
	}
}
