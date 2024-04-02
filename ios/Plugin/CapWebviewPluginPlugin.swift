import Foundation
import Capacitor
import WebKit

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapWebviewPluginPlugin)
public class CapWebviewPluginPlugin: CAPPlugin {
    private let ionic  = CapWebviewPlugin()

    // @objc public func echo(_ value: String) -> String {
    //     print(value)
    //     return value
    // }
    @objc func showWebView(_ call: CAPPluginCall) {
        guard let urlString = call.getString("url") else {
            call.reject("Must provide a URL")
            return
        }
        
        guard let url = URL(string: urlString) else {
            call.reject("Invalid URL")
            return
        }
        
        DispatchQueue.main.async {
            // Créez une configuration pour la WKWebView
            let webConfiguration = WKWebViewConfiguration()
            // Activez JavaScript dans la configuration
            webConfiguration.preferences.javaScriptEnabled = true
            
            // Utilisez la configuration lors de la création de la WKWebView
            let webView = WKWebView(frame: UIScreen.main.bounds, configuration: webConfiguration)
            let request = URLRequest(url: url)
            webView.load(request)
            
            if let viewController = self.bridge?.viewController {
                viewController.view.addSubview(webView)
            }
            
            call.resolve()
        }
    }
}
