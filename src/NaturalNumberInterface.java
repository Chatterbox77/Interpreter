/**
 * ADT for the class NaturalNumber
 * 
 * @author Andrey Horban,Piotr Zaitev
 * @elements chars of the type char
 * @structure none
 * @domain characters in range [0,inf),no leading zeros allowed
 * @constructor
 *
 *              NaturalNumber(NaturalNumber src);
 *              <dl>
 *              Creates the new instance of the NaturalNumber which is the copy
 *              of the src
 *              <dt><b>@PRE-condition</b>
 *              <dd>-
 *              <dt><b>@POST-condition</b>
 *              <dd>The new NaturalNumber-object contains a copy of the src.
 *              </dl>
 *              NaturalNumber();
 *              <dl>
 *              Creates the new instance of the NaturalNumber with the default
 *              value==0
 *              <dt><b>@PRE-condition</b>
 *              <dd>-
 *              <dt><b>@POST-condition</b>
 *              <dd>The new NaturalNumber-object contains a the value 0
 *              </dl>
 **/
public interface NaturalNumberInterface extends Data<NaturalNumberInterface> {
	/**
	 * convert the object of the type NaturalNumber to String
	 * 
	 * @precondition -
	 * @postcondition String representation of the given NaturalNumber
	 **/
	String toString();

	/**
	 * appends new element to the NatrualNumber
	 * 
	 * @throws APException
	 *             if the Character that is adding is a negative number,or if it
	 *             is not a digit,or if input value is the value with leading zeros
	 * @throws APException
	 * 			   if the number with leading zeros 
	 * @precondition -
	 * @postcondition the new element was added to the NaturalNumber
	 **/

	void append(char c) throws APException;


	/**
	 * Check the equality between to objects of the type Identifier
	 * 
	 * @precondition: -
	 * @postcondition: true: two object have the same content false: otherwise
	 **/
	boolean equals(NaturalNumberInterface n);



}
