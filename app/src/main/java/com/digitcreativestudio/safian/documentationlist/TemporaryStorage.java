package com.digitcreativestudio.safian.documentationlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by faqih_000 on 1/5/2016.
 */
public class TemporaryStorage {
    Context context;
    SharedPreferences pref;
    Editor edit;

    public static final String PREF_NAME = "DocumentList";
    public static final String PREF_MANUALS = "Manuals";
    public static final String PREF_PLANS = "Plans";
    public static final String PREF_AGREEMENTS = "Agreements";
    public static final String PREF_DOCUMENTS = "Documents";
    public static final String PREF_PROCEDURES = "Procedures";
    public static final String PREF_GUIDELINES = "Guidelines";

    public static final String KEY_ISSET = "isset";

    public static final String KEY_OFFICE = "office";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PHONE_OR_EMAIL = "phoneOrEmail";
    public static final String KEY_LAST_UPDATE = "lastUpdate";
    public static final String KEY_VERIFICATION = "verification";
    public static final String KEY_SENT_TO_HEAD_OFFICE = "sendToHeadOffice";
    public static final String KEY_SENT_TO_BRANCH = "sentToBranch";
    public static final String KEY_SENT_TO_OFFSITE = "sentToOffsite";
    public TemporaryStorage(Context context){
        this.context = context;
    }

    public void saveData(String[] details, List<List<String[]>> documents){
        pref = context.getSharedPreferences(PREF_NAME, 0);
        edit = pref.edit();
        edit.putString(KEY_OFFICE, details[0]);
        edit.putString(KEY_ADDRESS, details[1]);
        edit.putString(KEY_PHONE_OR_EMAIL, details[2]);
        edit.putString(KEY_LAST_UPDATE, details[3]);
        edit.putString(KEY_VERIFICATION, details[4]);
        edit.putString(KEY_SENT_TO_HEAD_OFFICE, details[5]);
        edit.putString(KEY_SENT_TO_BRANCH, details[6]);
        edit.putString(KEY_SENT_TO_OFFSITE, details[7]);
        edit.apply();

        saveManuals(documents.get(0));
        savePlans(documents.get(1));
        saveAgreements(documents.get(2));
        saveDocuments(documents.get(3));
        saveProcedures(documents.get(4));
        saveGuidelines(documents.get(5));
    }

    public void saveManuals(List<String[]> manuals){
        pref = context.getSharedPreferences(PREF_MANUALS, 0);
        edit = pref.edit();
        int i = 0;
        for(String[] manual : manuals){
            edit.putString(String.valueOf(i), serialize(manual));
            ++i;
        }
        edit.apply();
    }
    public void savePlans(List<String[]> plans){
        pref = context.getSharedPreferences(PREF_PLANS, 0);
        edit = pref.edit();
        int i = 0;
        for (String[] plan : plans){
            edit.putString(String.valueOf(i), serialize(plan));
            ++i;
        }
        edit.apply();
    }
    public void saveAgreements(List<String[]> agreements){
        pref = context.getSharedPreferences(PREF_AGREEMENTS, 0);
        edit = pref.edit();
        int i = 0;
        for (String[] agreement : agreements){
            edit.putString(String.valueOf(i), serialize(agreement));
            ++i;
        }
        edit.apply();
    }
    public void saveDocuments(List<String[]> documents){
        pref = context.getSharedPreferences(PREF_DOCUMENTS, 0);
        edit = pref.edit();
        int i = 0;
        for (String[] document : documents){
            edit.putString(String.valueOf(i), serialize(document));
            ++i;
        }
        edit.apply();
    }
    public void saveProcedures(List<String[]> procedures){
        pref = context.getSharedPreferences(PREF_PROCEDURES, 0);
        edit = pref.edit();
        int i = 0;
        for (String[] procedure : procedures){
            edit.putString(String.valueOf(i), serialize(procedure));
            ++i;
        }
        edit.apply();
    }
    public void saveGuidelines(List<String[]> guidelines){
        pref = context.getSharedPreferences(PREF_GUIDELINES, 0);
        edit = pref.edit();
        int i = 0;
        for (String[] guideline : guidelines){
            edit.putString(String.valueOf(i), serialize(guideline));
            ++i;
        }
        edit.apply();
    }

    public String[] getDetails(){
        pref = context.getSharedPreferences(PREF_NAME, 0);
        return new String[]{
                pref.getString(KEY_OFFICE, ""),
                pref.getString(KEY_ADDRESS, ""),
                pref.getString(KEY_PHONE_OR_EMAIL, ""),
                pref.getString(KEY_LAST_UPDATE, ""),
                pref.getString(KEY_VERIFICATION, ""),
                pref.getString(KEY_SENT_TO_HEAD_OFFICE, ""),
                pref.getString(KEY_SENT_TO_BRANCH, ""),
                pref.getString(KEY_SENT_TO_OFFSITE, "")
        };
    }

    public List<List<String[]>> getDocumentsPref(){
        List<List<String[]>> documentsPref = new LinkedList<>();

        pref = context.getSharedPreferences(PREF_MANUALS, 0);
        List<String[]> documents = new LinkedList<>();
        Map<String, ?> prefMap = this.pref.getAll();
        for(int i = 0; i < (prefMap.entrySet()).size(); i++){
            String[] items = deserialize(pref.getString(String.valueOf(i), ""));
            documents.add(items);
        }
        documentsPref.add(documents);

        pref = context.getSharedPreferences(PREF_PLANS, 0);
        documents = new LinkedList<>();
        prefMap = this.pref.getAll();
        for(int i = 0; i < (prefMap.entrySet()).size(); i++){
            String[] items = deserialize(pref.getString(String.valueOf(i), ""));
            documents.add(items);
        }
        documentsPref.add(documents);

        pref = context.getSharedPreferences(PREF_AGREEMENTS, 0);
        documents = new LinkedList<>();
        prefMap = this.pref.getAll();
        for(int i = 0; i < (prefMap.entrySet()).size(); i++){
            String[] items = deserialize(pref.getString(String.valueOf(i), ""));
            documents.add(items);
        }
        documentsPref.add(documents);

        pref = context.getSharedPreferences(PREF_DOCUMENTS, 0);
        documents = new LinkedList<>();
        prefMap = this.pref.getAll();
        for(int i = 0; i < (prefMap.entrySet()).size(); i++){
            String[] items = deserialize(pref.getString(String.valueOf(i), ""));
            documents.add(items);
        }
        documentsPref.add(documents);

        pref = context.getSharedPreferences(PREF_PROCEDURES, 0);
        documents = new LinkedList<>();
        prefMap = this.pref.getAll();
        for(int i = 0; i < (prefMap.entrySet()).size(); i++){
            String[] items = deserialize(pref.getString(String.valueOf(i), ""));
            documents.add(items);
        }
        documentsPref.add(documents);

        pref = context.getSharedPreferences(PREF_GUIDELINES, 0);
        documents = new LinkedList<>();
        prefMap = this.pref.getAll();
        for(int i = 0; i < (prefMap.entrySet()).size(); i++){
            String[] items = deserialize(pref.getString(String.valueOf(i), ""));
            documents.add(items);
        }
        documentsPref.add(documents);

        return documentsPref;
    }

    public void isset(boolean isset){
        pref = context.getSharedPreferences(PREF_NAME, 0);
        edit = pref.edit();
        edit.putBoolean(KEY_ISSET, isset).apply();
    }

    public boolean isset(){
        pref = context.getSharedPreferences(PREF_NAME, 0);
        return pref.getBoolean(KEY_ISSET, false);
    }

    public void clearAll(){
        pref = context.getSharedPreferences(PREF_NAME, 0);
        edit = pref.edit();
        edit.clear().apply();

        pref = context.getSharedPreferences(PREF_MANUALS, 0);
        edit = pref.edit();
        edit.clear().apply();

        pref = context.getSharedPreferences(PREF_PLANS, 0);
        edit = pref.edit();
        edit.clear().apply();

        pref = context.getSharedPreferences(PREF_AGREEMENTS, 0);
        edit = pref.edit();
        edit.clear().apply();

        pref = context.getSharedPreferences(PREF_DOCUMENTS, 0);
        edit = pref.edit();
        edit.clear().apply();

        pref = context.getSharedPreferences(PREF_PROCEDURES, 0);
        edit = pref.edit();
        edit.clear().apply();

        pref = context.getSharedPreferences(PREF_GUIDELINES, 0);
        edit = pref.edit();
        edit.clear().apply();
    }

    private String serialize(String[] arrayString){
        String result = Arrays.toString(arrayString);
        return result.substring(1, result.length()-1);
    }

    private String[] deserialize(String string){
        return string.split(", ");
    }
}
