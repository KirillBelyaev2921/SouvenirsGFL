package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;

public class EditSouvenirPanel extends MainPanel {
	private Manufacturer selectedManufacturer;
	private Souvenir selectedSouvenir;

	public EditSouvenirPanel(SouvenirController controller) {
		super(controller);

		LabelWithListPanel<Souvenir> souvenirs = new LabelWithListPanel<>("All souvenirs:",
				controller.getSouvenirs().stream().toList());
		this.add(souvenirs);

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

		JButton editSouvenir = new JButton("Edit souvenir");
		editSouvenir.addActionListener(e -> {
			if (controller.editSouvenir(selectedSouvenir, nameField.getText(), selectedManufacturer,
					dateField.getText(), priceField.getText())) {
				editSouvenir.setText("Edited!");
				editSouvenir.setEnabled(false);
			} else {
				editSouvenir.setText("Wrong data!");
			}
		});
		this.add(editSouvenir);

		souvenirs.addListSelectionListener(e -> {
			selectedSouvenir = souvenirs.getSelectedValue();
			nameField.setText(selectedSouvenir.name());
			manufacturers.setSelectedIndex(manufacturers.indexOf(selectedSouvenir.manufacturer()));
			dateField.setText(selectedSouvenir.dateString());
			priceField.setText(selectedSouvenir.price() + "");
		});
	}
}
