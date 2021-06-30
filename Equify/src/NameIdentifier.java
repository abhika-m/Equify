import java.io.IOException;
 
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class NameIdentifier {

	public static void main(String[] args) throws IOException,
    ClassNotFoundException {

		// Initialize 
		MaxentTagger left3 = new MaxentTagger(
				"taggers/english-left3words-distsim.tagger");
		
		MaxentTagger bidirectional = new MaxentTagger(
				"taggers/english-bidirectional-distsim.tagger");
		
		MaxentTagger caseless = new MaxentTagger(
				"taggers/english-caseless-left3words-distsim.tagger");

		// The sample string
		String sample = "Alice went to the store";
		String sample2 = "Alice went to the store in Washington";

		// The tagged string
		String tagged = left3.tagString("Left 3: " + sample);
		String tagged2 = left3.tagString("Left 3: " + sample2);
		
		String tagged3 = bidirectional.tagString("bidirectional: " + sample);
		String tagged4 = bidirectional.tagString("bidirectional: " + sample2);
		
		
		String tagged5 = caseless.tagString("caseless: " + sample);
		String tagged6 = caseless.tagString("caseless: " + sample2);

		// Output the result
		System.out.println(tagged);
		System.out.println(tagged2);
		System.out.println();
		System.out.println(tagged3);
		System.out.println(tagged4);
		System.out.println();
		System.out.println(tagged5);
		System.out.println(tagged6);
	}

}
