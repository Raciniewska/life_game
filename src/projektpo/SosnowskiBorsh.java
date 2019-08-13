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
public class SosnowskiBorsh extends Plant {

    public SosnowskiBorsh(int x, int y, World world1) {
        super(x, y, world1);
        id = 5;
        strenth = 10;
    }

    @Override
    public int action(int dir) {
        if (locationX + 1 <= world.size - 1) {
            if (world.GetField(locationY, locationX + 1) != null && world.GetField(locationY, locationX + 1).getInniciative() != 0) {
                world.GetField(locationY, locationX + 1).commentDeathbyPlant();
                world.deleteOrganism(locationY, locationX + 1);
            }
        }
        if (locationX - 1 >= 0) {
            if (world.GetField(locationY, locationX - 1) != null && world.GetField(locationY, locationX - 1).getInniciative() != 0) {
                world.GetField(locationY, locationX - 1).commentDeathbyPlant();
                world.deleteOrganism(locationY, locationX - 1);
            }
        }
        if (locationY + 1 <= world.size - 1) {
            if (world.GetField(locationY + 1, locationX) != null && world.GetField(locationY + 1, locationX).getInniciative() != 0) {
                world.GetField(locationY + 1, locationX).commentDeathbyPlant();
                world.deleteOrganism(locationY + 1, locationX);
            }
        }
        if (locationY - 1 >= 0) {
            if (world.GetField(locationY - 1, locationX) != null && world.GetField(locationY - 1, locationX).getInniciative() != 0) {
                world.GetField(locationY - 1, locationX).commentDeathbyPlant();
                world.deleteOrganism(locationY - 1, locationX);
            }
        }
        return super.action(dir);
    }

    @Override
    public void draw(JPanel p) {
        p.setBackground(Color.BLACK);
    }

    @Override
    public void create(int Y, int X) {
        SosnowskiBorsh plant = new SosnowskiBorsh(X, Y, world);
        world.setField(Y, X, plant);
        world.addOrganism(plant);
    }
}
