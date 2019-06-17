package ToolWrapper;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;
import worldModel.T2PSentence;
import worldModel.Text;

import java.util.ArrayList;
import java.util.List;

public class StanfordParserFunctionality {

    private StanfordParserInitializer SPInitializer = StanfordParserInitializer.getInstance();
    private StanfordCoreNLP pipeline;
    private GrammaticalStructureFactory gsf;

    private static StanfordParserFunctionality instance;

    public StanfordParserFunctionality(){
        pipeline = SPInitializer.getPipeline();
        gsf = SPInitializer.getGrammaticalStructure();
    }

    public synchronized static StanfordParserFunctionality getInstance(){
        if(instance==null){
            instance = new StanfordParserFunctionality();
        }
        return instance;
    }

    public synchronized static void resetInstance(){ instance = null;}

    public synchronized Text createText(String input){

        Text _result = new Text();

        Annotation document = new Annotation(input);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);


        List<String> lemmatizedArray = new ArrayList<>();
        String lemmatizedText ="";

        /**for (CoreMap sentence : sentences) {
            if(sentence.get(TokensAnnotation.class).get(0).value().equals("#")){
                continue;
            }

            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                if(token.get(PartOfSpeechAnnotation.class).equals("VBZ") || token.get(PartOfSpeechAnnotation.class).equals("VBG")|| token.get(PartOfSpeechAnnotation.class).equals("VBP")){
                    token.setValue(token.lemma());
                }
                lemmatizedArray.add(token.value());
            }
        }
        for (String str: lemmatizedArray){
            lemmatizedText+=str+" ";
        }
        System.out.println(lemmatizedText);

        document = new Annotation(lemmatizedText);
        pipeline.annotate(document);

        sentences = document.get(SentencesAnnotation.class); **/

        ArrayList<CoreLabel> _list = new ArrayList<CoreLabel>();

        for(CoreMap sentence : sentences) {
            System.out.println("Satz" +sentence);
            ArrayList<String> posTags = new ArrayList<>();
            for(CoreLabel token: sentence.get(TokensAnnotation.class)){
                _list.add(token);
                String pos = token.get(PartOfSpeechAnnotation.class);
                posTags.add(pos);

            }

            T2PSentence _s = new T2PSentence(_list);

            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
            _s.setTree(tree);

            GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
            System.out.println("enhanced: "+gs.typedDependenciesEnhancedPlusPlus());
            System.out.println(tree);
            System.out.println(posTags);
            _s.setGrammaticalStructure(gs);

            _result.addSentence(_s);
            _list.clear();

        }

        return _result;
    }

}