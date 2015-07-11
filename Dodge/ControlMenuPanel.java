package Dodge;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

/**
 * The panel for the controls menu, in which the user will be able to customize controls.
 * @author Owen Jow
 */
public class ControlMenuPanel extends MenuPanel {
    private static final int X_COORD = 376, LEFT_Y = 143, RIGHT_Y = 183, UP_Y = 221, 
            DOWN_Y = 257, POWERUP_Y = 295, PAUSE_Y = 335;
    private boolean controlSelected;
    private int[] keys;
    // The y-values for the different control keys, in the order they appear
    private static final int[] Y_VALUES = new int[] { LEFT_Y, RIGHT_Y, UP_Y, DOWN_Y, 
            POWERUP_Y, PAUSE_Y };
    
    /** 
     * Constructs a control menu panel. 
     * Initializes images and assigns a value to a key listener variable.
     */
    public ControlMenuPanel() {
        // Initialize images
        menuImages = new Image[] { Images.get("controlLeft"), Images.get("controlRight"),
                 Images.get("controlUp"), Images.get("controlDown"), Images.get("controlPowerup"),
                 Images.get("controlPause") };
        this.kl = new KeyListener();
        // Initialize the keys array with the current controls
        keys = new int[] { GameState.pInfo.leftKey, GameState.pInfo.rightKey,
                GameState.pInfo.upKey, GameState.pInfo.downKey, GameState.pInfo.powerupKey,
                GameState.pInfo.pauselectKey };
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(menuImages[(imgIndex > 5) ? 5 : imgIndex], 0, 0, null);
        
        // Now we have to display the actual keys that the controls are mapped to
        // We'll draw these keys as strings:
        g.setFont(new Font("Verdana", Font.BOLD, 15));
        
        // Draw the first layer of keys (we'll have two layers so it has that nice 3D effect)
        g.setColor(Color.BLACK);
        for (int i = 0; i < keys.length; i++) {
            if (controlSelected && i == imgIndex) {
                g2.drawString("...", X_COORD, Y_VALUES[i]);
            } else {
                g2.drawString(KeyEvent.getKeyText(keys[i]), X_COORD, Y_VALUES[i]);
            }
        }
        
        // Second layer of keys
        for (int i = 0; i < keys.length; i++) {
            g2.setColor((i == imgIndex) ? Color.WHITE : Color.GRAY);
            if (controlSelected && i == imgIndex) {
                g2.drawString("...", X_COORD, Y_VALUES[i]);
            } else {
                g2.drawString(KeyEvent.getKeyText(keys[i]), X_COORD - 1, Y_VALUES[i] - 1);
            }
        }
    }
    
    /**
     * Checks if the key specified by KEY_CODE is the same as any other control (or, to be precise,
     * any control that's not the one associated with KEY_INDEX). If so, we will select 
     * the offending duplicate (by altering the imgIndex variable) 
     * and thereby force the user to bind a new key for that control.
     * @param keyCode the key that we'll be checking for duplicates against
     * @param keyIndex the index of the control that we don't need to check against
     * @return true if the specified key has a duplicate
     */
    private boolean runUniquenessCheck(int keyCode, int keyIndex) {
        for (int i = 0; i < keys.length; i++) {
            if (i != keyIndex && keyCode == keys[i]) {
                imgIndex = i;
                return true;
            }
        }
        
        return false;
    }
    
    public class KeyListener extends KeyAdapter {
        /**
         * Handles the menu's response to keys being pressed.
         * @param KeyEvent evt the extraordinary event that is a key being pressed
         */
        public void keyPressed(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            if (keyCode == KeyEvent.VK_UNDEFINED) { return; } // we don't want no undefined keys
            
            // Check if the user pressed ESC and therefore wants to return to the main menu
            if (keyCode == KeyEvent.VK_ESCAPE) {
                // Don't let the user escape if the possibility of duplicates remains
                if (!controlSelected) {
                    deactivate();
                    Dodge.savePersistentInfo(GameState.pInfo);
                    GameState.layout.show(GameState.contentPanel, "mainMenu");
                    GameState.mainMenuPanel.activate();
                }
            } else if (controlSelected) {
                // We know a control is selected... but which one?
                switch (imgIndex) { // ^cue switch logic
                    case 0:
                        GameState.pInfo.leftKey = keyCode;
                        break;
                    case 1:
                        GameState.pInfo.rightKey = keyCode;
                        break;
                    case 2:
                        GameState.pInfo.upKey = keyCode;
                        break;
                    case 3:
                        GameState.pInfo.downKey = keyCode;
                        break;
                    case 4:
                        GameState.pInfo.powerupKey = keyCode;
                        break;
                    case 5:
                        GameState.pInfo.pauselectKey = keyCode;
                        break;
                }
                
                keys[imgIndex] = keyCode;
                if (!runUniquenessCheck(keyCode, imgIndex)) {
                    controlSelected = false; // no duplicates, so nothing is selected now
                }
            } else if (keyCode == GameState.pInfo.upKey) {
                if (imgIndex > 0) { 
                    imgIndex--;
                }
            } else if (keyCode == GameState.pInfo.downKey) {
                if (imgIndex < 5) { 
                    imgIndex++;
                }
            } else if (keyCode == GameState.pInfo.pauselectKey || keyCode == KeyEvent.VK_ENTER) {
                controlSelected = true;
            }
            
            repaint();
        }
    }
}
