package com.alegla.FaceDetector.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.alegla.FaceDetector.models.Image;

public class ToolsDetection {

	private static final Logger LOG = LogManager.getLogger(ToolsDetection.class);
	
	
	public static String encodeBase64(Mat imgRect, String verificationID) throws IOException {
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", imgRect, matOfByte);
		
		byte[] bytes = matOfByte.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bufImage = ImageIO.read(in);
		
		boolean isColor = checkColorImage(bufImage);
		
		if(!isColor) {
			LOG.info("Imagen blanco y ngegro -> Sele aplica filtro de color al recorte para que se pueda analizar");
			LOG.info("Comienzo aplicacion filtro de color al recorte");
			//New filter to the image-------------------------
			for(int y = 0;y < bufImage.getHeight(); y++) {
				for(int x = 0;x < bufImage.getWidth(); x++) {
					//Retrieving contents of a pixel
					int pixel = bufImage.getRGB(x, y);
					//Creating a color object from pixel value
					Color color = new Color(pixel, true);
					//Retrieing the R G B values
					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();
					
					//Modifying the RGB values
					int avg = (red + green + blue) / 3;
					int depth = 40;
					int intensity = 50;
					red = avg + (depth*2);
					green = avg + depth;
					blue = avg - intensity;
					
					//Creating new color object 
					color = new Color(red, green, blue);
					//Setting new color to the image
					bufImage.setRGB(x, y, color.getRGB());
				}
			}
			LOG.info("Termino aplicacion de filtro al recorte con exito");
			//End of new filter--------------------------------
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bufImage, ".jpg", bos);
			byte[] byte2 = bos.toByteArray();
			//Parameter (verificationId, imageOriginal, imageResize, faceDetected, statusImageImage)
			//saveImage(verificationId, null, null, byte2, "NoColor");			
			//return Base64.encodeBytes(byte2, DONT_BREAK_LINES).toString();
			return Base64.getEncoder().encodeToString(byte2);
		}else {
			//Parameter (verificationId, imageOriginal, imageResize, faceDetected, statusImageImage)
			//saveImage(verificationId, null, null, byte, "Color");
			//return Base64.encodeBytes(bytes, DONT_BREAK_LINES).toString();
			return Base64.getEncoder().encodeToString(bytes);
		}
	}
	
	
	private static boolean checkColorImage(BufferedImage bufImage) {
		boolean isColor = false;
		for(int y = 0; y < bufImage.getHeight(); y++) {
			for(int x = 0; x < bufImage.getWidth(); x++) {
				//Retrieving contents of a pixel
				int pixel = bufImage.getRGB(x, y);
				//Creating a color object from pixel value
				Color color = new Color(pixel, true);
				//Retrieving the R G B values
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				
				if(red != green && red != blue && blue != green) {
					isColor = true;
					break;
				}
				else isColor = false;
			}
		}
		return isColor;
	}
	
	public void isRotate(Mat src, Mat dst) {
		Core.transpose(src, dst);
		Core.flip(dst, dst, 0);
	}
	
	public static void rotateImage(Mat src, Mat dst, int p) {
		if(p == Core.ROTATE_90_CLOCKWISE || p == Core.ROTATE_90_COUNTERCLOCKWISE) {
			Core.transpose(src, dst);
			Core.flip(dst, dst, p);
		}else {
			Core.flip(src, dst, p);
		}
	}
	
	public static List<Image> checkFaceObteined(List<Image> facesDetected, CascadeClassifier classifier, int faces2Detect, String xmlToValidate, String imageType){
		List<Image> facesChecked = new ArrayList<Image>();
		classifier = new CascadeClassifier(xmlToValidate);
		LOG.info("Faces detected -> " + facesDetected.size());
		LOG.info("Discanding fake detect...");
		for(int i=0; i < facesDetected.size(); i++) {
			MatOfRect faceDetected = new MatOfRect();
			Mat src = Imgcodecs.imdecode(new MatOfByte(Base64.getDecoder().decode(facesDetected.get(i).getFile())), Imgcodecs.IMREAD_UNCHANGED);
			
			classifier.detectMultiScale(src, faceDetected);
			
			if(facesChecked.size() < faces2Detect) {
				if(faceDetected.toArray().length != 0) {
					int index = 0;
					for(Rect rect: faceDetected.toArray()) {
						if(index != faces2Detect) {
							if((src.width() > 202 && src.height() > 207) && (src.height() < 420) && imageType.equals("DNI")) {
								LOG.info("The croping image is a face - DNI");
								facesChecked.add(new Image(facesDetected.get(i).getFile()));
								index++;
							}else {
								LOG.info("The croping image is a face - NO DNI");
								facesChecked.add(new Image(facesDetected.get(i).getFile()));
								index++;
							}
						}
					}
				}
			}
		}
		LOG.info("FACES CHECKED: " + facesChecked.size());
		if(facesChecked.size() == 0) {
			return null;
		}else {
			LOG.info("Total faces detected: " + facesChecked.size());
			return facesChecked;
		}
	}
	
	public static Mat resizeImage(Mat src, int attempt, String imageType) {
		if(attempt == 1 && imageType.equals("DNI")) {
			//ResizingImage (1280width x 960 height)
			Mat resizeImage = new Mat();
			//Size scale = new Size(1280, 960)
			Size scaleSize = new Size((src.width() * 120)/100, (src.height() * 140) / 100);
			Imgproc.resize(src, resizeImage, scaleSize, 2, 2, Imgproc.INTER_AREA);
			return resizeImage;
		}
		//int width = (src.width()*160) / 100;
		//int height = (src.height() * 160) / 100;
		//Size scaleSize = new Size(width, height)
		return src;
	}
	
	public static void saveImage(String verificationId, String faceDetected, String isSaved, String maxImagesStorage, String pathImages) {
		int totalImageStorage = checkQuantityImagesSaved(pathImages);
		boolean isFlagToSaved = Boolean.parseBoolean(isSaved);
		OutputStream stream = null;
		
		if(isFlagToSaved) {
			LOG.info("VerificationID: " + verificationId + "-- Total images storages -> " + totalImageStorage + "/" + maxImagesStorage);
			if(totalImageStorage < Integer.parseInt(maxImagesStorage)) {
				File theDir = new File(pathImages);
				byte[] base64 = DatatypeConverter.parseBase64Binary(faceDetected);
				String path = pathImages + "/" + "Face--" + verificationId + ".jpg";
				File file = new File(path);
				
				//Save image
				try {
					if(!theDir.exists()) {
						theDir.mkdirs();
					}
					stream = new FileOutputStream(file);
					stream.write(base64);
					LOG.info("VerificationID: " + verificationId + "-- The image was saved successfully  - OK");
				}catch(Exception ex) {
					LOG.error("There was a problem in the process to save the image: " + ex.getMessage());
				}finally {
					try {
						stream.close();
						stream.flush();
						System.gc();
					}catch(Exception e) {
						LOG.error("There was a problem in the process to sae the image: " + e.getMessage());
					}
				}
			}else {
				LOG.info("VerificationID: " + verificationId + " -- The limit of stored images was reached - The image won't save");
			}
		}else {
			LOG.info("VerificationID: " + verificationId + " -- The system to save images is desactived - The image won't save");
		}
	}
	
	private static int checkQuantityImagesSaved(String pathFolder) {
		int totalImagesSaved = 0;
		File directory = new File(pathFolder);
		if(directory.exists()) {
			File[] files = directory.listFiles();
			if(null != files) {
				totalImagesSaved = files.length;
				return totalImagesSaved;
			}
		}
		return totalImagesSaved;
	}
	
	public static String getPathLib(String pathFolder) {
		File directory = new File(pathFolder);
		if(directory.exists()) {
			File[] files = directory.listFiles();
			if(null != files) {
				for(int i=0; i < files.length; i++) {
					if(files[i].isDirectory()) {
						String folder = files[i].getName().substring(0,7);
						if(folder.equals("opencv_")) {
							//return files[i].getAbsolutePath()+ checkOS();
							return files[i].getAbsolutePath().concat("/libopencv_java342.so");
						}
					}
				}
			}
		}
		return null;
	}
	
	//private static String checkOS()	{
	//	String libName = null;
	//	LOG.info(System.getProperty("os.name"));
	//	if(SystemUtils.IS_OS_WINDOWS) {
	//		libName = "/opencv_java342.dll";
	//	}else if (SystemUtils.IS_OS_LINUX) {
	//		libName = "/libopencv_java342.so";
	//	}
	//	return libName;
	//}
	
	public static void loadLibrary(String pathLib) {
    	System.load(new File(getPathLib(pathLib)).getAbsolutePath());
    	nu.pattern.OpenCV.loadLocally();
	}
	
	public static <DataSource> ByteArrayOutputStream decodeBase64(String base64) throws IOException {
		//DataSource attachment = (DataSource) Base64.getEncoder().encodeToString(base64.getBytes());
		BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64.getBytes())));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(inputImage, "jpg", byteArrayOutputStream);
		return byteArrayOutputStream;
	}
}
