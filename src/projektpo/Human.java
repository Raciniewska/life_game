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
public class Human extends Animal {

    private int round;
    private boolean eliksir;

    public Human(int x, int y, int time, World world1) {
        super(x, y, time, world1);
        this.inniciative = 4;
        this.strenth = 5;
        this.id = 0;
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.getHSBColor(0.1f, 0.2f, 0.9f));
    }

    @Override
    public int action(int dir) {
        int x = 0;
        int y = 0;
        if (round > 0) {
            round--;
            strenth--;
            commentEliksir(strenth);
            if (round == 0) {
                round = -7;
            }
        }
        if (round < 0) {
            round++;
            if (round == 0) {
                eliksir = false;
            }
        }
        if (dir == UP) {
            y--;
        }
        if (dir == DOWN) {
            y++;
        }
        if (dir == LEFT) {
            x--;
        }
        if (dir == RIGHT) {
            x++;
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

    public void turnOnSkill() {
        if (eliksir == false) {
            round = 6;
            eliksir = true;
            strenth += 6;
        }
    }

    @Override
    public void create(int X, int Y) {
    }

    public void commentEliksir(int x) {
        world.addComment("czlowiek ma sile " + x);
    }
}
