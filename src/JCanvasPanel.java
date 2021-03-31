import javax.swing.*;
import java.awt.*;

public class JCanvasPanel extends JPanel {

    DataManager dm;

    public JCanvasPanel(DataManager dm){

        this.dm = dm;

    }

    void drawSubdivision(Node root, Graphics2D g2){

        if(root.n1!=null && root.n1.rect.isWhite==false)
        {
            drawSubdivision(root.n1,g2);
        }


        if(root.n2!=null &&  root.n2.rect.isWhite==false)
        {
            drawSubdivision(root.n2,g2);
        }


        if(root.n3!=null &&  root.n3.rect.isWhite==false)
        {
            drawSubdivision(root.n3,g2);
        }


        if(root.n4!=null &&  root.n4.rect.isWhite==false)
        {
            drawSubdivision(root.n4,g2);
        }

        if(root.n1==null&&root.rect.isMixed==true) {
            g2.setColor(Color.red);
            g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);
            g2.drawLine(root.rect.origin.x, root.rect.origin.y, root.rect.origin.x + root.rect.width, root.rect.origin.y + root.rect.height); //dodaje przekątne [trojkaty]
        }

        if(root!=null&&root.rect.isBlack==true) {
            g2.setColor(Color.red);
            g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);
            g2.drawLine(root.rect.origin.x, root.rect.origin.y, root.rect.origin.x + root.rect.width, root.rect.origin.y + root.rect.height); //dodaje przekątne [trojkaty]
        }

        /*if(root.n1 != null)
            drawSubdivision(root.n1, g2);
        if(root.n2 != null)
            drawSubdivision(root.n2, g2);
        if(root.n3 != null)
            drawSubdivision(root.n3, g2);
        if(root.n4 != null)
            drawSubdivision(root.n4, g2);*/

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(dm.bgImg, 0, 0, this);
        drawSubdivision(dm.rootNode, g2);
    }

    @Override
    public void repaint() {
        super.repaint();



    }
}
