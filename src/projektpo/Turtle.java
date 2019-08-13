/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import java.util.Random;
import javax.swing.*;
import java.awt.Color;

/**
 *
 * @author Basia
 */
public class Turtle extends Animal {

    public Turtle(int x, int y, int time, World world1) {
        super(x, y, time, world1);
        inniciative = 1;
        this.strenth = 2;
        this.id = 4;
    }

    @Override
    public int action(int dir) {
        int possibility = 0;
        Random randomGenerator = new Random();
        possibility = randomGenerator.nextInt(100);
        if (possibility >= 75) {
            return (super.action(dir));
        } else {
            return 0;//nic nie usuniete
        }
    }

    @Override
    public int dItDefended(int strenth1, Organism id1) {
        if (strenth1 < 5) {
            return -1;
        } else {
            return super.dItDefended(strenth1, id1);
        }
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.getHSBColor(0.40f, 1.0f, 0.6f));
    }

    @Override
    public void create(int X, int Y) {
        Turtle animal = new Turtle(X, Y, world.getTime(), world);
        world.setField(Y, X, animal);
        world.addOrganism(animal);
        world.sortOrganisms();
        world.increaseTime();
    }
}
