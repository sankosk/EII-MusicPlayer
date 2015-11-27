package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import player.SingleFile;
import player.YoutubeDownloader;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class YoutubeDownloaderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JLabel lblEscribeUnEnlace;
	private String content;
	private JTextArea textArea;
	private JPanel panel_1;
	private JButton btnNewButton;
	
	private MainWindow mW;
	private JButton btnCancel;
	private JPanel panel_2;
	private JLabel lblGuardarEn;
	private JTextField txtdownloadedsongs;

	public MainWindow getmW() {
		return mW;
	}

	public void setmW(MainWindow mW) {
		this.mW = mW;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoutubeDownloader frame = new YoutubeDownloader();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public YoutubeDownloaderDialog(MainWindow mW) {
		setmW(mW);
		setTitle("EII Music Player: Youtube Downloader");
		setBounds(100, 100, 450, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getPanel());
		{
			JPanel panel1 = new JPanel();
			panel1.setBounds(10, 108, 414, 190);
			contentPanel.add(panel1);
			panel1.setLayout(null);
			panel1.add(getTextArea());
		}
		contentPanel.add(getPanel_1());
		contentPanel.add(getPanel_2());
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 59, 414, 36);
			panel.setLayout(null);
			panel.add(getLblEscribeUnEnlace());
		}
		return panel;
	}
	private JLabel getLblEscribeUnEnlace() {
		if (lblEscribeUnEnlace == null) {
			lblEscribeUnEnlace = new JLabel("Escribe un enlace de youtube por linea:");
			lblEscribeUnEnlace.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEscribeUnEnlace.setBounds(10, 11, 342, 20);
		}
		return lblEscribeUnEnlace;
	}
	
	private void downloadFromYoutube(){
		
		YoutubeDownloader yb = new YoutubeDownloader();
		String[] lines = textArea.getText().split("\n");
		for(int i=0; i<lines.length; i++){
			try {
				String[] url = yb.getDownloadURL(lines[i]);
				File f = new File(txtdownloadedsongs.getText()+url[1]+i+".mp3");
				yb.downloadSong(url[0], f);
				getmW().getListLibraryModel().addElement(new SingleFile(f));//.addElement(new SingleFile(f));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(null, "Los .mp3 se han descargado correctamente y se han añadido a la libreria.");
		dispose();
		//Jtext area content
		
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(10, 11, 394, 168);
		}
		return textArea;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(10, 303, 414, 36);
			panel_1.setLayout(null);
			panel_1.add(getBtnNewButton());
			panel_1.add(getBtnCancel());
		}
		return panel_1;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Download");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					downloadFromYoutube();
				}
			});
			btnNewButton.setBounds(232, 11, 89, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setBounds(325, 11, 89, 23);
		}
		return btnCancel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBounds(14, 13, 410, 36);
			panel_2.setLayout(null);
			panel_2.add(getLblGuardarEn());
			panel_2.add(getTxtdownloadedsongs());
		}
		return panel_2;
	}
	private JLabel getLblGuardarEn() {
		if (lblGuardarEn == null) {
			lblGuardarEn = new JLabel("Guardar en:");
			lblGuardarEn.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGuardarEn.setBounds(12, 13, 86, 16);
		}
		return lblGuardarEn;
	}
	private JTextField getTxtdownloadedsongs() {
		if (txtdownloadedsongs == null) {
			txtdownloadedsongs = new JTextField();
			txtdownloadedsongs.setText("downloadedSongs/");
			txtdownloadedsongs.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtdownloadedsongs.setBounds(99, 10, 299, 22);
			txtdownloadedsongs.setColumns(10);
		}
		return txtdownloadedsongs;
	}
}
