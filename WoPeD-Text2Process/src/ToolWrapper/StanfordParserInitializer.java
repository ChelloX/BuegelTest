package ToolWrapper;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.TreebankLanguagePack;

import java.util.Properties;


public class StanfordParserInitializer {

    private static StanfordParserInitializer SPinitializer;
    private static StanfordCoreNLP pipeline;
    private TreebankLanguagePack tlp;
    private GrammaticalStructureFactory gsf;

    private StanfordParserInitializer(){
    }

    /*
    * Returns current Instance of the StanfordParserInitializer if it already exists.
    * If not, it creates a new Instance of the StanfordParserInitializer and initializes its values.
     */
    public synchronized static StanfordParserInitializer getInstance(){
        if (SPinitializer == null){
            synchronized (StanfordParserInitializer.class) {
                if (SPinitializer == null) {
                    SPinitializer = new StanfordParserInitializer();
                    SPinitializer.init();
                }
            }

        }
        return SPinitializer;
    }

    /*
    * Sets the current StanfordParserInstance to null
     */
    public static synchronized void resetInstance(){
        SPinitializer=null;
    }

    public synchronized StanfordCoreNLP getPipeline() {return pipeline;};
    public synchronized GrammaticalStructureFactory getGrammaticalStructure(){return gsf;}

    /*
    * Initializes the StanfordParserInitializer values by creating an Annotation pipeline with the respective
    * chosen Annotators and creating a GrammaticalStructureFactory.
     */
    private void init(){
        try{
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
            //props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,dcoref");
            props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
            //props.setProperty("depparse.model", "edu/stanford/nlp/models/parser/nndep/english_UD.gz");
            pipeline = new StanfordCoreNLP(props);
            tlp = new PennTreebankLanguagePack();
            gsf = tlp.grammaticalStructureFactory();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
