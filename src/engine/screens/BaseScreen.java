package engine.screens;

import java.awt.event.ActionEvent;

public abstract class BaseScreen extends Screen {
    public BaseScreen(String id) {
        super(id);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        this.handleMouseClick();
    }
}
