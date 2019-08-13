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
public class Sheep extends Animal {

    public Sheep(int x, int y, int time, World world1) {
        super(x, y, time, world1);
        this.inniciative = 4;
        this.strenth = 4;
        this.id = 2;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.gray);
    }

    @Override
    public void create(int X, int Y) {
        Sheep animal = new Sheep(X, Y, world.getTime(), world);
        world.setField(Y, X, animal);
        world.addOrganism(animal);
        world.sortOrganisms();
        world.increaseTime();
    }
}
