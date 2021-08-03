package com.pbs.basketboardapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.basketboardapp.R;
import com.pbs.basketboardapp.MainActivity;
import com.pbs.basketboardapp.adapter.ItemBackgroundAdapter;
import com.pbs.basketboardapp.model.Background;

import java.util.ArrayList;
import java.util.List;

public class DialogBackground extends DialogFragment {

	private ListView list;
	private Background itemSelected;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		String titulo = getResources().getString(R.string.titulo_dialogo_backgroud);
		View view = getContentView();

		List<Background> lBackground = new ArrayList<Background>();
		lBackground.add(new Background(R.drawable.ic_fondo_pizarra, false));
		lBackground.add(new Background(R.drawable.ic_fondo_pizarra2, false));
		lBackground.add(new Background(R.drawable.ic_fondo_pizarra3, false));
		lBackground.add(new Background(R.drawable.ic_fondo_pizarra4, false));
		lBackground.add(new Background(R.drawable.ic_fondo_pizarra5, false));

		final ItemBackgroundAdapter adapter = new ItemBackgroundAdapter(getActivity(), lBackground);
		list = view.findViewById(R.id.list_backgrouds);
		list.setAdapter(adapter);
		list.scrollTo(0, 5);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				itemSelected = adapter.getItem(position);
			}
		});

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(titulo);
		builder.setView(view);

		builder.setPositiveButton(R.string.boton_aceptar, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (itemSelected != null)
					((MainActivity) getActivity()).changeBackground(itemSelected.getImage());
			}
		});

		return builder.create();
	}

	private View getContentView() {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		return inflater.inflate(R.layout.background_dialog, null);
	}
}