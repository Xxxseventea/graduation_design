package utils;

import android.util.Log;

import java.math.BigDecimal;

public class StandardUtils {

    public double fat(double BMI,int age, int sex){
        //体脂率：1.2×BMI+0.23×年龄-5.4-10.8×性别（男为1，女为0）。

       double result =  1.2 * BMI + 0.23 * age - 5.4 - 10.8 * sex;
       double result1 = new BigDecimal(result).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result1*2;
    }

    public double BMI(float weight , int height){


        double height1 = (double)(Math.round(height*100/100)/100.0);
        Log.d("身高：米", String.valueOf(height1));

        double bmi = (weight / 2) / (height1 * height1);

        double result = new BigDecimal(bmi).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

        return result;
    }

    public int BasalMetabolism(int sex,float weight,int height , int age){
        //女性基础代谢率=661+9.6*体重(kg)+1.72*身高(cm)-4.7*年龄
        // 男性基础代谢率=67+13.73*体重(kg)+5*身高(cm)-6.9*年龄

        if (sex == 1){
            return (int) (67+13.73 * (weight/2) + 5*height - 6.9 * age);
        }else {
            return (int) (611+9.6 * (weight/2) + 1.72*height - 4.7 * age);
        }

    }

    public int isFatStandard(double fat){
        //0 :偏瘦
        // 1 ：正常
        //2：偏高
        //3.超高

        if (fat < 15)
        {
            return 0;
        }
        else if (fat >= 15 && fat < 25)
        {
            return 1;
        }
        else if (fat >= 25 && fat < 30 )
        {
            return 2;
        }
        else {
            return 3;
        }
    }

    public int isWeightStandard(int height,int sex,double weight){
        //男性的标准体重计算方法为（身高cm-80）*70%；女性标准体重计算方法为（身高cm-70）*60%。
        // 标准体重正负10%以内为正常体重，标准体重正负10%-20%为体重过重或过轻，标准体重正负20%以上为肥胖或者体重不足。

        /**
         * 0 体重不足
         * 1 过轻
         * 2 正常
         * 3 过重
         * 4 肥胖
         */
        if (sex == 1){
            double weight1 = (height - 80) * 0.6;
            double weight2 = (height - 80) * 0.8;

            double weight3 = (height - 80) * 0.5;
            double weight4 = (height - 80) * 0.9;

            if (weight < weight3){
                return 0;
            }else if (weight >= weight3 && weight <= weight1){
                return 1;
            }else if (weight >= weight1 && weight <= weight2){
                return 2;

            }else if (weight >= weight2 && weight <= weight4){
                return 3;
            }else{
                return 4;
            }
        }else {
            double weight1 = (height - 70) * 0.5;
            double weight2 = (height - 70) * 0.7;

            double weight3 = (height - 70) * 0.4;
            double weight4 = (height - 70) * 0.8;

            if (weight < weight3){
                return 0;
            }else if (weight >= weight3 && weight <= weight1){
                return 1;
            }else if (weight >= weight1 && weight <= weight2){
                return 2;

            }else if (weight >= weight2 && weight <= weight4){
                return 3;
            }else{
                return 4;
            }
        }
    }

    public int isBMIStandard(double BMI){

         //0 :偏瘦
        // 1 ：正常
        //2：偏高
        //3.超高

        if (BMI < 18.5)
        {
            return 0;
        }
        else if (BMI >= 18.5 && BMI < 24 )
        {
           return 1;
        }
        else if (BMI >= 24 && BMI < 28 )
        {
           return 2;
        }
        else {
            return 3;
        }

    }
}
