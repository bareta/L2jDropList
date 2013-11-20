package database.model;

import Telas.DropListNpc;
import Telas.Map;
import java.awt.Color;
import java.awt.Window;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Configurator {
    private static Properties properties = new Properties();
    
    /**
     * Carrega informações de uma chave especifica
     * @param confignome String com o nome da chave 
     * @return String com o valor da chave
     */
    public static String ReadConfigSingle(String confignome){
        try {
            properties.load(new FileInputStream("config.ini"));
        } catch (IOException ex) {
            Logger.getLogger(Configurator.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }          
        String value=properties.getProperty(confignome);
        return value;
    }

    /**
     * Carrega informações de uma lista de chaves especifica
     * @param confignome List com o nome das chave 
     * @return String com o valor da chave
     */
    public static HashMap<String, String> ReadConfigSingle(Object[] confignome){
        HashMap<String, String> map = new HashMap<String, String>();
        String key,value;
        try {
            properties.load(new FileInputStream("config.ini"));
        } catch (IOException ex) {
            Logger.getLogger(Configurator.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
        for(int i=0;i<confignome.length;i++){ 
            key=confignome[i].toString();
            value=properties.getProperty(key);
            map.put(key, value);
        }
        return map;
    }    
        
    /**
     * Salva configuraçoes no config.ini
     * @param confignome String com o nome da propriedade
     * @param campos valor da propriedade
     */
    public static void SaveConfig(String confignome, String campos){        
        properties.setProperty(confignome,campos); 
        try {
            properties.store(new FileOutputStream("config.ini"), "Arquivo de Configurações");
        } catch (IOException ex) {
            Logger.getLogger(Configurator.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    public static ImageIcon Icon(String icone){
        ImageIcon ico = new ImageIcon(icone);
        if(ico.getImageLoadStatus()==4){
            ico = new ImageIcon("icones/NOIMAGE.jpg");
        }
        return ico;
    }
    
    public static String iconName(String icone){
        String ico="icones/";
        ico+=icone;
        ico=ico.replace("Branchsys2.icon.","").replace("BranchSys.","").replace("branchsys2.","");
        ico=ico.replace("BranchSys2.","").replace("icon.", "").replace("Icon.", "");
        ico=ico.replace("icon2.", "").replace("etc_i.", "").replace("br_cashtex.item.", "");
        ico+=".jpg";
        if(ico.equalsIgnoreCase("icones/.jpg")){
            ico="icones/NOIMAGE.jpg";
        }
        return ico;
    }
        
    private static ImageIcon downloadFile(String link){
        String x="http://localhost/trunk/bareta/sindicato/agrosala/noticias/database/img/";
        ImageIcon ico = null;
        System.out.println("DOWN="+x+link);
        try {            
            URL url = new URL(x+link);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream(); 
           
            File novoArq = new File(link.replace("%20", " ").replace("icon.", ""));
            if(!novoArq.exists()){
                
                BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(novoArq));
                byte[] buffer = new byte[32 * 1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {
                    fOut.write(buffer, 0, bytesRead);
                }
                fOut.flush();
                fOut.close();
                is.close(); 
            }
            ico = new ImageIcon(novoArq.getAbsolutePath());
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
        }
        return ico;
    }
    
    /*
     * Pega valor de chaves do config.ini
     * caso a chave na exista insere o valor 1
     */
    public static int getValue(String key){
        int v;
        try{
            v=Integer.parseInt(Configurator.ReadConfigSingle(key));
        }catch(Exception e){
            v=1;
            Configurator.SaveConfig(key, "1");
        }
        return v;
    }
    
    public static Color getCor(String key){
        return new Color(Integer.parseInt(Configurator.ReadConfigSingle(key)));
    }
    
    public static void setDefaultColors(){
        Configurator.SaveConfig("Lista1",new Color(236, 236, 236).getRGB()+"");        
        Configurator.SaveConfig("Lista2",new Color(215, 215, 215).getRGB()+"");
        Configurator.SaveConfig("Spoil1",new Color(255, 255, 196).getRGB()+"");
        Configurator.SaveConfig("Spoil2",new Color(204, 204, 255).getRGB()+"");
        Configurator.SaveConfig("Selecao",new Color(100, 100, 100).getRGB()+"");       
    }
    
    public static void testColors(){
        try{
            int v=Integer.parseInt(Configurator.ReadConfigSingle("Lista1"));
        }catch(Exception e){
            Configurator.setDefaultColors();
        }
    }
    
    public static boolean getWindow(String janela){
        boolean n=false;
        Window w[]=Window.getWindows();
        Window j;
        for(int i=0;i<w.length;i++){
            j=w[i];    
            //System.out.println("JANELA="+j.getName()+"="+janela);
            if(j.getName().equalsIgnoreCase(janela)){
                j.setVisible(true);                    
                j.toFront();
                j.setLocationRelativeTo(null);
                j.requestFocus();
                if(w[i] instanceof Telas.DropListNpc){
                    ((DropListNpc)w[i]).setState(JFrame.NORMAL);
                }else if(w[i] instanceof Telas.Map){
                    ((Map)w[i]).setState(JFrame.NORMAL);
                }
                n=true;
                break;
            }
        }
        return n;
    }    
}
