// IBookInterface.aidl
package aidl;

// Declare any non-default types here with import statements
import aidl.Book;
interface IBookInterface {
   List<Book> getBooks(int id);
}