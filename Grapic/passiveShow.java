package Grapic;

import javax.swing.JPanel;

public class passiveShow extends JPanel{
private infoPanel inf ;
	public passiveShow(MainFrame f) throws Exception {
		inf=infoPanel.getinsist(f);
		PassivePanel pp=new PassivePanel(f);
		add(inf);
		add(pp);
	}
}
