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
    // Default control options
    static final int LEFT_DEFAULT = KeyEvent.VK_LEFT, RIGHT_DEFAULT = KeyEvent.VK_RIGHT,
            UP_DEFAULT = KeyEvent.VK_UP, DOWN_DEFAULT = KeyEvent.VK_DOWN, 
            POWERUP_DEFAULT = KeyEvent.VK_A, PAUSELECT_DEFAULT = KeyEvent.VK_SHIFT;
    
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
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(menuImages[(imgIndex > 5) ? 5 : imgIndex], 0, 0, null);
        
        // Now we have to display the actual keys that the controls are mapped to
        // We'll draw these keys as strings:
        g.setFont(new Font("Verdana", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.leftKey), X_COORD, LEFT_Y);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.rightKey), X_COORD, RIGHT_Y);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.upKey), X_COORD, UP_Y);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.downKey), X_COORD, DOWN_Y);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.powerupKey), X_COORD, POWERUP_Y);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.pauselectKey), X_COORD, PAUSE_Y);
        g.setColor(Color.WHITE);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.leftKey), X_COORD - 1, LEFT_Y - 1);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.rightKey), X_COORD - 1, RIGHT_Y - 1);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.upKey), X_COORD - 1, UP_Y - 1);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.downKey), X_COORD - 1, DOWN_Y - 1);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.powerupKey), X_COORD - 1, POWERUP_Y - 1);
        g2.drawString(KeyEvent.getKeyText(GameState.pInfo.pauselectKey), X_COORD - 1, PAUSE_Y - 1);
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
                deactivate();
                Dodge.savePersistentInfo(GameState.pInfo);
                GameState.layout.show(GameState.contentPanel, "mainMenu");
                GameState.mainMenuPanel.activate();
            } else if (controlSelected) {
                switch (imgIndex) {
                    case 0:
                        GameState.pInfo.leftKey = keyCode;
                        // Make sure it's not the same as any of the other keys
                        // ...and if it is, restore that other key to its default
                        if (keyCode == GameState.pInfo.rightKey) {
                            GameState.pInfo.rightKey = RIGHT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.upKey) {
                            GameState.pInfo.upKey = UP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.downKey) {
                            GameState.pInfo.downKey = DOWN_DEFAULT;
                        } else if (keyCode == GameState.pInfo.powerupKey) {
                            GameState.pInfo.powerupKey = POWERUP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.pauselectKey) {
                            GameState.pInfo.pauselectKey = PAUSELECT_DEFAULT;
                        }
                        
                        break;
                    case 1:
                        GameState.pInfo.rightKey = keyCode;
                        if (keyCode == GameState.pInfo.leftKey) {
                            GameState.pInfo.leftKey = LEFT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.upKey) {
                            GameState.pInfo.upKey = UP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.downKey) {
                            GameState.pInfo.downKey = DOWN_DEFAULT;
                        } else if (keyCode == GameState.pInfo.powerupKey) {
                            GameState.pInfo.powerupKey = POWERUP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.pauselectKey) {
                            GameState.pInfo.pauselectKey = PAUSELECT_DEFAULT;
                        }
                        
                        break;
                    case 2:
                        GameState.pInfo.upKey = keyCode;
                        if (keyCode == GameState.pInfo.leftKey) {
                            GameState.pInfo.leftKey = LEFT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.rightKey) {
                            GameState.pInfo.rightKey = RIGHT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.downKey) {
                            GameState.pInfo.downKey = DOWN_DEFAULT;
                        } else if (keyCode == GameState.pInfo.powerupKey) {
                            GameState.pInfo.powerupKey = POWERUP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.pauselectKey) {
                            GameState.pInfo.pauselectKey = PAUSELECT_DEFAULT;
                        }
                        
                        break;
                    case 3:
                        GameState.pInfo.downKey = keyCode;
                        if (keyCode == GameState.pInfo.leftKey) {
                            GameState.pInfo.leftKey = LEFT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.rightKey) {
                            GameState.pInfo.rightKey = RIGHT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.upKey) {
                            GameState.pInfo.upKey = UP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.powerupKey) {
                            GameState.pInfo.powerupKey = POWERUP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.pauselectKey) {
                            GameState.pInfo.pauselectKey = PAUSELECT_DEFAULT;
                        }
                        
                        break;
                    case 4:
                        GameState.pInfo.powerupKey = keyCode;
                        if (keyCode == GameState.pInfo.leftKey) {
                            GameState.pInfo.leftKey = LEFT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.rightKey) {
                            GameState.pInfo.rightKey = RIGHT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.upKey) {
                            GameState.pInfo.upKey = UP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.downKey) {
                            GameState.pInfo.downKey = DOWN_DEFAULT;
                        } else if (keyCode == GameState.pInfo.pauselectKey) {
                            GameState.pInfo.pauselectKey = PAUSELECT_DEFAULT;
                        }
                        
                        break;
                    case 5:
                        GameState.pInfo.pauselectKey = keyCode;
                        if (keyCode == GameState.pInfo.leftKey) {
                            GameState.pInfo.leftKey = LEFT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.rightKey) {
                            GameState.pInfo.rightKey = RIGHT_DEFAULT;
                        } else if (keyCode == GameState.pInfo.upKey) {
                            GameState.pInfo.upKey = UP_DEFAULT;
                        } else if (keyCode == GameState.pInfo.downKey) {
                            GameState.pInfo.downKey = DOWN_DEFAULT;
                        } else if (keyCode == GameState.pInfo.powerupKey) {
                            GameState.pInfo.powerupKey = POWERUP_DEFAULT;
                        }
                        
                        break;
                }
                
                controlSelected = false;
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
