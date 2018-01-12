/**
 * @author I321593 created Jun 19, 2017
 */
package com.example.offers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examples.services.OfferFactory;
import com.examples.services.OffersItf;
import com.google.gson.Gson;

/**
 * @author I321593
 *
 */
@RestController
@RequestMapping("/webhook")
public class ServiceController {

  @Autowired
  org.springframework.core.io.ResourceLoader loader;

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public WebhookResponse greeting(@RequestBody String obj) {

    String offers = invokeOffers(obj);

    WebhookResponse response = null;

    // String offers = getOffers("movies");
    /*
     * Resource resource = loader.getResource("classpath:facebook.json");
     * 
     * try { InputStream inputStream = resource.getInputStream();
     * 
     * return response = new WebhookResponse(ResourceLoader.getJson(inputStream),
     * ResourceLoader.getJson(inputStream), ResourceLoader.getJson(inputStream));
     * 
     * } catch (IOException e) { System.out.println("Exception while loading file " +
     * e.getMessage()); }
     */

    response = new WebhookResponse(offers, offers, "");

    return response;
  }

  /**
   * @param obj
   * @return
   */
  @SuppressWarnings("unchecked")
  private String invokeOffers(String obj) {
    Gson gson = new Gson();

    Map<String, Object> bodymap = gson.fromJson(obj, Map.class);

    Map<String, Object> resultMap = (Map<String, Object>) bodymap.get("result");

    Map<String, String> param = (Map<String, String>) resultMap.get("parameters");

    String offers = param.computeIfPresent("typeOffer", (key, value) -> getOffers(value));
    return offers;
  }

  /**
   * @param typeOffers
   * @return
   */
  private String getOffers(String typeOffers) {
    OffersItf offersbasedOnType = OfferFactory.getOffersbasedOnType(typeOffers);
    return offersbasedOnType.getOffers();
  }

  /**
   * @param value
   * @param value2
   * @return
   * @return
   */
  private static String processPromocode(Object value, String word) {
    System.out.println(value.toString());
    return value.toString() + " " + word;
  }
}
