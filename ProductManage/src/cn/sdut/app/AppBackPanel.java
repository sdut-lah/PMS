package cn.sdut.app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cn.sdut.main.Product;

public class AppBackPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	Image image;
	/**
	 * 添加背景图片面板
	 * @param img
	 */
	public AppBackPanel(String img){
		
		URL url = Product.class.getResource("/images/"+img);
		image = new ImageIcon(url).getImage();
	}
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),this);
		
	}

}
