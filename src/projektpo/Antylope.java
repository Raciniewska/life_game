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
public class Antylope extends Animal {

    public Antylope(int x, int y, int time, World world1) {
        super(x, y, time, world1);
        inniciative = 4;
        this.strenth = 4;
        this.id = 5;
    }

    @Override
    public int action(int dir) {
        int direction, x = 0, y = 0;
        Random randomGenerator = new Random();
        direction = randomGenerator.nextInt(4);
        if (direction == LEFT) {
            x = x - 2;
        } else if (direction == RIGHT) {
            x = x + 2;
        } else if (direction == DOWN) {
            y = y + 2;
        } else if (direction == UP) {
            y = y - 2;
        }
        if ((locationX + x) >= 0 && (locationX + x) <= world.size - 1 && (locationY + y) >= 0 && (locationY + y) <= world.size - 1) {

            if (world.GetField(locationY + y, locationX + x) == null) {
                locationX = locationX + x;
                locationY = locationY + y;
                world.moveAnimal(locationY - y, locationX - x, locationY, locationX);
                return 0; // 0- nic nie usuniete
            } else {//collision
                return (collision(y, x));
            }
        }
        return 0;
    }

    @Override
    public int dItDefended(int strenth1, Organism id1) {
        int possibility;
        Random randomGenerator = new Random();
        possibility = randomGenerator.nextInt(100);
        if (possibility > 50) {
            return (super.dItDefended(strenth1, id1));
        } else {
            int X = -1, Y = -1;
            if (locationX + 2 <= world.size - 1 && world.GetField(locationY, locationX + 2) == null) {
                X = locationX + 2;
                Y = locationY;
            } else if (locationX - 2 <= world.size - 1 && locationX - 2 >= 0 && world.GetField(locationY, locationX - 2) == null) {
                X = locationX - 2;
                Y = locationY;
            } else if (locationY - 2 >= 0 && world.GetField(locationY - 2, locationX) == null) {
                X = locationX;
                Y = locationY - 2;
            } else if (locationY + 2 <= world.size - 1 && world.GetField(locationY + 2, locationX) == null) {
                X = locationX;
                Y = locationY + 2;
            }
            if (X == -1 || Y == -1) {
                return (super.dItDefended(strenth1, this));
            } else {
                //tmp=world.getField(location.y+y,location.x+x);
                world.moveAnimal(locationY, locationX, Y, X);
                locationY = Y;
                locationX = X;
                //tmp->setX(X); tmp->setY(Y);
                //world.moveAnimal(location.y,location.x,location.y + y,location.x + x);
                //location.x = location.x + x;
                //location.y = location.y + y;
                commentAntylopeEscape();

            }
            return -2;//antylopa uciekla
        }
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.magenta);
    }

    @Override
    public void create(int X, int Y) {
        Antylope animal = new Antylope(X, Y, world.getTime(), world);
        world.setField(Y, X, animal);
        world.addOrganism(animal);
        world.sortOrganisms();
        world.increaseTime();
    }
}
