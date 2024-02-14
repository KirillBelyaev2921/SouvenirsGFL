package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;

import javax.swing.*;
import java.util.Collection;

public class RemoveManufacturerPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public RemoveManufacturerPanel(SouvenirController controller) {
		super(controller);

		JLabel manufacturersLabel = new JLabel("All manufacturers:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Collection<Manufacturer> manufacturersList = controller.getManufacturers();
		manufacturers.setListData(manufacturersList.toArray(new Manufacturer[0]));
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);

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
