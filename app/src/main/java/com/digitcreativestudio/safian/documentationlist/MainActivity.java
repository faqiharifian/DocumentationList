package com.digitcreativestudio.safian.documentationlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    int currentPage = 0;
    ViewFlipper vf;
    LinearLayout manuals, plans, agreements, documents, procedures, guidelines;
    Button addManual, addPlan, addAgreement, addDocument, addProcedure, addGuideline;
    LinearLayout manualFields, planFields, agreementFields, documentFields, procedureFields, guidelineFields;
    LayoutInflater inflater;

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
//        return super.onTouchEvent(touchevent);
        switch(touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 - x2 < -100)
                {
                    if(vf.getDisplayedChild()>0){
                        vf.setInAnimation(this, R.anim.in_from_left);
                        vf.setOutAnimation(this, R.anim.out_to_right);
                        vf.showPrevious();
                    }
                }

                // if right to left sweep event on screen
                if (x1 - x2 > 100)
                {
                    if(vf.getDisplayedChild() < vf.getChildCount()-1){
                        vf.setInAnimation(this, R.anim.in_from_right);
                        vf.setOutAnimation(this, R.anim.out_to_left);
                        vf.showNext();

                    }
                }
/*
                // if UP to Down sweep event on screen
                if (y1 < y2)
                {
                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }

                //if Down to UP sweep event on screen
                if (y1 > y2)
                {
                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }*/
                break;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ((Button) findViewById(R.id.generate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("clicked", "clicked");
                String nameOfOffice = ((EditText) findViewById(R.id.name_of_office)).getText().toString();
                String address = ((EditText) findViewById(R.id.address)).getText().toString();
                String phoneOrEmail = ((EditText) findViewById(R.id.phone_or_email)).getText().toString();
                MyPDF myPDF = new MyPDF(getApplicationContext(), MainActivity.this);
                myPDF.execute(nameOfOffice, address, phoneOrEmail, getInput(),
                        ((EditText) findViewById(R.id.last_update)).getText().toString(),
                        ((EditText) findViewById(R.id.verification)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_head)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_branch)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_offsite)).getText().toString()
                );
            }
        });

        inflater = (LayoutInflater) this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        vf = (ViewFlipper) findViewById(R.id.viewFlipper);

        manuals = (LinearLayout) findViewById(R.id.manuals);
        plans = (LinearLayout) findViewById(R.id.plans);
        agreements = (LinearLayout) findViewById(R.id.agreements);
        documents = (LinearLayout) findViewById(R.id.documents);
        procedures = (LinearLayout) findViewById(R.id.procedures);
        guidelines = (LinearLayout) findViewById(R.id.guidelines);

        addManual = (Button) findViewById(R.id.add_manual);
        addPlan = (Button) findViewById(R.id.add_plan);
        addAgreement = (Button) findViewById(R.id.add_agreement);
        addDocument = (Button) findViewById(R.id.add_document);
        addProcedure = (Button) findViewById(R.id.add_procedure);
        addGuideline = (Button) findViewById(R.id.add_guideline);

        manualFields = (LinearLayout) manuals.findViewById(R.id.manual_fields);
        planFields = (LinearLayout) plans.findViewById(R.id.plan_fields);
        agreementFields = (LinearLayout) agreements.findViewById(R.id.agreement_fields);
        documentFields = (LinearLayout) documents.findViewById(R.id.document_fields);
        procedureFields = (LinearLayout) procedures.findViewById(R.id.procedure_fields);
        guidelineFields = (LinearLayout) guidelines.findViewById(R.id.guideline_fields);


        addManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
//                        (Context.LAYOUT_INFLATER_SERVICE);

                LinearLayout newlayout = (LinearLayout) inflater.inflate(R.layout.fields, manualFields);
                LinearLayout newChild = (LinearLayout) newlayout.getChildAt(manualFields.getChildCount()-1);
                ((EditText) newChild.getChildAt(0)).setText("Manual Name "+(manualFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(1)).setText("Manual Date "+(manualFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(2)).setText("Manual Author "+(manualFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(3)).setText("Manual Custodian "+(manualFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(4)).setText("Manual On-Site "+(manualFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(5)).setText("Manual Off-Site "+(manualFields.getChildCount()-1));
            }
        });

        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
//                        (Context.LAYOUT_INFLATER_SERVICE);

                LinearLayout newlayout = (LinearLayout) inflater.inflate(R.layout.fields, planFields);
                LinearLayout newChild = (LinearLayout) newlayout.getChildAt(planFields.getChildCount()-1);
                ((EditText) newChild.getChildAt(0)).setText("Plan Name "+(planFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(1)).setText("Plan Date "+(planFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(2)).setText("Plan Author "+(planFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(3)).setText("Plan Custodian "+(planFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(4)).setText("Plan On-Site "+(planFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(5)).setText("Plan Off-Site "+(planFields.getChildCount()-1));
            }
        });

        addAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
//                        (Context.LAYOUT_INFLATER_SERVICE);

                LinearLayout newlayout = (LinearLayout) inflater.inflate(R.layout.fields, agreementFields);
                LinearLayout newChild = (LinearLayout) newlayout.getChildAt(agreementFields.getChildCount()-1);
                ((EditText) newChild.getChildAt(0)).setText("Agreement Name "+(agreementFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(1)).setText("Agreement Date "+(agreementFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(2)).setText("Agreement Author "+(agreementFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(3)).setText("Agreement Custodian "+(agreementFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(4)).setText("Agreement On-Site "+(agreementFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(5)).setText("Agreement Off-Site "+(agreementFields.getChildCount()-1));
            }
        });

        addDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
//                        (Context.LAYOUT_INFLATER_SERVICE);

                LinearLayout newlayout = (LinearLayout) inflater.inflate(R.layout.fields, documentFields);
                Log.e("count", newlayout.getChildCount()+"");
                LinearLayout newChild = (LinearLayout) newlayout.getChildAt(documentFields.getChildCount()-1);
                ((EditText) newChild.getChildAt(0)).setText("Document Name "+(documentFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(1)).setText("Document Date "+(documentFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(2)).setText("Document Author "+(documentFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(3)).setText("Document Custodian "+(documentFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(4)).setText("Document On-Site "+(documentFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(5)).setText("Document Off-Site "+(documentFields.getChildCount()-1));
            }
        });

        addProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
//                        (Context.LAYOUT_INFLATER_SERVICE);

                LinearLayout newlayout = (LinearLayout) inflater.inflate(R.layout.fields, procedureFields);
                Log.e("count", newlayout.getChildCount()+"");
                LinearLayout newChild = (LinearLayout) newlayout.getChildAt(procedureFields.getChildCount()-1);
                ((EditText) newChild.getChildAt(0)).setText("Manual Name "+(procedureFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(1)).setText("Manual Date "+(procedureFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(2)).setText("Manual Author "+(procedureFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(3)).setText("Manual Custodian "+(procedureFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(4)).setText("Manual On-Site "+(procedureFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(5)).setText("Manual Off-Site "+(procedureFields.getChildCount()-1));
            }
        });

        addGuideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService
//                        (Context.LAYOUT_INFLATER_SERVICE);

                LinearLayout newlayout = (LinearLayout) inflater.inflate(R.layout.fields, guidelineFields);
                Log.e("count", newlayout.getChildCount()+"");
                LinearLayout newChild = (LinearLayout) newlayout.getChildAt(guidelineFields.getChildCount()-1);
                ((EditText) newChild.getChildAt(0)).setText("Guideline Name "+(guidelineFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(1)).setText("Guideline Date "+(guidelineFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(2)).setText("Guideline Author "+(guidelineFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(3)).setText("Guideline Custodian "+(guidelineFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(4)).setText("Guideline On-Site "+(guidelineFields.getChildCount()-1));
                ((EditText) newChild.getChildAt(5)).setText("Guideline Off-Site "+(guidelineFields.getChildCount()-1));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

    private List<List<String[]>> getInput(){
        List<String[]> manuals = new ArrayList<>();

        for(int i = 0; i<(manualFields.getChildCount()); i++){
            LinearLayout current = (LinearLayout) manualFields.getChildAt(i);
            String[] manual = {
                    ((EditText) current.getChildAt(0)).getText().toString(),
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(2)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(4)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
            };

            manuals.add(manual);
        }
        List<String[]> plans = new ArrayList<>();

        for(int i = 0; i<(planFields.getChildCount()); i++){
            LinearLayout current = (LinearLayout) planFields.getChildAt(i);
            String[] plan = {
                    ((EditText) current.getChildAt(0)).getText().toString(),
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(2)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(4)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
            };

            plans.add(plan);
        }

        List<String[]> agreements = new ArrayList<>();

        for(int i = 0; i<(agreementFields.getChildCount()); i++){
            LinearLayout current = (LinearLayout) agreementFields.getChildAt(i);
            String[] agreement = {
                    ((EditText) current.getChildAt(0)).getText().toString(),
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(2)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(4)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
            };

            agreements.add(agreement);
        }

        List<String[]> documents = new ArrayList<>();

        for(int i = 0; i<(documentFields.getChildCount()); i++){
            LinearLayout current = (LinearLayout) documentFields.getChildAt(i);
            String[] document = {
                    ((EditText) current.getChildAt(0)).getText().toString(),
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(2)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(4)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
            };

            documents.add(document);
        }

        List<String[]> procedures = new ArrayList<>();

        for(int i = 0; i<(procedureFields.getChildCount()); i++){
            LinearLayout current = (LinearLayout) procedureFields.getChildAt(i);
            String[] procedure = {
                    ((EditText) current.getChildAt(0)).getText().toString(),
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(2)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(4)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
            };

            procedures.add(procedure);
        }

        List<String[]> guidelines = new ArrayList<>();

        for(int i = 0; i<(guidelineFields.getChildCount()); i++){
            LinearLayout current = (LinearLayout) guidelineFields.getChildAt(i);
            String[] guideline = {
                    ((EditText) current.getChildAt(0)).getText().toString(),
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(2)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(4)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
            };

            guidelines.add(guideline);
        }

        List<List<String[]>> params = new ArrayList<>();
        params.add(manuals);
        params.add(plans);
        params.add(agreements);
        params.add(documents);
        params.add(procedures);
        params.add(guidelines);

        return params;
    }

}
