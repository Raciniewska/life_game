/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import javax.swing.*;
import java.awt.Color;

/**
 *
 * @author Basia
 */
public class Wolf extends Animal {

    public Wolf(int x, int y, int time, World world1) {
        super(x, y, time, world1);
        inniciative = 5;
        this.strenth = 9;
        this.id = 1;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.getHSBColor(0.10f, 1.0f, 0.6f));

    }

    @Override
    public void create(int X, int Y) {
        Wolf animal = new Wolf(X, Y, world.getTime(), world);
        world.setField(Y, X, animal);
        world.addOrganism(animal);
        world.sortOrganisms();
        world.increaseTime();
    }
}
