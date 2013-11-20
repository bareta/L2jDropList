package Telas.classes;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class Item {
    
    private int id;
    private int preco;
    private String nome;
    private String icone;
    private int chance;
    private int rate;

    public Item(int id, int preco, String nome, String icone, int chance, int rate) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.icone = icone;
        this.chance = chance;
        this.rate = rate;
    }

    public Item(int id, int preco, String icone) {
        this.id = id;
        this.preco = preco;
        this.icone = icone;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
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

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
  
}
