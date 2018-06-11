import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;


public class AnalyzerTestMethodCalculateScores {

    @Test public void WhenListOfWordsisEmpy() {
        Analyzer classAnalyzerTest = new Analyzer();
        Map<String, Double> wordsScores=new HashMap<String, Double>() ;
        Set<Word> Palabras = new HashSet<Word>();

        assertEquals(wordsScores, classAnalyzerTest.calculateScores(Palabras));
    }

    @Test public void CalculateScoresWord_it() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listsentences = new ArrayList<Sentence>();
        Map<String, Double> wordsScores=new HashMap<String, Double>() ;
        listsentences.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listsentences.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listsentences.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West ."));
        listsentences.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more ."));
        listsentences.add(new Sentence(2, "Best indie of the year , so far ."));
        Set<Word>Palabras = classAnalyzerTest.allWords(listsentences);
        wordsScores=classAnalyzerTest.calculateScores(Palabras);

        // Imprimimos el Map con un Iterador
        Iterator it = wordsScores.keySet().iterator();
        double valor=0;
        while(it.hasNext()){
            String key = (String)it.next();
            if (key.equals("it"))
                valor=wordsScores.get(key);
        }
        Word Palabra = new Word("it");

        Palabra.increaseTotal(1);
        Palabra.increaseTotal(0);

        assertEquals(Palabra.calculateScore(),valor,0.001);

    }

}
