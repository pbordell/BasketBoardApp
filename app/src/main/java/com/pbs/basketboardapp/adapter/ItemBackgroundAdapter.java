package com.pbs.basketboardapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.basketboardapp.R;
import com.pbs.basketboardapp.model.Background;

import java.util.List;

public class ItemBackgroundAdapter extends ArrayAdapter<Background> {

	private final Activity context;
	private final List<Background> lBackgrounds;

	public ItemBackgroundAdapter(Activity context, List<Background> lBackgrounds) {
		super(context, R.layout.single_background, lBackgrounds);
		this.context = context;
		this.lBackgrounds = lBackgrounds;
	}

	static class ViewHolder {
		protected ImageView image;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = null;

		if (convertView == null) {

			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.single_background, null);

			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.image = view.findViewById(R.id.img_background);
			viewHolder.checkbox = view.findViewById(R.id.checkbox_background);
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(lBackgrounds.get(position));

		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(lBackgrounds.get(position));
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.image.setImageResource(lBackgrounds.get(position).getImage());
		holder.checkbox.setChecked(lBackgrounds.get(position).isSelected());

		return view;
	}
}