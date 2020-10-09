        package com.example.buper.Adapter;

        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.recyclerview.widget.RecyclerView;

        import com.example.buper.Item.Item_Transaksi;
        import com.example.buper.Menu.Menu_Detail_Keranjang;
        import com.example.buper.Menu.Menu_Utama;
        import com.example.buper.Menu.Upload_Bukti_Bayar;
        import com.example.buper.R;
        import com.example.buper.Server.Network;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.List;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        public class Adapter_Transaksi extends RecyclerView.Adapter<Adapter_Transaksi.MyViewHolder> {
                Context context;
                List<Item_Transaksi> menu;
                ProgressDialog loading ;
        public Adapter_Transaksi(Context context, List<Item_Transaksi> data_menu) {
                this.context = context;
                this.menu= data_menu;
                }
        @Override
        public Adapter_Transaksi.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(context).inflate(R.layout.list_transaksi, parent, false);
            Adapter_Transaksi.MyViewHolder holder = new Adapter_Transaksi.MyViewHolder(view);
                return holder;
                }
        @Override
        public void onBindViewHolder(final Adapter_Transaksi.MyViewHolder holder, final int position) {

                holder.nama.setText(menu.get(position).getNama_buper());
                holder.harga.setText(menu.get(position).getJumlah());
                final String Status=menu.get(position).getStatus();
                if (Status.equals("Menunggu Verifikasi")){
                    holder.Upload.setVisibility(View.GONE);
                    holder.Status.setText("Menunggu Verifikasi");
                }else if (Status.equals("verifikasi")){
                    holder.Upload.setVisibility(View.VISIBLE);
                    holder.Status.setText("Mekanisme Peminjaman Di Terima, Silahkan Bayar");
                }else if (Status.equals("havepaid")){
                    holder.Upload.setVisibility(View.GONE);
                    holder.Status.setText("Pembayaran Berhasil");
                }else if (Status.equals("Menunggu Verifikasi Pembayaran")){
                    holder.Upload.setVisibility(View.GONE);
                    holder.Status.setText("Menunggu Verifikasi Pembayaran");
                }else{
                    holder.Upload.setVisibility(View.GONE);
                    holder.Status.setText("Pembayaran Berhasil");
                }
                holder.Upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent varIntent = new Intent(context, Upload_Bukti_Bayar.class);
                        varIntent.putExtra("ID", menu.get(position).getId_transaksi());
                        context.startActivity(varIntent);

                    }
                });
                holder.hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        loading = ProgressDialog.show(context,"Loading.....",null,true,true);
                        String idkeranjang=menu.get(position).getId_transaksi();
                //                Delete(idkeranjang);
                        showdialog(idkeranjang);
                        }
                        });
            }

        private void Delete(String idkeranjang) {
                retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().delete_Transaksi(idkeranjang);
                call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                try {
                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                if (jsonRESULTS.getString("success").equals("true")){
                String pesan=jsonRESULTS.getString("message");
                Log.d("response api", jsonRESULTS.toString());
                Toast.makeText(context, ""+pesan, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, Menu_Utama.class);
                context.startActivity(intent);
                showdialogdata(pesan);
                } else{
        //                            loading.dismiss();
                String pesan=jsonRESULTS.getString("message");
                Log.d("response api", jsonRESULTS.toString());
                Toast.makeText(context, ""+pesan, Toast.LENGTH_SHORT).show();
                showdialogdata(pesan);
                }
                } catch (JSONException e) {
                e.printStackTrace();
                } catch (IOException e) {
                e.printStackTrace();
                }
                } else {
                }
                }
        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                Toast.makeText(context, "Gangguan Pada Server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
        //                    RegisterGagal(pesan_regsiter);
                } catch (Exception e) {
                e.printStackTrace();
                }

                }
                });

                }


        @Override
        public int getItemCount() {
                return menu.size();
                }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView harga,nama,Status,total;
            ImageButton hapus;
            Button Upload;
            public MyViewHolder(View itemView) {
                super(itemView);
                nama = (TextView) itemView.findViewById(R.id.nama_transaksi);
                harga = (TextView) itemView.findViewById(R.id.total_transaksi);
                Status = (TextView) itemView.findViewById(R.id.status_transaksi);
                hapus = (ImageButton) itemView.findViewById(R.id.delete_transaki);
                Upload= (Button) itemView.findViewById(R.id.Upload_bukti);

            }
        }

            public void showdialog(final String data){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Apakah Anda Yakin Akan Menghapus Transaksi ini?");
        //        builder.setMessage(data);
                builder.setCancelable(true);
                builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loading.dismiss();
        //                Menu_Login.super.onBackPressed();
                    }
                });
                builder.setNegativeButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Delete(data);
        //                Toast.makeText(context,"Terima Kasih",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            public void showdialogdata(final String data){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pesan");
                builder.setMessage(data);
                builder.setCancelable(true);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loading.dismiss();
                        Intent intent=new Intent(context, Menu_Utama.class);
                        context.startActivity(intent);
        //                Menu_Login.super.onBackPressed();
                    }
                });
                builder.setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
        //                Delete(data);
        //                Toast.makeText(context,"Terima Kasih",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
