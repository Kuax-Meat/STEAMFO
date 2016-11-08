import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author 심재형(MEAT)
 */
public class Mainframe extends JFrame implements Runnable {
	private int pX, pY;
	/* Main Window Variables */
	public JWindow minimenu = new JWindow();
	public JFrame confwin = new JFrame();
	public JPanel titleBar = new JPanel();
	public JLabel titleminiMenu = new JLabel();
	public JLabel titleIMG = new JLabel();
	public JLabel titleCloseButton = new JLabel();
	public JPanel statusBar = new JPanel();
	public JLabel[] SSale1IMG = new JLabel[10];
	public JLabel[] SSale1Des = new JLabel[10];
	public JLabel SSale1IMG1 = new JLabel();
	public JLabel SSale1IMG2 = new JLabel();
	public JLabel SSale1IMG3 = new JLabel();
	public JPanel Wrap1 = new JPanel(null);
	public JPanel WrapSec1 = new JPanel(null);
	public JPanel Section1 = new JPanel();
	public JLabel Section1Lab = new JLabel("Steam Highlights");
	public JScrollPane Section1Scroll;
	public JPanel realMain = new JPanel();
	public JPanel Section2 = new JPanel();
	public JLabel Sec2Title = new JLabel();
	public JLabel Sec2Lab = new JLabel();
	public JLabel Sec2LabBTA = new JLabel();
	public JLabel[] HSale1IMG = new JLabel[10];
	public JPanel Sec2list = new JPanel();
	public JScrollPane Section2Scroll;
	public JPanel Wrap2 = new JPanel(null);
	public JLabel Sec3Title = new JLabel();
	public JLabel[] GSale1IMG = new JLabel[30];
	public JPanel Section3 = new JPanel();
	public JScrollPane Section3Scroll;
	public JPanel Wrap3 = new JPanel(null);
	public JPanel closeButton = new JPanel();
	public FileIO fio;
	
	String[] LinkPath = new String[20];
	/* End of Main Window Variables */
	
	/* Additional Frame Components */
	public JPanel[] cSPanel = new JPanel[3];
	public JPanel[] cWrap = new JPanel[3];
	public JLabel[] cWL = new JLabel[3];
	public JLabel[][] cSL = new JLabel[3][5];
	public JScrollPane[] cSscroll = new JScrollPane[3];
	public JLabel splitter = new JLabel();
	/* End of Custom Window Variables */
	
	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("STEAMFO");
		setBackground(new Color(255, 255, 255));
		setUndecorated(true);
		setIconImage(new ImageIcon(getClass().getResource("steamfo_icon.png")).getImage());
		buildMiniMenu();
		
		fio = new FileIO();
		
		titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36.png")));
		titleminiMenu.setForeground(new Color(255, 255, 255));
		titleminiMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
            	minimenu();
            }
		});
		titleCloseButton.setIcon(new ImageIcon(getClass().getResource("close_button.png")));
		closeButton.setBackground(new Color(0, 0, 0));
		titleCloseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				System.exit(0);
            }
			
			public void mouseEntered(MouseEvent evt) {
				closeButton.setBackground(new Color(255, 255, 255));
				titleCloseButton.setIcon(new ImageIcon(getClass().getResource("close_button_hover.png")));
			}
			
			public void mouseExited(MouseEvent evt) {
				closeButton.setBackground(new Color(0, 0, 0));
				titleCloseButton.setIcon(new ImageIcon(getClass().getResource("close_button.png")));
			}
		});
		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				System.exit(0);
            }
			
			public void mouseEntered(MouseEvent evt) {
				closeButton.setBackground(new Color(255, 255, 255));
				titleCloseButton.setIcon(new ImageIcon(getClass().getResource("close_button_hover.png")));
			}
			
			public void mouseExited(MouseEvent evt) {
				closeButton.setBackground(new Color(0, 0, 0));
				titleCloseButton.setIcon(new ImageIcon(getClass().getResource("close_button.png")));
			}
		});
		
		closeButton.add(titleCloseButton);
		GroupLayout closeButtonWrapLayout = new GroupLayout(closeButton);
		closeButton.setLayout(closeButtonWrapLayout);
		closeButtonWrapLayout.setHorizontalGroup(
				closeButtonWrapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(closeButtonWrapLayout.createSequentialGroup()
            	.addGap(35)
            	.addComponent(titleCloseButton)
                .addGap(36, 36, 36))
        );
		closeButtonWrapLayout.setVerticalGroup(
			closeButtonWrapLayout.createSequentialGroup()
			.addGap(13)
			.addComponent(titleCloseButton)
		);
		
		titleIMG.setForeground(new Color(255, 255, 255));
		titleIMG.setIcon(new ImageIcon(getClass().getResource("Steam_Icon_text.png")));
		titleIMG.setFocusable(false);
		
		titleIMG.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				titleBarMousePressed(evt);
			}
		});
		titleIMG.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
            	titleBarMouseDragged(evt);
            }
		});
		titleBar.setBackground(new Color(0, 0, 0));
		titleBar.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
            	titleBarMouseDragged(evt);
            }
		});
		titleBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				titleBarMousePressed(evt);
			}
		});
		GroupLayout titleBarLayout = new GroupLayout(titleBar);
		titleBar.setLayout(titleBarLayout);
		titleBarLayout.setHorizontalGroup(
				titleBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(titleBarLayout.createSequentialGroup()
            	.addComponent(titleminiMenu)
                .addComponent(titleIMG)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton))
        );
		titleBarLayout.setVerticalGroup(
			titleBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(titleminiMenu)
			.addComponent(titleIMG)
            .addComponent(closeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
		
		for (int i = 0; i < 10; i++) {
			SSale1IMG[i] = new JLabel("Image");
			SSale1IMG[i].setForeground(new java.awt.Color(255, 255, 255));
			SSale1IMG[i].setDoubleBuffered(true);
			SSale1IMG[i].setOpaque(true);
			SSale1Des[i] = new JLabel("Loading..");
			SSale1Des[i].setForeground(new java.awt.Color(255, 255, 255));
			SSale1Des[i].setBackground(new java.awt.Color(0, 0, 0));
			SSale1Des[i].setDoubleBuffered(true);
			SSale1Des[i].setOpaque(true);			
			SSale1Des[i].setFont(new Font("Malgun Gothic", 0, 14));		/* FIX ME */
			
			HSale1IMG[i] = new JLabel("Image");
			HSale1IMG[i].setForeground(new java.awt.Color(255, 255, 255));
			HSale1IMG[i].setDoubleBuffered(true);
			HSale1IMG[i].setOpaque(true);
		}
		
		for (int i = 0; i < 30; i++) {
			GSale1IMG[i] = new JLabel("Image");
			GSale1IMG[i].setForeground(new java.awt.Color(255, 255, 255));
			GSale1IMG[i].setDoubleBuffered(true);
			GSale1IMG[i].setOpaque(true);
		}
		SSale1IMG1.setForeground(new java.awt.Color(255, 255, 255));
		SSale1IMG1.setDoubleBuffered(true);
		SSale1IMG1.setOpaque(true);
		SSale1IMG2.setForeground(new java.awt.Color(255, 255, 255));
		SSale1IMG2.setDoubleBuffered(true);
		SSale1IMG2.setOpaque(true);
		SSale1IMG3.setForeground(new java.awt.Color(255, 255, 255));
		SSale1IMG3.setDoubleBuffered(true);
		SSale1IMG3.setOpaque(true);
		
		Section1.setBackground(new Color(0, 0, 0));
		Section1Lab.setForeground(new Color(255, 255, 255));
		Section1Lab.setFont(new Font("Consolas", Font.BOLD, 13));
		Section1Lab.setIcon(new ImageIcon(getClass().getResource("Sec1TitleIcon.png")));
		Sec2LabBTA.setText("Loading...");
		Sec2LabBTA.setForeground(new Color(151, 177, 71));
		Wrap1.setBackground(new Color(51, 51, 51));
		Wrap1.setPreferredSize(new Dimension(900, 400));
		WrapSec1.setBackground(new Color(51, 51, 51));
		Section2.setBackground(new Color(0, 0, 0));
		Section2.setPreferredSize(new Dimension(900, 130));
		GroupLayout Sec2Layout = new GroupLayout(Section2);
		Sec2Layout.setHorizontalGroup(
				Sec2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(Sec2Layout.createSequentialGroup()
	                .addGroup(Sec2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(Sec2Lab, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Sec2LabBTA, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)))
	        );
			Sec2Layout.setVerticalGroup(
				Sec2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(Sec2Layout.createSequentialGroup()
	                .addComponent(Sec2Lab)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(Sec2LabBTA, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
	        );
		Sec2list.setBackground(new Color(0, 0, 0));
		Wrap2.setBackground(new Color(0, 0, 0));
		Wrap3.setBackground(new Color(0, 0, 0));
		Section3.setBackground(new Color(0, 0, 0));
		Sec2Lab.setText("");
		Sec2Title.setForeground(new Color(255, 255, 255));
		Sec2Title.setText("");
		Sec2Title.setFont(new Font("Consolas", Font.BOLD, 13));
		Sec2Title.setIcon(new ImageIcon(getClass().getResource("Sec2TitleIcon.png")));
		Sec3Title.setForeground(new Color(255, 255, 255));
		Sec3Title.setFont(new Font("Consolas", Font.BOLD, 13));
		Sec3Title.setIcon(new ImageIcon(getClass().getResource("Sec3TitleIcon.png")));
		GroupLayout Section1Layout = new GroupLayout(Section1);
		Section1.setLayout(Section1Layout);
        Section1Layout.setHorizontalGroup(
            Section1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Section1Layout.createSequentialGroup()
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[0])
                        .addComponent(SSale1IMG[0], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[1])
                        .addComponent(SSale1IMG[1], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[2])
                        .addComponent(SSale1IMG[2], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[3])
                        .addComponent(SSale1IMG[3], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[4])
                        .addComponent(SSale1IMG[4], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[5])
                        .addComponent(SSale1IMG[5], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[6])
                        .addComponent(SSale1IMG[6], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[7])
                        .addComponent(SSale1IMG[7], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[8])
                        .addComponent(SSale1IMG[8], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(SSale1Des[9])
                        .addComponent(SSale1IMG[9], GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Section1Layout.setVerticalGroup(
            Section1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Section1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Section1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(SSale1IMG[0], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[1], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[2], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[3], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[4], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[5], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[6], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[7], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[8], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1IMG[9], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                	.addComponent(SSale1Des[0])
                	.addComponent(SSale1Des[1])
                    .addComponent(SSale1Des[2])
                    .addComponent(SSale1Des[3])
                    .addComponent(SSale1Des[4])
                    .addComponent(SSale1Des[5])
                    .addComponent(SSale1Des[6])
                    .addComponent(SSale1Des[7])
                    .addComponent(SSale1Des[8])
                    .addComponent(SSale1Des[9])
                    )
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )//.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
        );
        Section1Scroll = new JScrollPane(Section1);
        Section1Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Section1Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		Section1Scroll.setBorder(null);
		Section1Scroll.setAutoscrolls(true);
		Section1Scroll.setDoubleBuffered(true);
		Section1Scroll.setOpaque(false);
		Section1Scroll.setBounds(0, 0, 860, 160);
		Section1Scroll.getHorizontalScrollBar().setUI(new CustomScrollBar());
		
		GroupLayout Section2Layout = new GroupLayout(Sec2list);
		Sec2list.setLayout(Section2Layout);
		Section2Layout.setHorizontalGroup(
			Section2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Section2Layout.createSequentialGroup()
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[0], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[1], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[2], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[3], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[4], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[5], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[6], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[7], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[8], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(HSale1IMG[9], GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Section2Layout.setVerticalGroup(
            Section2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Section2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Section2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(HSale1IMG[0], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[1], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[2], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[3], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[4], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[5], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[6], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[7], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[8], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                	.addComponent(HSale1IMG[9], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    )
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        Section2Scroll = new JScrollPane(Sec2list);
        Section2Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Section2Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		Section2Scroll.setBorder(null);
		Section2Scroll.setAutoscrolls(true);
		Section2Scroll.setDoubleBuffered(true);
		Section2Scroll.setOpaque(false);
		Section2Scroll.setBounds(0, 0, 860, 195);
		Section2Scroll.getHorizontalScrollBar().setUI(new CustomScrollBar());
		
		GroupLayout Section3Layout = new GroupLayout(Section3);
		Section3.setLayout(Section3Layout);
		Section3Layout.setHorizontalGroup(
			Section3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Section3Layout.createSequentialGroup()
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[0], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[10], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[1], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[11], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[2], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[12], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[3], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[13], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[4], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[14], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[5], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[15], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[6], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[16], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[7], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[17], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[8], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[18], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(GSale1IMG[9], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addComponent(GSale1IMG[19], GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Section3Layout.setVerticalGroup(
            Section3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Section3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                	.addComponent(GSale1IMG[0], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[1], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[2], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[3], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[4], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[5], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[6], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[7], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[8], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                	.addComponent(GSale1IMG[9], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    )
                .addGroup(Section3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    	.addComponent(GSale1IMG[10], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[11], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[12], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[13], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[14], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[15], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[16], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[17], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[18], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(GSale1IMG[19], GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                        )
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        Section3Scroll = new JScrollPane(Section3);
        Section3Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Section3Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		Section3Scroll.setBorder(null);
		Section3Scroll.setAutoscrolls(true);
		Section3Scroll.setDoubleBuffered(true);
		Section3Scroll.setOpaque(false);
		Section3Scroll.setBounds(0, 0, 860, 215);
		Section3Scroll.getHorizontalScrollBar().setUI(new CustomScrollBar());
		
		Wrap1.setPreferredSize(new Dimension(860, 160));
		Wrap1.add(Section1Scroll);
		Wrap2.setPreferredSize(new Dimension(860, 200));
		Wrap2.add(Section2Scroll);
		Wrap3.setPreferredSize(new Dimension(860, 215));
		Wrap3.add(Section3Scroll);
        GroupLayout WrapLayout = new GroupLayout(WrapSec1);
        WrapSec1.setLayout(WrapLayout);
        WrapLayout.setHorizontalGroup(
            WrapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(WrapLayout.createSequentialGroup()
                .addGroup(WrapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(Section1Lab)
                    .addComponent(Wrap1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sec2Title, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Section2, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Wrap2, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sec3Title, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
                    .addComponent(Wrap3, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE))
                
                .addContainerGap(28, Short.MAX_VALUE))
        );
        WrapLayout.setVerticalGroup(
            WrapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(WrapLayout.createSequentialGroup()
                .addComponent(titleBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Section1Lab)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wrap1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(20, 20)
                .addComponent(Sec2Title)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(Section2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
	            .addComponent(Wrap2, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            .addContainerGap(20, 20)
                .addComponent(Sec3Title)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(Wrap3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(WrapSec1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(WrapSec1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        this.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		minimenu.setVisible(false);
    			titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36.png")));
            }
        });
        pack();
        this.setSize(860, 860);
        
        /* Custom Window */
        if (fio.custom[0][0] != null) {
        	this.setSize(1820, 860);
    		splitter.setIcon(new ImageIcon(getClass().getResource("splitter.png")));
    		for (int i = 0; i < 3; i++) {
    			cSPanel[i] = new JPanel();
    			cSPanel[i].setBackground(new Color(0, 0, 0));
    			cWL[i] = new JLabel("Section " + i);
    			cWL[i].setForeground(new Color(255, 255, 255));
    			cWL[i].setFont(new Font("Malgun Gothic", Font.BOLD, 19));
    			for (int j = 0; j < 5; j++) {
	    			cSL[i][j] = new JLabel("Image");
	    			cSL[i][j].setForeground(new java.awt.Color(255, 255, 255));
	    			cSL[i][j].setDoubleBuffered(true);
	    			cSL[i][j].setOpaque(true);
    			}
	    		GroupLayout cSLayout = new GroupLayout(cSPanel[i]);
	    		cSPanel[i].setLayout(cSLayout);
	    		cSLayout.setHorizontalGroup(
	    			cSLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addGroup(cSLayout.createSequentialGroup()
	                    .addGroup(cSLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addComponent(cSL[i][0], GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(cSLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addComponent(cSL[i][1], GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(cSLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addComponent(cSL[i][2], GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(cSLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addComponent(cSL[i][3], GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    .addGroup(cSLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                            .addComponent(cSL[i][4], GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            );
	            cSLayout.setVerticalGroup(
	                cSLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addGroup(cSLayout.createSequentialGroup()
	                    .addContainerGap()
	                    .addGroup(cSLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	                    	.addComponent(cSL[i][0], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
	                    	.addComponent(cSL[i][1], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
	                    	.addComponent(cSL[i][2], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
	                    	.addComponent(cSL[i][3], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
	                    	.addComponent(cSL[i][4], GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
	                        )
	                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    )
	            );
	            cSscroll[i] = new JScrollPane(cSPanel[i]);
	            cSscroll[i].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	            cSscroll[i].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	            cSscroll[i].setBorder(null);
	            cSscroll[i].setAutoscrolls(true);
	            cSscroll[i].setDoubleBuffered(true);
	            cSscroll[i].setOpaque(false);
	            cSscroll[i].setBounds(0, 0, 860, 160);
	            cSscroll[i].getHorizontalScrollBar().setUI(new CustomScrollBar());
	    		
	            cWrap[i] = new JPanel(null);
	    		cWrap[i].setPreferredSize(new Dimension(860, 160));
	    		cWrap[i].add(cSscroll[i]);
    		}
    		WrapSec1.removeAll();
    		GroupLayout beta = new GroupLayout(WrapSec1);
    		WrapSec1.setLayout(beta);
    		beta.setHorizontalGroup(
    				beta.createParallelGroup(GroupLayout.Alignment.LEADING)
    	            .addComponent(titleBar, 1720, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    	            .addGroup(beta.createSequentialGroup()
    	            	.addGap(25)
    	                .addGroup(beta.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                    .addComponent(Section1Lab)
    	                    .addComponent(Wrap1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    	                    .addComponent(Sec2Title, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
    	                    .addComponent(Section2, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
    	                    .addComponent(Wrap2, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
    	                    .addComponent(Sec3Title, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
    	                    .addComponent(Wrap3, GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE))
    	                .addComponent(splitter)
    	                .addGroup(beta.createParallelGroup(GroupLayout.Alignment.LEADING)
        	                    .addComponent(cWL[0])
        	                    .addComponent(cWrap[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	                    .addComponent(cWL[1], GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
        	                    .addComponent(cWrap[1], GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
        	                    .addComponent(cWL[2], GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE)
        	                    .addComponent(cWrap[2], GroupLayout.PREFERRED_SIZE, 860, GroupLayout.PREFERRED_SIZE))
    	                .addGap(25))
    					);
    		beta.setVerticalGroup(
    				beta.createParallelGroup(GroupLayout.Alignment.LEADING)
    	            .addGroup(beta.createSequentialGroup()
    	                .addComponent(titleBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
    	                .addGroup(beta.createParallelGroup(GroupLayout.Alignment.LEADING)
    	                		.addGroup(beta.createSequentialGroup()
				    	                .addComponent(Section1Lab)
				    	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    	                .addComponent(Wrap1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				    	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    	                .addContainerGap(20, 20)
				    	                .addComponent(Sec2Title)
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		            .addComponent(Section2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
				    		            .addComponent(Wrap2, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		            .addContainerGap(20, 20)
				    	                .addComponent(Sec3Title)
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		            .addComponent(Wrap3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
    	                		.addComponent(splitter)
    	                		.addGroup(beta.createSequentialGroup()
				    	                .addComponent(cWL[0])
				    	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    	                .addComponent(cWrap[0], GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				    	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    	                .addContainerGap(20, 20)
				    	                .addComponent(cWL[1])
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		            .addComponent(cWrap[1], GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		            .addContainerGap(20, 20)
				    	                .addComponent(cWL[2])
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		            .addComponent(cWrap[2], GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				    		            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
    	                		)
    	                )
    		);
    		Thread customthread = new Thread(this);
    		customthread.setName("tc1");
    		customthread.start();
        }/* Custom Window End */

        this.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		minimenu.setVisible(false);
    			titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36.png")));
            }
        });
	}
	
	public void titleBarMousePressed(MouseEvent evt) {                                     
		pX = evt.getX();
        pY = evt.getY();
        minimenu.setVisible(false);
		titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36.png")));
    }
	
	public void titleBarMouseDragged(MouseEvent evt) {
		this.setLocation(this.getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
		minimenu.setVisible(false);
		titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36.png")));
	}
	
	public void run() {
		// Steam
		//System.out.println(Thread.currentThread().getName());
		String threadname = Thread.currentThread().getName();
		if (threadname.equals("t1") == true) {
			Section1Lab.setText("Loading...");
			WebParse a = new WebParse();
			String[] steamHlights = a.parsesteam("http://store.steampowered.com/", fio.steam[0], fio.steam[1], 15, 0);
			BufferedImage[] reimg = new BufferedImage[20];
	        try {
	        	for (int i = 0; i < 10; i++) {
		        	reimg[i] = ImageIO.read(new URL(steamHlights[i]));
		        	SSale1IMG[i].setIcon(new ImageIcon(reimg[i].getScaledInstance(SSale1IMG[1].getWidth(), SSale1IMG[1].getHeight(), Image.SCALE_SMOOTH)));
		        	SSale1Des[i].setText(steamHlights[i+15]);
		        	if (steamHlights[i].contains("app")) {
		        		String path = "http://store.steampowered.com/app/" + steamHlights[i].substring(steamHlights[i].indexOf("apps/") + 5, steamHlights[i].indexOf("/header"));
		        		SSale1IMG[i].setText(path);
		        	} else if (steamHlights[i].contains("sub")) {
		        		String path = "http://store.steampowered.com/sub/" + steamHlights[i].substring(steamHlights[i].indexOf("subs/") + 5, steamHlights[i].indexOf("/header"));
		        		SSale1IMG[i].setText(path);
		        	}
		        	SSale1IMG[i].addMouseListener(new MouseAdapter() {
		        		@Override
		        		public void mouseClicked(MouseEvent e) {
		        			JLabel a = new JLabel();
		        			a = (JLabel) e.getSource();
		        			try {
								Desktop.getDesktop().browse(new URI(a.getText()));
							} catch (IOException | URISyntaxException e1) {
								e1.printStackTrace();
							}
		        		}
		        	});
	        	}
	        	Section1Lab.setText("");
	        } catch(MalformedURLException e) {
	            System.out.println(steamHlights[1]);
	            Section1Lab.setText("Error: MalformedURLException");
	        } catch (IOException e) {
				e.printStackTrace();
				Section1Lab.setText("Error: IOException");
			}
	        
	        for (int j = 0; j < 9; j++) {
        		if (SSale1IMG[j+1].getIcon() == null) {
        			SSale1IMG[j+1].setVisible(false);
        			SSale1Des[j+1].setVisible(false);
        		}
        	}
	        
		} else if (threadname.equals("t2") == true) {
			WebParse b = new WebParse();
			Sec2Title.setText("Loading...");
			String[] humbleURL = b.parseHumble(fio.humble[0]);
			BufferedImage[] reimg = new BufferedImage[10];
			try {
				Sec2Lab.setIcon(new ImageIcon(new URL(humbleURL[0])));
				Sec2Lab.addMouseListener(new MouseAdapter() {
					@Override
	        		public void mouseClicked(MouseEvent e) {
	        			try {
							Desktop.getDesktop().browse(new URI(fio.humble[0]));
						} catch (IOException | URISyntaxException e1) {
							e1.printStackTrace();
						}
	        		}
	        	});
				
				for (int i = 1; i <= 9; i++) {
					if (humbleURL[i] != null) {
						if (humbleURL[i].contains("http")) {
				        	reimg[i] = ImageIO.read(new URL(humbleURL[i]));
				        	HSale1IMG[i - 1].setIcon(new ImageIcon(reimg[i].getScaledInstance(HSale1IMG[1].getWidth(), HSale1IMG[1].getHeight(), Image.SCALE_SMOOTH)));
						} else {
							HSale1IMG[i - 1].setText(humbleURL[i]);
						}
						
			        	HSale1IMG[i - 1].addMouseListener(new MouseAdapter() {
			        		@Override
			        		public void mouseClicked(MouseEvent e) {
			        			try {
									Desktop.getDesktop().browse(new URI(fio.humble[0]));
								} catch (IOException | URISyntaxException e1) {
									e1.printStackTrace();
								}
			        		}
			        	});
					}
				}
				
				for (int j = 0; j <= 9; j++) {
					if (HSale1IMG[j].getIcon() == null) {
						HSale1IMG[j].setVisible(false);
					}
				}
				if (humbleURL[29] != null) {
					Sec2LabBTA.setText(humbleURL[29]);
				} else {
					Sec2LabBTA.setText("");
				}
				Sec2Title.setText("");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				Sec2Title.setText("Error: MalformedURLException");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sec2Title.setText("Error: IOException");
			}
		} else if (threadname.equals("t3") == true) {
			String[] gmgURL;
			WebParse c = new WebParse();
			Sec3Title.setText("Loading...");
			gmgURL = c.parseGMG(fio.gmg[0], fio.gmg[1], fio.gmg[2], fio.gmg[3]);
			BufferedImage[] reimg = new BufferedImage[30];
			try {
				for (int i = 0; i <= 19; i++) {
					if (gmgURL[i] != null) {
						String tmp;
			        	reimg[i] = ImageIO.read(new URL(gmgURL[i]));
			        	GSale1IMG[i].setIcon(new ImageIcon(reimg[i].getScaledInstance(GSale1IMG[1].getWidth(), GSale1IMG[1].getHeight(), Image.SCALE_SMOOTH)));
			        	if (gmgURL[i+50] != null) {
			        		tmp = gmgURL[i+50];
			        	} else {
							tmp = "http://www.greenmangaming.com/" + gmgURL[i].substring(gmgURL[i].indexOf("ducts/") + 6, gmgURL[i].indexOf("/boxart"));
			        	}
			        	GSale1IMG[i].setText(tmp);
		        		GSale1IMG[i].addMouseListener(new MouseAdapter() {
		        			@Override
			        		public void mouseClicked(MouseEvent e) {
			        			JLabel a = (JLabel) e.getSource();
			        			try {
									Desktop.getDesktop().browse(new URI(a.getText()));
								} catch (IOException | URISyntaxException e1) {
									
									e1.printStackTrace();
								}
			        		}
			        	});
			        		
					}
		        	
	        	}
				
				for (int j = 0; j <= 9; j++) {
					if (GSale1IMG[j].getIcon() == null) {
						GSale1IMG[j].setVisible(false);
					}
				}
				Sec3Title.setText("");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				Sec3Title.setText("Error: MalformedURLException");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sec3Title.setText("Error: IOException");
			}
		} else if (threadname.equals("tc1") == true) {
			for (int i = 0; i < 3; i++) {
				if (fio.custom[i][0] != null) {
					if (fio.custom[i][0].length() >= 1) {
						cWL[i].setText(fio.custom[i][0]);
						WebParse a = new WebParse();
						String[] receive_Data = a.parseCustom(fio.custom[i][1], fio.custom[i][2], fio.custom[i][3], fio.custom[i][4]);
						BufferedImage[] reimg = new BufferedImage[10];
						try {
							for (int j = 0; j < 5; j++) {
								if (receive_Data[j] != null) {
									if (receive_Data[j].contains("http")) {
										reimg[j] = ImageIO.read(new URL(receive_Data[j]));
							        	cSL[i][j].setIcon(new ImageIcon(reimg[j].getScaledInstance(cSL[0][0].getWidth(), cSL[0][0].getHeight(), Image.SCALE_SMOOTH)));
									} else {
										cSL[i][j].setText(receive_Data[j]);
									}
									/*
						        	HSale1IMG[i - 1].addMouseListener(new MouseAdapter() {
						        		@Override
						        		public void mouseClicked(MouseEvent e) {
						        			try {
												Desktop.getDesktop().browse(new URI(fio.humble[0]));
											} catch (IOException | URISyntaxException e1) {
												e1.printStackTrace();
											}
						        		}
						        	});*/
								} else if (receive_Data[j] == null) {
									cSL[i][j].setVisible(false);
								}
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else if (fio.custom[i][0] == null) {
					cWrap[i].setVisible(false);
					cWL[i].setVisible(false);
				}
			}
		}
	}
	
	public void minimenu() {
		//minimenu.setLocationRelativeTo(Section1Lab);
		
		if (minimenu.isVisible()) {
			minimenu.setVisible(false);
			titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36.png")));
		} else {
			minimenu.setLocation((int)this.getLocation().getX(), (int)this.getLocation().getY() + 48);
			minimenu.setVisible(true);
			titleminiMenu.setIcon(new ImageIcon(getClass().getResource("menu-36-clicked.png")));
		}
	}
	
	public void buildMiniMenu() {
		minimenu.setBackground(new Color(255, 255, 255));
		minimenu.setSize(70, 100);
		minimenu.setVisible(false);
		
		JPanel item1p = new JPanel();
		JPanel item2p = new JPanel();
		JLabel item1 = new JLabel("추가/삭제");
		JLabel item2 = new JLabel("종료");
		JPanel wrap = new JPanel();
		item1.setForeground(new Color(255, 255, 255));
		item1.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		item1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
            	item1p.setBackground(new Color(100, 100, 100));
            }
			
			public void mouseExited(MouseEvent evt) {
				item1p.setBackground(new Color(0, 0, 0));
            }
			
			public void mouseClicked(MouseEvent evt) {
				if (confwin.isVisible()) {
					confwin.setVisible(false);
				} else {
					confwin.setLocationRelativeTo(Wrap1);
					confwin.setVisible(true);
				}
				minimenu.setVisible(false);
            }
		});
		
		item2.setForeground(new Color(255, 255, 255));
		item2.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		item2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
            	item2p.setBackground(new Color(100, 100, 100));
            }
			
			public void mouseExited(MouseEvent evt) {
				item2p.setBackground(new Color(0, 0, 0));
            }
			
			public void mouseClicked(MouseEvent evt) {
				System.exit(0);
            }
		});
		
		item1p.setBackground(new Color(0,0,0));
		item1p.add(item1);
		
		item2p.setBackground(new Color(0,0,0));
		//wrap.setBorder(new LineBorder(new Color(130, 130, 130), 1));
		item2p.add(item2);
		
		GroupLayout minimenuwrap = new GroupLayout(wrap);
		wrap.setLayout(minimenuwrap);
		minimenuwrap.setHorizontalGroup(
				minimenuwrap.createSequentialGroup()
					.addGroup(minimenuwrap.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(item1p)
						.addComponent(item2p))
		        );
		minimenuwrap.setVerticalGroup(
				minimenuwrap.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(minimenuwrap.createSequentialGroup()
		                	.addComponent(item1p)
		                	.addComponent(item2p)
		                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
		        );
		
		wrap.setBackground(new Color(0,0,0));
		minimenu.add(wrap);
		minimenu.pack();
	}
	
	public void configWin() {
		confwin = new JFrame("Add/Remove");
		
		JPanel to = new JPanel();
		JList listbox = new JList();
		DefaultListModel dlm = new DefaultListModel();
		for (int i = 0; i < 3; i++)
		{
			if (fio.custom[i][0] != null) {
				dlm.addElement(fio.custom[i][0]);
			}
		}
		listbox.setModel(dlm);
		JButton addBtn = new JButton("Add");
		JButton delBtn = new JButton("Delete");
		JButton okBtn = new JButton("Save");
		JButton cclBtn = new JButton("Cancel");
		
		JLabel[] textlabel = new JLabel[5];
		JTextField[] input = new JTextField[5];
		for (int i = 0; i < 5; i++) {
			textlabel[i] = new JLabel("");
			input[i] = new JTextField();
			input[i].setColumns(15);
		}
		textlabel[0].setText("사이트 이름");
		textlabel[1].setText("사이트 주소");
		textlabel[2].setText("ID/CLASS");
		textlabel[3].setText("키워드1");
		textlabel[4].setText("키워드2");
		GroupLayout configWinLayout = new GroupLayout(to);
		to.setLayout(configWinLayout);
		configWinLayout.setHorizontalGroup(
				configWinLayout.createSequentialGroup()
					.addContainerGap(10, 10)
					.addGroup(configWinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(listbox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addGroup(configWinLayout.createSequentialGroup()
								.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(delBtn, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(configWinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(configWinLayout.createSequentialGroup()
									.addComponent(textlabel[1], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addComponent(input[1], GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
							.addGroup(configWinLayout.createSequentialGroup()
								.addGroup(configWinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(configWinLayout.createSequentialGroup()
											.addComponent(textlabel[0], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addComponent(input[0], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(configWinLayout.createSequentialGroup()
											.addComponent(textlabel[2], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addComponent(input[2], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))) 
								.addGroup(configWinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(configWinLayout.createSequentialGroup()
											.addComponent(textlabel[3], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addComponent(input[3], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(configWinLayout.createSequentialGroup()
											.addComponent(textlabel[4], GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addComponent(input[4], GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										
										.addGroup(configWinLayout.createSequentialGroup()
											.addGap(25)
											.addComponent(okBtn, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
											.addGap(10)
											.addComponent(cclBtn, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
									)))
					.addContainerGap(50, 50)
		        );
		configWinLayout.setVerticalGroup(
				configWinLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(configWinLayout.createSequentialGroup()
							.addGap(10)
		                	.addComponent(listbox, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
		                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		                	.addGroup(configWinLayout.createParallelGroup()
		                			.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		                			.addComponent(delBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addGroup(configWinLayout.createSequentialGroup()
							.addGap(10)	
							.addGroup(configWinLayout.createParallelGroup()
								.addComponent(textlabel[1])
								.addComponent(input[1], GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGroup(configWinLayout.createParallelGroup()
								.addGroup(configWinLayout.createSequentialGroup()
									.addGroup(configWinLayout.createParallelGroup()
										.addComponent(textlabel[0])
										.addComponent(input[0], GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addGroup(configWinLayout.createParallelGroup()
										.addComponent(textlabel[2])
										.addComponent(input[2], GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
								.addGroup(configWinLayout.createSequentialGroup()
										.addGroup(configWinLayout.createParallelGroup()
											.addComponent(textlabel[3])
											.addComponent(input[3], GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addGroup(configWinLayout.createParallelGroup()
											.addComponent(textlabel[4])
											.addComponent(input[4], GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
										.addGap(138)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(configWinLayout.createParallelGroup()
											.addComponent(okBtn)
											.addComponent(cclBtn)))))
		        );
		confwin.add(to);
		
		listbox.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (listbox.getSelectedIndex() != -1) {
					input[0].setText(fio.custom[listbox.getSelectedIndex()][0]);
					input[1].setText(fio.custom[listbox.getSelectedIndex()][1]);
					input[2].setText(fio.custom[listbox.getSelectedIndex()][2]);
					input[3].setText(fio.custom[listbox.getSelectedIndex()][3]);
					input[4].setText(fio.custom[listbox.getSelectedIndex()][4]);
				}
			}
		});
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (dlm.getSize() < 3) {
					dlm.addElement("이름을 입력하세요");
					fio.custom[fio.customindex][0] = "새 사이트";
					fio.custom[fio.customindex][1] = "http://";
					fio.custom[fio.customindex][2] = "ID";
					fio.custom[fio.customindex][3] = "Keyword1";
					fio.custom[fio.customindex][4] = "Keyword2";
					listbox.setSelectedIndex(fio.customindex);
					fio.customindex++;
				} else {
					JOptionPane.showMessageDialog(confwin, "입력할 수 있는 사이트는 3개가 최대입니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fio.sortArray(listbox.getSelectedIndex());
				fio.saveFile();
				fio.openFile();
				dlm.clear();
				for (int i = 0; i < 3; i++) {
					if (fio.custom[i][0] != null) {
						dlm.addElement(fio.custom[i][0]);
					}
				}
			}
			
		});
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int a = listbox.getSelectedIndex();
				if (a != -1) {
					for (int i = 0; i < 5; i++) {
						if (input[i].getText() != null || !input[i].getText().equals("")) {
							if (i == 0 || i == 3 || i == 4) 
								fio.custom[a][i] = input[i].getText();
							if ((i == 1 && input[i].getText().contains("http")) || (i == 2 && (input[i].getText().contains("id") || input[i].getText().contains("class")))) 
								fio.custom[a][i] = input[i].getText();
						} else {
							switch (i) {
							case 0:
								JOptionPane.showMessageDialog(confwin, "사이트 이름을 입력해야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
								break;
							case 1:
								JOptionPane.showMessageDialog(confwin, "사이트 주소를 입력해야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
								break;
							case 2:
								JOptionPane.showMessageDialog(confwin, "찾으려는 keyword가 id인지 class인지 명시하여야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
								break;
							case 3:
								JOptionPane.showMessageDialog(confwin, "찾으려는 keyword를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
								break;
							case 4:
								JOptionPane.showMessageDialog(confwin, "두 번째 keyword도 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
								break;
							default:
								break;
							}
						}
					}
				}
				fio.saveFile();
				fio.openFile();
				dlm.clear();
				for (int i = 0; i < 3; i++) {
					if (fio.custom[i][0] != null) {
						dlm.addElement(fio.custom[i][0]);
					}
				}
			}
			
		});
		
		cclBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confwin.setVisible(false);
			}
		});
		
		confwin.pack();
		confwin.setSize(700, 290);
		confwin.setResizable(false);
		confwin.setVisible(false);
	}
	
	public void customFrame() {
		
	}
	
	public static void main(String args[]) {
		Mainframe mainWin = new Mainframe();
		mainWin.setVisible(true);
		mainWin.configWin();
		Thread th1 = new Thread(mainWin);
		th1.setName("t1");
		th1.start();
		Thread th2 = new Thread(mainWin);
		th2.setName("t2");
		th2.start();
		Thread th3 = new Thread(mainWin);
		th3.setName("t3");
		th3.start();
	}
}