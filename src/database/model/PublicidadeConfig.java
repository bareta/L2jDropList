/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database.model;

import ads.WinManage;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class PublicidadeConfig {
    public static void addBanner(JPanel painel){        
        painel.add(WinManage.createContent("http://localhost/trunk/bareta/droplist/") , BorderLayout.CENTER);
    }
}
