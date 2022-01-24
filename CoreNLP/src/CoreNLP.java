import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CoreNLP {
    // takes in text and prints arrays of names in text
    // uses part of speech tagger from Stanford Core NLP
    public static void main(String[] args) {
    	
        // creates properties for identification
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        
        // creates a natural language processor type
        StanfordCoreNLP processor = new StanfordCoreNLP(props);

        // text to tag
        String text = "Alice went to the store in Seattle. She went with Brittany. Alice and Brittany are friends.";

        // annotation type will tag with necessary identifier
        Annotation document = new Annotation(text);

        // tagging the text
        processor.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        
        // creates list of names present in input
        ArrayList<String> names = new ArrayList<String>();
        
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
                if(entity.equals("PERSON") && !names.contains(word)) {
                	names.add(word);
                }
                // prints word with tagging
                System.out.print(String.format("%s\\%s", word, pos));
                
                // doesn't print entity if it is O
                if(!entity.equals("O")) {
                	System.out.print("\\" + entity + " ");
                }
                else {
                	System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(names);
   
    }
}
