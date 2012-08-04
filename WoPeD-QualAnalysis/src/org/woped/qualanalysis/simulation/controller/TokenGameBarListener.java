package org.woped.qualanalysis.simulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TokenGameBarListener implements ActionListener, ChangeListener, ListSelectionListener,
        CaretListener {

    // Constants
    // ======================
    // Playback configuration
    public final static int CHOOSE_STEPWISE = 1;
    public final static int CHOOSE_PLAYBACK = 2;
    public final static int CHANGE_PLAYMODE = 14; // This is used for the SlimView, only

    // Navigation Button
    public final static int CLICK_FAST_BACKWARD = 4;
    public final static int CLICK_BACKWARD = 5;
    public final static int CLICK_STOP = 6;
    public final static int CLICK_PLAY = 7;
    public final static int CLICK_PAUSE = 8;
    public final static int CLICK_FORWARD = 9;
    public final static int CLICK_FAST_FORWARD = 10;

    // Process stepping
    public final static int CLICK_STEP_UP = 11;
    public final static int CLICK_STEP_DOWN = 12;

    // Auto Choices
    public final static int CHOOSE_AUTO_CHOICE = 13;

    // History management
    public final static int CHOOSE_DELETE_CURRENT = 16;

    // Get additional RemoteControls on stage
    public final static int CHOOSE_VIEW = 36;

    // Use analysis functions from simulatorBar
    public final static int STOP_TG = 37;
    public final static int CALL_WOFLAN = 38;
    public final static int ANALYSIS = 39;
    public final static int QUANTCAP = 40;
    public final static int QUANTSIM = 41;
    public final static int REACHABILITY_GRAPH = 42;

    // AutoChoice List
    public final static int CHOOSE_REMOTECONTROLL = 43;

    // Variables
    private int ID = 0;
    private TokenGameBarController RemoteControl = null;

    // Needed for RemoteControlElements
    public TokenGameBarListener(int ElementID, TokenGameBarController RC) {
        ID = ElementID;
        RemoteControl = RC;
    }

    private void actionRouter() {
        switch (ID) {
        case CHOOSE_STEPWISE:
            RemoteControl.setAutoPlayback(false);
            break;
        case CHOOSE_PLAYBACK:
            RemoteControl.setAutoPlayback(true);
            break;
        case CLICK_FAST_BACKWARD:
            if (RemoteControl.tokengameRunning()) {
                RemoteControl.occurTransitionMulti(true);
            }
            break;
        case CLICK_BACKWARD:
            /*
             * Will make a step back
             */
            if (RemoteControl.tokengameRunning()) {
                if (RemoteControl.getAutoPlayBack()) {
                    RemoteControl.setEndOfAutoPlay(false);
                    RemoteControl.autoOccurAllTransitions(true);
                } else {
                    RemoteControl.occurTransition(true);
                }
            }
            break;
        case CLICK_STOP:
            stopTokenGame();
            RemoteControl.disablePlayButtons();
            break;
        case CLICK_PLAY:
        	// Start the playback fun
        	RemoteControl.startPlayback();
            break;
        case CLICK_PAUSE:
            RemoteControl.setEndOfAutoPlay(true);
            break;
        case CLICK_FORWARD:
            if (RemoteControl.tokengameRunning()) {
                if (RemoteControl.getAutoPlayBack()) {
                    RemoteControl.setEndOfAutoPlay(false);
                    RemoteControl.autoOccurAllTransitions(false);
                } else {
                    RemoteControl.occurTransition(false);
                }
            }
            break;
        case CLICK_FAST_FORWARD:
            if (RemoteControl.tokengameRunning()) {
                RemoteControl.occurTransitionMulti(false);
            }
            break;
        case CLICK_STEP_UP:
            if (RemoteControl.tokengameRunning()) {
                RemoteControl.changeTokenGameReference(null, true);
            }
            break;
        case CLICK_STEP_DOWN:
            if (RemoteControl.tokengameRunning()) {
                RemoteControl.getTokenGameController().setStepIntoSubProcess(true);
                RemoteControl.occurTransition(false);
            }
            break;
        case CHOOSE_AUTO_CHOICE:
            if (RemoteControl.tokengameRunning()) {
                RemoteControl.switchAutoChoice();
            }
            break;
        case CHANGE_PLAYMODE:
            /*
             * Switch Playmode to stepwise
             */
            if (RemoteControl.getAutoPlayBack()) {
                RemoteControl.setAutoPlayback(false);
                break;
            }
            /*
             * Switch playmode to autoplay
             */
            else {
                RemoteControl.setAutoPlayback(true);
            }
            break;
        case CHOOSE_DELETE_CURRENT:
            deleteCurrentHistory();
            break;
        case STOP_TG:
            RemoteControl.stopTG();
            break;
        case CHOOSE_VIEW:
            break;
        default:
            break;
        }

    }

    /*
     * Reset TokenGame to Startposition and Enable PlayButton
     */
    private void stopTokenGame() {
        if (RemoteControl.tokengameRunning()) {
            stopAction();
            RemoteControl.enablePlayButton();
            RemoteControl.enableStepDown(null);
            // RemoteControl.enableRecordButton();
        }
    }

    private void deleteCurrentHistory() {
        RemoteControl.clearHistoryData();
    }

    private void stopAction() {
        RemoteControl.setEndOfAutoPlay(true);
        while (RemoteControl.getTokenGameController().getThisEditor().isSubprocessEditor()) {
            RemoteControl.changeTokenGameReference(null, true);
        }
        RemoteControl.getTokenGameController().tokenGameRestore();
        RemoteControl.clearChoiceBox();
    }

    /*
     * Action Events
     */
    public void actionPerformed(ActionEvent e) {
        // Calls the method for the centralized Action-Handling
        actionRouter();
    }

    public void stateChanged(ChangeEvent arg0) {
        actionRouter();

    }

    public void valueChanged(ListSelectionEvent e) {
        actionRouter();
    }

    public void caretUpdate(CaretEvent e) {
        actionRouter();
    }
}