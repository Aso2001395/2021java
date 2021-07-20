public class Ship {

    public int ship_No;
    public int ship_x;
    public int ship_y;
    public int sinkCount = 3;
    public boolean isHit = false;
    public boolean isSink = false;
    public boolean isNear = false;

    //初期化
    public void init(int ship_No, GameManager gm) {
        this.ship_No = ship_No;
        this.sinkCount = 3;
        this.isHit = false;
        this.isSink = false;
        this.isNear = false;
        gm.setPos(this);
    }

    //船が生存しているかどうか
    public void isSurvive() {
        if(isSink) {
            System.out.println("撃沈");
        } else {
            System.out.println("生存");
        }
    }

    //船の残機があるかどうか
    public void isSink() {
        if(sinkCount <= 0) {
            sinkCount = 0;
            isSink =  true;
        }
    }

    //爆弾が当たったかどうか
    public void isCheckSuv(GameManager gm) {
        isSink();
        if(isSink) {
            ship_No = gm.inside;
            gm.setPos(this);
            System.out.println("撃沈しました");
        } else {
            if(isHit) {
                System.out.println("爆弾が命中　船が移動しました");
                isHit = false;
                gm.setPos(this);
            } else if(isNear) {
                System.out.println("近くにいるようです");
                isNear = false;
            } else {
                System.out.println("周りにはいないようです");
            }
        }
    }

    //爆弾投下位置と船の位置関係
    public void isCheckPos(GameManager gm, int bom_x, int bom_y) {
        int dropPos = gm.field[bom_y][bom_x];
        isHit(dropPos);
        isNear(gm, bom_x, bom_y);
    }

    //船が爆弾に当たったかどうか
    public void isHit(int dropPos) {
        if(dropPos == ship_No) {
            isHit = true;
            sinkCount--;
        }
    }

    //船が投下位置の周りにいるかどうか
    public void isNear(GameManager gm, int bom_x, int bom_y) {
        int dropPos = gm.field[bom_y-1][bom_x];
        if(dropPos == ship_No) {
            isNear = true;
        }

        dropPos = gm.field[bom_y+1][bom_x];
        if(dropPos == ship_No) {
            isNear = true;
        }

        dropPos = gm.field[bom_y][bom_x-1];
        if(dropPos == ship_No) {
            isNear = true;
        }

        dropPos = gm.field[bom_y][bom_x+1];
        if(dropPos == ship_No) {
            isNear = true;
        }
    }
}