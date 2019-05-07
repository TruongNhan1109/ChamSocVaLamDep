package vn.edu.tdc.tracnghiemtq.view_models;

import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * Created by IT on 10/4/2018.
 */

public class MyRadiButtonGrop {
    private ArrayList<RadioButton> radioButtons;

    public MyRadiButtonGrop(RadioButton... radioButtons) {
        this.radioButtons = new ArrayList<RadioButton>();
        for(RadioButton item: radioButtons)
        {
            item.setOnClickListener(OnClick);
            this.radioButtons.add(item);
        }
    }

    View.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(RadioButton item: radioButtons)
            {
                item.setChecked(false);
            }
            ((RadioButton) view).setChecked(true);
        }
    };
}
