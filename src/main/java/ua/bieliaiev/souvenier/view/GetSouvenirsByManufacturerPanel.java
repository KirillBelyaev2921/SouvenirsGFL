package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;

import javax.swing.*;
import java.util.Collection;

public class GetSouvenirsByManufacturerPanel extends MainPanel {
	public GetSouvenirsByManufacturerPanel(SouvenirController controller) {
		super(controller);

		JLabel manufacturersLabel = new JLabel("Select manufacturer:");
		JList<Manufacturer> manufacturers = new JList<>();
		Collection<Manufacturer> manufacturersList = controller.getManufacturers();
		manufacturers.setListData(manufacturersList.toArray(new Manufacturer[0]));
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);

		JLabel souvenirsLabel = new JLabel("All souvenirs by manufacturer:");
		JList<Souvenir> souvenirs = new JList<>();
		souvenirs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane souvenirsPane = new JScrollPane(souvenirs);
		this.add(souvenirsLabel);
		this.add(souvenirsPane);

		manufacturers.addListSelectionListener(e -> {
			Collection<Souvenir> souvenirsList = controller.getSouvenirsByManufacturer(manufacturers.getSelectedValue());
			souvenirs.setListData(souvenirsList.toArray(new Souvenir[0]));
		});
	}
}
