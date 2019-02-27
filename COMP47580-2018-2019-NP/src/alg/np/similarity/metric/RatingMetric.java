/**
 * Compute the similarity between two items based on the Cosine between item ratings
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class RatingMetric implements SimilarityMetric
{
	private DatasetReader reader; // dataset reader

	/**
	 * constructor - creates a new RatingMetric object
	 * @param reader - dataset reader
	 */
	public RatingMetric(final DatasetReader reader)
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
		
		//Getting Item Profiles for Movie X and Movie Y
		Profile xprofile=reader.getItemProfiles().get(X);
		Profile yprofile=reader.getItemProfiles().get(Y);
		
		//Geeting Norm for Movie X and Movie Y
		double xnorm=xprofile.getNorm();
		double ynorm=yprofile.getNorm();
		
		//Calculating product
		double product=0.0;
		Set<Integer> ids = xprofile.getCommonIds(yprofile);
				
		for(Integer i : ids) {
			product+=xprofile.getValue(i)*yprofile.getValue(i);
		}
		//Returning Cosine Similarity
		return xnorm*ynorm >0 ? product/(xnorm*ynorm) : 0;
	}
}
