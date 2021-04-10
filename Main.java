import jdk.internal.cmm.SystemResourcePressureImpl;

import java.util.LinkedList;
import java.util.Random;

public class Main {


   static LinkedList<byte[][]> out = new LinkedList<byte[][]>();

    static void Solve(byte[][] sud, int x, int y){


        if (y > 8){
            out.add(sud);
            return;
        }


        if (sud[y][x] != 0){

            if (x < 8)
                Solve(sud, x+1, y);
            else
                Solve(sud, 0, y+1);


            return;
        }


        // get vals that are not possible
        LinkedList<Byte> npos = new LinkedList<Byte>();

        for(int i=0;i<sud[y].length;i++) {
            if(sud[y][i] != 0){
                npos.add(sud[y][i]);
            }
        }

        for(int i=0;i<9;i++) {
            if(sud[i][x] != 0) {
                npos.add(sud[i][x]);
            }
        }

        int sx = (x/3) * 3;
        int sy = (y/3) * 3;

        for(int i=sy;i<sy+3;i++) {
            for(int j=sx;j<sx+3;j++) {
                if(sud[i][j] != 0)
                    npos.add(sud[i][j]);
            }
        }



        for(int i=1;i<10;i++) {


            if(!npos.contains((byte)i)) {

                sud[y][x] = (byte)i;


                if (x < 8){
                    Solve(sud, x+1, y);
                }
                else{
                    Solve(sud, 0, y+1);
                }


                if(out.size() > 1){
                    return;
                }

                sud[y][x] = (byte)0;

            }
        }


        return;
    }


    public static void main(String[] args) {

        byte[][] sud = new byte[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };


        long s = System.currentTimeMillis();
        Solve(sud, 0, 0);
        System.out.println(System.currentTimeMillis() - s + " ms");




        for(int i=0;i<out.size();i++) {

            for(int y=0;y<9;y++) {
                for(int x=0;x<9;x++) {

                    System.out.print(out.get(i)[y][x] + " ");
                }
                System.out.println("");
            }
            System.out.println(" --- end --- ");
        }




    }
}
