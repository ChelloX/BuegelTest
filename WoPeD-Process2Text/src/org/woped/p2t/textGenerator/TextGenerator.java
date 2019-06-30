package org.woped.p2t.textGenerator;

import de.hpi.bpt.process.Process;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.woped.p2t.contentDetermination.labelAnalysis.EnglishLabelDeriver;
import org.woped.p2t.contentDetermination.labelAnalysis.EnglishLabelHelper;
import org.woped.p2t.dataModel.dsynt.DSynTMainSentence;
import org.woped.p2t.dataModel.dsynt.DSynTSentence;
import org.woped.p2t.dataModel.pnmlReader.PNMLReader;
import org.woped.p2t.dataModel.pnmlReader.PetriNet.PetriNet;
import org.woped.p2t.dataModel.pnmlReader.PetriNetToProcessConverter;
import org.woped.p2t.dataModel.process.ProcessModel;
import de.hpi.bpt.graph.algo.rpst.RPST;
import de.hpi.bpt.process.ControlFlow;
import de.hpi.bpt.process.Node;
import org.woped.p2t.contentDetermination.preprocessing.FormatConverter;
import org.woped.p2t.contentDetermination.preprocessing.RigidStructurer;
import org.woped.p2t.sentencePlanning.DiscourseMarker;
import org.woped.p2t.sentencePlanning.ReferringExpressionGenerator;
import org.woped.p2t.sentencePlanning.SentenceAggregator;
import org.woped.p2t.sentenceRealization.SurfaceRealizer;
import org.woped.p2t.textPlanning.PlanningHelper;
import org.woped.p2t.textPlanning.TextPlanner;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class TextGenerator {
    private String contextPath = "";
    ArrayList<String> values= new ArrayList<String>();

    public TextGenerator(String contextPath) {
        this.contextPath = contextPath;
    }

    public TextGenerator() {
    }

    public String toText(String input) throws Exception {
        return toText(input, false);
    }

    public String toText(String input, boolean surfaceOnly) throws Exception {
        String imperativeRole = "";
        ByteArrayInputStream is = new ByteArrayInputStream( input.getBytes() );
        PNMLReader pnmlReader = new PNMLReader();
        PetriNet petriNet = pnmlReader.getPetriNetFromPNMLString(is);
        PetriNetToProcessConverter pnConverter = new PetriNetToProcessConverter();
        ProcessModel model = pnConverter.convertToProcess(petriNet);

        //check number splits/joins
        pnConverter.printConversion();

        //Show Activities
        System.out.println(model.getActivites());


        HashMap<Integer, String> transformedElemsRev = pnConverter.transformedElemsRev;

        EnglishLabelHelper lHelper = new EnglishLabelHelper(contextPath);
        EnglishLabelDeriver lDeriver = new EnglishLabelDeriver(lHelper);

        // Annotate model
        model.annotateModel(lDeriver, lHelper);

        // Convert to RPST
        FormatConverter formatConverter = new FormatConverter();
        Process p = formatConverter.transformToRPSTFormat(model);
        RPST<ControlFlow, Node> rpst = new RPST<>(p);

        // Check for Rigids
        boolean containsRigids = PlanningHelper.containsRigid(rpst.getRoot(), rpst);

        // Structure Rigid and convert back
        if (containsRigids) {
            p = formatConverter.transformToRigidFormat(model);
            RigidStructurer rigidStructurer = new RigidStructurer();
            p = rigidStructurer.structureProcess(p);
            model = formatConverter.transformFromRigidFormat(p);
            p = formatConverter.transformToRPSTFormat(model);
            rpst = new RPST<>(p);
        }

        // Convert to Text
        TextPlanner converter = new TextPlanner(rpst, model, lDeriver, lHelper, imperativeRole, false, false);
        converter.convertToText(rpst.getRoot(), 0);
        ArrayList<DSynTSentence> sentencePlan = converter.getSentencePlan();


        // Aggregation
        SentenceAggregator sentenceAggregator = new SentenceAggregator();
        sentencePlan = sentenceAggregator.performRoleAggregation(sentencePlan);

        // Referring Expression
        ReferringExpressionGenerator refExpGenerator = new ReferringExpressionGenerator(lHelper);
        sentencePlan = refExpGenerator.insertReferringExpressions(sentencePlan, false);

        // Discourse Marker
        DiscourseMarker discourseMarker = new DiscourseMarker();
        sentencePlan = discourseMarker.insertSequenceConnectives(sentencePlan);

        // Realization
        SurfaceRealizer surfaceRealizer = new SurfaceRealizer();
        String surfaceText = surfaceRealizer.realizeSentenceMap(sentencePlan, transformedElemsRev);

        // Cleaning
        surfaceText = surfaceRealizer.postProcessText(surfaceText);

        /*if (surfaceOnly) {
            return surfaceText;
        }*/

        return surfaceText;
    }

 /*   private String appendTextToFile(String file, String text) {
        StringBuilder newFile = new StringBuilder();
        try {
            FileInputStream fstream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (!strLine.equals("</pnml>")) {
                    newFile.append(strLine).append("\n");
                } else {
                    newFile.append(text).append("\n</pnml>");
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile.toString();
    }*/
}
