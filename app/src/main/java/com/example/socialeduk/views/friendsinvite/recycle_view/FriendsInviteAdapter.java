package com.example.socialeduk.views.friendsinvite.recycle_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.Volley;
import com.example.socialeduk.R;
import com.example.socialeduk.interfaces.VolleyCallBack;
import com.example.socialeduk.models.dto.AcceptAndRefuseFriendRequest;
import com.example.socialeduk.models.dto.BlockAndSendFriendRequest;
import com.example.socialeduk.models.dto.DefaultResponse;
import com.example.socialeduk.services.BlockAndUnblockUserService;
import com.example.socialeduk.services.FriendRequestService;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class FriendsInviteAdapter extends RecyclerView.Adapter<FriendsInviteAdapter.ViewHolder>{

    private ArrayList<FriendsInviteContent> arrayList;

    public FriendsInviteAdapter(ArrayList<FriendsInviteContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    //@Override
    public FriendsInviteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_friend_invitations_result,parent,false);
        return new FriendsInviteAdapter.ViewHolder(view);
    }

    //@Override
    public void onBindViewHolder(@NonNull FriendsInviteAdapter.ViewHolder holder, int position) {
        FriendsInviteContent invite =  arrayList.get(position);

        holder.friendName.setText(invite.getName());
        holder.friendEmail.setText(invite.getEmail());
        holder.friendIcon.setImageResource(invite.getFriendIcon());
        holder.acceptFriend.setOnClickListener(view -> acceptFriendRequest(invite.getMyId(), invite.getId(), invite.getContext(), position));
        holder.blockFriend.setOnClickListener(view -> blockFriend(invite.getMyId(), invite.getId(), invite.getContext(), position));
        holder.refuseFriend.setOnClickListener(v -> refuseFriendRequest(invite.getMyId(), invite.getId(), invite.getContext(), position));
    }

    private void refuseFriendRequest(Long userId, Long friendRequestId, Context context, int position) {
        AcceptAndRefuseFriendRequest request = new AcceptAndRefuseFriendRequest();
        request.setUserId(userId);
        request.setFriendRequestId(friendRequestId);


        DefaultResponse<String> acceptResponse = new DefaultResponse<>();
        FriendRequestService friendRequestService = new FriendRequestService(Volley.newRequestQueue(context));

        try{
            friendRequestService.refuseFriendRequest(request, new VolleyCallBack() {
                @Override
                public void onSuccess(String response) {
                    JSONObject obj = null;
                    try {
                        obj = (JSONObject) new
                                JSONTokener(response).nextValue();
                        acceptResponse.setMessage(obj.getString("message"));
                        acceptResponse.setStatus(obj.getString("status"));
                        acceptResponse.setData(obj.getString("data"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (acceptResponse.getData().equals("true")){
                        Toast.makeText(context, "Solicitação recusada com sucesso!", Toast.LENGTH_LONG).show();
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                    } else if (acceptResponse.getData().equals("null")) {
                        Toast.makeText(context, "Solicitação já recusada ou não encontrada!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch (JSONException e){
            Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }
    }

    private void acceptFriendRequest(Long userId, Long friendRequestId, Context context, int position) {
        AcceptAndRefuseFriendRequest request = new AcceptAndRefuseFriendRequest();
        request.setUserId(userId);
        request.setFriendRequestId(friendRequestId);


        DefaultResponse<String> acceptResponse = new DefaultResponse<>();
        FriendRequestService friendRequestService = new FriendRequestService(Volley.newRequestQueue(context));

        try{
            friendRequestService.acceptFriendRequest(request, new VolleyCallBack() {
                @Override
                public void onSuccess(String response) {
                    JSONObject obj = null;
                    try {
                        obj = (JSONObject) new
                                JSONTokener(response).nextValue();
                        acceptResponse.setMessage(obj.getString("message"));
                        acceptResponse.setStatus(obj.getString("status"));
                        acceptResponse.setData(obj.getString("data"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (acceptResponse.getData().equals("true")){
                        Toast.makeText(context, "Solicitação aceita com sucesso!", Toast.LENGTH_LONG).show();
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                    } else if (acceptResponse.getData().equals("null")) {
                        Toast.makeText(context, "Usuário já bloqueado ou não encontrado!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch (JSONException e){
            Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }
    }

    private void blockFriend(Long idSender, Long idRecivier, Context context, int position) {
        BlockAndSendFriendRequest request = new BlockAndSendFriendRequest();
        request.setSender(idSender);
        request.setReceiver(idRecivier);


        DefaultResponse<String> blockResponse = new DefaultResponse<>();
        BlockAndUnblockUserService blockUserService = new BlockAndUnblockUserService(Volley.newRequestQueue(context));

        try{
            blockUserService.block(request, new VolleyCallBack() {
                @Override
                public void onSuccess(String response) {
                    JSONObject obj = null;
                    try {
                        obj = (JSONObject) new
                                JSONTokener(response).nextValue();
                        blockResponse.setMessage(obj.getString("message"));
                        blockResponse.setStatus(obj.getString("status"));
                        blockResponse.setData(obj.getString("data"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (blockResponse.getData().equals("true")){
                        Toast.makeText(context, "Usuário bloqueado com sucesso!", Toast.LENGTH_LONG).show();
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                    } else if (blockResponse.getData().equals("null")) {
                        Toast.makeText(context, "Usuário já bloqueado ou não encontrado!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch (JSONException e){
            Toast.makeText(context, "Algo de errado ocorreu. Por favor, tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }
    }

    //@Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView friendName;
        TextView friendEmail;
        ImageView friendIcon;
        ImageButton acceptFriend;
        ImageButton blockFriend;
        ImageButton refuseFriend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.layout_friendsInvite_name);
            friendEmail = itemView.findViewById(R.id.layout_friendsInvite_email);
            friendIcon = itemView.findViewById(R.id.layout_friendsInvite_icon);
            acceptFriend = itemView.findViewById(R.id.layout_friendsInvite_addButton);
            blockFriend = itemView.findViewById(R.id.layout_friendsInvite_blockButton);
            refuseFriend = itemView.findViewById(R.id.layout_friendsInvite_refuseButton);
        }
    }
}
