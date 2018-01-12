# TextInputLayout

1、```TextInputLayout```和```TextInputEditText```是```material design```中的一种效果。与普通的```EditText```相比，输入效果和错误提示都做的比较好。先来看一下效果图:

![](https://github.com/zhenghuiC/TextInputLayout/blob/master/src/main/res/drawable-xhdpi/a_img.gif?raw=true)


2、使用：

- 需要先引入依赖包

```
implementation 'com.android.support:design:26.1.0'
```

- 在xml布局中引入

```
 <android.support.design.widget.TextInputLayout
        android:id="@+id/inputLayout"
        android:layout_marginTop="10dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.design.widget.TextInputEditText
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:inputType="number"
            android:hint="(TextInputLayout+TextInputEditText)"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </android.support.design.widget.TextInputLayout>
```
使用方法很简单。

3、```Editext```和```TextInputEditText```的区别

一般情况下，这两者差别不大，但是当手机切换到横屏的时候，```EditText```和```TextInputLayout```搭配，```hint```会在输入的时候变空白。下面用图片说话,注意看搭配之后的输入时候的```hint```：

![](https://github.com/zhenghuiC/TextInputLayout/blob/master/src/main/res/drawable-xhdpi/b_img.gif?raw=true)

![](https://github.com/zhenghuiC/TextInputLayout/blob/master/src/main/res/drawable-xhdpi/c_img.gif?raw=true)


因此最好还是用```TextInputLayout``` 和```TextInputEditText```进行搭配


4、栗子

- 布局文件的话我们还是使用刚刚上面贴出来的代码

- 下面是我们做的包括错误提示

```
public class MainActivity extends AppCompatActivity {
    Button button;
    TextInputLayout inputLayout ;
    private String RESULT_OK="OK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button)findViewById(R.id.btn);
        inputLayout =(TextInputLayout)findViewById(R.id.inputLayout);
        
        //用户点击确定
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取用户输入的结果
                String string =inputLayout.getEditText().getText().toString();
                inputLayout.setErrorEnabled(false);
                String result =validate(string);
                if(result.equals(RESULT_OK)) {
                    Toast.makeText(MainActivity.this, "格式正确", Toast.LENGTH_SHORT).show();
                }else{
                    showError(inputLayout,result);
                }
            }
        });
    }

    //设置错误信息提示并将焦点放到出错的地方
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    //对输入格式进行检查
    public String validate(String string){
        if(string==null || string.equals("")){
            return "手机号码不能为空！";
        }else if(string.length()!=11){
            return "手机号码长度不对！";
        }
        return RESULT_OK;
    }
}

```

***
