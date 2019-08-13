/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Basia
 */
public class Dandelion extends Plant {

    public Dandelion(int x, int y, World world1) {
        super(x, y, world1);
        id = 2;
        strenth = 0;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.yellow);
    }

    @Override
    public void create(int Y, int X) {
        Dandelion plant = new Dandelion(X, Y, world);
        world.setField(Y, X, plant);
        world.addOrganism(plant);
    }

    @Override
    public int action(int dir) {
        for (int i = 0; i < 3; i++) {
            int possibility;
            Random randomGenerator = new Random();
            possibility = randomGenerator.nextInt(100);
            if (getCzyRozm() == 'T') {
                if (possibility <= 5) {
                    int X = -1, Y = -1;
                    if (locationX + 1 <= world.size - 1 && world.GetField(locationY, locationX + 1) == null) {
                        X = locationX + 1;
                        Y = locationY;
                    } else if (locationX - 1 >= 0 && world.GetField(locationY, locationX - 1) == null) {
                        X = locationX - 1;
                        Y = locationY;
                    } else if (locationY - 1 >= 0 && world.GetField(locationY - 1, locationX) == null) {
                        Y = locationY - 1;
                        X = locationX;
                    } else if (locationY + 1 <= world.size - 1 && world.GetField(locationY + 1, locationX) == null) {
                        Y = locationY + 1;
                        X = locationX;
                    }
                    if (X != -1 && Y != -1) {
                        create(Y, X);
                        commentRecreation();
                    }
                }
            } else {
                setCzyRozm('T');
            }
        }
        return 0;
    }
}
