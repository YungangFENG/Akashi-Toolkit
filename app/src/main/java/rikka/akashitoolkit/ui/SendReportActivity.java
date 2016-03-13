package rikka.akashitoolkit.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import rikka.akashitoolkit.R;
import rikka.akashitoolkit.utils.IntentUtils;

/**
 * Created by Rikka on 2016/3/13.
 */
public class SendReportActivity extends BaseActivity {
    public static final String EXTRA_EMAIL_BODY =
            "rikka.akashitoolkit.EMAIL_BODY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_send_report);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_EMAIL_BODY)) {
            handleSendEmail(intent);
        } else {
            throw new RuntimeException("Crash test!");
        }
    }

    private void handleSendEmail(final Intent intent) {
        new AlertDialog.Builder(this)
                .setTitle("崩溃了..."/*R.string.app_crash_title*/)
                .setMessage("将log以邮件形式发送给开发者以帮助解决问题"/*R.string.app_crash_message*/)
                .setPositiveButton("发送邮件"/*R.string.app_crash_send_email*/, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendEmail(intent.getStringExtra(EXTRA_EMAIL_BODY));
                        finish();
                    }
                })
                .setNegativeButton("取消"/*R.string.app_crash_send_cancel*/, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                })
                .show();
    }

    private void sendEmail(String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "rikka@xing.moe", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Akashi Toolkit crash log");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        //startActivity(Intent.createChooser(intent, "Send crash log by Email"));

//        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent = Intent.createChooser(intent, "send via"/*getString(R.string.send_via)*/);
        if (IntentUtils.isValid(this, intent)) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "没有邮件客户端的样子"/*R.string.app_crash_no_email_client*/, Toast.LENGTH_SHORT).show();
        }

    }
}