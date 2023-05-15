package com.example.famille.modelle;

public class Person {

    private int id;
    private String firstname, lastname, born_on, gender, tel, employ, country, city, profession, account_state;
    private int genreration, parent_id;
    private  String created_at, updated_at, email, password, matrimonial,  alive;


    public Person(int id, String firstname, String lastname, String born_on, String gender, String tel, String employ, String country, String city, String profession, String account_state, int generation, int parent_id, String created_at, String updated_at, String email, String password, String matrimonial, String alive) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.born_on = born_on;
        this.gender = gender;
        this.tel = tel;
        this.employ = employ;
        this.country = country;
        this.city = city;
        this.profession = profession;
        this.account_state = account_state;
        this.genreration = generation;
        this.parent_id = parent_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.email = email;
        this.password = password;
        this.matrimonial = matrimonial;
        this.alive = alive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBorn_on() {
        return born_on;
    }

    public void setBorn_on(String born_on) {
        this.born_on = born_on;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmploy() {
        return employ;
    }

    public void setEmploy(String employ) {
        this.employ = employ;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAccount_state() {
        return account_state;
    }

    public void setAccount_state(String account_state) {
        this.account_state = account_state;
    }

    public int getGenreration() {
        return genreration;
    }

    public void setGenreration(int genreration) {
        this.genreration = genreration;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatrimonial() {
        return matrimonial;
    }

    public void setMatrimonial(String matrimonial) {
        this.matrimonial = matrimonial;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "nom ='" + firstname + '\'' + ", genreration =" + genreration ;
    }
}
