import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.IOException;

public class part_of_speech_tagger {

	public static void main(String[] args) throws IOException,
     ClassNotFoundException {

		// Initialize the tagger
		MaxentTagger tagger = new MaxentTagger(
         "Taggers/english-left3words-distsim.tagger");

		// The sample string
		String sample = "This is a sample text";

		// The tagged string
		String tagged = tagger.tagString(sample);

		// Output the result
		System.out.println(tagged);
	}

}
