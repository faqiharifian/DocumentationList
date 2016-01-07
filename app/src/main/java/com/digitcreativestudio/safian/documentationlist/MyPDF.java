package com.digitcreativestudio.safian.documentationlist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by faqih_000 on 12/14/2015.
 */
public class MyPDF extends AsyncTask<Object, Void, Void> {
    private Context mContext;
    private Activity mActivity;
    private ProgressDialog pDialog;

    public MyPDF(Context context, Activity activity){
        mContext = context;mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(mActivity);
        pDialog.setMessage("Loading..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Object... params) {
        String nameOfOffice = (String) params[0];
        String address = (String) params[1];
        String phoneOrEmail = (String) params[2];
        List<List<String[]>> others = (List<List<String[]>>) params[3];
        try {
            String dpath = "/sdcard/"+mContext.getString(R.string.app_name);
            File directory = new File(dpath);
            directory.mkdirs();

//            String fpath = "/sdcard/"+mContext.getString(R.string.app_name)+"/new.pdf";
//            File file = new File(mContext.getFilesDir(), "new.pdf");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            File file = new File(directory, sdf.format(new Date())+".pdf");
            // If file does not exists, then create it
            if (!file.exists()) {
//                file.mkdirs();
                file.createNewFile();
            }

            // step 1
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            // step 2
            PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));

//            writer.setBoxSize("art", new Rectangle(document.getPageSize()));

//            HeaderFooter event = new HeaderFooter();
//            writer.setPageEvent(event);
            // step 3
            document.open();
            // step 4


            Paragraph p = new Paragraph();
            p.setTabSettings(new TabSettings(56f));
            p.add(new Chunk("Name of Office"));
            p.add(Chunk.TABBING);
            p.add(new Chunk(": "+nameOfOffice));

            document.add(p);

            p = new Paragraph();
            p.setTabSettings(new TabSettings(56f));
            p.add(new Chunk("Address"));
            p.add(Chunk.TABBING);
            p.add(Chunk.TABBING);
            p.add(new Chunk(": "+address));

            document.add(p);

            p = new Paragraph();
            p.setTabSettings(new TabSettings(56f));
            p.add(new Chunk("Phone / Email"));
            p.add(Chunk.TABBING);
            p.add(new Chunk(": "+phoneOrEmail));

            document.add(p);

            document.add(Chunk.NEWLINE);

            float[] columnWidths = {1, 1, 1, 1, 1, 1};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            table.getDefaultCell().setUseAscender(true);
            table.getDefaultCell().setUseDescender(true);

            table.getDefaultCell().setBackgroundColor(new GrayColor(0.5f));
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell("Name");
            table.addCell("Date of Receipt");
            table.addCell("Author");
            table.addCell("Custodian");
            table.addCell("On-Site Location");
            table.addCell("Off-Site Location");

            table.setHeaderRows(1);
//            table.setFooterRows(1);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(new GrayColor(0.75f));
            cell.setPhrase(new Phrase("Manuals"));
            cell.setColspan(6);
            table.addCell(cell);

            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            List<String[]> manuals = others.get(0);
            Log.e("count manuals", manuals.size()+"");
            for(String[] manual: manuals){
                    table.addCell(manual[0]);
                    table.addCell(manual[1]);
                    table.addCell(manual[2]);
                    table.addCell(manual[3]);
                    table.addCell(manual[4]);
                    table.addCell(manual[5]);
            }

            cell = new PdfPCell();
            cell.setBackgroundColor(new GrayColor(0.75f));
            cell.setPhrase(new Phrase("Plans"));
            cell.setColspan(6);
            table.addCell(cell);

            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            List<String[]> plans = others.get(1);
            for(String[] plan: plans){
                    table.addCell(plan[0]);
                    table.addCell(plan[1]);
                    table.addCell(plan[2]);
                    table.addCell(plan[3]);
                    table.addCell(plan[4]);
                    table.addCell(plan[5]);
            }

            cell = new PdfPCell();
            cell.setBackgroundColor(new GrayColor(0.75f));
            cell.setPhrase(new Phrase("Agreements"));
            cell.setColspan(6);
            table.addCell(cell);

            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            List<String[]> agreements = others.get(2);
            for(String[] agreement: agreements){
                    table.addCell(agreement[0]);
                    table.addCell(agreement[1]);
                    table.addCell(agreement[2]);
                    table.addCell(agreement[3]);
                    table.addCell(agreement[4]);
                    table.addCell(agreement[5]);
            }

            cell = new PdfPCell();
            cell.setBackgroundColor(new GrayColor(0.75f));
            cell.setPhrase(new Phrase("Documents"));
            cell.setColspan(6);
            table.addCell(cell);

            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            List<String[]> documents = others.get(3);
            for(String[] doc: documents){
                    table.addCell(doc[0]);
                    table.addCell(doc[1]);
                    table.addCell(doc[2]);
                    table.addCell(doc[3]);
                    table.addCell(doc[4]);
                    table.addCell(doc[5]);
            }

            cell = new PdfPCell();
            cell.setBackgroundColor(new GrayColor(0.75f));
            cell.setPhrase(new Phrase("Procedures"));
            cell.setColspan(6);
            table.addCell(cell);

            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            List<String[]> procedures = others.get(4);
            for(String[] procedure: procedures){
                    table.addCell(procedure[0]);
                    table.addCell(procedure[1]);
                    table.addCell(procedure[2]);
                    table.addCell(procedure[3]);
                    table.addCell(procedure[4]);
                    table.addCell(procedure[5]);
            }

            cell = new PdfPCell();
            cell.setBackgroundColor(new GrayColor(0.75f));
            cell.setPhrase(new Phrase("Guidelines"));
            cell.setColspan(6);
            table.addCell(cell);

            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            List<String[]> guidelines = others.get(5);
            for(String[] guideline: guidelines){
                    table.addCell(guideline[0]);
                    table.addCell(guideline[1]);
                    table.addCell(guideline[2]);
                    table.addCell(guideline[3]);
                    table.addCell(guideline[4]);
                    table.addCell(guideline[5]);
            }

            table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell("");
            table.addCell("Last Update");
            table.addCell("Verification");
            table.addCell("Sent to Head Office");
            table.addCell("Sent to Branch");
            table.addCell("Sent to Off-Site");

            table.addCell("Data Verified by");
            table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            table.addCell((String)params[4]);
            table.addCell((String)params[5]);
            table.addCell((String)params[6]);
            table.addCell((String)params[7]);
            table.addCell((String)params[8]);

            document.add(table);
//            Log.e("Page count", document.getP)
            // step 5
            document.close();
//            writer.close();


            // Create a reader
            PdfReader reader = new PdfReader(file.getAbsolutePath());
            // Create a stamper
            File newFile = new File(directory, sdf.format(new Date())+".pdf");
            if (!newFile.exists()) {
//                newFile.mkdirs();
                newFile.createNewFile();
            }
            PdfStamper stamper
                    = new PdfStamper(reader, new FileOutputStream(newFile.getAbsolutePath()));
            // Loop over the pages and add a header to each page
            int n = reader.getNumberOfPages();
            for (int i = 1; i <= n; i++) {
                Rectangle rect = reader.getPageSize(i);

                ColumnText.showTextAligned(stamper.getOverContent(i),
                        Element.ALIGN_RIGHT, new Phrase(String.format("Page %d of %d", i, n)),
                        rect.getRight() - 50, rect.getTop() - 30, 0);
            }
////            // Close the stamper
            stamper.close();
            reader.close();
            Log.d("Suceess", "Sucess " + file.getAbsolutePath());
            Log.d("Suceess", "Sucess " + newFile.getAbsolutePath());

            file.delete();

            PackageManager packageManager = mActivity.getPackageManager();
            Intent testIntent = new Intent(Intent.ACTION_VIEW);
            testIntent.setType("application/pdf");
            List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.size() > 0 && newFile.isFile()) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(newFile), "application/pdf");
                mActivity.startActivity(intent);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pDialog.dismiss();
        MainActivity.pdfGenerated = true;
    }
}
