package Dodge;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/** 
 * The main menu panel for the game.
 * @author Owen Jow
 */
public class MainMenuPanel extends MenuPanel {
    private boolean isTitleScr;
    private static final Image TITLE_SCR = Images.get("titleScreen");
    
    /** 
     * Constructs a minigame menu panel. Initializes images.
     */
    public MainMenuPanel() {
        // Initialize images
        menuImages = new Image[] { Images.get("mmPlay"), Images.get("mmControls"), 
                Images.get("mmCredits"), Images.get("mmClose") };
        this.kl = new KeyListener();
        isTitleScr = true;
    }
    
    /**
     * Paints the menu's background image.
     * @param g the graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage((isTitleScr)? TITLE_SCR : menuImages[imgIndex], 0, 0, null);
    }
    
    /**
     * The KeyListener for the minigame menu.
     * Controls registered: LEFT, RIGHT, ENTER, and BACKSPACE.
     */
    public class KeyListener extends KeyAdapter {
        /**
         * Handles the menu's response to keys being pressed.
         * @param KeyEvent evt the extraordinary event that is a key being pressed
         */
        public void keyPressed(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            
            if (isTitleScr) { 
                isTitleScr = false; // transition out of the title screen
            } else if (keyCode == GameState.pInfo.leftKey || keyCode == GameState.pInfo.rightKey) {
                imgIndex = (imgIndex == 0) ? 1 : 0;
            } else if (keyCode == GameState.pInfo.downKey) {
                if (imgIndex > 0 && imgIndex < 3) { 
                    imgIndex++; // highlight the option below the current option
                } else if (imgIndex == 3) {
                    imgIndex = 1; // wrap around to the top
                }
            } else if (keyCode == GameState.pInfo.upKey) {
                if (imgIndex > 1) { 
                    imgIndex--; // highlight the option above the current option
                } else if (imgIndex == 1) {
                    imgIndex = 3; // wrap around to the bottom
                }
            } else if (keyCode == GameState.pInfo.pauselectKey || keyCode == KeyEvent.VK_ENTER) {
                deactivate();
                switch (imgIndex) {
                    case 0: 
                        GameState.layout.show(GameState.contentPanel, "dodgePreG");
                        GameState.dodgePreGPanel.activate();
                        break;
                    case 1:
                        GameState.layout.show(GameState.contentPanel, "controlMenu");
                        GameState.controlMPanel.activate();
                        break;
                    case 2:
                        GameState.layout.show(GameState.contentPanel, "credits");
                        GameState.creditsPanel.activate();
                        break;
                    default:
                        System.exit(0); // quit the program and close the window
                }
            }
            
            repaint();
        }
    }
}
