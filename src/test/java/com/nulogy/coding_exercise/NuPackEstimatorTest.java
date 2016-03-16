package test.java.com.nulogy.coding_exercise;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import main.java.com.nulogy.coding_exercise.BasePriceMustBePositiveException;
import main.java.com.nulogy.coding_exercise.NegativePersonCountException;
import main.java.com.nulogy.coding_exercise.NuPackEstimator;
import main.java.com.nulogy.coding_exercise.ProductCategoryNotSpecifiedException;

public class NuPackEstimatorTest {

	/* negative test cases - base price
	 *   1) negative base price (assume must be positive)
	 *   2) valid person count 
	 *   3) valid category (assume a product can only be in one category) 
	 */
	@Test(expected = BasePriceMustBePositiveException.class)
	public void testNegativeBasePrice() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("-100"), 1, "food");
	    estimator.estimateFinalCost();
	}

	/* negative test cases - base price
	 *   1) zero base price (assume must be positive)
	 *   2) valid person count 
	 *   3) valid category (assume a product can only be in one category) 
	 */
	@Test(expected = BasePriceMustBePositiveException.class)
	public void testZeroBasePrice() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("0"), 1, "food");
	    estimator.estimateFinalCost();
	}	

	/* negative test cases - base price
	 *   1) null base price (assume must be positive)
	 *   2) valid person count 
	 *   3) valid category (assume a product can only be in one category) 
	 */
	@Test(expected = BasePriceMustBePositiveException.class)
	public void testNullBasePrice() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(null, 1, "food");
	    estimator.estimateFinalCost();
	}		
	
	/* negative test cases - negative person count
	 *   1) valid base price (assume must be positive)
	 *   2) negative person count 
	 *   3) valid category (assume a product can only be in one category) 
	 */
	@Test(expected = NegativePersonCountException.class)
	public void testNegativePersonCount() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), -1, "food");
	    estimator.estimateFinalCost();
	}	

	/* negative test cases - no category specified
	 *   1) valid base price (assume must be positive)
	 *   2) valid person count 
	 *   3) null category (assume a product can only be in one category) 
	 */
	@Test(expected = ProductCategoryNotSpecifiedException.class)
	public void testNullCategory() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 1, null);
	    estimator.estimateFinalCost();
	}
	
	/* negative test cases - no category specified
	 *   1) valid base price (assume must be positive)
	 *   2) valid person count 
	 *   3) null category (assume a product can only be in one category) 
	 */
	@Test(expected = ProductCategoryNotSpecifiedException.class)
	public void testEmptyCategory() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 1, "");
	    estimator.estimateFinalCost();
	}
	
	/* negative test cases - invalid category  - 
	 *   assume a product can only be in one category: pharmaceutical, food, electronics, other
	 *   after assessing the requirements, we will assume that the category is specified from a list of selections
	 *   this ensures no typos if the end user were to type in the category
	 *   this also allows us to check for the exact strings "pharmaceutical", "food", "electronics"
	 *   and accept any other input, as valid and requiring no markup
	 *   Thus, no invalid category test is required as there is assumed to be no invalid category that will
	 *   be provided as input
	 */
	
	/* positive test cases - flat markup only
	 *   1) valid base price (assume must be positive)
	 *   2) no person markup (assume zero people working is possible!)
	 *   3) no markup for category (can be any non-null string and we assume to be valid)
	 */
	@Test
	public void testFlatMarkupOnly() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 0, "books");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("105.00"));
	}
	
	/* positive test cases - flat markup plus person markup, one person
	 *   1) valid base price (assume must be positive)
	 *   2) one person
	 *   3) no markup for category (can be any non-null string and we assume to be valid)
	 */
	@Test
	public void testFlatMarkupAndOnePersonMarkup() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 1, "books");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("106.26"));
	}
	
	/* positive test cases - flat markup plus multi person markup
	 *   1) valid base price (assume must be positive)
	 *   2) more than one people working on it
	 *   3) no markup for category (can be any non-null string and we assume to be valid)
	 */
	@Test
	public void testFlatMarkupAndMultiPersonMarkup() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 5, "books");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("111.30"));
	}	
	
	/* positive test cases - flat markup plus multi person markup, plus category markup
	 *   1) valid base price (assume must be positive)
	 *   2) more than one people working on it
	 *   3) markup for category 
	 */
	@Test
	public void testAllMarkupsPharm() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 5, "pharmaceuticals");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("119.18"));
	}
	
	/* positive test cases - flat markup plus multi person markup, plus category markup
	 *   1) valid base price (assume must be positive)
	 *   2) more than one people working on it
	 *   3) markup for category 
	 */
	@Test
	public void testAllMarkupsFood() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 5, "food");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("124.95"));
	}
	
	/* positive test cases - flat markup plus multi person markup, plus category markup
	 *   1) valid base price (assume must be positive)
	 *   2) more than one people working on it
	 *   3) markup for category 
	 */
	@Test
	public void testAllMarkupsElectronics() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 5, "electronics");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("113.40"));
	}
	
	/* positive test cases - no person markup
	 *   1) valid base price (assume must be positive)
	 *   2) no people working on it
	 *   3) markup for category 
	 */
	@Test
	public void testNoPersonMarkup() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("100"), 0, "electronics");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("107.10"));
	}
	
	// test 1 from problem statement
	@Test
	public void testInput1() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("1299.99"), 3, "food");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("1591.58"));
	}	
	
	// test 2 from problem statement
	@Test
	public void testInput2() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("5432.00"), 1, "drugs");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("6199.81"));
	}
	
	// test 3 from problem statement
	@Test
	public void testInput3() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("12456.95"), 4, "books");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("13707.63"));
	}
	
	// large base price test
	@Test
	public void testLargeBasePrice() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("10000000"), 1, "drugs");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("11413500.00"));
	}
	
	// large number of people working on the project
	@Test
	public void testLargeNumberOfPeople() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("150"), 1000, "drugs");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("2059.31"));
	}
	
	// large number of people working on the project and large base price
	@Test
	public void testLargeNumberOfPeopleAndLargeBasePrice() throws BasePriceMustBePositiveException, 
	  NegativePersonCountException, ProductCategoryNotSpecifiedException {
		NuPackEstimator estimator = new NuPackEstimator(new BigDecimal("150000"), 2000, "drugs");
	    assertEquals(estimator.estimateFinalCost(), new BigDecimal("3949312.50"));
	}
}
