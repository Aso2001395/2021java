import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GameManager gm = new GameManager();

        Ship sp1 = new Ship();
        Ship sp2 = new Ship();
        Ship sp3 = new Ship();
        
        sp1.init(1,gm);
        sp2.init(2,gm);
        sp3.init(3,gm);

        gm.title();
        
        while(!(sp1.isSink && sp2.isSink && sp3.isSink)) {

            System.out.print("船１：");
            sp1.isSurvive();
            System.out.print("船２：");
            sp2.isSurvive();
            System.out.print("船３：");
            sp3.isSurvive();

            System.out.printf("--------[%sターン目]--------\n",++gm.turn);

            System.out.println("爆弾のX座標を入力してください(1~5)");
            int bom_x = sc.nextInt();

            System.out.println("爆弾のY座標を入力してください(1~5)");
            int bom_y = sc.nextInt();

            //盤面を表示
            // gm.showField();
            //船の残機を表示
            // gm.showSinkCount(sp1,sp2,sp3);

            sp1.isCheckPos(gm, bom_x, bom_y);
            sp2.isCheckPos(gm, bom_x, bom_y);
            sp3.isCheckPos(gm, bom_x, bom_y);

            System.out.print("船１：");
            sp1.isCheckSuv(gm);
            
            System.out.print("船２：");
            sp2.isCheckSuv(gm);

            System.out.print("船３：");
            sp3.isCheckSuv(gm);

            //区切り
            System.out.println("---------------------------");
        }

        gm.result();

        sc.close();
    }
}