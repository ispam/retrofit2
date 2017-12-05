package destinum.tech.requests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {

    private Context mContext;
    private List<GitHubRepo> values;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
        super(context, R.layout.list_item_pagination, values);

        this.mContext = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textView =  row.findViewById(R.id.textView);

        GitHubRepo item = values.get(position);
        String message = item.getName();
        textView.setText(message);

        return row;
    }
}
