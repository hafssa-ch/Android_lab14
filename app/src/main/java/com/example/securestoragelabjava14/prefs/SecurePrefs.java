package com.example.securestoragelabjava14.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

public final class SecurePrefs {
    private static final String TAG = "SecurePrefs";
    private static final String PREFS_NAME = "secure_prefs";
    private static final String KEY_API_TOKEN = "secure_api_token";

    private SecurePrefs() {}

    private static SharedPreferences getSecurePrefs(Context context) throws GeneralSecurityException, IOException {
        // Création du MasterKey avec gestion de l'existence
        MasterKey masterKey = new MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        return EncryptedSharedPreferences.create(
                context,
                PREFS_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }

    public static void saveToken(Context context, String token) {
        try {
            getSecurePrefs(context).edit().putString(KEY_API_TOKEN, token).apply();
        } catch (Exception e) {
            Log.e(TAG, "Erreur sauvegarde token chiffré", e);
        }
    }

    public static String loadToken(Context context) {
        try {
            return getSecurePrefs(context).getString(KEY_API_TOKEN, "");
        } catch (Exception e) {
            Log.e(TAG, "Erreur chargement token chiffré", e);
            return "";
        }
    }

    public static void clear(Context context) {
        try {
            getSecurePrefs(context).edit().clear().apply();
        } catch (Exception e) {
            Log.e(TAG, "Erreur effacement token chiffré", e);
        }
    }
}