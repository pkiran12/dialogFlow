/**
 * @author I321593 created Jun 19, 2017
 */
package com.example.offers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;

/**
 * @author I321593
 *
 */
public class TestApp {

  /**
   * @param args
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
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

             genericMap.computeIfPresent("genericPromocode", (key, value) -> processPromocode(value));
        
             genericMap.computeIfPresent("txnAmount", (key, value) -> processPromocode(value));


          });

      /*
       * offerCategories.forEach((key, value) -> {
       * 
       * if (value instanceof String && value.toString() .contains("recharges")) {
       * 
       * if (key.toString() .contains("offersIdList")) {
       * 
       * List<Map<String, String>> offerIdlist = (List<Map<String, String>>) value;
       * System.out.println(value); }
       * 
       * }
       * 
       * });
       */

      System.out.println();

    } catch (IOException e) {
      System.out.println(e);
    }
  }

  /**
   * @param value
   * @return 
   * @return
   */
  private static String processPromocode(Object value) {
    System.out.println(value.toString());
    return value.toString();
  }

}
