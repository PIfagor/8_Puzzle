import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board implements Comparable<Board>{
	/**
	 * конструюЇмо дошку у вигл€д≥ двовим≥рного масиву N на N
	 * 
	 * @param (blocks[i][j] =блок в р€ду i, колонц≥ j)
	 */
	private int[][] block;
	private int prewievStep = 0;
	private Board prew;
	private int N = 0;

	public Board(int[][] blocks, Board prew) {
		block = blocks;
		this.prew = prew;
		N = blocks.length;
	};

	public Board(int[][] blocks) {
		this(blocks, null);
	};

	private Board(int[][] blocks, Board prew, int i, int j, int deltaI,
			int deltaJ) {
		
		N = blocks.length;
		block = new int [N][N] ;
		for (int k = 0; k < N; k++) {
			for (int k2 = 0; k2 < N; k2++) {
				if (k==i && k2 == j) 
					block[k][k2] = blocks[i+ deltaI][j+ deltaJ];
				else if (k==i + deltaI && k2 == j + deltaJ) 
					block[k][k2] = blocks[i][j];
				else 
					block[k][k2] = blocks[k][k2];
			}
		}
		
		
		this.prew= prew;
		this.prewievStep = prew.prewievStep+1;
	}

	/** розм≥рн≥сть дошки N */
	public int dimension() {
		return block.length;
	};
	
	/** к≥льк≥сть блок≥в не на своЇму м≥сц≥ */
	public int hamming() {
		int result = 0;
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block.length; j++)
				if ((3 * i + j < block.length * block.length - 1)
						&& (block[i][j] != 3 * i + j + 1))
					result++;
		return result + prewievStep;

	};

	/** сума ћанхатенських в≥дстаней м≥ж блоками ≥ ц≥льовим станом */
	public int manhattan() {
		int result = 0;
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block.length; j++) {

				int temp = block[i][j];
				if (temp != 0) {
					int defaultI = (temp - 1) / 3;
					int defaultJ = temp - 1 - defaultI * 3;
					result += Math.abs(defaultI - i) + Math.abs(defaultJ - j);
				}

			}
		return result + prewievStep;
	};

	/** чи Ї ц€ дошка ц≥льовим станом */
	public boolean isGoal() {
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block.length; j++)
				if ((block[i][j] != 3 * i + j + 1) && (block[i][j] != 0))
					return false;
		return true;
	};

	/** чи ц€ дошка р≥вна y? */
	public boolean equals(Object y) {
		if (y instanceof Board) {
			Board temp = (Board) y;
			for (int i = 0; i < block.length; i++)
				for (int j = 0; j < block.length; j++)
					if (block[i][j] != temp.block[i][j])
						return false;
			return true;
		}
		return false;
	};

	/** вс≥ сусдн≥ дошки */
	public Iterable<Board> neighbors() {
		ArrayList<Board> result = new ArrayList<Board>();
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block.length; j++) {
				if (block[i][j] == 0) {
					if (i - 1 >= 0)
						result.add(new Board(block, this, i, j, -1, 0));
					if (i + 1 < N)
						result.add(new Board(block, this, i, j, 1, 0));
					if (j - 1 >= 0)
						result.add(new Board(block, this, i, j, 0, -1));
					if (j + 1 < N)
						result.add(new Board(block, this, i, j, 0, 1));
					break;
				}
			}
		}
		result.remove(prew);
		return result;
	};

	/** строкове поданн€ */
	public String toString() {
		String res = "";
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block.length; j++) {
				res += block[i][j];
			}
			res += "\n";
		}
		return res;
	}
	
	public int at (int i, int j)
	{
		return block[i][j];
	}
	public void incPrewStep()
	{
		prewievStep++;
	}
	
	@Override
	public int compareTo(Board in) {
		//return this.manhattan()-in.manhattan();
		return this.hamming()-in.hamming();
	};
}
