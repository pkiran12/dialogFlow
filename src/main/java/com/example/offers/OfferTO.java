/**
 * @author I321593 created Jun 19, 2017
 */
package com.example.offers;

import java.util.List;

import com.google.gson.Gson;

/**
 * @author I321593
 *
 */
public class OfferTO {

  private String website;

  private String offers;

  /**
   * @return the website
   */
  public String getWebsite() {
    return website;
  }

  /**
   * @param website the website to set
   */
  public void setWebsite(String website) {
    this.website = website;
  }

  /**
   * @return the offers
   */
  public String getOffers() {
    return offers;
  }

  /**
   * @param offers the offers to set
   */
  public void setOffers(String offers) {
    this.offers = offers;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    System.out.println(offers);
    return "" + website + " offers \n" + offers;
  }

}
