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
public class Grass extends Plant {

    public Grass(int x, int y, World world1) {
        super(x, y, world1);
        id = 1;
        strenth = 0;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.GREEN);
    }

    @Override
    public void create(int Y, int X) {
        Grass plant = new Grass(X, Y, world);
        world.setField(Y, X, plant);
        world.addOrganism(plant);
    }
}
