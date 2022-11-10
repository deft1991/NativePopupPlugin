package com.deftfun.nativepopup;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.unity3d.player.UnityPlayer;

import java.util.Calendar;

public class Bridge {
    static AlertDialog alertDialog;

    public Bridge() {
    }

    public static void ShowDialogNeutral(String title, String message, String accept, String neutral, String decline) {
        DismissCurrentAlert();
        alertDialog = (new AlertDialog.Builder(UnityPlayer.currentActivity)).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("MobileDialogNeutral", "OnAcceptCallBack", "0");
            }
        });
        alertDialog.setButton2(neutral, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("MobileDialogNeutral", "OnNeutralCallBack", "1");
            }
        });
        alertDialog.setButton3(decline, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("MobileDialogNeutral", "OnDeclineCallBack", "2");
            }
        });
        alertDialog.show();
    }

    public static void ShowDialogConfirm(String title, String message, String yes, String no) {
        DismissCurrentAlert();
        alertDialog = (new AlertDialog.Builder(UnityPlayer.currentActivity)).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("MobileDialogConfirm", "OnYesCallBack", "0");
            }
        });
        alertDialog.setButton2(no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("MobileDialogConfirm", "OnNoCallBack", "1");
            }
        });
        alertDialog.show();
    }

    public static void ShowDialogInfo(String title, String message, String ok) {
        DismissCurrentAlert();
        alertDialog = (new AlertDialog.Builder(UnityPlayer.currentActivity)).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("MobileDialogInfo", "OnOkCallBack", "0");
            }
        });
        alertDialog.show();
    }

    public static void DismissCurrentAlert() {
        if (alertDialog != null) {
            alertDialog.hide();
        }

    }

    public static void ShowDatePicker(int year, int month, int day) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(UnityPlayer.currentActivity, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s = String.format("%d-%d-%d %d:%d:%d", year, monthOfYear + 1, dayOfMonth, 0, 0, 0);
                UnityPlayer.UnitySendMessage("MobileDateTimePicker", "PickerClosedEvent", s);
            }
        }, year, month - 1, day);
        datePickerDialog.show();
    }

    public static void ShowTimePicker() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(11);
        int minute = c.get(12);
        TimePickerDialog timePickerDialog = new TimePickerDialog(UnityPlayer.currentActivity, new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                int yeah = c.get(1);
                int day = c.get(5);
                int month = c.get(2);
                Calendar cx = Calendar.getInstance();
                String s = String.format("%d-%d-%d %d:%d:%d", yeah, month + 1, day, hourOfDay, minute, 0);
                UnityPlayer.UnitySendMessage("MobileDateTimePicker", "PickerClosedEvent", s);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}
