package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.kulvir72510.cms6.MainActivity.userId;

public class add2 extends AppCompatActivity {
    EditText model_name,gst,road_price,color,price;
    Button btn_save;
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    Map<String, Object> docData=new HashMap<>();;

    private Uri imageUri;
    StorageReference storageReference=storage.getReference();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    ImageView imageView6,img_back;
    String mn;
    String c;
    long p;
    long G;
    String O;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        price = findViewById(R.id.et_phone);
        color = findViewById(R.id.et_email_id);
        road_price = findViewById(R.id.et_city);
        gst = findViewById(R.id.et_address);
        model_name = findViewById(R.id.tv_email);
        btn_save = findViewById(R.id.btn_save);
        imageView6 = findViewById(R.id.imageView6);
        img_back = findViewById(R.id.img_back);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation3);
        bottomNavigationView.setSelectedItemId(R.id.ic_add);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_add){
                    return false;
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(add2.this,graph2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(add2.this,home2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(add2.this,profile2.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });
        CollectionReference reff = fStore.collection("users").document(userId).collection("postUrl");
        reff.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String name=documentSnapshot.getString("randomKey");
                    System.out.println(name);


                }
                System.out.println("fetched all data success");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String modelName = model_name.getText().toString().trim();
                final String col = color.getText().toString().trim();
                final long pri = Long.parseLong(price.getText().toString().trim());
                final long g = Long.parseLong(gst.getText().toString().trim());
                final String o = road_price.getText().toString();
                mn = modelName;
                c = col;
                p = pri;
                G = g;
                O = o;

                if (TextUtils.isEmpty(modelName)){
                    model_name.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(col)){
                    color.setError("password is required");
                    return;
                }
                if (TextUtils.isEmpty(o)){
                    road_price.setError("password is required");
                    return;
                }





                docData.put("model_name", modelName);
                docData.put("color", col);
                docData.put("price", pri);
                docData.put("phone",g);
                docData.put("other", o);


                fStore.collection("Models").add(docData);
                fStore.collection("users").document(userId).collection("postUrl").add(docData);

                Toast.makeText(add2.this,"Car added successfully",Toast.LENGTH_LONG).show();



            }
        });
    }
    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data != null && data.getData()!=null){
            imageUri= data.getData();
            imageView6.setImageURI(imageUri);
            System.out.println(imageUri+"  kulvir 1");
            uploadPicture();

        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();

        final StorageReference riversRef = storageReference.child("carImages/"+randomKey);


        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                        userId = fAuth.getCurrentUser().getUid();

                        docData.put("carUrl","images/"+randomKey);
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent=(100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage "+(int) progressPercent + "%");
            }
        });


    }
}
