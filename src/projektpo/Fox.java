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
public class Fox extends Animal {

    public Fox(int x, int y, int time, World world1) {
        super(x, y, time, world1);
        inniciative = 7;
        this.strenth = 3;
        this.id = 3;
    }

    @Override
    public int action(int dir) {
        int direction, x = 0, y = 0, tried = 0;
        boolean success = false;
        while (!success) {
            x = 0;
            y = 0;
            Random randomGenerator = new Random();
            direction = randomGenerator.nextInt(4);
            if (direction == LEFT) {
                x--;
            } else if (direction == RIGHT) {
                x++;
            } else if (direction == DOWN) {
                y++;
            } else if (direction == UP) {
                y--;
            }
            if ((locationX + x) >= 0 && (locationX + x) <= world.size - 1 && (locationY + y) >= 0 && (locationY + y) <= world.size - 1) {
                //if (Animal::checkMeetingWithBorh(x, y) == 1) {
                //	return 1;
                //}
                if (world.GetField(locationY + y, locationX + x) == null || world.GetField(locationY + y, locationX + x).getInniciative() == 0) {
                    success = true;
                } else {
                    Organism tmp = world.GetField(locationY + y, locationX + x);
                    if ((tmp.GetStrenth() <= strenth) || (tmp.getInniciative() != 0 && tmp.GetId() == id)) {
                        success = true;
                    }
                }
            }
            tried++;
            if (tried == 30) {
                return 0;
            }
        }
        if ((locationX + x) >= 0 && (locationX + x) <= world.size - 1 && (locationY + y) >= 0 && (locationY + y) <= world.size - 1) {
            if (world.GetField(locationY + y, locationX + x) == null) {
                locationX = locationX + x;
                locationY = locationY + y;
                world.moveAnimal(locationY - y, locationX - x, locationY, locationX);
                return 0;
            } else {
                return (collision(y, x));
            }
        }
        return 0;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.ORANGE);
    }

    @Override
    public void create(int X, int Y) {
        Fox animal = new Fox(X, Y, world.getTime(), world);
        world.setField(Y, X, animal);
        world.addOrganism(animal);
        world.sortOrganisms();
        world.increaseTime();
    }
}
