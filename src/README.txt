Command Line Interpreter 
program = { statement } <eof> ;
statement = assignment | print_statement | comment ;
assignment = identifier '=' expression <eoln> ;
print_statement = '?' expression <eoln> ;
comment = '/' <a line of text> <eoln> ;
identifier = letter { letter | number } ;
expression = term { additive_operator term } ;
term = factor { multiplicative_operator factor } ;
factor = identifier | complex_factor | set ;
complex_factor = '(' expression ')' ;
set = '{' row_natural_numbers '}' ;
row_natural_numbers = [ natural_number { ',' natural_number } ] ;
additive_operator = '+' | '|' | '−' ;
multiplicative_operator = '*' ;
natural_number = positive_number | zero ;
positive_number = not_zero { number } ;
number = zero | not_zero ;
zero = '0' ;
not_zero = '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' ;
letter = 'A'|'B'|'C'|'D'|'E'|'F'|'G'|'H'|'I'|'J'|'K'|'L'|'M'| 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g' | 'h' | 'i' | 'j' | 'k' | 'l' | 'm' |
'n' | 'o' | 'p' | 'q' | 'r' | 's' | 't' | 'u' | 'v' | 'w' | 'x' | 'y' | 'z' ;
Examples:

a={1,2,3}
?a+{4,5,6,7}
//1 2 3 4 5 6 7 
b={5,6,7}
c=a*b+({8,9}+{8})
?c
//8 9 
d=a+b+(c-{9})
?d
//1 2 3 5 6 7 8 
etc.