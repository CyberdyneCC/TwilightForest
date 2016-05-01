package twilightforest.block;

import net.minecraft.util.IIcon;

public class GiantBlockIcon implements IIcon {

    private IIcon baseIcon;
    private int myX;
    private int myY;

    public GiantBlockIcon(IIcon blockIcon, int x, int y) {
        this.baseIcon = blockIcon;
        this.myX = x;
        this.myY = y;
    }

    public int func_94211_a() {
        return this.baseIcon.func_94211_a() / 4;
    }

    public int func_94216_b() {
        return this.baseIcon.func_94216_b() / 4;
    }

    public float func_94209_e() {
        float f = this.baseIcon.func_94212_f() - this.baseIcon.func_94209_e();

        return this.baseIcon.func_94209_e() + f * 0.25F * (float) this.myX;
    }

    public float func_94212_f() {
        float f = this.baseIcon.func_94212_f() - this.baseIcon.func_94209_e();

        return this.baseIcon.func_94212_f() - f * 0.25F * (float) (3 - this.myX);
    }

    public float func_94214_a(double par1) {
        float f = this.func_94212_f() - this.func_94209_e();

        return this.func_94209_e() + f * (float) par1 / 16.0F;
    }

    public float func_94206_g() {
        float f = this.baseIcon.func_94210_h() - this.baseIcon.func_94206_g();

        return this.baseIcon.func_94206_g() + f * 0.25F * (float) this.myY;
    }

    public float func_94210_h() {
        float f = this.baseIcon.func_94210_h() - this.baseIcon.func_94206_g();

        return this.baseIcon.func_94210_h() - f * 0.25F * (float) (3 - this.myY);
    }

    public float func_94207_b(double par1) {
        float f = this.func_94210_h() - this.func_94206_g();

        return this.func_94206_g() + f * ((float) par1 / 16.0F);
    }

    public String func_94215_i() {
        return this.baseIcon.func_94215_i();
    }
}
