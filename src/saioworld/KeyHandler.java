package saioworld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPress, downPress, leftPress, rightPress;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 38){
            upPress = true;
        }
        if(code == 39){
            rightPress = true;
        }
        if(code == 40){
            downPress = true;
        }
        if(code == 37){
            leftPress = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 38){
            upPress = false;
        }
        if(code == 39){
            rightPress = false;
        }
        if(code == 40){
            downPress = false;
        }
        if(code == 37){
            leftPress = false;
        }
    }
}
