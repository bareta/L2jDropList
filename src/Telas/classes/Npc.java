package Telas.classes;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class Npc {
    private String nome;
    private int id;

    public Npc(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public Npc() {
        nome="";
        id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
