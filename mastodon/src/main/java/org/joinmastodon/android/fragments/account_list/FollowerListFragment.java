package org.joinmastodon.android.fragments.account_list;

import android.net.Uri;
import android.os.Bundle;

import org.joinmastodon.android.R;
import org.joinmastodon.android.api.requests.HeaderPaginationRequest;
import org.joinmastodon.android.api.requests.accounts.GetAccountFollowers;
import org.joinmastodon.android.model.Account;

public class FollowerListFragment extends AccountRelatedAccountListFragment{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setSubtitle(initialSubtitle = getResources().getQuantityString(R.plurals.x_followers, (int)(account.followersCount%1000), account.followersCount));
	}

	@Override
	public HeaderPaginationRequest<Account> onCreateRequest(String maxID, int count){
		return new GetAccountFollowers(getCurrentInfo().id, maxID, count);
	}

	@Override
	public Uri getWebUri(Uri.Builder base) {
		return super.getWebUri(base).buildUpon()
				.appendPath(isInstanceAkkoma() ? "#followers" : "/followers").build();
	}
}
