package Telas;

/**
 * Cria uma barra de progresso para exibir o carregamento de componentes.
 * @author Bareta
 */
public class Progress extends javax.swing.JDialog{

    public Progress(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        barra.setIndeterminate(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    /**
     * Progress é do tipo indeterminado?
     * @param opt boolean
     */
    public void isIndetermined(boolean opt){
        barra.setIndeterminate(opt);
    }
    
    /**
     * Valor da barra
     * @param v int
     */
    public void setValue(int v){
        barra.setValue(v);
    }
    
    /**
     * Poe um titulo na janela e altera o texto da barra de rolagem
     * @param title String
     * @param text String
     */
    public void setText(String title,String text){
        this.setTitle(title);
        barra.setString(text);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barra = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("ProgressBar");
        setUndecorated(true);

        barra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        barra.setForeground(new java.awt.Color(0, 0, 0));
        barra.setOpaque(true);
        barra.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barra, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barra;
    // End of variables declaration//GEN-END:variables
}