package com.inventory.br.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.inventory.br.R;
import com.inventory.br.data.CupContract.CupEntry;
import com.inventory.br.util.Utils;

/**
 * Created by gilsonjuniorpro on 5/6/17.
 */

public class EditActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = EditActivity.class.getName();

    private static final int EXISTING_CUP_LOADER = 0;

    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    private static final int PICK_IMAGE_REQUEST = 0;

    private Uri uri;

    private Uri mCurrentCupUri;

    private EditText mTitleEditText;

    private EditText mTypeEditText;

    private EditText mPriceEditText;

    private EditText mQuantityEditText;

    private ImageButton mSaveButton;

    private ImageButton mDeleteButton;

    private ImageButton mContactButton;

    private Button btIncrease;
    private Button btDecrease;

    private Button btSelectImage;
    private ImageView imageView;

    private Spinner mMaterialSpinner;

    private String supplierTitle;
    private String materialTitle;
    private String typeTitle;

    private int mMaterial = CupEntry.MATERIAL_CERAMIC;

    private boolean mCupHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mCupHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        mCurrentCupUri = intent.getData();

        mTitleEditText = (EditText) findViewById(R.id.edit_title);
        mMaterialSpinner = (Spinner) findViewById(R.id.spinner_material);
        mQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        mTypeEditText = (EditText) findViewById(R.id.edit_type);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mSaveButton = (ImageButton) findViewById(R.id.btSave);
        mContactButton = (ImageButton) findViewById(R.id.btContactSupplier);
        mDeleteButton = (ImageButton) findViewById(R.id.btDeleteCup);
        btIncrease = (Button) findViewById(R.id.btIncrease);
        btDecrease = (Button) findViewById(R.id.btDecrease);
        btSelectImage = (Button) findViewById(R.id.select_image);
        imageView = (ImageView) findViewById(R.id.image_view);

        mTitleEditText.setOnTouchListener(mTouchListener);
        mMaterialSpinner.setOnTouchListener(mTouchListener);
        mQuantityEditText.setOnTouchListener(mTouchListener);
        mTypeEditText.setOnTouchListener(mTouchListener);
        mPriceEditText.setOnTouchListener(mTouchListener);

        btIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyValue(mQuantityEditText.getText().toString(), "Addition");
                mCupHasChanged = true;
            }
        });

        btDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyValue(mQuantityEditText.getText().toString(), "Subtraction");
                mCupHasChanged = true;
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCup();
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

        mContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder b = new StringBuilder();
                b.append("Dear supplier, I would like to buy more 50 cups");
                b.append("\nTitle: ");
                b.append(supplierTitle);
                b.append("\nMaterial: ");
                b.append(materialTitle);
                b.append("\nType: ");
                b.append(typeTitle);
                b.append("\n\nPlease, answer me as soon as possible");
                b.append("\n\nBest Regards");
                b.append("\n\nInventory App");

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gilsonjuniorpro@gmail.com"} );
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order More");
                intent.putExtra(Intent.EXTRA_TEXT, b.toString());
                startActivity(Intent.createChooser(intent, "send mail"));
            }
        });

        btSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToOpenImageSelector();
                mCupHasChanged = true;
            }
        });

        if (mCurrentCupUri == null) {
            setTitle(getString(R.string.editor_activity_title_new_cup));

            invalidateOptionsMenu();

            mDeleteButton.setVisibility(View.GONE);
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_cup));

            getLoaderManager().initLoader(EXISTING_CUP_LOADER, null, this);
        }

        setupSpinner();
    }

    private void verifyValue(String value, String operation) {
        Integer aux = 0;
        if(value != null && !TextUtils.isEmpty(value)){
            aux = Integer.parseInt(value);
            aux = setOperation(aux, operation);
        }else{
            aux = setOperation(aux, operation);
        }
        mQuantityEditText.setText(aux.toString());
    }

    private int setOperation(int value, String operation){
        int ret = 0;
        if(operation.equals("Addition")){
            ret = ++value;
        }else{
            if(value > 0) {
                ret = --value;
            }
        }
        return ret;
    }


    private void setupSpinner() {
        ArrayAdapter materialSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_material_options, android.R.layout.simple_spinner_item);

        materialSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mMaterialSpinner.setAdapter(materialSpinnerAdapter);

        mMaterialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.material_ceramic))) {
                        mMaterial = CupEntry.MATERIAL_CERAMIC;
                    } else if (selection.equals(getString(R.string.material_plastic))) {
                        mMaterial = CupEntry.MATERIAL_PLASTIC;
                    } else {
                        mMaterial = CupEntry.MATERIAL_METAL;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mMaterial = CupEntry.MATERIAL_CERAMIC;
            }
        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                CupEntry._ID,
                CupEntry.COLUMN_TITLE,
                CupEntry.COLUMN_MATERIAL,
                CupEntry.COLUMN_QUANTITY,
                CupEntry.COLUMN_TYPE,
                CupEntry.COLUMN_PRICE,
                CupEntry.COLUMN_IMAGE};

        return new CursorLoader(this,
                mCurrentCupUri,
                projection,
                null,
                null,
                null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int titleColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_TITLE);
            int materialColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_MATERIAL);
            int quantityColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_QUANTITY);
            int typeColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_TYPE);
            int priceColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_PRICE);
            int imageColumnIndex = cursor.getColumnIndex(CupEntry.COLUMN_IMAGE);

            String title = cursor.getString(titleColumnIndex);
            int material = cursor.getInt(materialColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            String type = cursor.getString(typeColumnIndex);
            String price = cursor.getString(priceColumnIndex);
            String image = cursor.getString(imageColumnIndex);

            mTitleEditText.setText(title);

            switch (material) {
                case CupEntry.MATERIAL_PLASTIC:
                    mMaterialSpinner.setSelection(1);
                    break;
                case CupEntry.MATERIAL_METAL:
                    mMaterialSpinner.setSelection(2);
                    break;
                default:
                    mMaterialSpinner.setSelection(0);
                    break;
            }

            mQuantityEditText.setText(Integer.toString(quantity));
            mTypeEditText.setText(type);
            mPriceEditText.setText(price);
            uri = Uri.parse(image);
            imageView.setImageURI(uri);

            supplierTitle = title;
            materialTitle = Utils.getMaterial(material);
            typeTitle = type;
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTitleEditText.setText("");
        mMaterialSpinner.setSelection(0);
        mQuantityEditText.setText("0");
        mTypeEditText.setText("");
        mPriceEditText.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mCurrentCupUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:

                saveCup();
                return true;

            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;

            case android.R.id.home:
                if (!mCupHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveCup() {

        boolean res = checkField();
        if(res) {
            String titleString = mTitleEditText.getText().toString().trim();
            String quantityString = mQuantityEditText.getText().toString().trim();
            String typeString = mTypeEditText.getText().toString().trim();
            String priceString = mPriceEditText.getText().toString().trim();
            String image = uri.toString();

            if (mCurrentCupUri == null &&
                    TextUtils.isEmpty(titleString) && TextUtils.isEmpty(typeString) &&
                    TextUtils.isEmpty(priceString)) {
                return;
            }

            ContentValues values = new ContentValues();
            values.put(CupEntry.COLUMN_TITLE, titleString);
            values.put(CupEntry.COLUMN_MATERIAL, mMaterial);
            values.put(CupEntry.COLUMN_TYPE, typeString);
            values.put(CupEntry.COLUMN_PRICE, priceString);
            values.put(CupEntry.COLUMN_IMAGE, image);

            int quantity = 0;
            if (!TextUtils.isEmpty(quantityString)) {
                quantity = Integer.parseInt(quantityString);
            }
            values.put(CupEntry.COLUMN_QUANTITY, quantity);

            if (mCurrentCupUri == null) {
                Uri newUri = getContentResolver().insert(CupEntry.CONTENT_URI, values);

                if (newUri == null) {
                    Toast.makeText(this, getString(R.string.editor_insert_cup_failed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.editor_insert_cup_successful),
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                int rowsAffected = getContentResolver().update(mCurrentCupUri, values, null, null);

                if (rowsAffected == 0) {
                    Toast.makeText(this, getString(R.string.editor_update_cup_failed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.editor_update_cup_successful),
                            Toast.LENGTH_SHORT).show();
                }
            }

            finish();
        }else{
            Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_LONG).show();
        }
    }


    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteCup();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void deleteCup() {
        if (mCurrentCupUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentCupUri, null, null);

            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_cup_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_cup_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }


    private boolean checkField(){
        boolean res = true;

        if(mTitleEditText.getText().toString().trim().isEmpty()){
            mTitleEditText.setError("please fill this field");
            res = false;
            return res;
        }else{
            mTitleEditText.setError(null);
        }

        if(mQuantityEditText.getText().toString().trim().isEmpty()){
            mQuantityEditText.setError("please fill this field");
            res = false;
            return res;
        }else{
            mQuantityEditText.setError(null);
        }

        if(mTypeEditText.getText().toString().trim().isEmpty()){
            mTypeEditText.setError("please fill this field");
            res = false;
            return res;
        }else{
            mTypeEditText.setError(null);
        }

        if(mPriceEditText.getText().toString().trim().isEmpty()){
            mPriceEditText.setError("please fill this field");
            res = false;
            return res;
        }else{
            mPriceEditText.setError(null);
        }

        if (uri == null) {
            btSelectImage.setError("Missing image");
            res = false;
            return res;
        }else{
            btSelectImage.setError(null);
        }

        return res;
    }

    public void tryToOpenImageSelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        }else{
            Log.i(TAG, "PERMISSION_GRANTED");
            openImageSelector();
        }
    }

    private void openImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelector();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                uri = resultData.getData();
                imageView.setImageURI(uri);
                imageView.invalidate();
            }
        }
    }

}
