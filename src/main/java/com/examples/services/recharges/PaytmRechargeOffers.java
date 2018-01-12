/**
 * @author I321593 created Jun 25, 2017
 */
package com.examples.services.recharges;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author I321593
 *
 */
public class PaytmRechargeOffers implements RechargeOffersItf {

  private RechargeOffersItf rechargeItf;

  /*
   * (non-Javadoc)
   * 
   * @see com.examples.services.recharges.RechargeItf#getRechargeOffers(java.lang.StringBuilder)
   */
  @Override
  public void getRechargeOffers(StringBuilder builder) {
    setPaytmOffers(builder);
    rechargeItf.getRechargeOffers(builder);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.examples.services.recharges.RechargeItf#setNextInChain(com.examples.services.recharges.
   * RechargeItf)
   */
  @Override
  public void setNextInChain(RechargeOffersItf rechargeItf) {
    this.rechargeItf = rechargeItf;
  }

  /**
   * @param offerStringBuilder
   */
  private void setPaytmOffers(StringBuilder offerStringBuilder) {
    offerStringBuilder.append("\n");
    offerStringBuilder.append("Paytm Offers");
    offerStringBuilder.append("\n");
    getPaytmOffers(offerStringBuilder);
  }

  /**
   * @param to
   */
  private void getPaytmOffers(StringBuilder builder) {
    try {
      Document d = Jsoup.connect("https://paytm.com/offer/recharge/")
          .get();

      Elements elementLst = d.getElementsByTag("h3");
      AtomicInteger count = new AtomicInteger(0);
      elementLst.forEach(element -> {

        element.getElementsByTag("span")
            .append("&nbsp;.");

        String text1 = element.text();

        if (!text1.equalsIgnoreCase("menu")) {
          builder.append("\n");
          builder.append(count.incrementAndGet() + ".");
          builder.append(text1);
          builder.append("\n");
        }
      });
    } catch (IOException e) {
      System.out.println(e);
    }
  }

}
