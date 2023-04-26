package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declare ya shit
    TextView outputText;
    TextView calcs;
    EditText disc;
    EditText val;
  // double[] tempvalues = {658457, 297132, 244223, 239206, 209551, 195137, 144473, 121543, 120027, 83482, 80454, 77261, 72634, 67971, 67615, 64736, 64180, 52122, 51400, 51241, 51149, 47909, 46880, 45988, 45226, 38959, 37232, 36493, 35497, 34884, 33533, 32622, 32547, 28521, 27484, 25655, 24810, 24260, 23998, 21333, 20958, 20695, 20197, 19982, 19973, 19767, 19702, 19579, 19097, 18344, 18318, 17253, 16573, 16467, 15808, 15628, 15463, 15357, 15312, 15165, 15086, 14643, 14203, 12890, 12669, 12662, 12412, 11742, 11197, 11041, 10543, 10396, 10396, 9877, 9870, 9563, 9562, 9443, 9423, 9311, 9117, 8978, 8717, 8563, 8456, 7979, 7928, 7759, 7702, 7497, 7105, 7102, 6951, 6891, 6877, 6802, 6785, 6613, 6506, 6465, 6380, 6286, 6070, 5484, 5390, 4964, 1289};
   //double[] tempvalues = {1, 3, 4, 15, 27, 30, 33, 36, 41, 42, 44, 46, 72, 72};
    ArrayList<Double> values = new ArrayList<>();
    ArrayList<String> discs = new ArrayList<>();
    ArrayList<DataItem> dataItems = new ArrayList<>();
    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //how to connect the java and XML
        outputText = findViewById(R.id.outputText);
        outputText.setText("PRUTTIPONG MACHINE!!");
        calcs = findViewById(R.id.avrage);
        recycler = findViewById(R.id.recycler);




        disc = findViewById(R.id.inputText);
        val = findViewById(R.id.inputText2);

        DataItem myObj = new DataItem("Helsinki", 321312);
        DataItem my2ndObj = new DataItem("Esboo", 1231);

        calcs.setText(String.format("Example!\n%s: %.2f\n%s: %.2f",
                myObj.getName(),
                myObj.getVal(),
                my2ndObj.getName(),
                my2ndObj.getVal()));

     //   for (double value: tempvalues){
       //     values.add(value);
       // }

    }

    public void buttonHandler(View view) {
        String arrayString = val.getText().toString();
        String arrayStringdisc = disc.getText().toString();

        if (arrayString.isEmpty() || arrayStringdisc.isEmpty()) {
            outputText.setText("Input fields cannot be empty!");
        } else {
            double arrayadd = Double.parseDouble(arrayString);
            values.add(arrayadd);
            discs.add(arrayStringdisc);
            dataItems = Statistics.getSampleData(values, discs);

            CustomAdapter adapter = new CustomAdapter(this, dataItems);
            recycler.setAdapter(adapter);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            double lq = Statistics.calcLQ(values);
            double hq = Statistics.calcHQ(values);

            if (Double.isNaN(lq) || Double.isNaN(hq)) {
                calcs.setText("The calculations will commence when you have input 4 variables");
            } else {
                calcs.setText(String.format("Avrage: %.2f\nMedian: %.2f\nSDV: %.2f \nMax: %.2f \nMin: %.2f \nAvg: %.2f \nMode: %.2f \nLQ: %.2f \nHQ: %.2f \nQR: %.2f",
                        Statistics.calcMean(values),
                        Statistics.calcMedian(values),
                        Statistics.calcSDV(values),
                        Statistics.calcMax(values),
                        Statistics.calcMin(values),
                        Statistics.calcAVG(values),
                        Statistics.calcMode(values),
                        lq,
                        hq,
                        Statistics.calcQR(lq, hq)
                ));
            }

        }

    }}

