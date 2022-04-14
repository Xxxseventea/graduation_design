package bean;

public class FoodDetailBean {

    public int code;
    public String msg;
    public DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        public String foodId;
        public String name;
        public String calory;
        public String caloryUnit;
        public String joule;
        public String jouleUnit;
        public String protein;
        public String proteinUnit;
        public String fat;
        public String fatUnit;
        public String saturatedFat;
        public String saturatedFatUnit;
        public String fattyAcid;
        public String fattyAcidUnit;
        public String mufa;
        public String mufaUnit;
        public String pufa;
        public String pufaUnit;
        public String cholesterol;
        public String cholesterolUnit;
        public String carbohydrate;
        public String carbohydrateUnit;
        public String sugar;
        public String sugarUnit;
        public String fiberDietary;
        public String fiberDietaryUnit;
        public String natrium;
        public String natriumUnit;
        public String alcohol;
        public String alcoholUnit;
        public String vitaminA;
        public String vitaminAUnit;
        public String carotene;
        public String caroteneUnit;
        public String vitaminD;
        public String vitaminDUnit;
        public String vitaminE;
        public String vitaminEUnit;
        public String vitaminK;
        public String vitaminKUnit;
        public String thiamine;
        public String thiamineUnit;
        public String lactoflavin;
        public String lactoflavinUnit;
        public String vitaminB6;
        public String vitaminB6Unit;
        public String vitaminB12;
        public String vitaminB12Unit;
        public String vitaminC;
        public String vitaminCUnit;
        public String niacin;
        public String niacinUnit;
        public String folacin;
        public String folacinUnit;
        public String pantothenic;
        public String pantothenicUnit;
        public String biotin;
        public String biotinUnit;
        public String choline;
        public String cholineUnit;
        public String phosphor;
        public String phosphorUnit;
        public String kalium;
        public String kaliumUnit;
        public String magnesium;
        public String magnesiumUnit;
        public String calcium;
        public String calciumUnit;
        public String iron;
        public String ironUnit;
        public String zinc;
        public String zincUnit;
        public String iodine;
        public String iodineUnit;
        public String selenium;
        public String seleniumUnit;
        public String copper;
        public String copperUnit;
        public String fluorine;
        public String fluorineUnit;
        public String manganese;
        public String manganeseUnit;
        public int healthLight;
        public String healthTips;
        public String healthSuggest;
        public GlycemicInfoDataDTO glycemicInfoData;

        public int getHealthLight() {
            return healthLight;
        }

        public void setHealthLight(int healthLight) {
            this.healthLight = healthLight;
        }

        public String getHealthTips() {
            return healthTips;
        }

        public void setHealthTips(String healthTips) {
            this.healthTips = healthTips;
        }

        public String getHealthSuggest() {
            return healthSuggest;
        }

        public void setHealthSuggest(String healthSuggest) {
            this.healthSuggest = healthSuggest;
        }

        public static class GlycemicInfoDataDTO {
            public GiDTO gi;
            public GlDTO gl;

            public GiDTO getGi() {
                return gi;
            }

            public void setGi(GiDTO gi) {
                this.gi = gi;
            }

            public GlDTO getGl() {
                return gl;
            }

            public void setGl(GlDTO gl) {
                this.gl = gl;
            }

            public static class GiDTO {
                public String value;
                public String label;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }
            }

            public static class GlDTO {
                public String value;
                public String label;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJoule() {
            return joule;
        }

        public void setJoule(String joule) {
            this.joule = joule;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public GlycemicInfoDataDTO getGlycemicInfoData() {
            return glycemicInfoData;
        }

        public String getJouleUnit() {
            return jouleUnit;
        }

        public void setJouleUnit(String jouleUnit) {
            this.jouleUnit = jouleUnit;
        }

        public String getProteinUnit() {
            return proteinUnit;
        }

        public void setProteinUnit(String proteinUnit) {
            this.proteinUnit = proteinUnit;
        }

        public String getFatUnit() {
            return fatUnit;
        }

        public void setFatUnit(String fatUnit) {
            this.fatUnit = fatUnit;
        }

        public String getCarbohydrateUnit() {
            return carbohydrateUnit;
        }

        public void setCarbohydrateUnit(String carbohydrateUnit) {
            this.carbohydrateUnit = carbohydrateUnit;
        }

        public void setGlycemicInfoData(GlycemicInfoDataDTO glycemicInfoData) {
            this.glycemicInfoData = glycemicInfoData;
        }
    }
}
