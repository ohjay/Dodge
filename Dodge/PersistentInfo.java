package Dodge;

import java.io.Serializable;
import java.awt.event.KeyEvent;

/** 
 * A collection of data that should persist through different play sessions.
 * In other words, it represents the data that will be saved.
 * This will also serve as a container for individual save file data.
 * @author Owen Jow
 */
public class PersistentInfo implements Serializable {
    private static final long serialVersionUID = 7042866136132959358L; // version identifier
    int dodgeHighScore; // the game's high score
    
    // Control options
    int leftKey, rightKey, upKey, downKey, powerupKey, pauselectKey;
    
    /**
     * Constructor for a persistent info object.
     * (This should only be called once, the first time the game is ever played).
     * Sets all of the controls to their default options.
     */
    public PersistentInfo() {
        leftKey = ControlMenuPanel.LEFT_DEFAULT;
        rightKey = ControlMenuPanel.RIGHT_DEFAULT;
        upKey = ControlMenuPanel.UP_DEFAULT;
        downKey = ControlMenuPanel.DOWN_DEFAULT;
        powerupKey = ControlMenuPanel.POWERUP_DEFAULT;
        pauselectKey = ControlMenuPanel.PAUSELECT_DEFAULT;
    }
}
