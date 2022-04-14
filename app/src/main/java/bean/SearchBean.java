package bean;

import java.util.List;

public class SearchBean {

    public int code;
    public String msg;
    public DataDTO data;

    public static class DataDTO {
        public int page;
        public int totalCount;
        public int totalPage;
        public int limit;
        public List<ListDTO> list;

        public List<ListDTO> getList() {
            return list;
        }

        public void setList(List<ListDTO> list) {
            this.list = list;
        }

        public static class ListDTO {
            public String foodId;
            public String name;
            public int healthLevel;
            public String calory;

            public String getFoodId() {
                return foodId;
            }

            public void setFoodId(String foodId) {
                this.foodId = foodId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getHealthLevel() {
                return healthLevel;
            }

            public void setHealthLevel(int healthLevel) {
                this.healthLevel = healthLevel;
            }

            public String getCalory() {
                return calory;
            }

            public void setCalory(String calory) {
                this.calory = calory;
            }
        }
    }
}
