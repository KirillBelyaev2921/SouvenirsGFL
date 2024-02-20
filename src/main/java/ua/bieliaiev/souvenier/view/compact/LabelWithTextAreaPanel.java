package ua.bieliaiev.souvenier.view.compact;

import javax.swing.*;
import java.util.Collection;

public class LabelWithTextAreaPanel extends JPanel {

	private final JTextArea textArea = new JTextArea(25, 10);

	public LabelWithTextAreaPanel(String labelString) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel label = new JLabel(labelString);
		JScrollPane pane = new JScrollPane(textArea);
		this.add(label);
		this.add(pane);
	}

	public void setText(String manufacturerList) {
		textArea.setText(manufacturerList);
	}

	public void setText(Collection<?> list) {
		StringBuilder result = new StringBuilder();
		list.forEach(o ->
				result.append(" ").append(o.toString()).append("\n"));
		setText(result.toString());
	}
}
