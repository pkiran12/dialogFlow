/**
 * @author I321593 created Jun 20, 2017
 */
package com.example.offers;

/**
 * @author I321593
 *
 */
public class WebhookResponse {

  private final String speech;

  private final String displayText;

  private final String data;

  private final String source = "java-webhook";

  public WebhookResponse(String speech, String displayText, String data) {
    this.speech = speech;
    this.displayText = displayText;
    this.data = data;
  }

  public String getSpeech() {
    return speech;
  }

  public String getDisplayText() {
    return displayText;
  }

  public String getSource() {
    return source;
  }

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

}
