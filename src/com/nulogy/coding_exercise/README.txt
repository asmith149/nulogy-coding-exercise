Coding Exercise for Nulogy
By Andrew Smith
March 15th, 2016

Assumptions:
============ 

The following assumptions have been made regarding the coding exercise as defined in the provided 
document (see 'coding_excercise.txt').  Please also see javadoc and inline documentation when reviewing
code.

1) It is possible for zero people to work on a job (welcome to the age of automation!), but not 'negative' people
  (i.e. 'personCount' must be greater than or equal to zero).  Otherwise, assume 'personCount' is supplied as 
  an int as defined by Java.
2) For categories requiring a markup, the category name specified as input must match the exact name (case-
  sensitive) as defined in the problem statement (i.e. will assume all lower case: "food", "electronics") with the 
  exception of the 'pharmaceuticals' category which will accept either the name 'pharmaceuticals' or 'drugs'. 
  The reason there are two names mapping to the 'pharmaceuticals' category is because of 'Input 2' in the problem
  statement.  
3) 'category' cannot be null and cannot be an empty string, also assume there is only one category per project.
4) if non-null and not matching one of the markup categories as defined in assumption 2, it is assumed to be 
  a valid category that does not require a markup.
5) 'basePrice' must be non-null, greater than zero, and supplied as a BigDecimal object which will be rounded 
  up to two decimal places.
6) return value is a BigDecimal rounded up to two decimal places.