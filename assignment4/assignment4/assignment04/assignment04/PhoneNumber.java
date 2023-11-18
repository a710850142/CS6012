package assignment04;

import java.util.Comparator;

/**
 * Represents a phone number with separate area code, trunk, and rest components.
 * Provides methods to validate, compare, and format phone numbers.
 */
public class PhoneNumber {

  // Area code part of the phone number
  private String areaCode;

  // Trunk part of the phone number
  private String trunk;

  // Remaining part of the phone number
  private String rest;

  /**
   * Constructor that processes and sets up the phone number.
   * Removes non-digit characters and validates the length.
   * If invalid, initializes with default values.
   *
   * @param phoneNum the raw phone number string
   */
  public PhoneNumber(String phoneNum) {
    // Remove non-digit characters
    phoneNum = phoneNum.replaceAll("-|\\s|\\.|\\(|\\)", "");

    // Check if phone number is valid
    boolean isValid = phoneNum.length() == 10;
    for (int i = 0; isValid && i < 10; i++)
      if (!Character.isDigit(phoneNum.charAt(i)))
        isValid = false;

    // Set phone number components if valid, else use default values
    if (isValid) {
      areaCode = phoneNum.substring(0, 3);
      trunk = phoneNum.substring(3, 6);
      rest = phoneNum.substring(6, 10);
    } else {
      areaCode = "000";
      trunk = "000";
      rest = "0000";
      System.err
              .println("Phone number \"" + phoneNum + "\" is not formatted correctly, initializing as " + toString() + ".");
    }
  }

  /**
   * Checks for equality between this and another PhoneNumber object.
   *
   * @param other another object to compare with
   * @return true if both objects are PhoneNumbers with same components
   */
  public boolean equals(Object other) {
    if (!(other instanceof PhoneNumber))
      return false;

    PhoneNumber rhs = (PhoneNumber) other;

    return areaCode.equals(rhs.areaCode) && trunk.equals(rhs.trunk) && rest.equals(rhs.rest);
  }

  /**
   * Provides a string representation of the phone number.
   *
   * @return formatted phone number
   */
  public String toString() {
    return "(" + areaCode + ") " + trunk + "-" + rest;
  }

  /**
   * Generates a hash code for the phone number.
   *
   * @return hash code based on the phone number components
   */
  @Override
  public int hashCode() {
    return areaCode.hashCode() + trunk.hashCode() + rest.hashCode();
  }

  /**
   * Comparator for ordering PhoneNumber objects.
   * Compares area code, trunk, and rest in order.
   */
  protected static class OrderByNumber implements
          Comparator<PhoneNumber> {

    /**
     * Compares two PhoneNumber objects for ordering.
     *
     * @param lhs the left-hand side PhoneNumber object
     * @param rhs the right-hand side PhoneNumber object
     * @return negative if lhs is smaller, positive if larger, 0 if equal
     */
    public int compare(PhoneNumber lhs,
                       PhoneNumber rhs) {
      int areaCodeCompare = lhs.areaCode.compareTo(rhs.areaCode);
      if (areaCodeCompare != 0) {
        return areaCodeCompare;
      }

      int trunkCompare = lhs.trunk.compareTo(rhs.trunk);
      if (trunkCompare != 0){
        return trunkCompare;
      }

      return lhs.rest.compareTo(rhs.rest);
    }
  }
}
