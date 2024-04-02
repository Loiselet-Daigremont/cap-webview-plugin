import Foundation

@objc public class WebviewPlugin: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }

    @objc public func showWebView(_ url: String) -> Void {
        
    }
}
