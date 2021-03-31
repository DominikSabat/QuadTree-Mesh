import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DataManager {

    ArrayList<Point> points;
    ArrayList<Point> nodes;

    ArrayList<Edge> edges;
    ArrayList<Element> elements;

    BufferedImage bgImg;

    Node rootNode;
    int tolerance = 5;

    public DataManager(){

        points =  new ArrayList<>();
        nodes = new ArrayList<>();

        edges = new ArrayList<>();
        elements = new ArrayList<>();

    }

}
