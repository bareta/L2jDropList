package Telas;

import database.model.Configurator;
import database.model.HandScrollListener;
import database.model.PublicidadeConfig;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class Map extends javax.swing.JFrame {
    private String mob;
    private List<int[]> coords;
    private boolean err;
    
    public Map(String mob){
        this.mob=mob;
        setName("MAPA-"+mob);
        err=false;
        initComponents();
        scrollPane.getVerticalScrollBar().setUnitIncrement(50);
        HandScrollListener scrollListener = new HandScrollListener(lbMap);
        scrollPane.getViewport().addMouseMotionListener(scrollListener);
        scrollPane.getViewport().addMouseListener(scrollListener);
        try{
            setIconImage(
                Toolkit.getDefaultToolkit().getImage(Configurator.iconName("etc_map_ivory_i00"))
            );
        }catch(Exception e){
            System.err.println("Falha ao buscar icone");
        }
        toFront();
    }

    public void setCoords(List<int[]> coords) {
        if(coords.size()>0){
            this.coords = coords;
        }else{
            err=true;
        }
    }

    public void SetMap(){
        if(!err){
            lbMap.setIcon(pintaImg());
        }
    }
    
    public void CenterMap(){
        if(!err){
            Dimension size=scrollPane.getViewport().getViewSize();        
            int tempx=getX(coords.get(0)[0]);
            int tempy=getY(coords.get(0)[1]);
            tempx=(((size.width*100)/1812)*tempx)/100;
            tempy=(((size.height*100)/2620)*tempy)/100;        
            Point drop=new Point(tempx,tempy);        
            Rectangle retangulo=scrollPane.getViewport().getViewRect();
            int maxX=size.width-retangulo.width;
            int maxY=size.height-retangulo.height;
            int x=drop.x-(retangulo.width/2);
            int y=drop.y-(retangulo.height/2);
            Point view=new Point(x>maxX?maxX:x,y>maxY?maxY:y);
            scrollPane.getViewport().setViewPosition(view);
        }
    }
    
    private BufferedImage scaleImage(BufferedImage img, int width, int height,Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth*height < imgHeight*width) {
            width = imgWidth*height/imgHeight;
        } else {
            height = imgHeight*width/imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    private Icon pintaImg(){
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("map.jpg"));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            int w = img.getWidth(null);
            int h = img.getHeight(null);

            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawImage(img, 0, 0, null);

            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.BLUE);
            g.drawString(mob, 10, 30);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("População "+coords.size(), 10, 60);
            
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.setColor(Color.red);

            
            int x,y;
            int[] c;
            for(int i=0;i<coords.size();i++){
                c=coords.get(i);
                x=getX(c[0])-5;
                y=getY(c[1])+7;
                g.drawString("+", x, y);
            }     

            Icon image = new ImageIcon( scaleImage(bi, 1600, 1930,Color.BLACK) );        
            return image;
        }
        
    private int getX(int x){
        return 116+(x+107823)/200;
    }
    
    private int getY(int y){
        return 2580+(y-255420 )/200;
    }
 
    @Override
    public void setVisible(boolean b) {
        if(err){
            JOptionPane.showMessageDialog(this, "Nenhum spawn encontrado", "L2 DROPLIST", JOptionPane.ERROR_MESSAGE);
            this.dispose();           
        }else{
            super.setVisible(b);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Publicidade = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        lbMap = new javax.swing.JLabel();

        Publicidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Publicidade"));
        Publicidade.setName("JPub");
        Publicidade.setPreferredSize(new java.awt.Dimension(0, 0));
        Publicidade.setLayout(new java.awt.BorderLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DropList Map");
        setMinimumSize(new java.awt.Dimension(550, 500));

        lbMap.setMinimumSize(new java.awt.Dimension(300, 300));
        scrollPane.setViewportView(lbMap);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Publicidade;
    private javax.swing.JLabel lbMap;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}