package com.crazylegend.kotlinextensions.intent

import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.SET_ALARM
import android.app.Activity
import android.app.SearchManager
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.*
import android.widget.TimePicker
import androidx.annotation.RequiresPermission
import androidx.print.PrintHelper


/**
 * Created by hristijan on 3/28/19 to long live and prosper !
 */


fun Activity.pickImage(PICK_PHOTO_REQUEST_CODE: Int) {
    val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    )
    intent.setURIPermissionFlags()
    intent.type = "image/*"
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_REQUEST_CODE)
}


fun Activity.pickVideo(PICK_VIDEO_REQUEST_CODE: Int) {
    val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    )
    intent.setURIPermissionFlags()
    intent.type = "video/*"
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_VIDEO_REQUEST_CODE)
}


fun Activity.pickPDF(PICK_PDF_CODE: Int) {
    val intentPDF = Intent(Intent.ACTION_GET_CONTENT)
    intent.setURIPermissionFlags()
    intentPDF.type = "application/pdf"
    startActivityForResult(Intent.createChooser(intentPDF, "Select Picture"), PICK_PDF_CODE)
}

@RequiresPermission(allOf = [SET_ALARM])
fun Activity.createAlarm(message: String, hour: Int, minutes: Int) {
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

@RequiresPermission(allOf = [SET_ALARM])
fun Activity.startTimer(message: String, seconds: Int) {
    val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_LENGTH, seconds)
        putExtra(AlarmClock.EXTRA_SKIP_UI, true)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}


fun Activity.addEvent(title: String, location: String, begin: Long, end: Long) {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, title)
        putExtra(CalendarContract.Events.EVENT_LOCATION, location)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}


fun Activity.selectContact(REQUEST_SELECT_CONTACT: Int) {
    val intent = Intent(Intent.ACTION_PICK).apply {
        type = ContactsContract.Contacts.CONTENT_TYPE
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivityForResult(intent, REQUEST_SELECT_CONTACT)
    }
}

fun Activity.editContact(contactUri: Uri, email: String) {
    val intent = Intent(Intent.ACTION_EDIT).apply {
        data = contactUri
        putExtra(ContactsContract.Intents.Insert.EMAIL, email)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.insertContact(name: String, email: String) {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        type = ContactsContract.Contacts.CONTENT_TYPE
        putExtra(ContactsContract.Intents.Insert.NAME, name)
        putExtra(ContactsContract.Intents.Insert.EMAIL, email)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.showMap(geoLocation: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = geoLocation
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.playMedia(file: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = file
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.playSearchArtist(artist: String) {
    val intent = Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH).apply {
        putExtra(MediaStore.EXTRA_MEDIA_FOCUS, MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE)
        putExtra(MediaStore.EXTRA_MEDIA_ARTIST, artist)
        putExtra(SearchManager.QUERY, artist)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

/*
fun Activity.createNote(subject: String, text: String) {
    val intent = Intent(NoteIntents.ACTION_CREATE_NOTE).apply {
        putExtra(NoteIntents.EXTRA_NAME, subject)
        putExtra(NoteIntents.EXTRA_TEXT, text)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}*/

@RequiresPermission(allOf = [CALL_PHONE])
fun Activity.dialPhoneNumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.searchWeb(query: String) {
    val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
        putExtra(SearchManager.QUERY, query)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}


/**
 * Available types
 *
ACTION_SETTINGS
ACTION_WIRELESS_SETTINGS
ACTION_AIRPLANE_MODE_SETTINGS
ACTION_WIFI_SETTINGS
ACTION_APN_SETTINGS
ACTION_BLUETOOTH_SETTINGS
ACTION_DATE_SETTINGS
ACTION_LOCALE_SETTINGS
ACTION_INPUT_METHOD_SETTINGS
ACTION_DISPLAY_SETTINGS
ACTION_SECURITY_SETTINGS
ACTION_LOCATION_SOURCE_SETTINGS
ACTION_INTERNAL_STORAGE_SETTINGS
ACTION_MEMORY_CARD_SETTINGS
 */
fun Activity.openSettingsCategory(category: String) {
    val intent = Intent(category)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.openSettingsCategory(category: String, resultCode: Int) {
    val intent = Intent(category)
    if (intent.resolveActivity(packageManager) != null) {
        startActivityForResult(intent, resultCode)
    }
}

fun Activity.openWifiSettings() {
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}


fun Activity.composeMmsMessage(message: String, attachment: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        data = Uri.parse("smsto:")  // This ensures only SMS apps respond
        putExtra("sms_body", message)
        putExtra(Intent.EXTRA_STREAM, attachment)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.openWebPage(url: String) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Activity.doPhotoPrint(drawable: Int, jobName: String) {
    PrintHelper(this).apply {
        scaleMode = PrintHelper.SCALE_MODE_FIT
    }.also { printHelper ->
        val bitmap = BitmapFactory.decodeResource(resources, drawable)
        printHelper.printBitmap(jobName, bitmap)
    }
}


fun Activity.multipleImagesIntent(requestCode: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.setURIPermissionFlags()
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
    intent.action = Intent.ACTION_GET_CONTENT
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode)
}

/**
 * Available types
 *
ACTION_SETTINGS
ACTION_WIRELESS_SETTINGS
ACTION_AIRPLANE_MODE_SETTINGS
ACTION_WIFI_SETTINGS
ACTION_APN_SETTINGS
ACTION_BLUETOOTH_SETTINGS
ACTION_DATE_SETTINGS
ACTION_LOCALE_SETTINGS
ACTION_INPUT_METHOD_SETTINGS
ACTION_DISPLAY_SETTINGS
ACTION_SECURITY_SETTINGS
ACTION_LOCATION_SOURCE_SETTINGS
ACTION_INTERNAL_STORAGE_SETTINGS
ACTION_MEMORY_CARD_SETTINGS
 */
fun Context.openSettingsCategory(category: String) {
    val intent = Intent(category)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.openWifiSettings() {
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}


fun Context.composeMmsMessage(message: String, attachment: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        data = Uri.parse("smsto:")  // This ensures only SMS apps respond
        putExtra("sms_body", message)
        putExtra(Intent.EXTRA_STREAM, attachment)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.openWebPage(url: String) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.doPhotoPrint(drawable: Int, jobName: String) {
    PrintHelper(this).apply {
        scaleMode = PrintHelper.SCALE_MODE_FIT
    }.also { printHelper ->
        val bitmap = BitmapFactory.decodeResource(resources, drawable)
        printHelper.printBitmap(jobName, bitmap)
    }
}

fun Context.composeMessage(phone: String, message: String = "") {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        putExtra("sms_body", message)
        data = Uri.parse("sms:$phone")
    }
    try {
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.dialPhoneNumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Intent.setURIPermissionFlags() {
    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
}


fun Context.showTimePicker(
         hourOfDay: Int, minute: Int, is24Hour: Boolean,
        action: (view: TimePicker, hourOfDay: Int, minute: Int) -> Unit
) =
        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(action), hourOfDay, minute, is24Hour).show()


inline var TimePicker.hourCompat: Int
    set(value) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hour = value else currentHour = value
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hour else currentHour

inline var TimePicker.minuteCompat: Int
    set(value) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute = value else currentMinute = value
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute else currentMinute
