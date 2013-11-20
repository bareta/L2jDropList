package database;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Gerenciador de conexões com o banco de dados embarcado H2
 * @author Fábio Bareta
 */
public class conectaH2{  
    private int errorCode;
    
    private Connection conexao;  
    
    public conectaH2(){
        conexao=null;
        errorCode=0;
    }
    
    /**
     * Retorna a conexao ativa
     * @return conexao aberta
     */
    public Connection getConnection(){
        return conexao;
    }
    
    /**
     * Faz a conexao com o banco e mantem o objeto conn aberto.
     * @return true
     */
    public boolean conectar(){  
        boolean result = true;  
        try {
            Class.forName("org.h2.Driver").newInstance();
            this.conexao = DriverManager.getConnection("jdbc:h2:database/droplist;LOG=0;UNDO_LOG=0;AUTO_SERVER=TRUE;TRACE_LEVEL_FILE=0","bareta","bareta");
        } catch (InstantiationException ex) {
            result = false;
            JOptionPane.showMessageDialog(null, "Erro ao instanciar banco local\n"+ex.getMessage());  
        } catch (IllegalAccessException ex) {
            result = false;
            JOptionPane.showMessageDialog(null, "Acesso Ilegal banco local\n"+ex.getMessage());  
        }catch(ClassNotFoundException ex){  
            result = false;  
            JOptionPane.showMessageDialog(null, "Driver nao encontrado banco local\n"+ex.getMessage());  
        }catch(SQLException ex){  
            result = false;  
            JOptionPane.showMessageDialog(null, "Nao conectou ao banco erro na fonte banco local\n"+ex.getMessage());  
            System.err.println(ex.getMessage());
        }
        
        if(!result){
            System.exit(0);
        }
        return result;             
    }
    
    /**
     * Fecha a conexao com o banco de dados
     */
    public void desconectar(){            
        try{  
            conexao.close();
            conexao=null;
        }catch(SQLException erroSQL){
            System.err.println(erroSQL.getMessage());
        }  
    }
    
    /**
     * Executa uma sql e retorna um resultset
     * @param sql 
     */
    public ResultSet Execute(String sql){
        ResultSet rs = null;
        Statement st;
        try{
            st = conexao.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException sqlex){  
            System.err.println(sqlex.getMessage());
        }  
        return rs;
    }
    

    /**
     * Executa uma sql
     * @param sql String
     */
    public void execute(String sql){ 
        Statement st;
        try{  
            st = conexao.createStatement();
            st.execute(sql);
        }catch(SQLException sqlex){              
            System.err.println(sqlex.getMessage());
        }catch(Exception sqlex){
            System.err.println(sqlex.getMessage());
        }  
    }
    
    /**
     * Executa uma sql e retorna uma chave
     * @param sql String
     * @param chave String[]
     * @return int - chave
     */
    public int execute(String sql,String[] chave){      
        int idColVar=0;
        Statement st;
        try{  
            st = conexao.createStatement();
            st.executeUpdate(sql, chave);
            ResultSet rs = st.getGeneratedKeys(); 
            if(rs.next()){
                idColVar=rs.getInt(1);
            }
            rs.close();
        }catch(SQLException sqlex){              
            System.err.println(sqlex.getMessage());
            setErrorCode(sqlex.getErrorCode());
            //1062 codigo de duplicacao
        }
        return idColVar;
    }
    
    /**
     * Retorna o codigo de erro
     * @return int
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Seta o codigo do erro
     * @param errorCode 
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }    
}  