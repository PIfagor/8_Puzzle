import static org.junit.Assert.*;
 
import java.util.Random;
 
import org.junit.Before;
import org.junit.Test;
 

 
public class Tests {
	private Random r = new Random();
	@Before
	public void setUp() throws Exception {
	}
 
	@Test
	public void BoardEquality() {
 
		int[][] testBoard = new int[3][3];
		for (int i = 0; i < testBoard.length; ++i) {
			for (int j = 0; j < testBoard.length; ++j) {
				testBoard[i][j] = r.nextInt();
			}
		}
		Board b = new Board(testBoard);
		
		assertEquals(true, b.equals(b));
	}
	
	@Test
	public void BoardInEquality() {
 
		int[][] testBoard = new int[3][3];
		int[][] testBoard2 = new int[3][3];
		for (int i = 0; i < testBoard.length; ++i) {
			for (int j = 0; j < testBoard.length; ++j) {
				testBoard[i][j] = r.nextInt();
				testBoard2[i][j] = testBoard[i][j];
			}
		}
		testBoard2[0][0]++;
		Board b = new Board(testBoard);
		Board b2 = new Board(testBoard2);
				
		assertEquals(false, b.equals(b2));
		assertEquals(false, b.equals("string"));
	}
	
	@Test
	public void BoardDimension() {
		int[][] testBoard = new int[3][3];
		for (int i = 0; i < testBoard.length; ++i) {
			for (int j = 0; j < testBoard.length; ++j) {
				testBoard[i][j] = r.nextInt();
			}
		}
		Board b = new Board(testBoard);
		
		assertEquals(3, b.dimension());
	}
	
	@Test
	public void TestHamming() {
		int[][] testBoard = new int[][] {
											{8, 1, 3},
											{4, 0, 2},
											{7, 6, 5}
										};
		Board b = new Board(testBoard);
		assertEquals(5, b.hamming());
		
	}
	
	@Test
	public void TestManhattan() {
		int[][] testBoard = new int[][] {
				{8, 1, 3},
				{4, 0, 2},
				{7, 6, 5}
			};
		Board b = new Board(testBoard);
		assertEquals(10, b.manhattan());
	}
	
	@Test
	public void TestIsGoal() {
		int[][] testBoard = new int[][] {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
			};
		Board b = new Board(testBoard);
		assertEquals(true, b.isGoal());
		
		int[][] testBoard2 = new int[][] {
				{8, 1, 3},
				{4, 0, 2},
				{7, 6, 5}
			};
		Board b2 = new Board(testBoard2);
		assertEquals(false, b2.isGoal());
	}
	
	@Test
	public void TestToString() {
		int[][] testBoard = new int[][] {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
			};
		Board b = new Board(testBoard);
		String res = "123\n456\n780\n";
		assertEquals(res, b.toString());
	}
	
	
 
}