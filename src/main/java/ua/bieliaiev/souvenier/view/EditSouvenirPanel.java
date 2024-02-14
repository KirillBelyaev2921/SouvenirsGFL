package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class EditSouvenirPanel extends MainPanel {
	private Manufacturer selectedManufacturer;
	private Souvenir selectedSouvenir;

	public EditSouvenirPanel(SouvenirController controller) {
		super(controller);

		JLabel souvenirsLabel = new JLabel("All souvenirs:");
		JList<Souvenir> souvenirs = new JList<>();
		souvenirs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Collection<Souvenir> souvenirsList = controller.getSouvenirs();
		souvenirs.setListData(souvenirsList.toArray(new Souvenir[0]));
		JScrollPane souvenirsPane = new JScrollPane(souvenirs);
		this.add(souvenirsLabel);
		this.add(souvenirsPane);

		JLabel nameLabel = new JLabel("Enter the name");
		JTextField nameField = new JTextField(25);
		this.add(nameLabel);
		this.add(nameField);

		JLabel manufacturerLabel = new JLabel("Choose the manufacturer:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		manufacturers.addListSelectionListener(e -> selectManufacturer(manufacturers.getSelectedValue()));
		List<Manufacturer> manufacturersList = controller.getManufacturers().stream().toList();
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
			manufacturers.setSelectedIndex(manufacturersList.indexOf(selectedSouvenir.manufacturer()));
			dateField.setText(selectedSouvenir.dateString());
			priceField.setText(selectedSouvenir.price() + "");
		});
	}

	private void selectManufacturer(Manufacturer selectedValue) {
		this.selectedManufacturer = selectedValue;
	}
}
