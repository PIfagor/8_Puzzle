import java.util.ArrayList;

import lib.princeton.In;
import lib.princeton.StdOut;

public class Solver {
	private ArrayList<Board> result;
	private Board init;
	/** ������ ������ ��� ����� initial */
	public Solver(Board initial) {
		init = initial;
		if (isSolvable())
		{
			
			result = new ArrayList<Board>();
			while (!init.isGoal()) {
				result.add(init);
				MinPQ<Board> mp = new MinPQ<Board>();
				ArrayList<Board> tmp = (ArrayList<Board>) init.neighbors();
				for (Board board : tmp) {
					mp.insert(board);
				}
			
				init = mp.delMin();
				
			}
			result.add(init);
		}

	};

	/** �� �� ��������� ����� �������� */
	public boolean isSolvable() {

		int N = init.dimension();
		int[] horizontal = new int[N * N];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				horizontal[i * N + j] = init.at(i, j);
			}
		}

		int ammount = 0;
		for (int i = 0; i < N * N; ++i) {
			int invertions = 0;
			for (int j = i + 1; j < N * N; ++j) {
				if (horizontal[i] < horizontal[j] && horizontal[j] != 0
						&& horizontal[i] != 0) {
					++invertions;
				}
			}
			ammount += invertions;
		}
		return ammount % 2 == 0;
	};

	/** �������� ������� ����� ��� �������� �����, -1 ���� ���� ������ */
	public int moves() {
		return result.size();
	};

	/** ����������� ����� � ������������ �����; null ���� ���� ������ */
	public Iterable<Board> solution() {
		return result;
	};

	/** ������� */
	public static void main(String[] args) {
		// ��������� ��������� ����� � �����
		// In in = new In(args[0]);
		// int N = in.readInt();
		// int[][] blocks = new int[N][N];
		// for (int i = 0; i < N; i++)
		// for (int j = 0; j < N; j++)
		// blocks[i][j] = in.readInt();
		int N = 3;
		// int[][] blocks = new int[][]{{1,2,3},{4,5,6},{8,7,0}};
		// int[][] blocks = new int[][]{{0,1,3},{4,2,5},{7,8,6}};
		 int[][] blocks = new int[][] { { 8, 6, 7 }, { 2, 5, 4 }, { 3, 0, 1 } };
		ArrayList<Board> result = new ArrayList<>();
		// result.
		Board initial = new Board(blocks);

		// ���������
		Solver solver = new Solver(initial);

		// ����������� ������
		if (!solver.isSolvable())
			StdOut.println("����� �� �� �������� / Board can`t be slovable");
		else {
			StdOut.println("Min amount  steps  = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
