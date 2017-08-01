/**
 * ADT for the class Identifier
 * 
 * @author Andrey Horban,Piotr Zaitev
 * @elements chars of the type char
 * @structure linear
 * @domain all alphanumeric combinations which starts with a letter
 * @constructor
 *
 * Identifier(char c);
 *	    <dl>Creates the new instance of the Identifier with stores the copy of the c
 *		@throw APException if c is not alphanumeric
 *		
 *		<dt><b>@PRE-condition</b><dd>-
 *
 *		<dt><b>@POST-condition</b><dd>
 *		The new Identifier object was created out of the c
 *	    </dl>
 *	<br>
 * Identifier(Identifier src);
 *	    <dl>Creates the new instance of the Identifier which is the copy of the src
 *		<dt><b>@PRE-condition</b><dd>-
 *		<dt><b>@POST-condition</b><dd>The new
 *		Identifier-object contains a copy of the src.
 *	    </dl>
 **/
public interface IdentifierInterface extends Data<IdentifierInterface> {
	
	/**
	 * appends new element to the Identifier
	 * @throws APException if c is not alphanumeric,
	 * @precondition -
	 * @postcondition the new element was added to the Identifier 
	 **/
	
	
	
	void append(char c) throws APException;
	/** 
	 * returns the char on the specified index position
	 * @throws APException if index is out of the Identifier bounds
	 * @precondition -
	 * @postcondition char on the specified index position
	 **/
	
	char charAt(int index) throws APException;
	/**
	 * convert the object of the type Identifier to String
	 * @throws APException if the index is bigger then the number of chars in the StringBuffer object
	 * @precondition 
	 * -
	 * @postcondition 
	 * String representation of the given Identifier
	 **/
	String toString();


	/**
	 * Check the equality between to objects of the type Identifier

	 * 
	 * @precondition:
	 *  -
	 * @postcondition: 
	 * true: two object have the same content
	 * false: otherwise
	 **/
	 boolean equals(IdentifierInterface i);

	
	


}
