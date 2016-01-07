package com.digitcreativestudio.safian.documentationlist;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    int currentPage = 0;
    ViewFlipper vf;
    LinearLayout manualFields, planFields, agreementFields, documentFields, procedureFields, guidelineFields;

    ImageButton prev, add, next, generate;

    TemporaryStorage preferences;
    public static boolean pdfGenerated = false;

    @Override
    protected void onStop() {
        super.onStop();
        preferences.clearAll();
        preferences.saveData(
                new String[]{
                        ((EditText) findViewById(R.id.name_of_office)).getText().toString(),
                        ((EditText) findViewById(R.id.address)).getText().toString(),
                        ((EditText) findViewById(R.id.phone_or_email)).getText().toString(),
                        ((EditText) findViewById(R.id.last_update)).getText().toString(),
                        ((EditText) findViewById(R.id.verification)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_head_office)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_branch)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_offsite)).getText().toString()
                },
                getInput()
        );
        preferences.isset(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        preferences = new TemporaryStorage(getApplicationContext());

        manualFields = (LinearLayout) findViewById(R.id.manual_fields);
        planFields = (LinearLayout) findViewById(R.id.plan_fields);
        agreementFields = (LinearLayout) findViewById(R.id.agreement_fields);
        documentFields = (LinearLayout) findViewById(R.id.document_fields);
        procedureFields = (LinearLayout) findViewById(R.id.procedure_fields);
        guidelineFields = (LinearLayout) findViewById(R.id.guideline_fields);

        vf = (ViewFlipper) findViewById(R.id.viewFlipper);

        prev = (ImageButton) findViewById(R.id.prev);
        add = (ImageButton) findViewById(R.id.add);
        next = (ImageButton) findViewById(R.id.next);
        generate = (ImageButton) findViewById(R.id.generate);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vf.getDisplayedChild() > 0) {
                    vf.setInAnimation(MainActivity.this, R.anim.in_from_left);
                    vf.setOutAnimation(MainActivity.this, R.anim.out_to_right);
                    vf.showPrevious();
                    generate.animate().setDuration(500).alpha(0).setListener(new Animation(generate));
                    next.animate().setDuration(500).alpha(1).setListener(new Animation(next));
                }
                if(vf.getDisplayedChild() == 0){
                    prev.animate().setDuration(500).alpha(0).setListener(new Animation(prev));
                    add.animate().setDuration(500).alpha(0).setListener(new Animation(add));
                }
                if(vf.getDisplayedChild() > 0 && vf.getDisplayedChild() < vf.getChildCount()-1){
                    add.animate().setDuration(500).alpha(1).setListener(new Animation(add));
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vf.getDisplayedChild() < vf.getChildCount()) {
                    vf.setInAnimation(MainActivity.this, R.anim.in_from_right);
                    vf.setOutAnimation(MainActivity.this, R.anim.out_to_left);
                    vf.showNext();
                    prev.animate().setDuration(500).alpha(1).setListener(new Animation(prev));
                }
                if(vf.getDisplayedChild() == vf.getChildCount()-1){
                    next.animate().setDuration(500).alpha(0).setListener(new Animation(next));
                    add.animate().setDuration(500).alpha(0).setListener(new Animation(add));
                    generate.animate().setDuration(500).alpha(1).setListener(new Animation(generate));
                }
                if(vf.getDisplayedChild() > 0 && vf.getDisplayedChild() < vf.getChildCount()-1){
                    add.animate().setDuration(500).alpha(1).setListener(new Animation(add));
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPositionFlipper = vf.getDisplayedChild();
                View currentViewFlipper = vf.getCurrentView();
                LayoutInflater inflater = getLayoutInflater();
                LinearLayout container = null;
                RelativeLayout newView = null;

                switch(currentPositionFlipper){
                    case 1:
                        container = (LinearLayout) currentViewFlipper.findViewById(R.id.manual_fields);
                        inflater.inflate(R.layout.item_manual, container);
                        break;
                    case 2:
                        container = (LinearLayout) currentViewFlipper.findViewById(R.id.plan_fields);
                        inflater.inflate(R.layout.item_plan, container);
                        break;
                    case 3:
                        container = (LinearLayout) currentViewFlipper.findViewById(R.id.agreement_fields);
                        inflater.inflate(R.layout.item_agreement, container);
                        break;
                    case 4:
                        container = (LinearLayout) currentViewFlipper.findViewById(R.id.document_fields);
                        inflater.inflate(R.layout.item_document, container);
                        break;
                    case 5:
                        container = (LinearLayout) currentViewFlipper.findViewById(R.id.procedure_fields);
                        inflater.inflate(R.layout.item_procedure, container);
                        break;
                    case 6:
                        container = (LinearLayout) currentViewFlipper.findViewById(R.id.guideline_fields);
                        inflater.inflate(R.layout.item_guideline, container);
                        break;
                }
                newView = (RelativeLayout) container.getChildAt(container.getChildCount()-1);
                ((EditText) newView.getChildAt(1)).setText(((EditText) newView.getChildAt(1)).getText().toString() + container.getChildCount());
                ((EditText) newView.getChildAt(3)).setText(((EditText) newView.getChildAt(3)).getText().toString() + container.getChildCount());
                ((EditText) newView.getChildAt(5)).setText(((EditText) newView.getChildAt(5)).getText().toString() + container.getChildCount());
                ((EditText) newView.getChildAt(7)).setText(((EditText) newView.getChildAt(7)).getText().toString() + container.getChildCount());
                ((EditText) newView.getChildAt(9)).setText(((EditText) newView.getChildAt(9)).getText().toString() + container.getChildCount());
                ((EditText) newView.getChildAt(11)).setText(((EditText) newView.getChildAt(11)).getText().toString() + container.getChildCount());
                newView.findViewById(R.id.delete).setOnClickListener(new DeleteListener(newView));
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfOffice = ((EditText) findViewById(R.id.name_of_office)).getText().toString();
                String address = ((EditText) findViewById(R.id.address)).getText().toString();
                String phoneOrEmail = ((EditText) findViewById(R.id.phone_or_email)).getText().toString();
                MyPDF myPDF = new MyPDF(getApplicationContext(), MainActivity.this);
                myPDF.execute(nameOfOffice, address, phoneOrEmail, getInput(),
                        ((EditText) findViewById(R.id.last_update)).getText().toString(),
                        ((EditText) findViewById(R.id.verification)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_head_office)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_branch)).getText().toString(),
                        ((EditText) findViewById(R.id.sent_to_offsite)).getText().toString()
                );
            }
        });

        loadData();
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
        if (id == R.id.action_reset) {
            preferences.clearAll();
            preferences.isset(false);
            Intent i = getIntent();
            finish();
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private List<List<String[]>> getInput(){
        List<String[]> manuals = new ArrayList<>();

        for(int i = 0; i<(manualFields.getChildCount()); i++){
            RelativeLayout current = (RelativeLayout) manualFields.getChildAt(i);
            String[] manual = {
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
                    ((EditText) current.getChildAt(7)).getText().toString(),
                    ((EditText) current.getChildAt(9)).getText().toString(),
                    ((EditText) current.getChildAt(11)).getText().toString(),
            };

            manuals.add(manual);
        }
        List<String[]> plans = new ArrayList<>();

        for(int i = 0; i<(planFields.getChildCount()); i++){
            RelativeLayout current = (RelativeLayout) planFields.getChildAt(i);
            String[] plan = {
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
                    ((EditText) current.getChildAt(7)).getText().toString(),
                    ((EditText) current.getChildAt(9)).getText().toString(),
                    ((EditText) current.getChildAt(11)).getText().toString(),
            };

            plans.add(plan);
        }

        List<String[]> agreements = new ArrayList<>();

        for(int i = 0; i<(agreementFields.getChildCount()); i++){
            RelativeLayout current = (RelativeLayout) agreementFields.getChildAt(i);
            String[] agreement = {
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
                    ((EditText) current.getChildAt(7)).getText().toString(),
                    ((EditText) current.getChildAt(9)).getText().toString(),
                    ((EditText) current.getChildAt(11)).getText().toString(),
            };

            agreements.add(agreement);
        }

        List<String[]> documents = new ArrayList<>();

        for(int i = 0; i<(documentFields.getChildCount()); i++){
            RelativeLayout current = (RelativeLayout) documentFields.getChildAt(i);
            String[] document = {
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
                    ((EditText) current.getChildAt(7)).getText().toString(),
                    ((EditText) current.getChildAt(9)).getText().toString(),
                    ((EditText) current.getChildAt(11)).getText().toString(),
            };

            documents.add(document);
        }

        List<String[]> procedures = new ArrayList<>();

        for(int i = 0; i<(procedureFields.getChildCount()); i++){
            RelativeLayout current = (RelativeLayout) procedureFields.getChildAt(i);
            String[] procedure = {
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
                    ((EditText) current.getChildAt(7)).getText().toString(),
                    ((EditText) current.getChildAt(9)).getText().toString(),
                    ((EditText) current.getChildAt(11)).getText().toString(),
            };

            procedures.add(procedure);
        }

        List<String[]> guidelines = new ArrayList<>();

        for(int i = 0; i<(guidelineFields.getChildCount()); i++){
            RelativeLayout current = (RelativeLayout) guidelineFields.getChildAt(i);
            String[] guideline = {
                    ((EditText) current.getChildAt(1)).getText().toString(),
                    ((EditText) current.getChildAt(3)).getText().toString(),
                    ((EditText) current.getChildAt(5)).getText().toString(),
                    ((EditText) current.getChildAt(7)).getText().toString(),
                    ((EditText) current.getChildAt(9)).getText().toString(),
                    ((EditText) current.getChildAt(11)).getText().toString(),
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

    private class Animation implements Animator.AnimatorListener{
        View view;
        Animation(View view){
            this.view = view;
        }

        @Override
        public void onAnimationStart(Animator animator) {
            if(view.getAlpha() == 0)
                view.setVisibility(View.VISIBLE);
            view.setClickable(false);
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            if(view.getAlpha() == 0) {
                view.setClickable(false);
                view.setVisibility(View.INVISIBLE);
            }else
                view.setClickable(true);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }

    private void loadData(){
        LayoutInflater inflater = getLayoutInflater();
        if(preferences.isset()){
            String[] details = preferences.getDetails();
            ((EditText) findViewById(R.id.name_of_office)).setText(details[0]);
            ((EditText) findViewById(R.id.address)).setText(details[1]);
            ((EditText) findViewById(R.id.phone_or_email)).setText(details[2]);
            ((EditText) findViewById(R.id.last_update)).setText(details[3]);
            ((EditText) findViewById(R.id.verification)).setText(details[4]);
            ((EditText) findViewById(R.id.sent_to_head_office)).setText(details[5]);
            ((EditText) findViewById(R.id.sent_to_branch)).setText(details[6]);
            ((EditText) findViewById(R.id.sent_to_offsite)).setText(details[7]);

            List<List<String[]>> documentsPref = preferences.getDocumentsPref();

            List<String[]> manuals = documentsPref.get(0);

            for(int i = 0; i<(manuals.size()); i++){
                String[] manual = manuals.get(i);
                RelativeLayout current = (RelativeLayout)((LinearLayout)inflater.inflate(R.layout.item_manual, manualFields)).getChildAt(i);
                ((EditText) current.getChildAt(1)).setText(manual[0]);
                ((EditText) current.getChildAt(3)).setText(manual[1]);
                ((EditText) current.getChildAt(5)).setText(manual[2]);
                ((EditText) current.getChildAt(7)).setText(manual[3]);
                ((EditText) current.getChildAt(9)).setText(manual[4]);
                ((EditText) current.getChildAt(11)).setText(manual[5]);
                current.findViewById(R.id.delete).setOnClickListener(new DeleteListener(current));
            }

            List<String[]> plans = documentsPref.get(1);

            for(int i = 0; i<(plans.size()); i++){
                String[] plan = plans.get(i);
                RelativeLayout current = (RelativeLayout)((LinearLayout)inflater.inflate(R.layout.item_plan, planFields)).getChildAt(i);
                ((EditText) current.getChildAt(1)).setText(plan[0]);
                ((EditText) current.getChildAt(3)).setText(plan[1]);
                ((EditText) current.getChildAt(5)).setText(plan[2]);
                ((EditText) current.getChildAt(7)).setText(plan[3]);
                ((EditText) current.getChildAt(9)).setText(plan[4]);
                ((EditText) current.getChildAt(11)).setText(plan[5]);
                current.findViewById(R.id.delete).setOnClickListener(new DeleteListener(current));
            }

            List<String[]> agreements = documentsPref.get(2);

            for(int i = 0; i<(agreements.size()); i++){
                String[] agreement = agreements.get(i);
                RelativeLayout current = (RelativeLayout)((LinearLayout)inflater.inflate(R.layout.item_agreement, agreementFields)).getChildAt(i);
                ((EditText) current.getChildAt(1)).setText(agreement[0]);
                ((EditText) current.getChildAt(3)).setText(agreement[1]);
                ((EditText) current.getChildAt(5)).setText(agreement[2]);
                ((EditText) current.getChildAt(7)).setText(agreement[3]);
                ((EditText) current.getChildAt(9)).setText(agreement[4]);
                ((EditText) current.getChildAt(11)).setText(agreement[5]);
                current.findViewById(R.id.delete).setOnClickListener(new DeleteListener(current));
            }

            List<String[]> documents = documentsPref.get(3);

            for(int i = 0; i<(documents.size()); i++){
                String[] document = documents.get(i);
                RelativeLayout current = (RelativeLayout)((LinearLayout)inflater.inflate(R.layout.item_document, documentFields)).getChildAt(i);
                ((EditText) current.getChildAt(1)).setText(document[0]);
                ((EditText) current.getChildAt(3)).setText(document[1]);
                ((EditText) current.getChildAt(5)).setText(document[2]);
                ((EditText) current.getChildAt(7)).setText(document[3]);
                ((EditText) current.getChildAt(9)).setText(document[4]);
                ((EditText) current.getChildAt(11)).setText(document[5]);
                current.findViewById(R.id.delete).setOnClickListener(new DeleteListener(current));
            }

            List<String[]> procedures = documentsPref.get(4);

            for(int i = 0; i<(procedures.size()); i++){
                String[] procedure = procedures.get(i);
                RelativeLayout current = (RelativeLayout)((LinearLayout)inflater.inflate(R.layout.item_procedure, procedureFields)).getChildAt(i);
                ((EditText) current.getChildAt(1)).setText(procedure[0]);
                ((EditText) current.getChildAt(3)).setText(procedure[1]);
                ((EditText) current.getChildAt(5)).setText(procedure[2]);
                ((EditText) current.getChildAt(7)).setText(procedure[3]);
                ((EditText) current.getChildAt(9)).setText(procedure[4]);
                ((EditText) current.getChildAt(11)).setText(procedure[5]);
                current.findViewById(R.id.delete).setOnClickListener(new DeleteListener(current));
            }

            List<String[]> guidelines = documentsPref.get(5);

            for(int i = 0; i<(guidelines.size()); i++){
                String[] guideline = guidelines.get(i);
                RelativeLayout current = (RelativeLayout)((LinearLayout)inflater.inflate(R.layout.item_guideline, guidelineFields)).getChildAt(i);
                ((EditText) current.getChildAt(1)).setText(guideline[0]);
                ((EditText) current.getChildAt(3)).setText(guideline[1]);
                ((EditText) current.getChildAt(5)).setText(guideline[2]);
                ((EditText) current.getChildAt(7)).setText(guideline[3]);
                ((EditText) current.getChildAt(9)).setText(guideline[4]);
                ((EditText) current.getChildAt(11)).setText(guideline[5]);
                current.findViewById(R.id.delete).setOnClickListener(new DeleteListener(current));
            }
        }
    }

    private class DeleteListener implements View.OnClickListener{
        View view;
        public DeleteListener(View view){
            this.view = view;
        }
        @Override
        public void onClick(View view) {
            ((ViewGroup)this.view.getParent()).removeView(this.view);
        }
    }
}
