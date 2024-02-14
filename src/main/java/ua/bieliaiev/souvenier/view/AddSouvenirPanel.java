package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;

import javax.swing.*;
import java.util.Collection;

public class AddSouvenirPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public AddSouvenirPanel(SouvenirController controller) {
		super(controller);

		JLabel nameLabel = new JLabel("Enter the name");
		JTextField nameField = new JTextField(25);
		this.add(nameLabel);
		this.add(nameField);

		JLabel manufacturerLabel = new JLabel("Choose the manufacturer:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		manufacturers.addListSelectionListener(e -> selectManufacturer(manufacturers.getSelectedValue()));
		Collection<Manufacturer> manufacturersList = controller.getManufacturers();
		manufacturers.setListData(manufacturersList.toArray(new Manufacturer[0]));
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturerLabel);
		this.add(manufacturersPane);

		JLabel dateLabel = new JLabel("Enter month and year in format: yyyy.mm (for example, 2022.03)");
		JTextField dateField = new JTextField(25);
		this.add(dateLabel);
		this.add(dateField);

		JLabel priceLabel = new JLabel("Enter the price:");
		JTextField priceField = new JTextField(25);
		this.add(priceLabel);
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

	private void selectManufacturer(Manufacturer selectedValue) {
		this.selectedManufacturer = selectedValue;
	}
}
