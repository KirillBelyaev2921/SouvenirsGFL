package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.view.compact.LabelTextFieldPanel;

import javax.swing.*;
import java.util.Collection;

public class EditManufacturerPanel extends MainPanel {
	private Manufacturer selectedManufacturer;

	public EditManufacturerPanel(SouvenirController controller) {
		super(controller);

		JLabel manufacturersLabel = new JLabel("All manufacturers:");
		JList<Manufacturer> manufacturers = new JList<>();
		manufacturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Collection<Manufacturer> manufacturersList = controller.getManufacturers();
		manufacturers.setListData(manufacturersList.toArray(new Manufacturer[0]));
		JScrollPane manufacturersPane = new JScrollPane(manufacturers);
		this.add(manufacturersLabel);
		this.add(manufacturersPane);

		LabelTextFieldPanel nameField = new LabelTextFieldPanel("Enter the name of manufacturer");
		this.add(nameField);

		LabelTextFieldPanel countryField = new LabelTextFieldPanel("Enter the country of manufacturer");
		this.add(countryField);

		LabelTextFieldPanel emailField = new LabelTextFieldPanel("Enter the email address");
		this.add(emailField);

		LabelTextFieldPanel phoneField = new LabelTextFieldPanel("Enter the phone");
		this.add(phoneField);

		JButton editManufacturer = new JButton("Edit manufacturer");
		editManufacturer.addActionListener(e -> {
			if (controller.editManufacturer(selectedManufacturer, nameField.getText(), countryField.getText(),
					emailField.getText(), phoneField.getText())) {
				editManufacturer.setText("Edited!");
				editManufacturer.setEnabled(false);
			} else {
				editManufacturer.setText("Wrong data!");
			}
		});
		this.add(editManufacturer);

		manufacturers.addListSelectionListener(e -> {
			selectedManufacturer = manufacturers.getSelectedValue();
			nameField.setText(selectedManufacturer.name());
			countryField.setText(selectedManufacturer.country());
			emailField.setText(selectedManufacturer.email());
			phoneField.setText(selectedManufacturer.phone());
		});
	}
}
