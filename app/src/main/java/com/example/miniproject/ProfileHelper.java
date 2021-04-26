package com.example.miniproject;

public class ProfileHelper {
    String name,qualification,work,clinic_name,clinic_website,phn_no,mail;

    public ProfileHelper(String name, String qualification, String work, String clinic_name, String clinic_website, String phn_no, String mail) {
        this.name = name;
        this.qualification = qualification;
        this.work = work;
        this.clinic_name = clinic_name;
        this.clinic_website = clinic_website;
        this.phn_no = phn_no;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public String getClinic_website() {
        return clinic_website;
    }

    public void setClinic_website(String clinic_website) {
        this.clinic_website = clinic_website;
    }

    public String getPhn_no() {
        return phn_no;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
