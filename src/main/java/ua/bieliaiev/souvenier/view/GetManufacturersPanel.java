package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;

import javax.swing.*;
import java.util.Collection;

public class GetManufacturersPanel extends MainPanel {
	public GetManufacturersPanel(SouvenirController controller) {
		super(controller);

		JLabel manufacturersLabel = new JLabel("All manufacturers:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Collection<Manufacturer> manufacturersList = controller.getManufacturers();
		manufacturers.setListData(manufacturersList.toArray(new Manufacturer[0]));
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);
	}
}
