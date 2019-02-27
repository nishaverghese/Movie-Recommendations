/**
 * Compute the similarity between two items based on increase in confidence
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class IncConfidenceMetric implements SimilarityMetric
{
	private static double RATING_THRESHOLD = 4.0; // the threshold rating for liked items 
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new IncConfidenceMetric object
	 * @param reader - dataset reader
	 */
	public IncConfidenceMetric(final DatasetReader reader)
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
		// calculating similarity using conf(X => Y) / conf(!X => Y)
		
				// Getting profile of id X and Y

			    Profile x = reader.getItemProfiles().get(X);
			    Profile y = reader.getItemProfiles().get(Y);

			
			    double suppX = 0, nsuppX = 0, suppXY = 0, nsuppXY = 0;

			    Set<Integer> xIds = x.getIds();
		    

			    for(Integer id: xIds) {

			    	if(x.getValue(id)>=RATING_THRESHOLD)

			    		suppX++;

			    }

			    nsuppX = xIds.size() - suppX;
			    Set<Integer> commonIds = x.getCommonIds(y);

			    for(Integer id: commonIds) {

			    	if(x.getValue(id)>=RATING_THRESHOLD && y.getValue(id) >= RATING_THRESHOLD)

			    		suppXY++;

			    	else if (x.getValue(id)<RATING_THRESHOLD && y.getValue(id) >= RATING_THRESHOLD)

			    		nsuppXY++;

			    }		    

                //calculate conf(x=>y)

			    double confXY = 0.0;

			    if(suppX!=0)

			    	confXY = (suppXY*1.0)/(suppX*1.0);

			  
			    //calculate conf(x=>y)

			    double nconfXY = 0.0;

			    if(!(nsuppX==0))

			    		nconfXY = (nsuppXY*1.0)/(nsuppX*1.0);    

				return (nconfXY != 0.0) ? (confXY*1.0/nconfXY*1.0) : 0;
	}
}
