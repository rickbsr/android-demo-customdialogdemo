package com.rick.codingbydumbbell;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

    public void onClick(View view) {
        createCustomDialog();
    }

    private void createCustomDialog() {

        // Step 1. 建立一個 Dialog 對象。 這邊也可以使用 AlertDialog，只是 AlertDialog 預設帶有陰影特效，會影響佈局，所以這邊不使用。
        final Dialog customDialog = new Dialog(context);

        // Step 2. 設定標題樣式，這邊使用的是「無標題樣式」。
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Step 3. 利用 LayoutInflater 產生 View。
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_costom, null);

        // Step 4. 將 View 填充進去
        customDialog.setContentView(dialogView);

        // Step 5. Show
        customDialog.show();

        // Step 6. 設定寬、高，避免 Layout 顯示不完全
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        Window window = customDialog.getWindow();

        params.copyFrom(window.getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params); // 這行一定要在 Show 之後才會生效

        // Step 7. View Binder
        final EditText etHeight = dialogView.findViewById(R.id.et_height);
        final EditText etWeight = dialogView.findViewById(R.id.et_weight);
        Button btn_calc = dialogView.findViewById(R.id.btn_clac);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);

        // Step 8. Listener
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();
                if (!height.isEmpty() && !weight.isEmpty()) {
                    double h = Double.parseDouble(height);
                    double w = Double.parseDouble(weight);
                    double bmi = w / Math.pow((h / 100), 2);
                    String s = String.format("%.2f", bmi);
                    Toast.makeText(context, " BMI is : " + s, Toast.LENGTH_SHORT).show();
                    customDialog.dismiss();
                } else Toast.makeText(context, "請輸入身高或體重", Toast.LENGTH_SHORT).show();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }
}
