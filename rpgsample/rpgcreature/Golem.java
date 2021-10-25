package rpgcreature;

import java.util.Random;

public class Golem extends Monster{
    /**
     * ゴーレムクラスのコンストラクタ
     */
    public Golem(){
        super("ゴーレム",100);
        this.gold = 1000;
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = 0;
        if(r.nextInt(100) < 5) {
            damage = 30;
        } else {
            damage = r.nextInt(6) + 5;
        }
        System.out.printf("%sの攻撃！\n",getName());
        
        opponent.damaged(damage);
        
        displayMessage(opponent,damage);
        
    }
}
