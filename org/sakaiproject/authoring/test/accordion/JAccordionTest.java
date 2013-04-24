package org.sakaiproject.authoring.test.accordion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sakaiproject.authoring.accordion.JAccordion;

public class JAccordionTest {

	/**
	 * Debug, dummy method
	 */
	public static JPanel getDummyPanel(String name) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(name, JLabel.CENTER));
		return panel;
	}

	/**
	 * Debug test...
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("JAccordion Test");

		JAccordion outlookBar = new JAccordion();
		outlookBar.addBar("One",
				getDummyPanel("Onexxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxo"));
		outlookBar.addBar("Two", getDummyPanel("Two"));
		outlookBar.addBar("Three", getDummyPanel("Three"));
		outlookBar.addBar("Four", getDummyPanel("Four"));
		outlookBar.addBar("Five", getDummyPanel("Five"));
		outlookBar.setVisibleBar(2);
		//outlookBar.setPreferredSize(new Dimension(400, 400));

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(outlookBar, BorderLayout.CENTER);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width / 2 - 400, d.height / 2 - 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
