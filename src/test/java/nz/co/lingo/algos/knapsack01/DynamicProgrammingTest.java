package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.DynamicProgramming;
import nz.co.lingo.algos.knapsack01.examples.shopping.Product;
import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;
import nz.co.lingo.algos.knapsack01.examples.shopping.Tote;

import org.junit.BeforeClass;
import org.junit.Test;


public class DynamicProgrammingTest {
	static DynamicProgramming dp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dp = new DynamicProgramming();
	}
	
	/**
	  Verified test results using manual calculations

	0	1	2	3	4	5
0	0,0	0,0	0,0	0,0	0,0	0,0
1	0,0	0,0	0,0	0,0	0,0	0,0
2	0,0	0,0	0,0	0,0	0,0	0,0
3	0,0	0,0	0,0	0,0	0,0	0,0
4	0,0	0,0	0,0	0,0	0,0	2215;547
5	0,0	0,0	0,0	0,0	2080;1417	2215;547
6	0,0	0,0	0,0	2509;2078	2509;2078	2509;2078
7	0,0	0,0	1651;1160	2509;2078	2509;2078	4160;3238

	 */
	@Test
	public void testMaximizeMyShopping01() {
		Tote t = new Tote(5, 1, 1); // volume: 5
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(31288, 1990, 1, 1, 8, 1584); // volume: 8 
		Product p2 = new Product(14301, 2139, 1, 1, 7, 804); // volume: 7
		Product p3 = new Product(39987, 2229, 1, 1, 6, 1647); // volume: 6
		Product p4 = new Product(26950, 2215, 1, 1, 5, 547); // volume: 5
		Product p5 = new Product(10496, 2080, 1, 1, 4, 1417); // volume: 4
		Product p6 = new Product(17788, 2509, 1, 1, 3, 2078); // volume: 3
		Product p7 = new Product(5084, 1651, 1, 1, 2, 1160); // volume: 2

		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		products.add(p6);
		products.add(p7);

		dp.doSolve(products, t);
		assertEquals(TestUtil.setOfItems(p6,p7), t.getProducts().collect(Collectors.toSet()));
	}

	/**
	  Verified test results using manual calculations

	0	1	2	3	4	5	6	7	8	9	10
0	0;0	0;0	0;0	0;0	0;0	0;0	0;0	0;0	0;0	0;0	0;0
1	0;0	0;0	0;0	0;0	0;0	10;10	10;10	10;10	10;10	10;10	10;10
2	0;0	0;0	0;0	0;0	40;10	40;10	40;10	40;10	40;10	50;20	50;20
3	0;0	0;0	0;0	0;0	40;10	40;10	40;10	40;10	40;10	50;20	70;20
4	0;0	0;0	0;0	50;10	50;10	50;10	50;10	90;20	90;20	90;20	90;20

	 */
	@Test
	public void testMaximizeMyShopping02() {
		Tote t = new Tote(5, 1, 2); // volume: 10
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(1, 10, 1, 1, 5, 10); // volume: 5 
		Product p2 = new Product(2, 40, 1, 1, 4, 10); // volume: 4
		Product p3 = new Product(3, 30, 1, 1, 6, 10); // volume: 6
		Product p4 = new Product(4, 50, 1, 1, 3, 10); // volume: 3

		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);

		dp.doSolve(products, t);
		assertEquals(TestUtil.setOfItems(p2,p4), t.getProducts().collect(Collectors.toSet()));
	}

	/**
	 * Test the weight tie breaker
	 */
	@Test
	public void testMaximizeMyShopping03() {
		Tote t = new Tote(5, 1, 2); // volume: 10
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(1, 5, 1, 1, 10, 5); // volume: 10 
		Product p2 = new Product(2, 5, 1, 1, 10, 6); // volume: 10

		products.add(p1);
		products.add(p2);

		dp.doSolve(products, t);
		assertEquals(TestUtil.setOfItems(p1), t.getProducts().collect(Collectors.toSet()));
		
		t.removeAllProducts();
		products.clear();
		products.add(p2);
		products.add(p1);
		
		dp.doSolve(products, t);
		assertEquals(TestUtil.setOfItems(p1), t.getProducts().collect(Collectors.toSet()));
	}

	/**
	 * Test using data set and expected results from https://rosettacode.org/wiki/Knapsack_problem/0-1
	 */
	@Test
	public void testMaximizeMyShopping04() {
		Tote t = new Tote(10, 10, 4); // volume: 400
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(1, 150, 1, 1, 9, 10); 
		Product p2 = new Product(2, 35, 1, 1, 13, 10); 
		Product p3 = new Product(3, 200, 1, 1, 153, 10); 
		Product p4 = new Product(4, 160, 1, 1, 50, 10); 
		Product p5 = new Product(5, 60, 1, 1, 15, 10); 
		Product p6 = new Product(6, 45, 1, 1, 68, 10); 
		Product p7 = new Product(7, 60, 1, 1, 27, 10); 
		Product p8 = new Product(8, 40, 1, 1, 39, 10); 
		Product p9 = new Product(9, 30, 1, 1, 23, 10); 
		Product p10 = new Product(10, 10, 1, 1, 52, 10); 
		Product p11 = new Product(11, 70, 1, 1, 11, 10); 
		Product p12 = new Product(12, 30, 1, 1, 32, 10); 
		Product p13 = new Product(13, 15, 1, 1, 24, 10); 
		Product p14 = new Product(14, 10, 1, 1, 48, 10); 
		Product p15 = new Product(15, 40, 1, 1, 73, 10); 
		Product p16 = new Product(16, 70, 1, 1, 42, 10); 
		Product p17 = new Product(17, 75, 1, 1, 43, 10); 
		Product p18 = new Product(18, 80, 1, 1, 22, 10); 
		Product p19 = new Product(19, 20, 1, 1, 7, 10); 
		Product p20 = new Product(20, 12, 1, 1, 18, 10); 
		Product p21 = new Product(21, 50, 1, 1, 4, 10); 
		Product p22 = new Product(22, 10, 1, 1, 30, 10); 

		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		products.add(p6);
		products.add(p7);
		products.add(p8);
		products.add(p9);
		products.add(p10);
		products.add(p11);
		products.add(p12);
		products.add(p13);
		products.add(p14);
		products.add(p15);
		products.add(p16);
		products.add(p17);
		products.add(p18);
		products.add(p19);
		products.add(p20);
		products.add(p21);
		products.add(p22);

		dp.doSolve(products, t);
		assertEquals(TestUtil.setOfItems(p1,p2,p3,p4,p5,p7,p11,p16,p17,p18,p19,p21), t.getProducts().collect(Collectors.toSet()));
	}

	/**
	 * Test with no products
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testMaximizeMyShopping05() {
		List<Product> products = new ArrayList<>();
		Tote t = new Tote(1, 10, 1); // volume: 10

		dp.doSolve(products, t);
		assertEquals(Collections.EMPTY_SET, t.getProducts().collect(Collectors.toSet()));
	}

	/**
	 * Test with no products and just 1 product
	 */
	@Test
	public void testMaximizeMyShopping06() {
		List<Product> products = new ArrayList<>();
		Tote t = new Tote(1, 10, 1); // volume: 10

		dp.solve(products, t);
		assertEquals(Collections.EMPTY_SET, t.getProducts().collect(Collectors.toSet()));
	
		Product p1 = new Product(1, 20, 5, 2, 1, 1); // volume: 10
		products.add(p1);
		t.removeAllProducts();
		dp.doSolve(products, t);
		assertEquals(TestUtil.setOfItems(p1), t.getProducts().collect(Collectors.toSet()));
	}
}
