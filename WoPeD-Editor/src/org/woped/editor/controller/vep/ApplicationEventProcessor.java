package org.woped.editor.controller.vep;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;

import org.woped.core.config.ConfigurationManager;
import org.woped.core.controller.AbstractEventProcessor;
import org.woped.core.controller.AbstractGraph;
import org.woped.core.controller.AbstractViewEvent;
import org.woped.core.controller.IEditor;
import org.woped.core.model.AbstractModelProcessor;
import org.woped.core.model.petrinet.OperatorTransitionModel;
import org.woped.core.model.petrinet.PetriNetModelElement;
import org.woped.core.utilities.LoggerManager;
import org.woped.core.utilities.Utils;
import org.woped.editor.Constants;
import org.woped.editor.controller.ApplicationMediator;
import org.woped.editor.controller.VisualController;
import org.woped.editor.controller.vc.ConfigVC;
import org.woped.editor.controller.vc.EditorVC;

public class ApplicationEventProcessor extends AbstractEventProcessor
{
    private ApplicationMediator mediator = null;

    public ApplicationEventProcessor(int vepID, ApplicationMediator mediator)
    {
        super(vepID, mediator);
        this.mediator = mediator;
    }

    public void processViewEvent(AbstractViewEvent event)
    {
        IEditor currentEditor;
        if (event.getSource() instanceof EditorVC)
        {
            currentEditor = (EditorVC) event.getSource();
        } else
        {
            currentEditor = getMediator().getUi().getEditorFocus();
        }
        switch (event.getOrder())
        {
        case AbstractViewEvent.NEW:
            currentEditor = getMediator().createEditor(AbstractModelProcessor.MODEL_PROCESSOR_PETRINET, true);
            break;
        case AbstractViewEvent.DRAWMODE_PLACE:
            setDrawMode(PetriNetModelElement.PLACE_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_TRANSITION:
            setDrawMode(PetriNetModelElement.TRANS_SIMPLE_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_ANDJOIN:
            setDrawMode(OperatorTransitionModel.AND_JOIN_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_ANDSPLIT:
            setDrawMode(OperatorTransitionModel.AND_SPLIT_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_ANDSPLITJOIN:
            setDrawMode(OperatorTransitionModel.AND_SPLITJOIN_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_XORJOIN:
            setDrawMode(OperatorTransitionModel.XOR_JOIN_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_XORSPLIT:
            setDrawMode(OperatorTransitionModel.XOR_SPLIT_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_XORSPLITJOIN:
            setDrawMode(OperatorTransitionModel.XOR_SPLITJOIN_TYPE, true);
            break;
        case AbstractViewEvent.DRAWMODE_SUB:
            setDrawMode(PetriNetModelElement.SUBP_TYPE, true);
            break;

        case AbstractViewEvent.CONFIG:
            ConfigVC config = ((ConfigVC) getMediator().getViewController(ConfigVC.ID_PREFIX));
            if (config == null)
            {
                config = (ConfigVC) getWopedMediator().createViewController(ApplicationMediator.VIEWCONTROLLER_CONFIG);
            }
            if (getMediator().getUi() != null)
            {
                config.setLocation(Utils.getCenterPoint(getMediator().getUi().getBounds(), config.getSize()));
            }
            config.setVisible(true);

            break;

        case AbstractViewEvent.EXIT:
            getMediator().getUi().quit();
            break;

        case AbstractViewEvent.UPDATE:
            for (Iterator iter = getMediator().getUi().getAllEditors().iterator(); iter.hasNext();)
            {
                currentEditor = ((EditorVC) iter.next());
                currentEditor.getGraph().setGridVisible(ConfigurationManager.getConfiguration().isShowGrid());
                for (Iterator iterator = currentEditor.getModelProcessor().getElementContainer().getArcMap().keySet().iterator(); iterator.hasNext();)
                {
                    currentEditor.getModelProcessor().getElementContainer().getArcById(iterator.next()).initAttributes();
                }

                currentEditor.updateNet();
                VisualController.getInstance().propertyChange(new PropertyChangeEvent(this, "Update", null, null));
            }
            break;

        case AbstractViewEvent.CASCADE:
            getMediator().getUi().cascadeFrames();
            break;
        case AbstractViewEvent.ARRANGE:
            getMediator().getUi().arrangeFrames();
            break;
        case AbstractViewEvent.PRINT:
            print(currentEditor);
            break;

        /*
         * SCREENSHOT m_controlledWindow.makeScreenshot(); }
         * OLDUserInterface.getInstance().getActiveEditor().removePoint(); }
         * else if (targetEvent == CASCADE_WINDOWS) {
         * OLDUserInterface.getInstance().cascadeFrames(); } else if
         * (targetEvent == ARRANGE_WINDOWS) {
         * OLDUserInterface.getInstance().arrangeFrames();
         * 
         * CASCADE super("Action.Frames.Cascade");
         * VisualController.getInstance().addElement(this,
         * VisualController.WITH_EDITOR, VisualController.IGNORE,
         * VisualController.IGNORE);
         * 
         * ARRANGE super("Action.Frames.Arrange");
         * VisualController.getInstance().addElement(this,
         * VisualController.WITH_EDITOR, VisualController.IGNORE,
         * VisualController.IGNORE);
         * 
         *  
         */
        default:
            break;
        }
    }

    /**
     * TODO: DOCUMENTATION (alexnagy)
     * 
     * @param editor
     */
    public void print(IEditor editor)
    {
        AbstractGraph graph = editor.getGraph();
        if (graph != null)
        {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintable(graph);
            if (printJob.printDialog())
            {
                try
                {
                    printJob.print();
                } catch (PrinterException e)
                {
                    LoggerManager.warn(Constants.EDITOR_LOGGER, "Could not Print");
                    LoggerManager.debug(Constants.EDITOR_LOGGER, "Exception" + e);
                }
            }
        }
    }

    private void setDrawMode(int type, boolean active)
    {
        EditorVC currentEditor;
        for (Iterator iter = getMediator().getUi().getAllEditors().iterator(); iter.hasNext();)
        {
            currentEditor = (EditorVC) iter.next();
            currentEditor.setDrawingMode(active);
            currentEditor.setCreateElementType(type);
        }
    }

    private void resetDrawMode()
    {
        setDrawMode(-1, false);
    }

    /**
     * @return Returns the mediator.
     */
    private ApplicationMediator getWopedMediator()
    {
        return mediator;
    }

}
