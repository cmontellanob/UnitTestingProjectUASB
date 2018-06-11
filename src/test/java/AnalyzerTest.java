import org.junit.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;


public class AnalyzerTest {

    @Test public void analyzerReadFileMethodFileNotCanOpen() {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        assertEquals(listaesperada, classAnalyzerTest.readFile("d:\\prueba.txt"));
    }
    @Test public void analyzerReadFileMethodFileWithSentencesValid() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        listaesperada.add(new Sentence(0, "This was not as much fun as I thought it would be ."));
        listaesperada.add(new Sentence(1, "I had a lot of fun on this and learned a lot ."));
        listaesperada.add(new Sentence(-1, "It would be more fun if we had more time to work on it ."));
        listaesperada.add(new Sentence(2, "I didn’t think programming in Java could be so much fun !"));
        listaesperada.add(new Sentence(-2, "I would have preferred an easier assignment ."));
        listaesperada.add(new Sentence(2, "I can’t think of anything more fun than learning Java !"));
        String filename=FileName (listaesperada,"prueba1.txt");
        assertEquals(listaesperada, classAnalyzerTest.readFile(filename));
    }

    @Test public void analyzerReadFileMethodFileWithSentencesNoValidScore() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        listaesperada.add(new Sentence(0, "This was not as much fun as I thought it would be ."));
        listaesperada.add(new Sentence(1, "I had a lot of fun on this and learned a lot ."));
        String filename=FileName (listaesperada,"prueba2.txt");
        File archivo = new File(filename);
        FileWriter fr = new FileWriter(archivo,true);
        fr.write("3 It would be more fun if we had more time to work on it .");
        fr.write("-3 I didn’t think programming in Java could be so much fun !");
        fr.close();
        assertEquals(listaesperada, classAnalyzerTest.readFile(filename));

    }
    @Test public void analyzerReadFileMethodFileWithSentencesNoValidSentence() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        listaesperada.add(new Sentence(0, "This was not as much fun as I thought it would be ."));
        listaesperada.add(new Sentence(1, "I had a lot of fun on this and learned a lot ."));
        String filename=FileName (listaesperada,"prueba3.txt");
        File archivo = new File(filename);
        FileWriter fr = new FileWriter(archivo,true);
        fr.write("It would be more fun if we had more time to work on it .");
        fr.close();
        assertEquals(listaesperada, classAnalyzerTest.readFile(filename));

    }

    @Test public void analyzerAllWordsMethodCreateWordsWithListSentences() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        listaesperada.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        String filename=FileName (listaesperada,"prueba4.txt");
        List<Sentence> sentences=classAnalyzerTest.readFile(filename);
        Set<Word> Palabras = new HashSet<Word>();
        Palabras.add(new Word("a"));
        Palabras.add(new Word("bilingual"));
        Palabras.add(new Word("charmer"));
        Palabras.add(new Word("just"));
        Palabras.add(new Word("like"));
        Palabras.add(new Word("the"));
        Palabras.add(new Word("woman"));
        Palabras.add(new Word("who"));
        Palabras.add(new Word("inspired"));
        Palabras.add(new Word("it"));
        assertEquals(Palabras,classAnalyzerTest.allWords(sentences) );

    }

    @Test public void analyzerAllWordsVerifyWord_it() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        listaesperada.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listaesperada.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listaesperada.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West ."));
        listaesperada.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more ."));
        listaesperada.add(new Sentence(2, "Best indie of the year , so far ."));
        String filename=FileName (listaesperada,"prueba5.txt");
        List<Sentence> sentences=classAnalyzerTest.readFile(filename);

        Word Palabra = new Word("it");
        Word PalabraEncontrada=null;

        Palabra.increaseTotal(1);
        Palabra.increaseTotal(0);

        Set<Word>Palabras = classAnalyzerTest.allWords(sentences);
        for(Word palabra: Palabras) {
            //System.out.println(palabra.getText());

            if (palabra.getText().equals("it"))  {
                PalabraEncontrada = palabra;
            }
        }
        assertEquals(Palabra.getText(),PalabraEncontrada.getText());
        assertEquals(Palabra.getTotal(),PalabraEncontrada.getTotal());
        assertEquals(Palabra.getCount(),PalabraEncontrada.getCount());

    }

    @Test public void analyzerCalculateScoresMethodWhenListOfWordsisEmpy() {
        Analyzer classAnalyzerTest = new Analyzer();
        Map<String, Double> wordsScores=new HashMap<String, Double>() ;
        Set<Word> Palabras = new HashSet<Word>();

        assertEquals(wordsScores, classAnalyzerTest.calculateScores(Palabras));
    }

    @Test public void analyzerCalculateScoresWord_it() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listaesperada = new ArrayList();
        Map<String, Double> wordsScores=new HashMap<String, Double>() ;
        listaesperada.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listaesperada.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listaesperada.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West ."));
        listaesperada.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more ."));
        listaesperada.add(new Sentence(2, "Best indie of the year , so far ."));
        String filename=FileName (listaesperada,"prueba5.txt");
        List<Sentence> sentences=classAnalyzerTest.readFile(filename);

        Set<Word>Palabras = classAnalyzerTest.allWords(sentences);
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
        Word PalabraEncontrada=null;

        Palabra.increaseTotal(1);
        Palabra.increaseTotal(0);

        assertEquals(Palabra.calculateScore(),valor,0.001);

    }

    // metodo helper que ayuda  a crear archivo de texto a analizar
    private String FileName (List<Sentence> lista,String File) throws IOException {
        File miDir = new File(".");
        String archivo=miDir.getCanonicalPath()+"\\files\\"+File;
        BufferedWriter bw =new BufferedWriter (new FileWriter (archivo));
        for(Sentence sentence : lista) {
            bw.write(sentence.getScore()+" "+sentence.getText()+"\n");
        }
        bw.close ();
        return archivo;
    }

}
