export interface WebviewPluginPlugin {
  // echo(options: { value: string }): Promise<{ value: string }>;
  showWebView(options: { url: string }): Promise<void>;
}
