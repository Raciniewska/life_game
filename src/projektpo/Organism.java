/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import javax.swing.*;

/**
 *
 * @author Basia
 */
public abstract class Organism {

    protected String[] animals = {"Czlowiek", "Wilk", "Owca", "Lis", "Zolw", "Antylopa"};
    protected String[] plants = {"Trawa", "Mlecz", "Guarana", "Wilcze jagody", "Barszcz Sosnowskiego"};
    protected int age;
    protected int strenth;
    protected int inniciative;
    protected int id;
    protected int locationX;
    protected int locationY;
    final int LEFT = 1, UP = 3, DOWN = 0, RIGHT = 2;
    protected World world;

    abstract void create(int X, int Y);

    abstract void draw(JPanel p);

    abstract int dItDefended(int strenth1, Organism id1);

    abstract int collision(int y, int x);

    abstract int action(int dir);

    public int GetY() {
        return locationY;
    }

    public int GetX() {
        return locationX;
    }

    public int GetStrenth() {
        return strenth;
    }

    public int getInniciative() {
        return inniciative;
    }

    public int GetId() {
        return id;
    }

    public int GetAge() {
        return age;
    }

    public void commentDeathbyPlant() {
        world.addComment(animals[id] + " umarl, znalazl sie niedaleko barszczu sosnowskiego");
    }

    public void turnOnSkill() {
    }

}
