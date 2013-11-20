package Telas;

import database.conectaH2;
import database.model.Configurator;
import database.model.PublicidadeConfig;
import database.model.TableCellRenderer;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class DropListNpc extends javax.swing.JFrame {
    
    private int id;
    private conectaH2 conn;
    private String titulo;
    private Object tipo;
    private int droprate;
    private int spoilrate;
    private int adenarate;
    private int xprate;
    private int sprate;
    private int bossrate;   
    private boolean err;
    
    public DropListNpc(){
        droprate=Configurator.getValue("rated");
        spoilrate=Configurator.getValue("ratespoil");
        adenarate=Configurator.getValue("rateadena");
        xprate=Configurator.getValue("ratexp");
        sprate=Configurator.getValue("ratesp");       
        bossrate=Configurator.getValue("rateboss");
        err=false;
        initComponents();
        
        conn=new conectaH2();
        tabela.setRowHeight(32);
        TableCellRenderer cells=new TableCellRenderer(
                                        Configurator.getCor("Lista1"),
                                        Configurator.getCor("Lista2"),                                        
                                        Configurator.getCor("Spoil1"),
                                        Configurator.getCor("Spoil2"),
                                        Configurator.getCor("Selecao")
                                );
        tabela.setDefaultRenderer(String.class, cells);
        tabela.setDefaultRenderer(Integer.class, cells);
        tabela.setDefaultRenderer(Object.class, cells);
        PublicidadeConfig.addBanner(Publicidade);
    }

    public void SetData(Object id,Object name,Object tipo){
        if(name instanceof String){
            this.titulo=(String)name;
        }else{
            this.titulo=((JLabel)name).getText();
        }
        this.id=(Integer)id;
        this.titulo+="-"+id;
        setTitle(titulo);
        setName("DROPLISTNPC-"+titulo);
        this.tipo=tipo;        
    }   
    
    public void Busca(){
        try {
            conn.conectar();
            if(((String)tipo).equalsIgnoreCase("NPC")){
                buscaNpc(this.id);
            }else{
                showItem(this.id);
                buscaItem(this.id);                
            }
            conn.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(DropListNpc.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    private List<String> getIds(){
        List<String> l = new ArrayList<String>();
        for(int i=0;i<tabela.getModel().getRowCount();i++){
            l.add(tabela.getModel().getValueAt(i, 0)+"");
        }
        return l;
    }    
    
    private String showNpc(int id) throws SQLException{
        String npcstat,mtipo = null;
        ResultSet rs;
        conn.conectar();
        String sql="select * from npc where id="+id;
        rs=conn.Execute(sql);
        if(rs.next()){
                npc.setText(rs.getString("name")+" - Lv "+rs.getInt("level"));
                npcstat="Patack: {1} - Pdef: {2} - Matack: {3} - Mdef: {4} - HP: {5} - Speed: {6} - Range: {7} - XP: {8} - SP: {9}";
                npcstat=npcstat.replace("{1}", String.format("%.2f", rs.getDouble("PATK")));
                npcstat=npcstat.replace("{2}", String.format("%.2f", rs.getDouble("PDEF")));
                npcstat=npcstat.replace("{3}", String.format("%.2f", rs.getDouble("MATK")));
                npcstat=npcstat.replace("{4}", String.format("%.2f", rs.getDouble("MDEF")));
                npcstat=npcstat.replace("{5}", String.format("%.2f", rs.getDouble("HP")));
                npcstat=npcstat.replace("{6}", String.format("%.2f", rs.getDouble("RUNSPD")));
                npcstat=npcstat.replace("{7}", String.format("%.2f", rs.getDouble("ATTACKRANGE")));
                npcstat=npcstat.replace("{8}", String.format("%.2f", (rs.getDouble("exp")*xprate))); 
                npcstat=npcstat.replace("{9}", String.format("%.2f", (rs.getDouble("sp")*sprate))); 
                npcinfo.setText(npcstat);
                mtipo=rs.getString("type");
        }
        rs.close();
        conn.desconectar();
        try{
            setIconImage(
                Toolkit.getDefaultToolkit().getImage(Configurator.iconName("etc_question_mark_i00"))
            );
        }catch(Exception e){
            System.err.println("Falha ao buscar icone");
        }
        return mtipo;
    }
    
    private void showItem(int id) throws SQLException{
        String npcstat;
        ResultSet rs;
        
        String sql="select * from items where id="+id;
        rs=conn.Execute(sql);
        if(rs.next()){
                npc.setText(rs.getString("nome"));
                npc.setIcon(Configurator.Icon(rs.getString("icon")));                
                npcstat="Preço de venda no NPC: "+rs.getInt("preco")+" adenas.";              
                npcinfo.setText(npcstat);
            try{
                setIconImage(
                    Toolkit.getDefaultToolkit().getImage(
                        rs.getString("icon")
                    )
                );
            }catch(Exception e){
                System.err.println("Falha ao buscar icone");
            }
        }
        rs.close();
         
    }
    
    private void buscaNpc(int id) throws SQLException{
        ResultSet rs;
        Boolean v=false;
        int cat,min,max;
        double chance;
        String cate,tiponpc;
        Object[] drops;
        
        tiponpc=showNpc(id);
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);                
        
        System.out.println("Buscando ID:"+id);
        
        conn.conectar();
        String sql="SELECT droplist.mobId,droplist.itemId, droplist.max, droplist.min, items.nome, items.icon , droplist.category, droplist.chance"+
                    " FROM droplist LEFT JOIN items ON droplist.itemid = items.id"+
                    " WHERE (((droplist.mobId)="+id+")) order by droplist.category desc, droplist.chance desc;";
        
        rs=conn.Execute(sql);

        while (rs.next()){
            v=true;
            
            cat=rs.getInt("category");
            if(cat<0){
                cate="SPOIL";
            }else{
                cate="DROP";
            }
                        
            min=rs.getInt("min");
            max=rs.getInt("max");
            chance=rs.getDouble("chance");
                        
            if(tiponpc.equalsIgnoreCase("L2GrandBoss") || tiponpc.equalsIgnoreCase("L2RaidBoss") || tiponpc.equalsIgnoreCase("L2FlyRaidBoss")){
                cat=99;                
            }            
            drops=calcDrops(min, max, chance, cat,rs.getInt("droplist.itemId"));
            
            model.addRow(new Object[]{
                rs.getInt("droplist.itemId"),
                new JLabel(
                    rs.getString("nome"),
                    Configurator.Icon(rs.getString("icon")),
                    JLabel.LEFT
                ),
                drops[0],
                drops[1],
                cate,
                drops[2]
            });
        } 
        rs.close();
        conn.desconectar();
        
        if(!v){
            err=true;
        }
    }
    
    private void buscaItem(int id) throws SQLException{
        ResultSet rs;
        Boolean v=false;
        int cat,min,max;
        double chance;
        String cate,sql,tiponpc;
        Object[] drops;
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);                
        
        System.out.println("Buscando ID:"+id);
        
        sql="SELECT droplist.mobid, npc.name, npc.level,npc.type, droplist.min,droplist.max,droplist.category, droplist.chance, droplist.itemid"+
            " FROM npc RIGHT JOIN droplist ON npc.id = droplist.mobid"+
            " WHERE (((droplist.itemid)="+id+")) order by droplist.category desc, droplist.chance desc;";        
        rs=conn.Execute(sql);
        
        while (rs.next()){
            
            v=true;
            
            cat=rs.getInt("category");
            if(cat<0){
                cate="SPOIL";
            }else{
                cate="DROP";
            }            
            
            min=rs.getInt("min");
            max=rs.getInt("max");
            chance=rs.getDouble("chance");
            
            tiponpc=rs.getString("type");
            if(tiponpc.equalsIgnoreCase("L2GrandBoss") || tiponpc.equalsIgnoreCase("L2RaidBoss") || tiponpc.equalsIgnoreCase("L2FlyRaidBoss")){
                cat=99;                
            }
            
            drops=calcDrops(min, max, chance, cat, id); 
            
            model.addRow(new Object[]{
                rs.getInt("mobId"),
                rs.getString("name")+" ("+rs.getString("level")+")",
                drops[0],
                drops[1],
                cate,
                drops[2]
            });
        } 
        rs.close();

        if(!v){
            err=true;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        npc = new javax.swing.JLabel();
        npcinfo = new javax.swing.JLabel();
        butMapa = new javax.swing.JButton();
        Publicidade = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(titulo);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setName("DropListNpc");

        tabela.setAutoCreateRowSorter(true);
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item / NPC", "Min", "Max", "Tipo", "Chance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        tabela.getColumnModel().getColumn(2).setMinWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setMaxWidth(100);
        tabela.getColumnModel().getColumn(3).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setMaxWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(5).setMinWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setMaxWidth(100);

        npc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        npc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        butMapa.setIcon(Configurator.Icon("icones/etc_map_ivory_i00.jpg"));
        butMapa.setText("Mapa");
        butMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMapaActionPerformed(evt);
            }
        });

        Publicidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Publicidade"));
        Publicidade.setName("JPub");
        Publicidade.setPreferredSize(new java.awt.Dimension(0, 0));
        Publicidade.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(npcinfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(npc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23)
                        .addComponent(butMapa))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Publicidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(npc)
                    .addComponent(butMapa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(npcinfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Publicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void OpenWindow(final Object nome, final Object idnpc){
        if(tabela.getSelectedRow()>=0){
            final Object ntipo;
  
            if(((String)tipo).equalsIgnoreCase("NPC")){
                ntipo="ITEM";
            }else{
                ntipo="NPC";
            }
            if(Integer.parseInt(idnpc+"")>0){
                final Progress p = new Progress(this,true);
                final DropListNpc dln = new DropListNpc();
                p.setText("Buscando "+(nome instanceof JLabel?((JLabel)nome).getText():nome), "Aguarde...");
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground(){           
                        if(tabela.getSelectedRow()>=0){
                            dln.SetData(idnpc,nome,ntipo);                            
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
    }
    
    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if ( evt.getClickCount() == 2 ) {
            int modelRow = tabela.convertRowIndexToModel(tabela.getSelectedRow());
            final Object nome=tabela.getModel().getValueAt(modelRow, 1);                
            final Object idnpc=tabela.getModel().getValueAt(modelRow, 0);
            if(!Configurator.getWindow("DROPLISTNPC-"+(nome instanceof JLabel?((JLabel)nome).getText():nome)+"-"+idnpc)){
                OpenWindow(nome,idnpc);
            }            
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private List<int[]> getCoords(int id) throws SQLException{
        List<int[]> list = new ArrayList<int[]>();
        ResultSet rs;
        String sql;
        conn.conectar();
        
        if(((String)tipo).equalsIgnoreCase("NPC")){
            sql="select locx,locy,npc_templateid from spawnlist where npc_templateid="+id;
        }else{
            sql="select locx,locy,npc_templateid from spawnlist where npc_templateid in ("+getIds().toString().replace("[", "").replace("]", "") +")";
        }
        
        rs=conn.Execute(sql);      
        while (rs.next()){
            list.add(new int[]{rs.getInt("locx"),rs.getInt("locy")});
        }
        rs.close();
        conn.desconectar();
        return list;
    }
    
    private void OpenMap(String nome){
        final Map mapa = new Map(nome);
        final Progress p = new Progress(this,true);
        p.setText("Criando Mapa", "Buscando coordenadas...");
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground(){
                try {
                    mapa.setCoords(getCoords(id));
                } catch (SQLException ex) {
                    Logger.getLogger(DropListNpc.class.getName()).log(Level.SEVERE, null, ex);
                }
                mapa.SetMap();                
                return null;
            }            
            @Override
            protected void done() {
                p.dispose();
                mapa.setVisible(true);                
                mapa.CenterMap();
                mapa.toFront();
            };
        };        
        worker.execute();   
        p.setVisible(true);        
    }
    
    private void butMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butMapaActionPerformed
        String jan=npc.getText();
        if(!Configurator.getWindow("MAPA-"+jan)){
            OpenMap(jan);
        }         
    }//GEN-LAST:event_butMapaActionPerformed

    private Object[] calcDrops(int min,int max,Double Itemchance,int cat,int itemid){        
        int modifier=1;
        String chancef = null;
        double chance,chancet;
        boolean p=false;
        int full=1000000;
        
        if(itemid==57){
            chance=Itemchance*adenarate;
        }else if(cat<0){
            chance=Itemchance*spoilrate;
        }else if(cat==99){
            chance=Itemchance*bossrate;            
        }else{
            chance=Itemchance*droprate;
        }
        chancet=chance/10000;
        if(chance>full){
            modifier=(int)chance/full;
            chance%=full;
            p=true;
        }

        min*=modifier;
        max*=modifier;
        chance/=10000;
        if(p){            
            chancef="("+String.format("%.0f", chancet)+"%) "+String.format("%.1f", chance)+"%";
        }else{            
            chancef="("+String.format("%.2f", chancet)+"%) 1/"+((Double)(100/chance)).intValue();
        }
        Object[] o = new Object[]{min,max,chancef};                
        return o;
    }

    @Override
    public void setVisible(boolean b) {
        if(err){
            JOptionPane.showMessageDialog(this, "Nada encontrado", "L2 DROPLIST", JOptionPane.ERROR_MESSAGE);
            this.dispose();           
        }else{
            super.setVisible(b);
        }
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Publicidade;
    private javax.swing.JButton butMapa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel npc;
    private javax.swing.JLabel npcinfo;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}