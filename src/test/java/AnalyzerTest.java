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
    @Test public void analyzerReadFileMethodFileWithSentencesValid() {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        listaesperada.add(new Sentence(0, "This was not as much fun as I thought it would be ."));
        listaesperada.add(new Sentence(1, "I had a lot of fun on this and learned a lot ."));
        listaesperada.add(new Sentence(-1, "It would be more fun if we had more time to work on it ."));
        listaesperada.add(new Sentence(2, "I didn’t think programming in Java could be so much fun !"));
        listaesperada.add(new Sentence(-2, "I would have preferred an easier assignment ."));
        listaesperada.add(new Sentence(2, "I can’t think of anything more fun than learning Java !"));

        assertEquals(listaesperada, classAnalyzerTest.readFile("d:\\\\maestria\\\\UnitTestingProjectUASB\\\\files\\prueba1.txt"));
    }
}
/*
0 This was not as much fun as I thought it would be .
1 I had a lot of fun on this and learned a lot .
-1 It would be more fun if we had more time to work on it .
2 I didn’t think programming in Java could be so much fun !
        -2 I would have preferred an easier assignment .
        2 I can’t think of anything more fun than learning Java !
        */