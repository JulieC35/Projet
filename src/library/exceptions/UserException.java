/**
 * This class can be used whenever an error occurs with the usage of User class
 */

package library.exceptions;

public class UserException extends Exception{
    public UserException(String message){
        super(message);
    }
}