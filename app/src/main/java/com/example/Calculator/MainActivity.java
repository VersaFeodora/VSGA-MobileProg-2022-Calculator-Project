package com.example.Calculator;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.Calculator.databinding.ActivityMainBinding;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private double num1, num2, ans;
    private String s1, s2;
    private Button add, sub, div, mul, res, clr;
    private EditText opr1, opr2;
    private TextView w1, w2, result;
    private boolean getResult=false;

    //Check

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get data
        opr1 = (EditText) findViewById(R.id.Operand1);
        opr2 = (EditText) findViewById(R.id.Operand2);
        w1 = (TextView) findViewById(R.id.Warning1);
        w2 = (TextView) findViewById(R.id.Warning2);
        add = (Button) findViewById(R.id.plus);
        sub = (Button) findViewById(R.id.minus);
        div = (Button) findViewById(R.id.divide);
        mul = (Button) findViewById(R.id.multiply);
        res = (Button) findViewById(R.id.result);
        result = (TextView) findViewById(R.id.resultNum);
        clr = (Button) findViewById(R.id.clear);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setSelected(true);
                sub.setSelected(false);
                div.setSelected(false);
                mul.setSelected(false);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setSelected(false);
                sub.setSelected(true);
                div.setSelected(false);
                mul.setSelected(false);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setSelected(false);
                sub.setSelected(false);
                div.setSelected(true);
                mul.setSelected(false);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setSelected(false);
                sub.setSelected(false);
                div.setSelected(false);
                mul.setSelected(true);
            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opr1 = (EditText) findViewById(R.id.Operand1);
                opr2 = (EditText) findViewById(R.id.Operand2);
                s1 = opr1.getText().toString();
                s2 = opr2.getText().toString();
                if(s1.length() > 0 && s2.length() > 0){
                    num1 = Double.parseDouble(s1);
                    num2 = Double.parseDouble(s2);
                    w1.setVisibility(View.INVISIBLE);
                    w2.setVisibility(View.INVISIBLE);
                    if(add.isSelected()){
                        ans = num1 + num2;
                        result.setVisibility(View.VISIBLE);
                        result.setText(String.format("%.2f", ans));
                    } else if(sub.isSelected()){
                        ans = num1 - num2;
                        result.setVisibility(View.VISIBLE);
                        result.setText(String.format("%.2f", ans));
                    } else if(div.isSelected()){
                        if(num2 != 0){
                            ans = num1 / num2;
                            result.setVisibility(View.VISIBLE);
                            result.setText(String.format("%.2f", ans));
                        } else{
                            result.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Second operand cannot be 0!", Toast.LENGTH_SHORT).show();
                        }
                    } else if(mul.isSelected()){
                        ans = num1 * num2;
                        result.setVisibility(View.VISIBLE);
                        result.setText(String.format("%.2f", ans));
                    } else{
                        result.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Operator needs to be selected first!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    if(s1.length() == 0){
                        w1.setVisibility(View.VISIBLE);
                    } if(s2.length() == 0) {
                        w2.setVisibility(View.VISIBLE);
                    }
                    result.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Operand is needed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setSelected(false);
                sub.setSelected(false);
                div.setSelected(false);
                mul.setSelected(false);
                result.setVisibility(View.INVISIBLE);
                w1.setVisibility(View.INVISIBLE);
                w2.setVisibility(View.INVISIBLE);
                num1 = num2 = ans = 0;
                opr1.setText("");
                opr2.setText("");
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}