package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.util.List;
import java.util.Properties;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import player.MusicPlayer;
import player.SingleFile;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.DropMode;


public class MainWindow extends JFrame {

	//
	private boolean flag = true;
	private DefaultListModel<SingleFile> listLibraryModel;
	public DefaultListModel<SingleFile> getListLibraryModel() {
		return listLibraryModel;
	}

	public void setListLibraryModel(DefaultListModel<SingleFile> listLibraryModel) {
		this.listLibraryModel = listLibraryModel;
	}

	private DefaultListModel<SingleFile> listPlayModel;
	private MusicPlayer mP;

	private JPanel contentPane;
	private JPanel northPane;
	private JLabel lblIcon;
	private JSlider sliderVolume;
	private JPanel VolPane;
	private JLabel lblVol;
	private JLabel lblNVolume;
	private JPanel centralPane;
	private JPanel libraryPane;
	private JPanel playPane;
	private JLabel lblLibrary;
	private JLabel lblPlaylist;
	private JScrollPane scrollLibraryPane;
	private JScrollPane scrollPlayPane;
	private JList<SingleFile> libraryList;
	private JList<SingleFile> playList;
	private JPanel southLibraryPane;
	private JPanel southPlayPane;
	private JButton btnAddToPlayList;
	private JButton btnDelOfPlayList;
	private JButton btnBack;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnNext;
	private JButton btnDel;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JFileChooser fileChooser;
	private JMenuItem mntmOpen;
	private File listenedSong = null;
	private JMenuItem mntmYoutubeDownload;
	private JPanel panel;
	private JLabel lblPlaying;
	private JButton btnClear;
	private JButton btnRandom;
	private JButton btnRandom_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					Properties p = new Properties();
					p.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(p);
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		mP = new MusicPlayer();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 568);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getNorthPane(), BorderLayout.NORTH);
		contentPane.add(getCentralPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
	}

	private JPanel getNorthPane() {
		if (northPane == null) {
			northPane = new JPanel();
			northPane.setBackground(Color.BLACK);
			northPane.setLayout(new GridLayout(0, 3, 0, 0));
			northPane.add(getLblIcon());
			northPane.add(getSliderVolume());
			northPane.add(getVolPane());
		}
		return northPane;
	}
	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));
		}
		return lblIcon;
	}
	private JSlider getSliderVolume() {
		if (sliderVolume == null) {
			sliderVolume = new JSlider();
			sliderVolume.setFocusable(false);
			sliderVolume.setMinorTickSpacing(5);
			sliderVolume.setMajorTickSpacing(25);
			sliderVolume.setPaintLabels(true);
			sliderVolume.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					lblNVolume.setText(String.valueOf(sliderVolume.getValue()));
					setVolume(sliderVolume.getValue());
				}
			});
			sliderVolume.setForeground(new Color(0, 255, 0));
			sliderVolume.setPaintTicks(true);
			sliderVolume.setBackground(Color.BLACK);
			sliderVolume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return sliderVolume;
	}
	private JPanel getVolPane() {
		if (VolPane == null) {
			VolPane = new JPanel();
			VolPane.setBackground(Color.BLACK);
			VolPane.add(getLblVol());
			VolPane.add(getLblNVolume());
		}
		return VolPane;
	}
	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setForeground(new Color(0, 255, 0));
			lblVol.setFont(new Font("Tahoma", Font.PLAIN, 33));
		}
		return lblVol;
	}
	private JLabel getLblNVolume() {
		if (lblNVolume == null) {
			lblNVolume = new JLabel(""+sliderVolume.getValue());
			lblNVolume.setForeground(new Color(0, 255, 0));
			lblNVolume.setFont(new Font("Tahoma", Font.PLAIN, 33));
		}
		return lblNVolume;
	}
	private JPanel getCentralPane() {
		if (centralPane == null) {
			centralPane = new JPanel();
			centralPane.setBackground(new Color(0, 0, 0));
			centralPane.setLayout(new GridLayout(0, 2, 5, 0));
			centralPane.add(getLibraryPane());
			centralPane.add(getPlayPane());
		}
		return centralPane;
	}
	private JPanel getLibraryPane() {
		if (libraryPane == null) {
			libraryPane = new JPanel();
			libraryPane.setBackground(new Color(0, 0, 0));
			libraryPane.setLayout(new BorderLayout(2, 2));
			libraryPane.add(getLblLibrary(), BorderLayout.NORTH);
			libraryPane.add(getScrollLibraryPane(), BorderLayout.CENTER);
			libraryPane.add(getSouthLibraryPane(), BorderLayout.SOUTH);
		}
		return libraryPane;
	}
	private JPanel getPlayPane() {
		if (playPane == null) {
			playPane = new JPanel();
			playPane.setBackground(new Color(0, 0, 0));
			playPane.setLayout(new BorderLayout(2, 2));
			playPane.add(getLblPlaylist(), BorderLayout.NORTH);
			playPane.add(getScrollPlayPane(), BorderLayout.CENTER);
			playPane.add(getSouthPlayPane(), BorderLayout.SOUTH);
		}
		return playPane;
	}
	private JLabel getLblLibrary() {
		if (lblLibrary == null) {
			lblLibrary = new JLabel("\u266A Library:");
			lblLibrary.setFont(new Font("Arial", Font.PLAIN, 18));
			lblLibrary.setForeground(new Color(0, 255, 0));
		}
		return lblLibrary;
	}
	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("\u266A PlayList:");
			lblPlaylist.setFont(new Font("Arial", Font.PLAIN, 18));
			lblPlaylist.setForeground(new Color(0, 255, 0));
		}
		return lblPlaylist;
	}
	private JScrollPane getScrollLibraryPane() {
		if (scrollLibraryPane == null) {
			scrollLibraryPane = new JScrollPane();
			scrollLibraryPane.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			scrollLibraryPane.setViewportView(getList_1());
		}
		return scrollLibraryPane;
	}
	private JScrollPane getScrollPlayPane() {
		if (scrollPlayPane == null) {
			scrollPlayPane = new JScrollPane();
			scrollPlayPane.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			scrollPlayPane.setViewportView(getList_1_1());
		}
		return scrollPlayPane;
	}
	protected JList<SingleFile> getList_1() {
		if (libraryList == null) {
			listLibraryModel = new DefaultListModel<SingleFile>();
			libraryList = new JList<SingleFile>(listLibraryModel);
			libraryList.setBackground(new Color(0, 0, 0));
		}
		return libraryList;
	}
	private JList<SingleFile> getList_1_1() {
		if (playList == null) {
			listPlayModel = new DefaultListModel<SingleFile>();
			playList = new JList<SingleFile>(listPlayModel);
			playList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			playList.setBackground(new Color(0, 0, 0));
		}
		return playList;
	}
	private JPanel getSouthLibraryPane() {
		if (southLibraryPane == null) {
			southLibraryPane = new JPanel();
			southLibraryPane.setLayout(new GridLayout(0, 2, 0, 0));
			southLibraryPane.add(getBtnAddToPlayList());
			southLibraryPane.add(getBtnDelOfPlayList());
		}
		return southLibraryPane;
	}
	private JPanel getSouthPlayPane() {
		if (southPlayPane == null) {
			southPlayPane = new JPanel();
			southPlayPane.setLayout(new GridLayout(0, 7, 0, 0));
			southPlayPane.add(getBtnBack());
			southPlayPane.add(getBtnPlay());
			southPlayPane.add(getBtnStop());
			southPlayPane.add(getBtnNext());
			southPlayPane.add(getBtnDel());
			southPlayPane.add(getBtnClear());
			southPlayPane.add(getBtnRandom_1());
			//southPlayPane.add(getBtnRandom());
		}
		return southPlayPane;
	}
	
	private void addToPlayList(){
		for(int i=0; i<libraryList.getSelectedValuesList().size(); i++)
			listPlayModel.addElement(libraryList.getSelectedValuesList().get(i));
	}
	
	private void playSong(){
		if(playList.getSelectedIndex() == -1)
			playList.setSelectedIndex(0);
		
		mP.play(playList.getSelectedValue().getF());
		listenedSong = playList.getSelectedValue().getF();
		setVolume(sliderVolume.getValue());
		lblPlaying.setText("Esta sonando: " + playList.getSelectedValue());
	}
	
	private void setVolume(int vol){
		mP.setVolume(vol, sliderVolume.getMaximum());
	}
	
	private JButton getBtnAddToPlayList() {
		if (btnAddToPlayList == null) {
			btnAddToPlayList = new JButton("Add to PlayList");
			btnAddToPlayList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addToPlayList();
				}
			});
		}
		return btnAddToPlayList;
	}
	
	private void deleteFromLibrary(){
		List<SingleFile> files = libraryList.getSelectedValuesList();
		for(int i=0; i<files.size(); i++){
			if(listenedSong.equals(files.get(i)))
				mP.stop();
			listLibraryModel.removeElement(files.get(i));
		}
	}
	
	private JButton getBtnDelOfPlayList() {
		if (btnDelOfPlayList == null) {
			btnDelOfPlayList = new JButton("Delete");
			btnDelOfPlayList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteFromLibrary();
				}
			});
		}
		return btnDelOfPlayList;
	}
	
	public void getBack(){
//		if(playList.getSelectedIndex()==0)
//			playList.setSelectedIndex(playList.getComponents().length);
//		else
		
		int numCanciones = listPlayModel.size();
		if(playList.getSelectedIndex() != 0){
			playList.setSelectedIndex(playList.getSelectedIndex()-1);
			mP.play(playList.getSelectedValue().getF());
		}
		else{
			playList.setSelectedIndex(numCanciones-1);
			mP.play(playList.getSelectedValue().getF());
		}
		
		//playList.setSelectedIndex(playList.getSelectedIndex()-1);
		
		//mP.play(playList.getSelectedValue().getF());
		setVolume(sliderVolume.getValue());
		lblPlaying.setText("Esta sonando: " + playList.getSelectedValue());

	}
	
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("\u25C4\u25C4");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getBack();
				}
			});
			btnBack.setFont(new Font("Arial", Font.PLAIN, 11));
		}
		return btnBack;
	}
	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("\u25BA");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playSong();
				}
			});
			btnPlay.setFont(new Font("Arial", Font.PLAIN, 11));
		}
		return btnPlay;
	}
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("\u25A0");
			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mP.stop();
					flag = false;
					lblPlaying.setText("Esta sonando: ");
				}
			});
			btnStop.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return btnStop;
	}
	
	public void getNext(){
//		if(playList.getSelectedIndex() == playList.getComponents().length)
//			playList.setSelectedIndex(0);
//		else
		//playList.setSelectedIndex(playList.getSelectedIndex()+1);
		
		int numCanciones = listPlayModel.size();
		if(playList.getSelectedIndex() != numCanciones-1){
			playList.setSelectedIndex(playList.getSelectedIndex()+1);
			mP.play(playList.getSelectedValue().getF());
		}
		else{
			playList.setSelectedIndex(0);
			mP.play(playList.getSelectedValue().getF());
		}
		setVolume(sliderVolume.getValue());
		lblPlaying.setText("Esta sonando: " + playList.getSelectedValue());
	}
	
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("\u25BA\u25BA");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getNext();
				}
			});
			btnNext.setFont(new Font("Arial", Font.PLAIN, 11));
		}
		return btnNext;
	}
	
	private void deleteFromPlayList(){
		List<SingleFile> files = playList.getSelectedValuesList();
		for(int i=0; i<files.size(); i++){
			//if(listenedSong.equals(files.get(i)))
			mP.stop();
			listPlayModel.removeElement(files.get(i));
		}
		
	}
	
	private JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton("Delete");
			btnDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteFromPlayList();
				}
			});
		}
		return btnDel;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmOpen());
			mnFile.add(getMntmYoutubeDownload());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
		}
		return mntmExit;
	}
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
		}
		return mnPlay;
	}
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
		}
		return mnOptions;
	}
	
	public JFileChooser getFileChooser() {
		if(fileChooser == null){
			fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(true);
			String desktopPath = System.getProperty("user.home") + "/Desktop"; //Hacer que el directorio por defecto sea el escritorio
			fileChooser.setCurrentDirectory(new File(desktopPath));
		}
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
	
	protected void open() 
	{
		int respuesta = getFileChooser().showOpenDialog(null);
		if(respuesta == JFileChooser.APPROVE_OPTION){
			for(int i = 0; i < fileChooser.getSelectedFiles().length; i++){
				listLibraryModel.addElement(new SingleFile(fileChooser.getSelectedFiles()[i]));
			}
		}
	}
	
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					open();
				}
			});
		}
		return mntmOpen;
	}
	private JMenuItem getMntmYoutubeDownload() {
		if (mntmYoutubeDownload == null) {
			mntmYoutubeDownload = new JMenuItem("Download from youtube");
			mntmYoutubeDownload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showDialog();
				}
			});
		}
		return mntmYoutubeDownload;
	}
	
	private void showDialog(){
		YoutubeDownloaderDialog yDialog = new YoutubeDownloaderDialog(this);
		yDialog.setLocationRelativeTo(this);
		yDialog.setModal(true);
		yDialog.setVisible(true);
		yDialog.dispose();
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLblPlaying());
		}
		return panel;
	}
	private JLabel getLblPlaying() {
		if (lblPlaying == null) {
			lblPlaying = new JLabel("Esta sonando: ");
			lblPlaying.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblPlaying;
	}
	
	public void clear(){
		mP.stop();
		listPlayModel.removeAllElements();
	}
	
	private JButton getBtnClear() {
		if (btnClear == null) {
			btnClear = new JButton("Clear");
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();
				}
			});
		}
		return btnClear;
	}
	
//	private void random(){
//		
//	}
//	
//	private JButton getBtnRandom() {
//		if (btnRandom == null) {
//			btnRandom = new JButton("random");
//			btnRandom.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					int n=(int) (Math.random()*playList.getComponents().length);
//					//mP.play((SingleFile) playList.getComponents()[n]);
//					File[] s = (File[]) playList.getSelectedValues();
//					mP.play(s[n]);
//				}
//			});
//		}
//		return btnRandom;
//	}
	private JButton getBtnRandom_1() {
		if (btnRandom_1 == null) {
			btnRandom_1 = new JButton("random");
			btnRandom_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int numCanciones = listPlayModel.size();
					int n=(int)Math.floor(Math.random()*(0-(numCanciones))+(numCanciones));
		            playList.setSelectedIndex(n);
					mP.play(playList.getSelectedValue().getF());
				}
			});
		}
		return btnRandom_1;
	}
}
