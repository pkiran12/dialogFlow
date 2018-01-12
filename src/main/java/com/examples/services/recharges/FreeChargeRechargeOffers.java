/**
 * @author I321593 created Jun 25, 2017
 */
package com.examples.services.recharges;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;

import com.google.gson.Gson;

/**
 * @author I321593
 *
 */
public class FreeChargeRechargeOffers implements RechargeOffersItf {

  /*
   * (non-Javadoc)
   * 
   * @see com.examples.services.recharges.RechargeItf#getRechargeOffers(java.lang.StringBuilder)
   */
  @Override
  public void getRechargeOffers(StringBuilder builder) {
    setFreeChargeOffers(builder);
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

  }

  /**
   * @param offerStringBuilder
   */
  private void setFreeChargeOffers(StringBuilder offerStringBuilder) {
    offerStringBuilder.append("\n");
    offerStringBuilder.append("Freecharge Offers");
    offerStringBuilder.append("\n");
    getFreechargeOffers(offerStringBuilder);
  }
  
  
  
  @SuppressWarnings("unchecked")
  private void getFreechargeOffers(StringBuilder builder) {

    try {
      String body = Jsoup.connect(
          "https://www.freecharge.in/campaign-service/external/offers/getAllByChannel?channelId=11")
          .ignoreContentType(true)
          .execute()
          .body();

      Gson gson = new Gson();
      Map<String, Object> jsonMap = gson.fromJson(body, Map.class);

      List<Map<String, Object>> offerCategoriesLst =
          (List<Map<String, Object>>) jsonMap.get("offerCategories");

      List<Map<String, Object>> offers = (List<Map<String, Object>>) jsonMap.get("offers");

      Map<String, Object> offersMap = offerCategoriesLst.parallelStream()
          .filter(map -> map.get("description")
              .toString()
              .equalsIgnoreCase("recharges"))
          .findFirst()
          .orElse(new HashMap<>());

      List<Double> offerIdList = (List<Double>) offersMap.get("offersIdList");

      offers.parallelStream()
          .filter(offerListMap -> {

            Iterator<Double> iterator = offerIdList.iterator();

            boolean isMatch = false;

            while (iterator.hasNext()) {

              double offerId = (double) offerListMap.get("id");

              if (Double.compare(offerId, iterator.next()) == 0) {
                isMatch = true;
                break;
              }

            }

            /*
             * offerIdList.parallelStream() .forEach(offersId -> {
             * 
             * });
             */

            return isMatch;
          }

          )
          .forEach(offerMap -> {

            System.out.println(offerMap.get("description"));

            Map<String, Object> genericMap = (Map<String, Object>) offerMap.get("offerParams");

            Object vale = genericMap.computeIfPresent("genericPromocode",
                (key, value) -> processPromocode(value, "Promo Code"));

            Object minAmount = genericMap.computeIfPresent("txnAmount",
                (key, value) -> processPromocode(value, "Minimum Txn Amount"));

            builder.append("\n");
            builder.append(vale.toString());
            builder.append("\n");
            builder.append(minAmount.toString());
            builder.append("\n");
          });

    } catch (IOException e) {
      System.out.println(e);
    }

  }
  
  private static String processPromocode(Object value, String word) {
    System.out.println(value.toString());
    return value.toString() + " " + word;
  }

}
