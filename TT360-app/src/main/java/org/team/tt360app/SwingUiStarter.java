package org.team.tt360app;

import Ui.MainFrame;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class SwingUiStarter {

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(){
        SwingUtilities.invokeLater(() -> launchUi());
    }

    public void launchUi(){
        MainFrame mainFrame = new MainFrame();
    }
}
