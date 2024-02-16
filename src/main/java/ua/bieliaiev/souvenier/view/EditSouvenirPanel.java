package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.compact.LabelTextFieldPanel;

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

		LabelTextFieldPanel nameField = new LabelTextFieldPanel("Enter the name of souvenir");
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

		LabelTextFieldPanel dateField = new LabelTextFieldPanel("Enter month and year in format: yyyy.mm (for example, 2022.03)");
		this.add(dateField);

		LabelTextFieldPanel priceField = new LabelTextFieldPanel("Enter the price:");
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
