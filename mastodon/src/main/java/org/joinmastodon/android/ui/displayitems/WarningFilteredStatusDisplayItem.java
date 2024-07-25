package org.joinmastodon.android.ui.displayitems;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.joinmastodon.android.R;
import org.joinmastodon.android.fragments.BaseStatusListFragment;
import org.joinmastodon.android.model.LegacyFilter;
import org.joinmastodon.android.model.Status;
import org.joinmastodon.android.ui.OutlineProviders;

import java.util.List;

public class WarningFilteredStatusDisplayItem extends StatusDisplayItem{
	public boolean loading;
	public List<StatusDisplayItem> filteredItems;
	public LegacyFilter applyingFilter;

	public WarningFilteredStatusDisplayItem(String parentID, BaseStatusListFragment<?> parentFragment, Status status, List<StatusDisplayItem> filteredItems, LegacyFilter applyingFilter){
		super(parentID, parentFragment);
		this.status=status;
		this.filteredItems=filteredItems;
		this.applyingFilter=applyingFilter;
	}

	@Override
	public Type getType(){
		return Type.WARNING;
	}

	public static class Holder extends StatusDisplayItem.Holder<WarningFilteredStatusDisplayItem>{
		public final View warningWrap;
		public final Button showBtn;
		public final TextView text;
		public List<StatusDisplayItem> filteredItems;

		public Holder(Context context, ViewGroup parent){
			super(context, R.layout.display_item_warning, parent);
			warningWrap=findViewById(R.id.warning_wrap);
			showBtn=findViewById(R.id.reveal_btn);
			showBtn.setOnClickListener(i->item.parentFragment.onWarningClick(this));
			itemView.setOnClickListener(v->item.parentFragment.onWarningClick(this));
			text=findViewById(R.id.text);
		}

		@Override
		public void onBind(WarningFilteredStatusDisplayItem item){
			filteredItems=item.filteredItems;
			String title=item.applyingFilter.title;
			text.setText(item.parentFragment.getString(R.string.sk_filtered, title));

			if(item.inset){
				itemView.setClipToOutline(true);
				itemView.setOutlineProvider(OutlineProviders.roundedRect(12));
			}
		}

		@Override
		public void onClick(){}
	}
}
