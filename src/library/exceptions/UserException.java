/**
 * This class can be used whenever an error occurs with the usage of User objects
 */
package library.exceptions;

public class UserException extends Exception{

    /**
     * The constructor of the exception
	 * @param message The message of this error
     */
    public UserException(String message){
        super(message);
    }
}