package learningbase.msh.longtermcopy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import learningbase.msh.longtermcopy.R;
import learningbase.msh.longtermcopy.bean.Account;
import learningbase.msh.longtermcopy.network.RetrofitActivity;
import learningbase.msh.longtermcopy.presenter.MainPresenter;
import learningbase.msh.longtermcopy.util.AllCapTransformationMethod;
import learningbase.msh.longtermcopy.util.Utils;
import learningbase.msh.longtermcopy.view.MainView;

public class MainActivity extends RetrofitActivity implements MainView {
    private MainPresenter presenter;

    Button get, get2, post;
    TextView show;
    private ua.naiksoftware.stomp.client.StompClient StompClient;
    EditText input;

    private String TAG = "MSH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        input = (EditText) findViewById(R.id.input);
        input.setTransformationMethod(new AllCapTransformationMethod(true));


        input.setText(Utils.getPhone_BRAND());

        show = (TextView) findViewById(R.id.show);
        get = (Button) findViewById(R.id.get);
        get2 = (Button) findViewById(R.id.get2);
        post = (Button) findViewById(R.id.post);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDatas();
            }
        });

        get2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getData();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account();
                account.setMoney(1234);
                account.setName("fdsfdsfdsf");
                presenter.post(account);
            }
        });
    }


    @Override
    public void showView() {
        Toast.makeText(this, "ShowView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "onError()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccs(List<Account> da) {
        Toast.makeText(this, da.size() + "", Toast.LENGTH_SHORT).show();
        show.setText(da.toString());
    }

    @Override
    public void onSucc(Account account) {
        Toast.makeText(this, account.toString() + "", Toast.LENGTH_SHORT).show();
        show.setText(account.toString());
    }

    @Override
    public void showPostSucc(String msg) {
        show.setText(msg);
    }

}
