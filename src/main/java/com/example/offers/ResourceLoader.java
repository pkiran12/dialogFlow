/**
 * @author I321593 created Jun 27, 2017
 */
package com.example.offers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * @author I321593
 *
 */
public class ResourceLoader {

  private static String facebookResources;

  private static String loadTemplateFromJson(InputStream file) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(file);
      scanner.useDelimiter("\\Z");
      String content = scanner.next();

      return content;
    } catch (Exception e) {
      System.out.println("Exception is e" + e.getMessage());
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
    return "";
  }

  /**
   * load the Resources from Products.json
   * 
   * @return
   */
  public static String getFacebookJsonTemplate(InputStream file) {

    if (facebookResources == null) {
      facebookResources = loadTemplateFromJson(file);
    }
    System.out.println("values of the json from file " + facebookResources);
    return facebookResources;
  }

  public static String getJson(InputStream file) {
    getFacebookJsonTemplate(file);
    Gson gson = new Gson();
    Map fromJson = gson.fromJson(facebookResources, Map.class);
    String json = gson.toJson(fromJson);
    return json;

  }

}
