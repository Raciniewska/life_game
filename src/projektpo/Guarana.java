/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Basia
 */
public class Guarana extends Plant {

    public Guarana(int x, int y, World world1) {
        super(x, y, world1);
        id = 3;
        strenth = 0;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.RED);
    }

    @Override
    public void create(int Y, int X) {
        Guarana plant = new Guarana(X, Y, world);
        world.setField(Y, X, plant);
        world.addOrganism(plant);
    }

    @Override
    public int dItDefended(int strenth1, Organism id1) {
        id1.strenth += 3;
        commentDeath(id1.id);
        return 0;
    }

    @Override
    public void commentDeath(int x) {
        world.addComment(
                plants[id - 1]
                + " zostala zjedzona przez "
                + animals[x] + " sila zwierzecia zwiekszona o 3");
    }
}
