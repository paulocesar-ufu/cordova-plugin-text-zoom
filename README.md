
@totalpave/cordova-plugin-text-zoom
-----------------------------------

This plugin provides a `tp-text-zoom` preference to control the text zoom.

The default value is `auto`, in which case no text zoom is explicitly set on the webview. Any other value is parsed as an int and passed to [setTextZoom](https://developer.android.com/reference/android/webkit/WebSettings#setTextZoom(int)). If the value is not parseable as an integer, then `auto` will be used.


This setting will override the OS accessiblity text zoom settings, so generally speaking it's advisable to not change this setting and to instead make your UI adaptive to text size changes. However, sometimes this is not possible. Setting `tp-text-zoom` to `100` will force the webview to render text at it's "normal" size.

Note this only applies to the webview content. Native UI such as dialogs will still be scaled according to the OS accessibility settings.

Example Usage (`config.xml`):

```xml
...
<platform name="android">
    <preference name="tp-text-zoom" value="100" />
</platform>
```
