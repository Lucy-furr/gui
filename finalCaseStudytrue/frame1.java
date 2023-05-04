package finalCaseStudytrue;

import java.awt.*;

import javax.swing.*;
public class frame1 extends JFrame{

	frame1(){
		
		ImageIcon image1 = new ImageIcon("tsuLogo.png");
		 this.setIconImage(image1.getImage());

		ImageIcon image = new ImageIcon(new ImageIcon("tsuLogo.png").getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
		JLabel bgLabel = new JLabel("",image, JLabel.LEFT); 
		bgLabel.setBounds(0, 0, 450, 450);
		bgLabel.setVerticalAlignment(JLabel.TOP);
		
		JLabel header = new JLabel();
		header.setText("<html><H1>TSU Canteen</H1></html>");
		header.setForeground(new Color(137, 129, 33));
		header.setFont(new Font("MV boli", Font.BOLD, 80));
		header.setHorizontalTextPosition(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.TOP);
		
		//JPanel logo = new JPanel();
		//logo.add(bgLabel);
		//logo.setBounds(0, 0, 200, 200);
		
		 this.setTitle("CCS Canteen");
		 this.setSize(750, 600);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //this.setResizable(false);
		 this.setLocationRelativeTo(null);
		 this.add(header);
		 this.add(bgLabel);
		 this.setVisible(true);
		 this.getContentPane().setBackground(new Color(237, 198, 177));
		 
	}
}
