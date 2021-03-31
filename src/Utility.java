import java.awt.*;

public class Utility {

    DataManager dm;

    public Utility(DataManager dm) {

        this.dm = dm;

    }

    Boolean checkWhite(Rectangle rect) {

        Boolean isWhite = false;

        for (int wi = rect.origin.x; wi < (rect.origin.x + rect.width); wi++) {
            for (int hi = rect.origin.y; hi < (rect.origin.y + rect.height); hi++) {

                Color readColor = new Color(dm.bgImg.getRGB(wi, hi));

                if (readColor.getRed() == 255 && readColor.getGreen() == 255 && readColor.getBlue() == 255)
                    isWhite = true;
            }
        }
        return isWhite;
    }

    Boolean checkBlack(Rectangle rect) {

        Boolean isBlack = false;

        for (int wi = rect.origin.x; wi < (rect.origin.x + rect.width); wi++) {
            for (int hi = rect.origin.y; hi < (rect.origin.y + rect.height); hi++) {

                Color readColor = new Color(dm.bgImg.getRGB(wi, hi));

                if (readColor.getRed() == 0 && readColor.getGreen() == 0 && readColor.getBlue() == 0)
                    isBlack = true;
            }
        }
        return isBlack;
    }


    Boolean checkFill(Rectangle rect) {

        Boolean mixed = false;
        Boolean isBlack = false;
        Boolean isWhite = false;

        //700 x 500 /4
        //(0, 0), (350, 0), (0, 250), (350, 250)
        //black color 0 0 0
        //white color 255 255 255

        for (int wi = rect.origin.x; wi < (rect.origin.x + rect.width); wi++) {

            for (int hi = rect.origin.y; hi < (rect.origin.y + rect.height); hi++) {

                Color readColor = new Color(dm.bgImg.getRGB(wi, hi));

                if (readColor.getRed() == 0 && readColor.getGreen() == 0 && readColor.getBlue() == 0)
                    isBlack = true;

                if (readColor.getRed() == 255 && readColor.getGreen() == 255 && readColor.getBlue() == 255)
                    isWhite = true;

                if (isBlack && isWhite)
                    mixed = true;
            }

        }
        return mixed;
    }

    void subdivide(Node root) {

        if (checkFill(root.rect)) {
            root.rect.isMixed = true;

            int width = root.rect.width / 2;
            int height = root.rect.height / 2;

            if (width > dm.tolerance && height > dm.tolerance) {

                Node n1 = new Node(new Rectangle(new Point(root.rect.origin.x, root.rect.origin.y), width, height), null, null, null, null);
                Node n2 = new Node(new Rectangle(new Point(root.rect.origin.x + width, root.rect.origin.y), width, height), null, null, null, null);
                Node n3 = new Node(new Rectangle(new Point(root.rect.origin.x, root.rect.origin.y + height), width, height), null, null, null, null);
                Node n4 = new Node(new Rectangle(new Point(root.rect.origin.x + width, root.rect.origin.y + height), width, height), null, null, null, null);

                root.n1 = n1;
                root.n2 = n2;
                root.n3 = n3;
                root.n4 = n4;

                subdivide(root.n1);
                subdivide(root.n2);
                subdivide(root.n3);
                subdivide(root.n4);

            }
        }

        else
            {
            root.rect.isWhite = checkWhite(root.rect);
            root.rect.isBlack = checkBlack(root.rect);
            }
    }

    void constructQT() {

        int width = 700;
        int height = 500;

        Rectangle initialRectangle = new Rectangle(new Point(0, 0), width, height);

        dm.rootNode = new Node(initialRectangle, null, null, null, null);

        subdivide(dm.rootNode);
    }
}