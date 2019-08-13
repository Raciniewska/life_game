/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Basia
 */
public class Listener implements KeyListener {

    private Window window;

    public Listener(Window window1) {
        window = window1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            window.move(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            window.move(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            window.move(3);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            window.move(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
