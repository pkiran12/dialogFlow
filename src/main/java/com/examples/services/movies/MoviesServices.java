/**
 * @author I321593 created Jun 25, 2017
 */
package com.examples.services.movies;

import com.examples.services.OffersItf;

/**
 * @author I321593
 *
 */
public class MoviesServices implements OffersItf {

  private StringBuilder builder;

  /**
   * 
   */
  public MoviesServices(StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public String getOffers() {

    MovieOffersItf bmsOffers = new BMSMovieOffers();

    MovieOffersItf paytmOffers = new PaytmMovieOffers();

    bmsOffers.setNextInChain(paytmOffers);

    bmsOffers.getMovieOffers(builder);

    return builder.toString();
  }

}
