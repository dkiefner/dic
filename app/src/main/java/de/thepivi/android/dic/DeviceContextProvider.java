package de.thepivi.android.dic;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings.Secure;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Is used to provide context information of device.
 *
 * @author danny.kiefner
 */
public abstract class DeviceContextProvider {

    public static final String TRUE = "True";
    public static final String FALSE = "False";

    private static String formatDateTime(long dateTime, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        return dateFormat.format(dateTime);
    }

    public static String getBuildBoard() {
        return Build.BOARD;
    }

    public static String getBuildBrand() {
        return Build.BRAND;
    }

    public static String getBuildCpuAbi() {
        return Build.CPU_ABI;
    }

    public static String getBuildDevice() {
        return Build.DEVICE;
    }

    public static String getBuildDisplay() {
        return Build.DISPLAY;
    }

    public static String getBuildFingerprint() {
        return Build.FINGERPRINT;
    }

    public static String getBuildHost() {
        return Build.HOST;
    }

    public static String getBuildId() {
        return Build.ID;
    }

    public static String getBuildManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getBuildModel() {
        return Build.MODEL;
    }

    public static String getBuildProduct() {
        return Build.PRODUCT;
    }

    public static String getBuildTags() {
        return Build.TAGS;
    }

    public static long getBuildTime() {
        return Build.TIME;
    }

    public static String getBuildType() {
        return Build.TYPE;
    }

    public static String getBuildUser() {
        return Build.USER;
    }

    public static String getBuildVersionCodename() {
        return Build.VERSION.CODENAME;
    }

    public static String getBuildVersionIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String getBuildVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public static String getBuildVersionSdk() {
        return Build.VERSION.SDK;
    }

    public static int getBuildVersionSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    private static Configuration getConfiguration(Context context) {
        return context.getResources().getConfiguration();
    }

    public static float getConfigurationFontScale(Context context) {
        return getConfiguration(context).fontScale;
    }

    public static String getConfigurationHardKeyboardHidden(Context context) {
        switch (getConfiguration(context).hardKeyboardHidden) {
            case Configuration.HARDKEYBOARDHIDDEN_NO:
                return "HARDKEYBOARDHIDDEN_NO";
            case Configuration.HARDKEYBOARDHIDDEN_YES:
                return "HARDKEYBOARDHIDDEN_YES";
            default:
                return "HARDKEYBOARDHIDDEN_UNDEFINED";
        }
    }

    public static String getConfigurationKeyboard(Context context) {
        switch (getConfiguration(context).keyboard) {
            case Configuration.KEYBOARD_12KEY:
                return "KEYBOARD_12KEY";
            case Configuration.KEYBOARD_NOKEYS:
                return "KEYBOARD_NOKEYS";
            case Configuration.KEYBOARD_QWERTY:
                return "KEYBOARD_QWERTY";
            default:
                return "KEYBOARD_UNDEFINED";
        }
    }

    public static boolean getConfigurationKeyboardHidden(Context context) {
        return getConfiguration(context).keyboardHidden == Configuration.KEYBOARDHIDDEN_YES;
    }

    public static String getConfigurationLocale(Context context) {
        return getConfiguration(context).locale.toString();
    }

    public static int getConfigurationMcc(Context context) {
        return getConfiguration(context).mcc;
    }

    public static int getConfigurationMnc(Context context) {
        return getConfiguration(context).mnc;
    }

    public static String getConfigurationNavigation(Context context) {
        switch (getConfiguration(context).navigation) {
            case Configuration.NAVIGATION_DPAD:
                return "NAVIGATION_DPAD";
            case Configuration.NAVIGATION_NONAV:
                return "NAVIGATION_NONAV";
            case Configuration.NAVIGATION_TRACKBALL:
                return "NAVIGATION_TRACKBALL";
            case Configuration.NAVIGATION_WHEEL:
                return "NAVIGATION_WHEEL";
            default:
                return "NAVIGATION_UNDEFINED";
        }
    }

    public static String getConfigurationOrientation(Context context) {
        switch (getConfiguration(context).orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                return "ORIENTATION_LANDSCAPE";
            case Configuration.ORIENTATION_PORTRAIT:
                return "ORIENTATION_PORTRAIT";
            case Configuration.ORIENTATION_SQUARE:
                return "ORIENTATION_SQUARE";
            default:
                return "ORIENTATION_UNDEFINED";
        }
    }

    // TODO: xlarge fehlt
    public static String getConfigurationScreenLayout(Context context) {
        switch (getConfiguration(context).screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "SCREENLAYOUT_SIZE_LARGE";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "SCREENLAYOUT_SIZE_NORMAL";
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "SCREENLAYOUT_SIZE_SMALL";
            default:
                return "SCREENLAYOUT_SIZE_UNDEFINED";
        }
    }

    public static String getConfigurationTouchscreen(Context context) {
        switch (getConfiguration(context).orientation) {
            case Configuration.TOUCHSCREEN_FINGER:
                return "TOUCHSCREEN_FINGER";
            case Configuration.TOUCHSCREEN_NOTOUCH:
                return "TOUCHSCREEN_NOTOUCH";
            case Configuration.TOUCHSCREEN_STYLUS:
                return "TOUCHSCREEN_STYLUS";
            default:
                return "TOUCHSCREEN_UNDEFINED";
        }
    }

    public static String getCurrentDisplayLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    protected static Display getDisplay(Context context) {
        return getWindowManager(context).getDefaultDisplay();
    }

    public static float getDisplayDensity(Context context) {
        return getDisplayMetrics(context).density * 160;
    }

    public static float getDisplayDensityHeight(Context context) {
        return getDisplayMetrics(context).ydpi;
    }

    public static String getDisplayDensityString(Context context) {
        switch (getDisplayMetrics(context).densityDpi) {
            case DisplayMetrics.DENSITY_HIGH:
                return "DENSITY_HIGH";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "DENSITY_MEDIUM";
            case DisplayMetrics.DENSITY_LOW:
                return "DENSITY_LOW";
            default:
                return "DENSITY_DEFAULT";
        }
    }

    public static float getDisplayDensityWidth(Context context) {
        return getDisplayMetrics(context).xdpi;
    }

    public static int getDisplayHeight(Context context) {
        return getDisplay(context).getHeight();
    }

    private static float getDisplayInchesHeight(Context context) {
        return getDisplayMetrics(context).heightPixels / getDisplayDensityHeight(context);
    }

    private static float getDisplayInchesWidth(Context context) {
        return getDisplayMetrics(context).widthPixels / getDisplayDensityWidth(context);
    }

    protected static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager(context).getDefaultDisplay().getMetrics(metrics);

        return metrics;
    }

    public static String getDisplayResolution(Context context) {
        return String.format("%dx%d", getDisplay(context).getWidth(),
                getDisplay(context).getHeight());
    }

    public static double getDisplayResolutionInch(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        double a = Math.pow(metrics.widthPixels / metrics.xdpi, 2);
        double b = Math.pow(metrics.heightPixels / metrics.ydpi, 2);
        int c = (int) (Math.sqrt(a + b) * 100);

        return (double) c / 100;
    }

    private static float getDisplaySmallestWidth(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        float scaleFactor = metrics.density;
        float widthDp = metrics.widthPixels / scaleFactor;
        float heightDp = metrics.heightPixels / scaleFactor;
        return Math.min(widthDp, heightDp);
    }

    public static int getDisplayWidth(Context context) {
        return getDisplay(context).getWidth();
    }

    public static String getEnvironmentDataDirectory() {
        return Environment.getDataDirectory().toString();
    }

    public static String getEnvironmentDownloadCacheDirectory() {
        return Environment.getDownloadCacheDirectory().toString();
    }

    public static String getEnvironmentExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public static String getEnvironmentExternalStorageState() {
        return Environment.getExternalStorageState();
    }

    public static String getEnvironmentRootDirectory() {
        return Environment.getRootDirectory().toString();
    }

    protected static LocationManager getLocationmanager(Context context) {
        return (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
    }

    public static String getLocationManagerAllProviders(Context context) {
        StringBuilder sb = new StringBuilder();

        for (String provider : getLocationmanager(context).getAllProviders()) {
            sb.append("\n").append(provider);
        }

        return sb.toString();
    }

    public static String getLocationManagerBestProvider(Context context) {
        return getLocationmanager(context).getBestProvider(new Criteria(),
                true);
    }

    public static String getLocationManagerGpsStatus(Context context) {
        GpsStatus status = getLocationmanager(context).getGpsStatus(null);
        return null != status ? String.format(
                "maxSatellites=%d, timeToFirstFix=%d",
                status.getMaxSatellites(), status.getTimeToFirstFix())
                : "null";
    }

    public static String getLocationManagerLastKnownLocation(Context context) {
        Location location = getLocationmanager(context).getLastKnownLocation(
                getLocationManagerBestProvider(context));

        return null != location ? getLocationManagerLocationToString(location)
                : "null";
    }

    private static String getLocationManagerLocationToString(Location location) {
        StringBuilder sb = new StringBuilder();

        if (null != location) {
            sb.append("Location:");
            sb.append("\nAccuracy=").append(location.getAccuracy());
            sb.append("\nAltitude=").append(location.getAltitude());
            sb.append("\nBearing=").append(location.getBearing());
            sb.append("\nLatitude=").append(location.getLatitude());
            sb.append("\nLongitude=").append(location.getLongitude());
            sb.append("\nProvider=").append(location.getProvider());
            sb.append("\nSpeed=").append(location.getSpeed());
            sb.append("\nTime=").append(
                    formatDateTime(location.getTime(),
                            "yyyy-MM-dd hh:mm:ss:SSS"));
            sb.append("\nhasAccuracy=").append(location.hasAccuracy());
            sb.append("\nhasAltitude=").append(location.hasAltitude());
            sb.append("\nhasBearing=").append(location.hasBearing());
            sb.append("\nhasSpeed=").append(location.hasSpeed());
        }

        return sb.toString();
    }

    public static long getProcessElapsedCpuTime() {
        return Process.getElapsedCpuTime();
    }

    public static long getProcessMyPid() {
        return Process.myPid();
    }

    public static long getProcessMyTid() {
        return Process.myTid();
    }

    public static long getProcessMyUid() {
        return Process.myUid();
    }

    public static String getSecureAccessibilityEnabled(Context context) {
        return parseStringIntToStringBool(getSecureString(context,
                Secure.ACCESSIBILITY_ENABLED));
    }

    public static String getSecureAdbEnabled(Context context) {
        return parseStringIntToStringBool(getSecureString(context,
                Secure.ADB_ENABLED));
    }

    public static String getSecureAllowMockLocation(Context context) {
        return parseStringIntToStringBool(getSecureString(context,
                Secure.ALLOW_MOCK_LOCATION));
    }

    public static String getSecureAndroidId(Context context) {
        return getSecureString(context, Secure.ANDROID_ID);
    }

    public static String getSecureBackgroundData(Context context) {
        return getSecureString(context, Secure.BACKGROUND_DATA);
    }

    public static String getSecureBluetoothOn(Context context) {
        return getSecureString(context, Secure.BLUETOOTH_ON);
    }

    public static String getSecureDataRoaming(Context context) {
        return getSecureString(context, Secure.DATA_ROAMING);
    }

    public static String getSecureDefaultInputMethod(Context context) {
        return getSecureString(context, Secure.DEFAULT_INPUT_METHOD);
    }

    public static String getSecureDeviceProvisioned(Context context) {
        return getSecureString(context, Secure.DEVICE_PROVISIONED);
    }

    public static String getSecureEnableAccessibilityServices(Context context) {
        return getSecureString(context, Secure.ENABLED_ACCESSIBILITY_SERVICES);
    }

    public static String getSecureEnableInputMethods(Context context) {
        return getSecureString(context, Secure.ENABLED_INPUT_METHODS);
    }

    public static String getSecureHttpProxy(Context context) {
        return getSecureString(context, Secure.HTTP_PROXY);
    }

    public static String getSecureInstallNonMarketApps(Context context) {
        return getSecureString(context, Secure.INSTALL_NON_MARKET_APPS);
    }

    public static String getSecureLocationProvidersAllowed(Context context) {
        return getSecureString(context, Secure.LOCATION_PROVIDERS_ALLOWED);
    }

    public static String getSecureLoggingId(Context context) {
        return getSecureString(context, Secure.LOGGING_ID);
    }

    public static String getSecureNetworkPreference(Context context) {
        return getSecureString(context, Secure.NETWORK_PREFERENCE);
    }

    public static String getSecureParentalControlEnabled(Context context) {
        return getSecureString(context, Secure.PARENTAL_CONTROL_ENABLED);
    }

    public static String getSecureParentalControlRedirectUpdate(Context context) {
        return getSecureString(context, Secure.PARENTAL_CONTROL_LAST_UPDATE);
    }

    public static String getSecureParentalControlRedirectUrl(Context context) {
        return getSecureString(context, Secure.PARENTAL_CONTROL_REDIRECT_URL);
    }

    public static String getSecureSettingsClassname(Context context) {
        return getSecureString(context, Secure.SETTINGS_CLASSNAME);
    }

    protected static String getSecureString(Context context, String resourceId) {
        return Secure.getString(context.getContentResolver(), resourceId);
    }

    public static String getSecureSysPropSettingVersion(Context context) {
        return getSecureString(context, Secure.SYS_PROP_SETTING_VERSION);
    }

    public static String getSecureTtsDefaultCountry(Context context) {
        return getSecureString(context, Secure.TTS_DEFAULT_COUNTRY);
    }

    public static String getSecureTtsDefaultLang(Context context) {
        return getSecureString(context, Secure.TTS_DEFAULT_LANG);
    }

    public static String getSecureTtsDefaultPitch(Context context) {
        return getSecureString(context, Secure.TTS_DEFAULT_PITCH);
    }

    public static String getSecureTtsDefaultRate(Context context) {
        return getSecureString(context, Secure.TTS_DEFAULT_RATE);
    }

    public static String getSecureTtsDefaultSynth(Context context) {
        return getSecureString(context, Secure.TTS_DEFAULT_SYNTH);
    }

    public static String getSecureTtsDefaultVariant(Context context) {
        return getSecureString(context, Secure.TTS_DEFAULT_VARIANT);
    }

    public static String getSecureTtsUseDefaults(Context context) {
        return getSecureString(context, Secure.TTS_USE_DEFAULTS);
    }

    public static String getSecureUsbMassStorageEnabled(Context context) {
        return getSecureString(context, Secure.USB_MASS_STORAGE_ENABLED);
    }

    public static String getSecureUseGoogleMail(Context context) {
        return getSecureString(context, Secure.USE_GOOGLE_MAIL);
    }

    public static String getSecureWifiMaxDhcpRetryCount(Context context) {
        return getSecureString(context, Secure.WIFI_MAX_DHCP_RETRY_COUNT);
    }

    public static String getSecureWifiMobileDataTransitionWakelockTimeoutMs(
            Context context) {
        return getSecureString(context,
                Secure.WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS);
    }

    public static String getSecureWifiNetworksAvailableNotificationOn(
            Context context) {
        return getSecureString(context,
                Secure.WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON);
    }

    public static String getSecureWifiNetworksAvailableRepeatDelay(
            Context context) {
        return getSecureString(context,
                Secure.WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY);
    }

    public static String getSecureWifiWatchdogBackgroundCheckTimeoutMs(
            Context context) {
        return getSecureString(context,
                Secure.WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS);
    }

    public static String getSecureWifiWatchdogInitialIgnoredPingCount(
            Context context) {
        return getSecureString(context,
                Secure.WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT);
    }

    public static String getSecureWifiWatchdogMaxApChecks(Context context) {
        return getSecureString(context, Secure.WIFI_WATCHDOG_MAX_AP_CHECKS);
    }

    public static String getSecureWifiWatchdogOn(Context context) {
        return getSecureString(context, Secure.WIFI_WATCHDOG_ON);
    }

    public static String getSecureWifiWatchdogPingCount(Context context) {
        return getSecureString(context, Secure.WIFI_WATCHDOG_PING_COUNT);
    }

    public static String getSecureWifiWatchdogPingDelayMs(Context context) {
        return getSecureString(context, Secure.WIFI_WATCHDOG_PING_DELAY_MS);
    }

    public static String getSecureWifiWatchdogPingTimeoutMs(Context context) {
        return getSecureString(context, Secure.WIFI_WATCHDOG_PING_TIMEOUT_MS);
    }

    public static String getSecureWifiWatchdogWatchList(Context context) {
        return getSecureString(context, Secure.WIFI_WATCHDOG_WATCH_LIST);
    }

    protected static SensorManager getSensorManager(Context context) {
        return (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
    }

    public static String getSensorManagerSensorList(Context context) {
        StringBuilder sb = new StringBuilder();

        for (Sensor sensor : getSensorManager(context).getSensorList(
                Sensor.TYPE_ALL)) {
            sb.append("\n").append(getSensorManagerSensorToString(sensor));
        }

        return sb.toString();
    }

    public static String getSensorManagerSensorToString(Sensor sensor) {
        StringBuilder sb = new StringBuilder();

        String typ;

        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                typ = "TYPE_ACCELEROMETER";
                break;
            case Sensor.TYPE_GYROSCOPE:
                typ = "TYPE_GYROSCOPE";
                break;
            case Sensor.TYPE_LIGHT:
                typ = "TYPE_LIGHT";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                typ = "TYPE_MAGNETIC_FIELD";
                break;
            case Sensor.TYPE_ORIENTATION:
                typ = "TYPE_ORIENTATION";
                break;
            case Sensor.TYPE_PRESSURE:
                typ = "TYPE_PRESSURE";
                break;
            case Sensor.TYPE_PROXIMITY:
                typ = "TYPE_PROXIMITY";
                break;
            case Sensor.TYPE_TEMPERATURE:
                typ = "TYPE_TEMPERATURE";
                break;
            default:
                typ = "UNKNOWN";
                break;
        }

        sb.append("Sensor:");
        sb.append("\nName=").append(sensor.getName());
        sb.append("\nTyp=").append(typ);
        sb.append("\nVersion=").append(sensor.getVersion());
        sb.append("\nVendor=").append(sensor.getVendor());
        sb.append("\nResolution=").append(sensor.getResolution());
        sb.append("\nPower=").append(sensor.getPower()).append("mA");
        sb.append("\nMaximumRange=").append(sensor.getMaximumRange());

        return sb.toString();
    }

    protected static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static String getTelephonyManagerCallState(Context context) {
        switch (getTelephonyManager(context).getCallState()) {
            case TelephonyManager.CALL_STATE_IDLE:
                return "CALL_STATE_IDLE";
            case TelephonyManager.CALL_STATE_OFFHOOK:
                return "CALL_STATE_OFFHOOK";
            case TelephonyManager.CALL_STATE_RINGING:
                return "CALL_STATE_RINGING";
            default:
                return "UNKNOWN";
        }
    }

    public static String getTelephonyManagerCellLocation(Context context) {
        return null != getTelephonyManager(context).getCellLocation() ? getTelephonyManager(
                context).getCellLocation().toString()
                : "null";
    }

    public static String getTelephonyManagerDataActivity(Context context) {
        switch (getTelephonyManager(context).getDataActivity()) {
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "DATA_ACTIVITY_DORMANT";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "DATA_ACTIVITY_IN";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "DATA_ACTIVITY_INOUT";
            case TelephonyManager.DATA_ACTIVITY_NONE:
                return "DATA_ACTIVITY_NONE";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "DATA_ACTIVITY_OUT";
            default:
                return "UNKNOWN";
        }
    }

    public static String getTelephonyManagerDataState(Context context) {
        switch (getTelephonyManager(context).getDataState()) {
            case TelephonyManager.DATA_SUSPENDED:
                return "DATA_SUSPENDED";
            case TelephonyManager.DATA_CONNECTED:
                return "DATA_CONNECTED";
            case TelephonyManager.DATA_CONNECTING:
                return "DATA_CONNECTING";
            case TelephonyManager.DATA_DISCONNECTED:
                return "DATA_DISCONNECTED";
            default:
                return "UNKNOWN";
        }
    }

    public static String getTelephonyManagerDeviceId(Context context) {
        return getTelephonyManager(context).getDeviceId();
    }

    public static String getTelephonyManagerLine1Number(Context context) {
        return getTelephonyManager(context).getLine1Number();
    }

    // TODO: evtl einzeln zerlegen
    public static String getTelephonyManagerNeighboringCellInfo(Context context) {
        StringBuilder sb = new StringBuilder();

        for (NeighboringCellInfo cellInfo : getTelephonyManager(context)
                .getNeighboringCellInfo()) {
            sb.append(cellInfo.toString()).append("\n");
        }

        return sb.toString();
    }

    public static String getTelephonyManagerNetworkCountryIso(Context context) {
        return getTelephonyManager(context).getNetworkCountryIso();
    }

    public static String getTelephonyManagerNetworkOperator(Context context) {
        return getTelephonyManager(context).getNetworkOperator();
    }

    public static String getTelephonyManagerNetworkOperatorName(Context context) {
        return getTelephonyManager(context).getNetworkOperatorName();
    }

    public static String getTelephonyManagerNetworkType(Context context) {
        switch (getTelephonyManager(context).getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "NETWORK_TYPE_1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "NETWORK_TYPE_CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "NETWORK_TYPE_EDGE";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "NETWORK_TYPE_EVDO_0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "NETWORK_TYPE_EVDO_A";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "NETWORK_TYPE_GPRS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "NETWORK_TYPE_HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "NETWORK_TYPE_HSPA";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "NETWORK_TYPE_HSUPA";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "NETWORK_TYPE_UMTS";
            default:
                return "NETWORK_TYPE_UNKNOWN";
        }
    }

    public static String getTelephonyManagerPhoneType(Context context) {
        switch (getTelephonyManager(context).getPhoneType()) {
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "PHONE_TYPE_CDMA";
            case TelephonyManager.PHONE_TYPE_GSM:
                return "PHONE_TYPE_GSM";
            case TelephonyManager.PHONE_TYPE_NONE:
                return "PHONE_TYPE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    public static String getTelephonyManagerSimCountryIso(Context context) {
        return getTelephonyManager(context).getSimCountryIso();
    }

    public static String getTelephonyManagerSimOperator(Context context) {
        return getTelephonyManager(context).getSimOperator();
    }

    public static String getTelephonyManagerSimOperatorName(Context context) {
        return getTelephonyManager(context).getSimOperatorName();
    }

    public static String getTelephonyManagerSimSerialNumber(Context context) {
        return getTelephonyManager(context).getSimSerialNumber();
    }

    public static String getTelephonyManagerSimState(Context context) {
        switch (getTelephonyManager(context).getSimState()) {
            case TelephonyManager.SIM_STATE_ABSENT:
                return "SIM_STATE_ABSENT";
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                return "SIM_STATE_NETWORK_LOCKED";
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                return "SIM_STATE_PIN_REQUIRED";
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                return "SIM_STATE_PUK_REQUIRED";
            case TelephonyManager.SIM_STATE_READY:
                return "SIM_STATE_READY";
            default:
                return "SIM_STATE_UNKNOWN";
        }
    }

    public static String getTelephonyManagerSoftwareVersion(Context context) {
        return getTelephonyManager(context).getDeviceSoftwareVersion();
    }

    public static String getTelephonyManagerSubscriberId(Context context) {
        return getTelephonyManager(context).getSubscriberId();
    }

    public static String getTelephonyManagerVoiceMailAlphaTag(Context context) {
        return getTelephonyManager(context).getVoiceMailAlphaTag();
    }

    // TODO:
    // http://developer.android.com/reference/android/provider/Settings.System.html
    // TODO: http://developer.android.com/reference/android/hardware/Camera.html

    public static String getTelephonyManagerVoiceMailNumber(Context context) {
        return getTelephonyManager(context).getVoiceMailNumber();
    }

    protected static WindowManager getWindowManager(Context context) {
        return (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
    }

    protected static String parseStringIntToStringBool(String value) {
        String parsedValue;

        if (null == value) parsedValue = "Unknown";
        else if (value.toLowerCase().equals(TRUE.toLowerCase())) parsedValue = TRUE;
        else if (value.toLowerCase().equals(FALSE.toLowerCase())) parsedValue = FALSE;
        else if (value.toLowerCase().equals("1")) parsedValue = TRUE;
        else if (value.toLowerCase().equals("0")) parsedValue = FALSE;
        else parsedValue = "Unknown";

        return parsedValue;
    }

    public static String toString(Context context) {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append(toStringBuild()).append("\n\n");
            sb.append(toStringBuildVersion()).append("\n\n");
            sb.append(toStringLocalization()).append("\n\n");
            sb.append(toStringDisplay(context)).append("\n\n");
            sb.append(toStringConfiguration(context)).append("\n\n");
            sb.append(toStringSecure(context)).append("\n\n");
            sb.append(toStringTelephonyManager(context)).append("\n\n");
            sb.append(toStringEnvironment()).append("\n\n");
            sb.append(toStringSensormanager(context)).append("\n\n");
            sb.append(toStringProcess()).append("\n\n");
            sb.append(toStringLocationManager(context)).append("\n\n");
        } catch (Exception e) {
            Log.e(DeviceContextProvider.class.getSimpleName(), "", e);
        }

        return sb.toString();
    }

    public static String toStringBuild() {
        StringBuilder sb = new StringBuilder();

        sb.append("BUILD:");
        sb.append("\nBOARD=").append(getBuildBoard());
        sb.append("\nBRAND=").append(getBuildBrand());
        sb.append("\nCPU_ABI=").append(getBuildCpuAbi());
        sb.append("\nDEVICE=").append(getBuildDevice());
        sb.append("\nDISPLAY=").append(getBuildDisplay());
        sb.append("\nFINGERPRINT=").append(getBuildFingerprint());
        sb.append("\nHOST=").append(getBuildHost());
        sb.append("\nID=").append(getBuildId());
        sb.append("\nTAGS=").append(getBuildTags());
        sb.append("\nTIME=").append(getBuildTime());
        sb.append("\nTYPE=").append(getBuildType());
        sb.append("\nUSER=").append(getBuildUser());
        sb.append("\nMANUFACTURER=").append(getBuildManufacturer());
        sb.append("\nMODEL=").append(getBuildModel());
        sb.append("\nPRODUCT=").append(getBuildProduct());

        return sb.toString();
    }

    public static String toStringBuildVersion() {
        StringBuilder sb = new StringBuilder();

        sb.append("BUILD VERSION:");
        sb.append("\nRELEASE=").append(getBuildVersionRelease());
        sb.append("\nCODENAME=").append(getBuildVersionCodename());
        sb.append("\nINCREMENTAL=").append(getBuildVersionIncremental());
        sb.append("\nSDK=").append(getBuildVersionSdk());
        sb.append("\nSDK_INT=").append(getBuildVersionSdkInt());

        return sb.toString();
    }

    private static Object toStringConfiguration(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("CONFIGURATION:");
        sb.append("\nFont Scale=").append(getConfigurationFontScale(context));
        sb.append("\nHard Keyboard Hidden=").append(getConfigurationHardKeyboardHidden(context));
        sb.append("\nKeyboard=").append(getConfigurationKeyboard(context));
        sb.append("\nKeyboard Hidden=").append(getConfigurationKeyboardHidden(context));
        sb.append("\nLocale=").append(getConfigurationLocale(context));
        sb.append("\nMCC=").append(getConfigurationMcc(context));
        sb.append("\nMNC=").append(getConfigurationMnc(context));
        sb.append("\nNaviagtion=").append(getConfigurationNavigation(context));
        sb.append("\nOrientation=").append(getConfigurationOrientation(context));
        sb.append("\nScreenLayout=").append(getConfigurationScreenLayout(context));
        sb.append("\nTouchscreen=").append(getConfigurationTouchscreen(context));

        return sb.toString();
    }

    public static String toStringDisplay(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("Display:");
        sb.append("\nResolution=").append(getDisplayResolution(context));
        sb.append("\nScreen-Size=").append(getDisplayResolutionInch(context)).append("\"");
        sb.append("\nDensity=").append(getDisplayDensity(context)).append("dpi");
        sb.append("\nDensityHeight=").append(getDisplayDensityHeight(context)).append("dpi");
        sb.append("\nDensityWidth=").append(getDisplayDensityWidth(context)).append("dpi");
        sb.append("\nSmallestWidth=").append(getDisplaySmallestWidth(context)).append("dp");
        sb.append("\nInchesWidth=").append(getDisplayInchesWidth(context)).append("in");
        sb.append("\nInchesHeight=").append(getDisplayInchesHeight(context)).append("in");
        sb.append("\nDpi-Classification=").append(getDisplayDensityString(context));

        return sb.toString();
    }

    public static String toStringEnvironment() {
        StringBuilder sb = new StringBuilder();

        sb.append("Environment:");
        sb.append("\nDataDirectory=").append(getEnvironmentDataDirectory());
        sb.append("\nDownloadCacheDirectory=").append(getEnvironmentDownloadCacheDirectory());
        sb.append("\nExternalStorageDirectory=").append(getEnvironmentExternalStorageDirectory());
        sb.append("\nExternalStorageState=").append(getEnvironmentExternalStorageState());
        sb.append("\nRootDirectory=").append(getEnvironmentRootDirectory());

        return sb.toString();
    }

    public static String toStringLocalization() {
        StringBuilder sb = new StringBuilder();

        sb.append("Localization:");
        sb.append("\nCurrent System-Language=").append(getCurrentDisplayLanguage());

        return sb.toString();
    }

    private static Object toStringLocationManager(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("LocationManager:");
        sb.append("\nAllProviders=").append(getLocationManagerAllProviders(context));
        sb.append("\nBestProvider=").append(getLocationManagerBestProvider(context));
        sb.append("\nGpsStatus=").append(getLocationManagerGpsStatus(context));
        sb.append("\nLastKnownLocation=").append(getLocationManagerLastKnownLocation(context));

        return sb.toString();
    }

    private static Object toStringProcess() {
        StringBuilder sb = new StringBuilder();

        sb.append("Process:");
        sb.append("\nElapsedCpuTime=").append(getProcessElapsedCpuTime()).append("ms");
        sb.append("\nMyPid=").append(getProcessMyPid());
        sb.append("\nMyTid=").append(getProcessMyTid());
        sb.append("\nMyUid=").append(getProcessMyUid());

        return sb.toString();
    }

    public static String toStringSecure(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("Secure:");
        sb.append("\nANDROID_ID=").append(getSecureAndroidId(context));
        sb.append("\nACCESSIBILITY_ENABLED=").append(getSecureAccessibilityEnabled(context));
        sb.append("\nADB_ENABLED=").append(getSecureAdbEnabled(context));
        sb.append("\nALLOW_MOCK_LOCATION=").append(getSecureAllowMockLocation(context));

        return sb.toString();
    }

    private static Object toStringSensormanager(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("SensorManager:");
        sb.append("\nSensorList=").append(getSensorManagerSensorList(context));

        return sb.toString();
    }

    public static String toStringTelephonyManager(Context context) {
        StringBuilder sb = new StringBuilder();

        sb.append("TelephonyManager:");
        sb.append("\nCallState=").append(getTelephonyManagerCallState(context));
        sb.append("\nCellLocation=").append(getTelephonyManagerCellLocation(context));
        sb.append("\nDataActivity=").append(getTelephonyManagerDataActivity(context));
        sb.append("\nDataState=").append(getTelephonyManagerDataState(context));
        sb.append("\nDeviceId(IMEI)=").append(getTelephonyManagerDeviceId(context));
        sb.append("\nLine1Number=").append(getTelephonyManagerLine1Number(context));
        sb.append("\nNeighboringCellInfo=").append(getTelephonyManagerNeighboringCellInfo(context));
        sb.append("\nNetworkCountryIso=").append(getTelephonyManagerNetworkCountryIso(context));
        sb.append("\nNetworkOperator=").append(getTelephonyManagerNetworkOperator(context));
        sb.append("\nNetworkOperatorName=").append(getTelephonyManagerNetworkOperatorName(context));
        sb.append("\nNetworkType=").append(getTelephonyManagerNetworkType(context));
        sb.append("\nPhoneType=").append(getTelephonyManagerPhoneType(context));
        sb.append("\nSimCountryIso=").append(getTelephonyManagerSimCountryIso(context));
        sb.append("\nSimOperator=").append(getTelephonyManagerSimOperator(context));
        sb.append("\nSimOperatorName=").append(getTelephonyManagerSimOperatorName(context));
        sb.append("\nSimSerialNumber=").append(getTelephonyManagerSimSerialNumber(context));
        sb.append("\nSimState=").append(getTelephonyManagerSimState(context));
        sb.append("\nSoftwareVersion=").append(getTelephonyManagerSoftwareVersion(context));
        sb.append("\nSubscriberId=").append(getTelephonyManagerSubscriberId(context));
        sb.append("\nVoiceMailAlphaTag=").append(getTelephonyManagerVoiceMailAlphaTag(context));
        sb.append("\nVoiceMailNumber=").append(getTelephonyManagerVoiceMailNumber(context));

        return sb.toString();
    }
}
