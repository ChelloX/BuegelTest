package org.woped.file.t2p;

import org.junit.Test;
import javax.swing.*;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static sun.nio.cs.Surrogate.is;

public class PlainTextFileReaderTest {


    @Test
    public void read() {
        PlainTextFileReader ptfr = new PlainTextFileReader();
        StringBuilder sb = new StringBuilder();
        JFileChooser chooser = new JFileChooser();
        String usrPath = System.getProperty("user.dir");
        String[] filetype = {"txt","doc","docx"}; //

        for( String k: filetype) {
            File file = new File(usrPath + "/tests/org/woped/file/t2p/test." + k);
            sb = ptfr.readTxtFile(file, sb);
            assertEquals(sb.toString(), "A manager is managing a project\n");
            }
     }

}