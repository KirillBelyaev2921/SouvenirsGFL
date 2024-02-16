package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.view.compact.LabelTextFieldPanel;

import javax.swing.*;
import java.util.Collection;

public class AddSouvenirPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public AddSouvenirPanel(SouvenirController controller) {
		super(controller);

		LabelTextFieldPanel nameField = new LabelTextFieldPanel("Enter the name of souvenir");
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

		LabelTextFieldPanel dateField = new LabelTextFieldPanel("Enter month and year in format: yyyy.mm (for example, 2022.03)");
		this.add(dateField);

		LabelTextFieldPanel priceField = new LabelTextFieldPanel("Enter the price:");
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
