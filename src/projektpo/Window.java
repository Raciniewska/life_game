/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author Basia
 */
public class Window implements MouseListener {

    private String organisms[] = {"Wolf", "Sheep", "Fox", "Turtle", "Antylope", "Grass", "Dandelion", "Guarana", "DeadlyNightshade", "SosnowskiBorsh"};
    private World world;
    private JFrame f;
    private JButton b, save, load, skill, add;
    private JLabel label;
    private JTextArea textArea;
    private JScrollPane scrollableTextArea;
    private JPanel[][] table;
    private Listener listen;
    private JComboBox cb;
    private int Y;
    private int X;

    public Window(World world1) {
        listen = new Listener(this);
        world = world1;
        f = new JFrame("Projekt Po");
        f.addKeyListener(listen);
        b = new JButton("New round");
        b.addKeyListener(listen);
        add = new JButton("add");
        add.addKeyListener(listen);
        save = new JButton("save");
        save.addKeyListener(listen);
        skill = new JButton("Human skill");
        skill.addKeyListener(listen);
        load = new JButton("load game");
        load.addKeyListener(listen);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700, 800);
        cb = new JComboBox(organisms);
        cb.setBounds(406, 425, 130, 20);
        b.setBounds(406, 0, 100, 30);
        save.setBounds(406, 40, 100, 30);
        load.setBounds(406, 80, 100, 30);
        skill.setBounds(406, 120, 100, 30);
        add.setBounds(406, 160, 100, 30);
        textArea = new JTextArea(20, 40);
        label = new JLabel();
        label.addKeyListener(listen);
        label.setSize(400, 100);
        label.setBounds(506, 160, 200, 30);
        scrollableTextArea = new JScrollPane(textArea);
        table = new JPanel[world.size][];
        for (int i = 0; i < world.size; i++) {
            table[i] = new JPanel[world.size];
            for (int k = 0; k < world.size; k++) {
                table[i][k] = new JPanel();
                table[i][k].setBounds(k * 20, i * 20, 20, 20);
                table[i][k].setBackground(Color.white);
                f.add(table[i][k]);
            }
        }
        for (int i = 0; i < world.getOrganismSize(); i++) {
            Y = world.getOrganismY(i);
            X = world.getOrganismX(i);
            world.drawWorld(i, table[Y][X]);
        }
        scrollableTextArea.setBounds(0, 425, 400, 250);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        f.getContentPane().add(scrollableTextArea);
        f.add(b);
        f.add(save);
        f.add(load);
        f.add(skill);
        f.add(cb);
        f.add(add);
        f.add(label);
        f.addMouseListener(this);
        addMouseListener(this);
    }

    void show() {
        f.setLayout(null);
        f.setVisible(true);
        b.addActionListener(e -> action1(e));
        save.addActionListener(e -> save(e));
        load.addActionListener(e -> load(e));
        skill.addActionListener(e -> skill(e));
        add.addActionListener(e -> add(e));
    }

    public void action1(ActionEvent e) {
        String data = "";
        label.setText(data);
        for (int i = 0; i < world.size; i++) {
            for (int k = 0; k < world.size; k++) {
                table[i][k].setBackground(Color.white);
            }
        }
        world.doRound(-1);
        for (int i = 0; i < world.getOrganismSize(); i++) {
            Y = world.getOrganismY(i);
            X = world.getOrganismX(i);
            world.drawWorld(i, table[Y][X]);
        }
        textArea.setText(null);
        for (int i = 0; i < world.getCommentsSize(); i++) {
            textArea.append(world.getComment(i) + "\n");
        }
        world.commentClear();

    }

    public void save(ActionEvent e) {
        world.save();
    }

    public void add(ActionEvent e) {
        String data = "";
        label.setText(data);
        if (world.GetField(Y, X) == null) {
            switch (cb.getSelectedIndex()) {
                case 0: {
                    world.createNotRandom(Wolf.class, Y, X);
                    break;
                }
                case 1: {
                    world.createNotRandom(Sheep.class, Y, X);
                    break;
                }
                case 2: {
                    world.createNotRandom(Fox.class, Y, X);
                    break;
                }
                case 3: {
                    world.createNotRandom(Turtle.class, Y, X);
                    break;
                }
                case 4: {
                    world.createNotRandom(Antylope.class, Y, X);
                    break;
                }
                case 5: {
                    world.createPlantNotRandom(Grass.class, Y, X);
                    break;
                }
                case 6: {
                    world.createPlantNotRandom(Dandelion.class, Y, X);
                    break;
                }
                case 7: {
                    world.createPlantNotRandom(Guarana.class, Y, X);
                    break;
                }
                case 8: {
                    world.createPlantNotRandom(DeadlyNightshade.class, Y, X);
                    break;
                }
                case 9: {
                    world.createPlantNotRandom(SosnowskiBorsh.class, Y, X);
                    break;
                }
            }
            world.GetField(Y, X).draw(table[Y][X]);
        }
    }

    public void load(ActionEvent e) {
        world.load();
        for (int i = 0; i < world.size; i++) {
            for (int k = 0; k < world.size; k++) {
                table[i][k].setBackground(Color.white);
            }
        }
        for (int i = 0; i < world.getOrganismSize(); i++) {
            Y = world.getOrganismY(i);
            X = world.getOrganismX(i);
            world.drawWorld(i, table[Y][X]);
        }
    }

    public void skill(ActionEvent e) {
        world.turnOnSkill();
    }

    public void move(int dir) {
        String data = "";
        label.setText(data);
        for (int i = 0; i < world.size; i++) {
            for (int k = 0; k < world.size; k++) {
                table[i][k].setBackground(Color.white);
            }
        }
        world.doRound(dir);
        for (int i = 0; i < world.getOrganismSize(); i++) {
            Y = world.getOrganismY(i);
            X = world.getOrganismX(i);
            world.drawWorld(i, table[Y][X]);
        }
        textArea.setText(null);
        for (int i = 0; i < world.getCommentsSize(); i++) {
            textArea.append(world.getComment(i) + "\n");
        }
        world.commentClear();

    }

    public void mousePressed(MouseEvent e) {
        if (e.getY() <= 440 && e.getX() <= 400) {
            table[e.getY() / 20 - 2][e.getX() / 20].setBackground(Color.PINK);
            Y = e.getY() / 20 - 2;
            X = e.getX() / 20;

        }
        String data = "Press to add "
                + cb.getItemAt(cb.getSelectedIndex());
        label.setText(data);

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
