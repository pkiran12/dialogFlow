/**
 * @author I321593 created Jun 27, 2017
 */
package com.examples.services.movies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author I321593
 *
 */
public class BMSMovieOffers implements MovieOffersItf {

  /**
   * 
   */
  private static final String BMS_SITE = "https://in.bookmyshow.com";

  private MovieOffersItf movieOffersItf;

  private Map<String, StringBuilder> offersMap;

  /*
   * (non-Javadoc)
   * 
   * @see com.examples.services.movies.MovieOffersItf#getMovieOffers(java.lang.StringBuilder)
   */
  @Override
  public void getMovieOffers(StringBuilder builderOffer) {

    try {

      offersMap = new HashMap<>();

      Document document = Jsoup.connect(BMS_SITE + "/offers")
          .get();

      Elements elementById = document.getElementsByClass("list");

      elementById.forEach(element -> {

        Elements elementsByTag = element.getElementsByTag("aside");

        elementsByTag.forEach(elemTag -> {

          String offersOn = elemTag.attr("data-show");

          StringBuilder builder;

          if (offersMap.containsKey(offersOn)) {

            builder = offersMap.get(offersOn);

          } else {

            builder = new StringBuilder();

            offersMap.put(offersOn, builder);
          }

          Element first = elemTag.select("a")
              .first();

          String attr = first.attr("href");

          Elements offerNameTag = elemTag.getElementsByTag("h4");

          Elements abt = elemTag.getElementsByClass("abt");

          Elements date = elemTag.getElementsByClass("valid-till-date");
          builder.append("\n");
          builder.append(abt.text());
          builder.append("\n");
          builder.append(offerNameTag.text() + " " + date.text());
          builder.append("\n");
          builder.append(BMS_SITE + attr);
          builder.append("\n");

        });

      });
      builderOffer.append(offersMap.get("")
          .toString());
    } catch (IOException e) {
      System.out.println(e);
    }
    movieOffersItf.getMovieOffers(builderOffer);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.examples.services.movies.MovieOffersItf#setNextInChain(com.examples.services.movies.
   * MovieOffersItf)
   */
  @Override
  public void setNextInChain(MovieOffersItf movieOffersItf) {
    this.movieOffersItf = movieOffersItf;
  }

}
