package com.pbs.basketboardapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import com.example.basketboardapp.R;
import com.pbs.basketboardapp.MainActivity;

public class DialogStyle extends DialogFragment {

    private SeekBar seekBar;

    private int colorSelected = 0;
    private int grosorSelected = 10;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String titulo = getResources().getString(R.string.titulo_dialogo_estilos);
        View view = getContentView();

        seekBar = view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                grosorSelected = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        final CharSequence[] items = {"Green", "Red", "Blue", "White", "Black"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titulo);
        builder.setView(view);
        builder.setSingleChoiceItems(items, colorSelected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                colorSelected = which;
            }

        }).setPositiveButton(R.string.boton_aceptar, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (colorSelected == 0) {
                    ((MainActivity) getActivity()).changeColor(Color.GREEN);
                } else if (colorSelected == 1) {
                    ((MainActivity) getActivity()).changeColor(Color.RED);
                } else if (colorSelected == 2) {
                    ((MainActivity) getActivity()).changeColor(Color.BLUE);
                } else if (colorSelected == 3) {
                    ((MainActivity) getActivity()).changeColor(Color.WHITE);
                } else if (colorSelected == 4) {
                    ((MainActivity) getActivity()).changeColor(Color.BLACK);
                }
                ((MainActivity) getActivity()).changeStroke(grosorSelected);
            }
        });

        return builder.create();
    }

    private View getContentView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        return inflater.inflate(R.layout.style_dialog, null);
    }
}