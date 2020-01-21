package com.halfwaiter.converttoexcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.halfwaiter.converttoexcel.model_class.CellData;
import com.halfwaiter.converttoexcel.model_class.ExcHeader;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class MainActivity extends AppCompatActivity {

    List<ExcHeader> excHeaderList;
    List<CellData> cellDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        excHeaderList = new ArrayList<>();
        cellDataList = new ArrayList<>();

        cellDataList.add(new CellData("Roshan","Posakya"));
        cellDataList.add(new CellData("Bijay","Jaishi"));
        cellDataList.add(new CellData("Santosh","Sahani"));
        cellDataList.add(new CellData("Ashish","Shakya"));
        cellDataList.add(new CellData("Jayandra","Shahi"));
        cellDataList.add(new CellData("Bipuya","Bhujel"));

        excHeaderList.add(new ExcHeader("First Name","Last Name",cellDataList));
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }

    public void createExcelFile() {

//        final String fileName = "TodoList.xls";
//
//        //Saving file in external storage
//        File sdCard = Environment.getExternalStorageDirectory();
//        File directory = new File(sdCard.getAbsolutePath() + "/javatechig.todo");
//
//        //create directory if not exist
//        if(!directory.isDirectory()){
//            directory.mkdirs();
//        }
//
//        //file path
//        File file = new File(directory, fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;
        WritableFont ledgerFont = new WritableFont(
                WritableFont.createFont("Arial"),
                WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false,
                UnderlineStyle.NO_UNDERLINE, Colour.GRAY_80);
        WritableCellFormat titleformat = new WritableCellFormat(ledgerFont);



        FileOutputStream fos = null;
        File file = null;
        try {

            String str_path = getApplicationContext().getExternalFilesDir(null).getAbsolutePath();
       
            file = new File(str_path, getString(R.string.app_name) + ".xls");


            workbook = Workbook.createWorkbook(file, wbSettings);
            WritableSheet sheet = workbook.createSheet("MyShoppingList", 0);
            sheet.addCell(new Label(0, 0, "First Name", titleformat)); // column and row
            sheet.addCell(new Label(1, 0, "Last Name", titleformat));
            sheet.setColumnView(0,10);

            for (int i=0; i<cellDataList.size();i++){

                sheet.addCell(new Label(0, i+1, cellDataList.get(i).getD_cell0()));
                sheet.addCell(new Label(1, i+1, cellDataList.get(i).getD_cell1()));
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        } finally {
            File f = new File(file.getAbsoluteFile().toString());
            if (f.exists() && f.canRead()) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" +
                        f.getAbsolutePath()));
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "BODY");
                startActivity(Intent.createChooser(sendIntent, "Email:"));
            } else {
                Toast.makeText(MainActivity.this, "send",
                        Toast.LENGTH_LONG).show();
            }
            Toast.makeText(MainActivity.this, "Excel Sheet Generated "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }

    }

    public void save(View view) {

            createExcelFile();

    }


}
