package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;
import java.util.List;

public class GetManufacturersByLowerPricePanel extends MainPanel {
	public GetManufacturersByLowerPricePanel(SouvenirController controller) {
		super(controller);

		LabelWithTextFieldPanel priceField = new LabelWithTextFieldPanel("Enter the price:");
		this.add(priceField);

		JButton getManufacturersWhereAnyLowerPrice = new JButton("Get manufacturers that have at least 1 souvenir that has lower or equal price");
		this.add(getManufacturersWhereAnyLowerPrice);
		JButton getManufacturersWhereAllLowerPrice = new JButton("Get manufacturers that have all souvenirs that have lower or equal price");
		this.add(getManufacturersWhereAllLowerPrice);

		LabelWithListPanel<Manufacturer> manufacturers = new LabelWithListPanel<>("Manufacturers:");
		this.add(manufacturers);

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
