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
		list.forEach(o ->
				textArea.append(" " + o.toString() + "\n"));
	}
}
