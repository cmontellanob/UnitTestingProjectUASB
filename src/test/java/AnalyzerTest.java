import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
