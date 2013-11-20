package Telas.classes;

import java.util.List;

/**
 *
 * @author Fábio Ricardo Bareta
 */
public class DropList {
    private Npc npc;
    private List<Item> drops;

    public DropList(Npc npc, List<Item> drops) {
        this.npc = npc;
        this.drops = drops;
    }

    public List<Item> getDrops() {
        return drops;
    }

    public void setDrops(List<Item> drops) {
        this.drops = drops;
    }

    public Npc getNpc() {
        return npc;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }    
}
