/**
 * @author I321593 created Jun 25, 2017
 */
package com.examples.services;

import com.examples.services.movies.MoviesServices;
import com.examples.services.recharges.RechargeService;
import com.examples.services.shopping.FlipkartServices;

/**
 * @author I321593
 *
 */
public class OfferFactory {

  public static OffersItf getOffersbasedOnType(String typeOffer) {

    OffersItf offersitf = null;

    if (typeOffer == null || typeOffer.isEmpty()) {
      return new OffersItf() {

        @Override
        public String getOffers() {
          return "Enter a valid entry";
        }
      };
    }
    StringBuilder builder = new StringBuilder();
    if (typeOffer.equalsIgnoreCase("Recharge")) {
      offersitf = new RechargeService(builder);
    } else if (typeOffer.equalsIgnoreCase("Movies")) {
      offersitf = new MoviesServices(builder);
    } else if (typeOffer.equalsIgnoreCase("Flipkart")) {
      offersitf = new FlipkartServices();
    } else if (typeOffer.equalsIgnoreCase("Amazon")) {
      offersitf = new FlipkartServices();
    }

    return offersitf;
  }

}
