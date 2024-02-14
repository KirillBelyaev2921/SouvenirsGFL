package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;

import javax.swing.*;

public class GetManufacturersWithSouvenirsPanel extends MainPanel {
	public GetManufacturersWithSouvenirsPanel(SouvenirController controller) {
		super(controller);

		JLabel manufacturersLabel = new JLabel("All manufacturers:");
		JTextArea manufacturers = new JTextArea();
		manufacturers.setText(controller.getManufacturersWithSouvenirs());
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);
	}
}
