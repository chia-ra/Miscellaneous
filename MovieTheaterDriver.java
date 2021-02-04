import java.io.*;

public class MovieTheaterDriver {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("File path argument missing.");
            System.exit(0);
        }
        else {
            MovieTheater mt = new MovieTheater();

            try {
                File input = new File(args[0]);
                FileReader fr = new FileReader(input);
                BufferedReader br = new BufferedReader(fr);
                String nextLine = br.readLine();

                File output = new File("C:\\Users\\chiar\\OneDrive\\Desktop\\javaFiles\\output.txt");
                output.createNewFile();

                if (!output.exists()){
                    System.out.println("Output file does not exist.");
                    System.exit(1);
                }

                //clear file of previous data
                FileWriter fw = new FileWriter(output);
                fw.close();


                BufferedWriter bw = new BufferedWriter(new FileWriter(output, true));

                //read from input and create reservations written to the output file
                while (nextLine != null) {
                    String outputLine = mt.reservation(nextLine);
                    bw.write(outputLine + "\n");
                    //System.out.println(outputLine);
                    nextLine = br.readLine();
                }


                bw.close();
                br.close();
                fr.close();

                //print out the final seating arrangement
                int[][] theater = mt.getTheater();
                System.out.println ("  [ [ THEATER ] ]");
                for (int i=0; i<10; i++){
                    for (int j=0; j<20; j++) {
                        System.out.print(theater[i][j]);
                    }
                    System.out.println();
                }
                
                System.out.println("\n Output path::\n C:\\Users\\chiar\\OneDrive\\Desktop\\javaFiles\\output.txt");


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //WORK
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
