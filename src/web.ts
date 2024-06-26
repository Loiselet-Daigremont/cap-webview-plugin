import { WebPlugin } from '@capacitor/core';

import type { CapWebviewPluginPlugin } from './definitions';

export class CapWebviewPluginWeb extends WebPlugin implements CapWebviewPluginPlugin {
  // async echo(options: { value: string }): Promise<{ value: string }> {
  //   console.log('ECHO', options);
  //   return options;
  // }


  async showWebView(options: { url: string }): Promise<void> {
    // Utilisation triviale de 'options' pour éviter les avertissements
    console.warn(
      `La fonction WebView.showWebView n'est pas disponible sur la plateforme web. Tentative d'accès à l'URL : ${options.url}`,
    );
  }
}
