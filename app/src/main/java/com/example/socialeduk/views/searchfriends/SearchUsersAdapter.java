package com.example.socialeduk.views.searchfriends;

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
import com.example.socialeduk.models.dto.BlockAndSendFriendRequest;
import com.example.socialeduk.models.dto.DefaultResponse;
import com.example.socialeduk.services.UserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class SearchUsersAdapter extends RecyclerView.Adapter<SearchUsersAdapter.ViewHolder> {
    private ArrayList<SearchUserContent> arrayList;

    public SearchUsersAdapter(ArrayList<SearchUserContent> arrayList){
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public SearchUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_search_users_result,parent,false);
        return new SearchUsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUsersAdapter.ViewHolder holder, int position) {
        SearchUserContent user =  arrayList.get(position);

        //holder.friendIcon.setImageResource(user.friendIcon);
        holder.friendName.setText(user.getFriendName());
        holder.friendEmail.setText(user.getFriendEmail());
        holder.add.setOnClickListener(view -> sendFriendRequest(user.getMyId(), user.getFriendId(), user.getContext(), position));
        holder.block.setOnClickListener(view -> blockFriend(user.getMyId(), user.getFriendId(), user.getContext(), position));

    }

    private void sendFriendRequest(Long idSender, Long idRecivier, Context context, int position) {
        BlockAndSendFriendRequest request = new BlockAndSendFriendRequest();
        request.setSender(idSender);
        request.setReceiver(idRecivier);


        DefaultResponse<String> friendRequestResponse = new DefaultResponse<>();
        UserService userService = new UserService(Volley.newRequestQueue(context));

        try{
            userService.sendFriendRequest(request, new VolleyCallBack() {
                @Override
                public void onSuccess(String response) {
                    JSONObject obj = null;
                    try {
                        obj = (JSONObject) new
                                JSONTokener(response).nextValue();
                        friendRequestResponse.setMessage(obj.getString("message"));
                        friendRequestResponse.setStatus(obj.getString("status"));
                        friendRequestResponse.setData(obj.getString("data"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    if (friendRequestResponse.getData().equals("true")){
                        Toast.makeText(context, "Solicitacao enviada com sucesso.", Toast.LENGTH_LONG).show();
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                    } else if (friendRequestResponse.getData().equals("null")) {
                        Toast.makeText(context, "Solicitacao ja enviada ou usuario nao encontrado", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch (JSONException e){
            Toast.makeText(context, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }
    }

    private void blockFriend(Long idSender, Long idRecivier, Context context, int position) {
        BlockAndSendFriendRequest request = new BlockAndSendFriendRequest();
        request.setSender(idSender);
        request.setReceiver(idRecivier);


        DefaultResponse<String> blockResponse = new DefaultResponse<>();
        UserService userService = new UserService(Volley.newRequestQueue(context));

        try{
            userService.blockUser(request, new VolleyCallBack() {
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
                        Toast.makeText(context, "Usuario bloqueado com sucesso.", Toast.LENGTH_LONG).show();
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                    } else if (blockResponse.getData().equals("null")) {
                        Toast.makeText(context, "Usuario ja bloqueado ou nao encontrado", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                                "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(context, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                            "persistir, contate o administrador", Toast.LENGTH_LONG).show();
                }
            });
        }catch (JSONException e){
            Toast.makeText(context, "Algo de errado ocorreu. Por favor tente novamente. Se o erro " +
                    "persistir, contate o administrador", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView friendIcon;
        TextView friendName;
        TextView friendEmail;
        ImageButton add;
        ImageButton block;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendIcon = itemView.findViewById(R.id.layout_resultSearchUsers_icon);
            friendName = itemView.findViewById(R.id.layout_resultSearchUsers_name);
            friendEmail = itemView.findViewById(R.id.layout_resultSearchUsers_email);
            add = itemView.findViewById(R.id.layout_resultSearchUsers_addButton);
            block = itemView.findViewById(R.id.layout_resultSearchUsers_blockButton);
        }
    }


}
