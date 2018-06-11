import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Analyzer {

	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		List<String> lineas= new ArrayList();
		try {
			File archivo = new File(filename);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null) {
				lineas.add(linea);
			}
		}
		catch(Exception e){
			//System.out.print(e.printStackTrace());

		}
		finally
		{
			return CreateSentencesFromList(lineas);
		}


	}
	private static List<Sentence> CreateSentencesFromList(List<String> listString)
	{
		List<Sentence> list = new ArrayList();
		for (String linea : listString) {
			int pos=linea.indexOf(" ");
			try {
				int score = Integer.parseInt(linea.substring(0, pos));
				if (score>=-2 && score<=2) {
					String sentence = linea.substring(pos + 1);
					list.add(new Sentence(score, sentence.trim()));
				}
			}catch (Exception e)
			{
			//error de conversion
			}

		}
		return list;
	}

	/*
	 * Implemen t this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> Palabras = new HashSet<Word>();
		for(Sentence sentence : sentences) {
			String TextoAnalizar=sentence.getText().replaceAll("[,./']","");
			TextoAnalizar=TextoAnalizar.replaceAll("[-]","");

			String[] palabras=TextoAnalizar.split(" ");

            for (String palabra:palabras) {
				if (palabra.trim().length()>0) {
                    Palabras.add(new Word(palabra.toLowerCase().trim()));
				}
			}
		}
		//calcular scores
		for(Word palabra:Palabras)
		{
            for(Sentence sentence : sentences) {
                String cadena=sentence.getText().toLowerCase();
                String[] listapalabras=cadena.split(" ");

                for (String texto:listapalabras)
				{
				    if (texto.equals(palabra.getText()))
                        palabra.increaseTotal(sentence.score);

				}
			}
		}
		return Palabras;
	}

	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
        Map<String, Double> wordscores=new HashMap<String, Double>() ;
        for(Word palabra: words) {
            wordscores.put(palabra.getText(), palabra.calculateScore());
        }
        return wordscores;

	}

    public static Word getWordOnSet(Set<Word> Words, String word) {
        for(Word palabra: Words) {
            if (palabra.getText().equals(word.toLowerCase()))  {
                return palabra;
            }
        }
	    return null;
    }
}
