package com.mikepenz.materialdrawer.app.calender;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.app.R;
import com.mikepenz.materialdrawer.app.utils.CalendarFont;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class Profile extends AppCompatActivity {

    private Drawer result = null;

    MaterialCalendarView widget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.drawer_item_profile_header);
        ButterKnife.bind(this);

        int value[] = {R.id.textData,R.id.textName ,R.id.textEmail ,R.id.textH,R.id.textW,R.id.radioButton ,R.id.editName,
                R.id.editEmail ,R.id.editH ,R.id.editW ,R.id.editMessage ,R.id.pfButtonDate  ,R.id.textKg ,R.id.textCm};

        CalendarFont font =new CalendarFont() ;
        font.setFonts(value ,this) ;
        // Handle Toolbar
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(Profile.this, ((Nameable) drawerItem).getName().getText(Profile.this), Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                }).build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.pfButtonDate)
      void onMinClicked() {
        showDatePickerDialog(this, null, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                widget.setMinimumDate(CalendarDay.from(year, monthOfYear, dayOfMonth));
            }
        });
    }

    public static void showDatePickerDialog(Context context, CalendarDay day,
                                            DatePickerDialog.OnDateSetListener callback) {
        if (day == null) {
            day = CalendarDay.today();
        }
        DatePickerDialog dialog = new DatePickerDialog(
                context, 0, callback, day.getYear(), day.getMonth(), day.getDay()
        );
        dialog.show();
    }
}
