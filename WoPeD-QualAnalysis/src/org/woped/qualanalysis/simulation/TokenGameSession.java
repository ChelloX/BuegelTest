package org.woped.qualanalysis.simulation;

import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultListModel;

import org.woped.core.controller.IEditor;
import org.woped.core.model.PetriNetModelProcessor;
import org.woped.core.model.petrinet.AbstractPetriNetElementModel;
import org.woped.core.model.petrinet.SimulationModel;
import org.woped.core.model.petrinet.SubProcessModel;
import org.woped.core.model.petrinet.TransitionModel;

/**
 * The TokenGameSession class manages all interaction with the
 * token game toolbar and implements some of the logic to navigate the
 * token game. It is important to note that there is one TokenGameSession 
 * instance per token game, while there is one TokenGameController
 * per petrinet, where petrinet can be a top-level net or a subprocess. 
 * If more than one token game is running, two TokenGameSession instances exist.
 * 
 * @author weirdsoul
 *
 */
public class TokenGameSession implements Runnable {

    // constants
    // Playback Properties
    private ReferenceProvider desktop = null;
    private TokenGameController tgController = null;
    private PetriNetModelProcessor PetriNet = null;
    // Linked Lists
    private LinkedList<TransitionModel> followingActivatedTransitions = null;
    private LinkedList<TokenGameController> ProcessEditors = null;

    // Occurring Transitions
    private TransitionModel TransitionToOccur = null;
    private TransitionModel BackwardTransitionToOccur = null;
    private TransitionModel helpTransition = null;

    // SimulationModels
    private SimulationModel SaveableSimulation = null;

    // DefaultListModels
    private DefaultListModel ahxHistoryContent = null;

    // Vectors
    private Vector<TransitionModel> HistoryVector = null;

    // Booleans
    private boolean autoPlayBack = false;
    private boolean backward = false;
    private boolean newHistory = false;
    private boolean expertViewOnStage = false;

    // Threading control for autoplay mode
    private boolean endofautoplay = false;
    private Thread  autoPlayThread = null;
    
    // Integers
    private int HistoryIndex = 0;
    private int occurtimes = 3;
    private int delaytime = 1;

    public TokenGameSession(TokenGameController tgController, PetriNetModelProcessor PetriNet) {
        this.PetriNet = PetriNet;
        this.tgController = tgController;

        ahxHistoryContent = new DefaultListModel();

        addControlElements();
    }

    /**
     * The ExpertView and SlimView Objects will either been created or shown, depending on the case if they already exist or not
     */
    public void addControlElements() {
    	desktop = new ReferenceProvider();

        // Add InternalFrameListener to the EditorFrame to get informed about changes.
        // Need to manually put the last selected editor to foreground/focus. Cause if not
        // the InternalFrameListener may be bound to the ReachablilityGraph-Frame or another JInternalFrame
        if (desktop.getMediatorReference().getUi().getEditorFocus() instanceof IEditor) {
            desktop.getMediatorReference().getUi()
                    .selectEditor(desktop.getMediatorReference().getUi().getEditorFocus());
        }
    }

    // Get the ExpertView to the front
    public void getExpertViewOnStage() {

        setExpertViewStatus(true);
    }

    /*
     * Start playback. Executed when token game is enabled
     */
        
    public void startPlayback() {
        // Active TokenGame, disable DrawMode, checkNet and activate transition
        if (getTokenGameController().isVisualTokenGame()) {
            getTokenGameController().tokenGameCheckNet();
            if (playbackRunning()) {
                startHistoryPlayback();
            } else {
                clearHistoryData();
            }
        }
        
    }
    
    /*
     * ********************************************************************************************
     * Occurence and Depending Actions ********************************************************************************************
     */

    /**
     * This method let the active transition occur (currently only for sequences, as soon as two transitions are active, the method cannot occur so far)
     * @param BackWard  If true, specifies that we should occur the transition
     * 					"backwards", that is sending tokens from post- to pre-set
     * @param stepsInto If true, specifies that we should step into a sub process.
     * 					Effective only when not playing from history and not
     *   				playing backwards 
     */
    public synchronized void occurTransition(boolean BackWard, boolean stepInto) {    	
        /*
         * Backward is done with the
         */
        if (BackWard) {
            previousItem();
			if (BackwardTransitionToOccur != null) {
				TransitionToOccur = BackwardTransitionToOccur;
				tgController.occurTransitionbyTokenGameBarVC(TransitionToOccur,
						BackWard, false);
			}
        } else {

            // AFAIK needed to make automatic backward stepping
            BackwardTransitionToOccur = TransitionToOccur;
            if (playbackRunning()) {
                if ((HistoryIndex < HistoryVector.size()) && (HistoryIndex >= 0)) {
                    TransitionToOccur = HistoryVector.get(HistoryIndex++);

                    /*
                     * If History steps into a SubProcess, do so as well
                     */
                    if ((!TransitionToOccur.getId().contains("t")) && (!TransitionToOccur.getId().contains("a"))) {
                        if (HistoryIndex < HistoryVector.size()) {
                            helpTransition = HistoryVector.get(HistoryIndex);
                            if (helpTransition.getId().contains(TransitionToOccur.getId())
                                    && (helpTransition.getId().contains("a") || helpTransition.getId().contains("t"))) {
                            	// Override and specified decision of whether to step into 
                            	// a sub process if we find a specific behavior in our
                            	// records
                            	stepInto = true;
                            }
                        }
                    }
                    /*
                     * If History is in a Subprocess and it is finished, step out automatically
                     */
                    if (HistoryIndex < HistoryVector.size()) {
                        helpTransition = HistoryVector.get(HistoryIndex);
                        if (TransitionToOccur.getId().contains("t") || TransitionToOccur.getId().contains("a")) {
                            if (TransitionToOccur.getId().length() >= (helpTransition.getId().length() + 3)) {
                                changeTokenGameReference(null, true);
                            }
                        } else {
                            if (TransitionToOccur.getId().length() >= (helpTransition.getId().length() + 5)) {
                                changeTokenGameReference(null, true);
                            }
                        }
                    }

                    // Secure the net, so that no playback further than the last Simulation point can be done
                } else {
                    HistoryIndex = HistoryVector.size();
                }
            }
            // If end of net is not reached yet or there is still something to occur
            // If AutoChoice is Selected, occur without choice
            if (TransitionToOccur == null) {
                return;
            }
            if (followingActivatedTransitions.size() > 0) {
            	tgController.occurTransitionbyTokenGameBarVC(TransitionToOccur, 
            			BackWard, stepInto);
            }
        }
    }

    /**
     * This method let the multiple transition occur (now 3 times) (only for sequences, as two transitions are active, the method will stop)
     * 
     */
    public void occurTransitionMulti(boolean BackWard) {
        int i = 0;
        if (followingActivatedTransitions == null) {
            return;
        }
        if (BackWard) {
            while (i != occurtimes) {
                if (BackwardTransitionToOccur != null) {
                    occurTransition(BackWard, false);
                }
                i++;
            }
        } else {
            while (i != occurtimes) {
                if ((followingActivatedTransitions.size() < 2) && (!playbackRunning())) {
                    occurTransition(BackWard, false);
                } else
                    if (playbackRunning()) {
                        occurTransition(BackWard, false);
                    }
                i++;
            }
        }
    }

    /**
     * will fill the "BackwardTransitionToOccur" with Data
     */
    public void previousItem() {
        if (HistoryVector != null) {
            if (!playbackRunning()) {
                if (HistoryVector.size() > 0) {
                    BackwardTransitionToOccur = HistoryVector.remove(HistoryVector.size() - 1);
                } else {
                    BackwardTransitionToOccur = null;
                }
            }
            // If playback running
            else {
                if (HistoryIndex > 0) {
                    HistoryIndex--;
                    BackwardTransitionToOccur = HistoryVector.get(HistoryIndex);
                    return;
                } else // if(HistoryIndex < 0)
                {
                    BackwardTransitionToOccur = null;
                    return;
                }
            }
        }
    }

    /**
     * Will automatically run the TokenGame
     * 
     * @param BackWard
     */
    public void autoOccurAllTransitions(boolean BackWard) {
    	// Make sure only one thread is running at a time
    	if (autoPlayThread == null) {
    		backward = BackWard;
    		autoPlayThread = new Thread(this);
    		autoPlayThread.start();
    	}
    }
    
    public boolean getAutoPlayBackPlaying() {
    	return (autoPlayThread != null);
    }

    /**
     * This method generates a random index and choose one transition if their are more then one
     */
    public synchronized void proceedTransitionChoiceAuto() {
        if (!playbackRunning()) {
            int index = (int) Math.round(Math.random() * (followingActivatedTransitions.size() - 1));
            TransitionToOccur = followingActivatedTransitions.get(index);
            tgController.occurTransitionbyTokenGameBarVC(TransitionToOccur, false, false);
        } else {
            occurTransition(false, false);
        }
    }

    /*
     * ChoiceBox Handling section
     */

    /**
     * Adds a choice-item to followingActivatedTransitions
     */
    public void addFollowingItem(TransitionModel transition) {
        if (followingActivatedTransitions == null) {
            followingActivatedTransitions = new LinkedList<TransitionModel>();
        }
        followingActivatedTransitions.addLast(transition);
    }

    /**
     * Fills multi-choice box with content from followingActivatedTransitions
     */
    public void fillChoiceBox() {
        // prevent from NullPointerException
        if (followingActivatedTransitions == null) {
            return;
        }
        if (!playbackRunning()) {
            if (followingActivatedTransitions.size() == 1) {
                TransitionToOccur = followingActivatedTransitions.get(0);
            }
            if (followingActivatedTransitions.size() > 1) {
                for (int i = 0; i < followingActivatedTransitions.size(); i++) {
                    helpTransition = followingActivatedTransitions.get(i);
                }
            }
        }
    }

    /*
     * History Handling Section
     */

    /**
     * Starts the HistoryPlayback
     */
    public void startHistoryPlayback() {
        HistoryIndex = 0;
        TransitionToOccur = HistoryVector.get(HistoryIndex);
    }

    /**
     * Adds one more Item to the History List, to track the walked way
     */
    public void addHistoryItem(TransitionModel transition) {
        if (HistoryVector == null) {
            HistoryVector = new Vector<TransitionModel>(1);
            ahxHistoryContent.clear();
        }
        if (!playbackRunning()) {
            HistoryVector.add(transition);
        }
    }
    
    public int getNumHistoryItems() {
    	return (HistoryVector!=null)?HistoryVector.size():0;
    }

    /**
     * If History-tracking ("Recording") has been chosen by the user, Every occurred transition is added to this List
     * 
     * @param transition
     */
    public void addHistoryListItem(TransitionModel transition) {
        ahxHistoryContent.addElement(transition.getNameModel());
    }

    /**
     * Will create a Simulation-Object that can be added to the Simulations-Vector
     */
    @SuppressWarnings("unchecked")
    public void createSaveableHistory() {
        if (HistoryVector != null) {
            SaveableSimulation = new SimulationModel(PetriNet
                    .getNewElementId(AbstractPetriNetElementModel.SIMULATION_TYPE), "Default",
                    (Vector<TransitionModel>) HistoryVector.clone(), PetriNet.getLogicalFingerprint(), new Date());
            newHistory = true;
        }
    }

    /**
     * Will clear the History-Vector, the HistoryContent and will set the newHistory-variable to "true"
     */
    public void clearHistoryData() {
        ahxHistoryContent.clear();
        HistoryVector = null; // Set reference to null, so that a new history-Vector will be created!
        newHistory = false;
    }

    /*
     * Threading Methods
     */
    /**
     * Automatic TokenGame This Method occur automatic all transition if autoplayback is true. Choice transition will be occured by random choice
     * 
     * ToDo: 1) refreshNet after occurence - done 2) random choice only if no probabilities are set
     */
    public void run() {

        auto();
    }

    /**
     * Threading
     */
    public void auto() {
        if (followingActivatedTransitions != null) {
            while (!isEndOfAutoPlay()) {
                try {
                    javax.swing.SwingUtilities.invokeLater(new TokenGameRunnableObject(this));
                    Thread.sleep(getDelaytime() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            setEndOfAutoPlay(false);
        }
    }

    public void moveForward() {
        if (followingActivatedTransitions.size() == 0) {
            setEndOfAutoPlay(true);
        } else {
            if (followingActivatedTransitions.size() >= 2) {
                proceedTransitionChoiceAuto();
            } else {
                occurTransition(false, false);

            }
        }
    }

    public void moveBackward() {
        if (BackwardTransitionToOccur == null) {
            setEndOfAutoPlay(true);
        } else {
            occurTransition(true, false);
        }
    }

    /*
     * Setters and Getters
     */
    public LinkedList<TransitionModel> getFollowingActivatedTransitions() {
        return followingActivatedTransitions;
    }

    public SimulationModel getHistoryData() {
        return SaveableSimulation;
    }

    public DefaultListModel getHistoryContent() {
        return ahxHistoryContent;
    }

    public void setToOldHistory() {
        newHistory = false;
    }

    public void setExpertViewStatus(boolean status) {
        this.expertViewOnStage = status;
    }

    public boolean getExpertViewStatus() {
        return expertViewOnStage;
    }

    /**
     * Switches the AutoPlayback on or off
     * 
     * @param onoff
     */
    public void setAutoPlayback(boolean onoff) {
        autoPlayBack = onoff;
        if (!autoPlayBack)
            fillChoiceBox();
        tgController.checkNet();
    }

    /**
     * Sets the EndOfAutoPlay flag and wait for the auto-play thread to exit if 
     * parameter is set to true
     * 
     * @param end True if auto-play should end, false otherwise
     */
    public void setEndOfAutoPlay(boolean end) {
        endofautoplay = end;
        if ((end == true) && (autoPlayThread != null) && autoPlayThread.isAlive()) {
        	try {        	
        		autoPlayThread.join();
        		autoPlayThread = null;
        		tgController.checkNet();
        	} 
        	catch (InterruptedException e) {
        	}
        }
    }

    /**
     * Determines how often fast fw / bw occure
     */
    public int getOccurTimes() {
        return occurtimes;
    }

    /**
     * Determines how often fast fw / bw occure
     */
    public void setOccurTimes(int oc) {
        occurtimes = oc;
    }

    /**
     * Determines the delay time for autoplayback
     */
    public int getDelaytime() {
        return delaytime;
    }

    /**
     * Set DelayTime for Autoplayback
     */
    public void setDelaytime(int dt) {
        delaytime = dt;
    }

    /*
     * Special Getters: Reference Providers IndexNumbers and so on
     */

    /**
     * 
     * @return Reference to TokenGameController
     */
    public TokenGameController getTokenGameController() {
        return tgController;
    }

    /**
     * 
     * @return Reference to the PetriNet
     */
    public PetriNetModelProcessor getPetriNet() {
        return PetriNet;
    }

    /**
     * returns the ID of the transition which has to occur next
     * 
     * @return
     */
    public int getSelectedChoiceID() {
        return -1;
    }

    /**
     * Determines if the AutoPlayback is switched on
     * 
     * @return
     */
    public boolean getAutoPlayBack() {
        return autoPlayBack;
    }

    /*
     * boolean checks ("is"-checks)
     */

    /**
     * Returns the value for the endofautoplay variable
     * 
     */
    public boolean isEndOfAutoPlay() {
        return endofautoplay;
    }

    /**
     * Returns the value for the backward vasriable
     * 
     * @return
     */
    public boolean isBackward() {
        return backward;
    }

    public boolean isNewHistory() {
        return newHistory;
    }

    /**
     * this method is to check whether a real playback is running, a walkthrough without recording or a record session. If it is a real playback, the
     * application will not give the user any choice-possibility but it will just follow the history in the history-box
     * 
     * @return true / false
     */
    public boolean playbackRunning() {
        if (ahxHistoryContent.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * MISC
     */

    /**
     * Cleans up the ChoiceBox and the ChoiceArray. Is called by the TokenGameController.transitionClicked method and therefore makes it possible to step
     * through the net with in-Editor-clicks or Remote-clicks
     */
    public void cleanupTransition() {
        if (followingActivatedTransitions != null) {
            followingActivatedTransitions.clear();
        }
    }

    /** 
     * Changes the current token game controller reference (to one managing another graph instance)
     * When returning from a sub-process, newReference will be ignored and a value from an internal
     * stack used instead (ProcessEditors)
     *  
     * @param newReference  Specifies the new reference to be used and added to the stack
     * @param up			Specifies whether we want to go up or down
     */
    public void changeTokenGameReference(TokenGameController newReference, boolean up) {
        if (up) {
            if ((ProcessEditors != null) && (ProcessEditors.size() > 0)) {
                // Remove all remaining tokens in the Editor
                tgController.tokenGameRestore();
                tgController.closeSubProcess();
                tgController = ProcessEditors.removeLast();
                tgController.tokenGameCheckNet();

            }
        } else {
            if (ProcessEditors == null) {
                ProcessEditors = new LinkedList<TokenGameController>();
            }
            ProcessEditors.add(tgController);
            tgController = newReference;
        }
        // Update Ribbon to show Token Game icons
        desktop.getMediatorReference().getUi()
        .selectEditor(desktop.getMediatorReference().getUi().getEditorFocus());
    }

    public void setPlayIcon(boolean record) {
    }

    // Stop the TokenGame if Editor is closed
    public void stopTG() {
        tgController.stop();
        // simulatorBar.setViewChoiceListInvisible();
        desktop.getUIReference().getEditorFocus().toggleTokenGame();
        desktop.getUIReference().getContentPane().repaint();
    }

    /**
     * Pre-select sub process as the next transition to occur, but only
     * if there is only one sub process, so the selection is deterministic
     */
	public void setSubProcessTransition() {
		int activatedSubProcessCount = 0;
		TransitionModel subProcess = null;
		for (TransitionModel t : followingActivatedTransitions) {
			if (t instanceof SubProcessModel) {
				++activatedSubProcessCount;
				subProcess = t;
			}
			if (activatedSubProcessCount == 1)
				this.TransitionToOccur = subProcess;
		}
	}
}
