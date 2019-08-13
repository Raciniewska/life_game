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
public class DeadlyNightshade extends Plant {

    public DeadlyNightshade(int x, int y, World world1) {
        super(x, y, world1);
        id = 4;
        strenth = 99;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.getHSBColor(0.80f, 1.0f, 0.6f));
    }

    @Override
    public int dItDefended(int strenth1, Organism id1) {
        commentDeath(id1.id);
        id1.world.deleteOrganism(id1.locationY, id1.locationX);
        id1.world.setField(id1.locationY, id1.locationX, null);

        return -3;//smierc zwierzecia
    }

    @Override
    public void create(int Y, int X) {
        DeadlyNightshade plant = new DeadlyNightshade(X, Y, world);
        world.setField(Y, X, plant);
        world.addOrganism(plant);
    }
}
