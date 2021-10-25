import java.util.Random;
import java.util.Scanner;

import rpgcreature.Braver;
import rpgcreature.Golem;
import rpgcreature.Slime;
import rpgcreature.Wizard;
import rpgcreature.MetalSlime;
import rpgcreature.Monster;

//////////////////////////////////////
// メインクラス
/////////////////////////////////////
public class RPGMain {
    private final int MONSTER_NUM=3;
    private final int COMMAND_BATTLE=1;
    private final int COMMAND_RECOVERY=2;

    private final int SlimeNum = 0;
    private final int WizardNum = 1;
    private final int MetalSlimeNum = 2;
    private final int GolemNum = 3;

    private int totalGold;
    private int turn;
    private Braver braver;
    private Monster[] monsters;
    public static void main(String[] args){
        RPGMain rpg = new RPGMain();
        //ゲームスタート
        rpg.game();
    }

    /**
     * ゲームメインメソッド
     */
    public void game(){

        //タイトル表示
        dispTitle();

        //名前入力
        Scanner sc = new Scanner(System.in);
        System.out.println("あなたの名前を入力してください");
        String name = sc.nextLine();
        //入力された名前で主人公（勇者をインスタンス作成）
        braver = new Braver(name);

        //バトルスタートを表示する
        dispBattleStart();

        //敵を3体ランダムに決める
        dicideMonsters();

        //メインループ（無限ループ）
        while(true){
            //現在のターン数
            dispTurn();
            //現在の状態を表示
            dispStatus();
            //入力されたコマンドを取得
            int command = sc.nextInt();

            while(command != COMMAND_BATTLE && command != COMMAND_RECOVERY) {
                System.out.println("1または2を入力してください");
                command = sc.nextInt();
            }
            
            if( command == COMMAND_BATTLE ){
                //たたかう
                if( !battle() ){
                    break;
                }
            }else if( command == COMMAND_RECOVERY) {
                //回復する
                braver.recovery();
            }
        }

        dispTotalGold();
        sc.close();
    }

    /**
     * タイトルを表示する
     */
    private void dispTitle(){
        System.out.println("==========================");
        System.out.println("=       ASO QUEST        =");
        System.out.println("==========================");
    }

    /**
     * バトルスタートの表示
     */
    private void dispBattleStart(){
        System.out.println("==========================");
        System.out.println("====BATTLE START!!!!!!====");
        System.out.println("==========================");
    }

    /**
     * 現在の状態を表示する
     */
    private void dispStatus(){
        System.out.println("==========================");
        System.out.printf( "= %s                 =\n",braver.getName());
        System.out.printf( "= HP:%3d                 =\n",braver.getHp());
        System.out.println("==========================");
        System.out.println("どうしますか？1:たたかう 2:回復");
    }

    //現在のターン数を表示
    private void dispTurn() {
        System.out.printf("====%dターン目====\n",++turn);
    }

    //戦闘終了後の獲得ゴールド表示
    private void dispTotalGold() {
        System.out.printf("獲得ゴールド：%s",totalGold);
    }

    /**
     * モンスターを3体決定する
     */
    private void dicideMonsters(){
        Random r = new Random();
        monsters = new Monster[MONSTER_NUM];
        for(int i=0; i < MONSTER_NUM; i++){
            //乱数を取得してモンスターを決定する
            int value = r.nextInt(4);
            if( value == SlimeNum ){
                monsters[i] = new Slime();
            }else if( value == WizardNum){
                monsters[i] = new Wizard();
            }else if( value == MetalSlimeNum){
                monsters[i] = new MetalSlime();
            } else if(value == GolemNum){
                monsters[i] = new Golem();
            }
        }
        
        //「〇〇〇が現れた」を表示
        for(int i = 0; i < MONSTER_NUM; i++){
            monsters[i].displayAppearanceMsg();
        }
    }

    /**
     * たたかうコマンドに対する処理
     * 
     *  バトル継続するかのフラグ true：継続する false：バトル終了
     */
    private boolean battle(){
        //どのモンスターに攻撃するかを決定する
        Random r = new Random();
        Monster monster = null;
        //モンスター存在確認
        do{
            int index = r.nextInt(3);
            monster = monsters[index];
        }while( !monster.isThere() );

        //主人公→モンスターへ攻撃！
        braver.attack(monster);
        if( !monster.isAlive() ){
            System.out.printf("%sを倒した！\n",monster.getName());
            totalGold += monster.gold;
        }
        
        //3体居なくなった？
        if( isNotThereAllMonster() ){
            //すべて居なくなったら終了
            return false;
        }

        //モンスター→主人公からの攻撃 
        for(int i=0; i < MONSTER_NUM; i++){
            if(monsters[i].isThere()) { //修正箇所
                monsters[i].attack(braver);
            }
        }

        //主人公が死んだか？
        if( !braver.isAlive() ){
            System.out.println("あなたはしにました");
            return false;
        }

        return true;
    }

    /**
     * 全てのモンスターが居なくなったか？
     * @return true:すべて居なくなった false:まだモンスターは居る
     */
    private boolean isNotThereAllMonster(){
        boolean isNotThereMonster = true;
        for(int i=0; i < MONSTER_NUM; i++){
            if( monsters[i].isThere() ){
                isNotThereMonster = false;
                break;
            }
        }
        return isNotThereMonster;
    }
}
