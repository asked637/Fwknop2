package biz.incomsystems.fwknop2;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sonelli.juicessh.pluginlibrary.PluginContract;
//import com.sonelli.portknocker.R;

import java.util.UUID;

/**
 * Loads JuiceSSH connections from a cursor and provides an adapter
 * that can be used in a ListView or Spinner.
 */
public class ConnectionSpinnerAdapter extends CursorAdapter {

    public static final String TAG = "ConnectionAdapter";

    private LayoutInflater inflater;

    /**
     * Loads JuiceSSH connections ready for a ListView/Spinner
     *
     * @param context Context used to inflate layout
     */
    public ConnectionSpinnerAdapter(Context context) {
        super(context, null, false);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Returns the UUID connection ID for the item at a given position, or null if not available
     *
     * @param position Position of the item to get the ID of
     * @return The UUID connection ID or null if there is no connection at that position
     */
    public UUID getConnectionId(int position) {

        UUID id = null;

        if (getCursor() != null) {
            if(getCursor().moveToPosition(position)) {
                int idIndex = getCursor().getColumnIndex(PluginContract.Connections.COLUMN_ID);
                if (idIndex > -1) {
                    id = UUID.fromString(getCursor().getString(idIndex));
                }
            }
        }

        return id;

    }

    /**
     * Returns the connection name for the item at a given position, or null if not available
     *
     * @param position Position of the item to get the name of
     * @return The connection name
     */
    public String getConnectionName(int position) {

        String name = null;

        if (getCursor() != null) {
            if(getCursor().moveToPosition(position)) {
                int idIndex = getCursor().getColumnIndex(PluginContract.Connections.COLUMN_NAME);
                if (idIndex > -1) {
                    name = getCursor().getString(idIndex);
                }
            }
        }

        return name;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return inflater.inflate(R.layout.spinner_list_item, null, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        int nameColumn = cursor.getColumnIndex(PluginContract.Connections.COLUMN_NAME);

        if (nameColumn > -1) {

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            String name = cursor.getString(nameColumn);
            textView.setText(name);

        }

    }


}