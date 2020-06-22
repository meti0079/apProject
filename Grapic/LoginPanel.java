package Grapic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.w3c.dom.NameList;

import GAME.Gamestate;
import GAME.Logger;
import GAME.Login;
import GAME.Store;
import GAME.players;

public class LoginPanel extends JPanel {

	private JTextField namefield;
	private JTextField namefield2;
	private JPasswordField passfield;
	private JPasswordField passfield2;
	private JButton Blogin;
	private JButton BNewAccount;
	private Gamestate game;
	private JLabel error;
	private JLabel error1;
	private Logger log;
	public LoginPanel(MainFrame f,MenuPanel p) throws Exception {
		Dimension dim=new Dimension(500, 1000);
		setPreferredSize(dim);
		log=Logger.liginsist();
		game=Gamestate.getinsist();
		this.namefield=new JTextField(15);
		this.passfield=new JPasswordField(15);
		this.namefield2=new JTextField(15);
		this.passfield2=new JPasswordField(15);
		this.Blogin=new JButton("Sign in");
		this.BNewAccount =new JButton("Create new");
		///////set erooooor
		error=new JLabel("username or password is incoreect!!!!  try again");
		error.setFont(new Font("Tahoma", Font.BOLD, 15));
		error.setForeground(Color.RED);
		error.setVisible(false);
		error1=new JLabel("username  has already used!!!!");
		error1.setFont(new Font("Tahoma", Font.BOLD, 15));
		error1.setForeground(Color.RED);
		error1.setVisible(false);

		//////set  listener
		Blogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name =namefield.getText();
				String pass=passfield.getText();
				if(namefield.getText().equals("")) {
					error.setVisible(true);
				}else {
					if(passfield.getText().equals("")) {
						error.setVisible(true);
					}else {
						try {
							if(game.checkname(name, pass)) {
								game.readplayer(name);
								game.setStor(game.getPlayer().getMystore());
								setVisible(false);
								f.add(p);
								f.pack();
								f.setLocationRelativeTo(null);
							}else {
								error.setVisible(true);							
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				try {
					log.log(game.getPlayer().get_name(), "login at :  ", "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		BNewAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name=namefield2.getText();
				String pass=passfield2.getText();
				try {
					if(game.checkvalid(name)) {
						error1.setVisible(true);
					}else {
						File loog=new File(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+name);
						loog.getParentFile().mkdir();
						loog.createNewFile();
						game.setPlayer(new players(name, pass, 50));
						log.makelog(game.getPlayer().get_name(), game.getPlayer().get_pass());
						log.log(game.getPlayer().get_name(), "sign up ", " ");
						game.setStor(new Store());
						game.getPlayer().setMystore(game.getStor());
						Login log=new Login(game.getPlayer());
						setVisible(false);
						f.add(p,BorderLayout.CENTER);
						f.pack();
						f.setLocationRelativeTo(null);
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		});
		layoutComponenet();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon image = new ImageIcon("loginBackground.png"); 
		g.drawImage(image.getImage(),0,0,null);
		g.setColor(Color.WHITE);
		g.drawLine(20, 550, 200, 550);
		g.drawLine(300, 550, 475, 550);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.drawString("OR", 240, 555);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		///// cadr dor
		g.drawLine(15,35, 15, 985);
		g.drawLine(120, 25, 485, 25);
		g.drawLine(485, 25, 485, 985);
		g.drawLine(15, 985, 485, 985);
		g.drawString("WellCome", 10	, 30);
		g.drawString("Sign in", 30, 80);
		g.drawString("Username :", 50, 202);
		g.drawString("Password :", 50, 248);
		g.drawString("create account", 30, 600);
		g.drawString("Username :", 50, 659);
		g.drawString("Password :", 50, 709);
	}
	public void layoutComponenet() {
		setLayout(new GridBagLayout());
		GridBagConstraints g= new GridBagConstraints();
		g.weightx=0;
		g.weighty=0.1;
		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.NONE;
		g.anchor=GridBagConstraints.LINE_END;
		g.insets=new Insets(100,150,0, 150);
		add(namefield,g);
		////////////////  second row////////////////	
		g.gridx=0;
		g.insets=new Insets(190, 180, 0, 150);
		g.anchor=GridBagConstraints.LINE_START;
		add(passfield,g);
		////////////////next     
		g.gridy++;	
		g.gridx=0;
		g.insets=new Insets(0, 150, 600,220);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(Blogin,g);
		////////////////third row////////////////
		g.gridx=0;
		g.insets=new Insets(350,180, 0, 0);
		g.anchor=GridBagConstraints.FIRST_LINE_START;
		add(namefield2,g);
		/////  box
		g.gridx=0;
		g.insets=new Insets(400,180, 0, 0);
		g.anchor=GridBagConstraints.FIRST_LINE_START;
		add(passfield2,g);
		////////////////next     
		g.gridx=0;
		g.insets=new Insets(450,150, 0,180);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(BNewAccount,g);
		g.gridx=0;
		g.insets=new Insets(582,100, 0,140);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(error1,g);
		g.gridx=0;
		g.insets=new Insets(82,40, 0,80);
		g.anchor=GridBagConstraints.FIRST_LINE_END;
		add(error,g);
	}
}