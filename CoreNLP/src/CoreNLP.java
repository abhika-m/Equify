import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;

public class CoreNLP {
	
	
    public static void main(String[] args) throws FileNotFoundException {
    	
    	Random r = new Random();
    	ArrayList<Integer> indexes = new ArrayList<Integer>();
    	for(int i = 0; i < 13; i++) {
    		indexes.add(i);
    	}
    	// file story
    	File file = new File("stories/cinderella.txt");
    	Scanner input = new Scanner(file);
    	String story = "";
    	System.out.println("Original: ");
    	while(input.hasNextLine()) {
    		String s = input.nextLine();
    		story += s;
    		System.out.println(s);
    	}
    	String result = "";
    	//System.out.println("original: " + story);
    	input.close();
    	
        // creates properties for identification
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        
        // creates a natural language processor type
        StanfordCoreNLP processor = new StanfordCoreNLP(props);

        // annotation type will tag with necessary identifier
        Annotation document = new Annotation(story);

        // tagging the text
        processor.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        
        // creates list of names present in input
        ArrayList<String> names = new ArrayList<String>();
        Map<String, String> storyNames = new TreeMap<String, String>();
        ArrayList<String> diverseNames = new ArrayList<String>();
        diverseNames.add("Ananya");
        diverseNames.add("Ishaan");
        diverseNames.add("Lei");
        diverseNames.add("Bao");
        diverseNames.add("Dominique");
        diverseNames.add("Jeremiah");
        diverseNames.add("Jake");
        diverseNames.add("Sia");
        diverseNames.add("Kiara");
        diverseNames.add("James");
        diverseNames.add("Lee");
        diverseNames.add("Sandy");
        
        // loops each sentence
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // word being tagged
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // part of speech of word
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // name entity
                String entity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                
                // adds to names list if it is a person
                //if(entity.equals("PERSON") && !names.contains(word)) {
                	//names.add(word);
                //}
                
                if(entity.equals("PERSON")) {
                	if(!names.contains(word)) {
                		names.add(word);
                		int rand = r.nextInt(indexes.size()-1);
                		storyNames.put(word, diverseNames.get(rand));
                		indexes.remove(rand);
                	}
                	result += storyNames.get(word) + " ";
                }
                else {
                	result += word + " ";
                }
                // prints word with tagging
                //System.out.print(String.format("%s\\%s", word, pos));
                
                // doesn't print entity if it is O
                /*
                if(!entity.equals("O")) {
                	System.out.print("\\" + entity + " ");
                }
                else {
                	System.out.print(" ");
                }
                */
            }
            //System.out.println();
            result += "\n";
        }
        System.out.println(result);
        System.out.println(names);
      
    }
}
