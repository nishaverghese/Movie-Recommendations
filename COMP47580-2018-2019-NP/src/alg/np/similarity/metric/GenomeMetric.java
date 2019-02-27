/**
 * Compute the similarity between two items based on the Cosine between item genome scores
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class GenomeMetric implements SimilarityMetric
{
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new GenomeMetric object
	 * @param reader - dataset reader
	 */
	public GenomeMetric(final DatasetReader reader)
	{
		this.reader = reader;
	}
	
	/**
	 * computes the similarity between items
	 * @param X - the id of the first item 
	 * @param Y - the id of the second item
	 */
	public double getItemSimilarity(final Integer X, final Integer Y)
	{
		// calculate similarity using Cosine
		
		//Getting Genome scores of Movie X and Movie Y
		Profile xprofile=reader.getItem(X).getGenomeScores();
		Profile yprofile=reader.getItem(Y).getGenomeScores();
		
		//Getting norm for Movie X and Movie Y
		double xnorm=xprofile.getNorm();
		double ynorm=yprofile.getNorm();
		
		//Calculating product
		double product=0.0;
		Set<Integer> ids = xprofile.getIds();
		
		for(Integer i : ids) {
			product+=xprofile.getValue(i)*yprofile.getValue(i);
		}
		
		//Returning Cosine Similarity
		return xnorm*ynorm >0 ? product/(xnorm*ynorm) : 0;
	}
}
