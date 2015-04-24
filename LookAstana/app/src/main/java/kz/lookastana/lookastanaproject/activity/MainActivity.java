package kz.lookastana.lookastanaproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kz.lookastana.lookastanaproject.R;
import kz.lookastana.lookastanaproject.database.DatabaseHandler;
import kz.lookastana.lookastanaproject.object.Organization;

public class MainActivity extends Activity {

    EditText nameTxt, phoneTxt, emailTxt, addressTxt;
    ImageView organizationLogoImgView;
    ListView organizationListView;
    List<Organization> organizations = new ArrayList<Organization>();
    Uri logoUri = Uri.parse("android.resource//kz.lookastana.lookastanaproject/drawable/lookastana.png");
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        nameTxt = (EditText) findViewById(R.id.txtOrgName);
        phoneTxt = (EditText) findViewById(R.id.txtOrgPhone);
        emailTxt = (EditText) findViewById(R.id.txtOrgEmail);
        addressTxt = (EditText) findViewById(R.id.txtOrgAddress);
        organizationLogoImgView = (ImageView) findViewById(R.id.imgViewOrgLogo);
        organizationListView = (ListView) findViewById(R.id.orgListView);

        dbHandler = new DatabaseHandler(getApplicationContext());

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabOrgCreator);
        tabSpec.setIndicator("Добавление");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabOrgList);
        tabSpec.setIndicator("Список");
        tabHost.addTab(tabSpec);

        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Organization organization = new Organization(dbHandler.getOrganizationsCount(), String.valueOf(nameTxt.getText()), String.valueOf(phoneTxt.getText()), String.valueOf(emailTxt.getText()), String.valueOf(addressTxt.getText()),logoUri);
                if (!orgExists(organization)){
                    dbHandler.createOrganization(organization);
                    organizations.add(organization);
                    Toast.makeText(getApplicationContext(), String.valueOf(nameTxt.getText()) + " успешно добавлена в Список организаций!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Ошибка!!! " + String.valueOf(nameTxt.getText()) + " уже есть в Списке организаций!", Toast.LENGTH_SHORT).show();
            }
        });


        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(String.valueOf(nameTxt.getText()).trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        organizationLogoImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Выберите Логотип Организации"), 1);
            }
        });

        if (dbHandler.getOrganizationsCount() != 0){
            organizations.addAll(dbHandler.getAllOrganizations());
        }

        populateList();

    }

    private boolean orgExists(Organization organization){
        String name = organization.getOrgName();
        int orgCount = organizations.size();

        for (int i = 0; i < orgCount; i++) {
            if (name.compareToIgnoreCase(organizations.get(i).getOrgName()) == 0) {
                return true;
            }
        }

        return false;
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if (resCode == RESULT_OK){
            if (reqCode == 1){
                logoUri = data.getData();
                organizationLogoImgView.setImageURI(data.getData());
            }
        }
    }

    private void populateList(){
        ArrayAdapter<Organization> adapter = new OrganizationListAdapter();
        organizationListView.setAdapter(adapter);
    }

    private class OrganizationListAdapter extends ArrayAdapter<Organization> {
        public OrganizationListAdapter() {
            super(MainActivity.this, R.layout.listview_item, organizations);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            Organization currentOrganization = organizations.get(position);

            TextView name = (TextView) view.findViewById(R.id.orgName);
            name.setText(currentOrganization.getOrgName());
            TextView phone = (TextView) view.findViewById(R.id.orgPhone);
            phone.setText(currentOrganization.getOrgPhone());
            TextView email = (TextView) view.findViewById(R.id.orgEmail);
            email.setText(currentOrganization.getOrgEmail());
            TextView address = (TextView) view.findViewById(R.id.orgAddress);
            address.setText(currentOrganization.getOrgAddress());
            ImageView ivOrganizationLogo = (ImageView) view.findViewById(R.id.ivOrgLogo);
            ivOrganizationLogo.setImageURI(currentOrganization.getOrgLogoUri());
            return view;

        }

    }

}
