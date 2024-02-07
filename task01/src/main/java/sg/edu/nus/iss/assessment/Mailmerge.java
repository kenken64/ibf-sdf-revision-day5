package sg.edu.nus.iss.assessment;

public class Mailmerge {
    private String firstName;
    private String lastName;
    private String address;
    private int years;

    public Mailmerge(String _firstName, 
            String _lastName, String _address, int _years){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.address = _address;
        this.years = _years;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getYears() {
        return years;
    }
    public void setYears(int years) {
        this.years = years;
    }
    
    
}
