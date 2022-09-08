/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_hospital_management_system;

/**
 *
 * @author PC PAL
 */
public class patient {
    private int patientId;
    private String name;
    private String Address;
    private String gender;
    private String guardian;
    private int telephone;
    private String ward;
    private String admiteddate;
   // private String dischargedate;
    
    public patient(int patientId,String name,String Address,String gender,String guardian,int telephone,String ward,String admiteddate){
    
    this.patientId=patientId;
    this.name=name;
    this.Address=Address;
    this.gender=gender;
    this.guardian=guardian;
    this.telephone=telephone;
    this.ward=ward;
    this.admiteddate=admiteddate;
    //this.dischargedate=dischargedate;
    
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAdmiteddate() {
        return admiteddate;
    }

    public void setAdmiteddate(String admiteddate) {
        this.admiteddate = admiteddate;
    }

    /*public String getDischargedate() {
        return dischargedate;
    }

    public void setDischargedate(String dischargedate) {
        this.dischargedate = dischargedate;
    }*/
    
    
}
