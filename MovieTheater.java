
public class MovieTheater {
    // assumptions: ppl dont care where they sit; groups can be split if not enough space on any lines
    // GROUPS ARE NOT GREATER THAN 20.
    // Customer satisfaction: groups are not split up and are served fcfs as seats available
    private final int rows = 10;
    private final int cols = 20;
    //int empty = rows*cols;
    private int[][] seats = new int[rows][cols];
    private int[] empty = new int[rows]; //keep track of empty seats per row

    public MovieTheater() {
        for (int i=0; i<rows; i++) {
            empty[i] = cols;
        }
    }

    public int[][] getTheater() {
        return seats;
    }

    public String reservation(String inputLine) {
        // returns the string to be written into the output file

        String[] s = inputLine.split(" ");
        if (s.length != 2) {
            //input read from file is not in format R00X X
            return "<Invalid Format>";
        }
        String reservation = s[0];
        int guests = Integer.parseInt(s[1]);
        if (guests > cols) {
            return reservation + " <Party Too Large>";
        }

        String output = reservation + " ";

        boolean seated = false;
        while (!seated) {
            for (int i = 0; i < rows; i++) {
                if (empty[i]  == cols) {
                    empty[i] -= guests;
                    for (int j = 0; j<guests; j++) {
                        //add the seats to the output string and cross out the seats on the 2d array
                        //A=65
                        int seatNum = 65+i;
                        char c = (char)seatNum;
                        output += c;
                        output += j+1;
                        seats[i][j] = 1;
                        if (j!=guests-1) {
                            output+=",";
                        }
                    }
                    seated = true;
                    break;
                }
                else if (empty[i] >= guests + 3 && empty[i] !=cols) {
                    int j=0;
                    for (j=0; j<cols; j++) {
                        if (seats[i][j] == 0) {
                            break;
                        }
                        // j is where the end of the last party is
                    }
                    int k=0;
                    for (k=j; k<j+3; k++) {
                        //k is the pointer for the buffers
                        seats[i][k] = 2;
                        //buffer seats indicated with 2
                    }
                    for (int l=k; l<k+guests; l++) {
                        seats[i][l] = 1;
                        int seatNum = 65+i;
                        char c = (char)seatNum;
                        output += c;
                        output += l+1;
                        if (l!=k+guests-1) {
                            output+=",";
                        }
                    }

                    empty[i] -= guests+3;
                    seated = true;
                    break;
                }
                else if (i == rows-1 && (empty[i]!= cols && empty[i] < guests+3)){
                    output = reservation + " No more available seats.";
                    return output;
                }
            }
        }
        return output;
    }
}
