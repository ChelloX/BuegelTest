package Tests.UTToolwrapper;

import static org.junit.Assert.assertEquals;

import ToolWrapper.StanfordParserFunctionality;
import ToolWrapper.StanfordParserInitializer;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.*;
import org.junit.Test;
import worldModel.Text;

public class UTStanfordParser {
    @Test
    public void evaluateStanfordParserInvocation() {
        StanfordParserInitializer spi= StanfordParserInitializer.getInstance();

        /***check Initialization***/
        StanfordCoreNLP pipeline = spi.getPipeline();
        GrammaticalStructureFactory gsf = spi.getGrammaticalStructure();

        assertEquals("Stanford Parser Initialization Issue: Pipeline not initialized",true, pipeline!=null);
        assertEquals("Stanford Parser Initialization Issue: GrammaticalStructureFactory not initialized",true, gsf!=null);

        /***check Functionality***/
        String testText = "It's always difficult to write a test sentence, that actually makes sense. The Thing is also, that I even need two of them.";
        StanfordParserFunctionality spf =StanfordParserFunctionality.getInstance();
        Text analyzedText = spf.createText(testText);

        assertEquals("Stanford Parser Analysis Issue: Sentences not analyzed correctly",true, 2==analyzedText.getSize());
        assertEquals("Stanford Parser Analysis Issue: Words not analyzed correctly",true, 15==analyzedText.getSentence(0).length());
        assertEquals("Stanford Parser Analysis Issue: Words not analyzed correctly",true, 13==analyzedText.getSentence(1).length());

        GrammaticalStructure grammar = analyzedText.getSentence(0).getGrammaticalStructure();
        assertEquals("Stanford Parser Analysis Issue: Grammar not analyzed correctly",true, 15==grammar.allTypedDependencies().size());
        System.out.println("Stanford Parser works.");
    }
}