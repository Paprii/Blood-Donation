package checking_stock.shahrinkhan;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int minteger = 0;
    @Override /** declaring same method in child class as parent class */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } /** called when the attribute is starting
      * method can be call more than once
     */

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
        /*adding donators */

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }
    /* removing donators */

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }
}