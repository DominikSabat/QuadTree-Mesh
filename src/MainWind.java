import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainWind extends JFrame{

    private JPanel panel1;
    private JPanel mainPanel;
    private JCanvasPanel canvas;
    private JPanel buttonPanel;

    private JButton treeBut;

    DataManager dm;
    Utility util;

    public MainWind(String title){

        super(title);

        dm = new DataManager();
        util = new Utility(dm);

        //==============================================================================================================

        treeBut = new JButton("Create mesh");

        //==============================================================================================================

        treeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.repaint();
            }
        });

        //==============================================================================================================

        try {
            BufferedImage bg = ImageIO.read(new File("bg.jpg"));
            dm.bgImg = bg;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //==============================================================================================================

        canvas = new JCanvasPanel(dm);
        buttonPanel = new JPanel();

        buttonPanel.add(treeBut);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(1, 1));

        mainPanel.add(BorderLayout.CENTER, canvas);
        mainPanel.add(BorderLayout.EAST, buttonPanel);

        //==============================================================================================================

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(825, 550));
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args){

        MainWind mw = new MainWind("Mesh Generator");
        //JFrame mainFrame = new MainWind("Mesh Generator");
        mw.setVisible(true);

        mw.canvas.repaint();

        //mw.dm.points.add(new Point(20, 20));

        Utility util = new Utility(mw.dm);
        util.constructQT();


        //==============================================================================================================

//        mainFrame.pack();
//        mainFrame.setVisible(true);

    }

}
