package kz.lookastana.lookastanaproject.object;

import android.net.Uri;

public class Organization {

    private int _id;
    private String _orgName;
    private String _orgPhone;
    private String _orgEmail;
    private String _orgAddress;
    private Uri _orgLogoUri;

    public Organization() {
    }

    public Organization(int orgId, String orgName, String orgPhone, String orgEmail, String orgAddress, Uri orgLogoUri) {
        _id = orgId;
        _orgName = orgName;
        _orgPhone = orgPhone;
        _orgEmail = orgEmail;
        _orgAddress = orgAddress;
        _orgLogoUri = orgLogoUri;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
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

    public Uri getOrgLogoUri() {
        return _orgLogoUri;
    }

    public void setOrgLogoUri(Uri orgLogoUri) {
        _orgLogoUri = orgLogoUri;
    }
}
