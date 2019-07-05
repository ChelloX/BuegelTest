package P2TWebservice;

public class P2TController extends Thread {

    String text;
    public static final int MAX_INPUT_LENGTH=15000;//Reject any Request larger than this

    public String generateText (String text) {
        this.text = text;
        String output = "";
        org.woped.p2t.textGenerator.TextGenerator tg = new org.woped.p2t.textGenerator.TextGenerator();

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
