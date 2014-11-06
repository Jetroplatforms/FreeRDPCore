package com.freerdp.freerdpcore.sharedobjects.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.freerdp.freerdpcore.sharedobjects.utils.Logger.LogLevel;

/**
 * This FileSystemManager class is responsible for saving and getting images
 * from the FS. It supports saving to both the external and internal memory.
 * 
 */
public class FileSystemManagerServices {

	// ******************************************************************************************
	// Image:

	// Path to save image following the root of the external storage directory
	private static final String EXTERNAL_STORAGE_IMAGE_PATH = "Android/data/data/Images/jetro";
	// Path to save image following the root of the internal storage directory
	private static final String INTERNAL_STORAGE_IMAGE_PATH = "/Images/jetro";
	// Path to save json files to internal
	private final static String JSON_FILES_PATH = "/json_files";
	// Path to save html templates
	private final static String HTML_FILES_PATH = "/html_files";
	// The format to save the image
	private final static String SAVE_FORMAT_FOR_IMAGE = "png";

	// ******************************************************************************************
	// Parameters

	private final Context _context;

	private boolean _overrideExisting;
	private boolean _saveToInternalStorage;
	private boolean _saveToExternalStorage;
	private boolean _defaultGetFromExternalStorage;

	// ******************************************************************************************
	// Constructor

	/**
	 * Sets the FileSystemManager params. Can save both to the external or the
	 * internal but needs to specify where to get from.
	 * 
	 * @param context
	 *            - context by which we get the root folder of the external or
	 *            the internal memory
	 * @param saveToInternalStorage
	 *            - True to save to the internal memory
	 * @param saveToExternalStorage
	 *            - True to save to the external memory.
	 * @param defaultGetFromExternalStorage
	 *            - Sets the default value of where to get the bitmap from. true
	 *            will get from the external storage while false will get from
	 *            the internal
	 * @param overrideExisting
	 *            - True if to override existing images in the FS.
	 */
	public FileSystemManagerServices(final Context context,
			boolean saveToInternalStorage, boolean overrideExisting) {

		_context = context;
		_saveToInternalStorage = saveToInternalStorage;
		_saveToExternalStorage = false;
		_defaultGetFromExternalStorage = false;
		_overrideExisting = overrideExisting;

	}

	// ******************************************************************************************
	// Setters

	public void setOverrideExisting(boolean toOverride) {

		_overrideExisting = toOverride;

	}

	public void setSaveToExternal(boolean toSaveToExternal) {

		_saveToExternalStorage = toSaveToExternal;

	}

	public void setSaveToInternal(boolean toSaveToInternal) {

		_saveToInternalStorage = toSaveToInternal;

	}

	public void setToGetFromExternalByDetault(boolean toGetFromExternal) {

		_defaultGetFromExternalStorage = toGetFromExternal;

	}

	// *******************************************************************
	// For bitmaps

	/**
	 * Saves the bitmap to the FS, to the external or internal memory or both
	 * 
	 * @param bitmapName
	 *            - The key name by which to save the bitmap.
	 * @param targetHeight
	 * @param targetWidth
	 * @param bitmap
	 *            - The bitmap to save.
	 * @return - True if succeeded saving. If needs to save both to the external
	 *         and the internal will return true only if both succeeded.
	 */
	public boolean saveBitmap(String bitmapName, final Bitmap bitmap) {
		String imagesFolderPath = null;
		boolean saveToExternalSucceeded = false;
		boolean saveToInternalSucceeded = false;

		try {
			Logger.log(LogLevel.INFO, "Do cache bitmap: " + bitmapName);
			if (_saveToExternalStorage) {
				// Getting the path in the external storage to save the bitmap
				// to
				imagesFolderPath = Environment.getExternalStorageDirectory()
						+ "/" + EXTERNAL_STORAGE_IMAGE_PATH;
				final File filePath = new File(imagesFolderPath);
				// Creating the folder if it wasn't already created
				filePath.mkdirs();

				final String fileNameToSaveImage = bitmapName + "."
						+ SAVE_FORMAT_FOR_IMAGE;
				final File file = new File(imagesFolderPath,
						fileNameToSaveImage);

				saveToExternalSucceeded = saveBitmapToFile(file, bitmap,
						_overrideExisting);

			}
			if (_saveToInternalStorage) {
				// Getting the path in the internal memory to save the bitmap to
				File filesDirPath = _context.getFilesDir();
				filesDirPath.mkdirs();
				imagesFolderPath = filesDirPath.getAbsolutePath()
						+ INTERNAL_STORAGE_IMAGE_PATH;
				File filePathToImagesFolderPath = new File(imagesFolderPath);
				// Creating the folder if it wasn't already created
				filePathToImagesFolderPath.mkdirs();

				final String fileNameToSaveImage = convertStringToLegalFile(bitmapName)
						+ "." + SAVE_FORMAT_FOR_IMAGE;
				final File file = new File(imagesFolderPath,
						fileNameToSaveImage);

				saveToInternalSucceeded = saveBitmapToFile(file, bitmap,
						_overrideExisting);
			}
		} catch (Exception e) {
			Logger.e("FileSystemManagerService.saveBitmap", e,
					"Error caching Bitmap: " + bitmapName);
			return false;
		}

		// If not saving at all return false
		if (!_saveToExternalStorage && !_saveToInternalStorage) {

			return false;

		}
		// If set to save to external storage but failed saving
		if (_saveToExternalStorage && !saveToExternalSucceeded) {

			return false;

		}
		// If set to save to internal storage but failed saving
		if (_saveToInternalStorage && !saveToInternalSucceeded) {

			return false;

		}
		return true;
	}

	/**
	 * @param file
	 *            - The file to save the bitmap to
	 * @param bitmap
	 *            - The bitmap to save
	 * @param toOverrideIfExists
	 *            - If the file already exists this param decides weather to
	 *            override the file and save the bitmap again or to cancel the
	 *            save.
	 * @return true if saved the bitmap (if already existed and
	 *         toOverrideIfExists==false will return false)
	 */
	private boolean saveBitmapToFile(File file, Bitmap bitmap,
			boolean toOverrideIfExists) {
		try {
			if (bitmap == null) {
				return false;
			}
			if (!toOverrideIfExists && file.exists()) {
				return false;
			}

			OutputStream fOut = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
			fOut = null;
			Log.d("Text",
					"Lior: FileSystemManager: saveToFS: saved bitmap: file.getAbsolutePath(): "
							+ file.getAbsolutePath() + " toOverrideIfExists: "
							+ toOverrideIfExists);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Text",
					"Lior: Exception: FileSystemManager: saveToFS: saveBitmapToFile: file.getAbsolutePath(): "
							+ file.getAbsolutePath()
							+ " toOverrideIfExists: "
							+ toOverrideIfExists, e);
			return false;
		}
	}

	/**
	 * @param bitmapName
	 *            - The key name by which to get the bitmap.
	 * @param fromExternalStorage
	 *            - To load the file from the external or the internal memory
	 * @return - The bitmap if it exists or null if it doesn't
	 */
	public Bitmap getBitmap(String bitmapName, boolean fromExternalStorage) {
		try {
			final String imagesFolderPath;
			final String fileNameToLoadImage;
			if (fromExternalStorage) { // For external storage
				imagesFolderPath = Environment.getExternalStorageDirectory()
						+ "/" + EXTERNAL_STORAGE_IMAGE_PATH;
			} else { // For internal storage
				File filePath = _context.getFilesDir();
				filePath.mkdirs();
				imagesFolderPath = filePath.getAbsolutePath()
						+ INTERNAL_STORAGE_IMAGE_PATH;
			}
			// *******************************
			fileNameToLoadImage = convertStringToLegalFile(bitmapName) + "."
					+ SAVE_FORMAT_FOR_IMAGE;
			final String completePathToImage = imagesFolderPath + "/"
					+ fileNameToLoadImage;

			final Bitmap bitmapFromFS = BitmapFactory
					.decodeFile(completePathToImage);
			return bitmapFromFS;

		} catch (final Exception e) {
			Log.e("Text",
					"Exception: FileSystemManager: getBitmap: bitmapFileName: "
							+ bitmapName + " fromExternalStorage: "
							+ fromExternalStorage, e);
			return null;
		}
	}

	/**
	 * Gets the bitmap from the external or internal memory by default as
	 * specified in the constructor
	 * 
	 * @param bitmapName
	 *            - The key name by which to get the bitmap.
	 * @param targetHeight
	 * @param targetWidth
	 * @return - The bitmap if it exists or null if it doesn't
	 */
	public Bitmap getBitmap(String bitmapName) {
		return getBitmap(convertStringToLegalFile(bitmapName),
				_defaultGetFromExternalStorage);
	}

	private String convertStringToLegalFile(String str) {
		String legalStr = "";

		try {
			legalStr = str.replaceAll("/", "_");
			legalStr = legalStr.replace(".", "_");
		} catch (Exception e) {
			Logger.log(LogLevel.ERROR, "Error converting " + str
					+ " to legal cache file string ");

			return null;
		}

		return legalStr;
	}

	/**
	 * Save the JSON as String to file
	 * 
	 * @param fileName
	 *            - JSON file name
	 * @param jsonString
	 *            - the JSON as string
	 * @param toOverrideIfExists
	 *            - override the existing file(on version upgrade)
	 * @return
	 */
	public boolean saveJsonAsStringToInternal(String fileName,
			String jsonString, boolean toOverrideIfExists) {
		String jsonFolderPath = null;
		boolean saveToInternalSucceeded = false;

		try {
			if (_saveToInternalStorage) {
				// Getting the path in the internal memory to save the bitmap to
				File filesDirPath = _context.getFilesDir();
				filesDirPath.mkdirs();
				jsonFolderPath = filesDirPath.getAbsolutePath()
						+ JSON_FILES_PATH;
				File filePathToJsonFilesFolderPath = new File(jsonFolderPath);
				// Creating the folder if it wasn't already created
				filePathToJsonFilesFolderPath.mkdirs();
				// creating the file
				final File file = new File(filePathToJsonFilesFolderPath,
						fileName);
				FileOutputStream fos = new FileOutputStream(file);
				// writing string to file
				fos.write(jsonString.getBytes());
				fos.flush();
				fos.close();
				fos = null;

				return true;
			}
		} catch (Exception e) {
			Logger.log(LogLevel.ERROR, "Error saving json: " + fileName);
			return false;
		}

		// If not saving at all return false
		if (!_saveToExternalStorage && !_saveToInternalStorage) {

			return false;

		}
		// If set to save to internal storage but failed saving
		if (_saveToInternalStorage && !saveToInternalSucceeded) {

			return false;
		}
		return false;
	}

	/**
	 * Save an html file after extracting the content from a given url
	 * 
	 * @param fileName
	 *            - the file name to save with
	 * @param htmlContent
	 *            - the html content
	 */
	public void saveHtmlFileAsString(String fileName, String htmlContent) {
		String htmlFolderPath = null;

		try {
			if (_saveToInternalStorage) {
				// Getting the path in the internal memory to save the bitmap to
				File filesDirPath = _context.getFilesDir();
				filesDirPath.mkdirs();
				htmlFolderPath = filesDirPath.getAbsolutePath()
						+ HTML_FILES_PATH;
				File filePathToJsonFilesFolderPath = new File(htmlFolderPath);
				// Creating the folder if it wasn't already created
				filePathToJsonFilesFolderPath.mkdirs();
				// creating the file
				final File file = new File(filePathToJsonFilesFolderPath,
						fileName);
				FileOutputStream fos = new FileOutputStream(file);
				// writing string to file
				fos.write(htmlContent.getBytes());
				fos.flush();
				fos.close();
				fos = null;
			}
		} catch (Exception e) {
			Logger.log(LogLevel.ERROR, "Error saving html file : " + fileName);
		}
	}

	/**
	 * Load JSON file from internal storage
	 * 
	 * @param fileName
	 *            - JSON file name
	 * @return - json file as string to parse
	 */
	public String loadJsonFileFromInternal(String fileName)
			throws FileNotFoundException {
		FileInputStream fis;
		BufferedReader reader;
		String jsonAsString = null;

		try {
			File filesDirPath = _context.getFilesDir();
			filesDirPath.mkdirs();
			String jsonFilePath = filesDirPath.getAbsolutePath()
					+ JSON_FILES_PATH;
			File filePathToJsonFilesFolderPath = new File(jsonFilePath,
					fileName);
			fis = new FileInputStream(filePathToJsonFilesFolderPath);
			reader = new BufferedReader(new InputStreamReader(fis));
			jsonAsString = reader.readLine();
			fis.close();
		}

		catch (IOException e) {
			Logger.e(e, "Error loading file: " + fileName);
		}

		return jsonAsString;
	}

	/**
	 * Delete old file JSON that was saved(in case of file version change)
	 * 
	 * @param fileName
	 *            - the file name to delete
	 * @return - true if succeeded, false otherwise
	 */
	public boolean deleteOldJsonFile(String fileName) {
		try {
			File filesDirPath = _context.getFilesDir();
			filesDirPath.mkdirs();
			String jsonFilePath = filesDirPath.getAbsolutePath()
					+ JSON_FILES_PATH;
			File filePathToJsonFilesFolderPath = new File(jsonFilePath,
					fileName);
			boolean succeeded = filePathToJsonFilesFolderPath.delete();
			return succeeded;
		} catch (Exception e) {
			Logger.e(e, "Error deleting file: " + fileName);
			return false;
		}
	}

	/**
	 * method for deleting old day products images and replace with new ones
	 */
	public void deleteFilesFromDirectory(String fileOrDirectory) {
		File filesDirPath = _context.getFilesDir();
		File file = new File(filesDirPath + fileOrDirectory);
		if (file.isDirectory()) {
			for (File child : file.listFiles())
				child.delete();
		} else {
			file.delete();
		}
	}
}
