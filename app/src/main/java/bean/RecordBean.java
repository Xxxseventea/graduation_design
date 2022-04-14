package bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;

public  class RecordBean  implements Serializable {
   String tizhong;
   String jichudaixie;
   String BMI;
   String zhifang;
   int isfat;
   int isBMI;
   int isweight;

    public String getTizhong() {
        return tizhong;
    }

    public void setTizhong(String tizhong) {
        this.tizhong = tizhong;
    }

    public String getJichudaixie() {
        return jichudaixie;
    }

    public void setJichudaixie(String jichudaixie) {
        this.jichudaixie = jichudaixie;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getZhifang() {
        return zhifang;
    }

    public void setZhifang(String zhifang) {
        this.zhifang = zhifang;
    }

    public int getIsfat() {
        return isfat;
    }

    public void setIsfat(int isfat) {
        this.isfat = isfat;
    }

    public int getIsBMI() {
        return isBMI;
    }

    public void setIsBMI(int isBMI) {
        this.isBMI = isBMI;
    }

    public int getIsweight() {
        return isweight;
    }

    public void setIsweight(int isweight) {
        this.isweight = isweight;
    }
}
