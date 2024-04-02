export interface CapWebviewPluginPlugin {
  // echo(options: { value: string }): Promise<{ value: string }>;
  showWebView(options: { url: string }): Promise<void>;
}
