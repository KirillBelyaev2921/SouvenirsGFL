package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;

import javax.swing.*;
import java.util.List;

public class GetManufacturersByLowerPricePanel extends MainPanel {
	public GetManufacturersByLowerPricePanel(SouvenirController controller) {
		super(controller);

		JLabel priceLabel = new JLabel("Enter the price:");
		JTextField priceField = new JTextField(25);
		this.add(priceLabel);
		this.add(priceField);

		JButton getManufacturersWhereAnyLowerPrice = new JButton("Get manufacturers which have at least 1 souvenir has lower or equal price");
		this.add(getManufacturersWhereAnyLowerPrice);
		JButton getManufacturersWhereAllLowerPrice = new JButton("Get manufacturers which have all souvenirs have lower or equal price");
		this.add(getManufacturersWhereAllLowerPrice);

		JLabel manufacturersLabel = new JLabel("Manufacturers:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);

		getManufacturersWhereAnyLowerPrice.addActionListener(e -> {
			List<Manufacturer> manufacturerList = controller.getManufacturersByAnyLowerPriceSouvenir(priceField.getText());
			manufacturers.setListData(manufacturerList.toArray(new Manufacturer[0]));
		});

		getManufacturersWhereAllLowerPrice.addActionListener(e -> {
			List<Manufacturer> manufacturerList = controller.getManufacturersByAllLowerPriceSouvenir(priceField.getText());
			manufacturers.setListData(manufacturerList.toArray(new Manufacturer[0]));
		});
	}
}
