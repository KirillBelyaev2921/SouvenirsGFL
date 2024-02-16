package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;

import javax.swing.*;

public class RemoveManufacturerPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public RemoveManufacturerPanel(SouvenirController controller) {
		super(controller);

		LabelWithListPanel<Manufacturer> manufacturers = new LabelWithListPanel<>("Select manufacturer:",
				controller.getManufacturers().stream().toList());
		this.add(manufacturers);

		JButton removeManufacturer = new JButton("Remove manufacturer");
		removeManufacturer.addActionListener(e -> {
			if (controller.removeManufacturer(selectedManufacturer)) {
				removeManufacturer.setText("Removed!");
				removeManufacturer.setEnabled(false);
			} else {
				removeManufacturer.setText("Wrong data!");
			}
		});
		this.add(removeManufacturer);

		manufacturers.addListSelectionListener(e -> selectedManufacturer = manufacturers.getSelectedValue());
	}
}
