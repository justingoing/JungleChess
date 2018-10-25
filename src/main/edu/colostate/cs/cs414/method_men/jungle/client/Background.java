package edu.colostate.cs.cs414.method_men.jungle.client;

import java.awt.*;

public class Background extends Canvas{

    Image img;

    public Background(){
        img = Toolkit.getDefaultToolkit().getImage("/home/connor/IdeaProjects/cs414-f18-001-Method-Men/src/Images/jungle.jpg");
    }

    public void paint(Graphics g){
        int width = getSize().width;
        int height = getSize().height;
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(img, 0, 0, width, height, this);
    }
}
