/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import database.model.MyTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class Read {
    private conectaH2 conn;
    
    public Read(){
        conn=new conectaH2();
    }
    
    public List<String> Items(){
        String text,dir;
        List<String> item = new ArrayList<String>();
        dir=getClass().getResource("/database/item").getPath();
        File f = new File(dir);
        File[] listOfFiles = f.listFiles();
        for (int j = 0; j < listOfFiles.length; j++) {
            if (listOfFiles[j].isFile()) {
                //text = listOfFiles[j].getName();
                //System.out.println(text);
                item.add(listOfFiles[j].getName());
            }
        }
        return item;
    }
    
    public List<String> Npcs(){
        String text,dir;
        List<String> item = new ArrayList<String>();
        dir=getClass().getResource("/database/npcs").getPath();
        System.out.println(dir);
        File f = new File(dir);
        File[] listOfFiles = f.listFiles();
        System.out.println("SIze:"+listOfFiles.length);
        for (int j = 0; j < listOfFiles.length; j++) {
            if (listOfFiles[j].isFile()) {
                //text = listOfFiles[j].getName();
                //System.out.println(text);
                item.add(listOfFiles[j].getName());
            }
        }
        return item;
    }
    
    public void ReadAll(MyTableModel model){
        List<String> item = Items();
        for(int i=0;i<item.size();i++){
            System.out.println("LENDO:"+item.get(i));
            ReadXml(model, item.get(i));
        }
    }
    
    public void ReadAllNpc(MyTableModel model){
        List<String> item = Npcs();
        for(int i=0;i<item.size();i++){
            System.out.println("LENDO NPCS:"+item.get(i));
            ReadXmlNpc(model, item.get(i));
        }
    }
    
    public void ReadXml(MyTableModel model,String XML){
        List<Object> ufl = new ArrayList<Object>();
        String loc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc = null;
        Element raiz,contato;
        NodeList listaContatos;

        try {
            db = dbf.newDocumentBuilder();
            loc = getClass().getResource("/database/item/"+XML).toString();
            doc = db.parse(loc);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        raiz = doc.getDocumentElement();
        listaContatos = raiz.getElementsByTagName("item");

        String nome,icone = "",preco = "0";
        int id;
        NodeList sets;
        Element specs;
        conn.conectar();
        for (int i=0; i<listaContatos.getLength(); i++){
            contato = (Element) listaContatos.item(i);

            sets=contato.getElementsByTagName("set");
            
            for(int i2=0;i2<sets.getLength();i2++){
                specs=(Element) sets.item(i2);
                if(specs.getAttribute("name").equalsIgnoreCase("price")){
                    preco=specs.getAttribute("val");
                }
                if(specs.getAttribute("name").equalsIgnoreCase("icon")){
                    icone=specs.getAttribute("val");
                }                
            }
            
            nome=contato.getAttributeNode("name").getNodeValue();
            id=new Integer(contato.getAttributeNode("id").getNodeValue());
            
            model.addRow(new Object[]{id,nome,preco,icone});
            String sql;
            if(!nome.equalsIgnoreCase("")){
                nome=nome.replace("'", "");
            }
            if(!icone.equalsIgnoreCase("")){
                icone=iconName(icone);
            }
            sql="insert into items(id,nome,preco,icon) values ("+id+",'"+nome+"',"+preco+",'"+icone+"')";
            //System.out.println(sql);
            conn.execute(sql);            
        }    
        conn.desconectar();
        //return ufl;
    }
    
    public static String iconName(String icone){
        String ico="icones/";
        ico+=icone;
        ico=ico.replace("'", "");
        ico=ico.replace("Branchsys2.icon.","");
        ico=ico.replace("BranchSys.","").replace("branchsys2.","");
        ico=ico.replace("BranchSys2.","");
        ico=ico.replace("icon.", "");
        ico=ico.replace("Icon.", "");
        ico=ico.replace("icon2.", "");
        ico=ico.replace("etc_i.", "");        
        ico=ico.replace("br_cashtex.item.", "");
        ico+=".jpg";
        if(ico.equalsIgnoreCase("icones/.jpg")){
            ico="icones/NOIMAGE.jpg";
        }
        return ico;
    }
    
    public void ReadXmlNpc(MyTableModel model,String XML){
        List<Object> ufl = new ArrayList<Object>();
        String loc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc = null;
        Element raiz,contato;
        NodeList listaContatos;

        try {
            db = dbf.newDocumentBuilder();
            loc = getClass().getResource("/database/Npcs/"+XML).toString();
            doc = db.parse(loc);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        raiz = doc.getDocumentElement();
        listaContatos = raiz.getElementsByTagName("npc");

        String nome;
        int id;

        conn.conectar();
        for (int i=0; i<listaContatos.getLength(); i++){
            contato = (Element) listaContatos.item(i);
            
            nome=contato.getAttributeNode("clientName").getNodeValue();
            id=new Integer(contato.getAttributeNode("id").getNodeValue());
            
            model.addRow(new Object[]{id,nome});
            String sql;
            if(!nome.equalsIgnoreCase("")){
                nome=nome.replace("'", "");
            }            
            sql="insert into npc(id,nome) values ("+id+",'"+nome+"')";
            conn.execute(sql);
            //System.out.println(sql);            
        }      
        conn.desconectar();
        //return ufl;
    }   
    
    public void Busca(String txt){
                
    }
}