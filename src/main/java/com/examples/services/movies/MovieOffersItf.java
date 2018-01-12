/**
 * @author I321593 created Jun 27, 2017
 */
package com.examples.services.movies;

/**
 * @author I321593
 *
 */
public interface MovieOffersItf {

  void getMovieOffers(StringBuilder builder);

  void setNextInChain(MovieOffersItf rechargeItf);

}
