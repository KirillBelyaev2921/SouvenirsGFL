package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;

public class AddSouvenirPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public AddSouvenirPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextFieldPanel nameField = new LabelWithTextFieldPanel("Enter the name of souvenir");
		this.add(nameField);

		LabelWithListPanel<Manufacturer> manufacturers = new LabelWithListPanel<>("Choose the manufacturer:",
				controller.getManufacturers().stream().toList());
		manufacturers.addListSelectionListener(e -> selectedManufacturer = manufacturers.getSelectedValue());
		this.add(manufacturers);

		LabelWithTextFieldPanel dateField = new LabelWithTextFieldPanel("Enter month and year in format: yyyy.mm (for example, 2022.03)");
		this.add(dateField);

		LabelWithTextFieldPanel priceField = new LabelWithTextFieldPanel("Enter the price:");
		this.add(priceField);

		JButton saveSouvenir = new JButton("Save souvenir");
		saveSouvenir.addActionListener(e -> {
			if (controller.addSouvenir(nameField.getText(), selectedManufacturer,
					dateField.getText(), priceField.getText())) {
				saveSouvenir.setText("Saved!");
				saveSouvenir.setEnabled(false);
			} else {
				saveSouvenir.setText("Wrong data!");
			}
		});
		this.add(saveSouvenir);
	}

}
