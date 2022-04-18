package cmsc256;

public class MyBook implements Comparable<MyBook>{

   private String title;
   private String authorFirstName;
   private String authorLastName;
   private String ISBN10;
   private String ISBN13;

   public MyBook() {
      title = "None Given";
      authorFirstName = "None Given";
      authorLastName = "None Given";
      ISBN10 = "0000000000";
      ISBN13 = "0000000000000";
   }

   public MyBook(String aTitle, String aAuthorFirstName, String aAuthorLastName, String aISBN10, String aISBN13) {
      title = aTitle;
      authorFirstName = aAuthorFirstName;
      authorLastName = aAuthorLastName;
      ISBN10 = aISBN10;
      ISBN13 = aISBN13;
   }

   /**
    * Getters and Setters
    */
   public String getTitle() {
      if (title == null) {
         throw new IllegalArgumentException();
      }
      return title;
   }

   public void setTitle(String input) {
      if (input == null) {
         throw new IllegalArgumentException();
      }
      this.title = input;
   }

   public String getAuthorFirstName() {
      if (authorFirstName == null) {
         throw new IllegalArgumentException();
      }
      return authorFirstName;
   }

   public void setAuthorFirstName(String input) {
      if (input == null) {
         throw new IllegalArgumentException();
      }
      this.authorFirstName = input;
   }

   public String getAuthorLastName() {
      if (authorLastName == null) {
         throw new IllegalArgumentException();
      }
      return authorLastName;
   }

   public void setAuthorLastName(String input) {
      if (input == null) {
         throw new IllegalArgumentException();
      }
      this.authorLastName = input;
   }

   public String getISBN10() {
      if (ISBN10 == null) {
         throw new IllegalArgumentException();
      }
      return ISBN10;
   }

   public void setISBN10(String input) throws IllegalArgumentException {
      if (input.length() != 10) {
         throw new IllegalArgumentException();
      }
      //Checking if each character is a digit
      for(int i = 0; i < input.length(); i++) {
         boolean result = Character.isDigit(input.charAt(i));
         if(result == false){
            throw new IllegalArgumentException();
         }
      }
      this.ISBN10 = input;
   }

   public String getISBN13() {
      if (ISBN13 == null) {
         throw new IllegalArgumentException();
      }
      return ISBN13;
   }

   public void setISBN13(String input) throws IllegalArgumentException{
      if (input.length() != 13) {
         throw new IllegalArgumentException();
      }
      for(int i = 0; i < input.length(); i++) {
         boolean result = Character.isDigit(input.charAt(i));
         if(result == false){
            throw new IllegalArgumentException();
         }
      }
      this.ISBN13 = input;
   }

   /**
    * toString Method to Print it out
    * @return
    */
   public String toString() {
      return "Title: " + getTitle() + "\nAuthor: " + getAuthorFirstName() + " " + getAuthorLastName() + "\nISBN10: " + getISBN10() + "\nISBN13: " + getISBN13();
   }
   /**
    * Methods
    */
   public boolean equals(Object otherBook) {
      if (this == otherBook)
         return true;
      if(!(otherBook instanceof MyBook))
         return false;
   
      MyBook other = (MyBook)otherBook;
      return true;
   }
   public int compareTo(MyBook other){
      int result = authorLastName.compareTo(other.getAuthorLastName());
   
      if(result == 0){
         result = authorFirstName.compareTo(other.getAuthorFirstName());
      }
      if(result == 0){
         result = title.compareTo(other.getTitle());
      }
      return result;
   
   }
}