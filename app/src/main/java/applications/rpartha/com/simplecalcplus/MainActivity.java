package applications.rpartha.com.simplecalcplus;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Double.NaN;

/**
 * Created by tillu on 7/29/2017
 */

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero;
    Button plus, minus, times, divide, pow;
    Button leftParen, rightParen, equals, clear, dec;
    TextView inputView, resView;

    CustomSettings customSettings;
    ColorPicker colorPicker;

    private Operation operation;
    private double first = Double.NaN, second = 0;

    /*
     * Available operations stored in an enum
     */
    private enum Operation{
        ADD,
        SUBSTRACT,
        MULTIPLY,
        DIVIDE,
        POWER,
        EQUAL
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Log.e("Error"+Thread.currentThread().getStackTrace()[2],paramThrowable.getLocalizedMessage());
            }
        });*/

        init();

        customSettings = new CustomSettings(this);
        colorPicker = new ColorPicker(this);
        refreshColors();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Drawable drawable;
        for(int i = 0; i < menu.size(); i++) {
            drawable = menu.getItem(i).getIcon();
            drawable.mutate();
            drawable.setColorFilter(customSettings.getBackgroundColor(), PorterDuff.Mode.SRC_ATOP);
            drawable.setAlpha(255);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = new Intent(this, CustomSettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onResume(){
        super.onResume();
        refreshColors();
    }

    /**
     * Initiate all views and set onClickListeners
     */
    private void init(){
        one = (Button)findViewById(R.id.button_1);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "1");
            }
        });
        two = (Button)findViewById(R.id.button_2);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "2");
            }
        });
        three = (Button)findViewById(R.id.button_3);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "3");
            }
        });
        four = (Button)findViewById(R.id.button_4);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "4");
            }
        });
        five = (Button)findViewById(R.id.button_5);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "5");
            }
        });
        six = (Button)findViewById(R.id.button_6);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "6");
            }
        });
        seven = (Button)findViewById(R.id.button_7);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "7");
            }
        });
        eight = (Button)findViewById(R.id.button_8);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "8");
            }
        });
        nine = (Button)findViewById(R.id.button_9);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "9");
            }
        });
        zero = (Button)findViewById(R.id.button_0);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "0");
            }
        });
        plus = (Button)findViewById(R.id.button_add);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                operation = Operation.ADD;
                resView.setText(String.valueOf(first) + " + ");
                inputView.setText(null);
            }
        });
        minus = (Button)findViewById(R.id.button_substract);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                operation = Operation.SUBSTRACT;
                resView.setText(String.valueOf(first) + " + ");
                inputView.setText(null);

            }
        });
        times = (Button)findViewById(R.id.button_multiply);
        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                operation = Operation.MULTIPLY;
                resView.setText(String.valueOf(first) + " × ");
                inputView.setText(null);
            }
        });
        divide = (Button)findViewById(R.id.button_divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                operation = Operation.DIVIDE;
                resView.setText(String.valueOf(first) + " ÷ ");
                inputView.setText(null);
            }
        });
        pow = (Button)findViewById(R.id.button_power);
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                operation = Operation.POWER;
                resView.setText(String.valueOf(first) + " ^ ");
                inputView.setText(null);
            }
        });
        leftParen  = (Button)findViewById(R.id.button_paren_left);
        leftParen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + "(");
            }
        });
        rightParen = (Button)findViewById(R.id.button_paren_right);
        rightParen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + ")");
            }
        });
        equals = (Button)findViewById(R.id.button_equals);
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                operation = Operation.EQUAL;
                resView.setText(resView.getText().toString() + String.valueOf(second) + " = " + String.valueOf(first));
                inputView.setText(null);
                second = first;
                first = Double.NaN;
            }
        });
        clear = (Button)findViewById(R.id.button_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(null);
                resView.setText(null);
                first = NaN;
                second = NaN;
            }
        });
        dec = (Button)findViewById(R.id.button_decimal);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputView.setText(inputView.getText().toString() + ".");
            }
        });
        inputView = (TextView)findViewById(R.id.input);
        resView = (TextView)findViewById(R.id.result);
    }

    private void calculate(){
        if(!Double.isNaN(first)){
            second = Double.parseDouble(inputView.getText().toString());
            switch (operation) {
                case ADD:
                    first += second;
                    break;
                case SUBSTRACT:
                    first -= second;
                    break;
                case MULTIPLY:
                    first *= second;
                    break;
                case DIVIDE:
                    first /= second;
                    break;
                case POWER:
                    first = Math.pow(first, second);
                    break;
                case EQUAL:
                    break;
            }
        }

        else if(inputView.getText().toString() == ""){
            first = second;
        }

        else{
            first = Double.parseDouble(inputView.getText().toString());
        }
    }

    public void refreshColors(){
        ((Button)findViewById(R.id.button_1)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_2)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_3)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_4)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_5)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_6)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_7)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_8)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_9)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_0)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_add)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_substract)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_multiply)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_divide)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_power)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_paren_left)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_paren_right)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_equals)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_clear)).setTextColor(customSettings.getPrimaryColor());
        ((Button)findViewById(R.id.button_decimal)).setTextColor(customSettings.getPrimaryColor());

        findViewById(android.R.id.content).setBackgroundColor(customSettings.getBackgroundColor());

        colorPicker.setTopBottomBarsColor(customSettings.getPrimaryColor());
        colorPicker.setTextAndIconColor(customSettings.getBackgroundColor());
        inputView.setTextColor(customSettings.getPrimaryColor());
        resView.setTextColor(customSettings.getPrimaryColor());
    }
}
