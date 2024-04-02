package lodaweb.fr;

import android.webkit.WebSettings; // Importez WebSettings
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.net.Uri;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Environment;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapWebviewPlugin")
public class CapWebviewPluginPlugin extends Plugin {

    @PluginMethod
    public void showWebView(PluginCall call) {
        String url = call.getString("url");
        if (url != null) {
            getActivity()
                    .runOnUiThread(
                            () -> {
                                FrameLayout contentView = getActivity().findViewById(android.R.id.content);
                                WebView webView = new WebView(getContext());
                                webView.setLayoutParams(
                                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                FrameLayout.LayoutParams.MATCH_PARENT));

                                // Activez JavaScript ici
                                WebSettings webSettings = webView.getSettings();
                                webSettings.setJavaScriptEnabled(true);

                                // Gestion des téléchargements
                                webView.setDownloadListener(new DownloadListener() {
                                    @Override
                                    public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                            String mimeType, long contentLength) {
                                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                                        request.setMimeType(mimeType);
                                        // // ------------------------COOKIE!!------------------------
                                        String cookies = CookieManager.getInstance().getCookie(url);
                                        request.addRequestHeader("cookie", cookies);
                                        // // ------------------------COOKIE!!------------------------
                                        request.addRequestHeader("User-Agent", userAgent);
                                        request.setDescription("Téléchargement de fichier...");
                                        request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                                        request.allowScanningByMediaScanner();
                                        request.setNotificationVisibility(
                                                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                                URLUtil.guessFileName(url, contentDisposition, mimeType));
                                        DownloadManager dm = (DownloadManager) getContext()
                                                .getSystemService(Context.DOWNLOAD_SERVICE);
                                        dm.enqueue(request);
                                        getActivity().runOnUiThread(() -> {
                                            // Affiche un message si nécessaire ou mettez à jour l'interface utilisateur
                                        });
                                    }
                                });

                                contentView.addView(webView);
                                webView.setWebViewClient(new WebViewClient());
                                webView.loadUrl(url);
                            });
            call.resolve();
        } else {
            call.reject("Must provide a URL");
        }
    }

}
