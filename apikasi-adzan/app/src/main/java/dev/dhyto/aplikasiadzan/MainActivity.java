package dev.dhyto.aplikasiadzan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import dev.dhyto.aplikasiadzan.data.model.AdzanResponse;
import dev.dhyto.aplikasiadzan.data.model.AdzanResponse.DataBean;
import dev.dhyto.aplikasiadzan.data.model.AdzanResponse.DataBean.DateBean;
import dev.dhyto.aplikasiadzan.data.model.AdzanResponse.DataBean.TimingsBean;
import dev.dhyto.aplikasiadzan.data.remote.AdzanService;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // request retrofit yang bersifat asynchronous
        /*AdzanService.getAPI()
                .getPrayerTimeByCity("Makassar", "ID")
        .enqueue(new Callback<AdzanResponse>() {
            @Override
            public void onResponse(Call<AdzanResponse> call, Response<AdzanResponse> response) {
                if(response.isSuccessful()) {
                    List<DataBean> dataBeans = response.body().getData();

                    for (DataBean dataBean: dataBeans) {
                        TimingsBean timingsBean = dataBean.getTimings();
                        DateBean dateBean = dataBean.getDate();

                        Log.d("Jadwal Sholat Subuh", timingsBean.getFajr() + "in "
                        + dateBean.getReadable());
                    }
                }
            }

            @Override
            public void onFailure(Call<AdzanResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });*/
        new GetPrayerTimeTask().execute();
    }

    // gunakan asynctask untuk request retrofit yang bersifat synchronous
    private class GetPrayerTimeTask extends AsyncTask<Void, Void, AdzanResponse> {
        @Override
        protected AdzanResponse doInBackground(Void... voids) {
            Response<AdzanResponse> response = null;
            try {
                response = AdzanService.getAPI()
                        .getPrayerTimeByCity("Makassar", "ID").execute();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.body();
        }

        @Override
        protected void onPostExecute(AdzanResponse adzanResponse) {
            List<DataBean> dataBeans = adzanResponse.getData();

            for (DataBean dataBean : dataBeans) {
                TimingsBean timingsBean = dataBean.getTimings();
                DateBean dateBean = dataBean.getDate();

                Log.d("Jadwal Sholat Subuh", timingsBean.getFajr() + "in "
                        + dateBean.getReadable());
            }
        }
    }
}
