package rpgcreature;

import java.util.Random;

/**
 * スライムクラス
 */
public class Slime extends Monster{

    /**
     * スライムクラスのコンストラクタ
     */
    public Slime(){
        super("スライム",12,0);
        this.gold = 120;
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = r.nextInt(5);
        System.out.printf("%sの攻撃！\n",getName());
        
        damage -= opponent.defense;
        if(damage < 0) {
            damage = 1;
        }
        opponent.damaged(damage);
        
        displayMessage(opponent,damage);
        
    }
    
}
