import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AnalyzerTest {
    @Test public void analyzerReadFileMethodFileNotCanOpen() {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        assertEquals(listaesperada, classAnalyzerTest.readFile("d:\\prueba.txt"));
    }
    @Test public void analyzerReadFileMethodFileExist() {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        assertEquals(listaesperada, classAnalyzerTest.readFile("d:\\prueba.txt"));
    }
}
