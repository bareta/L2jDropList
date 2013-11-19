package database.model;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer{
    String tipo;
    DateFormat formatter;
    private Color lista1;
    private Color lista2;
    private Color spoil1;
    private Color spoil2;
    private Color selecao;
    
    public TableCellRenderer(String tipo) {
        this.tipo=tipo;
        this.selecao=new Color(100, 100, 100);
        this.lista1=new Color(236, 236, 236);
        this.lista2=new Color(215, 215, 215);
        this.spoil1=new Color(255, 255, 196);
        this.spoil2=new Color(204, 204, 255);
    }

    public TableCellRenderer() {
        this.tipo="";
        this.selecao=new Color(100, 100, 100);
        this.lista1=new Color(236, 236, 236);
        this.lista2=new Color(215, 215, 215);
        this.spoil1=new Color(255, 255, 196);
        this.spoil2=new Color(204, 204, 255);        
    }

    public TableCellRenderer(Color lista1, Color lista2, Color selecao) {
        this.lista1 = lista1;
        this.lista2 = lista2;
        this.selecao = selecao;
        this.spoil1=new Color(255, 255, 196);
        this.spoil2=new Color(204, 204, 255);  
    }

    public TableCellRenderer(Color lista1, Color lista2, Color spoil1, Color spoil2, Color selecao) {
        this.lista1 = lista1;
        this.lista2 = lista2;
        this.spoil1 = spoil1;
        this.spoil2 = spoil2;
        this.selecao = selecao;
    }
        
    @Override
    public Component getTableCellRendererComponent (JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        
        if (isSelected) {
            if(column==0){
                cell.setBackground(new Color(150, 150, 150));
            }else{
                cell.setBackground(selecao);
            }
        }else{
            cell.setForeground(Color.BLACK);
            if(column<0){
                cell.setBackground(new Color(0, 0, 0));
                cell.setForeground(Color.white);
            }else{
                if(table.getColumnCount()>=4){
                    //tabela com mais de 4 colunas lista os drops
                    if(table.getValueAt(row,4).toString().equalsIgnoreCase("SPOIL")){
                        //pinta as linhas de conteudo spoliavel
                        if (row % 2 == 0) {
                            cell.setBackground(spoil1);
                        }else{
                            cell.setBackground(spoil2);
                        }                        
                    }else{
                        //conteudo normal igual a busca
                        if (row % 2 == 0) {
                            cell.setBackground(lista1);
                        }else{
                            cell.setBackground(lista2);
                        }                         
                    }
                }else{
                    //tabela de busca
                    if (row % 2 == 0) {
                        cell.setBackground(lista1);
                    }else{
                        cell.setBackground(lista2);
                    }
                }
            }
        }
        return cell;
    }
    
    @Override
    public void setValue(Object value) {
        if (value instanceof JLabel){
            setText(((JLabel)value).getText());
            setIcon(((JLabel)value).getIcon());
            setBorder(new EmptyBorder(0,0,0,0));
        }else if (value instanceof String){
            setIcon(null);
            super.setValue(value);        
        }else if (value instanceof Integer){
            setIcon(null);
            super.setValue(value);
        }else{
            setIcon(null);
            super.setValue(value);
        }
    }    
}
