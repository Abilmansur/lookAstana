package kz.lookastana.lookastanaproject.object;

public class Organization {

    private String _orgName;
    private String _orgPhone;
    private String _orgEmail;
    private String _orgAddress;

    public Organization() {
    }

    public Organization(String orgName, String orgPhone, String orgEmail, String orgAddress) {
        _orgName = orgName;
        _orgPhone = orgPhone;
        _orgEmail = orgEmail;
        _orgAddress = orgAddress;
    }

    public String getOrgName() {
        return _orgName;
    }

    public void setOrgName(String orgName) {
        _orgName = orgName;
    }

    public String getOrgPhone() {
        return _orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        _orgPhone = orgPhone;
    }

    public String getOrgEmail() {
        return _orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        _orgEmail = orgEmail;
    }

    public String getOrgAddress() {
        return _orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        _orgAddress = orgAddress;
    }
}
