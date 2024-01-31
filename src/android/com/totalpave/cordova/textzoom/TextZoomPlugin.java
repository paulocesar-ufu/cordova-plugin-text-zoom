/*
   Copyright 2024 Total Pave Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.totalpave.cordova.textzoom;

import android.view.View;
import android.webkit.WebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class TextZoomPlugin extends CordovaPlugin {

    public static final String TAG = "TextZoomPlugin";

    @Override
    protected void pluginInitialize() {
        String value = preferences.getString("tp-text-zoom", "auto");
        Integer maybeZoom = null;

        if (!value.equals("auto")) {
            try {
                maybeZoom = Integer.parseInt(value);
            }
            catch (NumberFormatException ex) {
                LOG.w(TAG, "Unable to parse tp-text-zoom value, falling back to \"auto\"");
                ex.printStackTrace();
                maybeZoom = null;
            }
        }

        if (maybeZoom != null) {
            final int zoom = maybeZoom;
            cordova.getActivity().runOnUiThread(() -> {
                View view = this.webView.getView();
                if (!(view instanceof WebView)) {
                    LOG.w(TAG, "Webview doesn't appear to be an instance of WebView, not applying text zoom.");
                    return;
                }

                WebView webview = (WebView) view;
                webview.getSettings().setTextZoom(zoom);
            });
        }
    }
}
