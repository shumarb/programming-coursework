/**
 * name	     : SHEIKH UMAR
 */

import java.util.*;

class Land{
    private boolean test = false;
    private int max = 0, size;

    // Print out largest sides of square with no trees
    // Precon: Grid has gone through check method
    // Postcon: nil
    private void print(){
        System.out.println(max);
    }

    // Display grid
    // Precon: test==true.
    // Postcon: nil.
    private void disp(int [][] grid){
        System.out.println();
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check for the largest land with no trees in grid
    // Precon: Grid contains correct cells with tree (-1) & empty (0). 
    // Postcon: nil.
    private void check(int [][] grid){
        // Each cell represents the bottom-right hand corner of the largest possible square that can be
        // formed. Hence, it hold the value of the length of the
        // largest possible square that can be formed.
        for(int i = 0; i<grid.length; i++){
            if( grid[0][i]==0 ){
                grid[0][i] = 1;
                max = 1; // There is at least 1 empty cell
            }
            if( grid[i][0] == 0){
                grid[i][0] = 1;
                max = 1; // There is at least 1 empty cell
            }
        }
        if( test ){
            disp(grid);
        }

        for(int i = 1; i < grid.length; i++){
            for(int j = 1; j < grid[i].length; j++){
                if( grid[i][j]==0 ){
                    int above = grid[i-1][j];
                    int left = grid[i][j-1];
                    int diag = grid[i-1][j-1];

                    // Set current grid as 1 if cell above, diagonal or left of it is <0
                    if( above < 0 || left < 0 || diag < 0 ){
                        grid[i][j] = 1;
                    }

                    // Cells around current cell are >=0. Find minimum
                    // value among above, left, diag
                    else{
                        // If above==left==diag -> left==diag
                        if( above==left && above==diag ){
                            grid[i][j] = 1 + above;
                        }
                        // above, left, and diag are not the same value,
                        // so find the minimum among them, and set
                        // current cell as 1+minimum of the 3.
                        else{
                            grid[i][j] = 1 + Math.min(above, Math.min(left, diag));
                        }
                    }
                }
                if( grid[i][j]>max ){
                    max = grid[i][j];
                }
            }
        }
        if( test ){
            disp(grid);
        }
    }

    // Read in square size, position of black cells - indicated by -1
    // Precon: No 2d array has been formed.
    // Postcon: A 2d array with the cells entered by user to contain a tree 
    // has been formed.
    private void read(){
        Scanner sc = new Scanner(System.in);

        size = sc.nextInt();
        if( test ){
            System.out.println("size = " + size);
        }

        // Create grid, set all values to 1. Only cell with trees will
        // be marked as 0.
        int[][] grid = new int[size][size];
        if( test ){
            disp(grid);
        }

        // Read in the black cells, set it to true
        int numBlack;
        numBlack = sc.nextInt();
        if( test ){
            System.out.println("numBlack = " + numBlack);
        }
        for(int i = 0; i<numBlack; i++){
            int len, breadth;
            len = sc.nextInt();
            breadth = sc.nextInt();
            if( test ){
                System.out.println("len = " + len + ", breadth = " + breadth);
            }
            grid[len-1][breadth-1] = -1;
        }

        if( test ){
            disp(grid);
        }

        check(grid);
        print();
    }

    private void run(){
        read();
    }

    public static void main(String[] args){
        Land test = new Land();
        test.run();
    }
}
