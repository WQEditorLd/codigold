package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.imsglobal.jaxb.ld.Conference;
import org.imsglobal.jaxb.ld.GameService;
import org.imsglobal.jaxb.ld.IndexSearch;
import org.imsglobal.jaxb.ld.SendMail;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class ServiceOptionDialog extends JDialog {

	private static final long serialVersionUID = -6186101235667979250L;
	
	private JPanel centralPanel;
	private JPanel buttonsPanel;
	
	private JRadioButton gameServiceButton;
	private JRadioButton sendMailButton;
	private JRadioButton conferenceButton;
	private JRadioButton indexSearchButton;
	
	private Object service;
	
	public ServiceOptionDialog(JDialog parent) {
		super(parent, true);
		init();
	}

	private void init() {
		
		buttonsPanel = createButtonsPanel();		
		centralPanel = createCentralPanel();
		
		add(centralPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		
		setTitle(Bundle.getString("title.service"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}
	
	private JPanel createCentralPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 4));
		
		sendMailButton = new JRadioButton(Bundle.getString("title.sendmail"),true);
		conferenceButton = new JRadioButton(Bundle.getString("title.conference"));
		indexSearchButton = new JRadioButton(Bundle.getString("title.indexsearch"));
		gameServiceButton = new JRadioButton(Bundle.getString("title.game"));
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(sendMailButton);
		buttonGroup.add(conferenceButton);
		buttonGroup.add(indexSearchButton);
		buttonGroup.add(gameServiceButton);
		
		panel.add(sendMailButton);
		panel.add(conferenceButton);
		panel.add(indexSearchButton);
		panel.add(gameServiceButton);
		
		return panel;
	}

	private JPanel createButtonsPanel() {
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton okButton = new JButton(Bundle.getString("button.ok"));
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				populateService();
				setVisible(false);
			}
		});
		buttonsPanel.add(okButton);
		
		JButton cancelButton = new JButton(Bundle.getString("button.cancel"));
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);
		
		return buttonsPanel;
	}
	
	private void populateService() {
		if(sendMailButton.isSelected()){
			service = new SendMail();
		}
		if(conferenceButton.isSelected()){
			service = new Conference();
		}
		if(indexSearchButton.isSelected()){
			service = new IndexSearch();
		}
		if(gameServiceButton.isSelected()){
			service = new GameService();
		}
	}

	public Object getService() {
		return service;
	}

	public void setRole(Object service) {
		this.service = service;
	}
	
	

}
