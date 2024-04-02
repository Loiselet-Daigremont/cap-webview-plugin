import { registerPlugin } from '@capacitor/core';

import type { CapWebviewPluginPlugin } from './definitions';

const CapWebviewPlugin = registerPlugin<CapWebviewPluginPlugin>('CapWebviewPlugin', {
  web: () => import('./web').then(m => new m.CapWebviewPluginWeb()),
});

export * from './definitions';
export { CapWebviewPlugin };
