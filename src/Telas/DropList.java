package Telas;

import database.conectaH2;
import database.model.Configurator;
import database.model.PublicidadeConfig;
import database.model.TableCellRenderer;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import updater.AutoUpdater;
import util.NetworkInfo;
/**
 *
 * @author Fábio Ricardo Bareta
 */
public class DropList extends javax.swing.JFrame {
    
    private conectaH2 conn;
    /** Creates new form DropList */
    public DropList() {        
        Configurator.testColors();
        initComponents();
        conn=new conectaH2();                
        
        TableCellRenderer cells=new TableCellRenderer(
                                        Configurator.getCor("Lista1"),
                                        Configurator.getCor("Lista2"),
                                        Configurator.getCor("Selecao")
                                );
        tabela.setDefaultRenderer(String.class, cells);
        tabela.setDefaultRenderer(Integer.class, cells);
        tabela.setDefaultRenderer(Object.class, cells);
        
        tabela.setRowHeight(32);
        
        jLabel11.setIcon(Configurator.Icon("icones/etc_alphabet_l_i00.jpg"));
        jLabel12.setIcon(Configurator.Icon("icones/etc_alphabet_i_i00.jpg"));
        jLabel13.setIcon(Configurator.Icon("icones/etc_alphabet_n_i00.jpg"));
        jLabel14.setIcon(Configurator.Icon("icones/etc_alphabet_e_i00.jpg"));
        jLabel15.setIcon(Configurator.Icon("icones/etc_alphabet_a_i00.jpg"));
        jLabel16.setIcon(Configurator.Icon("icones/etc_alphabet_g_i00.jpg"));
        jLabel17.setIcon(Configurator.Icon("icones/etc_alphabet_e_i00.jpg"));
        jLabel18.setIcon(Configurator.Icon("icones/etc_alphabet_ii_i00.jpg"));
        
        jLabel19.setText("    ");
        
        jLabel20.setIcon(Configurator.Icon("icones/etc_alphabet_d_i00.jpg"));
        jLabel21.setIcon(Configurator.Icon("icones/etc_alphabet_r_i00.jpg"));
        jLabel22.setIcon(Configurator.Icon("icones/etc_alphabet_o_i00.jpg"));
        jLabel23.setIcon(Configurator.Icon("icones/etc_alphabet_p_i00.jpg"));
        jLabel24.setIcon(Configurator.Icon("icones/etc_alphabet_l_i00.jpg"));
        jLabel25.setIcon(Configurator.Icon("icones/etc_alphabet_i_i00.jpg"));
        jLabel1.setIcon(Configurator.Icon("icones/etc_alphabet_s_i00.jpg"));
        jLabel2.setIcon(Configurator.Icon("icones/etc_alphabet_t_i00.jpg"));
        
        try{
            setIconImage(
                Toolkit.getDefaultToolkit().getImage(Configurator.iconName("br_cash_rune_of_rp_i00"))
            );
        }catch(Exception e){
            System.err.println("Falha ao buscar icone");
        }        
        setLocationRelativeTo(null);
        
        PublicidadeConfig.addBanner(Publicidade);
        pack();
        NetworkInfo.show();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textBusca = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Publicidade = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("L2J Drop Calculator");
        setName("DropList");

        textBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBuscaKeyReleased(evt);
            }
        });

        btnBusca.setText("Buscar");
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getColumn(0).setMinWidth(50);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);
        tabela.getColumnModel().getColumn(1).setMinWidth(100);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
        tabela.getColumnModel().getColumn(2).setMinWidth(50);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(2).setMaxWidth(50);

        jPanel1.add(jLabel11);
        jPanel1.add(jLabel12);
        jPanel1.add(jLabel13);
        jPanel1.add(jLabel14);
        jPanel1.add(jLabel15);
        jPanel1.add(jLabel16);
        jPanel1.add(jLabel17);
        jPanel1.add(jLabel18);
        jPanel1.add(jLabel19);
        jPanel1.add(jLabel20);
        jPanel1.add(jLabel21);
        jPanel1.add(jLabel22);
        jPanel1.add(jLabel23);
        jPanel1.add(jLabel24);
        jPanel1.add(jLabel25);
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);

        Publicidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Publicidade"));
        Publicidade.setName("JPub");
        Publicidade.setPreferredSize(new java.awt.Dimension(0, 0));
        Publicidade.setLayout(new java.awt.BorderLayout());

        jMenu1.setText("Arquivo");
        jMenu1.add(jSeparator3);

        jMenuItem1.setIcon(Configurator.Icon("icones/br_cash_rune_of_exp_i00.jpg"));
        jMenuItem1.setText("Rates");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator2);

        jMenuItem4.setIcon(Configurator.Icon("icones/color_name_i00.jpg"));
        jMenuItem4.setText("Cores");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Update");
        jMenu2.add(jSeparator5);

        jMenuItem2.setIcon(Configurator.Icon("icones/refresh.jpg"));
        jMenuItem2.setText("Verificar Atualização");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ajuda");
        jMenu3.add(jSeparator7);

        jMenuItem3.setIcon(Configurator.Icon("icones/accessory_skeleton_cap_i00.jpg"));
        jMenuItem3.setText("Sobre");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator1);

        jMenuItem6.setIcon(Configurator.Icon("icones/br_cash_wrapped_accessary_i00.jpg"));
        jMenuItem6.setText("Remover Publicidade");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);
        jMenu3.add(jSeparator8);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusca))
                    .addComponent(Publicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusca)
                    .addComponent(textBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Publicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        AtualizaTabela();
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if ( evt.getClickCount() == 2 ) {
            int modelRow = tabela.convertRowIndexToModel(tabela.getSelectedRow());
            final Object tipo=tabela.getModel().getValueAt(modelRow, 2);
            final Object nome=tabela.getModel().getValueAt(modelRow, 1);
            final Object id=tabela.getModel().getValueAt(modelRow, 0);
            if(!Configurator.getWindow("DROPLISTNPC-"+(nome instanceof JLabel?((JLabel)nome).getText():nome)+"-"+id)){
                OpenWindow(tipo,nome,id);
            }
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DropRates dr=new DropRates(this,true);
        dr.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void textBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscaKeyReleased
        if(evt.getKeyCode()==10){
            AtualizaTabela();         
        }
    }//GEN-LAST:event_textBuscaKeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AutoUpdater j = new updater.AutoUpdater(this,"http://sindmaracaju.com.br/apps/droplist/","L2jDropList.jar");         
        
        j.addDeny("droplist.lock.db");
        j.addDeny("thumbs.db");
        j.addDeny("config.ini");
        
        j.UpdateGui();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(null, "fabiobareta@hotmail.com");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        DropCores dc=new DropCores(this, true);
        dc.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        getContentPane().remove(Publicidade);
        pack();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void OpenWindow(final Object tipo, final Object nome, final Object id){
        if(Integer.parseInt(id+"")>0){
            final Progress p = new Progress(this,true);
            final DropListNpc dln = new DropListNpc();
            p.setText("Buscando "+(nome instanceof JLabel?((JLabel)nome).getText():nome), "Aguarde...");
            SwingWorker worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground(){           
                    if(tabela.getSelectedRow()>=0){
                            dln.SetData(id,nome,tipo);                            
                            dln.Busca();
                    }
                    return null;
                }
                @Override
                protected void done() {
                    p.dispose();
                    dln.setVisible(true);                        
                    dln.toFront();
                };            
            };
            worker.execute();   
            p.setVisible(true);             
        }
    }
    
    public void AtualizaTabela(){
        String busca=textBusca.getText();
        final Progress p = new Progress(this,true);
        p.setText("Procurando "+busca, "Aguarde, verificando NPC`s e items...");
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground(){           
                try {            
                    BuscaNpc(textBusca.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(DropList.class.getName()).log(Level.SEVERE, null, ex);
                } 
                return null;
            }
            
            @Override
            protected void done() {
                p.dispose();               
            };            
        };
        
        worker.execute();   
        p.setVisible(true);        
    }
    
    private void BuscaNpc(String txt) throws SQLException{
        ResultSet rs;
        boolean f=false;
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        conn.conectar();
        String sql="SELECT * FROM npc WHERE name like '%"+txt+"%' ORDER BY name";
        rs=conn.Execute(sql);      
        while (rs.next()){
            f=true;
            if(!rs.getString("type").equalsIgnoreCase("L2Npc")){
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name")+" ("+rs.getString("level")+")", "NPC"});
            }
        }
        rs.close();
        if(!f){
            model.addRow(new Object[]{0,"Nenhum NPC encontrado",""});                    
        }
        
        f=false;
        sql="SELECT id,nome,icon FROM items WHERE nome like '%"+txt+"%' ORDER BY nome";
        rs=conn.Execute(sql);       
        
        while (rs.next()){            
            f=true;        
            model.addRow(new Object[]{
                rs.getInt("id"),
                new JLabel(rs.getString("nome"),Configurator.Icon(rs.getString("icon")),JLabel.LEFT),
                "ITEM"});
        }
        rs.close();
        
        if(!f){
            model.addRow(new Object[]{0,"Nenhum Item encontrado",""});                    
        }
        conn.desconectar();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Publicidade;
    private javax.swing.JButton btnBusca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField textBusca;
    // End of variables declaration//GEN-END:variables
}