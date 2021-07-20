import java.util.*;

public class GameManager {

    public final int outside = -1;
    public final int inside = 0;
    public int turn = 0;
    public int[][] field = {
        {-1,-1,-1,-1,-1,-1,-1},
        {-1, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0,-1},
        {-1,-1,-1,-1,-1,-1,-1}
    };

    //タイトルを表示
    public void title() {
        System.out.println("***************************");
        System.out.println("　　　　 戦艦ゲーム　　　　　");
        System.out.println("***************************");
    }

    //リザルトを表示
    public void result() {
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("      congratulations!     ");
        System.out.println("+++++++++++++++++++++++++++");
    }

    //盤面を表示
    public void showField() {
        for(int i=1; i<field.length-1; i++) {
            for(int j=1; j<field[i].length-1; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    //船の残機を表示
    public void showSinkCount(Ship sp1, Ship sp2, Ship sp3) {
        System.out.println("sp1 count : " + sp1.sinkCount);
        System.out.println("sp2 count : " + sp2.sinkCount);
        System.out.println("sp3 count : " + sp3.sinkCount);
    }

    //船をランダムな位置に配置
    public void setPos(Ship sp) {

        Random rd = new Random();
        boolean isSet = true;
        
        while(isSet) {

            int pos_x = rd.nextInt(4) + 1;
            int pos_y = rd.nextInt(4) + 1;

            if(field[pos_y][pos_x] == 0) {
                field[pos_y][pos_x] = sp.ship_No;
                field[sp.ship_y][sp.ship_x] = inside;
                sp.ship_x = pos_x;
                sp.ship_y = pos_y;
                isSet = false;
            }
            
        }
    }
}