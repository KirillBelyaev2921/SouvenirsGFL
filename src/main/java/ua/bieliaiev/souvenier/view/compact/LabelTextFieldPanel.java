package ua.bieliaiev.souvenier.view.compact;

import javax.swing.*;

public class LabelTextFieldPanel extends JPanel {

	private final JTextField field = new JTextField(25);;
	public LabelTextFieldPanel(String labelString) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel label = new JLabel(labelString);
		this.add(label);
		this.add(field);
	}

	public String getText() {
		return field.getText();
	}

	public void setText(String text) {
		field.setText(text);
	}
}
