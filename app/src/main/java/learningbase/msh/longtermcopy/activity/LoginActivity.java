package learningbase.msh.longtermcopy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import learningbase.msh.longtermcopy.R;
import learningbase.msh.longtermcopy.network.RetrofitActivity;
import learningbase.msh.longtermcopy.presenter.LoginPresenter;
import learningbase.msh.longtermcopy.view.LoginView;

/**
 * Created by lenovo on 2017/8/8.
 */

public class LoginActivity extends RetrofitActivity implements LoginView {
    private LoginPresenter presenter;
    private Button login;

    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login("123","456");
            }

        });
    }

    @Override
    public void loginsucc() {
        Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginError() {
        Toast.makeText(this,"登陆失败",Toast.LENGTH_LONG).show();
    }
}
