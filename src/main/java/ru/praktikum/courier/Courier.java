package ru.praktikum.courier;

    public class Courier {
        private String login;
        private String password;
        private String firstName;
        private int id;


        public Courier(String login, String password){
            this.login = login;
            this.password = password;
        }

        public Courier(String login, String password, String firstName) {
            this.login = login;
            this.password = password;
            this.firstName = firstName;
        }
        public Courier(int id) {
            this.id = id;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
    }





