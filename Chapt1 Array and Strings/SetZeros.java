/**
Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
column are set to 0.
*/

/**
At first glance, this problem seems easy: just iterate through the matrix and every time
we see a cell with value zero, set its row and column to 0.There's one problem with that
solution though: when we come across other cells in that row or column, we'll see the
zeros and change their row and column to zero. Pretty soon, our entire matrix will be
set to zeros.

One way around this is to keep a second matrix which flags the zero locations. We would
then do a second pass through the matrix to set the zeros. This would take 0 (MN) space.

Do we really need 0(MN) space? No. Since we're going to set the entire row and column
to zero, we don't need to track that it was exactly cell[2] [4] (row 2, column 4). We
only need to know that row 2 has a zero somewhere, and column 4 has a zero somewhere.
We'll set the entire row and column to zero anyway, so why would we care to
keep track of the exact location of the zero?

The code below implements this algorithm. We use two arrays to keep track of all the
rows with zeros and all the columns with zeros. We then make a second pass of the
matrix and set a cell to zero if its row or column is zero.
*/

public void setZeros(int[][] matrix){
	boolean[] row = new boolean[matrix.length];
	boolean[] colum = new boolean[matrix[0].length];

	//Store the row and column index with value 0
	for(int i = 0; i < matrix.length; i++){
		for(int j = 0; j < matrix[0].length; j++){
			if(matrix[i][j] == 0){
				row[i] = true;
				column[j] = true;
			}
		}
	}

	//set arr[i][j] to 0 if either row i or column j has a 0
	for(int i = 0; i < matrix.length; i++){
		for(int j = 0; j < matrix[0].length; j++){
			if(row[i]||column[j]){
				matrix[i][j] = 0;
			}
		}
	}
}


//To make this somewhat more space efficient, we could use a bit vector instead of a
//boolean array.