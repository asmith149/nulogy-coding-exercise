package com.nulogy.coding_exercise;

import java.math.BigDecimal;

/*
 * NuPackEstimator class
 * 
 * calculates the total cost of a project by applying markups (see 'coding_exercise.txt')
 */
public class NuPackEstimator {

	private BigDecimal basePrice;
	private int personCount;
	private String category;
	
	private static final BigDecimal FLAT_MARKUP = new BigDecimal("0.05");
	private static final double PERSON_MARKUP = 0.012;
	
	private static final double PHARM_MARKUP = 0.075;
	private static final double FOOD_MARKUP = 0.13;
    private static final double ELECTRONICS_MARKUP = 0.02;
	private static final String PHARMACEUTICALS = "pharmaceuticals";
	private static final String DRUGS = "drugs";
	private static final String FOOD = "food";
	private static final String ELECTRONICS = "electronics";
	  
	public NuPackEstimator(BigDecimal basePrice, int personCount, String category) {
		//round up to two decimal places
		if (basePrice != null) {
			this.basePrice = basePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.personCount = personCount;
		this.category = category;
	}

	/*
	 * provides an estimate of the final cost of a project given a base price, number of people working on 
	 * the project, and a category for the goods in the project.
	 * 
	 * @throws BasePriceMustBePositiveException if this.basePrice is not a positive value
	 * @throws NegativePersonCountException if this.personCount is less than zero
	 * @throws ProductCategoryNotSpecifiedException if this.category is not specified
	 * 
	 * @return	the estimated cost for the project
	 */
	public BigDecimal estimateFinalCost() throws BasePriceMustBePositiveException,
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		BigDecimal finalCost;
		BigDecimal afterFlatMarkup;
		BigDecimal basePrice = this.basePrice;
		String category = this.category;
		int personCount = this.personCount;
		double percentAdditionalMarkup = 0;
		
		//Ensure base price is greater than zero
		if (basePrice == null || basePrice.compareTo(BigDecimal.ZERO) < 1) {
		  throw new BasePriceMustBePositiveException();	
		}
		
		//must be a category specified
		if ((category == null) || (category.equals(""))){
			throw new ProductCategoryNotSpecifiedException();
		}
		
		//person count must not be negative
		if (personCount < 0) {
		  throw new NegativePersonCountException();
		}

		//calculate the flat markup
		afterFlatMarkup = basePrice.add(basePrice.multiply(FLAT_MARKUP));
		 
		//calculate the person markup
		if (personCount > 0) {
			percentAdditionalMarkup = PERSON_MARKUP * personCount;
		}
		
	    percentAdditionalMarkup = percentAdditionalMarkup + getCategoryMarkup(category);
	    
	    if (percentAdditionalMarkup > 0) {
		  finalCost = afterFlatMarkup.add(afterFlatMarkup.multiply(BigDecimal.valueOf(percentAdditionalMarkup)));
	    }
		else {
	      finalCost	= afterFlatMarkup;
		}
		
		return finalCost.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	//just factored this out after testing to clean up the code a bit, assumes non-null 'name'
	private double getCategoryMarkup (String name) {
	  double categoryMarkup = 0;
	  
      //assume case sensitive category names
	  if ((name.equals(PHARMACEUTICALS)) || (name.equals(DRUGS))) {
		  categoryMarkup = PHARM_MARKUP;
	  }
	  else if (name.equals(FOOD)) {
		  categoryMarkup = FOOD_MARKUP;
	  }
	  else if (name.equals(ELECTRONICS)) {
		  categoryMarkup = ELECTRONICS_MARKUP;
	  }	
	  
	  return categoryMarkup;
	}
	
}
