package com.alegla.FaceDetector.config;

import java.io.File;
import java.util.Random;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RenameFolderNameLibraryOCV implements InitializingBean{

	private static final Logger LOG = LogManager.getLogger(RenameFolderNameLibraryOCV.class);
	
	@Value("${pathLib}")
	private String pathLib;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		renameFolderOS();
	}
	
//	private void deleteFolderSO(String path) {
//		File directory = new File(path);
//		if(directory.exists()) {
//			File[] files = directory.listFiles();
//			if(null != files) {
//				for(int i = 0; i < files.length; i++) {
//					if(files[i].isDirectory()) {
//						if(files[i].getName().length() > 18) {
//							String folder = files[i].getName().substring(0,14);
//							if(folder.equals("opencv_openpnp")) {
//								try {
//									FileUtils.deleteDirectory(new File(directory.getPath() + "/" + files[i].getName()));
//								}catch(IOException e) {
//									e.printStackTrace();
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}
	
	private void renameFolderOS() {
		String newFolderOS = "opencv_".concat(String.valueOf(new Random().nextInt(1000)));
		File[] files = new File(pathLib).listFiles();
		
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				String folder = files[i].getName().substring(0,7);
				if(folder.equals("opencv_")) {
					LOG.info("------------FOLDER FOUND -> " + files[i].getName());
					boolean isExist = true;
					while(isExist) {
						newFolderOS = "opencv_".concat(String.valueOf(new Random().nextInt(1000)));
						File newPathLib = new File(pathLib.concat(newFolderOS));
						if(!newPathLib.exists()) {
							File oldName = new File(pathLib + files[i].getName());
							File renameFolder = new File(pathLib + newFolderOS);
							boolean flag = oldName.renameTo(renameFolder);
							if(flag)
								LOG.info("------------ THE FOLDER HAS RENAMED TO -> " + newFolderOS);
							else
								LOG.info("------------ ERROR TO TRY RENAME THE FOLDER");
							checkFolder(pathLib + newFolderOS);
							isExist = false;
						}
					}
					break;
				}
			}
		}
	}
		
		private void checkFolder(String path) {
			File file = new File(path);
			LOG.info("-------- VERIFYING IF THE FOLDER EXISTS......");
			if(!file.exists()) {
				LOG.warn("----- STATUS: FAILED");
				LOG.warn("----- ERROR - THE FOLDER DOESN'T EXISTS");
			}else {
				LOG.info("----- STATUS: OK");
			}
		}


		public String getPathLib() {
			return pathLib;
		}

		public void setPathLib(String pathLib) {
			this.pathLib = pathLib;
		}


	

		
}
