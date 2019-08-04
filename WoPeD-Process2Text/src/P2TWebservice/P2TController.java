package P2TWebservice;

public class P2TController extends Thread {


    // This is the controller class, which is being called by the class P2T Servlet
    // Right now only consists of one return and one generateText Method
    // generate Text calls the main function in TextGenerator to translate a petri net to
    // natural language
    String text;
    public static final int MAX_INPUT_LENGTH=15000;//Reject any Request larger than this

    public String generateText (String text) {
        this.text = text;
        String output = "";
        org.woped.p2t.textGenerator.TextGenerator tg =
                new org.woped.p2t.textGenerator.TextGenerator();

        try {
            output = tg.toText(text, true);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getText() {
        return text;
    }



}
