import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AnalizerTestMethodAllWord {

    @Test
    public void sentece_create_a_bilingual_charmer_just_like_the_woman_who_inspired_it() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listsentences= new ArrayList<Sentence>();
        listsentences.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
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
        assertEquals("Construye adecuadamente lista de palabras",Palabras,classAnalyzerTest.allWords(listsentences) );
    }
    @Test
    public void Word_Charmer_Count_1()
    {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> sentenceList = new ArrayList<Sentence>();
        sentenceList.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        Set<Word> setOfWords = classAnalyzerTest.allWords(sentenceList);
        Word word = Analyzer.getWordOnSet(setOfWords, "charmer");
        Assert.assertEquals("Concide Aparecer ",1, word.getCount());
    }

    @Test public void Word_it_Total_1() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listsentences = new ArrayList<Sentence>();
        listsentences.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listsentences.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listsentences.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West ."));
        listsentences.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more ."));
        listsentences.add(new Sentence(2, "Best indie of the year , so far ."));
        Set<Word>SetWords = classAnalyzerTest.allWords(listsentences);
        Word PalabraEncontrada=Analyzer.getWordOnSet(SetWords, "it");
        /* Objeto Word:            "it"
            count:  2
            Total:            1
            Score:0.5
            */
        assertEquals("Coincide Scores Palabras",1,PalabraEncontrada.getTotal());
    }
    @Test public void Word_it_Count_2() throws IOException {
        Analyzer classAnalyzerTest = new Analyzer();
        List<Sentence> listsentences = new ArrayList<Sentence>();
        listsentences.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listsentences.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listsentences.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West ."));
        listsentences.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more ."));
        listsentences.add(new Sentence(2, "Best indie of the year , so far ."));
        Set<Word>SetWords = classAnalyzerTest.allWords(listsentences);
        Word PalabraEncontrada=Analyzer.getWordOnSet(SetWords, "it");
           /* Objeto Word:            "it"
            count:  2
            Total:            1
            Score:0.5
            */
        assertEquals("Coincide Apariciones",2,PalabraEncontrada.getCount());
    }

    @Test
    public void Word_like_Count_5()
    {
        List<Sentence> listsentences = new ArrayList<>();
        listsentences.add(new Sentence(1, "Like uno"));
        listsentences.add(new Sentence(2, "Like more two"));
        listsentences.add(new Sentence(-2, "Dont Like third"));
        listsentences.add(new Sentence(1, "Like fourth"));
        listsentences.add(new Sentence(0, "Like fifth"));
        Set<Word> SetWords = Analyzer.allWords(listsentences);
        Word resultingWord = Analyzer.getWordOnSet(SetWords, "like");
        Assert.assertEquals("Cuenta similar aparicion ",5, resultingWord .getCount());
    }
    @Test
    public void Word_like_Total_2()
    {
        List<Sentence> listsentences = new ArrayList<>();
        listsentences.add(new Sentence(1, "Like uno"));
        listsentences.add(new Sentence(2, "Like more two"));
        listsentences.add(new Sentence(-2, "Dont Like third"));
        listsentences.add(new Sentence(1, "Like fourth"));
        listsentences.add(new Sentence(0, "Like fifth"));
        Set<Word> SetWords = Analyzer.allWords(listsentences);
        Word resultingWord = Analyzer.getWordOnSet(SetWords, "like");
        Assert.assertEquals("Cuenta similar score",2, resultingWord .getTotal());
    }

    @Test
    public void Word_as_Total_2()
    {
        List<Sentence> sentenceList = new ArrayList<>();
        sentenceList.add(new Sentence(1, "This was not as much fun as I thought it would be"));

        Set<Word> setOfWords = Analyzer.allWords(sentenceList);

        Word resultingWord_as = Analyzer.getWordOnSet(setOfWords, "as");

        Assert.assertEquals("Cuenta similar score", 2, resultingWord_as.getTotal());
    }

    @Test
    public void Word_the_CalculateScore_25()
    {
        List<Sentence> listsentences = new ArrayList<>();
        listsentences.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listsentences.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listsentences.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West"));
        listsentences.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more"));
        listsentences.add(new Sentence(2, "Best indie of the year, so far"));

        Set<Word> setOfWords = Analyzer.allWords(listsentences);

        Word resultingWord_the = Analyzer.getWordOnSet(setOfWords, "the");

        Assert.assertEquals("Similiar Score", 0.25, resultingWord_the.calculateScore(),0.0001);
    }

    @Test
    public void Words_it_CalculateScore_05()
    {
        List<Sentence> listsentences = new ArrayList<>();
        listsentences.add(new Sentence(1, "a bilingual charmer , just like the woman who inspired it"));
        listsentences.add(new Sentence(0, "Like a less dizzily gorgeous companion to Mr. Wong 's In"));
        listsentences.add(new Sentence(-1, "As inept as big-screen remakes of The Avengers and The Wild Wild West ."));
        listsentences.add(new Sentence(0, "It 's everything you 'd expect -- but nothing more ."));
        listsentences.add(new Sentence(2, "Best indie of the year , so far ."));
         /* Objeto Word:            "it"
            count:  2
            Total:            1
            Score:0.5
            */
        Set<Word> setOfWords = Analyzer.allWords(listsentences);

        Word resultingWord_it = Analyzer.getWordOnSet(setOfWords, "it");

        Assert.assertEquals("the word wrong Calculate score", 0.5, resultingWord_it.calculateScore(),0.0001);
    }
}
