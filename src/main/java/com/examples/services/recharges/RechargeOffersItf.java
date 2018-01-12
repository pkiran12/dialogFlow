/**
 * @author I321593 created Jun 25, 2017
 */
package com.examples.services.recharges;

/**
 * @author I321593
 *
 */
public interface RechargeOffersItf {

  void getRechargeOffers(StringBuilder builder);

  void setNextInChain(RechargeOffersItf rechargeItf);

}
