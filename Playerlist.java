public class PlayerList extends ArrayAdapter<Player> {
    private Activity context;
    ArrayList<Player> gamePlayers;
    public PlayerList(@NonNull Activity context, ArrayList<Player> gamePlayers) {
        super(context, R.layout.player_list_item_new,gamePlayers);
        this.context = (Activity) context;
        this.gamePlayers = gamePlayers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.player_list_item_new, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.name);
        TextView textViewPos = (TextView) listViewItem.findViewById(R.id.curposition);
        TextView textViewBal = (TextView) listViewItem.findViewById(R.id.balance);

        Player player = gamePlayers.get(position);
        textViewName.setText(player.getUserName());
        textViewPos.setText(player.getCurPos());
        textViewBal.setText(player.getBalance());

        return listViewItem;
    }
}
