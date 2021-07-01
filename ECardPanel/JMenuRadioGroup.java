import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * Represents a group of JMenuRadioButtons that can be added to a menu,
 * with convenient methods for finding the currently selected index or item.
 */
public class JMenuRadioGroup {
	
	private JRadioButtonMenuItem[] menuItems;

	/**
	 * Construct a group of buttons, given the text for each button.
	 * Initially, no item is selected.
	 * @param itemNames an array of strings that will be used as the text
	 *    for the menu items.  Must not be null.  Only makes sense if the
	 *    length is two or greater.
	 */
	public JMenuRadioGroup(String[] itemNames) {
		menuItems = new JRadioButtonMenuItem[itemNames.length];
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < menuItems.length; i++) {
			menuItems[i] = new JRadioButtonMenuItem(itemNames[i]);
			group.add(menuItems[i]);
		}
	}
	
	/**
	 * Construct a group of buttons, given the text for each button,
	 * and the index of the initially selected item.
	 * @param itemNames a non-null array containing the text for the menu items.
	 * @param selectedIndex the index in the array of the item that will initially
	 *    be selected.  If it is not in the range 0 through itemNames.length - 1,
	 *    then no item is initially selected.
	 */
	public JMenuRadioGroup(String[] itemNames, int selectedIndex) {
		this(itemNames);
		setSelectedIndex(selectedIndex);
	}
	
	/**
	 * Selects one of the items (or no item) in the group,
	 * deselecting the previously selected item.
	 * @param index the index of the item to be selected, or
	 *    -1 to select no item.  (In fact, if the index is
	 *    anything out of the range 0 to getItemCount(), then
	 *    no item will be selected.)
	 */
	public void setSelectedIndex(int index) {
		if (index < 0 || index >= menuItems.length) {
			int i = getSelectedIndex();
			if (i >= 0)
				menuItems[i].setSelected(false);
		}
		else {
		    menuItems[index].setSelected(true);	
		}			
	}
	
	/**
	 * Returns the index of the currently selected item, or -1
	 * if no item is currently selected.
	 */
	public int getSelectedIndex() {
		for (int i = 0; i < menuItems.length; i++) {
			if (menuItems[i].isSelected())
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns the text of the currently selected item, or null
	 * if no item is currently selected.
	 */
	public String getSelectedItem() {
		for (int i = 0; i < menuItems.length; i++) {
			if (menuItems[i].isSelected())
				return menuItems[i].getText();
		}
		return null;
	}
	
	/**
	 * Adds all of the JRadioButtonMenuItems to a specified menu.
	 */
	public void addToMenu(JMenu menu) {
		for (JRadioButtonMenuItem item : menuItems)
			menu.add(item);
	}
	
	/**
	 * Adds an ActionListener to each of the menu items.
	 */
	public void addListener(ActionListener listener) {
		for (JRadioButtonMenuItem item : menuItems)
			item.addActionListener(listener);
	}
	
	/**
	 * Returns the number or menu items in this group.
	 */
	public int getItemCount() {
		return menuItems.length;
	}
	
	/**
	 * Gets one of the JRadioButtonMenuItems from this group.
	 * @param index the index of the item, ranging from 0 through
	 *     the value of getItemCount().
	 */
	public JRadioButtonMenuItem getItem(int index) {
		return menuItems[index];
	}
	
	/**
	 * A convenience method for enabling/disabling all the
	 * items in this group.
	 */
	public void setEnabled(boolean enable) {
		for (JRadioButtonMenuItem item : menuItems)
			item.setEnabled(enable);
	}
	
}
