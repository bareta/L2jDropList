/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.model;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JViewport;

public class HandScrollListener extends MouseAdapter{
    private final Cursor defCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    private final Cursor hndCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    private final Point pp = new Point();
    private JLabel image;

    public HandScrollListener(JLabel image){
        this.image = image;
    }

    @Override
    public void mouseDragged(final MouseEvent e){
        JViewport vport = (JViewport)e.getSource();
        Point cp = e.getPoint();
        Point vp = vport.getViewPosition();
        vp.translate(pp.x-cp.x, pp.y-cp.y);
        image.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
        pp.setLocation(cp);
    }

    @Override
    public void mousePressed(MouseEvent e){
        image.setCursor(hndCursor);
        pp.setLocation(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e){
        image.setCursor(defCursor);
        image.repaint();
    }
}