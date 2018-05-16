import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.TexturePaint;

class MenuPanel extends JPanel
{
    private Image image;
    private BufferedImage bufferedImage;
    private TexturePaint texture;
    Rectangle rect;

    public MenuPanel()
    {
        image = new ImageIcon(getClass().getResource("KwirkCover.png")).getImage();
        bufferedImage = toBufferedImage(image, image.getWidth(null), image.getHeight(null));
        setPreferredSize(new Dimension(695, 700));
        rect = new Rectangle(0, 0, 695, 700);
        texture = new TexturePaint(bufferedImage, rect);
        repaint();
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        setBackground(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(texture);
        g2.setPaint (texture);
        g2.fillRect(0, 0, 695, 700);
        Stroke previousSroke = g2.getStroke();
        g2.setStroke(new BasicStroke(4.0f));
        g2.setStroke(previousSroke);
        g2.setPaint (Color.BLACK);
        
        repaint();
    }

    public static BufferedImage toBufferedImage(Image image, int width, int height)
    {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage bimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, 695, 700, 0, 0, 695, 700, null);
        bGr.dispose();
        return bimage;
    }
}
