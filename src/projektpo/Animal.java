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
public abstract class Animal extends Organism {

    protected boolean isOldEnough;

    public Animal(int x, int y, int time, World world1) {
        locationX = x;
        locationY = y;
        age = time;
        world = world1;
        isOldEnough = false;
    }

    public int reproduce(int x, int y) {
        int X = -1, Y = -1;
        if (locationX + x + 1 <= world.size - 1 && locationX + x + 1 >= 0 && world.GetField(locationY + y, locationX + x + 1) == null) {
            X = locationX + x + 1;
            Y = locationY + y;
        } else if (locationX + x - 1 >= 0 && locationX + x - 1 <= world.size - 1 && world.GetField(locationY + y, locationX + x - 1) == null) {
            X = locationX + x - 1;
            Y = locationY + y;
        } else if (locationY + y - 1 >= 0 && locationY + y - 1 <= world.size - 1 && world.GetField(locationY + y - 1, locationX + x) == null) {
            X = locationX + x;
            Y = locationY + y - 1;
        } else if (locationY + y + 1 <= world.size - 1 && locationY + y + 1 >= 0 && world.GetField(locationY + y + 1, locationX + x) == null) {
            X = locationX + x;
            Y = locationY + y + 1;
        }
        if (X < 0 || Y < 0) {
            return 0;
        } else {
            create(X, Y);
            commentRecreation();
            return 4;//dodany element za
        }
    }

    @Override
    public int collision(int y, int x) {
        Organism tmp = world.GetField(locationY + y, locationX + x);
        int czyOdparlAtak = 0;
        if (tmp.GetId() == id && tmp.getInniciative() != 0) {
            if (isOldEnough == true) {
                return (reproduce(x, y));
            } else {
                isOldEnough = true;
                return 0;//nic sie nie stalo
            }
        }
        czyOdparlAtak = tmp.dItDefended(strenth, this);
        if (czyOdparlAtak == -3) {
            commentDeathByEating();
            return 1;

        }
        if (czyOdparlAtak == -1) {
            commentTurtleDefense();
            return 0;
        }
        if (czyOdparlAtak == -2) {
            if (world.GetField(locationY + y, locationX + x) == null) {
                locationX = locationX + x;
                locationY = locationY + y;
                world.moveAnimal(locationY - y, locationX - x, locationY, locationX);
                return 0; // 0- nic nie usuniete
            }
            return 0;
        }
        if (czyOdparlAtak == 1) {
            world.deleteOrganism(locationY, locationX);
            world.setField(locationY, locationX, null);
            if (tmp.getInniciative() != 0) {
                commentFightLost(tmp.GetId());
            }
            return 1;//usuniety bierzacy element
        } else {
            if (tmp.getInniciative() != 0) {
                commentAttackWin(tmp.GetId());
            }
            world.deleteOrganism(tmp.GetY(), tmp.GetX());
            world.moveAnimal(locationY, locationX, locationY + y, locationX + x);
            locationY = locationY + y;
            locationX = locationX + x;
            if (tmp.inniciative > inniciative) {
                return 2;//usuniety element nizej;
            }
            if (tmp.inniciative == inniciative) {
                if (tmp.age < age) {
                    return 2;//usuniety element nizej
                } else {
                    return 3;//usunieto el wyzej
                }
            } else {
                return 3;//usuniety element wyzej
            }
        }
    }

    @Override
    public int action(int dir) {
        int direction, x = 0, y = 0;
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
        if (strenth1 >= strenth) {
            commentDeath();
            return 0;
        } else {
            return 1;
        }
    }

    public void commentAttackWin(int x) {
        world.addComment(animals[id] + " wygral walke, atakujac " + animals[x]);
    }

    public void commentFightLost(int x) {
        world.addComment(animals[id] + " umarl, atakujac " + animals[x]);
    }

    public void commentDeath() {
        world.addComment(animals[id] + " umarl, zostal zaatakowany");
    }

    public void commentRecreation() {
        world.addComment(animals[id] + " ulegl rozmnozeniu");
    }

    public void commentDeathByEating() {
        world.addComment(animals[id] + " umarl, zjadl trujaca rosline");
    }

    public void commentIncreaseStrenth() {
        world.addComment(animals[id] + " zwiekszyl sile o 3");
    }

    public void commentTurtleDefense() {
        world.addComment("Zolw obronil sie przed atakiem " + animals[id]);
    }

    public void commentAntylopeEscape() {
        world.addComment("Antylopa uciekla przed walka");
    }
}
