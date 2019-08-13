/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import java.util.Random;

/**
 *
 * @author Basia
 */
public abstract class Plant extends Organism {

    private char czyRozm;

    public char getCzyRozm() {
        return czyRozm;
    }

    public void setCzyRozm(char i) {
        czyRozm = i;
    }

    public Plant(int x, int y, World world1) {
        czyRozm = 'N';
        inniciative = 0;
        age = 0;
        locationX = x;
        locationY = y;
        world = world1;
    }

    @Override
    public int collision(int y, int x) {
        return 0;
    }

    @Override
    public int action(int dir) {
        int possibility;
        Random randomGenerator = new Random();
        possibility = randomGenerator.nextInt(100);
        if (czyRozm == 'T') {
            if (possibility < 5) {
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
            czyRozm = 'T';
        }
        return 0;
    }

    public void commentRecreation() {
        world.addComment("Rozmnozenie " + plants[id - 1]);
    }

    public void commentDeath(int x) {
        world.addComment(
                plants[id - 1]
                + " zostala zjedzona przez "
                + animals[x]);
    }

    @Override
    public int dItDefended(int strenth1, Organism id1) {
        if (strenth1 >= strenth) {
            commentDeath(id1.GetId());
            return 0;
        } else {
            return 1;
        }
    }
}
