/**
 * @author I321593 created Jun 25, 2017
 */
package com.examples.services.recharges;

import com.examples.services.OffersItf;

/**
 * @author I321593
 *
 */
public class RechargeService implements OffersItf {

  private StringBuilder builder;

  /**
   * @param builder
   */
  public RechargeService(StringBuilder builder) {
    this.builder = builder;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.examples.services.OffersItf#getOffers()
   */
  @Override
  public String getOffers() {

    RechargeOffersItf paytmOffers = new PaytmRechargeOffers();

    RechargeOffersItf freechargeOffers = new FreeChargeRechargeOffers();

    paytmOffers.setNextInChain(freechargeOffers);

    paytmOffers.getRechargeOffers(builder);

    return builder.toString();
  }

}
