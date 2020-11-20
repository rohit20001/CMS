package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

public class add3 extends AppCompatActivity {
    EditText model_name,gst,road_price,color,price;
    Button btn_save;
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    Map<String, Object> hashMap=new HashMap<>();;

    private Uri imageUri;
    StorageReference storageReference=storage.getReference();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    ImageView imageView6,img_back;
    String mn;
    String c;
    long p;
    long G;
    String O;
    String x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);
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
                else if(id==R.id.ic_home){
                    startActivity(new Intent(add3.this,home3.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(add3.this,profile3.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        /*CollectionReference reff = fStore.collection("users").document(userId).collection("postUrl");
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
        });*/
        if (fAuth.getCurrentUser()!=null) {
            DocumentReference documentReference = fStore.collection("users").document(userId);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        x = String.valueOf(documentSnapshot.getLong("Phone"));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }

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





                hashMap.put("Model",model_name.getText().toString());
                hashMap.put("Color",color.getText().toString());
                hashMap.put("Price",price.getText().toString());
                hashMap.put("Gst",gst.getText().toString());
                hashMap.put("RoadPrice",road_price.getText().toString());
                hashMap.put("publisherEmail",fAuth.getCurrentUser().getEmail());
                hashMap.put("publisherPhone",x);


                fStore.collection("Models").add(hashMap);
                fStore.collection("users").document(userId).collection("postUrl").add(hashMap);

                Toast.makeText(add3.this,"Car added successfully",Toast.LENGTH_LONG).show();



            }
        });
    }
        /*btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mModel = model_name.getText().toString().trim();
                final String mColor = color.getText().toString().trim();
                final String mPrice = price.getText().toString().trim();
                final String mRoad = road_price.getText().toString().trim();
                final String mGst = gst.getText().toString().trim();

                if (TextUtils.isEmpty(mModel)) {
                    model_name.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(mColor)) {
                    color.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(mPrice)) {
                    price.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(mRoad)) {
                    road_price.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(mGst)) {
                    gst.setError("email is required");
                    return;
                }
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cars");
                HashMap<String, Object> hashMap=new HashMap<>();
                hashMap.put("Model",model_name.getText().toString());
                hashMap.put("Color",color.getText().toString());
                hashMap.put("Price",price.getText().toString());
                hashMap.put("Gst",gst.getText().toString());
                hashMap.put("RoadPrice",road_price.getText().toString());
                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(add3.this,"Car Added.",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(add3.this,"error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });*/

        /*imageView6.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(add3.this,"Car added successfully",Toast.LENGTH_LONG).show();



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
        });*/



}
