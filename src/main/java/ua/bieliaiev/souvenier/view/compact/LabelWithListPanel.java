package ua.bieliaiev.souvenier.view.compact;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class LabelWithListPanel<T> extends JPanel {

	private final JList<T> jList = new JList<>();
	private List<T> list;

	@SuppressWarnings("unchecked")
	public LabelWithListPanel(String labelString, List<T> list) {
		this(labelString);
		this.list = list;
		jList.setListData((T[]) list.toArray());
	}
	public LabelWithListPanel(String labelString) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel label = new JLabel(labelString);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane pane = new JScrollPane(jList);
		this.add(label);
		this.add(pane);
	}

	public void addListSelectionListener(ListSelectionListener l) {
		jList.addListSelectionListener(l);
	}

	public T getSelectedValue() {
		return jList.getSelectedValue();
	}

	public int indexOf(T t) {
		return list.indexOf(t);
	}

	public void setSelectedIndex(int i) {
		jList.setSelectedIndex(i);
	}

	public void setListData(T[] array) {
		jList.setListData(array);
	}
}
