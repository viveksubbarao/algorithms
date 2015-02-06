/****************************************************************************
 *  @author Vivek Subbarao
 *  Date: 01/27/2015
 *  Compilation:  javac Percolation.java
 *  Execution:  java Percolation
 *  Dependencies: WeightedQuickUnionUF.java StdOut.java
 *
 *  Percolation class implementing the monte carlo simulation.
 *
 ****************************************************************************/

/**
 *  The <tt>Percolation</tt> class implements the monte carlo simulation to
 *  find whether a system of N * N nodes percolates or not. In order to
 *  implement the simulation it makes use of weighted quick union find
 *  algorithm.
 */
public class Percolation {
    private WeightedQuickUnionUF objUF; // Weighted Quick Union Find object to
                                        // carry out union and find operations
                                        // on the grid nodes.
    private byte[] nodeState;           // Maintains the state (open or closed)
                                        // of each node in the grid.
    private int topVirtualNode = 0;
    private int bottomVirtualNode = 0;
    private int gridDimension;

    /**
     * Initialize quick union find data structure, initial node states,
     * top and bottom virtual nodes.
     * @param N grid dimension
     */
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("Parameter N must be positive");

        gridDimension = N;

        /**
         * The Id array should contain N*N + 1 objects. The additional
         * 1 object is for the topVirtualNode
         */
        objUF = new WeightedQuickUnionUF((N * N) + 2);
        nodeState = new byte[(N * N) + 1];
        for (int i = 0; i < (N * N); i++) {
            nodeState[i] = 0;
        }

        bottomVirtualNode = (N * N) + 1;
    }

    /**
     * Common function to validate row and column identifiers
     * @param i row identifier
     * @param j column identifier
     */
    private void validateArgs(int i, int j)
    {
        if (i < 1 || i > gridDimension || j < 1 || j > gridDimension)
            throw new IndexOutOfBoundsException("Invalid input. Both i and j"
                    + " should be within 1 and " + gridDimension
                    + " included.");
    }

    /**
     * Find whether node j is adjacent (top, left, right or bottom) of
     * node i
     * @param i node whose neighbour is to be verified. i is not the row/column
     *          identifier. If row = 1 and col = 2, then i = (N * row) + col
     * @param j node whose authenticity as neighbour of i is to be verified.
     *          j is not the row/column identifier. If row = 1 and col = 2,
     *          then j = (N * row) + col
     * @return  true if j is the neighbour of i, else false
     */
    private boolean isAdjacentNode(int i, int j)
    {
        if (j < 1 || j > (gridDimension * gridDimension))
            return false;

        int rowI = (i - 1) / gridDimension;
        int rowJ = (j - 1) / gridDimension;

        if (rowI == rowJ) {
            return (j == i + 1 || j == i - 1);
        } else if (rowI != rowJ) {
            return (j == i + gridDimension || j == i - gridDimension);
        }

        return false;
    }

    /**
     * Connect node i to node j
     * @param i node to connect to j. i is not the row/column identifier.
     *          If row = 1 and col = 2, then i = (N * row) + col. It can
     *          also be viewed as an index into the id and nodeState arrays
     * @param j node to which i is to be connected. j is not the row/column
     *          identifier. If row = 1 and col = 2, then j = (N * row) + col.
     *          It can also be viewed as an index into the id and nodeState
     *          arrays
     */
    private void connect(int i, int j)
    {
        /**
         * If node j is open and a neighbour of i, connect both.
         */
        if (isAdjacentNode(i, j) && (nodeState[j] == 1)) {
            objUF.union(i, j);

            if ((j >= (gridDimension * (gridDimension - 1)))
                    && (j <= (gridDimension * (gridDimension))))
                bottomVirtualNode = j;
        }
    }

    /**
     * Open a node
     * @param i Node's row identifier
     * @param j Node's column identifier
     */
    public void open(int i, int j)
    {
        validateArgs(i, j);

        if (isOpen(i, j))
            return;

        int row = i - 1; // convert i to 0 based row identifier.

        nodeState[(gridDimension * row) + j] = 1; // Open the node

        /**
         * Connect the opened node to it's neighbours. Convert the row and
         * column identifiers to index into id and nodeState arrays.
         */
        connect(((gridDimension * row) + j), (gridDimension * (row - 1)) + j);
        connect(((gridDimension * row) + j), (gridDimension * (row + 1)) + j);
        connect(((gridDimension * row) + j), (gridDimension * row) + (j - 1));
        connect(((gridDimension * row) + j), (gridDimension * row) + (j + 1));

        /**
         * If the node is in the first row, connect it to the topVirtualNode.
         * If the node is in the bottom row, connect it to the bottomVirtualNode.
         */
        if (row == 0)
            objUF.union(topVirtualNode, (gridDimension * row) + j);
    }

    /**
     * Checks whether a node is open or closed.
     * @param i row identifier
     * @param j column identifier
     * @return true if the node is open, else false
     */
    public boolean isOpen(int i, int j)
    {
        validateArgs(i, j);
        int row = i - 1; // convert i to 0 based row identifier.
        return nodeState[(row * gridDimension) + j] == 1;
    }

    /**
     * Checks whether a node is full, i.e whether it is connected
     * to any of the nodes in the top row, or not.
     * @param i row identifier
     * @param j column identifier
     * @return true if is full, else false
     */
    public boolean isFull(int i, int j)
    {
        validateArgs(i, j);
        int row = i - 1; // convert i to 0 based row identifier.
        return objUF.connected((gridDimension * row) + j, topVirtualNode);
    }

    /**
     * Check whether a system percolates. i.e Whether there is a path from
     * any of the nodes in the top row to any of the nodes in the bottom row.
     * @return true if the system percolates, else false.
     */
    public boolean percolates()
    {
        return objUF.connected(bottomVirtualNode, topVirtualNode);
    }

    public static void main(String[] args)
    {
        int N = 5, count = 0;
        Percolation p = new Percolation(N);

        p.open(1, 1);
        p.open(1, 2);
        p.open(3, 4);
    }
}