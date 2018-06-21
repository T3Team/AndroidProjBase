package base.android.t3t.netrequestdemo.entity;

import java.util.List;

/**
 * Created by dale on 2018/6/19.
 */
public class NetDemoBean {
    /**
     * data : 0
     * list : [{"name":"helllo","phone":"111111","id":0}]
     */

    private int data;
    private List<ListBean> list;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : helllo
         * phone : 111111
         * id : 0
         */

        private String name;
        private String phone;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}
