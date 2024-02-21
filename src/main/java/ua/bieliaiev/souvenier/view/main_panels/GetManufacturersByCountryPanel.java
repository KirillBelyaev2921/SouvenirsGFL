package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextFieldPanel;

import javax.swing.*;
import java.util.List;

public class GetManufacturersByCountryPanel extends MainPanel {
	public GetManufacturersByCountryPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextFieldPanel countryField = new LabelWithTextFieldPanel("Enter the country:");
		this.add(countryField);

		JButton getSouvenirsByCountryButton = new JButton("Get all souvenirs produced in this country");
		this.add(getSouvenirsByCountryButton);

		LabelWithListPanel<Souvenir> manufacturers = new LabelWithListPanel<>("Souvenirs:");
		this.add(manufacturers);

		getSouvenirsByCountryButton.addActionListener(e -> {
			List<Souvenir> souvenirList = controller.getSouvenirsByCountry(countryField.getText());
			manufacturers.setListData(souvenirList.toArray(new Souvenir[0]));
		});

	}
}
